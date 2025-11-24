package com.example.restaurant.discount;

import com.example.restaurant.model.Bautura;
import com.example.restaurant.model.Produs;

import java.util.Map;

public class HappyHourDiscount implements RegulaDiscount {
    @Override
    public double aplica(double subtotal, Map<Produs, Integer> produse) {
        double reducere = 0.0;
        for (Map.Entry<Produs, Integer> entry : produse.entrySet()) {
            if (entry.getKey() instanceof Bautura) {
                reducere += entry.getKey().getPret() * entry.getValue() * 0.20;
            }
        }
        return subtotal - reducere;
    }
}
