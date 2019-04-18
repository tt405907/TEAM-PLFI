package plfi.plfi;

import java.util.ArrayList;

import commun.Identification;

public class Controleur {

    Connexion connexion;
    boolean isEnigme;
    boolean isRTC;
    int dernierCoup = -1;
    String nom = "mon nom";
    int level = 1000;
    Identification moi = new Identification("identification");
    private DisplayRTC view;
    private DisplayEnigme viewEnigme;
    private DisplayReflex viewReflex;

    public Controleur(DisplayRTC view) {
        setView(view);
        this.isRTC = true;
        this.isEnigme = false;
    }

    public Controleur(DisplayEnigme view) {setViewEnigme(view);
    this.isEnigme = true;
    this.isRTC = false;}

    public Controleur(DisplayReflex view){setViewReflex(view);
    this.isEnigme = false;
    this.isRTC = false;}


    public Connexion getConnexion() {
        return connexion;
    }

    public boolean isEnigme(){
        return this.isEnigme;
    }
    public boolean isRTC(){
        return this.isRTC;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }


    public void aprèsConnexion() {
        connexion.envoyerId(moi);

    }

    public void apresConnexionEnigme(){
        connexion.envoyerIdEnigme(moi);
    }

    public void apresConnexionReflex(){
        connexion.envoyerIdReflex(moi);
    }

    public void getStats(){
        connexion.demanderStats(moi);
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


    public void updateTextviewReflex(String forme){
        viewReflex.updateGameReflex(forme);
    }
    public void resultCTR(String winner, String imageserv) {
        view.updateGame(winner,imageserv);
    }


    public void resultStatsEnigme(String bonnes,String total,String mauvaise){
        viewEnigme.updateStats(bonnes,mauvaise,total);
    }

    public void resultStatsTCR(String triangle,String carre,String rond,String victoires,String defaites,String egalites,String total){
        view.updateStats(triangle,carre,rond,victoires,defaites,egalites,total);
    }

    public void resultatStatsReflex(String toutes_mauvaises_reponses,String score_max,String parties,String toutes_bonnes_réponses){
        viewReflex.updateGameStats(toutes_mauvaises_reponses, score_max, parties, toutes_bonnes_réponses);
    }
    public void enigmeTextview(String enigme){
        viewEnigme.updateGameEnigme(enigme);
    }

    public void enigmeReponseTextview(String reponse){
        viewEnigme.updateGameReponseEnigme(reponse);
    }

    public void reponseTextviewReflex(String reponse){
        viewReflex.updateGameReponseReflex(reponse);
    }

    public void setView(DisplayRTC view) {
        this.view = view;
    }

    public void setViewEnigme(DisplayEnigme view) { this.viewEnigme = view ; }

    public void setViewReflex(DisplayReflex view) {
        this.viewReflex = view;
    }

    public DisplayRTC getView() {
        return view;
    }
    public  DisplayEnigme getViewEnigme() {return viewEnigme;}
    public DisplayReflex getViewReflex(){
        return viewReflex;
    }

    }