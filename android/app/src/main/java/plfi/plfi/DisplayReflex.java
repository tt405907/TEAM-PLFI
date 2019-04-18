package plfi.plfi;

public interface DisplayReflex {
    void updateGameReflex (String forme);
    void updateGameReponseReflex(String reponse);
    void updateGameStats(String toutes_mauvaises_reponses,String score_max,String parties,String toutes_bonnes_réponses);
}
