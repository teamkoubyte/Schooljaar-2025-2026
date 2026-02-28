package com.example.abstracteklassenenabstractemethodes;

import javafx.scene.paint.Color;

public abstract class Figuur {
    protected Color kleur;

    public Figuur(Color kleur) {
        this.kleur = kleur;
    }

    public Color getKleur() {
        return kleur;
    }

    public abstract double berekenOmtrek();
    public abstract double berkenOppervlakte();

    @Override
    public String toString() {
        return "Kleur: " + kleur;
    }
}
