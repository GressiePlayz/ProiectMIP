package com.example.restaurant.service;

import com.example.restaurant.discount.RegulaDiscount;
import com.example.restaurant.model.Produs;

import java.util.HashMap;
import java.util.Map;

public class Comanda {
    private Map<Produs, Integer> produse = new HashMap<>();

    // TVA fix (9%)
    public static final double TVA = 0.09;

    public void adaugaProdus(Produs produs, int cantitate) {
        produse.put(produs, produse.getOrDefault(produs, 0) + cantitate);
    }

    public double calculeazaTotal(RegulaDiscount regula) {
        double subtotal = 0.0;
        for (Map.Entry<Produs, Integer> entry : produse.entrySet()) {
            subtotal += entry.getKey().getPret() * entry.getValue();
        }

        // aplicăm regula de discount (dacă există)
        double totalCuDiscount = regula != null ? regula.aplica(subtotal, produse) : subtotal;

        // aplicăm TVA
        return totalCuDiscount * (1 + TVA);
    }

    public void afiseazaComanda() {
        System.out.println("--- Comanda ---");
        for (Map.Entry<Produs, Integer> entry : produse.entrySet()) {
            System.out.println(entry.getValue() + " x " + entry.getKey());
        }
    }
}
