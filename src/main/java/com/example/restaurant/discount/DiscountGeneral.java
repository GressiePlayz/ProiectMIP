package com.example.restaurant.discount;

import com.example.restaurant.model.Produs;

import java.util.Map;

public class DiscountGeneral implements RegulaDiscount {
    private double procent;

    public DiscountGeneral(double procent) {
        this.procent = procent;
    }

    @Override
    public double aplica(double subtotal, Map<Produs, Integer> produse) {
        return subtotal * (1 - procent);
    }
}
