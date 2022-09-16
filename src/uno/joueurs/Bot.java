package uno.joueurs;

import uno.Uno;
import uno.cartes.Couleur;
import uno.cartes.PaquetDeCartes;

import java.util.Random;

public class Bot extends Joueur {

    public Bot(Uno uno, PaquetDeCartes paquetDeCartes, int no) {
        super(uno, paquetDeCartes, no);
        nom = "Bot" + no;
    }

    @Override
    public void jouer(String coup) {
        boolean carteTrouvee = false;
        boolean carteSansCouleur = false;
        int indexCarte = 0;

        while (indexCarte < getPaquetDeCartes().getNombreDeCartes() && !carteTrouvee) {   //On recherche si une carte de la main du joueur (bot) peut être posée sur le talon
            carteTrouvee = uno.getSommetDefausse().peutEtreRecouverte(getPaquetDeCartes().getCarte(indexCarte));
            indexCarte++;
        }
        indexCarte -= 1;    //On annule le dernier indexCarte++

        //On gère le fait d'enlever la carte de la main du bot pour la mettre sur la défausse et on gère le fait de piocher
        if (carteTrouvee) {
            uno.getDefausse().ajouter(getPaquetDeCartes().getCarte(indexCarte));
            getPaquetDeCartes().supprimerCarte(indexCarte);
            carteSansCouleur = uno.getSommetDefausse().estSansCouleur();
            uno.getSommetDefausse().appliquerEffet();
        }
        else {
            //Le bot pioche et si la carte peut-être posée il la pose
            if (uno.getPioche().estVide()) {    //Si la pioche est vide, on la remplace par la défausse que l'on retourne
                PaquetDeCartes temp = new PaquetDeCartes();
                temp.ajouter(uno.getDefausse().piocher());   //On stocke temporairement le sommet de la défausse
                uno.setPioche(uno.getDefausse());   //On mets la défausse dans la pioche
                uno.getPioche().retourner();
                uno.setDefausse(temp);
            }
            getPaquetDeCartes().ajouter(uno.getPioche().piocher());
            uno.getDialogue().printPioche();
            if (uno.getSommetDefausse().peutEtreRecouverte(getPaquetDeCartes().getSommet())) {
                uno.getDefausse().ajouter(getPaquetDeCartes().piocher());
                carteSansCouleur = uno.getSommetDefausse().estSansCouleur();
                uno.getSommetDefausse().appliquerEffet();
            }
        }

        //On gère le fait que la carte posée soit une carte sansCouleur
        if (carteSansCouleur) {
            uno.setCouleurAleatoireSommetDefausse();
        }

    }

    @Override
    public boolean estHumain() {
        return false;
    }
}
