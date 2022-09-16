package uno.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.Uno;
import uno.cartes.*;
import uno.erreur.CoupIncorrect;
import uno.joueurs.JoueurHumain;

import static org.junit.jupiter.api.Assertions.*;

class JoueurHumainTest {
    JoueurHumain joueurHumain;
    Uno uno;
    PaquetDeCartes paquetDeCartes;
    FabriqueCartes fabriqueCartes;
    Carte carte;

    @BeforeEach
    void setUp() {
        uno = new Uno();
        fabriqueCartes = FabriqueCartes.getInstance();
        paquetDeCartes = fabriqueCartes.getPaquet1CarteDeChaque(uno);
        joueurHumain = new JoueurHumain(uno, paquetDeCartes, 0, "test");
    }

    @Test
    void carteChoisie() {
        try {
            carte = joueurHumain.carteChoisie("0");
            assertEquals(carte.effet(), 0);
            assertEquals(carte.getCouleur(), Couleur.JAUNE);
            assertEquals(carte.getValeur(), 8);

            carte = joueurHumain.carteChoisie("1");
            assertEquals(carte.effet(), 1);
            assertEquals(carte.getCouleur(), Couleur.BLEU);

            carte = joueurHumain.carteChoisie("2v");
            assertEquals(carte.effet(), 2);
            assertTrue(carte.estSansCouleur());

            carte = joueurHumain.carteChoisie("3");
            assertEquals(carte.effet(), 3);
            assertEquals(carte.getCouleur(), Couleur.VERT);

            carte = joueurHumain.carteChoisie("4");
            assertEquals(carte.effet(), 4);
            assertEquals(carte.getCouleur(), Couleur.BLEU);

            carte = joueurHumain.carteChoisie("5r");
            assertEquals(carte.effet(), 5);
            assertTrue(carte.estSansCouleur());

        } catch (CoupIncorrect coupIncorrect) {
            coupIncorrect.printStackTrace();
        }
    }
}