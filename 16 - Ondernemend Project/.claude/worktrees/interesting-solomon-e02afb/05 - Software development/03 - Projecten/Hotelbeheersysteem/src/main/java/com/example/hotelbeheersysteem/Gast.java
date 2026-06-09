package com.example.hotelbeheersysteem;

public class Gast {
    private String voornaam;
    private String familienaam;
    private String code;
    private String email;
    private String telefoon;

    public Gast(String code, String voornaam, String familienaam, String email, String telefoon) {
        this.code = code;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.telefoon = telefoon;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public String getCode() {
        return code;
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

    @Override
    public String toString() {
        String tekst = "";
        tekst += "**********Gast " + code + ": " + voornaam + " " + familienaam + "**********\n";
        tekst += "Email: " + email + "\n";
        tekst += "Telefoon: " + telefoon + "\n";
        return tekst;
    }
}