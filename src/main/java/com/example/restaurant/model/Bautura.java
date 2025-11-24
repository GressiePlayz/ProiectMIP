package com.example.restaurant.model;

public final class Bautura extends Produs {
    private int volum;

    // Constructor implicit: categoria BAUTURA_RACORITOARE
    public Bautura(String nume, double pret, int volum) {
        super(nume, pret, Categoria.BAUTURA_RACORITOARE);
        this.volum = volum;
    }

    // Constructor extins: permite alegerea categoriei (ex. BAUTURA_ALCOOLICA)
    public Bautura(String nume, double pret, int volum, Categoria categorie) {
        super(nume, pret, categorie);
        this.volum = volum;
    }

    @Override
    public String toString() {
        return String.format("> %s - %.2f RON - Volum: %dml",
                getNume(), getPret(), volum);
    }
}
