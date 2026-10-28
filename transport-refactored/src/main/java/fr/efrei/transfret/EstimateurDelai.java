package fr.efrei.transfret;

public class EstimateurDelai {

    private static final double VITESSE_ROUTE_KMH = 70.0;
    private static final int HEURES_ROUTE_PAR_JOUR = 9;
    private static final int HEURES_ROUTE_PAR_JOUR_URGENT = 18;
    private static final double KM_PAR_JOUR_RAIL = 1440.0;
    private static final int JOURS_EN_PLUS_RAIL = 1;
    private static final double KM_PAR_JOUR_MER = 720.0;
    private static final int JOURS_EN_PLUS_MER = 2;
    private static final int JOURS_AIR = 1;
    private static final int JOURS_AIR_LONGUE_DISTANCE = 2;
    private static final int SEUIL_LONGUE_DISTANCE_AIR = 3000;
    private static final int DELAI_MINIMUM = 1;

    // retourne le délai de livraison en jours
    public int calculerDelai(int distance, ModeTransport mode, boolean urgent) {
        int jours = 0;
        switch (mode) {
            case ROUTE:
                double heures = distance / VITESSE_ROUTE_KMH;
                if (urgent) {
                    jours = (int) Math.ceil(heures / HEURES_ROUTE_PAR_JOUR_URGENT);
                } else {
                    jours = (int) Math.ceil(heures / HEURES_ROUTE_PAR_JOUR);
                }
                break;
            case RAIL:
                jours = (int) Math.ceil(distance / KM_PAR_JOUR_RAIL) + JOURS_EN_PLUS_RAIL;
                if (urgent) jours = jours - 1;
                break;
            case MER:
                jours = (int) Math.ceil(distance / KM_PAR_JOUR_MER) + JOURS_EN_PLUS_MER;
                break;
            case AIR:
                jours = JOURS_AIR;
                if (distance > SEUIL_LONGUE_DISTANCE_AIR) jours = JOURS_AIR_LONGUE_DISTANCE;
                if (urgent) jours = JOURS_AIR;
                break;
        }
        if (jours < DELAI_MINIMUM) jours = DELAI_MINIMUM;
        return jours;
    }
}
