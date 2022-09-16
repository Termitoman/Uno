package uno.dialogue;

import uno.Uno;
import uno.erreur.CoupIncorrect;

import java.util.Scanner;

public class DialogueLigneDeCommande {
    Uno uno;

    public DialogueLigneDeCommande(Uno uno) {
        this.uno = uno;
        Scanner scanner;
        boolean bonChoix;
        System.out.println("Bienvenue à vous, joueur. Installez-vous pour jouer au UNO");

        //On demande le nombre de joueurs
        System.out.print("Combien de personnes jouent lors de cette partie ? (2 - 10 joueurs max) :");
        int nbJoueurs = 0;
        bonChoix = false;
        while (!bonChoix) {
            scanner = new Scanner(System.in);
            nbJoueurs = scanner.nextInt();
            bonChoix = nbJoueurs >= 2 && nbJoueurs <= 10;
            if (!bonChoix) {
                System.out.print("\nLe jeu ne peut pas commencer avec ce nombre de joueurs, veuillez en choisir un nouveau entre 2 et 10 :");
            }
        }

        //On demande la difficultée du jeu (à faire quand difficultée est codé)

        //On commence la partie
        uno.initialiser(nbJoueurs);
    }

    public void mettreAJour() {
        if (uno.partieEstFinie()) {
            System.out.println("La partie est terminée, voici les scores !");
            int gagnant = 0;
            for (int i = 0; i < uno.nbJoueurs(); i++) {
                System.out.println("Le score du joueur n°" + i + " est : " + uno.getJoueuri(i).getScore());
                if (uno.getJoueuri(i).getScore() == 0) {
                    gagnant = i;
                }
            }
            System.out.println("Le gagnant est : " + uno.getJoueuri(gagnant).getNom());
        }
        else {
            System.out.println("La carte au sommet du talon est :" + uno.getSommetDefausse().toString());
            if (!uno.getJoueurActuel().estHumain()) {
                System.out.println(uno.getJoueurActuel().getNom() + " est entrain de jouer");
                System.out.println("Il reste " + uno.nbCartesJoueurActuel() + " cartes au bot");
                try {
                    uno.getJoueurActuel().jouer("");
                    Thread.sleep(1000);
                } catch (CoupIncorrect coupIncorrect) {
                    System.out.println(uno.getJoueurActuel().getNom() + " n'a pas réussi à jouer !"); //n'est pas sensé arriver car on ne throw pas d'erreur dans jouer quand c'est un bot
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Au tour du joueur " + uno.getJoueurActuel().getNom() + " !");
                System.out.println("Voici votre main : ");
                for (int i = 0; i < uno.nbCartesJoueurActuel(); i++) {
                    System.out.println(i + " : " + uno.getJoueurActuel().getPaquetDeCartes().getCarte(i));
                }
                System.out.print("Pour jouer, tapez le numéro de la carte ainsi qu'une couleur si c'est un joker ou un plus4 ou tapez juste p pour piocher : ");
                Scanner scanner = new Scanner(System.in);
                String coup = scanner.nextLine();
                boolean coupCorrect = false;
                while (!coupCorrect) {
                    try {
                        uno.getJoueurActuel().jouer(coup);
                        coupCorrect = true;
                    } catch (CoupIncorrect coupIncorrect) {
                        System.out.println("Erreur : " + coupIncorrect.getMessage());
                        System.out.print("Votre coup est incorrect, veuillez recommencer : ");
                        scanner = new Scanner(System.in);
                        coup = scanner.nextLine();
                    }
                }
            }
        }
    }

    public void printPasseTonTour() {
        System.out.println("Vous passez votre tour !");
    }

    public void printPioche() {
        System.out.println("Le bot pioche !");
    }
}
