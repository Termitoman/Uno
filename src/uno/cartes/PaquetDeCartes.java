package uno.cartes;

import uno.Uno;
import uno.erreur.ErreurFichier;

import java.io.*;
import java.util.*;

public class PaquetDeCartes implements Iterator<Carte> {

    private final ArrayList<Carte> paquet;

    private int index;

    public PaquetDeCartes() {
        paquet = new ArrayList<>(108);
        index = 0;
    }

    public ArrayList<Carte> getPaquet() {
        return paquet;
    }

    public Carte getCarte(int index) {
        return getPaquet().get(index);
    }

    public void supprimerCarte(int index) {
        assert (index >= 0 && index <= getNombreDeCartes()) : "index n'existe pas (supprimerCarte)";
        getPaquet().remove(index);
    }

    public void ajouter(Carte... cartes) {
        getPaquet().addAll(Arrays.asList(cartes));
    }

    public int getNombreDeCartes() {
        int res = 0;
        for (Carte c : getPaquet()) {
            res += 1;
        }
        return res;
    }

    public boolean estVide() {
        return getNombreDeCartes() == 0;
    }

    public int getValeur() {
        int res = 0;
        for (Carte c : getPaquet()) {
            res += c.getValeur();
        }
        return res;
    }

    public String toString() {
        int capa = getNombreDeCartes()*30 + 8; //30 = nombre de caractères dans le toString de Carte + 1 pour l'espace entre les uno.cartes et 8 = nombre de caractères de "Paquet :"
        StringBuilder res = new StringBuilder(capa);
        res.append("Paquet :");
        for (Carte c : paquet) {
            res.append(" ").append(c.toString());
        }
        return res.toString();
    }

    public void ajouter(PaquetDeCartes pdc) {
        getPaquet().addAll(pdc.getPaquet());
    }

    public void melanger() {
        Collections.shuffle(getPaquet());
    }

    public void retourner() {
        Collections.reverse(getPaquet());
    }

    public Carte getSommet() {
        boolean b = estVide();
        assert (!b) : "On ne peut pas prendre le sommet d'un paquet de uno.cartes vide";
        return getCarte(getNombreDeCartes() - 1);
    }

    public Carte piocher() {
        boolean b = estVide();
        assert (!b) : "On ne peut pas piocher dans un paquet de uno.cartes vide";
        Carte c = getCarte(getNombreDeCartes() - 1);
        getPaquet().remove(getNombreDeCartes() - 1);
        return c;
    }

    public void ecrire(String nomDeFicher) throws ErreurFichier {
        String chemin;
        File file;
        FileWriter fileWriter;
        PrintWriter flotFiltre;
        chemin = "/home/termitoman/IdeaProjects/ProjetBPO/src/" + nomDeFicher; //Chemin absolu
        file = new File(chemin);
        if (file.exists()) { //Si le fichier existe on ne le remplace pas
            throw new ErreurFichier("Fichier Déjà existant");
        }
        try {
            fileWriter = new FileWriter(file);
            flotFiltre = new PrintWriter(new BufferedWriter(fileWriter));
            for (int i = 0; i < getNombreDeCartes(); i++) {
                switch (getCarte(i).effet()) {
                    case (0):
                        flotFiltre.println(getCarte(i).getValeur() + " " + getCouleurCartei(i));
                        break;
                    case (1):
                        flotFiltre.println("ChangementDeSens " + getCouleurCartei(i));
                        break;
                    case (2):
                        flotFiltre.println("Joker SansCouleur");
                        break;
                    case (3):
                        flotFiltre.println("PasseTonTour " + getCouleurCartei(i));
                        break;
                    case (4):
                        flotFiltre.println("Plus2 " + getCouleurCartei(i));
                        break;
                    case (5):
                        flotFiltre.println("Plus4 SansCouleur");
                        break;
                }
            }
            flotFiltre.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lire(String nomDefichier) throws ErreurFichier {
        String chemin;
        FileReader fileReader;
        BufferedReader filtreFile;
        Scanner filtre;
        File file;
        chemin = "/home/termitoman/IdeaProjects/ProjetBPO/src/" + nomDefichier; //Chemin absolu
        file = new File(chemin);
        if (!file.exists()) { //Si le fichier n'existe pas on ne peut pas le lire
            throw new ErreurFichier("Fichier non existant");
        }
        try {
            fileReader = new FileReader(chemin);
            filtreFile = new BufferedReader(fileReader);
            filtre = new Scanner(filtreFile);
            Uno uno = new Uno();
            int temp;
            String temp2;
            while (filtre.hasNext()) {
                if (filtre.hasNextInt()) {
                    temp = filtre.nextInt();
                    switch (filtre.next()) {
                        case ("BLEU"):
                            ajouter(new CarteChiffre(uno, Couleur.BLEU, temp));
                            break;
                        case ("ROUGE"):
                            ajouter(new CarteChiffre(uno, Couleur.ROUGE, temp));
                            break;
                        case ("VERT"):
                            ajouter(new CarteChiffre(uno, Couleur.VERT, temp));
                            break;
                        case ("JAUNE"):
                            ajouter(new CarteChiffre(uno, Couleur.JAUNE, temp));
                            break;
                        default:
                            throw new ErreurFichier("Pas possible de lire car pas un fichier avec que des uno.cartes");
                    }
                } else {
                    temp2 = filtre.next();
                    switch (temp2) {
                        case ("ChangementDeSens"):
                            switch (filtre.next()) {
                                case ("BLEU"):
                                    ajouter(new CarteChangementDeSens(uno, Couleur.BLEU));
                                    break;
                                case ("ROUGE"):
                                    ajouter(new CarteChangementDeSens(uno, Couleur.ROUGE));
                                    break;
                                case ("VERT"):
                                    ajouter(new CarteChangementDeSens(uno, Couleur.VERT));
                                    break;
                                case ("JAUNE"):
                                    ajouter(new CarteChangementDeSens(uno, Couleur.JAUNE));
                                    break;
                                default:
                                    throw new ErreurFichier("Pas possible de lire car pas un fichier avec que des uno.cartes");
                            }
                            break;

                        case ("PasseTonTour"):
                            switch (filtre.next()) {
                                case ("BLEU"):
                                    ajouter(new CartePasseTonTour(uno, Couleur.BLEU));
                                    break;
                                case ("ROUGE"):
                                    ajouter(new CartePasseTonTour(uno, Couleur.ROUGE));
                                    break;
                                case ("VERT"):
                                    ajouter(new CartePasseTonTour(uno, Couleur.VERT));
                                    break;
                                case ("JAUNE"):
                                    ajouter(new CartePasseTonTour(uno, Couleur.JAUNE));
                                    break;
                                default:
                                    throw new ErreurFichier("Pas possible de lire car pas un fichier avec que des uno.cartes");
                            }
                            break;

                        case ("Plus2"):
                            switch (filtre.next()) {
                                case ("BLEU"):
                                    ajouter(new CartePlus2(uno, Couleur.BLEU));
                                    break;
                                case ("ROUGE"):
                                    ajouter(new CartePlus2(uno, Couleur.ROUGE));
                                    break;
                                case ("VERT"):
                                    ajouter(new CartePlus2(uno, Couleur.VERT));
                                    break;
                                case ("JAUNE"):
                                    ajouter(new CartePlus2(uno, Couleur.JAUNE));
                                    break;
                                default:
                                    throw new ErreurFichier("Pas possible de lire car pas un fichier avec que des uno.cartes");
                            }
                            break;

                        case ("Joker"):
                            ajouter(new CarteJoker(uno));
                            filtre.next();
                            break;

                        case ("Plus4"):
                            ajouter(new CartePlus4(uno));
                            filtre.next();
                            break;

                        default:
                            throw new ErreurFichier("Pas possible de lire car pas un fichier avec que des uno.cartes");
                    }
                }

            }
            filtre.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean hasNext() {
        return index < getNombreDeCartes();
    }

    @Override
    public Carte next() {
        index++;
        return getCarte(index - 1);
    }

    public void resetNext() {
        index = 0;
    }

    public String getCouleurCartei(int i) {
        return getCarte(i).getCouleur().toString();
    }
}

