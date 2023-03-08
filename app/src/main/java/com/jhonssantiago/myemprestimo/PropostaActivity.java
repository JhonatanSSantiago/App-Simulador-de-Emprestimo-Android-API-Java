package com.jhonssantiago.myemprestimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PropostaActivity extends AppCompatActivity {
    private TextView resultado, nome, salario;
    private String texto = "";

    private double porcentagem_juros, valorJuros, valor_parcela;

    NumberFormat formatter = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposta);
        resultado = findViewById(R.id.Resultado);
        nome = findViewById(R.id.nomeClienteResultado);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        Intent it = getIntent();

        double valorEmprestimo = it.getDoubleExtra("dado_valorEmprestimo", 0);
        porcentagem_juros = it.getDoubleExtra("porcentagem_juros", 0);
        int qtd = it.getIntExtra("dado_qdtParcela", 0);
        String nomeC = it.getStringExtra("dado_nome");

        nome.setText(nomeC +" temos a seguinte proposta para você: ");
        calcularJuros(valorEmprestimo);

        for(int i = 1; i <= qtd; i++){
           double montante = valorEmprestimo + valorJuros;
           double juros_anterior = valorJuros;
           valorEmprestimo = montante;
           calcularJuros(valorEmprestimo);
           calcularParcela(valorEmprestimo, qtd);

            texto += "\n"+ i + "º Mês - TOTAL: " + formatter.format(montante) + " - juros: " + formatter.format(juros_anterior);
        }

        resultado.setText(texto +" \n"+ qtd +" vezes de: " +formatter.format(valor_parcela));
    }

    private void calcularParcela(double valorEmprestimo, int qtd) {
        valor_parcela = valorEmprestimo/qtd;
    }

    public void calcularJuros(double valorEmprestimo){
        valorJuros = (valorEmprestimo*porcentagem_juros)/100;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //identificar a ação de voltar a tela
            case android.R.id.home:
                //encerra a activity
                finish();
                break;
        }

        return super.onOptionsItemSelected( item );
    }

}