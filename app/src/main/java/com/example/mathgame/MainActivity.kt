package com.example.mathgame

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var num1: Int = 0
    var num2: Int = 0
    var op: String = ""
    var count: Int = 0
    var score: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mainView = findViewById<ConstraintLayout>(R.id.main)
        conta(mainView)
    }

    fun conta (view: View) {

        val proxButton = findViewById<Button>(R.id.prox)
        val viewColor = findViewById<ConstraintLayout>(R.id.main)
        val conta = findViewById<TextView>(R.id.conta)
        num1 = (0 .. 100).random()
        num2 = (0 .. 100).random()
        op = if (Random.nextBoolean()) "+" else "-"

        if(op == "+"){
            conta.text = "$num1 + $num2"
        } else {
            if(num1 >= num2) {
                conta.text = "$num1 - $num2"
            } else{
                var aux = num1
                num1 = num2
                num2 = aux
                conta.text = "$num1 - $num2"
            }
        }


        viewColor.setBackgroundColor(Color.WHITE)
    }

    fun env (view: View) {

        val input = findViewById<EditText>(R.id.resposta)
        val output = findViewById<TextView>(R.id.respostaCorreta)
        val viewColor = findViewById<ConstraintLayout>(R.id.main)
        val green = getColor(R.color.acerto)
        val red = getColor(R.color.erro)
        val envButton = findViewById<Button>(R.id.env)
        val proxButton = findViewById<Button>(R.id.prox)
        val resultadoButton = findViewById<Button>(R.id.resultado)
        val conta = findViewById<TextView>(R.id.conta)
        var resCerta: Int = 0
        when (op){
            "+" -> resCerta = num1 + num2
            "-" -> resCerta = num1 - num2
        }

        output.visibility = View.VISIBLE
        if(input.length() == 0){
            Toast.makeText(this, "Forneça uma resposta!", Toast.LENGTH_SHORT).show()
        } else {
            val valor = input.text.toString().toInt()
            if (count < 5){
                proxButton.visibility = View.VISIBLE
                if (resCerta == valor){
                    viewColor.setBackgroundColor(green)
                    score += 20
                    output.text = "Correto"
                } else {
                    viewColor.setBackgroundColor(red)
                    output.text = "A resposta certa é $resCerta"
                }
                count ++
                if (count == 4){
                    proxButton.visibility = View.GONE

                }
            }

           if (count == 5){
                viewColor.setBackgroundColor(Color.WHITE)
                envButton.visibility = View.GONE
                proxButton.visibility = View.GONE
                input.visibility = View.GONE
                output.visibility = View.GONE
                conta.visibility = View.GONE
                resultadoButton.visibility = View.VISIBLE
            }
        }
    }

    fun mostrarResultado (view: View){

        val pontos = findViewById<TextView>(R.id.score)
        val resultadoButton = findViewById<Button>(R.id.resultado)
        val btnReiniciar = findViewById<Button>(R.id.reiniciar)

        resultadoButton.visibility = View.GONE
        pontos.visibility = View.VISIBLE
        pontos.text = "$score pontos"
        btnReiniciar.visibility = View.VISIBLE
    }
    fun reiniciarJogo (view: View){
        num1 = 0
        num2 = 0
        op = ""
        count = 0
        score = 0
        val input = findViewById<EditText>(R.id.resposta)
        val viewColor = findViewById<ConstraintLayout>(R.id.main)
        val envButton = findViewById<Button>(R.id.env)
        val conta = findViewById<TextView>(R.id.conta)
        val output = findViewById<TextView>(R.id.respostaCorreta)
        val btnReiniciar = findViewById<Button>(R.id.reiniciar)
        val pontos = findViewById<TextView>(R.id.score)

        viewColor.setBackgroundColor(Color.WHITE)
        envButton.visibility = View.VISIBLE
        input.visibility = View.VISIBLE
        conta.visibility = View.VISIBLE
//        output.visibility = View.GONE
        btnReiniciar.visibility = View.GONE
        pontos.visibility = View.GONE
    }
}