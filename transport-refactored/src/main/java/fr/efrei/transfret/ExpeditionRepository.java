package fr.efrei.transfret;

public interface ExpeditionRepository {

    void sauvegarder(Expedition expedition);

    void mettreAJour(Expedition expedition);

    Expedition trouver(String id);
}
