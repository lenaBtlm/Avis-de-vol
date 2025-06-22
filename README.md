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
```bash
# Installation et compilation
cd backend/avisdevol
./mvnw clean install

# Lancement de l'application
./mvnw spring-boot:run

# L'application est accessible sur http://localhost:8080
```

### Structure et Relations

#### Points d'Entrée
- `AvisDeVolApplication.java` : Point d'entrée de l'application Spring Boot
- `application.properties` : Configuration de l'application et base de données

#### Controllers (API REST)
- `ReviewController.java` : Gestion des requêtes pour les avis
  - Endpoints pour création/lecture/modification/suppression des avis
  - Validation des données entrantes
  
- `CommentController.java` : Gestion des commentaires
  - Endpoints pour les réponses aux avis
  - Gestion des interactions avec les avis

#### Models (Entités)
- `Review.java` : Modèle pour les avis
  - Structure des données d'un avis
  - Relations avec les autres entités (Status, Comment)

- `Comment.java` : Modèle pour les commentaires
  - Structure des réponses aux avis
  - Liaison avec les avis concernés

- `StatusDTO.java` : Objet de transfert pour les statuts (sert à transporter les données entre le backend et le frontend de façon sécurisée et simplifiée)
    - Gestion des états des avis
    - Communication avec le frontend

#### Repositories (Accès aux données)
- `ReviewRepository.java` : Interface d'accès aux avis
  - Opérations sur les avis
  - Requêtes personnalisées

- `CommentRepository.java` : Interface d'accès aux commentaires
  - Gestion des commentaires
  - Requêtes spécifiques aux commentaires

### Tests du Backend via Postman

#### 1. Gestion des Avis
**Liste des avis**
  ```http
  GET http://localhost:8080/api/reviews
  ```
- Récupération de tous les avis

**Filtrage et Tri**

**Par note:**
  ```http
  GET http://localhost:8080/api/reviews/score/{rating}
  ```

  Exemple avec comme note 4:
  ```http
  GET http://localhost:8080/api/reviews/score/4
  ```

**Par date:**
  ```http
  GET http://localhost:8080/api/reviews/date/{dateOfFlight}
  ```

  Exemple avec comme date 2025-06-22:
  ```http
  GET http://localhost:8080/api/reviews/date/2025-06-22
  ```

**Par compagnie:**
  ```http
  GET http://localhost:8080/api/reviews/company/{company}
  ```

  Exemple avec comme compagnie Air France:
  ```http
  GET http://localhost:8080/api/reviews/company/air%20france
  ```

**Par numéro de vol:**
  ```http
  GET http://localhost:8080/api/reviews/flight/{flightNumber}
  ```

  Exemple avec comme numéro de vol A380:
  ```http
  GET http://localhost:8080/api/reviews/flight/A380
  ```

- **Visualisation d'un avis spécifique**
  ```http
  GET http://localhost:8080/api/reviews/{id}
  ```

  Exemple avec comme id 5:
  ```http
  GET http://localhost:8080/api/reviews/5
  ```

#### 2. Gestion des Réponses
- **Ajout d'une réponse**
**Ajout d'une réponse à un avis**
```http
POST http://localhost:8080/api/reviews/{id}/comments
```
```json
{
    "text": "I agree",
    "public": true
}
```
Le champ `public` permet d'indiquer si le commentaire doit être visible publiquement sur le frontend.
Si `public` est à `true`, le commentaire sera affiché aux utilisateurs ; si `false`, il restera privé et ne sera pas affiché.
Cette option facilitera la gestion de la visibilité des réponses lors de l'ajout de la fonctionnalité côté frontend.

- **Modification du statut**
  ```http
  PUT http://localhost:8080/api/reviews/{id}/status
  ```
  ```json
  {
    "status": "PUBLISHED"  // ou "TREATED", "REJECTED"
  }
  ```

## 4. Base de données

### Configuration
- Base de données : MySQL 8.0
- Base de données : avisdevol

### Structure de la base de données

#### Tables principales

1. **review** : Stockage des avis
```sql
+----------------+--------------+------+-----+---------+----------------+
| Field          | Type         | Null | Key | Default | Extra          |
+----------------+--------------+------+-----+---------+----------------+
| id             | bigint       | NO   | PRI | NULL    | auto_increment |
| comment        | varchar(255) | YES  |     | NULL    |                |
| date_of_flight | varchar(255) | YES  |     | NULL    |                |
| flight_number  | varchar(255) | YES  |     | NULL    |                |
| rating         | int          | YES  |     | NULL    |                |
| status         | varchar(20)  | YES  |     | NULL    |                |
| company        | varchar(255) | YES  |     | NULL    |                |
+----------------+--------------+------+-----+---------+----------------+
```

2. **comment** : Stockage des réponses aux avis
```sql
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| id        | bigint       | NO   | PRI | NULL    | auto_increment |
| is_public | bit(1)       | NO   |     | NULL    |                |
| text      | varchar(255) | YES  |     | NULL    |                |
| review_id | bigint       | NO   | MUL | NULL    |                |
+-----------+--------------+------+-----+---------+----------------+
```

### Relations
- Une relation One-to-Many entre `review` et `comment`
- Un avis peut avoir plusieurs commentaires
- Un commentaire appartient à un seul avis
- La suppression d'un avis entraîne la suppression de ses commentaires

## 5. Diagramme de l'application

![Diagramme Mermaid](./diagram/avisdevol_%20Mermaid.png)

J'ai utilisé [Mermaid](https://mermaid-js.github.io/) pour illustrer les relations entre les entités de l'application.