package uno.cartes;

import uno.Uno;

public class CartePasseTonTour extends Carte {

    public CartePasseTonTour(Uno uno, Couleur couleur) {
        super(uno, couleur);
    }

    public boolean peutEtreRecouverte(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    public int getValeur() {
        return 20;
    }

    public int effet() {
        return 3;
    }

    @Override
    public void appliquerEffet() {
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
        return true;
    }

    public boolean peutEtrePoseeSur(CartePlus2 c) {
        return estDeCouleurCompatible(c.getCouleur());
    }

    public boolean peutEtrePoseeSur(CartePlus4 c) {
        return c.estSansCouleur() || estDeCouleurCompatible(c.getCouleur());
    }

    @Override
    public String toString() {
        return "Passe ton tour " + getCouleur().getnomCouleur();
    }
}
