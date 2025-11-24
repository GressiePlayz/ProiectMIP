package com.example.restaurant.model;

public final class Mancare extends Produs {
    private int gramaj;

    // Constructor implicit: categoria FEL_PRINCIPAL
    public Mancare(String nume, double pret, int gramaj) {
        super(nume, pret, Categoria.FEL_PRINCIPAL);
        this.gramaj = gramaj;
    }

    // Constructor extins: permite alegerea categoriei (ex. DESERT, APERITIV)
    public Mancare(String nume, double pret, int gramaj, Categoria categorie) {
        super(nume, pret, categorie);
        this.gramaj = gramaj;
    }

    @Override
    public String toString() {
        return String.format("> %s - %.2f RON - Gramaj: %dg",
                getNume(), getPret(), gramaj);
    }
}
