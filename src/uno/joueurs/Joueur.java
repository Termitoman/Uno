package uno.joueurs;

import uno.cartes.PaquetDeCartes;
import uno.Uno;
import uno.erreur.CoupIncorrect;

public abstract class Joueur {
    protected Uno uno;
    protected PaquetDeCartes paquetDeCartes;
    protected String nom;
    protected int numero;

    public Joueur (Uno uno, PaquetDeCartes paquetDeCartes, int numero) {
        this.uno = uno;
        this.paquetDeCartes = paquetDeCartes;
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setPaquetDeCartes(PaquetDeCartes paquetDeCartes) {
        this.paquetDeCartes = paquetDeCartes;
    }

    public String toString() {
        return "Joueur{" +
                "nom=" + nom +
                ", numero=" + numero +
                ", PaquetDeCartes=" + paquetDeCartes.toString() +
                '}';
    }

    public PaquetDeCartes getPaquetDeCartes() {
        return paquetDeCartes;
    }

    public abstract void jouer(String coup) throws CoupIncorrect;

    public int getScore() {
        int score = 0;
        getPaquetDeCartes().resetNext();
        while (getPaquetDeCartes().hasNext()) {
            score += getPaquetDeCartes().next().getValeur();
        }
        return score;
    }

    public abstract boolean estHumain();

    public void piocherDansPioche() {
        this.getPaquetDeCartes().ajouter(uno.getPioche().piocher());
    }
}
