package com.example.examen1zandpoortbus;

public class Chauffeur {
    private String code;
    private String naam;
    private SoortArbeidsovereenkomst soortArbeidsovereenkomst;

    public Chauffeur(String code, String naam, SoortArbeidsovereenkomst soortArbeidsovereenkomst) {
        this.code = code;
        this.naam = naam;
        this.soortArbeidsovereenkomst = soortArbeidsovereenkomst;
    }

    public String getCode() {
        return code;
    }

    public String getNaam() {
        return naam;
    }

    public SoortArbeidsovereenkomst getSoortArbeidsovereenkomst() {
        return soortArbeidsovereenkomst;
    }

    @Override
    public String toString() {
        String tekst = "";
        tekst += "**********Chauffeur " + code + ": " + naam + "**********\n";
        tekst += "Soort arbeidsovereenkomst: " + soortArbeidsovereenkomst;
        return tekst;
    }
}