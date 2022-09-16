package uno.tests;

import uno.Uno;
import uno.cartes.*;
import uno.erreur.ErreurFichier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaquetDeCartesTest {
    FabriqueCartes fabriqueCartes;
    PaquetDeCartes paquetDeCartes;
    PaquetDeCartes paquetDeCartes6;
    PaquetDeCartes paquetDeCartes108;
    PaquetDeCartes paquetDeCartesbis;
    PaquetDeCartes paquetDeCartes6bis;
    PaquetDeCartes paquetDeCartes108bis;
    Uno uno;
    //Uno uno; Pas encore utile

    @BeforeEach
    void setUp() {
        uno = new Uno();
        fabriqueCartes = FabriqueCartes.getInstance();
        paquetDeCartes = fabriqueCartes.getPaquetVide();
        paquetDeCartes6 = fabriqueCartes.getPaquet1CarteDeChaque(uno);
        paquetDeCartes108 = fabriqueCartes.getPaquetUno(uno);

        paquetDeCartesbis = new PaquetDeCartes();
        paquetDeCartes6bis = new PaquetDeCartes();
        paquetDeCartes108bis = new PaquetDeCartes();

        //uno = new Uno();
    }

    @Test
    void getPaquet() {
    }

    @Test
    void getCarte() {
    }

    @Test
    void ajouter() {
        //Je sais pas faire
    }

    @Test
    void getNombreDeCartes() {
        assertEquals(paquetDeCartes.getNombreDeCartes(), 0);
        assertEquals(paquetDeCartes6.getNombreDeCartes(), 6);
        assertEquals(paquetDeCartes108.getNombreDeCartes(), 108);
    }

    @Test
    void estVide() {
        assertTrue(paquetDeCartes.estVide());
        assertFalse(paquetDeCartes6.estVide());
        assertFalse(paquetDeCartes108.estVide());
    }

    @Test
    void getValeur() {
        assertEquals(paquetDeCartes.getValeur(), 0);
        assertEquals(paquetDeCartes6.getValeur(), 168);
        assertEquals(paquetDeCartes108.getValeur(), 1240);
    }

    @Test
    void testToString() {
        //Pas sur d'en avoir besoin car pas sur le schéma
    }

    @Test
    void testAjouter() {
        //Je sais pas faire
    }

    @Test
    void melanger() {
        //Je sais pas faire
    }

    @Test
    void retourner() {
        //Je sais pas faire
    }

    @Test
    void getSommet() {
        //Je sais pas faire
    }

    @Test
    void piocher() {
        //Je sais pas faire
    }

    @Test
    void ecrire() {
        try {
            paquetDeCartes.ecrire("PaquetVide.txt");
        } catch (ErreurFichier erreurFichier) {
            erreurFichier.printStackTrace();
        }
        try {
            paquetDeCartes6.ecrire("Paquet6.txt");
        } catch (ErreurFichier erreurFichier) {
            erreurFichier.printStackTrace();
        }
        try {
            paquetDeCartes108.ecrire("Paquet108.txt");
        } catch (ErreurFichier erreurFichier) {
            erreurFichier.printStackTrace();
        }

        try {
            paquetDeCartes.ecrire("PaquetVide.txt");
        } catch (ErreurFichier erreurFichier) {
            System.out.println("Fichier déjà trouvé ! (Résultat normal)");
        }
        try {
            paquetDeCartes6.ecrire("Paquet6.txt");
        } catch (ErreurFichier erreurFichier) {
            System.out.println("Fichier déjà trouvé ! (Résultat normal)");
        }
        try {
            paquetDeCartes108.ecrire("Paquet108.txt");
        } catch (ErreurFichier erreurFichier) {
            System.out.println("Fichier déjà trouvé ! (Résultat normal)");
        }
    }

    @Test
    void lire() {
        try {
            paquetDeCartes108bis.lire("PaquetVide.txt");
        } catch (ErreurFichier erreurFichier) {
            erreurFichier.printStackTrace();
        }
        assertEquals(paquetDeCartes108bis.getNombreDeCartes(), paquetDeCartes.getNombreDeCartes());
        assertEquals(paquetDeCartes.getValeur(), paquetDeCartes.getValeur());

        try {
            paquetDeCartesbis.lire("Paquet6.txt");
        } catch (ErreurFichier erreurFichier) {
            erreurFichier.printStackTrace();
        }
        assertEquals(paquetDeCartesbis.getNombreDeCartes(), paquetDeCartes6.getNombreDeCartes());
        assertEquals(paquetDeCartes6.getValeur(), paquetDeCartes6.getValeur());

        try {
            paquetDeCartes6bis.lire("Paquet108.txt");
        } catch (ErreurFichier erreurFichier) {
            erreurFichier.printStackTrace();
        }
        assertEquals(paquetDeCartes6bis.getNombreDeCartes(), paquetDeCartes108.getNombreDeCartes());
        assertEquals(paquetDeCartes108.getValeur(), paquetDeCartes108.getValeur());

    }

}