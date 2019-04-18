package plfi.jeux;

public interface DisplayEnigme {
    void updateGameEnigme(String enigme);
    void updateGameReponseEnigme(String reponse);
    void updateStats(String bonnes,String mauvaise,String total);
}
