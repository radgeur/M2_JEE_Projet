mvn eclipse:eclipse pour générer les dépéndances du pom.xml dans eclipse

Liste des urls :

index.html -> Page d'accueil du réseau
login.html -> Page pour se connecter
inscription.html -> Page pour s'inscrire : Formulaire
logged.html -> Page appelé quand l'utilisateur envoi un message
profils/user.html -> Affichage du profil d'un user et de ses messages
hashtags/nomHashTag.html  -> Affichage des messages en rapport avec ce hashTags


Liste des pages :

Consulter l'accueil du réseau social : index.html

Ecrire un message + liste des messages : loggedIn.html

S'identifier à l'application : login.html

S'inscrire à l'applicaion : inscription.html

Renseigner sa fiche profil pour détailler, Ses informations de contact (email, téléphone...), sa photo, Son id Tweeter,Son id Facebook, Son id LinkedIn : edit_profile.html

Rechercher des utilsateurs, et lire leur fiche profil : Au clic sur un utilisateur on affiche son profil, Afficher les tweets d'un utilisateur : profils/user.html

Clic sur un hashtag : on affiche les messages du hashtags : hashtags/nomHashTag.html

Pouvoir gérer l'ensemble des données via une API de web-service REST

BDD :

- User : id, pseudo=login, password, email, photo, idtweeter, idfb, idLinkedin
- Message : id, idUser, texteMessage
- Hashtag : id, texteHashTags
- Fusion : id, idMessage, idHashtag

Gestion de l'API de web services REST (accessible à l'url /data) :
	- /restData/users : Données utilisateurs
	- /restData/messages : Données messages
 	- /restData/hashtags : Données hashtags
	- /restData/messagesHashtags : Données messages avec leurs hashtags
	- /restData/user?login=login : Informations d'un utilisateur (login passé en paramètre)
	- /restData/messagesUser?login=login : Messages d'un utilisateur (login passé en paramètre)
	- /restData/hashtag?hashtag=Nom : Liste des messages contenant le hashtag (nom passé en paramètre)
