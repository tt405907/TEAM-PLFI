package plfi.plfi;

import java.util.ArrayList;

import commun.Coup;
import commun.Identification;

public class Controleur {

    Connexion connexion;

    int dernierCoup = -1;

    final Identification moi = new Identification("Moi sur Android", 1000);



    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }


    public void aprèsConnexion() {
        connexion.envoyerId(moi);

    }


}