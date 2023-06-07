package br.com.lucas.sistemabancario.model;

public class Conta {

    private int numeroConta;
    private String nomeTitular;
    private double saldo;
    private double valorDeposito;
    private double valorSaque;

    public Conta() {
    }

    public Conta(int numeroConta, String nomeTitular, double saldo) {
        this.numeroConta = numeroConta;
        this.nomeTitular = nomeTitular;
        this.saldo = saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void realizarDeposito(double valor) {
        if (valor > 0) {
            saldo += valor;
            valorDeposito += valor;
            System.out.println("Depósito de " + valor + " realizado com sucesso na conta.");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public double getValorDeposito() {
        return valorDeposito;
    }

    public void realizarSaque(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            valorSaque += valor;
            System.out.println("Saque de " + valor + " realizado com sucesso na conta.");
        } else {
            System.out.println("Valor inválido para saque ou saldo insuficiente.");
        }
    }

    public double getValorSaque() {
        return valorSaque;
    }

    @Override
    public String toString() {
        return "| Conta: " + numeroConta + " | Titular: " + nomeTitular + " | Saldo: " + saldo + " |";
    }
}