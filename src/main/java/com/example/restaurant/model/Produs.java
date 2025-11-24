package com.example.restaurant.model;

public sealed abstract class Produs permits Mancare, Bautura, Pizza {
    private String nume;
    private double pret;
    private Categoria categorie;

    public Produs(String nume, double pret, Categoria categorie) {
        this.nume = nume;
        this.pret = pret;
        this.categorie = categorie;
    }

    public String getNume() { return nume; }
    public double getPret() { return pret; }
    public Categoria getCategorie() { return categorie; }

    @Override
    public abstract String toString();
}
