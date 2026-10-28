package fr.efrei.transfret;

public interface CanalEmail {

    boolean envoyer(String destinataire, String sujet, String corps);
}
