package fr.efrei.transfret;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static final double CENTIMES = 100.0;

    public static SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");

    public static double arrondir(double valeur) {
        return Math.round(valeur * CENTIMES) / CENTIMES;
    }

    public static String formaterDate(Date d) {
        if (d == null) {
            return "";
        }
        return FORMAT_DATE.format(d);
    }

    public static String formaterPrix(double prix) {
        return String.format("%.2f €", prix);
    }

    public static boolean emailValide(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}
