package uno.cartes;

import uno.Uno;

public class CarteChangementDeSens extends Carte {

    public CarteChangementDeSens(Uno uno, Couleur couleur) {
        super(uno, couleur);
    }

    public boolean peutEtreRecouverte(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    public int getValeur() {
        return 20;
    }

    public int effet() {
        return 1;
    }

    public void appliquerEffet() {
        getUno().changerSensDuJeu();
    }

    public boolean peutEtrePoseeSur(CarteChiffre c) {
        return estDeCouleurCompatible(c.getCouleur());
    }

    public boolean peutEtrePoseeSur(CarteChangementDeSens c) {
        return true;
    }

    public boolean peutEtrePoseeSur(CarteJoker c) {
        return c.estSansCouleur() || estDeCouleurCompatible(c.getCouleur());
    }

    public boolean peutEtrePoseeSur(CartePasseTonTour c) {
        return estDeCouleurCompatible(c.getCouleur());
    }

    public boolean peutEtrePoseeSur(CartePlus2 c) {
        return estDeCouleurCompatible(c.getCouleur());
    }

    public boolean peutEtrePoseeSur(CartePlus4 c) {
        return c.estSansCouleur() || estDeCouleurCompatible(c.getCouleur());
    }

    @Override
    public String toString() {
        return "Changement De Sens " + getCouleur().getnomCouleur();
    }
}
