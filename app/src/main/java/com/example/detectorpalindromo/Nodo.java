package com.example.detectorpalindromo;

public class Nodo {
    private char data;
    private Nodo siguiente;

    public Nodo(char data) {
        this.data = data;
        this.siguiente = null;
    }

    public char getData() {
        return data;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
