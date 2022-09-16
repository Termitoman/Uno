package uno.cartes;

import uno.Uno;

public class CartePlus2 extends Carte {

    public CartePlus2(Uno uno, Couleur couleur) {
        super(uno, couleur);
    }

    public boolean peutEtreRecouverte(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    public int getValeur() {
        return 20;
    }

    public int effet() {
        return 4;
    }

    @Override
    public void appliquerEffet() {
        getUno().distribueCartesEnPlus(2);
        getUno().changerDeJoueur();
    }

    public boolean peutEtrePoseeSur(CarteChiffre c) {
        return estDeCouleurCompatible(c.getCouleur());
    }

    public boolean peutEtrePoseeSur(CarteChangementDeSens c) {
        return estDeCouleurCompatible(c.getCouleur());
    }

    public boolean peutEtrePoseeSur(CarteJoker c) {
        return c.estSansCouleur() || estDeCouleurCompatible(c.getCouleur());
    }

    public boolean peutEtrePoseeSur(CartePasseTonTour c) {
        return estDeCouleurCompatible(c.getCouleur());
    }

    public boolean peutEtrePoseeSur(CartePlus2 c) {
        return true;
    }

    public boolean peutEtrePoseeSur(CartePlus4 c) {
        return c.estSansCouleur() || estDeCouleurCompatible(c.getCouleur());
    }

    @Override
    public String toString() {
        return "Plus2 " + getCouleur().getnomCouleur();
    }
}
