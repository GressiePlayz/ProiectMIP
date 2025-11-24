package com.example.restaurant.discount;

import com.example.restaurant.model.Produs;

import java.util.Map;

public interface RegulaDiscount {
    double aplica(double subtotal, Map<Produs, Integer> produse);
}
