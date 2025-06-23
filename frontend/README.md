# Frontend

## Technologies et Dépendances
- Angular CLI version 20.0.3
- TypeScript 5.2.0
- Node.js 18+

## Bibliothèques externes
- Bootstrap 5.3.0 : Framework CSS pour le design responsive
- @ng-bootstrap/ng-bootstrap : Composants Bootstrap pour Angular

## Installation et démarrage
```bash
# Installation des dépendances
cd frontend
npm install

# Lancement du serveur de développement
ng serve

# L'application est accessible sur http://localhost:4200
```

## Flux de Données
```
Frontend (Angular) → Requêtes HTTP → Backend (Spring Boot) → Base de données
```

## Structure et Relations

### Points d'Entrée
- `index.html` : Point d'entrée HTML de l'application
- `main.ts` : Initialisation de l'application Angular
- `styles.css` : Styles globaux de l'application

### Configuration Application
- `app.config.ts` : Configuration globale de l'application Angular
- `app.routes.ts` : Définition des routes de navigation
- `app.html` : Template principal de l'application
- `app.css` : Styles du composant principal
- `app.ts` : Composant racine de l'application

### Composants Principaux
- `components/review-form/` : Gestion des formulaires d'avis
  - Communique avec `ReviewController` pour la soumission
  - Validation des données avant envoi
- `components/review-list/` : Affichage des avis
  - Récupération des données depuis le backend
  - Affichage et filtrage des avis

### Fichiers de Configuration
- `angular.json` : Configuration du projet Angular
- `proxy.conf.json` : Configuration du proxy pour le développement
- `tsconfig.json` : Configuration TypeScript
