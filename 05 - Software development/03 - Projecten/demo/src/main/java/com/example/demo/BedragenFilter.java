package com.example.demo;

import java.util.ArrayList;

public class BedragenFilter {

    public static ArrayList<Double> bedragenBinnenGrenzen(ArrayList<Double> bedragenLijst,
                                                           double ondergrens,
                                                           double bovengrens) {
        ArrayList<Double> resultaat = new ArrayList<>();

        for (int teller = 0; teller < bedragenLijst.size(); teller++) {
            double bedrag = bedragenLijst.get(teller);
            if (bedrag >= ondergrens && bedrag <= bovengrens) {
                resultaat.add(bedrag);
            }
        }

        return resultaat;
    }
}

