package com.example.restaurant;

import com.example.restaurant.discount.DiscountGeneral;
import com.example.restaurant.discount.HappyHourDiscount;
import com.example.restaurant.model.Bautura;
import com.example.restaurant.model.Mancare;
import com.example.restaurant.model.Produs;
import com.example.restaurant.service.Comanda;

public class Main {
    public static void main(String[] args) {
        Comanda comanda = new Comanda();

        Produs pizza = new Mancare("Pizza Margherita", 45.0, 450);
        Produs paste = new Mancare("Paste Carbonara", 52.5, 400);
        Produs limonada = new Bautura("Limonada", 15.0, 400);
        Produs apa = new Bautura("Apa Plata", 8.0, 500);

        comanda.adaugaProdus(pizza, 2);
        comanda.adaugaProdus(paste, 1);
        comanda.adaugaProdus(limonada, 1);
        comanda.adaugaProdus(apa, 1);

        comanda.afiseazaComanda();

        // fără discount
        double totalNormal = comanda.calculeazaTotal(null);
        System.out.printf("Total (fără discount): %.2f RON%n", totalNormal);

        // cu Happy Hour
        double totalHappyHour = comanda.calculeazaTotal(new HappyHourDiscount());
        System.out.printf("Total (Happy Hour): %.2f RON%n", totalHappyHour);

        // cu discount general 10%
        double totalValentine = comanda.calculeazaTotal(new DiscountGeneral(0.10));
        System.out.printf("Total (Valentine's Day -10%%): %.2f RON%n", totalValentine);
    }
}
