package plfi.plfi;

import java.util.ArrayList;

import commun.Identification;

public class Controleur {

    Connexion connexion;

    int dernierCoup = -1;
    String nom = "mon nom";
    int level = 1000;
    Identification moi = new Identification("identification");
    private DisplayRTC view;

    public Controleur(DisplayRTC view) {
        setView(view);
    }


    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }


    public void aprèsConnexion() {
        connexion.envoyerId(moi);

    }

    public String getNom(){
        return this.nom;
    }

    public int getNiveau(){
        return this.level;
    }

    public Identification getidentification(){
        return this.moi;
    }


    public void resultCTR(String winner, String imageserv) {
        view.updateGame(winner,imageserv);
    }

    public void setView(DisplayRTC view) {
        this.view = view;
    }

    public DisplayRTC getView() {
        return view;
    }
}