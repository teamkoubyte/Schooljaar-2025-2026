package com.example.hotelbeheersysteem;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Boeking {
    private String code;
    private LocalDate datumVan;
    private LocalDate datumTot;
    private int aantalPersonen;
    private String status;
    private BigDecimal totaalPrijs;
    private Kamer kamer;
    private Gast gast;

    public Boeking(String code, LocalDate datumVan, LocalDate datumTot, int aantalPersonen, String status, BigDecimal totaalPrijs, Kamer kamer, Gast gast) {
        this.code = code;
        this.datumVan = datumVan;
        this.datumTot = datumTot;
        this.aantalPersonen = aantalPersonen;
        this.status = status;
        this.totaalPrijs = totaalPrijs;
        this.kamer = kamer;
        this.gast = gast;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getDatumVan() {
        return datumVan;
    }

    public void setDatumVan(LocalDate datumVan) {
        this.datumVan = datumVan;
    }

    public LocalDate getDatumTot() {
        return datumTot;
    }

    public void setDatumTot(LocalDate datumTot) {
        this.datumTot = datumTot;
    }

    public int getAantalPersonen() {
        return aantalPersonen;
    }

    public void setAantalPersonen(int aantalPersonen) {
        this.aantalPersonen = aantalPersonen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotaalPrijs() {
        return totaalPrijs;
    }

    public void setTotaalPrijs(BigDecimal totaalPrijs) {
        this.totaalPrijs = totaalPrijs;
    }

    public Kamer getKamer() {
        return kamer;
    }

    public void setKamer(Kamer kamer) {
        this.kamer = kamer;
    }

    public Gast getGast() {
        return gast;
    }

    public void setGast(Gast gast) {
        this.gast = gast;
    }

    @Override
    public String toString() {
        String tekst = "";
        tekst += "**********Boeking " + code + "**********\n";
        tekst += "Datum van: " + datumVan + ", datum tot: " + datumTot + "\n";
        tekst += "Aantal personen: " + aantalPersonen + "\n";
        tekst += "Status: " + status + "\n";
        tekst += "Totaal prijs: " + totaalPrijs + "\n";
        tekst += "Kamer: " + kamer.getNummer() + "\n";
        tekst += "Gast: " + gast.getVoornaam() + " " + gast.getFamilienaam() + "\n";
        return tekst;
    }
}