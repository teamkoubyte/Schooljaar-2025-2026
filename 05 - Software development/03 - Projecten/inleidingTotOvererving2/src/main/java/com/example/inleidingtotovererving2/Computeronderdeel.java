package com.example.inleidingtotovererving2;

import java.math.BigDecimal;

public class Computeronderdeel {
    protected Integer serienummer;
    protected String merk;
    protected BigDecimal prijs;

    public Computeronderdeel() {
        serienummer = null;
        merk = "";
        prijs = null;
    }

    public Computeronderdeel(String merk) {
        serienummer = null;
        this.merk = merk;
        prijs = null;
    }

    public String toString() {
        String tekst;

        tekst = "Serienummer: " + serienummer + "\n";
        tekst += "Merk: " + merk + "\n";
        tekst += "Prijs: " + prijs;
        return (tekst);
    }
}
