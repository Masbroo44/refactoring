package fr.efrei.transfret;

public class TarifRoute implements TarifTransport {

    private static final double PRIX_KM_ROUTE = 1.2;
    private static final double PRIX_KG_ROUTE = 0.10;

    @Override
    public double calculer(int distance, double poids, double volume) {
        return distance * PRIX_KM_ROUTE + poids * PRIX_KG_ROUTE;
    }
}
