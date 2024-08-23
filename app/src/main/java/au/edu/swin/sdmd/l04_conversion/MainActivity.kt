package au.edu.swin.sdmd.l04_conversion

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val button = findViewById<Button>(R.id.converter)
        button.setOnClickListener {
            val etCelsius = findViewById<EditText>(R.id.celsius)
            val celsius = etCelsius.text.toString().toFloat()
            Log.d("CELSIUS", celsius.toString())
            val fahrenheit = (celsius * 9/5) + 32
            val tvFahrenheit = findViewById<TextView>(R.id.fahrenheit)
            tvFahrenheit.text = "${fahrenheit} F"
        }
    }
}