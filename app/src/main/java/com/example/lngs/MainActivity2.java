package com.example.lngs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    TextView con, compte, inscri;

    EditText user, pass;
    Button conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        con = findViewById(R.id.textView);
        compte = findViewById(R.id.compte);
        inscri = findViewById(R.id.inscrire);
        user = findViewById(R.id.utilisateurConn);
        pass = findViewById(R.id.mot_de_passConn);
        conn = findViewById(R.id.se_connecter);

        // Récupérer la taille du texte enregistrée depuis les SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int savedTextSize = sharedPreferences.getInt("textSize", 16); // Taille par défaut

        // Appliquer la taille du texte aux éléments de texte
        updateTextSize(savedTextSize);

        // Récupérer la langue sélectionnée depuis les préférences partagées
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedLanguage = shared.getString("language", "");

        // Vérifier si la langue sélectionnée est déjà utilisée
        if (!isCurrentLanguage(selectedLanguage)) {
            // Mettre à jour la langue en fonction de la sélection
            setLocale(selectedLanguage);
        } else {
            // La langue sélectionnée est déjà utilisée, pas besoin de redémarrer l'activité
            // Vous pouvez ajouter ici d'autres actions à effectuer dans ce cas
        }
    }

    private boolean isCurrentLanguage(String lang) {
        Locale currentLocale = getResources().getConfiguration().locale;
        String currentLanguage = currentLocale.getLanguage();

        // Comparer la langue actuelle avec la langue sélectionnée
        return currentLanguage.equals(lang);
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        // Redémarrez l'activité pour appliquer les changements de langue
        recreate();
    }

    private void updateTextSize(int fontSize) {
        con.setTextSize(fontSize);
        compte.setTextSize(fontSize);
        inscri.setTextSize(fontSize);
        user.setTextSize(fontSize);
        pass.setTextSize(fontSize);
        conn.setTextSize(fontSize);
    }
}
