package com.example.snackbar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var inputFieldOneET: EditText
    private lateinit var inputFieldTwoET: EditText
    private lateinit var saveBTN: Button
    private lateinit var deleteBTN: Button
    private lateinit var infoOutputTV: TextView
    private var tempData: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputFieldOneET = findViewById(R.id.inputFieldOneET)
        inputFieldTwoET = findViewById(R.id.inputFieldTwoET)
        saveBTN = findViewById(R.id.saveBTN)
        deleteBTN = findViewById(R.id.deleteBTN)
        infoOutputTV = findViewById(R.id.infoOutputTV)

        saveBTN.setOnClickListener {
            if ((inputFieldOneET.text.toString() + inputFieldTwoET.text.toString()).isNotEmpty()) {
                val currentDate = SimpleDateFormat("dd.MM.yyyy.HH:mm.", Locale.getDefault()).format(Date())
                tempData += "Сохранённые данные:\n" +
                        "${inputFieldOneET.text}\n" +
                        "${inputFieldTwoET.text}\n" +
                        "Дата: $currentDate\n"
                infoOutputTV.text = tempData
                Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Введите данные", Toast.LENGTH_SHORT).show()
            }
            inputFieldOneET.text.clear()
            inputFieldTwoET.text.clear()
        }

        deleteBTN.setOnClickListener { view ->
            if (tempData != "") {
                val snackBar = Snackbar
                    .make(
                        view,
                        "Подтвердите удаление",
                        Snackbar.LENGTH_LONG
                    )
                    .setAction("Удалить") {
                        Snackbar.make(
                            view,
                            "Данные удалены",
                            Snackbar.LENGTH_LONG
                        ).show()
                        infoOutputTV.text = ""
                        tempData = ""
                    }
                snackBar.show()
            } else {
                Toast.makeText(this, "Нечего удалять", Toast.LENGTH_SHORT).show()
            }
        }

    }
}