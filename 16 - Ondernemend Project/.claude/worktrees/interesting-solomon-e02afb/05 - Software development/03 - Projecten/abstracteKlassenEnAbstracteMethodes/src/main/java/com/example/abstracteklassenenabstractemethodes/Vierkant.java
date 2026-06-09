package com.example.abstracteklassenenabstractemethodes;

import javafx.scene.paint.Color;

public class Vierkant extends Figuur {
    private double zijde;

    public Vierkant(Color kleur) {
        setKleur(kleur);
    }

    public Vierkant(Color kleur, double zijde) {
        setKleur(kleur);
        this.zijde = zijde;
    }

    public double getZijde() {
        return zijde;
    }

    public void setZijde(double zijde) {
        this.zijde = zijde;
    }

    @Override
    public double berekenOmtrek() {
        return 4 * zijde;
    }

    @Override
    public double berekenOppervlakte() {
        return zijde * zijde;
    }

    @Override
    public String toString() {
        return "Vierkant[zijde=" + zijde + ", kleur=" + getKleur() + "]";
    }
}
