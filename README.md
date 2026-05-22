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

- `new` : initialise un nouveau dessin. Si un dessin était chargé ou en cours de création, il est abandonné.

- `load fichier.vec` : abandonne le dessin en cours et charge le dessin contenu dans le fichier `fichier.vec`.

- `save fichier.vec` : sauvegarde le contenu du dessin en cours dans le fichier `fichier.vec`.

- `line x y x' y' col` : créé une ligne entre les points (x,y) et (x',y') avec la couleur `col`.

- `rect x y x' y' col` : créé un rectangle entre les points (x,y) et (x',y') avec la couleur `col`.

- `circ x y r col` : créé un cercle de centre (x,y) et de rayon `r` avec la couleur `col`.

- `elli x y rx ry col` : créé une ellipse de centre (x,y), de rayon horizontal `rx`, de rayon vertical `ry` avec la couleur `col`.

- `list` : liste en numérotant les formes composant le dessins.

- `grp label r1 r2 ...` : créé un groupe avec le nom `label` et y place les éléments de dessin de rang `r1 r2 ...` . Le rang d’un élément est obtenu avec la commande `list`.

- `ugrp r` : défait le groupe de rang `r`. Pour cela, les éléments qui le composent sont déplacés à la racine du dessin et le groupe est supprimé.

- `quit` : termine le programme et rend la main au système.

### Editeur de fusion

- `merge A.vec B.vec fusion.vec` : fusionne le fichier `A.vec` avec le fichier `B.vec` sous le nom de `fusion.vec`.

- `quit` : termine le programme et rend la main au système.

### Editeur de conversion en image matricielle

- `v2bmp in.vec out.png` : convertis le fichier `in.vec` en fichier `out.png`.

- `quit` : termine le programme et rend la main au système.
