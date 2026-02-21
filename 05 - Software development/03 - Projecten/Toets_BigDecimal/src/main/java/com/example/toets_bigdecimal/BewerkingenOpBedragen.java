package com.example.toets_bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class BewerkingenOpBedragen {
    public static BigDecimal converteerInEur(BigDecimal bedrag) {
        return (bedrag.setScale(2, RoundingMode.HALF_UP));
    }

    public static BigDecimal converteerInEur(double getal) {
        return (converteerInEur(BigDecimal.valueOf(getal)));
    }

    public static BigDecimal converteerInEur(String getalAlsString) {
        return (converteerInEur(new BigDecimal(getalAlsString)));
    }

    public static String converteerEurBedragInString(BigDecimal EurBedrag) {
        return (EurBedrag.toString());
    }

    public static BigDecimal geefNulEurBedrag() {
        return (converteerInEur(BigDecimal.ZERO));
    }

    public static ArrayList<BigDecimal> berekenTeBetalenBedragen(ArrayList<BigDecimal> oorspronkelijkePrijzenLijst) {
        ArrayList<BigDecimal> teBetalenBedragenLijst = new ArrayList<BigDecimal>();
        BigDecimal prijs;
        BigDecimal teBetalen;
        BigDecimal korting;
        BigDecimal grens1 = converteerInEur(25);
        BigDecimal grens2 = converteerInEur(50);
        BigDecimal deelDoor2 = converteerInEur(2);
        BigDecimal deelDoor4 = converteerInEur(4);
        BigDecimal deelDoor20 = converteerInEur(20);

        for (int teller = 0; teller < oorspronkelijkePrijzenLijst.size(); teller++) {
            prijs = oorspronkelijkePrijzenLijst.get(teller);
            if (prijs != null) {
                if (prijs.compareTo(grens2) >= 0) {
                    korting = prijs.divide(deelDoor2, 2, RoundingMode.HALF_UP);
                    teBetalen = prijs.subtract(korting);
                } else if (prijs.compareTo(grens1) >= 0) {
                    korting = prijs.divide(deelDoor4, 2, RoundingMode.HALF_UP);
                    teBetalen = prijs.subtract(korting);
                } else {
                    korting = prijs.divide(deelDoor20, 2, RoundingMode.HALF_UP);
                    teBetalen = prijs.subtract(korting);
                }
                teBetalenBedragenLijst.add(converteerInEur(teBetalen));
            }
        }
        return (teBetalenBedragenLijst);
    }

    public static ArrayList<BigDecimal> berekenToegepasteKortingen(ArrayList<BigDecimal> oorspronkelijkePrijzenLijst) {
        ArrayList<BigDecimal> toegepasteKortingenLijst = new ArrayList<BigDecimal>();
        BigDecimal prijs;
        BigDecimal korting;
        BigDecimal grens1 = converteerInEur(25);
        BigDecimal grens2 = converteerInEur(50);
        BigDecimal deelDoor2 = converteerInEur(2);
        BigDecimal deelDoor4 = converteerInEur(4);
        BigDecimal deelDoor20 = converteerInEur(20);

        for (int teller = 0; teller < oorspronkelijkePrijzenLijst.size(); teller++) {
            prijs = oorspronkelijkePrijzenLijst.get(teller);
            if (prijs != null) {
                if (prijs.compareTo(grens2) >= 0) {
                    korting = prijs.divide(deelDoor2, 2, RoundingMode.HALF_UP);
                } else if (prijs.compareTo(grens1) >= 0) {
                    korting = prijs.divide(deelDoor4, 2, RoundingMode.HALF_UP);
                } else {
                    korting = prijs.divide(deelDoor20, 2, RoundingMode.HALF_UP);
                }
                toegepasteKortingenLijst.add(converteerInEur(korting));
            }
        }
        return (toegepasteKortingenLijst);
    }

    public static BigDecimal berekenSomVanToegepasteKortingen(ArrayList<BigDecimal> oorspronkelijkePrijzenLijst) {
        ArrayList<BigDecimal> toegepasteKortingenLijst = berekenToegepasteKortingen(oorspronkelijkePrijzenLijst);
        BigDecimal som = geefNulEurBedrag();

        for (int teller = 0; teller < toegepasteKortingenLijst.size(); teller++) {
            som = som.add(toegepasteKortingenLijst.get(teller));
        }

        return (som);
    }
}
