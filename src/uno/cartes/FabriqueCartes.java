package uno.cartes;

import uno.Uno;

public class FabriqueCartes {

    private static final FabriqueCartes instance = new FabriqueCartes() ;
    
    public static FabriqueCartes getInstance() { return instance; }

    public PaquetDeCartes getPaquetVide() {
        return new PaquetDeCartes();
     }

    public PaquetDeCartes getPaquet1CarteDeChaque(Uno uno) {
        PaquetDeCartes pdc = new PaquetDeCartes();

        pdc.ajouter(new CarteChiffre(uno, Couleur.JAUNE, 8));
        pdc.ajouter(new CarteChangementDeSens(uno, Couleur.BLEU));
        pdc.ajouter(new CarteJoker(uno));
        pdc.ajouter(new CartePasseTonTour(uno, Couleur.VERT));
        pdc.ajouter(new CartePlus2(uno, Couleur.BLEU));
        pdc.ajouter(new CartePlus4(uno));
        return pdc;
    }

    public PaquetDeCartes getPaquetUno(Uno uno) {
        PaquetDeCartes pdc = new PaquetDeCartes();

        for (Couleur c : Couleur.values()) {
            //On commence par les chiffres
            pdc.ajouter(new CarteChiffre(uno, c, 0));
            for (int i = 1; i <= 9; i++) {
                pdc.ajouter(new CarteChiffre(uno, c, i));
                pdc.ajouter(new CarteChiffre(uno, c, i));
            }

            //Ensuite les uno.cartes spéciales avec couleur
            for (int j = 0; j < 2; j++) {
                pdc.ajouter(new CarteChangementDeSens(uno, c));
                pdc.ajouter(new CartePasseTonTour(uno, c));
                pdc.ajouter(new CartePlus2(uno, c));
            }

            //Enfin les uno.cartes spéciales sans couleur
            pdc.ajouter(new CarteJoker(uno));
            pdc.ajouter(new CartePlus4(uno));
        }

        return pdc;
    }
}
   
