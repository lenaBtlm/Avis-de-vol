# Avis-de-vol

## 1. Introduction
"Avis de Vol" est une application web permettant aux voyageurs de partager leurs expériences de vol et aux compagnies aériennes de gérer ces retours clients de manière efficace.

### Objectif
L'application se compose de deux parties principales :
- Une interface client permettant aux voyageurs de soumettre des avis sur leurs vols
- Un back destiné aux gestionnaires pour le traitement et le suivi des avis

### Fonctionnalités principales
#### Partie Client
- Formulaire de soumission d'avis avec :
  - Sélection des informations du vol (numéro, date, compagnie)
  - Système de notation par étoiles (1-5)
  - Champ de commentaire
- Consultation des avis publiés

#### Partie Back
- Gestion complète des avis :
  - Liste des avis avec filtres et tri
  - Visualisation détaillée des avis
  - Système de réponse aux avis
  - Gestion du statut des avis (traité, publié, rejeté)

## 2. Frontend

### Technologies et Dépendances
- Angular CLI version 20.0.3
- TypeScript 5.2.0
- Node.js 18+

### Bibliothèques externes
- Bootstrap 5.3.0 : Framework CSS pour le design responsive
- @ng-bootstrap/ng-bootstrap : Composants Bootstrap pour Angular

### Installation et démarrage
```bash
# Installation des dépendances
cd frontend
npm install

# Lancement du serveur de développement
ng serve

# L'application est accessible sur http://localhost:4200
```

### Flux de Données
```
Frontend (Angular) → Requêtes HTTP → Backend (Spring Boot) → Base de données
```

### Structure et Relations

#### Points d'Entrée
- `index.html` : Point d'entrée HTML de l'application
- `main.ts` : Initialisation de l'application Angular
- `styles.css` : Styles globaux de l'application

#### Configuration Application
- `app.config.ts` : Configuration globale de l'application Angular
- `app.routes.ts` : Définition des routes de navigation
- `app.html` : Template principal de l'application
- `app.css` : Styles du composant principal
- `app.ts` : Composant racine de l'application

#### Composants Principaux
- `components/review-form/` : Gestion des formulaires d'avis
  - Communique avec `ReviewController` pour la soumission
  - Validation des données avant envoi
- `components/review-list/` : Affichage des avis
  - Récupération des données depuis le backend
  - Affichage et filtrage des avis

#### Fichiers de Configuration
- `angular.json` : Configuration du projet Angular
- `proxy.conf.json` : Configuration du proxy pour le développement
- `tsconfig.json` : Configuration TypeScript

## 3. Backend
### Technologies
- Spring Boot 3.5.0
- Java 17
- Maven

### Installation et démarrage


## 4. Base de données
### Configuration


### Modèle de données


## 5. Diagrammes
