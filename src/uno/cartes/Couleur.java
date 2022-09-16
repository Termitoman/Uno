package uno.cartes;


public enum Couleur {
    BLEU("Bleu"),
    ROUGE("Rouge"),
    VERT("Vert"),
    JAUNE("Jaune");
    
    private final String nomCouleur ;
    
    Couleur(String nCouleur) {
        boolean b = (nCouleur.equals("Bleu") || nCouleur.equals("Rouge") || nCouleur.equals("Vert") || nCouleur.equals("Jaune") /*|| nCouleur.equals("Sanscouleur")*/);
        assert(b):"Paramètre incorrect";
        this.nomCouleur = nCouleur;
    }

    public String getnomCouleur() {
        return this.nomCouleur;
    }

}
