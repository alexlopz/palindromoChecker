package com.example.detectorpalindromo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declaracion de elementos graficos
        final LinearLayout contenedorSuccess = findViewById(R.id.contenedorSuccess);
        final LinearLayout contenedorError = findViewById(R.id.contenedorError);
        final EditText editTextPalabra = findViewById(R.id.editTextPalabra);
        final Button buttonSubmit = findViewById(R.id.buttonSubmit);
        final ProgressBar progressCircular = findViewById(R.id.progressCircular);
        final ImageView radarIcon = findViewById(R.id.radarIcon);

        // Accion de boton en pantalla
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                procesar(radarIcon, progressCircular, contenedorError, contenedorSuccess, editTextPalabra);
            }
        });

        // Accion del boton en el teclado
        editTextPalabra.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    ocultarTeclado(editTextPalabra);
                    procesar(radarIcon, progressCircular, contenedorError, contenedorSuccess, editTextPalabra);
                    return true;
                }
                return false;
            }
        });

    }

    // Funcion que ejecuta el proceso cuando se presiona el boton de pantalla o el teclado
    private void procesar(final ImageView radarIcon,
                          final ProgressBar progressCircular,
                          final LinearLayout contenedorError,
                          final LinearLayout contenedorSuccess,
                          final EditText editTextPalabra) {

        // Se decide si se ocultan o muestran algunos elementos graficos
        radarIcon.setVisibility(View.GONE);
        progressCircular.setVisibility(View.VISIBLE);
        contenedorError.setVisibility(View.GONE);
        contenedorSuccess.setVisibility(View.GONE);

        // Se captura el texto y se convierte a mayuscula
        String textoCapturado = editTextPalabra.getText().toString().toLowerCase(Locale.ROOT);

        if (!textoCapturado.isEmpty()) {// se valida que no este vacio el texto
            textoCapturado = removerEspacios(textoCapturado);

            // se inserta cada letra en la lista enlazada
            final char[] arrayDeLetras = textoCapturado.toCharArray();
            final ListaEnlazada listaEnlazada = new ListaEnlazada();
            for (char letra : arrayDeLetras) {
                listaEnlazada.insertar(letra);
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressCircular.setVisibility(View.GONE);

                    if (listaEnlazada.esPalindromo(listaEnlazada.cabeza)) {// Si es palindromo se muestra icono y mensaje de success
                        contenedorError.setVisibility(View.GONE);
                        contenedorSuccess.setVisibility(View.VISIBLE);
                    } else {//Si no es palindromo se muestra icono de error
                        contenedorSuccess.setVisibility(View.GONE);
                        contenedorError.setVisibility(View.VISIBLE);
                    }
                }
            }, 2000);
        }
    }

    private String removerEspacios(final String texto) {
        return texto.replaceAll(getString(R.string.regexSpace), "");
    }

    private void ocultarTeclado(final EditText editText) {
        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}