package uno.joueurs;

import uno.cartes.Couleur;
import uno.erreur.CoupIncorrect;
import uno.cartes.Carte;
import uno.cartes.PaquetDeCartes;
import uno.Uno;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoueurHumain extends Joueur {


    public JoueurHumain(Uno uno, PaquetDeCartes paquetDeCartes, int no, String nom) {
        super(uno, paquetDeCartes, no);
        this.nom = nom;
    }

    public Carte carteChoisie(String coup) throws CoupIncorrect {
        Matcher matcher = Pattern.compile("\\d+").matcher(coup); //On cherche un nombre dans la chaîne de caractère
        String nbrStr;
        int nbrInt;
        if (matcher.find()) {   //On met le nombre dans une chaîne de caractère et dans un int
            nbrStr = matcher.group();
            nbrInt = Integer.parseInt(nbrStr);
        }
        else {
            throw new CoupIncorrect("Aucun entier trouvé");
        }

        //On regarde si la carte existe
        if (!(nbrInt < getPaquetDeCartes().getNombreDeCartes() && nbrInt >= 0)) {
            throw new CoupIncorrect("Nombre trop petit ou trop grand");
        }

        //On regarde si la couleur donnée (si il y en a une) est la bonne
        String coupTemp = coup.replace(nbrStr, "");   //On enlève le nombre pour avoir accès à la couleur si il y en a une
        if (getPaquetDeCartes().getCarte(nbrInt).effet() == 2 || getPaquetDeCartes().getCarte(nbrInt).effet() == 5) {
            if (coupTemp.length() == 1) {
                if (!(coupTemp.charAt(0) == 'b' || coupTemp.charAt(0) == 'r' || coupTemp.charAt(0) == 'v' || coupTemp.charAt(0) == 'j')) {
                    throw new CoupIncorrect("La couleur n'existe pas");
                }
            }
            else if (coupTemp.length() == 0) {
                throw new CoupIncorrect("La couleur n'a pas été rentrée");
            }
        }
        else {
            //On regarde si le joueur à donné une couleur alors que la carte ne le demande pas
            if (!coupTemp.equals("")) {
                throw new CoupIncorrect("On ne peut pas donner une couleur à la carte choisie");
            }
        }

        return getPaquetDeCartes().getCarte(nbrInt);
    }

    @Override
    public void jouer(String coup) throws CoupIncorrect{
        Matcher matcher = Pattern.compile("\\d+").matcher(coup);    //On cherche un nombre dans la chaîne de caractère

        if (matcher.find()) {   //Si le joueur veut poser une carte

            Carte carteJouee = carteChoisie(coup);  //On regarde si on peux jouer la carte

            String nbrStr = matcher.group();   //On stocke le nombre sous forme de string, sert pour les cas avec joker et plus4
            int nbrInt = Integer.parseInt(nbrStr);  //On stocke le nombre sous forme d'int, sert pour supprimer la carte jouée

            if (uno.getSommetDefausse().peutEtreRecouverte(carteJouee)) { //On joue la carte si possible
                String coupTemp = coup.replace(nbrStr, ""); //On enlève le nombre pour que seul les couleurs restent
                if (!(coupTemp.length() > 1)) { //On passe le tour du joueur si il marque autre chose que juste chiffre + 1 lettre
                    uno.getDefausse().ajouter(carteJouee);
                    getPaquetDeCartes().supprimerCarte(nbrInt);
                    carteJouee.appliquerEffet();
                    //On s'occupe du cas où la carte jouée est un joker ou un plus4
                    if (carteJouee.effet() == 2 || carteJouee.effet() == 5) {
                        switch (coupTemp.charAt(0)) {
                            case 'b':
                                uno.getSommetDefausse().setCouleur(Couleur.BLEU);
                                break;

                            case 'r':
                                uno.getSommetDefausse().setCouleur(Couleur.ROUGE);
                                break;

                            case 'j':
                                uno.getSommetDefausse().setCouleur(Couleur.JAUNE);
                                break;

                            case 'v':
                                uno.getSommetDefausse().setCouleur(Couleur.VERT);
                        }
                    }
                }
            }
            else {
                throw new CoupIncorrect("La carte ne peut pas être jouée sur le talon !");
            }
        }
        else if (coup.equals("p")) {
            if (uno.getPioche().estVide()) {    //Si la pioche est vide, on la remplace par la défausse que l'on retourne
                PaquetDeCartes temp = new PaquetDeCartes();
                temp.ajouter(uno.getDefausse().piocher());   //On stocke temporairement le sommet de la défausse
                uno.setPioche(uno.getDefausse());   //On mets la défausse dans la pioche
                uno.getPioche().retourner();
                uno.setDefausse(temp);
            }
            getPaquetDeCartes().ajouter(uno.getPioche().piocher());
            if (uno.getSommetDefausse().peutEtreRecouverte(getPaquetDeCartes().getSommet())) {
                uno.getDefausse().ajouter(getPaquetDeCartes().piocher());
                uno.getSommetDefausse().appliquerEffet();
                if (uno.getSommetDefausse().effet() == 2 || uno.getSommetDefausse().effet() == 5)
                    uno.setCouleurAleatoireSommetDefausse();
            }
        }
        else { //Cas ou le joueur passe son tour
            uno.getDialogue().printPasseTonTour();
        }
    }

    @Override
    public boolean estHumain() {
        return true;
    }
}
