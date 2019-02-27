package plfi.plfi;

import android.util.Log;
import android.widget.ImageView;

import java.net.SocketAddress;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import commun.Identification;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


import commun.Dessin;

public class Connexion  {

    private final Controleur controleur;
    Socket connexion;
    ImageView imageInitClient; // Image de depart du cleint
    ImageView imageInitServeur; // Image de depart a voir si on l'a met pas apres le resultat pour temp, a voir avec le groupe
    ImageView imageViewClient;

    final Object attenteDéconnexion = new Object();
    public JSONObject res;
    public String url;
    public String ip;
    public String port;

    public Connexion(String ip,String port, Controleur ctrl) {
        this.controleur = ctrl;
        controleur.setConnexion(this);

        try {
            connexion = IO.socket("http://"+ip+":"+port);

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
                    envoyerForme();
                }
            });

            connexion.on("resultctr", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("on a reçu un résultat avec "+objects.length+" paramètre(s) ");
                    if (objects.length > 0 ) {
                        JSONObject result = (JSONObject)objects[0];
                        res = result;
                        System.out.println(res);
                        try{
                            JSONObject r = res;
                            String winner = r.getString("result");
                            String imageserv = r.getString("formeServeur");
                            controleur.resultCTR(winner, imageserv);
                            /*

                            */
                        }
                        catch (JSONException e){
                            System.out.println(e);
                        }
                        /*if (result.getResult().equals(ResultCTR.CLIENT_GAGNE)) {
                        	System.out.println("gg no re");
                        	return;
                        }*/
                    }
                }
            });



        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("playctr", "socket avec  pb !!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }


    public JSONObject getRes(){
        return this.res;
    }

    private void envoyerForme() {
        Dessin dessin = new Dessin(2);
        JSONObject pieceJointe = new JSONObject();
        try {
            pieceJointe.put("formes", dessin);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        connexion.emit("playctr", pieceJointe);
    }

    public void sendForme(int forme) {
        Dessin dessin = new Dessin(forme);
        JSONObject pieceJointe = new JSONObject();
        try {
            pieceJointe.put("valeur", dessin.getValeur());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.connexion.emit("playctr", pieceJointe);
    }
    public void seConnecter() {
        // on se connecte
        Log.e("debug", "essaie de se connecter");
        this.connexion.connect();
        Log.e("debug", "connecté ?");

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

    public void envoyerCoup(int val) {
        connexion.emit("réponse",val);
    }
}
