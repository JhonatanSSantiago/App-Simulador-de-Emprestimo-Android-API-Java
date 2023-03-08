package com.jhonssantiago.myemprestimo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jhonssantiago.myemprestimo.cliente.Cliente;

/*
O app deve receber o valor do empréstimo, quantidade de parcelas, taxa de juros e salário do cliente.
Saiba que, o empréstimo só pode ser concedido se o valor da parcela for menor ou igual a 30% do salário do cliente. - OK
Você deve utilizar o cálculo de juros compostos, por exemplo, em um empréstimo no valor de R$ 10.000,00 em 10 parcelas,
se o banco utiliza uma taxa de juros de 10% ao mês, o valor total da dívida a ser pago no 1º mês será de R$ 1.100,00
(R$ 1.000,00 de empréstimo, acrescidos de R$ 100,00 que são 10% da quantia do empréstimo).
Porém, no 2º mês, os juros serão cobrados em cima dos R$ 1.100,00 cobrados no mês anterior,
e não em relação ao valor original do empréstimo, de R$ 1.000,00. Logo, no segundo mês a dívida terá valor de R$ 1.210,00.
A quantia é 10% maior que no 1º mês. Isso acontece sucessivamente e, no 10º mês, o valor total da dívida será de R$ 2.593,74.
Apresente todas as informações necessárias ao cliente, inclusive o valor de todas as parcelas.
Nota: definir pacotes e classe necessárias.
 */
public class MainActivity extends AppCompatActivity {
    private Button calcular;
    private EditText nometxt, salariotxt, jurostxt, parcelastxt, emprestimotxt;
    private String nome;
    private double salario, valorParcela, porcentagem, valorEmprestimo, juros, valorJuros;
    private int qtdParcela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nometxt = findViewById(R.id.nomeCliente);
        salariotxt = findViewById(R.id.salarioCliente);
        parcelastxt = findViewById(R.id.qtdParcelas);
        emprestimotxt = findViewById(R.id.valorEmprestimo);
        jurostxt = findViewById(R.id.valorJuros);
        calcular = findViewById(R.id.button);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                converterDados();
                Cliente c = new Cliente(nome, salario);
                Toast.makeText(getApplicationContext(), "cliente: "+ c.toString(), Toast.LENGTH_SHORT).show();

                calcularParcela(valorEmprestimo, qtdParcela); //Calcular o valor da primeira parcela
                calculcarPorcentagem(salario, valorParcela); //Verificar se parcela é 30% < Salario

                if(porcentagem < 30){
                    Intent it = new Intent(getApplicationContext(), PropostaActivity.class);
                    it.putExtra("dado_valorEmprestimo", valorEmprestimo);
                    it.putExtra("porcentagem_juros", juros);
                    it.putExtra("dado_qdtParcela", qtdParcela);
                    it.putExtra("dado_nome", nome);
                    startActivity(it);
                }else{
                    AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                    dialog.setTitle("Alerta");
                    dialog.setMessage("Olá "+nome+" Infelizmente não conseguimos oferecer um emprestimo nesse momento");
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    private void calculcarPorcentagem(double salario, double valorParcela) { //Verificar se parcela é 30% < Salario
        porcentagem = (valorParcela*100)/salario;
    }

    private void converterDados() {
        nome = nometxt.getText().toString();
        salario = Double.parseDouble(salariotxt.getText().toString());
        qtdParcela = Integer.parseInt(parcelastxt.getText().toString());
        valorEmprestimo =  Double.parseDouble(emprestimotxt.getText().toString());
        juros =  Double.parseDouble(jurostxt.getText().toString());
       // juros = juros/100;
    }

    private void calcularParcela(double emprestimo, int qtdparcela) { //Calcular o valor da primeira parcela
        valorParcela = emprestimo/qtdparcela;
    }

    public Context getActivity(){ return this; };
}