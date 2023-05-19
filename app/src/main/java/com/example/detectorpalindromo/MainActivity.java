package com.example.detectorpalindromo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextPalabra = findViewById(R.id.editTextPalabra);
        final Button buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String palabraFrase = editTextPalabra.getText().toString().toLowerCase(Locale.ROOT);
                if (!palabraFrase.isEmpty()) {
                    palabraFrase = palabraFrase.replaceAll("\\s", "");
                    char[] array = palabraFrase.toCharArray();
                    ListaEnlazada lista = new ListaEnlazada();

                    for (char c : array) {
                        lista.push(c);
                        lista.printList(lista.cabeza);
                    }
                    if (lista.esPalindromo(lista.cabeza)) {
                        Toast.makeText(MainActivity.this, "El texto dado es Palindromo", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "El texto dado no es Palindromo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}