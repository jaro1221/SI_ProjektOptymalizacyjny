package com.si;

public class Product {
    private String Id;
    private String Name;
    private double Dlugosc;
    private double Szerokosc;
    private double Wysokosc;
    private double Masa;

    public Product(String id, String name, double dlugosc, double szerokosc, double wysokosc, double masa) {
        Id = id;
        Name = name;
        Dlugosc = dlugosc;
        Szerokosc = szerokosc;
        Wysokosc = wysokosc;
        Masa = masa;
    }
}
