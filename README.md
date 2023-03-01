# Test Technique Java Backend - Ekwateur

L'objectif de ce test est l'écriture d'un programme purement backend. Il peut éventuellement servir de support dans le cadre 
d'un futur entretien au cours duquel il pourra être demandé de faire des évolutions fonctionelles dessus. Si certaines parties
ne te paraissent pas claires, nous t'encourageons à nous poser toutes les questions que tu juges utiles.

## Enoncé de l'exercice
Le product owner te demande de developper un programme qui permet de calculer le montant à facturer à un client d'Ekwateur pour 
un mois calendaire.

Ce programme devra gérer 2 types de clients :

A) Les clients Pro, qui ont les propriétés suivantes :
- Reference Client (EKW + 8 caractères numériques)
- N° SIRET
- Raison Sociale
- CA

B) Les particuliers, qui ont les propriétés suivantes :
- Reference Client (EKW + 8 caractères numériques)
- Civilité
- Nom
- Prénom

Un client peut consommer deux types d'énergies :
- Electricité
- Gaz

Chaque énergie est facturée au kWh.
- Pour les particuliers, le prix du kWh est de 0,121 € pour l'électricité et 0,115€ pour le gaz
- Pour les pro, ayant un CA supérieur à 1 000 000 €, le prix du kWh est de 0,114 € pour l'électricité et 0,111€ pour le gaz
- Pour les pro, ayant un CA inférieur à 1 000 000 €, le prix du kWh est de 0,118 € pour l'électricité et 0,113€ pour le gaz

## Contraintes techniques
La seule contrainte technique est l'utilisation du langage Java dans sa version 8 au minimum.

## Ce qui est attendu
Le minimum attendu est un programme fonctionnel qui puisse etre executé et testé.

## Organisation du projet
- Le projet suit une infrastructure en monorepo pour avoir tous les modules dans le même répo git
- cette structure favorise le passage en architecutre microservices
- la structure du projet est la suivante:
```
ekwateur-billing-module
│   README.md
└─apps
│   └─ billing-api
│   └─ customers-data-store
└─libs
│   └─ billing-dependencies
│   └─ billing-model
│   └─ rest-api-standards
│   └─ billing-common-test
─runConfigurations
│   └─ 01__billing_dependencies__clean_install__U_.xml
│   └─ 02_rest_api_standards__clean_install__U_.xml
│   └─ 03_billing_model__clean_install__U_.xml
│   └─ 04__common_test__clean_install__U_.xml
│   └─ 05_customer_data_store__clean_install__U_.xml
│   └─ 06_billing_api__clean_install__U_.xml
```
- **dossier apps**: contient les deux applications principales du projet 
  - **billing-api**: c'est le point d'entrée du projet, contient les api nécessaires exposées pour calculer la facturation
  - **customers-data-store**: comme son nom l'indique, c'est la couche d'accès aux données. elle est séparée de l'application de base afin de renforcer le découpage par domaine et renforcer le principe SRP 
- **dossier libs**: contient les librairies partagées par les applications
  - **billing-dependencies**: définit les dépendances maven du projet afin que tous les modules utilisent les mêmes versions
  - **billing-model**: contient les modèles de données utilisés par les applications
  - **rest-api-standards**: une librairie simple qui contient quelques implémentations en spring des standards REST
  - **billing-common-test**: une librairie qui contient les classes de test communes aux applications
 - **dossier runConfiguration**: contient les configurations maven pour builder chaque module du projet

## Comment démarrer le projet
- Pour démarrer le projet, il faut d'abord builder les librairies partagées du projet.
 Pour ce faire, il faut exécuter les configurations maven suivantes dans l'ordre:
  - 01__billing_dependencies__clean_install__U_.xml
  - 02_rest_api_standards__clean_install__U_.xml
  - 03_billing_model__clean_install__U_.xml
  - 04__common_test__clean_install__U_.xml
- Ensuite, il faut builder les applications:
  - 05_customer_data_store__clean_install__U_.xml
  - 06_billing_api__clean_install__U_.xml


- Une fois toutes les applications ont été buildées, il suffit de lancer dans l'ordre
  - la classe **CustomerDataStoreApplication.java** qui démarre sur le port 8081
  - et enfin la classe **BillingApiApplication.java** pour démarrer l'application sur le port 8080
  - Pour tester l'application, avec postman, interrogez l'url <em>**http://localhost:8080/bills/{customer-reference}?startDate={start-date}&endDate={endDate}** </em>
    - où:
        - **customer-reference**: la référence du client, les valeurs possibles sont 
          - **EKW00000001** pour un client particulier
          - **EKW00000002** pour un client pro ayant un CA inférieur à 1 000 000 €
          - **EKW00000003** pour un client pro ayant un CA supérieur à 1 000 000 €
        - **start-date**: est la date de début de la période de facturation,
        - **endDate**: est la date de fin de la période de facturation
          - les dates doivent être au format **yyyy-MM-dd**, l'interval de date dans l'application est entre 2023-02-01 et 2023-02-28 
  - Exemple: <em>**http://localhost:8080/bills/EKW00000001?startDate=2023-02-01&endDate=2023-02-28** </em>
