package com.example.examen1zandpoortbus;

import java.time.Month;
import java.util.ArrayList;

public class Rit {
    private String code;
    private String stadVanVertrek;
    private String stadVanAankomst;
    private int aantalReizigers;
    private Month maand;
    private Rittijdstip rittijdstip;
    private ArrayList<Chauffeur> chauffeursLijst;

    public Rit(String code, String stadVanVertrek, String stadVanAankomst) {
        this.code = code;
        this.stadVanVertrek = stadVanVertrek;
        this.stadVanAankomst = stadVanAankomst;
        this.chauffeursLijst = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getStadVanVertrek() {
        return stadVanVertrek;
    }

    public void setStadVanVertrek(String stadVanVertrek) {
        this.stadVanVertrek = stadVanVertrek;
    }

    public String getStadVanAankomst() {
        return stadVanAankomst;
    }

    public void setStadVanAankomst(String stadVanAankomst) {
        this.stadVanAankomst = stadVanAankomst;
    }

    public int getAantalReizigers() {
        return aantalReizigers;
    }

    public void setAantalReizigers(int aantalReizigers) {
        this.aantalReizigers = aantalReizigers;
    }

    public Month getMaand() {
        return maand;
    }

    public void setMaand(Month maand) {
        this.maand = maand;
    }

    public Rittijdstip getRittijdstip() {
        return rittijdstip;
    }

    public void setRittijdstip(Rittijdstip rittijdstip) {
        this.rittijdstip = rittijdstip;
    }

    public ArrayList<Chauffeur> getChauffeursLijst() {
        return chauffeursLijst;
    }

    public boolean voegChauffeurToe(Chauffeur chauffeur) {
        if (chauffeur == null || chauffeur.getCode() == null) {
            return false;
        }
        if (vraagChauffeurOp(chauffeur.getCode()) != null) {
            return false;
        }
        return chauffeursLijst.add(chauffeur);
    }

    public boolean schrapChauffeur(String code) {
        if (code == null) {
            return false;
        }
        for (int teller = 0; teller < chauffeursLijst.size(); teller++) {
            Chauffeur chauffeur = chauffeursLijst.get(teller);
            if (chauffeur != null && code.equals(chauffeur.getCode())) {
                chauffeursLijst.remove(teller);
                return true;
            }
        }
        return false;
    }

    public Chauffeur vraagChauffeurOp(String code) {
        if (code == null) {
            return null;
        }
        for (int teller = 0; teller < chauffeursLijst.size(); teller++) {
            Chauffeur chauffeur = chauffeursLijst.get(teller);
            if (chauffeur != null && code.equals(chauffeur.getCode())) {
                return chauffeur;
            }
        }
        return null;
    }

    public int geefAantalChauffeurs() {
        return chauffeursLijst.size();
    }

    public int geefAantalLoontrekkendeChauffeurs() {
        int aantal = 0;
        for (int teller = 0; teller < chauffeursLijst.size(); teller++) {
            Chauffeur chauffeur = chauffeursLijst.get(teller);
            if (chauffeur != null && chauffeur.getSoortArbeidsovereenkomst() == SoortArbeidsovereenkomst.LOONTREKKENDE) {
                aantal++;
            }
        }
        return aantal;
    }

    public int geefAantalInterimChauffeurs() {
        int aantal = 0;
        for (int teller = 0; teller < chauffeursLijst.size(); teller++) {
            Chauffeur chauffeur = chauffeursLijst.get(teller);
            if (chauffeur != null && chauffeur.getSoortArbeidsovereenkomst() == SoortArbeidsovereenkomst.INTERIM) {
                aantal++;
            }
        }
        return aantal;
    }

    @Override
    public String toString() {
        String tekst = "";
        tekst += "**********Rit " + code + "**********\n";
        tekst += "Stad van vertrek: " + stadVanVertrek + ", stad van aankomst: " + stadVanAankomst + "\n";
        tekst += "Aantal reizigers: " + aantalReizigers + "\n";
        tekst += "Maand: " + maand + "\n";
        tekst += "Rittijdstip: " + rittijdstip + "\n";
        tekst += "**********Chauffeurs (" + chauffeursLijst.size() + ")**********\n";
        for (int teller = 0; teller < chauffeursLijst.size(); teller++) {
                Chauffeur chauffeur = chauffeursLijst.get(teller);
                if (chauffeur != null) {
                    tekst += "Chauffeur " + chauffeur.getCode() + ": " + chauffeur.getNaam() + "\n";
                    tekst += "Soort arbeidsovereenkomst: " + chauffeur.getSoortArbeidsovereenkomst() + "\n";
                }
            }
        return tekst;
    }
}

