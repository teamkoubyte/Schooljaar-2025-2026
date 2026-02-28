package com.example.abstracteklassenenabstractemethodes;

import javafx.scene.paint.Color;

public class GelijkzijdigeDriehoek {
    private double basis;
    private double hoogte;

    public GelijkzijdigeDriehoek(Color kleur) {
        super(kleur);
    }

    public GelijkzijdigeDriehoek(Color kleur, double basis, double hoogte) {
        super(kleur);
        this.basis = basis;
        this.hoogte = hoogte;
    }

    public double getBasis() {
        return basis;
    }

    public void setBasis(double basis) {
        this.basis = basis;
    }

    public double getHoogte() {
        return hoogte;
    }

    public void setHoogte(double hoogte) {
        this.hoogte = hoogte;
    }

    @Override
    public double berekenOmtrek() {
        return 3 * basis;
    }
    
    @Override
    public double berekenOppervlakte() {
        return zijde * zijde;
    }

    @Override
    public String toString() {
        return "GelijkzijdigeDriehoek [basis" + basis + ", hoogte=" + hoogte + ", " + super.toString() + "]";
    }
}
