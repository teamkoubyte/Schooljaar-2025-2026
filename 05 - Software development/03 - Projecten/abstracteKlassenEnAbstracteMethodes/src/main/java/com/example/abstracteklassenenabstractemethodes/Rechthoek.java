package com.example.abstracteklassenenabstractemethodes;

import javafx.scene.paint.Color;

public class Rechthoek extends Figuur {
    private double lengte;
    private double breedte;

    public Rechthoek(Color kleur) {
        setKleur(kleur);
    }

    public Rechthoek(Color kleur, double lengte, double breedte) {
        setKleur(kleur);
        this.lengte = lengte;
        this.breedte = breedte;
    }

    public double getLengte() {
        return lengte;
    }

    public void setLengte(double lengte) {
        this.lengte = lengte;
    }

    public double getBreedte() {
        return breedte;
    }

    public void setBreedte(double breedte) {
        this.breedte = breedte;
    }

    @Override
    public double berekenOmtrek() {
        return 2 * (lengte + breedte);
    }

    @Override
    public double berekenOppervlakte() {
        return lengte * breedte;
    }

    @Override
    public String toString() {
        return "Rechthoek[lengte=" + lengte + ", breedte=" + breedte + ", kleur=" + getKleur() + "]";
    }
}
