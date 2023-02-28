package com.jhonssantiago.myemprestimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jhonssantiago.myemprestimo.emprestimo.Emprestimo;

public class PropostaActivity extends AppCompatActivity {
    private TextView resultado;
    private String texto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposta);
        resultado = findViewById(R.id.Resultado);

        Intent it = getIntent();

        double valorEmprestimo = it.getDoubleExtra("dado_valorEmprestimo", 0.0);
        double jurosMes = it.getDoubleExtra("dado_jurosMes", 0);
        int qtdParcela = it.getIntExtra("dado_qdtParcela", 0);
        double valorParcela = it.getDoubleExtra("dado_valorParcela", 0.0);

        Emprestimo e = new Emprestimo(valorEmprestimo, jurosMes, qtdParcela, valorParcela);
        int qtd = e.getQuantidade_parcela();
        double valor_e = e.getValor_emprestimo();
        double juros_m = e.getJuros();

        double anterior = 0.0;
        for(int i = 1; i <= qtd; i++){
            double montante = valor_e * Math.pow((1 + juros_m), i);
            double juros = montante - valor_e - anterior;
            anterior += juros;
            texto += "\nMês: " + i + " - Montante: " + montante + " - Juros: " + juros;
        }
        resultado.setText(texto);
    }

}