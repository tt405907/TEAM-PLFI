package plfi.plfi;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import commun.Dessin;
import commun.Identification;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Connexion  {

    private final Controleur controleur;
    Socket connexion;
    public JSONObject res;
    public Connexion(String urlServeur, Controleur ctrl) {
        this.controleur = ctrl;
        controleur.setConnexion(this);

        try {
            connexion = IO.socket(urlServeur);

            System.out.println("on s'abonne à la connection / déconnection ");;

            connexion.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(" on est connecté ! et on s'identifie ");


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
                        /*if (result.getResult().equals(ResultCTR.CLIENT_GAGNE)) {
                        	System.out.println("gg no re");
                        	return;
                        }*/
                    }
                }
            });



        } catch (URISyntaxException e) {
            e.printStackTrace();
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
        connexion.emit("playctr", pieceJointe);
    }
    public void seConnecter() {
        // on se connecte
        Log.e("debug", "essaie de se connecter");
        connexion.connect();

    }

    public void envoyerId(Identification moi) {
        // pas de conversion automatique obj <-> json avec le json de base d'android
        JSONObject pieceJointe = new JSONObject();
        try {
            pieceJointe.put("nom", moi.getNom());
            pieceJointe.put("niveau", moi.getNiveau());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        connexion.emit("identification", pieceJointe);
    }

    public void envoyerCoup(int val) {
        connexion.emit("réponse",val);
    }

    public static class gamePRINT {
    }
}
