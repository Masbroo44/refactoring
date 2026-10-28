package fr.efrei.transfret;

public class Notificateur {

    private final CanalEmail email;
    private final CanalSms sms;

    public Notificateur(CanalEmail email, CanalSms sms) {
        this.email = email;
        this.sms = sms;
    }

    public void envoyerEmail(Client client, String sujet, String corps) {
        email.envoyer(client.getEmail(), sujet, corps);
    }

    // email pour tout le monde, SMS en plus pour les clients premium
    public void notifier(Client client, String sujet, String corps, String texteSms) {
        envoyerEmail(client, sujet, corps);
        if (client.getType() == TypeClient.PREMIUM) {
            sms.envoyerSms(client.getTelephone(), texteSms);
        }
    }
}
