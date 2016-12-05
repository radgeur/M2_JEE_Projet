index.html -> Page d'accueil (Accueil du réseau social + Possibilité de se connecter + S'inscrire)
login.html -> Page pour se connecter
inscription.html -> Page pour s'inscrire : Formulaire
loggedIn.html -> Page affiché lorsque l'utilisateur est connecté -> Liste des messages + champ pour ajouter un message
		  -> Bouton pour éditer le profil utilisateur
profils/user.html -> Affichage du profil d'un user et de ses messages
hashtags/nomHashTag.html  -> Affichage des messages en rapport avec ce hashTags
edit_profile.html -> Editer son profil (il faut être bien entendu logger)


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
- Fusion : id, idMessage, idHashta
