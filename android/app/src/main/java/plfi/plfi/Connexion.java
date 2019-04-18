package plfi.plfi;

import android.util.Log;
import android.widget.ImageView;

import java.net.SocketAddress;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import commun.Identification;
import commun.Point;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import commun.Dessin;

public class Connexion {

    private final Controleur controleur;
    Socket connexion;
    ImageView imageInitClient; // Image de depart du cleint
    ImageView imageInitServeur; // Image de depart a voir si on l'a met pas apres le resultat pour temp, a voir
                                // avec le groupe
    ImageView imageViewClient;

    final Object attenteDéconnexion = new Object();
    public JSONObject res;
    public String url;
    public String ip;
    public String port;

    public Connexion(String ip, String port, Controleur ctrl) {
        this.controleur = ctrl;
        controleur.setConnexion(this);

        try {
            connexion = IO.socket("http://" + ip + ":" + port);

            Log.e("playrtc", "on s'abonne à la connection / déconnection ");

            connexion.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(" on est connecté ! et on s'identifie ");
                    JSONObject json = new JSONObject();
                    try {
                        json.put("nom", controleur.getidentification().getNom());
                        connexion.emit("identification", json);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

            connexion.on("disconnect", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(" !! on est déconnecté !! ");
                    connexion.disconnect();
                    connexion.close();

                }
            });

            // on recoit une question
            // on recoit une question
            connexion.on("question", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("Question!");
                }
            });

            connexion.on("resultatenigme", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("on a reçu un résultat avec " + objects.length + " paramètre(s) ");
                    if (objects.length > 0) {
                        Boolean result = (Boolean) objects[0];
                        String out;
                        if (result){
                            out = " JUSTE";
                        }
                        else {
                            out = "  FAUX";
                        }
                        controleur.
                                enigmeReponseTextview(out);

                    }
                }
            });

            connexion.on("resultatreflex", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("on a reçu un résultat avec " + objects.length + " paramètre(s) ");
                    if (objects.length > 0) {
                        Boolean result = (Boolean) objects[0];
                        String out;
                        if (result){
                            out = " JUSTE";
                        }
                        else {
                            out = "  FAUX";
                        }
                        controleur.reponseTextviewReflex(out);

                    }
                }
            });
            //Reception aléatoire entre carre rond carre
            connexion.on("reflextarget", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("on a reçu un résultat avec " + objects.length + " paramètre(s) ");
                    if (objects.length > 0) {
                        String out= (String) objects[0];
                        controleur.updateTextviewReflex(out);
                    }
                }
            });

            connexion.on("enigme", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("on a reçu un résultat avec " + objects.length + " paramètre(s) ");
                    if (objects.length > 0) {
                        String result = (String) objects[0];
                        controleur.enigmeTextview(result);

                    }
                }
            });

            connexion.on("stats", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("stats");
                    if (objects.length > 0) {
                        JSONObject result = (JSONObject) objects[0];
                        res = result;
                        System.out.println(res);
                        try {
                            JSONObject r = res;
                            JSONObject statEnigme = res.getJSONObject("statsEnigme");
                            JSONObject TCR = res.getJSONObject("statsTCR");
                            if(controleur.isEnigme()){
                                String bonne = statEnigme.getString("bonnes");
                                String mauvaise = statEnigme.getString("mauvaises");
                                String total = statEnigme.getString("total");
                                controleur.resultStatsEnigme(bonne,total,mauvaise);
                            }
                            else if(controleur.isRTC()){
                                System.out.println("stat tcr");
                                statEnigme = TCR;
                                String triangle = statEnigme.getString("triangles");
                                String carre = statEnigme.getString("carres");
                                String rond = statEnigme.getString("ronds");

                                String total = statEnigme.getString("total");
                                String defaite = statEnigme.getString("defaites");
                                String bonne = statEnigme.getString("victoires");
                                String egalite = statEnigme.getString("egalites");

                                controleur.resultStatsTCR(triangle,carre,rond,bonne,defaite,egalite,total);

                            }
                            else {
                                System.out.println("stat reflex");
                                JSONObject stats = res.getJSONObject("statsReflex");
                                //(String toutes_mauvaises_reponses,String score_max,String parties,String toutes_bonnes_réponses);
                                String tmp = stats.getString("mauvais");
                                String sm = stats.getString("max");
                                String p = stats.getString("parties");
                                String tbr = stats.getString("bons");

                                controleur.resultatStatsReflex(tmp,sm,p,tbr);
                            }
                            /*

                             */
                        } catch (JSONException e) {
                            System.out.println(e);
                        }
                        /*
                         * if (result.getResult().equals(ResultCTR.CLIENT_GAGNE)) {
                         * System.out.println("gg no re"); return; }
                         */
                    }
                }
            });





            connexion.on("resultctr", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("on a reçu un résultat avec " + objects.length + " paramètre(s) ");
                    if (objects.length > 0) {
                        JSONObject result = (JSONObject) objects[0];
                        res = result;
                        System.out.println(res);
                        try {
                            JSONObject r = res;
                            String winner = r.getString("result");
                            String imageserv = r.getString("formeServeur");
                            controleur.resultCTR(winner, imageserv);
                            /*
                            
                            */
                        } catch (JSONException e) {
                            System.out.println(e);
                        }
                        /*
                         * if (result.getResult().equals(ResultCTR.CLIENT_GAGNE)) {
                         * System.out.println("gg no re"); return; }
                         */
                    }
                }
            });

        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("playctr", "socket avec  pb !!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }

    public JSONObject getRes() {
        return this.res;
    }

    private JSONObject dessinToJSON(Dessin dessin) throws JSONException {
        JSONObject json = new JSONObject();
        JSONArray points = new JSONArray();
        for (Point p : dessin.getPoints()) {
            JSONObject jp = new JSONObject();
            jp.put("x", p.getX());
            jp.put("y", p.getY());
            points.put(jp);
        }
        json.put("points", points);
        return json;
    }

    public void sendForme(List<Point> points,String emit) {



        Dessin dessin = Dessin.fromList(points);
        try {
            this.connexion.emit(emit, dessinToJSON(dessin));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean seConnecter() {
        // on se connecte
        Log.e("debug", "essaie de se connecter");
        try {
            this.connexion.connect();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    public void seDeconnecter(){
        try{
            this.connexion.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void envoyerId(Identification moi) {
        // pas de conversion automatique obj <-> json avec le json de base d'android
        JSONObject pieceJointe = new JSONObject();
        try {
            pieceJointe.put("nom", moi.getNom());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        connexion.emit("identification", pieceJointe);
    }

    public void envoyerIdEnigme(Identification moi) {
        // pas de conversion automatique obj <-> json avec le json de base d'android
        JSONObject pieceJointe = new JSONObject();
        try {
            pieceJointe.put("nom", moi.getNom());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        connexion.emit("demandeenigme", pieceJointe);
    }

    public void envoyerIdReflex(Identification moi) {
        // pas de conversion automatique obj <-> json avec le json de base d'android
        JSONObject pieceJointe = new JSONObject();
        try {
            pieceJointe.put("nom", moi.getNom());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        connexion.emit("startreflex", pieceJointe);
    }

    public void demanderStats(Identification moi){
        JSONObject pieceJointe = new JSONObject();
        try {
            pieceJointe.put("nom", moi.getNom());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        connexion.emit("demandestats", pieceJointe);
    }


    public void envoyerCoup(int val) {
        connexion.emit("réponse", val);
    }

    public static class gamePRINT {
    }
}
