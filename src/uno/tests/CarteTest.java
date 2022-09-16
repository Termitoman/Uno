package uno.tests;

import uno.cartes.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.Uno;

class CarteTest {
    Uno uno;
    CarteChiffre carteChiffre;
    CarteChiffre carteChiffreBis;
    CarteChiffre carteChiffre2Bis;
    CarteChangementDeSens carteChangementDeSens;
    CarteChangementDeSens carteChangementDeSensBis;
    CarteJoker carteJoker;
    CartePasseTonTour cartePasseTonTour;
    CartePasseTonTour cartePasseTonTourBis;
    CartePlus2 cartePlus2;
    CartePlus2 cartePlus2Bis;
    CartePlus4 cartePlus4;

    @BeforeEach
    void setUp() {
        uno = new Uno();
        carteChiffre = new CarteChiffre(uno, Couleur.VERT, 0);
        carteChiffreBis = new CarteChiffre(uno, Couleur.BLEU, 7);
        carteChiffre2Bis = new CarteChiffre(uno, Couleur.VERT, 7);
        carteChangementDeSens = new CarteChangementDeSens(uno, Couleur.JAUNE);
        carteChangementDeSensBis = new CarteChangementDeSens(uno, Couleur.BLEU);
        carteJoker = new CarteJoker(uno);
        cartePasseTonTour = new CartePasseTonTour(uno, Couleur.ROUGE);
        cartePasseTonTourBis = new CartePasseTonTour(uno, Couleur.BLEU);
        cartePlus2 = new CartePlus2(uno,Couleur.VERT);
        cartePlus2Bis = new CartePlus2(uno,Couleur.BLEU);
        cartePlus4 = new CartePlus4(uno);
    }

    //Je n'effectue pas le test de la fonction peutEtreRecouverte, car cette fonction ne fait rien d'autre qu'une utilisation des fonction peutEtrePoseeSur que l'on teste déjà

    @Test
    void estSansCouleur() {
        //Tests où le résultat est vrai
        assertTrue(carteJoker.estSansCouleur());
        assertTrue(cartePlus4.estSansCouleur());

        //Tests où le résultat est faux
        assertFalse(carteChiffre.estSansCouleur());
        assertFalse(carteChangementDeSens.estSansCouleur());
        assertFalse(cartePasseTonTour.estSansCouleur());
        assertFalse(cartePlus2.estSansCouleur());
        assertFalse(carteChiffreBis.estSansCouleur());
        assertFalse(carteChangementDeSensBis.estSansCouleur());
        assertFalse(cartePasseTonTourBis.estSansCouleur());
        assertFalse(cartePlus2Bis.estSansCouleur());
    }

    @Test
    void appliquerEffet() {
        uno.initialiser(5);
        //On ne peux pas tester pour carteChiffre

        //Test pour carteChangementDeSens
        assertTrue(uno.isSensHoraire());
        carteChangementDeSens.appliquerEffet();
        assertFalse(uno.isSensHoraire());
        carteChangementDeSensBis.appliquerEffet();
        assertTrue(uno.isSensHoraire());

        //On ne peut pas tester pour carteJoker

        //Test pour cartePasseTonTour
        uno.setJoueurQuiJoue(3);
        cartePasseTonTour.appliquerEffet();
        assertEquals(uno.getJoueurQuiJoue(), 4);
        cartePasseTonTourBis.appliquerEffet();
        assertEquals(uno.getJoueurQuiJoue(), 0);

        //Test pour cartePlus2
        uno.setJoueurQuiJoue(3);
        cartePlus2.appliquerEffet();
        assertEquals(uno.getJoueurQuiJoue(), 4);
        assertEquals(uno.nbCartesJoueurActuel(), 9);
        cartePlus2Bis.appliquerEffet();
        assertEquals(uno.getJoueurQuiJoue(), 0);
        assertEquals(uno.nbCartesJoueurActuel(), 9);

        //Test pour cartePlus4
        uno.setJoueurQuiJoue(3);
        cartePlus4.appliquerEffet();
        assertEquals(uno.getJoueurQuiJoue(), 4);
        assertEquals(uno.nbCartesJoueurActuel(), 13);
        cartePlus4.appliquerEffet();
        assertEquals(uno.getJoueurQuiJoue(), 0);
        assertEquals(uno.nbCartesJoueurActuel(), 13);
    }

    @Test
    void estDeCouleurCompatible() {
        //Tests où le résultat est vrai
        assertTrue(carteChiffre.estDeCouleurCompatible(Couleur.VERT));
        assertTrue(carteChangementDeSens.estDeCouleurCompatible(Couleur.JAUNE));
        assertTrue(cartePasseTonTour.estDeCouleurCompatible(Couleur.ROUGE));
        assertTrue(cartePlus2.estDeCouleurCompatible(Couleur.VERT));
        assertTrue(carteChiffreBis.estDeCouleurCompatible(Couleur.BLEU));
        assertTrue(carteChangementDeSensBis.estDeCouleurCompatible(Couleur.BLEU));
        assertTrue(cartePasseTonTourBis.estDeCouleurCompatible(Couleur.BLEU));
        assertTrue(cartePlus2Bis.estDeCouleurCompatible(Couleur.BLEU));

        //Tests où le résultat est faux

        //Tests pour carteChiffre
        assertFalse(carteChiffre.estDeCouleurCompatible(Couleur.JAUNE));
        assertFalse(carteChiffre.estDeCouleurCompatible(Couleur.ROUGE));
        assertFalse(carteChiffre.estDeCouleurCompatible(Couleur.BLEU));
        assertFalse(carteChiffreBis.estDeCouleurCompatible(Couleur.JAUNE));
        assertFalse(carteChiffreBis.estDeCouleurCompatible(Couleur.ROUGE));
        assertFalse(carteChiffreBis.estDeCouleurCompatible(Couleur.VERT));

        //Tests pour carteChangementDeSens
        assertFalse(carteChangementDeSens.estDeCouleurCompatible(Couleur.BLEU));
        assertFalse(carteChangementDeSens.estDeCouleurCompatible(Couleur.ROUGE));
        assertFalse(carteChangementDeSens.estDeCouleurCompatible(Couleur.VERT));
        assertFalse(carteChangementDeSensBis.estDeCouleurCompatible(Couleur.JAUNE));
        assertFalse(carteChangementDeSensBis.estDeCouleurCompatible(Couleur.ROUGE));
        assertFalse(carteChangementDeSensBis.estDeCouleurCompatible(Couleur.VERT));

        //Tests pour CartePasseSonTour
        assertFalse(cartePasseTonTour.estDeCouleurCompatible(Couleur.BLEU));
        assertFalse(cartePasseTonTour.estDeCouleurCompatible(Couleur.JAUNE));
        assertFalse(cartePasseTonTour.estDeCouleurCompatible(Couleur.VERT));
        assertFalse(cartePasseTonTourBis.estDeCouleurCompatible(Couleur.ROUGE));
        assertFalse(cartePasseTonTourBis.estDeCouleurCompatible(Couleur.JAUNE));
        assertFalse(cartePasseTonTourBis.estDeCouleurCompatible(Couleur.VERT));

        //Tests pour CartePlus2
        assertFalse(cartePlus2.estDeCouleurCompatible(Couleur.BLEU));
        assertFalse(cartePlus2.estDeCouleurCompatible(Couleur.JAUNE));
        assertFalse(cartePlus2.estDeCouleurCompatible(Couleur.ROUGE));
        assertFalse(cartePlus2Bis.estDeCouleurCompatible(Couleur.VERT));
        assertFalse(cartePlus2Bis.estDeCouleurCompatible(Couleur.JAUNE));
        assertFalse(cartePlus2Bis.estDeCouleurCompatible(Couleur.ROUGE));
    }

    @Test
    void getValeur() {
        assertEquals(carteChiffre.getValeur(), 0);
        assertEquals(carteChiffreBis.getValeur(), 7);
        assertEquals(carteChiffre2Bis.getValeur(), 7);

        assertEquals(carteChangementDeSens.getValeur(), 20);
        assertEquals(carteChangementDeSensBis.getValeur(), 20);

        assertEquals(carteJoker.getValeur(), 50);

        assertEquals(cartePasseTonTour.getValeur(), 20);
        assertEquals(cartePasseTonTourBis.getValeur(), 20);

        assertEquals(cartePlus2.getValeur(), 20);
        assertEquals(cartePlus2Bis.getValeur(), 20);

        assertEquals(cartePlus4.getValeur(), 50);
    }

    @Test
    void effet() {
        assertEquals(carteChiffre.effet(), 0);
        assertEquals(carteChiffreBis.effet(), 0);
        assertEquals(carteChiffre2Bis.effet(), 0);

        assertEquals(carteChangementDeSens.effet(), 1);
        assertEquals(carteChangementDeSensBis.effet(), 1);

        assertEquals(carteJoker.effet(), 2);

        assertEquals(cartePasseTonTour.effet(), 3);
        assertEquals(cartePasseTonTourBis.effet(), 3);

        assertEquals(cartePlus2.effet(), 4);
        assertEquals(cartePlus2Bis.effet(), 4);

        assertEquals(cartePlus4.effet(), 5);
    }

    @Test
    void peutEtrePoseeSur() {
        //Test sur les carteChiffre

        //Tests avec les carteChiffre
        assertTrue(carteChiffre.peutEtrePoseeSur(carteChiffre));
        assertFalse(carteChiffre.peutEtrePoseeSur(carteChiffreBis));
        assertTrue(carteChiffre.peutEtrePoseeSur(carteChiffre2Bis));

        //Test avec les carteChangementDeSens
        assertFalse(carteChangementDeSens.peutEtrePoseeSur(carteChiffre));
        assertFalse(carteChangementDeSens.peutEtrePoseeSur(carteChiffreBis));

        //Test avec les carteJoker
        assertTrue(carteJoker.peutEtrePoseeSur(carteChiffre));

        //Test avec les cartePasseTonTour
        assertFalse(cartePasseTonTour.peutEtrePoseeSur(carteChiffre));
        assertFalse(cartePasseTonTour.peutEtrePoseeSur(carteChiffreBis));

        //Test avec les cartePlus2
        assertTrue(cartePlus2.peutEtrePoseeSur(carteChiffre));
        assertFalse(cartePlus2.peutEtrePoseeSur(carteChiffreBis));

        //Test avec les cartePlua4
        assertTrue(cartePlus4.peutEtrePoseeSur(carteChiffre));
    }

    @Test
    void testPeutEtrePoseeSur() {
        //Test sur les carteChangement

        //Tests avec les carteChiffre
        assertFalse(carteChiffre.peutEtrePoseeSur(carteChangementDeSens));
        assertFalse(carteChiffre.peutEtrePoseeSur(carteChangementDeSensBis));

        //Test avec les carteChangementDeSens
        assertTrue(carteChangementDeSens.peutEtrePoseeSur(carteChangementDeSens));
        assertTrue(carteChangementDeSens.peutEtrePoseeSur(carteChangementDeSensBis));

        //Test avec les carteJoker
        assertTrue(carteJoker.peutEtrePoseeSur(carteChangementDeSens));

        //Test avec les cartePasseTonTour
        assertFalse(cartePasseTonTour.peutEtrePoseeSur(carteChangementDeSens));
        assertFalse(cartePasseTonTour.peutEtrePoseeSur(carteChangementDeSensBis));

        //Test avec les cartePlus2
        assertFalse(cartePlus2.peutEtrePoseeSur(carteChangementDeSens));
        assertFalse(cartePlus2.peutEtrePoseeSur(carteChangementDeSensBis));

        //Test avec les cartePlua4
        assertTrue(cartePlus4.peutEtrePoseeSur(carteChangementDeSens));
    }

    @Test
    void testPeutEtrePoseeSur1() {
        //Test sur les carteJoker
        carteJoker.setCouleur(Couleur.VERT);
        //Tests avec les carteChiffre
        assertTrue(carteChiffre.peutEtrePoseeSur(carteJoker));

        //Test avec les carteChangementDeSens
        assertFalse(carteChangementDeSens.peutEtrePoseeSur(carteJoker));

        //Test avec les carteJoker
        assertTrue(carteJoker.peutEtrePoseeSur(carteJoker));

        //Test avec les cartePasseTonTour
        assertFalse(cartePasseTonTour.peutEtrePoseeSur(carteJoker));

        //Test avec les cartePlus2
        assertTrue(cartePlus2.peutEtrePoseeSur(carteJoker));

        //Test avec les cartePlua4
        assertTrue(cartePlus4.peutEtrePoseeSur(carteJoker));
    }

    @Test
    void testPeutEtrePoseeSur2() {
        //Test sur les cartePasseTonTour

        //Tests avec les carteChiffre
        assertFalse(carteChiffre.peutEtrePoseeSur(cartePasseTonTour));
        assertFalse(carteChiffre.peutEtrePoseeSur(cartePasseTonTourBis));

        //Test avec les carteChangementDeSens
        assertFalse(carteChangementDeSens.peutEtrePoseeSur(cartePasseTonTour));
        assertFalse(carteChangementDeSens.peutEtrePoseeSur(cartePasseTonTourBis));

        //Test avec les carteJoker
        assertTrue(carteJoker.peutEtrePoseeSur(cartePasseTonTour));

        //Test avec les cartePasseTonTour
        assertTrue(cartePasseTonTour.peutEtrePoseeSur(cartePasseTonTour));
        assertTrue(cartePasseTonTour.peutEtrePoseeSur(cartePasseTonTourBis));

        //Test avec les cartePlus2
        assertFalse(cartePlus2.peutEtrePoseeSur(cartePasseTonTour));
        assertFalse(cartePlus2.peutEtrePoseeSur(cartePasseTonTourBis));

        //Test avec les cartePlua4
        assertTrue(cartePlus4.peutEtrePoseeSur(cartePasseTonTour));
    }

    @Test
    void testPeutEtrePoseeSur3() {
        //Test sur les cartePlus2

        //Tests avec les carteChiffre
        assertTrue(carteChiffre.peutEtrePoseeSur(cartePlus2));
        assertFalse(carteChiffre.peutEtrePoseeSur(cartePlus2Bis));

        //Test avec les carteChangementDeSens
        assertFalse(carteChangementDeSens.peutEtrePoseeSur(cartePlus2));
        assertFalse(carteChangementDeSens.peutEtrePoseeSur(cartePlus2Bis));

        //Test avec les carteJoker
        assertTrue(carteJoker.peutEtrePoseeSur(cartePlus2));

        //Test avec les cartePasseTonTour
        assertFalse(cartePasseTonTour.peutEtrePoseeSur(cartePlus2));
        assertFalse(cartePasseTonTour.peutEtrePoseeSur(cartePlus2Bis));

        //Test avec les cartePlus2
        assertTrue(cartePlus2.peutEtrePoseeSur(cartePlus2));
        assertTrue(cartePlus2.peutEtrePoseeSur(cartePlus2Bis));

        //Test avec les cartePlua4
        assertTrue(cartePlus4.peutEtrePoseeSur(cartePlus2));
    }

    @Test
    void testPeutEtrePoseeSur4() {
        //Test sur les cartePlus4
        cartePlus4.setCouleur(Couleur.VERT);
        //Tests avec les carteChiffre
        assertTrue(carteChiffre.peutEtrePoseeSur(cartePlus4));

        //Test avec les carteChangementDeSens
        assertFalse(carteChangementDeSens.peutEtrePoseeSur(cartePlus4));

        //Test avec les carteJoker
        assertTrue(carteJoker.peutEtrePoseeSur(cartePlus4));

        //Test avec les cartePasseTonTour
        assertFalse(cartePasseTonTour.peutEtrePoseeSur(cartePlus4));

        //Test avec les cartePlus2
        assertTrue(cartePlus2.peutEtrePoseeSur(cartePlus4));

        //Test avec les cartePlua4
        assertTrue(cartePlus4.peutEtrePoseeSur(cartePlus4));
    }
}