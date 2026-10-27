# Etape 1 : Diagnostic

## Classes

| Classe | Rôle | Dépend de |
|---|---|---|
| Main | lance une démo | ServiceClient, ServiceExpedition, Flotte |
| ServiceClient | valide et crée un client | Database, EmailService |
| ServiceExpedition | crée, démarre, livre et annule une expédition | Database, CalculateurPrix, AffectationVehicule, EmailService, SmsService, Utils |
| CalculateurPrix | calcule le prix HT | Colis |
| AffectationVehicule | choisit le véhicule | Flotte, Camion, CamionFrigorifique, Train, Bateau, Avion |
| Flotte | liste des véhicules | Camion, CamionFrigorifique, Train, Bateau, Avion |
| IVehicule, Vehicule | interface et classe mère des véhicules | Colis |
| Camion, CamionFrigorifique, Train, Bateau, Avion | les véhicules | Vehicule |
| Client, Colis | données | rien |
| Expedition | données d'une expédition + facture | Client, Colis, Vehicule, Utils |
| Database | stockage en mémoire | Client, Expedition |
| EmailService, SmsService | envoi des notifications | rien |
| Utils | dates, arrondi, distances | rien |

## Défauts

| Défaut | Emplacement | Principe violé |
|---|---|---|
| Large Class : validation, prix, délai et notifications dans la même classe | ServiceExpedition | S |
| Long Method : environ 70 lignes | ServiceExpedition.creerExpedition() | S |
| La facture est faite dans l'entité | Expedition.genererFacture() | S |
| Switch Statements : if/else sur le mode | CalculateurPrix.calculerPrixHT(), ServiceExpedition.calculerDelai(), ServiceExpedition.demarrerExpedition() | O |
| Switch Statements : if/else sur le type de client | CalculateurPrix.calculerPrixHT() | O |
| instanceof pour choisir le véhicule | AffectationVehicule.trouverVehicule() | O |
| Le camion frigo refuse les colis non réfrigérés | CamionFrigorifique.charger() | L |
| Refused Bequest : faireLePlein() lance une exception | Train | L |
| Refused Bequest : rouler(), naviguer(), voler() lancent UnsupportedOperationException | IVehicule, Camion, Train, Bateau, Avion | I |
| new CalculateurPrix(), new AffectationVehicule(), new SmsService() | ServiceExpedition | D |
| Singleton Database.getInstance() et champs static | Database, Flotte, EmailService, SmsService | D |
| Primitive Obsession : mode, statut et type de client en String, type de colis en int | Client, Colis, Expedition, CalculateurPrix, ServiceExpedition | - |
| Nombres magiques (1.2, 167, 1440...) | CalculateurPrix, ServiceExpedition | - |
| Duplicate Code : test dangereux / réfrigéré copié 3 fois | CalculateurPrix, AffectationVehicule, ServiceExpedition | - |
| Dead Code : getPoidsTotal(), getVolumeTotal(), Utils.arrondir()... | Expedition, Utils | - |
| Long Parameter List : 7 paramètres | ServiceExpedition.creerExpedition(), ServiceExpedition.calculerDevis() | - |

## Ajouter FLUVIAL

Shotgun Surgery : un seul ajout oblige à modifier plusieurs classes.

- CalculateurPrix.calculerPrixHT() : le tarif
- ServiceExpedition.creerExpedition() : pas d'urgence
- ServiceExpedition.calculerDelai() : le délai
- ServiceExpedition.demarrerExpedition() : le départ
- AffectationVehicule.trouverVehicule() : choisir la péniche
- Flotte.initialiser() : ajouter la péniche
- Utils.getDistance() : Paris - Le Havre
- nouvelle classe Peniche

Total : 5 fichiers modifiés + 1 nouveau.

## Ajouter hors gabarit

- Colis : savoir si une dimension dépasse 200 cm
- CalculateurPrix.calculerPrixHT() : x1,10 avant la remise

Total : 2 fichiers modifiés.
