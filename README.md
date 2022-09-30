<h1>API GESTION DE MODÈLES DE VOITURE</h1>

<h2>Liste des marques de voitures (GET : /)</h2>
<img src="https://user-images.githubusercontent.com/74946423/193211578-4e571454-071d-4d1b-8a85-8dd96c7c7ca0.png">

<h2>Liste des modèles d’une marque par le nom de la marque (GET : /models/make-name/{name})</h2>
 <img src="https://user-images.githubusercontent.com/74946423/193211799-f6221b6f-31b3-4254-9e66-2a208f5a99a1.png">

<h2>Liste des modèles d’une marque par l’id de la marque (GET : /models/make-id/{id})</h2>
 <img src="https://user-images.githubusercontent.com/74946423/193211819-22c50aa5-1214-4d00-8719-aff5dbb1f51c.png">

<h2>Recherche d’un modèle par id (GET : /models/id/{id})</h2>
 <img src="https://user-images.githubusercontent.com/74946423/193211841-2089bf6a-2f81-4779-b96d-be2b51f0e0c1.png">

<h2>Liste des modèles selon le nom, la catégorie et l’année (GET : /models)</h2>
<h3>Paramètres :</h3>
<ul>
  <li>name : Nom du modèle (optionnel)</li>
  <li>category : Catégorie du modèle (optionnel)</li>
  <li>year : Année de production du modèle (optionnel)</li>
</ul>
<img src="https://user-images.githubusercontent.com/74946423/193211891-dee792c9-8562-4ffa-b577-22f6709929ae.png">

 <br>

<h2>Création d’un nouveau modèle (POST : /models/create/{makeId})</h2>
makeId : identifiant de la marque concernée
<h3>Paramètres (JSON) :</h3>
<ul>
  <li>name : Nom du modèle (obligatoire)</li>
  <li>category : Catégorie du modèle (obligatoire)</li>
  <li>year : Année de production du modèle (obligatoire)</li>
</ul>
<img src="https://user-images.githubusercontent.com/74946423/193211935-12cde7a9-3416-49d9-a5af-76f7913aca81.png">

<br>
 
<h2>Modification d’un modèle (PUT : /models/update)</h2>
<h3>Paramètres JSON :</h3>
<ul>
  <li>id : Identifiant du modèle à modifier (obligatoire)</li>
  <li>name : Nom du modèle (optionnel)</li>
  <li>category : Catégorie du modèle (optionnel)</li>
  <li>year : Année de production du modèle (optionnel)</li>
</ul>
<img src="https://user-images.githubusercontent.com/74946423/193212100-2a58dc4e-c6ba-494f-8edd-2e4b164d4704.png">

<br>
 
<h2>Suppression d’un modèle (DELETE : /models/delete/{id}</h2>
id : Identifiant du modèle à supprimer
<img src="https://user-images.githubusercontent.com/74946423/193212125-c62085e7-26e4-4aed-9fe8-c3258182ef17.png">

<br>
 
<h2>Logo d’une marque (GET : /logo/{name}</h2>
name : nom de la marque
 <img src="https://user-images.githubusercontent.com/74946423/193212157-625f734d-3869-4ef0-8232-a20ea3b5281d.png">



