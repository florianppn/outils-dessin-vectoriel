# Outils de dessin vectoriel

Petit éditeur de dessin vectoriel en Java : interface graphique (Swing) et commandes en ligne de commande.

## Prérequis

- Java 17
- Maven

## Lancer l’application

À la racine du dépôt :

```bash
mvn -Pedit exec:java
```

## Lancer les autres programmes

### Fusion de deux dessins (`merge`)

```bash
mvn -Pmerge exec:java
```

### Conversion en image PNG (`v2bmp`)

```bash
mvn -Pv2bmp exec:java
```

Les fichiers d'entrée/sortie sont ensuite fournis via l'interpréteur de commandes dans le terminal de l'application
(par ex. `merge A.vec B.vec fusion.vec` ou `v2bmp in.vec out.png`).

## Construire le projet

```bash
mvn package
```

## Générer la Javadoc

```bash
mvn javadoc:javadoc
```

La documentation HTML est produite dans le dossier `javadoc/` (ouvrir `javadoc/apidocs/index.html` dans un navigateur).

Le rapport du projet se trouve dans `doc/rapport.tex`.
