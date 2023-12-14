Extension n°1 : 'SudokuBase_ext1'
=> Pour cette extension, nous partirons du principe qu'il sera judicieux de tenter sa chance seulement si il y a deux valeurs possibles. Dans le pire des cas, on aura un point de pénalité. Lorsque l'on a plus de deux valeurs possibles, on prendra un joker pour n'avoir qu'un point de pénalité.

Extension n°2 : 'SudokuBase_ext2'
=> Nous appliquerons cette extension dans les deux fonctions de saisie de valeur, soit 'saisirGrilleIncomplete' et 'saisirGrilleIncompleteFichier'.

Extension n°3 : 'SudokuBase_ext3'
=> Pour une meilleure compréhension et une meilleure optimisation de la pile 'tabTrous', nous avons utilisés la classe Stack qui est implémentée directement dans Java : cette classe agit exactement comme la méthode LIFO (Last In, First Out). C'est pour cela que nous insérons directement les trous sans insérer au préalable le nombre de trous à l'indice 0.
=> Nous avons implémentés trois fonctions (remplirTabTrous, chercheTrou, tourOrdinateur) dont deux fonctions (chercheTrou, tourOrdinateur) étant des modifications avec l'ajout de la pile 'tabTrous' comme paramètre pour l'utiliser directement dans la fonction 'partie'.

Extension n°4 : 'SudokuBase_ext4'
=> Pour effectuer ces transformations sur une grille pré-définie, nous avons créé la fonction 'genererGrilleCompleteOrdinateur' qui prend en paramètre le nombre de succession de transformation (pour répéter nbSuccessions fois).
=> Pour chaque succession sera décidé la transformation subie, via un chiffre aléatoire:
    - La fonction 'rotationGrille' applique une rotation de 90° à la grille;
    - La fonction 'symetrieHorizontalGrille' échange symétriquement les positions horizontalement;
    - La fonction 'symetrieDiagonaleGrille' échange symétriquement les positions diagonalement;
    - La fonction 'flipflopGrille' échange deux lignes précisé en paramètres, appartenant au même sous-carré. Ce sous-carré étant choisi aléatoirement à chaque appel.

Extension n°5 : 'SudokuBase_ext5'
=> Pour créer une grille incomplète à partir d'une grille complète, avec trous évidents seulement, nous avons décidés de séparer en trois fonctions:
    - La fonction 'initGrilleIncompleteFacile' qui est la base de l'extension qui permet de parcourir la grille indiqué en paramètre et d'attribuer les trous si possible. Cette fonction retoure alors le nombre de trous généré;
    - La fonction 'peutEtreEvident' qui vérifie si la case indiqué en paramètre peut être un trou évident;
    - La fonction 'verifierNouvelEvident' qui vérifie si la case indiqué en paramètre peut être un trou évident et n'affecte pas les autres trous évidents. Si cette dernière vérification n'est pas faite, les trous précédents peuvent se retrouver avec plusieurs solutions (donc plus évidents).
