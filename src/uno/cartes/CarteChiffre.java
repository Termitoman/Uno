package uno.cartes;

import uno.Uno;

public class CarteChiffre extends Carte {

    private int numero;

    public CarteChiffre(Uno uno, Couleur couleur, int num) {
        super(uno, couleur);
        setNumero(num);
    }

    private void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean peutEtreRecouverte(Carte c) {
        return c.peutEtrePoseeSur(this);
    }

    public int getValeur() {
        return numero;
    }

    public int effet() {
        return 0;
    }

    public void appliquerEffet() {
    }

    public boolean peutEtrePoseeSur(CarteChiffre c) { return getValeur() == c.getValeur() || estDeCouleurCompatible(c.getCouleur()); }

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
        return estDeCouleurCompatible(c.getCouleur());
    }

    public boolean peutEtrePoseeSur(CartePlus4 c) {
        return c.estSansCouleur() || estDeCouleurCompatible(c.getCouleur());
    }

    @Override
    public String toString() {
        return getValeur() + " " + getCouleur().getnomCouleur();
    }
}
