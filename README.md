# SimpleCash – Projet Spring Boot

Application bancaire permettant aux conseillers de gérer leurs clients, leurs comptes, et les opérations sensibles telles que les virements internes.

---

## Objectifs du projet

- Implémenter une application de gestion client pour la banque **SimpleCash**.  
- Fournir une architecture claire (modèle en couches).  
- Exposer des Web Services REST.  
- Gérer les opérations bancaires essentielles :
  - CRUD Client
  - Gestion des comptes
  - Crédit / débit
  - Virement compte à compte (avec logs AOP)
  - Audit des comptes  
- Prioriser la qualité, la modernité et la lisibilité du code conformément aux critères d’évaluation.

---

## Architecture

Architecture en couches :

```
src/main/java
 ├── controller    - Exposition REST (API)
 ├── service       - Règles métier + transactions
 ├── repository    - Accès base (JPA)
 ├── entity        - Modèle de données (JPA)
 ├── dto           - Objets d'échange API
 ├── config        - AOP, logging, configuration
 └── exception     - Gestion des erreurs métier
```

Base de données : **H2** (in-memory)  
ORM : **JPA / Hibernate**  
Serveur : **Spring Boot 3.5.8**  
Java : **17**

---

## Technologies utilisées

- Java 17  
- Spring Boot (Web, JPA, AOP)  
- H2 Database  
- Maven  
- Lombok  
- JUnit 5 (tests)

---

## Fonctionnalités prévues (MVP)

### Indispensables (prioritaires)
- CRUD Client  
- Gestion des comptes (courant / épargne)  
- Créditer un compte  
- Débiter un compte (+ gestion du découvert)  
- Virement compte à compte  
- Logging AOP des opérations de virement (transactions sensibles)  
- Audit : liste des comptes créditeurs/débiteurs + totaux  

### Secondaires (si le temps le permet)
- Gestion des cartes bancaires  
- Simulation de crédit  
- Tests unitaires avancés  
- Documentation détaillée des endpoints

---

## Tests

Tests unitaires prévus pour :  
- opérations de débit/crédit  
- validation des règles métier  
- virements  
- audit  

Les tests sont un plus dans l’évaluation.

---

## Documentation

La documentation finale inclura :  
- Diagramme UML (architecture + classes métier)  
- User stories minimales  
- Bilan du projet : reste à faire, difficultés rencontrées, fonctionnalités réalisées

---

## Lancement du projet

### 1. Cloner le projet

```bash
git clone https://github.com/raphaellelievre/projet_Raphael_Lelievre.git
cd projet_Raphael_Lelievre
```

### 2. Lancer l’application

```bash
mvn spring-boot:run
```

### 3. Accès H2 Console

```
http://localhost:8080/h2-console
```

**Configuration H2 par défaut**  
- JDBC URL : `jdbc:h2:mem:testdb`  
- User : `sa`  
- Password : (vide)

---

## Auteur

Raphaël Lelièvre
