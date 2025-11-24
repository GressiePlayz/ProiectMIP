package com.example.restaurant.model;

import java.util.List;

public final class Pizza extends Produs {
    private String blat;
    private String sos;
    private List<String> toppinguri;

    private Pizza(Builder builder) {
        super(builder.nume, builder.pret, Categoria.PIZZA);
        this.blat = builder.blat;
        this.sos = builder.sos;
        this.toppinguri = builder.toppinguri;
    }

    public static class Builder {
        private String nume;
        private double pret;
        private String blat;
        private String sos;
        private List<String> toppinguri = List.of();

        public Builder(String nume, double pret, String blat, String sos) {
            this.nume = nume;
            this.pret = pret;
            this.blat = blat;
            this.sos = sos;
        }

        public Builder cuToppinguri(List<String> toppinguri) {
            this.toppinguri = toppinguri;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    @Override
    public String toString() {
        return String.format("> %s - %.2f RON - Blat: %s, Sos: %s, Toppinguri: %s",
                getNume(), getPret(), blat, sos, String.join(", ", toppinguri));
    }
}
