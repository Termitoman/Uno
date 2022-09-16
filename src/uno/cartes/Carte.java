package uno.cartes;

import uno.Uno;

public abstract class Carte {

    protected Uno uno;

    protected Couleur couleur ;

    public Carte(Uno uno) {
        setUno(uno);
        setCouleur(null);
    }

    public Carte(Uno uno, Couleur couleur) {
        setUno(uno);
        setCouleur(couleur);
    }

    protected void setUno(Uno uno) {
        this.uno = uno;
    }

    protected Uno getUno() {
        return uno;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public abstract boolean peutEtreRecouverte(Carte c);

    public abstract int getValeur();

    public abstract int effet();

    public boolean estSansCouleur() {
    return getCouleur() == null;
    }

    public abstract void appliquerEffet() ;

    public boolean estDeCouleurCompatible(Couleur c) {
        return getCouleur() == c;
    }

    public abstract boolean peutEtrePoseeSur(CarteChiffre c);

    public abstract boolean peutEtrePoseeSur(CarteChangementDeSens c);

    public abstract boolean peutEtrePoseeSur(CarteJoker c);

    public abstract boolean peutEtrePoseeSur(CartePasseTonTour c);

    public abstract boolean peutEtrePoseeSur(CartePlus2 c);

    public abstract boolean peutEtrePoseeSur(CartePlus4 c);

    public abstract String toString();

}
