package com.example.abstracteklassenenabstractemethodes;

import javafx.scene.paint.Color;

public abstract class Figuur {
    private Color kleur;

    public Figuur() {
    }

    public Figuur(Color kleur) {
        this.kleur = kleur;
    }

    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }

    public abstract double berekenOmtrek();

    public abstract double berekenOppervlakte();

    @Override
    public String toString() {
        return "kleur=" + kleur;
    }
}
