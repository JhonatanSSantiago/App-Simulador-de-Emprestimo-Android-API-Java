package com.jhonssantiago.myemprestimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jhonssantiago.myemprestimo.cliente.Cliente;
import com.jhonssantiago.myemprestimo.emprestimo.Emprestimo;

/*
O app deve receber o valor do empréstimo, quantidade de parcelas, taxa de juros e salário do cliente.
Saiba que, o empréstimo só pode ser concedido se o valor da parcela for menor ou igual a 30% do salário do cliente. - OK
Você deve utilizar o cálculo de juros compostos, por exemplo, em um empréstimo no valor de R$ 1000,00 em 10 parcelas,
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
    private double salario, valorParcela, porcentagem, valorEmprestimo,  jurosMes;
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
                calcularParcela(valorEmprestimo, qtdParcela);
                calulcarPorcentagem(salario, valorParcela);
              //  Toast.makeText(getApplicationContext(), "%: "+ porc, Toast.LENGTH_LONG).show();
                if(porcentagem < 30){
                    Toast.makeText(getApplicationContext(), "Emprestimo: valor total: "+valorEmprestimo+" qtd parcelas: "+qtdParcela+" valor parcela: "+valorParcela+ "juros de : "+jurosMes, Toast.LENGTH_LONG).show();
                    Intent it = new Intent(getApplicationContext(), PropostaActivity.class);
                    it.putExtra("dado_valorEmprestimo", valorEmprestimo);
                    it.putExtra("dado_jurosMes", jurosMes);
                    it.putExtra("dado_qdtParcela", qtdParcela);
                    it.putExtra("dado_valorParcela", valorParcela);
                    startActivity(it);
                }else{
                    Toast.makeText(getApplicationContext(), "Não podemos, porcentagem: "+porcentagem, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void calulcarPorcentagem(double salario, double valorParcela) {
        porcentagem = (valorParcela*100)/salario;
    }

    private void converterDados() {
        nome = nometxt.getText().toString();
        salario = Double.parseDouble(salariotxt.getText().toString());
        qtdParcela = Integer.parseInt(parcelastxt.getText().toString());
        valorEmprestimo =  Double.parseDouble(emprestimotxt.getText().toString());
        jurosMes =  Double.parseDouble(jurostxt.getText().toString());
    }

    private void calcularParcela(double emprestimo, int parcela) {
        valorParcela = emprestimo/parcela;
    }
}