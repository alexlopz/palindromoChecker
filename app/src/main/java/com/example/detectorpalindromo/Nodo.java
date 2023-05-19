package com.example.detectorpalindromo;

public class Nodo {
    private char data;
    private Nodo siguiente;

    public Nodo(char d) {
        data = d;
        siguiente = null;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
