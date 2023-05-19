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

        final LinearLayout contenedorSuccess = findViewById(R.id.contenedorSuccess);
        final LinearLayout contenedorError = findViewById(R.id.contenedorError);
        final EditText editTextPalabra = findViewById(R.id.editTextPalabra);
        final Button buttonSubmit = findViewById(R.id.buttonSubmit);
        final ProgressBar progressCircular = findViewById(R.id.progressCircular);
        final ImageView radarIcon = findViewById(R.id.radarIcon);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit(radarIcon, progressCircular, contenedorError, contenedorSuccess, editTextPalabra);
            }
        });

        editTextPalabra.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    // Aquí colocas el código que deseas ejecutar cuando se presiona Enter o se envía el formulario
                    hideKeyboard(editTextPalabra);
                    submit(radarIcon, progressCircular, contenedorError, contenedorSuccess, editTextPalabra);
                    return true;
                }
                return false;
            }
        });

    }

    private void hideKeyboard(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void submit(ImageView radarIcon,
                        ProgressBar progressCircular,
                        LinearLayout contenedorError,
                        LinearLayout contenedorSuccess,
                        EditText editTextPalabra) {
        radarIcon.setVisibility(View.GONE);
        progressCircular.setVisibility(View.VISIBLE);
        contenedorError.setVisibility(View.GONE);
        contenedorSuccess.setVisibility(View.GONE);
        String palabraFrase = editTextPalabra.getText().toString().toLowerCase(Locale.ROOT);
        if (!palabraFrase.isEmpty()) {
            palabraFrase = palabraFrase.replaceAll("\\s", "");
            char[] array = palabraFrase.toCharArray();
            ListaEnlazada lista = new ListaEnlazada();

            for (char c : array) {
                lista.push(c);
                lista.printList(lista.cabeza);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressCircular.setVisibility(View.GONE);
                    if (lista.esPalindromo(lista.cabeza)) {
                        contenedorError.setVisibility(View.GONE);
                        contenedorSuccess.setVisibility(View.VISIBLE);
                    } else {
                        contenedorSuccess.setVisibility(View.GONE);
                        contenedorError.setVisibility(View.VISIBLE);
                    }
                }
            }, 2000);
        }
    }
}