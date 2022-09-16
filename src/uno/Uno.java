package uno;

import uno.cartes.Carte;
import uno.cartes.Couleur;
import uno.cartes.FabriqueCartes;
import uno.cartes.PaquetDeCartes;
import uno.dialogue.DialogueLigneDeCommande;
import uno.joueurs.Bot;
import uno.joueurs.Joueur;
import uno.joueurs.JoueurHumain;

import java.util.Random;

public class Uno {
    private Joueur[] joueurs;
    private PaquetDeCartes pioche;
    private PaquetDeCartes defausse;
    private int joueurQuiDistribue;
    private int joueurQuiJoue;
    private boolean sensHoraire;
    private DialogueLigneDeCommande dialogue;


    public Uno(){
        sensHoraire = true;
    }

    public void initialiser(int nbJoueurs) {
        assert(nbJoueurs >= 2 && nbJoueurs <=10):"Trop de joueurs";
        creerLesJoueurs(nbJoueurs);
        distribuerCartes();
        choisirQuiDistribue();
        choisirQuiJoue();
    }

    public void creerLesJoueurs(int nbJoueurs) {
        assert(nbJoueurs >= 2 && nbJoueurs <=10):"Trop de joueurs";
        joueurs = new Joueur[nbJoueurs];
        joueurs[0] = new JoueurHumain(this, new PaquetDeCartes(), 0, "VraiJoueur");
        for (int i = 1; i < nbJoueurs; i++) {
            joueurs[i] = new Bot(this, new PaquetDeCartes(), i);
        }
    }

    public int nbJoueurs() {
        return joueurs.length;
    }

    public PaquetDeCartes getPioche() {
        return pioche;
    }

    public void setPioche(PaquetDeCartes pioche) {
        this.pioche = pioche;
    }

    public PaquetDeCartes getDefausse() {
        return defausse;
    }

    public void setDefausse(PaquetDeCartes defausse) {
        this.defausse = defausse;
    }

    public void distribuerCartes() {
        FabriqueCartes fabriqueCartes = FabriqueCartes.getInstance();
        setPioche(fabriqueCartes.getPaquetUno(this));
        getPioche().melanger();
        for (int j = 0; j < 7; j++) {
            for(int i = 0; i < nbJoueurs(); i++) {
                getJoueuri(i).piocherDansPioche();
            }
        }
        setDefausse(fabriqueCartes.getPaquetVide());
        getDefausse().ajouter(getPioche().piocher());
        if (getSommetDefausse().effet() == 2 || getSommetDefausse().effet() == 5) {
            this.setCouleurAleatoireSommetDefausse();
        }
    }

    public void choisirQuiDistribue() {
        Random random = new Random();
        joueurQuiDistribue = random.nextInt(nbJoueurs());
    }

    public int getJoueurQuiDistribue() {
        return joueurQuiDistribue;
    }

    public void choisirQuiJoue() {
        joueurQuiJoue = (getJoueurQuiDistribue() + 1) % (nbJoueurs());  // fait la même chose que : si jQuiDistri est le dernier joueur alors jQuiJoue est le premier sinon jQuiJoue = jQuiDistri + 1
    }

    public int getJoueurQuiJoue() {
        return joueurQuiJoue;
    }

    public void setJoueurQuiJoue(int joueurQuiJoue) {
        this.joueurQuiJoue = joueurQuiJoue;
    }   //utile pour les tests uniquement

    public Joueur getJoueuri(int i) {
        return joueurs[i];
    }

    public void changerSensDuJeu() {
        sensHoraire = !sensHoraire;
    }

    public void changerDeJoueur() {
        if (sensHoraire) {
            joueurQuiJoue = (getJoueurQuiJoue() + 1) % (nbJoueurs()); //Formule qui passe au joueur suivant et si on est au dernier joueur on revient au premier
        }
        else {
            joueurQuiJoue = ((getJoueurQuiJoue() - 1) + nbJoueurs()) % nbJoueurs(); //idem mais quand on tourne dans l'autre sens
        }
    }

    public void distribueCartesEnPlus(int nbCartes) {
        if (getPioche().estVide()) {    //Si la pioche est vide, on la remplace par la défausse que l'on retourne
            PaquetDeCartes temp = new PaquetDeCartes();
            temp.ajouter(getDefausse().piocher());   //On stocke temporairement le sommet de la défausse
            setPioche(getDefausse());   //On mets la défausse dans la pioche
            getPioche().retourner();
            setDefausse(temp);
        }
        if (sensHoraire) {
            for (int i = 0; i < nbCartes; i++) {
                getJoueuri((getJoueurQuiJoue() + 1) % (nbJoueurs())).piocherDansPioche(); //On fait piocher les cartes au joueur suivant
            }
        }
        else {
            for (int i = 0; i < nbCartes; i++) {
                getJoueuri(((getJoueurQuiJoue() - 1) + nbJoueurs()) % nbJoueurs()).piocherDansPioche(); //idem mais quand le sens est inversé
            }
        }
    }

    public boolean isSensHoraire() {
        return sensHoraire;
    }

    public void jeu() {
        DialogueLigneDeCommande dial = new DialogueLigneDeCommande(this) ;
        setDialogue(dial);
        //Pour appliquer le premier effet, on simule le fait que ce soit un joueur précédant le premier joueur qui joue la carte
        sensHoraire = !sensHoraire;
        changerDeJoueur();
        getSommetDefausse().appliquerEffet();//On applique l'effet de la première carte
        sensHoraire = !sensHoraire;
        changerDeJoueur();

        //On commence la partie véritablement
        dialogue.mettreAJour();
        while (!partieEstFinie()) {
            changerDeJoueur();
            dialogue.mettreAJour();
        }
        dialogue.mettreAJour(); //Pour le message de fin
    }

    public void setDialogue(DialogueLigneDeCommande dialogue) {
        this.dialogue = dialogue;
    }

    public boolean partieEstFinie() {
        boolean res = false;
        for (int i = 0; i < nbJoueurs(); i++) {
            res = res || getJoueuri(i).getPaquetDeCartes().estVide();
        }
        return res;
    }

    public Carte getSommetDefausse() {
        return getDefausse().getSommet();
    }

    public int nbCartesJoueurActuel() {return getJoueurActuel().getPaquetDeCartes().getNombreDeCartes();}

    public void setCouleurAleatoireSommetDefausse() {
        Random random = new Random();
        int couleurRand = random.nextInt(4);
        switch (couleurRand) {
            case 0 :
                getSommetDefausse().setCouleur(Couleur.BLEU);
                break;

            case 1 :
                getSommetDefausse().setCouleur(Couleur.VERT);
                break;

            case 2 :
                getSommetDefausse().setCouleur(Couleur.ROUGE);
                break;

            case 3 :
                getSommetDefausse().setCouleur(Couleur.JAUNE);
                break;
        }
    }

    public Joueur getJoueurActuel() {
        return this.getJoueuri(this.getJoueurQuiJoue());
    }

    public DialogueLigneDeCommande getDialogue() {
        return dialogue;
    }
}
