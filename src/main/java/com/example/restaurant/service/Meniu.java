package com.example.restaurant.service;

import com.example.restaurant.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Meniu {
    private Map<Categoria, List<Produs>> produsePeCategorii = new HashMap<>();

    public void adaugaProdus(Produs produs) {
        produsePeCategorii
                .computeIfAbsent(produs.getCategorie(), k -> new ArrayList<>())
                .add(produs);
    }

    public List<Produs> getProduseDinCategorie(Categoria categorie) {
        return produsePeCategorii.getOrDefault(categorie, Collections.emptyList());
    }

    // Returnează produsele considerate vegetariene (heuristică simplă pe nume), sortate după preț crescător
    public List<Produs> getVegetarieneSortate() {
        Set<String> vegetarKeywords = Set.of("vegetar", "salat", "vegan", "tofu");
        return produsePeCategorii.values().stream()
                .flatMap(List::stream)
                .filter(p -> {
                    String lower = p.getNume().toLowerCase();
                    return vegetarKeywords.stream().anyMatch(lower::contains);
                })
                .sorted(Comparator.comparingDouble(Produs::getPret))
                .collect(Collectors.toList());
    }

    // Returnează prețul mediu pentru o categorie, dacă există produse în acea categorie
    public OptionalDouble getPretMediu(Categoria categorie) {
        return getProduseDinCategorie(categorie).stream()
                .mapToDouble(Produs::getPret)
                .average();
    }

    // Verifică existența unui produs mai scump decât pragul specificat
    public boolean existaProdusScump(double prag) {
        return produsePeCategorii.values().stream()
                .flatMap(List::stream)
                .anyMatch(p -> p.getPret() > prag);
    }

    // Caută un produs după nume (comparare case-insensitive)
    public Optional<Produs> cautaProdus(String nume) {
        return produsePeCategorii.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.getNume().equalsIgnoreCase(nume))
                .findFirst();
    }

    public void exportaMeniu(String filename) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()   // activează formatul frumos
                .create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(produsePeCategorii, writer);
            System.out.println("Meniul a fost exportat cu succes în " + filename);
        } catch (IOException e) {
            System.out.println("Eroare la exportul meniului: " + e.getMessage());
        }
    }

}
