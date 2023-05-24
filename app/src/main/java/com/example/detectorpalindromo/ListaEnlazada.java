package com.example.detectorpalindromo;

public class ListaEnlazada {

    Nodo cabeza;
    Nodo slow_ptr, fast_ptr, second_half;

    public boolean esPalindromo(final Nodo nodo) {
        slow_ptr = nodo;
        fast_ptr = nodo;
        Nodo prev_of_slow_ptr = nodo;
        Nodo midnode = null; // manejar la lista de tamaños impares
        boolean res = true; // initialize result

        if (nodo != null && nodo.getSiguiente() != null) {
            while (fast_ptr != null && fast_ptr.getSiguiente() != null) {
                fast_ptr = fast_ptr.getSiguiente().getSiguiente();
                prev_of_slow_ptr = slow_ptr;
                slow_ptr = slow_ptr.getSiguiente();
            }
            if (fast_ptr != null) {
                midnode = slow_ptr;
                slow_ptr = slow_ptr.getSiguiente();
            }

            second_half = slow_ptr;
            prev_of_slow_ptr.setSiguiente(null);
            reverse();
            res = compareLists(nodo, second_half);

            reverse();

            if (midnode != null) {
                prev_of_slow_ptr.setSiguiente(midnode);
                midnode.setSiguiente(second_half);
            } else prev_of_slow_ptr.setSiguiente(second_half);
        }
        return res;
    }

    private void reverse() {
        Nodo prev = null;
        Nodo current = second_half;
        Nodo next;
        while (current != null) {
            next = current.getSiguiente();
            current.setSiguiente(prev);
            prev = current;
            current = next;
        }
        second_half = prev;
    }

    private boolean compareLists(Nodo head1, Nodo head2) {
        Nodo temp1 = head1;
        Nodo temp2 = head2;

        while (temp1 != null && temp2 != null) {
            if (temp1.getData() == temp2.getData()) {
                temp1 = temp1.getSiguiente();
                temp2 = temp2.getSiguiente();
            } else return false;
        }

        return temp1 == null && temp2 == null;
    }

    public void push(char new_data) {
        Nodo new_node = new Nodo(new_data);
        new_node.setSiguiente(cabeza);
        cabeza = new_node;
    }

//    public void printList(Nodo ptr) {
//        while (ptr != null) {
//            System.out.print(ptr.getData() + "->");
//            ptr = ptr.getSiguiente();
//        }
//        System.out.println("NULL");
//    }
}
