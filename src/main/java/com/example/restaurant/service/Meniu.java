package com.example.restaurant.service;

import com.example.restaurant.model.*;

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

    public List<Produs> getVegetarieneSortate() {
        return produsePeCategorii.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.getNume().toLowerCase().contains("vegetarian"))
                .sorted(Comparator.comparing(Produs::getNume))
                .collect(Collectors.toList());
    }

    public OptionalDouble getPretMediu(Categoria categorie) {
        return getProduseDinCategorie(categorie).stream()
                .mapToDouble(Produs::getPret)
                .average();
    }

    public boolean existaProdusScump(double prag) {
        return produsePeCategorii.values().stream()
                .flatMap(List::stream)
                .anyMatch(p -> p.getPret() > prag);
    }

    public Optional<Produs> cautaProdus(String nume) {
        return produsePeCategorii.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.getNume().equalsIgnoreCase(nume))
                .findFirst();
    }
}
