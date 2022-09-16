package uno.cartes;

import uno.Uno;

public class CarteJoker extends Carte {
    public CarteJoker(Uno uno) {
        super(uno);
    }

    public boolean peutEtreRecouverte(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    public int getValeur() {
        return 50;
    }

    public int effet() {
        return 2;
    }

    @Override
    public void appliquerEffet() {
        //Pas d'effet à appliquer pour la carte joker
    }

    public boolean peutEtrePoseeSur(CarteChiffre c) {
        return true;
    }

    public boolean peutEtrePoseeSur(CarteChangementDeSens c) {
        return true;
    }

    public boolean peutEtrePoseeSur(CarteJoker c) {
        return true;
    }

    public boolean peutEtrePoseeSur(CartePasseTonTour c) {
        return true;
    }

    public boolean peutEtrePoseeSur(CartePlus2 c) {
        return true;
    }

    public boolean peutEtrePoseeSur(CartePlus4 c) {
        return true;
    }

    @Override
    public String toString() {
        if (estSansCouleur()) {
            return "Joker";
        }
        return "Joker " + getCouleur().getnomCouleur();
    }
}
