# Année 1 - SAE 1.01
Projet Sudoku - SAE 1.01 | Année 1 | IUT Montpellier-Sète

# Descriptif
**Sujet** : On s'intéresse à un jeu phare jouable à un seul joueur : le **Sudoku**. Le joueur doit compléter une grille de Sudoku incomplète _(une grille carrée 9x9 partitionnée en 9 carrés 3x3)_, en une grille de Sudoku complète. Cependant dans ce projet, on considère une **variante** du jeu de Sudoku, qui se joue à 2 joueurs : chacun des 2 joueurs choisit en secret une grille de Sudoku complète, en enlève certaines valeurs et donne la grille de Sudoku dite incomplète à l'autre joueur, qui ensuite à son tour devra compléter la grille de Sudoku incomplète donnée par l'autre joueur. Le but est de programmer une partie de ce jeu entre l'ordinateur et un joueur humain, jouable par le biais d'un terminal/[shell](https://fr.wikipedia.org/wiki/Bourne-Again_shell).

Le travail est segmenté en deux parties :
- La **version de base** (sur 11 points) constitue le "minimum requis" du jeu de Sudoku pour être jouable, c'est-à-dire une **partie simple** entre l'ordinateur et un joueur humain, où le joueur humain choisit le _**nbTrous** (nombre de trous pour une grille incomplète)_, pouvant vérifier les propositions des joueurs et **attribuer** les points gagnés ou perdus, ainsi qu'évidemment indiquer le **gagnant**.
- La **version améliorée** (sur 12 points) constitue la version de base et les différentes extensions améliorant la qualité et la complexité du jeu, il y a un total de 8 extensions.

L'ensemble du sujet est disponible en [pdf](sujet_SAE_1_Sudoku.pdf).

# Extensions
- [x] 3.1/ Joker versus choix aléatoire (1 pt)
- [x] 3.2/ Gestion de la tricherie (1 pt)
- [x] 3.3/ Optimisation de la gestion des trous évidents (1 pt)
- [x] 3.4/ Construction d’une grille de Sudoku complète (2 pts)
- [x] 3.5/ Construction d’une grille incomplète de niveau facile (1.5 pt)
- [ ] 3.6/ Comment trouver plus de trous évidents ? (2 pts)
- [ ] 3.7/ Elimination de valeurs impossibles (2 pts)
- [ ] 3.8/ Prêcher le vrai pour obtenir le faux (1.5 pt)

# Membres du groupe
- Milwenn FRANCEUS--COINTREL
- Eden LORENZO

# Implication de chaque membre
- Milwenn FRANCEUS--COINTREL (60%):
  - Base (ensPlein, uneValeur, afficheGrille, debCarre, initGrilleIncomplete, initPleines, suppValPoss, initPossibles, tourOrdinateur, partie, main)
  - Extension n°1
  - Extension n°2
  - Extension n°3
  - Extension n°4
  - Extension n°5
- Eden LORENZO (40%):
  - Base (supprime, saisirEntierMinMax, copieMatrice, saisirGrilleIncomplete, chercheTrou, tourHumain, partie, main)
  - Extension n°1
  - Extension n°2
