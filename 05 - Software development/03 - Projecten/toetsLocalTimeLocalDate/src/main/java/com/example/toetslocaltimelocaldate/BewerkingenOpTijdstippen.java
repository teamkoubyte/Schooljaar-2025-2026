package com.example.toetslocaltimelocaldate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class BewerkingenOpTijdstippen {
    public static String formatteerTijdstip(LocalTime tijdstip, FormatStyle formaat) {
        return (tijdstip.format(DateTimeFormatter.ofLocalizedTime(formaat)));
    }

    public static String formatteerTijdstip(int aantalUren, int aantalMinuten, FormatStyle formaat) {
        return (formatteerTijdstip(LocalTime.of(aantalUren, aantalMinuten), formaat));
    }

    public static String formatteerTijdstip(int aantalUren, int aantalMinuten, int aantalSeconden, FormatStyle formaat) {
        return (formatteerTijdstip(LocalTime.of(aantalUren, aantalMinuten, aantalSeconden), formaat));

    }

    public static int berekenVertragingInMinuten(LocalTime geplandeAankomsttijd, LocalTime verwachteAankomsttijd) {
        int geplandeAankomstInMinuten;
        int verwachteAankomstInMinuten;
        int vertragingInMinuten;

        geplandeAankomstInMinuten = (geplandeAankomsttijd.getHour() * 60) + geplandeAankomsttijd.getMinute();
        verwachteAankomstInMinuten = (verwachteAankomsttijd.getHour() * 60) + verwachteAankomsttijd.getMinute();
        vertragingInMinuten = verwachteAankomstInMinuten - geplandeAankomstInMinuten;

        if (vertragingInMinuten < 0) {
            return (0);
        } else {
            return (vertragingInMinuten);
        }
    }

    public static ArrayList<Integer> berekenVertragingenPerHalte(LocalTime[][] aankomsttijdenMatrix) {
        ArrayList<Integer> vertragingenPerHalte;
        int vertragingInMinuten;
        int teller;

        vertragingenPerHalte = new ArrayList<Integer>();
        for (teller = 0; teller < aankomsttijdenMatrix.length; teller++) {
            vertragingInMinuten = berekenVertragingInMinuten(aankomsttijdenMatrix[teller][0], aankomsttijdenMatrix[teller][1]);
            vertragingenPerHalte.add(vertragingInMinuten);
        }
        return (vertragingenPerHalte);
    }

    public static int berekenSomVanVertragingen(LocalTime[][] aankomsttijdenMatrix) {
        int somVanVertragingen = 0;
        int vertragingInMinuten;
        int teller;

        for (teller = 0; teller < aankomsttijdenMatrix.length; teller++) {
            vertragingInMinuten = berekenVertragingInMinuten(aankomsttijdenMatrix[teller][0], aankomsttijdenMatrix[teller][1]);
            somVanVertragingen += vertragingInMinuten;
        }
        return (somVanVertragingen);
    }

    public static int berekenTotaleRitduur(LocalTime[][] aankomsttijdenMatrix) {
        LocalTime geplandeAankomstEersteHalte;
        LocalTime verwachteAankomstLaatsteHalte;
        int geplandeAankomstEersteHalteInMinuten;
        int verwachteAankomstLaatsteHalteInMinuten;
        int totaleRitduurInMinuten;

        geplandeAankomstEersteHalte = aankomsttijdenMatrix[0][0];
        verwachteAankomstLaatsteHalte = aankomsttijdenMatrix[aankomsttijdenMatrix.length - 1][1];

        geplandeAankomstEersteHalteInMinuten = (geplandeAankomstEersteHalte.getHour() * 60) + geplandeAankomstEersteHalte.getMinute();
        verwachteAankomstLaatsteHalteInMinuten = (verwachteAankomstLaatsteHalte.getHour() * 60) + verwachteAankomstLaatsteHalte.getMinute();
        totaleRitduurInMinuten = verwachteAankomstLaatsteHalteInMinuten - geplandeAankomstEersteHalteInMinuten;

        if (totaleRitduurInMinuten < 0) {
            return (totaleRitduurInMinuten + (24 * 60));
        } else {
            return (totaleRitduurInMinuten);
        }
    }
}
