package com.example.restaurant;

public class Mancare extends Produs {
    private int gramaj;

    public Mancare(String nume, double pret, int gramaj) {
        super(nume, pret);
        this.gramaj = gramaj;
    }

    @Override
    public String toString() {
        return String.format("> %s - %.1f RON - Gramaj: %dg",
                getNume(), getPret(), gramaj);
    }
}
