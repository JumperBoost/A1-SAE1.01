Extension n°1 : 'SudokuBase_ext1'
=> Pour cette extension, nous partirons du principe qu'il sera judicieux de tenter sa chance seulement si il y a deux valeurs possibles. Dans le pire des cas, on aura un point de pénalité. Lorsque l'on a plus de deux valeurs possibles, on prendra un joker pour n'avoir qu'un point de pénalité.

Extension n°2 : 'SudokuBase_ext2'
=> Nous appliquerons cette extension dans les deux fonctions de saisie de valeur, soit 'saisirGrilleIncomplete' et 'saisirGrilleIncompleteFichier'.

Extension n°3 : 'SudokuBase_ext3'
=> Pour une meilleure compréhension et une meilleure optimisation de la pile 'tabTrous', nous avons utilisés la classe Stack qui est implémentée directement dans Java : cette classe agit exactement comme la méthode LIFO (Last In, First Out). C'est pour cela que nous insérons directement les trous sans insérer au préalable le nombre de trous à l'indice 0.
=> Nous avons implémentés trois fonctions (remplirTabTrous, chercheTrou, tourOrdinateur) dont deux fonctions (chercheTrou, tourOrdinateur) étant des modifications avec l'ajout de la pile 'tabTrous' comme paramètre pour l'utiliser directement dans la fonction 'partie'.