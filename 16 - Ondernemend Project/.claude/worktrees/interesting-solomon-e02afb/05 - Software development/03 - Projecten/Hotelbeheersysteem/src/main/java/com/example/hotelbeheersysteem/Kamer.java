package com.example.hotelbeheersysteem;

import java.math.BigDecimal;

public class Kamer {
    private String nummer;
    private String type;
    private String beschrijving;
    private BigDecimal prijsPerNacht;
    private int capaciteit;
    private boolean beschikbaar;

    public Kamer(String nummer, String type, String beschrijving, BigDecimal prijsPerNacht, int capaciteit, boolean beschikbaar) {
        this.nummer = nummer;
        this.type = type;
        this.beschrijving = beschrijving;
        this.prijsPerNacht = prijsPerNacht;
        this.capaciteit = capaciteit;
        this.beschikbaar = beschikbaar;
    }

    public String getNummer() {
        return nummer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public BigDecimal getPrijsPerNacht() {
        return prijsPerNacht;
    }

    public void setPrijsPerNacht(BigDecimal prijsPerNacht) {
        this.prijsPerNacht = prijsPerNacht;
    }

    public int getCapaciteit() {
        return capaciteit;
    }

    public void setCapaciteit(int capaciteit) {
        this.capaciteit = capaciteit;
    }

    public boolean isBeschikbaar() {
        return beschikbaar;
    }

    public void setBeschikbaar(boolean beschikbaar) {
        this.beschikbaar = beschikbaar;
    }

    @Override
    public String toString() {
        String tekst = "";
        tekst += "**********Kamer " + nummer + "**********\n";
        tekst += "Type: " + type + "\n";
        tekst += "Beschrijving: " + beschrijving + "\n";
        tekst += "Prijs per nacht: " + prijsPerNacht + "\n";
        tekst += "Capaciteit: " + capaciteit + "\n";
        tekst += "Beschikbaar: " + beschikbaar + "\n";
        return tekst;
    }
}