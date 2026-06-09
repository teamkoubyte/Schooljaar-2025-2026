package com.example.hotelbeheersysteem;

import java.util.ArrayList;

public class Hotel {
    private String code;
    private String naam;
    private String beschrijving;
    private String adres;
    private String email;
    private String telefoon;
    private int sterren;
    private ArrayList<Kamer> kamers;
    private ArrayList<Boeking> boekingen;
    private ArrayList<Gast> gasten;

    public Hotel(String code, String naam, String beschrijving, String adres, String email, String telefoon, int sterren) {
        this.code = code;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.adres = adres;
        this.email = email;
        this.telefoon = telefoon;
        this.sterren = sterren;
        this.kamers = new ArrayList<>();
        this.boekingen = new ArrayList<>();
        this.gasten = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public int getSterren() {
        return sterren;
    }

    public void setSterren(int sterren) {
        this.sterren = sterren;
    }

    public ArrayList<Kamer> getKamers() {
        return kamers;
    }

    public void setKamers(ArrayList<Kamer> kamers) {
        this.kamers = kamers;
    }

    public ArrayList<Boeking> getBoekingen() {
        return boekingen;
    }

    public void setBoekingen(ArrayList<Boeking> boekingen) {
        this.boekingen = boekingen;
    }

    public ArrayList<Gast> getGasten() {
        return gasten;
    }

    public void setGasten(ArrayList<Gast> gasten) {
        this.gasten = gasten;
    }

    @Override
    public String toString() {
        String tekst = "";
        tekst += "**********Hotel " + code + ": " + naam + "**********\n";
        tekst += "Beschrijving: " + beschrijving + "\n";
        tekst += "Adres: " + adres + "\n";
        tekst += "Email: " + email + "\n";
        tekst += "Telefoon: " + telefoon + "\n";
        tekst += "Sterren: " + sterren + "\n\n";
        for (int teller = 0; teller < kamers.size(); teller++) {
            Kamer kamer = kamers.get(teller);
            tekst += kamer.toString();
            tekst += "\n";
        }
        for (int teller = 0; teller < boekingen.size(); teller++) {
            Boeking boeking = boekingen.get(teller);
            tekst += boeking.toString();
            tekst += "\n";
        }
        for (int teller = 0; teller < gasten.size(); teller++) {
            Gast gast = gasten.get(teller);
            tekst += gast.toString();
            tekst += "\n";
        }
        return tekst;
    }
}