package com.example.lngs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private Spinner languageSpinner, textSizeSpinner;
    TextView textParam, textMdeSombre, textLngues, textTaillle, textDecon, textApropos;
    private ArrayAdapter<String> spinnerAdapter, adapter;
    private String currentLanguage="" ; // Langue par défaut (français)
    private SharedPreferences sharedPreferences;
    private SharedPreferences shared;

    boolean nightMODE;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor edit;
    Button cpnn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cpnn = findViewById(R.id.connecter);
        cpnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });


        //taille du text

        // Référencez les éléments du layout
        textParam = findViewById(R.id.textParametres);
        textMdeSombre = findViewById(R.id.textModeSombre);
        textLngues = findViewById(R.id.textLangues);
        textTaillle = findViewById(R.id.textTailleText);
        textDecon = findViewById(R.id.textDeconnexion);
        textApropos = findViewById(R.id.textApropos);

        textSizeSpinner = findViewById(R.id.textSpinner);

        //Déconnexion
        textDecon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, MainActivity2.class);
               startActivity(intent);
                finish();
            }
        });

        textApropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, aprops.class);
                startActivity(intent);
            }
        });

        // Créez un ArrayAdapter pour les options de taille du texte
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.text_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Appliquez l'adaptateur au Spinner
        textSizeSpinner.setAdapter(adapter);

        // Ajoutez un écouteur de sélection pour détecter les changements
        textSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = parent.getItemAtPosition(position).toString();
                int fontSize = 16; // Taille par défaut

                // Changer la taille en fonction de l'option sélectionnée
                if (selectedSize.equals("Moyen")) {
                    fontSize = 16;
                } else if (selectedSize.equals("Petit")) {
                    fontSize = 14;
                } else if (selectedSize.equals("Grand")) {
                    fontSize = 20;
                }
                //pour l'englais
                if (selectedSize.equals("Average")) {
                    fontSize = 16;
                } else if (selectedSize.equals("Little")) {
                    fontSize = 14;
                } else if (selectedSize.equals("Big")) {
                    fontSize = 20;
                }
                //pour l'arabe
                if (selectedSize.equals("متوسط")) {
                    fontSize = 16;
                } else if (selectedSize.equals("صغير")) {
                    fontSize = 14;
                } else if (selectedSize.equals("كبير")) {
                    fontSize = 20;
                }

                textParam.setTextSize(fontSize);
                textMdeSombre.setTextSize(fontSize);
                textLngues.setTextSize(fontSize);
                textTaillle.setTextSize(fontSize);
                textDecon.setTextSize(fontSize);
                textApropos.setTextSize(fontSize);
                // Enregistrer la taille du texte sélectionnée dans les SharedPreferences
                SharedPreferences.Editor editor = shared.edit();
                editor.putInt("textSize", fontSize);
                editor.apply();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ne rien faire lorsque rien n'est sélectionné
            }
        });

        //night mode
        SwitchCompat switcher = findViewById(R.id.switchMode);
        // we used sharedPreferences to save the mode if exit the app and go back again
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMODE = sharedPreferences.getBoolean("night", false); // light mode is the default mode

        if(nightMODE){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nightMODE){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                    String selectedLanguage = shared.getString("language", "");
                    editor.putString("language", selectedLanguage);
                    editor.apply();

                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                    String selectedLanguage = shared.getString("language", "");
                    editor.putString("language", selectedLanguage);
                    editor.apply();

                }
                editor.apply();
            }
        });




        //langues
        shared = PreferenceManager.getDefaultSharedPreferences(this);
        currentLanguage = shared.getString("language", "");

        if (!currentLanguage.equals("")) {
            setLocale(currentLanguage);
        }

        if (currentLanguage.isEmpty()) {
            // Si la langue n'est pas encore définie, utilisez la langue par défaut de l'appareil
            currentLanguage = Locale.getDefault().getLanguage();
            SharedPreferences.Editor editor = shared.edit();
            editor.putString("language", currentLanguage);
            editor.apply();
        }

        languageSpinner = findViewById(R.id.languageSpinner);
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.addAll("Français", "English", "العربية"); // Ajoutez les noms des langues dans l'ordre correspondant aux valeurs du Spinner

        languageSpinner.setAdapter(spinnerAdapter);
        currentLanguage = shared.getString("language", "");
        languageSpinner.setSelection(getLanguageIndex(currentLanguage)); // Sélectionne la langue par défaut

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = getLanguageCode(position);
                setLocale(selectedLanguage);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ne rien faire
            }
        });
    }

    private int getLanguageIndex(String languageCode) {
        switch (languageCode) {
            case "Français":
                return 0; // Français
            case "English":
                return 1; // Anglais
            case "العربية":
                return 2; // Arabe
            default:
                return 0; // Par défaut, retourne l'index correspondant au français
        }
    }

    private String getLanguageCode(int position) {
        switch (position) {
            case 0:
                return ""; // Français
            case 1:
                return "en"; // Anglais
            case 2:
                return "ar"; // Arabe
            default:
                return ""; // Par défaut, retourne une chaîne vide
        }
    }


    private void setLocale(String lang) {
        String currentLanguage = shared.getString("language", "");

        if (!currentLanguage.equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);

            Resources resources = getResources();
            Configuration configuration = resources.getConfiguration();
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());

            // Enregistrer la langue actuelle dans les préférences partagées
            SharedPreferences.Editor edit = shared.edit();
            edit.putString("language", lang);
            edit.apply();

            currentLanguage = lang; // Mettre à jour la langue actuelle

            recreate(); // Redémarrer l'activité pour appliquer les changements de langue
        } else {
            // Mise à jour de la configuration uniquement
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);

            Resources resources = getResources();
            Configuration configuration = resources.getConfiguration();
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
    }


}