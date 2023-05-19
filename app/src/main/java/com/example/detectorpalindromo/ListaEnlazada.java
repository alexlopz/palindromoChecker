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
            /* Get the middle of the list. Move slow_ptr by 1
               and fast_ptr by 2, slow_ptr will have the middle
               node */
            while (fast_ptr != null && fast_ptr.getSiguiente() != null) {
                fast_ptr = fast_ptr.getSiguiente().getSiguiente();

                /*We need previous of the slow_ptr for
                  linked lists  with odd elements */
                prev_of_slow_ptr = slow_ptr;
                slow_ptr = slow_ptr.getSiguiente();
            }

            /* fast_ptr would become NULL when there are even elements
               in the list and not NULL for odd elements. We need to skip
               the middle node for odd case and store it somewhere so that
               we can restore the original list */
            if (fast_ptr != null) {
                midnode = slow_ptr;
                slow_ptr = slow_ptr.getSiguiente();
            }

            // Now reverse the second half and compare it with first half
            second_half = slow_ptr;
            prev_of_slow_ptr.setSiguiente(null);// NULL terminate first half
            reverse(); // Reverse the second half
            res = compareLists(nodo, second_half); // compare

            /* Construct the original list back */
            reverse(); // Reverse the second half again

            if (midnode != null) {
                // If there was a mid node (odd size case) which
                // was not part of either first half or second half.
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

    /* Function to check if two input lists have same data*/
    private boolean compareLists(Nodo head1, Nodo head2) {
        Nodo temp1 = head1;
        Nodo temp2 = head2;

        while (temp1 != null && temp2 != null) {
            if (temp1.getData() == temp2.getData()) {
                temp1 = temp1.getSiguiente();
                temp2 = temp2.getSiguiente();
            } else return false;
        }

        /* Both are empty return 1*/
        return temp1 == null && temp2 == null;

        /* Will reach here when one is NULL
           and other is not */
    }

    /* Push a node to linked list. Note that this function
    changes the head */
    public void push(char new_data) {
        /* Allocate the Nodo &
           Put in the data */
        Nodo new_node = new Nodo(new_data);

        /* link the old list off the new one */
        new_node.setSiguiente(cabeza);

        /* Move the head to point to new Nodo */
        cabeza = new_node;
    }

    // A utility function to print a given linked list
    public void printList(Nodo ptr) {
        while (ptr != null) {
            System.out.print(ptr.getData() + "->");
            ptr = ptr.getSiguiente();
        }
        System.out.println("NULL");
    }
}
