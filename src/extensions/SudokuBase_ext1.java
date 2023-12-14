package extensions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuBase_ext1 {

    //.........................................................................
    // Fonctions utiles
    //.........................................................................


    /** pré-requis : min <= max
     *  résultat :   un entier saisi compris entre min et max, avec re-saisie éventuelle jusqu'à ce qu'il le soit
     */
    public static int saisirEntierMinMax(int min, int max){
        //________________________________________________________
        int entier = Ut.saisirEntier();
        if(entier < min  || entier > max) {
            Ut.afficher("Impossible, veuillez réessayer: ");
            return saisirEntierMinMax(min, max);
        }
        return entier;
    }  // fin saisirEntierMinMax
    //.........................................................................

    /** MODIFICI
     *  pré-requis : mat1 et mat2 ont les mêmes dimensions
     *  action : copie toutes les valeurs de mat1 dans mat2 de sorte que mat1 et mat2 soient identiques
     */
    public static void copieMatrice(int[][] mat1, int[][] mat2){
        //________________________________________________________
        for(int i = 0; i < mat1.length; i++) {
            for(int j = 0; j < mat1[i].length; j++) {
                mat2[i][j] = mat1[i][j];
            }
        }
    }  // fin copieMatrice

    //.........................................................................

    /** pré-requis :  n >= 0
     *  résultat : un tableau de booléens représentant le sous-ensemble de l'ensemble des entiers
     *             de 1 à n égal à lui-même
     */
    public static boolean[] ensPlein(int n){
        //_____________________________________
        boolean[] ensemble = new boolean[n+1];
        for(int i = 0; i <= n; i++) {
            ensemble[i] = true;
        }
        return ensemble;
    }  // fin ensPlein

    //.........................................................................


    /** pré-requis : 1 <= val < ens.length
     *  action :     supprime la valeur val de l'ensemble représenté par ens, s'il y est
     *  résultat :   vrai ssi val était dans cet ensemble
     */
    public static boolean supprime(boolean[] ens, int val){
        //______________________________________________________
        if(ens[val]) {
            ens[val] = false;
            return true;
        } else return false;
    }  // fin supprime

    //.........................................................................


    /** pré-requis : l'ensemble représenté par ens n'est pas vide
     *  résultat :   un élément de cet ensemble
     */
    public static int uneValeur(boolean[] ens){
        //_____________________________________________
        for(int i = 1; i < ens.length; i++) {
            if(ens[i])
                return i;
        }
        return 0;
    }  // fin uneValeur

    //.........................................................................

    /**

     1 2 3 4 5 6 7 8 9
     -------------------
     1 |6 2 9|7 8 1|3 4 5|
     2 |4 7 3|9 6 5|8 1 2|
     3 |8 1 5|2 4 3|6 9 7|
     -------------------
     4 |9 5 8|3 1 2|4 7 6|
     5 |7 3 2|4 5 6|1 8 9|
     6 |1 6 4|8 7 9|2 5 3|
     -------------------
     7 |3 8 1|5 2 7|9 6 4|
     8 |5 9 6|1 3 4|7 2 8|
     9 |2 4 7|6 9 8|5 3 1|
     -------------------


     1 2 3 4 5 6 7 8 9
     -------------------
     1 |6 0 0|0 0 1|0 4 0|
     2 |0 0 0|9 6 5|0 1 2|
     3 |8 1 0|0 4 0|0 0 0|
     -------------------
     4 |0 5 0|3 0 2|0 7 0|
     5 |7 0 0|0 0 0|1 8 9|
     6 |0 0 0|0 7 0|0 0 3|
     -------------------
     7 |3 0 0|0 2 0|9 0 4|
     8 |0 9 0|0 0 0|7 2 0|
     9 |2 4 0|6 9 0|0 0 0|
     -------------------


     * pré-requis : 0<=k<=3 et g est une grille k^2xk^2 dont les valeurs sont comprises
     *              entre 0 et k^2 et qui est partitionnée en k sous-carrés kxk
     * action : affiche la  grille g avec ses sous-carrés et avec les numéros des lignes
     *          et des colonnes de 1 à k^2.
     * Par exemple, pour k = 3, on obtient le dessin d'une grille de Sudoku
     *
     */
    public static void afficheGrille(int k,int[][] g){
        //__________________________________________________
        Ut.afficherSL("   1 2 3|4 5 6|7 8 9");
        int n = 0;
        // Afficher k blocs de sous-carrés
        for(int i = 0; i < k; i++) {
            Ut.afficherSL("-".repeat(21));
            // Afficher une ligne
            for(int j = 0; j < k; j++) {
                Ut.afficher((n+1) + " |");
                for(int m = 0; m < k; m++) {
                    Ut.afficher((g[n][k * m] == 0 ? "." : g[n][k * m]) + " " +
                            (g[n][k * m + 1] == 0 ? "." : g[n][k * m + 1]) + " " +
                            (g[n][k * m + 2] == 0 ? "." : g[n][k * m + 2]) + "|");
                }
                Ut.sauterALaLigne();
                n++;
            }
        }
        Ut.afficherSL("-".repeat(21));
    } // fin afficheGrille
    //.........................................................................

    /** pré-requis : k > 0, 0 <= i < k^2 et 0 <= j < k^2
     *  résultat : (i,j) étant les coordonnées d'une case d'une grille k^2xk^2 partitionnée
     *             en k sous-carrés kxk, retourne les coordonnées de la case du haut à gauche
     *             du sous-carré de la grille contenant cette case.
     *  Par exemple, si k=3, i=5 et j=7, la fonction retourne (3,6).
     */
    public static int[] debCarre(int k,int i,int j) {
        //__________________________________________________
        int[] coord = new int[2];
        coord[0] = (i / k) * k;
        coord[1] = (j / k) * k;
        return coord;
    }  // fin debCarre


    //.........................................................................
    // Initialisation
    //.........................................................................

    /** MODIFICI
     *  pré-requis : gComplete est une matrice 9X9
     *  action   :   remplit gComplete pour que la grille de Sudoku correspondante soit complète
     *  stratégie :  les valeurs sont données directement dans le code et on peut utiliser copieMatrice pour mettre à jour gComplete
     */
    public static void initGrilleComplete(int [][] gComplete){
        //_________________________________________________
        gComplete[0] = new int[]{6, 2, 9, 7, 8, 1, 3, 4, 5};
        gComplete[1] = new int[]{4, 7, 3, 9, 6, 5, 8, 1, 2};
        gComplete[2] = new int[]{8, 1, 5, 2, 4, 3, 6, 9, 7};
        gComplete[3] = new int[]{9, 5, 8, 3, 1, 2, 4, 7, 6};
        gComplete[4] = new int[]{7, 3, 2, 4, 5, 6, 1, 8, 9};
        gComplete[5] = new int[]{1, 6, 4, 8, 7, 9, 2, 5, 3};
        gComplete[6] = new int[]{3, 8, 1, 5, 2, 7, 9, 6, 4};
        gComplete[7] = new int[]{5, 9, 6, 1, 3, 4, 7, 2, 8};
        gComplete[8] = new int[]{2, 4, 7, 6, 9, 8, 5, 3, 1};
    } // fin initGrilleComplete

    //.........................................................................

    /** MODIFICI
     *  pré-requis : gSecret est une grille de Sudoku complète de mêmes dimensions que gIncomplete et 0 <= nbTrous <= 81
     *  action :     modifie gIncomplete pour qu'elle corresponde à une version incomplète de la grille de Sudoku gSecret (gIncomplete peut être complétée en gSecret),
     *               avec nbTrous trous à des positions aléatoires
     */
    public static void initGrilleIncomplete(int nbTrous, int [][] gSecret, int[][] gIncomplete){
        //___________________________________________________________________________
        int[][] grille = new int[gSecret.length][gSecret[0].length];
        copieMatrice(gSecret, grille);
        while(nbTrous > 0) {
            int x = Ut.randomMinMax(0, 8);
            int y = Ut.randomMinMax(0, 8);

            if(grille[x][y] != 0) {
                grille[x][y] = 0;
                nbTrous--;
            }
        }
        copieMatrice(grille, gIncomplete);
    } // fin initGrilleIncomplete

    //.........................................................................


    /** MODIFICI
     *  pré-requis : 0 <= nbTrous <= 81 ; g est une grille 9x9 (vide a priori)
     *  action :   remplit g avec des valeurs saisies au clavier comprises entre 0 et 9
     *               avec exactement nbTrous valeurs nulles
     *               et avec re-saisie jusqu'à ce que ces conditions soient vérifiées.
     *               On suppose dans la version de base que la grille saisie est bien une grille de Sudoku incomplète.
     *  stratégie : utilise la fonction saisirEntierMinMax
     */
    public static void saisirGrilleIncomplete(int nbTrous, int[][] g){
        //_________________________________________________
        int nbGrilleSaisieTrous = 0;
        g = new int[9][9];
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                afficheGrille(3, g);
                Ut.afficher("Indiquer le chiffre en position (" + (i+1) + "," + (j+1) + "): ");
                int chiffre = saisirEntierMinMax(0, 9);
                if(chiffre == 0)
                    nbGrilleSaisieTrous++;
                g[i][j] = chiffre;
            }
        }

        Ut.clearConsole();
        afficheGrille(3, g);
        if(nbGrilleSaisieTrous != nbTrous) {
            Ut.afficherSL("Impossible, veuillez re-saisir la grille. Il n'y a pas le bon nombre de trous.");
            saisirGrilleIncomplete(nbTrous, g);
        }
    }  // fin saisirGrilleIncomplete

    /** MODIFICI
     *  pré-requis : 0 <= nbTrous <= 81 ; g est une grille 9x9 (vide a priori) ;
     *               fic est un nom de fichier de ce répertoire contenant des valeurs de Sudoku
     *  action :   remplit g avec les valeurs lues dans fic. Si la grille ne contient pas des valeurs
     *             entre 0 et 9 ou n'a pas exactement nbTrous valeurs nulles, la méthode doit signaler l'erreur,
     *             et l'utilisateur doit corriger le fichier jusqu'à ce que ces conditions soient vérifiées.
     *             On suppose dans la version de base que la grille saisie est bien une grille de Sudoku incomplète.
     */
    public static void saisirGrilleIncompleteFichier(int nbTrous, int [][] g, String fic){
        //_________________________________________________

        try (BufferedReader lecteur = new BufferedReader(new FileReader(fic))) {
            int nbGrilleSaisieTrous = 0;
            for (int i = 0 ; i < 9 ; i++){
                String ligne = lecteur.readLine();
                String [] valeurs = ligne.split("\\s+");
                for (int j = 0 ; j < 9 ; j++) {
                    // Des tests d'erreur sont à ajouter quelque part !
                    int chiffre = Integer.parseInt(valeurs[j]);
                    if(chiffre == 0)
                        nbGrilleSaisieTrous++;
                    else if(chiffre < 0 || chiffre > 9) {
                        Ut.afficherSL("Impossible, veuillez re-saisir la grille. Il y a des valeurs incorrectes.");
                        return;
                    }
                    g[i][j] = chiffre;
                }
            }
            if(nbGrilleSaisieTrous != nbTrous) {
                Ut.afficherSL("Impossible, veuillez re-saisir la grille. Il n'y a pas le bon nombre de trous.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // fin saisirGrilleIncompleteFichier

    //.........................................................................

    //.........................................................................



    /** pré-requis : gOrdi est une grille de Sudoku incomplète,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     *  action : met dans valPossibles l'ensemble des entiers de 1 à 9 pour chaque trou de gOrdi
     *           et leur nombre dans nbValPoss
     */
    public static void initPleines(int [][] gOrdi, boolean[][][] valPossibles, int [][] nbValPoss){
        //________________________________________________________________________________________________
        for(int i = 0; i < gOrdi.length; i++) {
            for(int j = 0; j < gOrdi[i].length; j++) {
                valPossibles[i][j] = ensPlein(9);
                nbValPoss[i][j] = 9;
            }
        }
    }  // fin initPleines

    //.........................................................................


    /** pré-requis : gOrdi est une grille de Sudoku incomplète,
     *               0 <= i < 9, 0 <= j < 9, gOrdi[i][j] > 0,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     *  action : supprime dans les matrices valPossibles et nbValPoss la valeur gOrdi[i][j] pour chaque case de la ligne,
     *           de la colonne et du carré contenant la case (i,j) correspondant à un trou de gOrdi.
     */
    public static void suppValPoss(int [][] gOrdi, int i, int j, boolean[][][] valPossibles, int [][]nbValPoss){
        //_____________________________________________________________________________________________________________
        int chiffreASupprimer = gOrdi[i][j];

        // Sous-carré
        int[] debCarre = debCarre(3, i, j);
        for(int x = debCarre[0]; x < debCarre[0] + 3; x++) {
            for(int y = debCarre[1]; y < debCarre[1] + 3; y++) {
                if(valPossibles[x][y][chiffreASupprimer]) {
                    valPossibles[x][y][chiffreASupprimer] = false;
                    nbValPoss[x][y]--;
                }
            }
        }

        // Ligne
        for(int y = 0; y < 9; y++) {
            int[] debCarreY = debCarre(3, i, y);
            if(debCarreY != debCarre) {
                if(valPossibles[i][y][chiffreASupprimer]) {
                    valPossibles[i][y][chiffreASupprimer] = false;
                    nbValPoss[i][y]--;
                }
            }
        }

        // Colonne
        for(int x = 0; x < 9; x++) {
            int[] debCarreX = debCarre(3, x, j);
            if(debCarreX != debCarre) {
                if(valPossibles[x][j][chiffreASupprimer]) {
                    valPossibles[x][j][chiffreASupprimer] = false;
                    nbValPoss[x][j]--;
                }
            }
        }
    }  // fin suppValPoss


    //.........................................................................

    /** pré-requis : gOrdi est une grille de Sudoju incomplète,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     * action :      met dans valPossibles l'ensemble des valeurs possibles de chaque trou de gOrdi
     *               et leur nombre dans nbValPoss
     */
    public static void initPossibles(int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss){
        //________________________________________________________________________________________________
        initPleines(gOrdi, valPossibles, nbValPoss);
        for(int i = 0; i < gOrdi.length; i++) {
            for(int j = 0; j < gOrdi[i].length; j++) {
                if (gOrdi[i][j] != 0) {
                    suppValPoss(gOrdi, i, j, valPossibles, nbValPoss);
                }
            }
        }
    }  // fin initPossibles

    //.........................................................................


    /** pré-requis : gSecret, gHumain et gOrdi sont des grilles 9x9
     *  action :     - demande au joueur humain de saisir le nombre nbTrous compris entre 0 et 81,
     *               - met dans gSecret une grille de Sudoku complète,
     *               - met dans gHumain une grille de Sudoku incomplète, pouvant être complétée en gSecret
     *                  et ayant exactement nbTrous trous de positions aléatoires,
     *               - met dans gOrdi une grille de Sudoku incomplète saisie par le joueur humain
     *                  ayant  nbTrous trous,
     *               - met dans valPossibles l'ensemble des valeurs possibles de chaque trou de gOrdi
     *                  et leur nombre dans nbValPoss.
     * retour : la valeur de nbTrous
     */
    public static int initPartie(int [][] gSecret, int [][] gHumain, int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss){
        //______________________________________________________________________________________________
        Ut.afficher("Veuillez saisir un nombre de trous entre 0 et 81: ");
        int nbTrous = saisirEntierMinMax(0, 81);
        initGrilleComplete(gSecret);
        initGrilleIncomplete(nbTrous, gSecret, gHumain);
        initPleines(gOrdi, valPossibles, nbValPoss);
        saisirGrilleIncompleteFichier(nbTrous, gOrdi, "grille1.txt");
        initPossibles(gOrdi, valPossibles, nbValPoss);
        return nbTrous;
    }

    //...........................................................
    // Tour du joueur humain
    //...........................................................

    /** pré-requis : gHumain est une grille de Sudoju incomplète pouvant se compléter en
     *               la  grille de Sudoku complète gSecret
     *
     *  résultat :   le nombre de points de pénalité pris par le joueur humain pendant le tour de jeu
     *
     *  action :     effectue un tour du joueur humain
     */
    public static int tourHumain(int [][] gSecret, int [][] gHumain){
        //___________________________________________________________________
        int penalites = 0;
        boolean fin = false;
        while (!fin) {
            afficheGrille(3, gHumain);
            Ut.afficher("Veuillez saisir la coordonnée x (ordonnée): ");
            int x = saisirEntierMinMax(1, 9) - 1;
            Ut.afficher("Veuillez saisir la coordonnée y (abscisse): ");
            int y = saisirEntierMinMax(1, 9) - 1;
            Ut.afficherSL("Vous venez de choisir la case (" + (x+1) + "," + (y+1) + ")");
            Ut.afficher("Veuillez saisir le chiffre à jouer (1-9 ou 0 pour Joker): ");
            int chiffre = saisirEntierMinMax(0, 9);
            if(chiffre == 0) {
                Ut.afficherSL("Vous avez demandé un Joker. Le chiffre était le " + gSecret[x][y] + " (+1 pt de pénalité)");
                gHumain[x][y] = gSecret[x][y];
                penalites++;
                fin = true;
            } else if(chiffre == gSecret[x][y]) {
                Ut.afficherSL("Bravo, le chiffre était bien le " + chiffre);
                gHumain[x][y] = chiffre;
                fin = true;
            } else {
                Ut.afficherSL("Dommage, ce n'est pas le bon chiffre (+1 pt de pénalité)");
                penalites++;
            }
        }
        return penalites;
    }  // fin  tourHumain

    //.........................................................................
    // Tour de l'ordinateur
    //.........................................................................

    /** pré-requis : gOrdi et nbValPoss sont des matrices 9x9
     *  résultat :   le premier trou (i,j) de gOrdi (c'est-à-dire tel que gOrdi[i][j]==0)
     *               évident (c'est-à-dire tel que nbValPoss[i][j]==1) dans l'ordre des lignes,
     *                s'il y en a, sinon le premier trou de gOrdi dans l'ordre des lignes
     *
     */
    public static int[] chercheTrou(int[][] gOrdi,int [][]nbValPoss){
        //___________________________________________________________________
        int[] premierTrou = new int[]{-1, -1};
        for(int i = 0; i < gOrdi.length; i++) {
            for(int j = 0; j < gOrdi[i].length; j++) {
                if(gOrdi[i][j] == 0) {
                    // Préciser le premier trou si nécessaire
                    if(premierTrou[0] == -1) {
                        premierTrou[0] = i;
                        premierTrou[1] = j;
                    }
                    // Vérifier si c'est un trou évident
                    if(nbValPoss[i][j] == 1)
                        return new int[]{i, j};
                }
            }
        }
        return premierTrou;
    }  // fin chercheTrou

    //.........................................................................

    /** pré-requis : gOrdi est une grille de Sudoju incomplète,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     *  action :     effectue un tour de l'ordinateur
     */
    public static int tourOrdinateur(int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss){
        //________________________________________________________________________________________________
        int[] trou = chercheTrou(gOrdi, nbValPoss);
        if(nbValPoss[trou[0]][trou[1]] == 1) {
            // Trou évident
            gOrdi[trou[0]][trou[1]] = uneValeur(valPossibles[trou[0]][trou[1]]);
            suppValPoss(gOrdi, trou[0], trou[1], valPossibles, nbValPoss);
            return 0;
        } else {
            if(nbValPoss[trou[0]][trou[1]] == 2) {
                // Il y a deux valeurs possibles, on en choisit une au hasard
                int[] chiffresPossibles = new int[2];
                int n = 0;
                for(int i = 1; i < valPossibles[trou[0]][trou[1]].length; i++) {
                    if(valPossibles[trou[0]][trou[1]][i]) {
                        chiffresPossibles[n++] = i;
                    }
                }
                int chiffre = chiffresPossibles[Ut.randomMinMax(0, 1)];
                // On demande au joueur humain si c'est le bon chiffre, sinon on prend l'autre valeur avec un point de pénalité
                afficheGrille(3, gOrdi);
                Ut.afficherSL("L'ordinateur propose le chiffre " + chiffre + " pour la case (" + (trou[0]+1) + "," + (trou[1]+1) + ")");
                Ut.afficher("Veuillez saisir 1 si c'est le bon chiffre, 2 sinon: ");
                int reponse = saisirEntierMinMax(1, 2);
                if(reponse == 1) {
                    gOrdi[trou[0]][trou[1]] = chiffre;
                    suppValPoss(gOrdi, trou[0], trou[1], valPossibles, nbValPoss);
                    return 0;
                } else {
                    gOrdi[trou[0]][trou[1]] = chiffresPossibles[0] == chiffre ? chiffresPossibles[1] : chiffresPossibles[0];
                    suppValPoss(gOrdi, trou[0], trou[1], valPossibles, nbValPoss);
                    return 1;
                }
            } else {
                // Il y a plus de deux valeurs possibles, on demande un Joker
                afficheGrille(3, gOrdi);
                Ut.afficherSL("L'ordinateur demande un Joker pour la case (" + (trou[0]+1) + "," + (trou[1]+1) + ")");
                Ut.afficher("Veuillez saisir le chiffre correspondant à cette case: ");
                int chiffre = saisirEntierMinMax(1, 9);
                gOrdi[trou[0]][trou[1]] = chiffre;
                suppValPoss(gOrdi, trou[0], trou[1], valPossibles, nbValPoss);
                return 1;
            }
        }
    }  // fin tourOrdinateur

    //.........................................................................
    // Partie
    //.........................................................................

    /** pré-requis : aucun
     *
     *  action : crée et initialise les matrices utilisées dans une partie, et effectue une partie de Sudoku entre le joueur humain et l'ordinateur.
     *
     *  résultat :   0 s'il y a match nul, 1 si c'est le joueur humain qui gagne et 2 sinon
     */
    public static int partie(){
        //_____________________________
        int[][] gSecret = new int[9][9], gHumain = new int[9][9];
        int[][] gOrdi = new int[9][9];
        int[][] nbValPoss = new int[9][9];
        boolean[][][] valPossibles = new boolean[9][9][10];

        int nbTrous = initPartie(gSecret, gHumain, gOrdi, valPossibles, nbValPoss);
        int penalitesH = 0, penalitesO = 0;
        // Faire jouer chaque tour
        while(nbTrous > 0) {
            Ut.clearConsole();
            Ut.afficherSL("C'est au tour du joueur");
            penalitesH += tourHumain(gSecret, gHumain);
            nbTrous--;
            if(nbTrous > 0) {
                Ut.afficherSL("C'est au tour de l'ordinateur");
                penalitesO += tourOrdinateur(gOrdi, valPossibles, nbValPoss);
            }
        }
        Ut.clearConsole();
        Ut.afficherSL("Fin de la partie");
        // Afficher les grilles finales avec les pénalités
        Ut.afficherSL("Grille humain :");
        afficheGrille(3, gHumain);
        Ut.afficherSL("=> Pénalités humain : " + penalitesH);
        Ut.afficherSL("Grille ordinateur :");
        afficheGrille(3, gOrdi);
        Ut.afficherSL("=> Pénalités ordinateur : " + penalitesO);
        // Vérifier le vainqueur
        if(penalitesH < penalitesO) {
            // Le vainqueur est l'humain
            return 1;
        } else if(penalitesO < penalitesH) {
            // Le vainqueur est l'ordinateur
            return 2;
        } else {
            // Aucun vainqueur, match nul
            return 0;
        }
    }  // fin partie

    //.........................................................................


    /** pré-requis : aucun
     *  action :     effectue une partie de Sudoku entre le joueur humain et l'ordinateur
     *               et affiche qui a gagné
     */
    public static void main(String[] args){
        //________________________________________
        int vainqueur = partie();
        if(vainqueur == 0) {
            Ut.afficherSL("Match nul. Il n'y a aucun vainqueur.");
        } else if(vainqueur == 1) {
            Ut.afficherSL("Le joueur a gagné.");
        } else {
            Ut.afficherSL("L'ordinateur a gagné.");
        }
    }  // fin main

} // fin SudokuBase



