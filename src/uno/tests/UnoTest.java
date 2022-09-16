package uno.tests;

import uno.cartes.FabriqueCartes;
import uno.cartes.PaquetDeCartes;
import uno.Uno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnoTest {
    Uno uno;


    @BeforeEach
    void setUp() {
        uno = new Uno();
    }

    @Test
    void initialiser() {
        uno.initialiser(7);
        assertEquals(uno.nbJoueurs(), 7);

        assertEquals(uno.getJoueuri(0).getNom(), "VraiJoueur");
        assertEquals(uno.getJoueuri(0).getPaquetDeCartes().getNombreDeCartes(), 7);
        for (int i = 1; i < uno.nbJoueurs(); i++) {
            assertEquals(uno.getJoueuri(i).getNom(), "Bot"+i);
            assertEquals(uno.getJoueuri(i).getPaquetDeCartes().getNombreDeCartes(),7);
        }

        if(uno.getJoueurQuiDistribue() != uno.nbJoueurs()-1) {
            assertEquals(uno.getJoueurQuiJoue(), uno.getJoueurQuiDistribue() + 1);
        }
        else {
            assertEquals(uno.getJoueurQuiJoue(), 0);
        }


        assertEquals(uno.getPioche().getNombreDeCartes(), 108 - (7*7) - 1);

        assertEquals(uno.getDefausse().getNombreDeCartes(), 1);

        assertTrue(uno.isSensHoraire());

    }

    @Test
    void creerLesJoueurs() {
        //Je teste pour savoir si on peut créer des joueurs plusieurs fois de suite sans problèmes
        uno.creerLesJoueurs(5);
        assertEquals(uno.nbJoueurs(), 5);

        uno.creerLesJoueurs(3);
        assertEquals(uno.nbJoueurs(), 3);
    }

    @Test
    void changerSensDuJeu() {
        uno.initialiser(3);
        uno.changerSensDuJeu();
        assertFalse(uno.isSensHoraire());

        uno.changerSensDuJeu();
        assertTrue(uno.isSensHoraire());
    }

    @Test
    void changerDeJoueur() {
        uno.initialiser(3);
        int joueurQuiJouait = uno.getJoueurQuiJoue();
        uno.changerDeJoueur();
        if (joueurQuiJouait!= uno.nbJoueurs() - 1) {
            assertEquals(uno.getJoueurQuiJoue(), joueurQuiJouait + 1);
        }
        else {
            assertEquals(uno.getJoueurQuiJoue(), 0);
        }
    }


    @Test
    void distribueCartesEnPlus() {
        uno.initialiser(7);
        uno.distribueCartesEnPlus(4);

        assertEquals(uno.getPioche().getNombreDeCartes(), 108 - (7*7) - 5);
        assertEquals(uno.getJoueuri((uno.getJoueurQuiJoue() + 1) % (uno.nbJoueurs())).getPaquetDeCartes().getNombreDeCartes(), 7 + 4);
    }


}