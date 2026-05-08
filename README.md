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

## Lancer l’application Fusion (`merge A.vec B.vec fusion.vec`)

À la racine du dépôt :

```bash
mvn clean compile -Pmerge exec:java
```

### Lancer l’application de Conversion en image PNG (`v2bmp in.vec out.png`)

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
