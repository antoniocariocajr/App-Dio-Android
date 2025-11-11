package com.appdioandroid

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.btn_portuguese).setOnClickListener {
            setLocale("pt")
        }

        findViewById<Button>(R.id.btn_english).setOnClickListener {
            setLocale("en")
        }
        findViewById<Button>(R.id.btn_spanish).setOnClickListener {
            setLocale("es")
        }
    }
    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale) // Define a localidade padrão

        // 1. Atualizar a Configuração
        val config = Configuration()
        config.setLocale(locale)

        // 2. Aplicar a nova configuração ao contexto base da aplicação
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        // 3. Reiniciar a Activity para aplicar as mudanças imediatamente
        // (Isso recria a Activity e recarrega os recursos strings.xml)
        val intent = intent
        finish() // Finaliza a Activity atual
        startActivity(intent) // Inicia uma nova instância da Activity
    }
}