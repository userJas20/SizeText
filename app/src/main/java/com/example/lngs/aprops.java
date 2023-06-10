package com.example.lngs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.util.Locale;

public class aprops extends AppCompatActivity {

    TextView intro, t1, t2, t3, t4, t5, t6, t7, t8, t9, mrc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprops);

        intro = findViewById(R.id.intro);
        t1 = findViewById(R.id.text1);
        t2 = findViewById(R.id.text2);
        t3 = findViewById(R.id.text3);
        t4 = findViewById(R.id.text4);
        t5 = findViewById(R.id.text5);
        t6 = findViewById(R.id.text6);
        t7 = findViewById(R.id.text7);
        t8 = findViewById(R.id.text8);
        t9 = findViewById(R.id.text9);
        mrc = findViewById(R.id.merci);

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
        intro.setTextSize(fontSize);
        t1.setTextSize(fontSize);
        t2.setTextSize(fontSize);
        t3.setTextSize(fontSize);
        t4.setTextSize(fontSize);
        t5.setTextSize(fontSize);
        t6.setTextSize(fontSize);
        t7.setTextSize(fontSize);
        t8.setTextSize(fontSize);
        t9.setTextSize(fontSize);
        mrc.setTextSize(fontSize);

    }
    }