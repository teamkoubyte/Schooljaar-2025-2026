package com.example.project_met_klasse_toetsenbord;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Toetsenbord {
    //**********Attributen**********
    private String serienummer;
    private String merk;
    private boolean draadloos;
    private BigDecimal prijs;

    //**********Constructor**********
    public Toetsenbord(String merk) {
        this.merk = merk;
    }

    public Toetsenbord() {
        this.merk = "Logitech";
        this.draadloos = true;
    }

    //**********Getters en setters**********
    public String getSerienummer() {
        return serienummer;
    }

    public void setSerienummer(String serienummer) {
        this.serienummer = serienummer;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public boolean isDraadloos() {
        return draadloos;
    }

    public void setDraadloos(boolean draadloos) {
        this.draadloos = draadloos;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    //**********Andere methodes**********
    public BigDecimal berekenPrijs(int aantal) {
        return (prijs.multiply(BigDecimal.valueOf(aantal)));
    }

    public BigDecimal berekenPrijs(boolean draadloos) {
        BigDecimal prijs;

        if (draadloos) {
            prijs = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_UP);
        } else {
            prijs = BigDecimal.valueOf(50).setScale(2, RoundingMode.HALF_UP);
        }
        return (prijs);
    }

    //**********toString**********

    @Override
    public String toString() {
        String tekst ="";

        tekst += "**********Toetsenbord: " + merk + "**********\n";
        tekst += "Serienummer: " + serienummer + "\n";
        tekst += "Draadloos: " + draadloos + "\n";
        tekst += "Prijs: " + prijs + "\n";
        return tekst;
    }
}
