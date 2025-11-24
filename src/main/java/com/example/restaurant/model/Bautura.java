package com.example.restaurant.model;

public final class Bautura extends Produs {
    private int volum;

    public Bautura(String nume, double pret, int volum) {
        super(nume, pret);
        this.volum = volum;
    }

    @Override
    public String toString() {
        return String.format("> %s - %.1f RON - Volum: %dml",
                getNume(), getPret(), volum);
    }
}
