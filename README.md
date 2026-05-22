# Outils de dessin vectoriel

Petit éditeur de dessin vectoriel en Java : interface graphique (Swing) et commandes en ligne de commande.

## Prérequis

- Java 17
- Maven

## Lancer l’application Edit

À la racine du dépôt :

```bash
mvn clean compile -Pedit exec:java
```

## Lancer l’application Fusion

À la racine du dépôt :

```bash
mvn clean compile -Pmerge exec:java
```

### Lancer l’application de Conversion en image PNG

À la racine du dépôt :

```bash
mvn clean compile -Pv2bmp exec:java
```

## Générer la Javadoc

```bash
mvn javadoc:javadoc
```

La documentation HTML est produite dans le dossier `javadoc/` (ouvrir `javadoc/apidocs/index.html` dans un navigateur).

Le rapport du projet se trouve dans `doc/rapport.pdf`.

## Guide des commandes

Une fois l'application lancée, vous pouvez saisir les commandes suivantes dans la console.

### Éditeur Principal
Permet de créer, grouper et manipuler des formes. Les couleurs peuvent être saisies par nom (ex: `red`, `blue`).

*   **Formes géométriques :**
    *   `rect <x> <y> <largeur> <hauteur> <couleur>` : Ajoute un rectangle.
    *   `circ <cx> <cy> <rayon> <couleur>` : Ajoute un cercle.
    *   `line <x1> <y1> <x2> <y2> <couleur>` : Ajoute une ligne.
    *   `elli <cx> <cy> <rx> <ry> <couleur>` : Ajoute une ellipse.
*   **Groupement :**
    *   `grp <id1> <id2> ...` : Groupe au moins deux formes par leur index.
    *   `ugrp <id>` : Dégroupe une forme composite.
*   **Gestion du dessin :**
    *   `list` : Affiche la liste des formes présentes et leurs index.
    *   `new` : Efface le dessin actuel.
    *   `load <fichier.xml>` : Charge un dessin depuis un fichier.
    *   `save <fichier.xml>` : Sauvegarde le dessin actuel.
*   **Système :**
    *   `quit` : Quitte l'application.

### Editeur de fusion
Outil spécialisé pour combiner deux fichiers de dessin.

*   `merge <fichier1.xml> <fichier2.xml> <sortie.xml>` : Fusionne les deux fichiers sources dans un nouveau fichier de sortie.
*   `quit` : Quitte l'application.

### Editeur de conversion
Outil de rendu pour transformer vos dessins vectoriels en images.

*   `v2bmp <entrée.xml> <sortie.bmp>` : Convertit un fichier XML de dessin en image bitmap.
*   `quit` : Quitte l'application.
