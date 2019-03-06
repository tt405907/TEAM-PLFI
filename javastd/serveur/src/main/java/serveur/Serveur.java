package serveur;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

import commun.Dessin;
import commun.Forme;
import commun.Identification;
import commun.jeux.ResultCTR;
import serveur.jeux.GameCTR;

/**
 * attend une connexion, on envoie une question puis on attend une réponse,
 * jusqu'à la découverte de la bonne réponse le client s'identifie (som, ni
 * eau)
 */
public class Serveur {

    SocketIOServer serveur;
    final Object attenteConnexion = new Object();
    Identification leClient;

    // Objets pour les jeux
    private FormeMatcher matcher = new FormeMatcher();
    private GameCTR ctr = new GameCTR();
    private Random rand = new Random();

    public Serveur(Configuration config) {
        // creation du serveur
        serveur = new SocketIOServer(config);

        // Objet de synchro

        System.out.println("préparation du listener");

        // on accept une connexion
        serveur.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("connexion de " + socketIOClient.getRemoteAddress());

                // on ne s'arrête plus ici
            }
        });

        // réception d'une identification
        serveur.addEventListener("identification", Identification.class, new DataListener<Identification>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Identification identification, AckRequest ackRequest)
                    throws Exception {
                System.out.println("Le client est " + identification.getNom());
                leClient = new Identification(identification.getNom());

                // on enchaine sur une question
                poserUneQuestion(socketIOClient);
            }
        });

        // Jeu du CTR
        serveur.addEventListener("playctr", Dessin.class, new DataListener<Dessin>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Dessin dessin, AckRequest ackRequest) throws Exception {
                System.out.println(leClient.getNom() + " a envoyé un dessin pour une partie de CTR");
                // System.out.println(dessin.asList());
                // Identifie la forme du client
                Forme formeClient = matcher.identify(dessin);
                // Joue une forme aléatoire
                Forme formeServeur = randomForme();
                // Joue la partie
                ResultCTR res = ctr.play(formeClient, formeServeur);
                System.out.println(res);
                // Renvoie le résultat
                renvoyerResultatCTR(socketIOClient, res);
                // Si le client a battu le serveur
                if (res.getResult().equals(ResultCTR.CLIENT_GAGNE)) {
                    System.out.println("le client a gagné!");
                } else {
                    System.out.println("le client n'a pas encore gagné");
                }

            }
        });

    }

    /**
     * Donne une forme aléatoire pour une partie de CTR
     */
    private Forme randomForme() {
        int num = rand.nextInt(3);
        switch (num) {
        case 0:
            return Forme.CARRE;
        case 1:
            return Forme.TRIANGLE;
        case 2:
        default:
            return Forme.ROND;
        }
    }

    private void démarrer() {

        serveur.start();

        System.out.println("en attente de connexion");
        synchronized (attenteConnexion) {
            try {
                attenteConnexion.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("erreur dans l'attente");
            }
        }

        System.out.println("Une connexion est arrivée, on arrête");
        serveur.stop();

    }

    private void poserUneQuestion(SocketIOClient socketIOClient) {
        socketIOClient.sendEvent("question");
    }

    private void renvoyerResultatCTR(SocketIOClient socketIOClient, ResultCTR result) {
        socketIOClient.sendEvent("resultctr", result);
    }

    public static final void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Configuration config = new Configuration();
        config.setHostname("192.168.0.105");
        config.setPort(10101);

        Serveur serveur = new Serveur(config);
        serveur.démarrer();

        System.out.println("fin du main");

    }

}
