package uno.cartes;

import uno.Uno;

public class CartePlus4 extends Carte {
    public CartePlus4(Uno uno) {
        super(uno);
    }

    public boolean peutEtreRecouverte(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    public int getValeur() {
        return 50;
    }

    public int effet() {
        return 5;
    }

    @Override
    public void appliquerEffet() {
        getUno().distribueCartesEnPlus(4);
        getUno().changerDeJoueur();
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
            return "Plus4";
        }
        return "Plus4 " + getCouleur().getnomCouleur();
    }
}
