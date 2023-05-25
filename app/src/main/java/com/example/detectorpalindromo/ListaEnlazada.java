package com.example.detectorpalindromo;

public class ListaEnlazada {

    Nodo cabeza;
    Nodo punteroLento, punteroRapido, segundaMitad;

    public boolean esPalindromo(final Nodo nodo) {
        punteroLento = nodo;
        punteroRapido = nodo;
        Nodo punteroLentoAnterior = nodo;
        Nodo nodoMedio = null; // manejar la lista de tamaños impares
        boolean resultado = true;

        if (nodo != null && nodo.getSiguiente() != null) {
		/* Obtener el centro de la lista. Mover punteroLento por 1
                y punteroRapido por 2, punteroLento tendrá el medio nodo (algoritmo de la tortuga y liebre) */
            while (punteroRapido != null && punteroRapido.getSiguiente() != null) {
                punteroRapido = punteroRapido.getSiguiente().getSiguiente();

		/*Necesitamos punteroLentoAnterior para
                   listas enlazadas con elementos impares */
                punteroLentoAnterior = punteroLento;
                punteroLento = punteroLento.getSiguiente();
            }

		/* punteroRapido se convertiría en NULL cuando hay elementos pares
                en la lista y no NULL para elementos impares. tenemos que saltar
                el nodo medio para el caso impar y almacenarlo en algún lugar para que podemos restaurar la lista original */
            if (punteroRapido != null) {
                nodoMedio = punteroLento;
                punteroLento = punteroLento.getSiguiente();
            }

            // Ahora invierta la segunda mitad y compárela con la primera mitad.
            segundaMitad = punteroLento;
            punteroLentoAnterior.setSiguiente(null);// NULL termina la primera mitad
            reverse(); // Invertir la segunda mitad
            resultado = compararListas(nodo, segundaMitad); // compare

            /* Vuelva a construir la lista original */
            reverse(); // Invertir la segunda mitad de nuevo

            if (nodoMedio != null) {
                // Si hubiera un nodo medio (caso de tamaño impar) que no formó parte ni de la primera mitad ni de la segunda mitad.
                punteroLentoAnterior.setSiguiente(nodoMedio);
                nodoMedio.setSiguiente(segundaMitad);
            } else {
                punteroLentoAnterior.setSiguiente(segundaMitad);
            }
        }
        return resultado;
    }

    private void reverse() {
        Nodo anterior = null;
        Nodo actual = segundaMitad;
        Nodo siguiente;
        while (actual != null) {
            siguiente = actual.getSiguiente();
            actual.setSiguiente(anterior);
            anterior = actual;
            actual = siguiente;
        }
        segundaMitad = anterior;
    }

    /*Función para verificar si dos listas de entrada tienen los mismos datos*/
    private boolean compararListas(Nodo cabeza1, Nodo cabeza2) {
        Nodo temporal1 = cabeza1;
        Nodo temporal2 = cabeza2;

        while (temporal1 != null && temporal2 != null) {
            if (temporal1.getData() == temporal2.getData()) {
                temporal1 = temporal1.getSiguiente();
                temporal2 = temporal2.getSiguiente();
            } else {
                return false;
            }
        }

        /* Ambos están vacíos volver 1*/
        return temporal1 == null && temporal2 == null;

        /* Llegará aquí cuando uno sea NULL
            y otro no */
    }

    // Inserta un nodo a la lista enlazada
    public void insertar(char dato) {
        final Nodo nodoNuevo = new Nodo(dato);
        nodoNuevo.setSiguiente(cabeza);
        cabeza = nodoNuevo;
    }
}
