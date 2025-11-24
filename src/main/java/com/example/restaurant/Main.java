package com.example.restaurant;

import com.example.restaurant.discount.DiscountGeneral;
import com.example.restaurant.discount.HappyHourDiscount;
import com.example.restaurant.model.*;
import com.example.restaurant.service.Comanda;
import com.example.restaurant.service.Config;
import com.example.restaurant.service.Meniu;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // === ITERAȚIA 4: Citire configurare externă ===
        Config config;
        try {
            config = new Gson().fromJson(new FileReader("config.json"), Config.class);
        } catch (IOException e) {
            System.out.println("Eroare: Fișierul de configurare lipsește. Contactați suportul tehnic.");
            return;
        } catch (JsonSyntaxException e) {
            System.out.println("Eroare: Fișierul de configurare este corupt. Contactați suportul tehnic.");
            return;
        }

        System.out.println("=== " + config.getNumeRestaurant() + " ===");

        // === ITERAȚIA 1: Meniul de bază ===
        Produs pizza = new Mancare("Pizza Margherita", 45.0, 450);
        Produs paste = new Mancare("Paste Carbonara", 52.5, 400);
        Produs limonada = new Bautura("Limonada", 15.0, 400);
        Produs apa = new Bautura("Apa Plata", 8.0, 500);

        System.out.println("\n--- Meniul de bază ---");
        System.out.println("> " + pizza);
        System.out.println("> " + paste);
        System.out.println("> " + limonada);
        System.out.println("> " + apa);

        // === ITERAȚIA 2: Comanda clientului + discounturi ===
        Comanda comanda = new Comanda(config.getTva());
        comanda.adaugaProdus(pizza, 2);
        comanda.adaugaProdus(paste, 1);
        comanda.adaugaProdus(limonada, 1);
        comanda.adaugaProdus(apa, 1);

        System.out.println("\n--- Comanda Clientului ---");
        comanda.afiseazaComanda();

        double totalNormal = comanda.calculeazaTotal(null);
        System.out.printf("Total (fără discount): %.2f RON%n", totalNormal);

        double totalHappyHour = comanda.calculeazaTotal(new HappyHourDiscount());
        System.out.printf("Total (Happy Hour): %.2f RON%n", totalHappyHour);

        double totalValentine = comanda.calculeazaTotal(new DiscountGeneral(0.10));
        System.out.printf("Total (Valentine's Day -10%%): %.2f RON%n", totalValentine);

        // === ITERAȚIA 3: Meniu pe categorii + interogări + pizza custom ===
        Meniu meniu = new Meniu();
        meniu.adaugaProdus(pizza);
        meniu.adaugaProdus(paste);
        meniu.adaugaProdus(limonada);
        meniu.adaugaProdus(apa);
        meniu.adaugaProdus(new Mancare("Salata vegetariană", 32.0, 250, Categoria.APERITIV));
        meniu.adaugaProdus(new Mancare("Tiramisu", 28.0, 120, Categoria.DESERT));
        meniu.adaugaProdus(new Mancare("Steak", 120.0, 300, Categoria.FEL_PRINCIPAL));
        meniu.adaugaProdus(new Bautura("Vin roșu", 35.0, 150, Categoria.BAUTURA_ALCOOLICA));

        Pizza pizzaCustom = new Pizza.Builder("Pizza Custom", 55.0, "Subțire", "Roșu")
                .cuToppinguri(List.of("Mozzarella", "Ciuperci", "Ananas"))
                .build();
        meniu.adaugaProdus(pizzaCustom);

        System.out.println("\n--- Deserturi ---");
        meniu.getProduseDinCategorie(Categoria.DESERT).forEach(System.out::println);

        System.out.println("\n--- Vegetarian ---");
        meniu.getVegetarieneSortate().forEach(System.out::println);

        System.out.println("\n--- Preț mediu desert ---");
        meniu.getPretMediu(Categoria.DESERT)
                .ifPresentOrElse(
                        avg -> System.out.printf("%.2f RON%n", avg),
                        () -> System.out.println("Nicio desert în meniu.")
                );

        System.out.println("\n--- Există produs > 100 RON? ---");
        System.out.println(meniu.existaProdusScump(100));

        System.out.println("\n--- Căutare produs: 'Vin roșu' ---");
        meniu.cautaProdus("Vin roșu")
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Produsul nu a fost găsit.")
                );

        System.out.println("\n--- Pizza Customizată ---");
        System.out.println(pizzaCustom);

        // === ITERAȚIA 4: Export meniu ===
        meniu.exportaMeniu("meniu_export.json");
    }
}
