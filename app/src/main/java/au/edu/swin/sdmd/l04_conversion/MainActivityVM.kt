package au.edu.swin.sdmd.l04_conversion

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.getValue

class MainActivityVM : AppCompatActivity() {
    private val viewModel: CalViewModel by viewModels()

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
            val celsiusText = etCelsius.text.toString()

            if (celsiusText.isNotEmpty()) {
                try {
                    viewModel.celsius = celsiusText.toFloat()
                    Log.d("CELSIUS", viewModel.celsius.toString())

                    viewModel.fahrenheit = viewModel.convertToFahrenheit()
                    val tvFahrenheit = findViewById<TextView>(R.id.fahrenheit)
                    tvFahrenheit.text = String.format("%.1f F", viewModel.fahrenheit)

                    Snackbar.make(findViewById(R.id.main), "Conversion successful!", Snackbar.LENGTH_SHORT).show()
                } catch (e: NumberFormatException) {
                    Snackbar.make(findViewById(R.id.main), "Please enter a valid number", Snackbar.LENGTH_SHORT).show()
                }
            } else {
                Snackbar.make(findViewById(R.id.main), "Please enter a temperature", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
class CalViewModel : ViewModel() {
    var celsius: Float = 0.0f
    var fahrenheit: Float = 0.0f

    fun convertToFahrenheit(): Float {
        fahrenheit = (celsius * 9/5) + 32
        return fahrenheit
    }


}

