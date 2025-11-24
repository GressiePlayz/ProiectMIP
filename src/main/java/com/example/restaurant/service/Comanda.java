package com.example.restaurant.service;

import com.example.restaurant.model.Produs;
import com.example.restaurant.discount.RegulaDiscount;

import java.util.HashMap;
import java.util.Map;

public class Comanda {
    private Map<Produs, Integer> produse = new HashMap<>();
    private double tva;

    public Comanda(double tva) {
        this.tva = tva;
    }

    public void adaugaProdus(Produs produs, int cantitate) {
        produse.put(produs, produse.getOrDefault(produs, 0) + cantitate);
    }

    public void afiseazaComanda() {
        produse.forEach((produs, cantitate) ->
                System.out.println(cantitate + " x " + produs));
    }

    public double calculeazaTotal(RegulaDiscount regula) {
        double subtotal = produse.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPret() * e.getValue())
                .sum();
        double totalCuDiscount = regula != null ? regula.aplica(subtotal, produse) : subtotal;
        return totalCuDiscount * (1 + tva);
    }
}
