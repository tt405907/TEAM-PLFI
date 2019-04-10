package client;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import commun.Dessin;
import commun.Forme;
import commun.Identification;
import commun.Point;
import commun.jeux.ResultCTR;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Utilisé pour tester rapidement les envois de dessins, car plus léger que le client Android
 */
public class Client {

    Identification moi = new Identification("Chicken dinner K/D GOD");

    private int reflexTries;

    Socket connexion;

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

                    //envoyerForme();
                    //connexion.emit("demandeenigme", new JSONObject(moi));
                    connexion.emit("startreflex", new JSONObject(moi));
                }
            });
            
            connexion.on("resultctr", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("on a reçu un résultat avec "+objects.length+" paramètre(s) ");
                    if (objects.length > 0 ) {
                    	ResultCTR result = resultFromJSON((JSONObject)objects[0]);
                    	
                        System.out.println(result);
                        if (result != null && result.getResult().equals(ResultCTR.CLIENT_GAGNE)) {
                        	System.out.println("gg no re");
                        	demanderStats();
                        	return;
                        }
                    }
                    System.out.println("On rejoue");
                    envoyerForme();
                }
            });


            // Enigme
            connexion.on("enigme", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("Enigme reçue!");
                    System.out.println(objects[0]);
                    
                    envoyerCarreEnigme();
                }
            });


            // Réponse énigme
            connexion.on("resultatenigme", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("Résultat de l'énigme");
                    boolean result = (Boolean)objects[0];
                    if (result) System.out.println("Correct! :)");
                    else System.out.println("Pas correct! :(");
                }
            });


            // Stats
            connexion.on("stats", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("Stats reçus!");
                    System.out.println(objects[0]);
                    connexion.close();
                }
            });
            
            // Reflex
            connexion.on("reflextarget", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("Forme cible reçue!");
                    System.out.println(objects[0]);
                    
                    reflexTries = 5;
                    envoyerCarreReflex();
                    
                }
            });


            // Réponse énigme
            connexion.on("resultatreflex", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("Résultat du reflex");
                    boolean result = (Boolean)objects[0];
                    if (result) System.out.println("Correct! :)");
                    else System.out.println("Pas correct! :(");
                    
                    if (--reflexTries <= 0) demanderStats();
                    else envoyerCarreReflex();
                }
            });



        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
    
    private static ResultCTR resultFromJSON(JSONObject json) {
    	try {
			return new ResultCTR(Forme.valueOf(json.getString("formeClient")), 
					Forme.valueOf(json.getString("formeServeur")), 
					json.getString("result"));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    private void envoyerForme() {
    	JSONObject json = new JSONObject(new Dessin(new Point[]{new Point(0, 0), new Point(0.5F, -9), new Point(100, 20)}));
    	connexion.emit("playctr", json);
    }
    
    private void envoyerCarreEnigme() {
    	JSONObject json = new JSONObject(new Dessin(new Point[]{new Point(100, 100), new Point(300, 100), new Point(300, 300), new Point(100, 300)}));
    	connexion.emit("reponseenigme", json);
    }
    
    private void envoyerCarreReflex() {
    	JSONObject json = new JSONObject(new Dessin(new Point[]{new Point(100, 100), new Point(300, 100), new Point(300, 300), new Point(100, 300)}));
    	connexion.emit("formereflex", json);
    }
    
    private void demanderStats() {
    	connexion.emit("demandestats", new JSONObject(moi));
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