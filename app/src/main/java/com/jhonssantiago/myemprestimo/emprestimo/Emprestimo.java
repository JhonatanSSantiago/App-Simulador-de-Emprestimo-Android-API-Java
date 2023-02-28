package com.jhonssantiago.myemprestimo.emprestimo;

public class Emprestimo {
    private double valor_emprestimo;
    private double juros;
    private int quantidade_parcela;
    private double valor_parcela;

    public Emprestimo(double valor_emprestimo, double juros, int quantidade_parcela, double valor_parcela) {
        this.valor_emprestimo = valor_emprestimo;
        this.juros = juros;
        this.quantidade_parcela = quantidade_parcela;
        this.valor_parcela = valor_parcela;
    }

    public double getValor_emprestimo() {
        return valor_emprestimo;
    }

    public void setValor_emprestimo(double valor_emprestimo) {
        this.valor_emprestimo = valor_emprestimo;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public int getQuantidade_parcela() {
        return quantidade_parcela;
    }

    public void setQuantidade_parcela(int quantidade_parcela) {
        this.quantidade_parcela = quantidade_parcela;
    }

    public double getValor_parcela() {
        return valor_parcela;
    }

    public void setValor_parcela(double valor_parcela) {
        this.valor_parcela = valor_parcela;
    }
}

/*
valor = valor_p x juros
valor_p = valor
valor = valor_p x juros
*/