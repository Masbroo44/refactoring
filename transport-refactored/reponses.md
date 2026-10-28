# Réponses

## Etape 3 : ServiceClient, une ou deux responsabilités ?

Pour moi, il y avait deux responsabilités : vérifier les informations du client, puis le créer. J'ai donc sorti la vérification dans une classe ValidateurClient, comme pour les expéditions. Maintenant, ServiceClient s'occupe seulement de créer le client et d'envoyer le mail de bienvenue, en passant par le Notificateur.
