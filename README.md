#Devoir 1
=======
Votre nom: *Veuillez inscrire votre nom complet ici*
Votre URL sur Heroku: *Veuillez fournir l'URL d'accès à Heroku ici*
=======
##Correction 

*Votre correction sera fournie à cet endroit*

=======

##Énoncé

**Contexte**

Vous avez acheté un système d’affichage d’images protégées par tatouage numérique (watermarking). Le watermarking est une technique qui permet d’inclure du code dans une image afin d’en protéger le contenu. Ici, le watermark est simplement un texte en transparence qui est ajouté sur l’image. Pour faire ceci, les requêtes d’affichage d’images passent par un Servlet, soit WatermarkServlet. 

Donc lors de l’affichage d’une étiquette d’une image, en y intégrant un mapping qui sera compris dans le fichier web.xml, l’image sera affichée par le Servlet de watermarking. Cependant, même si la requête HTTP est faite sur une image située dans le répertoire de vignettes, le servlet va plutôt remplacer le nom du répertoire de vignette par le nom du répertoire de fichiers originaux, qui lui ne devrait pas être connu du côté client. Il va alors intégrer le watermark à l’image originale s’il s’agit d’un aperçu d’image et ensuite écrit directement dans la réponse le fichier de l’image. Le résultat est que l’utilisateur reçoit une image sans en avoir la source sur le serveur.  

Votre compagnie a déjà réalisé le contexte d’utilisation. Les pages JSP sont créées de même que les classes du domaine d’affaire. Les modifications au script de watermarking ont été faites afin de faire le mapping vers un dossier caché et pour ne pas ajouter le watermark si l’utilisateur a payé pour l’image (dans ce cas, si le champ de carte de crédit n’est pas vide). Il vous reste donc à intégrer le tout.

**Instructions**

Pour ce faire, vous aurez à créer le Servlet de contrôle du flot de navigationainsi que le fichier web.xml qui appellera les bons servlets et qui déclarera les variables d’initialisation nécessaires. Voici la liste des fichiers qui vous dont donnés et leur rôle.

* *Dossier* vignettes
  * Dossier contenant des vignettes des images 
* *Dossier* dossierUltraTopSecret
  * Dossier contenant les images originales en haute qualité
* WatermarkServlet.java
  * Servlet faisant l’affichage d’une image originale et y ajoutant un tatouage numérique
* Image.java
  * JavaBean qui contient un titre ainsi que le nom du fichier de l’image
* Collection.java
  * Objet contenant et initialisant les JavaBeans Image
* Index.jsp
  * Page permettant d’entrer dans le catalogue
* Collection.jsp
  * Page contenant une liste déroulante de toutes les images disponibles
* Details.jsp
  * Fragment de page intégré par AJAX qui affiche la vignette de l’image ainsi que le lien pour afficher l’aperçu de l’image ou pour l’acheter
* Erreur.jsp
  * Page à afficher lorsqu’une erreur de type fichier non trouvé (404) est appelée

Votre travail est donc de créer les deux fichiers suivants

* Web.xml
  * Descripteur de l’application web qui fait le mapping sur les servlets et qui déclare les variables d’initialisation. 
* AjaxServlet.java
  * Servlet qui fait l’affichage de la liste des images, qui peut recevoir une requête AJAX pour l’affichage du fragment des détails. Doit faire partie du paquet Source avec toutes les autres classes.
  
**Étapes initiales de configuration**
*Vous devez avoir un compte gratuit sur heroku.com
*Assurez-vous que [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html) est installé
*Assurez-vous que [Maven 3](http://maven.apache.org/download.cgi) est installé
*Reliez votre repository Github avec votre compte Heroku en appelant l'URL suivant dans votre navigateur (après avoir modifié l'URL pour y mettre le nom de votre repository de devoir) https://heroku.com/deploy?template=https://github.com/gti525/repo-personnel-NOMGITHUB-SESSION.git. Pour la case de génération de nom d'app, choisissez un nom d'application qui vous rappellera qu'il s'agit de votre devoir 1, et écrivez ce nom dans le haut de ce fichier readme. Attendez la fin du déploiement et cliquez sur le bouton pour voir votre application. L'URL devrait être appelé et vous devriez voir une page HTML avec un lien "Afficher la liste des images".
*Dans Heroku, cliquez sur Personal Apps pour aller à vos applications. Cliquez sur l'application nouvellement créee.
*Allez dans l'onglet Deploy
*Dans la section Deployment Method, choisissez Github. Autorisez Github à se connecter à votre compte Heroku.
*Une fois connecté, choisissez dans la liste déroulante des repositories pour l'organisation GTI525, et dans le champ repo-name à droite, tapez-y le nom de votre repository de devoir et faites Search. Appuyez sur Connect pour relier votre repository Github et votre compte Heroku.
*Activez les déploiements automatiques en faisant Enable Automatic Deploys dans la section suivante.

Vous êtes maintenant prêt! Il est possible de tester en local en lançant l'exécution de la méthode Main, dans la classe Main située dans le paquet launch du répertoire src/main/java. Java va alors créer un serveur embarqué Tomcat et y déployer automatiquement l'application. C'est la même chose qui sera faite sur Heroku.

**Remise**

Les deux fichiers doivent être commités dans votre GIT personnel avant le mardi 7 juin à 18h00. 

Veuillez vous assurer d’indiquer votre nom et l'URL de votre application dans le haut de ce fichier dans l'espace réservé.

Afin de marquer votre remise, vous devez créer un tag afin de déterminer votre remise. Pour ce faire, si vous utilisez Tortoise Git, vous pouvez faire l’opération Create Tag lors du Commit. Lors du push, veuillez vous assurer que l’option Push Tags a été sélectionnée. Vous pouvez aussi créer un tag en cliquant sur le bouton « Draft a new release » dans l’onglet « release » de votre repository privé pour le devoir.

**Correction**

Si l’application fonctionne comme prévu et que le code respecte les bonnes pratiques, la note de 5 sera donnée. Par exemple, aucun chemin codé en dur devrait être présent dans le code et il ne devrait y avoir aucune erreur d'affichée à l'écran ou dans les logs.

Si certaines parties du code ne fonctionnent pas, le code sera évalué afin de vérifier la méthode utilisée. Des commentaires seront fournis pour expliquer la cause des erreurs.

Les détails de la correction de votre devoir vous seront retransmises par GIT dans le haut de ce fichier.