# Football_Stars - Application Android  
Une application Android dynamique qui affiche des stars du football avec des animations et une gestion interactive de liste.  

---

## Introduction  
L’application **Football_Stars** a été développée pour expérimenter plusieurs fonctionnalités du développement Android : **animations dynamiques**, **RecyclerView** avec filtrage, gestion des **menus**, et utilisation de **bibliothèques externes** comme Glide. Ce projet met également en avant l'architecture MVC, favorisant la séparation des responsabilités entre l'interface utilisateur et la logique métier.  

---

## Structure du Projet  
Le projet est organisé comme suit :  

Football_Stars/ │ ├── java/
│ ├── SplashActivity.java
│ ├── ListActivity.java
│ └── com/example/stars/
│ ├── adapter/
│ │ └── StarAdapter.java
│ ├── beans/
│ │ └── Star.java
│ ├── dao/
│ │ └── IDao.java
│ └── service/
│ └── StarService.java
│ ├── res/layout/
│ ├── activity_splash.xml
│ ├── activity_list.xml
│ ├── star_edit_item.xml
│ └── star_item.xml

---

## Architecture de l'Application  
L'application suit une **architecture MVC** :  
- **Modèle (Model)** : Défini par la classe `Star` qui représente une star de football et par le DAO `IDao` qui gère les données.  
- **Vue (View)** : Implémentée par les **layouts XML** (activity_splash, activity_list, etc.) pour afficher l'interface utilisateur.  
- **Contrôleur (Controller)** : Composé des activités **SplashActivity** et **ListActivity** ainsi que des services comme **StarService**.

---

## Fonctionnalités  
1. **Animations dynamiques** :  
   - Splash screen avec rotation, translation et effet de transparence.  
2. **Gestion de liste avec RecyclerView** :  
   - Liste des stars avec un filtrage dynamique.    
3. **Affichage circulaire d’images** avec Glide.
4. **partager un star**.
5. **Modifier le rating d'un star**
     


---

## Technologies et Bibliothèques  
- **Langage** : Java  
- **IDE** : Android Studio  
- **Architecture** : MVC  
- **Bibliothèques** :  
  - **Glide** : Chargement et gestion des images.  
  - **RecyclerView** : Affichage dynamique des listes.  
  - **CircleImageView** : Pour afficher des images circulaires.  

