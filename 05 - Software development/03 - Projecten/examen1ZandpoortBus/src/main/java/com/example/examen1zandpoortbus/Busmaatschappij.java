package com.example.examen1zandpoortbus;

import java.time.Month;
import java.util.ArrayList;

public class Busmaatschappij {
    private String code;
    private String naam;
    private String website;
    private SoortArbeidsovereenkomst soortArbeidsovereenkomst;
    private ArrayList<Rit> rittenLijst;

    public Busmaatschappij(String code, String naam, String website) {
        this.code = code;
        this.naam = naam;
        this.website = website;
        this.rittenLijst = new ArrayList<>();
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public SoortArbeidsovereenkomst getSoortArbeidsovereenkomst() {
        return soortArbeidsovereenkomst;
    }

    public boolean voegRitToe(Rit rit) {
        if (rit == null) {
            return false;
        }
        return rittenLijst.add(rit);
    }

    public boolean schrapRit(String code) {
        if (code == null) {
            return false;
        }
        for (int teller = 0; teller < rittenLijst.size(); teller++) {
            Rit rit = rittenLijst.get(teller);
            if (rit != null && code.equals(rit.getCode())) {
                rittenLijst.remove(teller);
                return true;
            }
        }
        return false;
    }

    public Rit vraagRitOp(String code) {
        if (code == null) {
            return null;
        }
        for (int teller = 0; teller < rittenLijst.size(); teller++) {
            Rit rit = rittenLijst.get(teller);
            if (rit != null && code.equals(rit.getCode())) {
                return rit;
            }
        }
        return null;
    }

    public int geefAantalRitten() {
        return rittenLijst.size();
    }

    public int geefAantalRittenInMaandopRittijdstip(Month maand, Rittijdstip rittijdstip) {
        if (maand == null || rittijdstip == null) {
            return 0;
        }
        int aantal = 0;
        for (int teller = 0; teller < rittenLijst.size(); teller++) {
            Rit rit = rittenLijst.get(teller);
            if (rit != null && rit.getMaand() == maand && rit.getRittijdstip() == rittijdstip) {
                aantal++;
            }
        }
        return aantal;
    }

    public int geefTotaalAantalReizigers() {
        int totaal = 0;
        for (int teller = 0; teller < rittenLijst.size(); teller++) {
            Rit rit = rittenLijst.get(teller);
            if (rit != null) {
                totaal += rit.getAantalReizigers();
            }
        }
        return totaal;
    }

    public int geefGemiddeldAantalreizigers() {
        if (rittenLijst.isEmpty()) {
            return 0;
        }
        return geefTotaalAantalReizigers() / rittenLijst.size();
    }

    public int geefTotaalAantalChauffeurs() {
        int totaal = 0;
        for (int teller = 0; teller < rittenLijst.size(); teller++) {
            Rit rit = rittenLijst.get(teller);
            if (rit != null && rit.getChauffeursLijst() != null) {
                totaal += rit.getChauffeursLijst().size();
            }
        }
        return totaal;
    }

    public int geefGemiddeldAantalChauffeurs() {
        if (rittenLijst.isEmpty()) {
            return 0;
        }
        return geefTotaalAantalChauffeurs() / rittenLijst.size();
    }

    public int geefTotaalAantalInterimChauffeurs() {
        int totaal = 0;
        for (int teller = 0; teller < rittenLijst.size(); teller++) {
            Rit rit = rittenLijst.get(teller);
            for (int chauffeurTeller = 0; chauffeurTeller < rit.getChauffeursLijst().size(); chauffeurTeller++) {
                Chauffeur chauffeur = rit.getChauffeursLijst().get(chauffeurTeller);
                if (chauffeur != null && chauffeur.getSoortArbeidsovereenkomst() == SoortArbeidsovereenkomst.INTERIM) {
                    totaal++;
                }
            }
        }
        return totaal;
    }

    public int geefGemideldAantalInterimChauffeurs() {
        if (rittenLijst.isEmpty()) {
            return 0;
        }
        return geefTotaalAantalInterimChauffeurs() / rittenLijst.size();
    }

    @Override
    public String toString() {
        String tekst = "";
        tekst += "**********Busmaatschappij " + code + ": " + naam + "**********\n";
        tekst += "Website: " + website + "\n\n";
        for (int teller = 0; teller < rittenLijst.size(); teller++) {
            Rit rit = rittenLijst.get(teller);
            tekst += "**********Rit " + rit.getCode() + "**********\n";
            tekst += "Stad van vertrek: " + rit.getStadVanVertrek()
                    + ", stad van aankomst: " + rit.getStadVanAankomst() + "\n";
            tekst += "Aantal reizigers: " + rit.getAantalReizigers() + "\n";
            tekst += "Maand: " + (rit.getMaand()) + "\n";
            tekst += "Rittijdstip: " + (rit.getRittijdstip()) + "\n";
            tekst += "\n";
            ArrayList<Chauffeur> chauffeurs = rit.getChauffeursLijst();

            for (int chauffeurTeller = 0; chauffeurTeller < chauffeurs.size(); chauffeurTeller++) {
                Chauffeur chauffeur = chauffeurs.get(chauffeurTeller);
                tekst += "**********Chauffeur " + chauffeur.getCode() + ": " + chauffeur.getNaam() + "**********\n";
                tekst += "Soort arbeidsovereenkomst: " + chauffeur.getSoortArbeidsovereenkomst() + "\n";
                tekst += "\n";
            }
            tekst += "\n";
        }
        return tekst;
    }

}
