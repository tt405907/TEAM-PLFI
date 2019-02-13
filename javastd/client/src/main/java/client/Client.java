package client;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import commun.Coup;
import commun.Dessin;
import commun.Forme;
import commun.Identification;
import commun.jeux.ResultCTR;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Client {

    Identification moi = new Identification("Chicken dinner K/D GOD", 42);

    Socket connexion;
    int propositionCourante = 50;

    // Objet de synchro
    final Object attenteDéconnexion = new Object();

    public Client(String urlServeur) {

        try {
            connexion = IO.socket(urlServeur);

            System.out.println("on s'abonne à la connection / déconnection ");;

            connexion.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(" on est connecté ! et on s'identifie ");

                    // on s'identifie
                    JSONObject id = new JSONObject(moi);
                    connexion.emit("identification", id);

                }
            });

            connexion.on("disconnect", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(" !! on est déconnecté !! ");
                    connexion.disconnect();
                    connexion.close();

                    synchronized (attenteDéconnexion) {
                        attenteDéconnexion.notify();
                    }
                }
            });


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
                    	
                        System.out.println(result);
                        /*if (result.getResult().equals(ResultCTR.CLIENT_GAGNE)) {
                        	System.out.println("gg no re");
                        	return;
                        }*/
                    }
                    System.out.println("On rejoue");
                    envoyerForme();
                }
            });



        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
    
    private void envoyerForme() {
        connexion.emit("playctr",  new JSONObject(new Dessin(Forme.CARRE.ordinal())));
    }

    private void seConnecter() {
        // on se connecte
        connexion.connect();

        System.out.println("en attente de déconnexion");
        synchronized (attenteDéconnexion) {
            try {
                attenteDéconnexion.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("erreur dans l'attente");
            }
        }
    }

    public static final void main(String []args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Client client = new Client("http://127.0.0.1:10101");
        client.seConnecter();



        System.out.println("fin du main pour le client");

    }

}