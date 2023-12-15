package com.example.androidexpert_sub1_amr0017.theme

import android.content.Context
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidexpert_sub1_amr0017.R
import com.example.androidexpert_sub1_amr0017.databinding.ActivityThemeBinding
import com.google.android.material.switchmaterial.SwitchMaterial

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ThemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        switchTheme.setOnCheckedChangeListener{ _: CompoundButton?, isChecked : Boolean ->
            Toast.makeText(this, "This Feature Doesnt Impelemented yet", Toast.LENGTH_SHORT).show()
        }
    }
}