package br.com.lucas.sistemabancario.main;

import br.com.lucas.sistemabancario.model.Conta;
import java.util.Scanner;

/**
 *
 * @author 00240578
 */
public class SistemaBancario {

     public static void main(String[] args) {

        Conta contasBancarias[] = new Conta[100];

        Scanner scn = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 7) {
            System.out.println("""
                           -----------------------------
                           Informe a opção desejada: 
                           1 - Cadastro
                           2 - Ordenação
                           3 - Depósito
                           4 - Saque
                           5 - Calcular Saldo Total
                           6 - Verifiação Saldo Negativo
                           7 - Sair""");

            opcao = scn.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Informe o número da conta: ");
                    int numeroConta = scn.nextInt();

                    System.out.println("Informe o nome do titular: ");
                    String nomeTitular = scn.next();

                    System.out.print("Informe o saldo inicial: ");
                    double saldoInicial = scn.nextDouble();

                    Conta conta = new Conta(numeroConta, nomeTitular, saldoInicial);

                    cadastrarConta(contasBancarias, conta);

                    System.out.println("Conta cadastrada: \n" + conta);
                    break;
                case 2:
                    System.out.println("Ordenar por: \n"
                            + "1 - Número\n"
                            + "2 - Saldo");

                    int opcaoOrdenacao = scn.nextInt();

                    if (opcaoOrdenacao == 1) {
                        ordenarContasPorNumero(contasBancarias);
                    } else if (opcaoOrdenacao == 2) {
                        ordenarContasPorSaldo(contasBancarias);
                    } else {
                        System.out.println("Opção inválida.");
                        break;
                    }

                    System.out.println("\nExibindo contas ordenadas: ");
                    for (Conta c : contasBancarias) {
                        if (c != null) {
                            System.out.println(c);
                        }
                    }

                    break;
                case 3:
                    System.out.println("Informe o critério de pesquisa: ");
                    System.out.println("1 - Número da conta");
                    System.out.println("2 - Nome do titular");
                    int criterio = scn.nextInt();

                    if (criterio == 1) {
                        System.out.println("Informe o número da conta: ");
                    } else if (criterio == 2) {
                        System.out.println("Informe o nome do titular: ");
                    }

                    String valorPesquisa = scn.next();

                    Conta contaEncontrada = pesquisarConta(contasBancarias, criterio, valorPesquisa);

                    if (contaEncontrada != null) {
                        System.out.println("Informe o valor do depósito: ");
                        double valorDeposito = scn.nextDouble();
                        contaEncontrada.realizarDeposito(valorDeposito);
                        System.out.println("Depósito realizado com sucesso na conta:\n" + contaEncontrada);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }

                    break;
                case 4:
                    System.out.println("Informe o número da conta: ");
                    int numeroContaSaque = scn.nextInt();

                    Conta contaEncontradaSaque = pesquisarContaPorNumero(contasBancarias, numeroContaSaque);

                    if (contaEncontradaSaque != null) {
                        System.out.println("Informe o valor do saque: ");
                        double valorSaque = scn.nextDouble();

                        if (valorSaque > 0 && valorSaque <= contaEncontradaSaque.getSaldo()) {
                            contaEncontradaSaque.realizarSaque(valorSaque);
                            System.out.println("Saque realizado com sucesso na conta:\n" + contaEncontradaSaque);
                        } else {
                            System.out.println("Valor inválido para saque ou saldo insuficiente.");
                        }
                    } else {
                        System.out.println("Conta não encontrada.");
                    }

                    break;
                case 5:
                    double saldoTotal = calcularSaldoTotal(contasBancarias);
                    System.out.println("Saldo Total: " + saldoTotal);
                    break;
                case 6:
                    saldoTotal = calcularSaldoTotal(contasBancarias);
                    System.out.println("Saldo Total: " + saldoTotal);

                    System.out.println("Contas com saldo negativo:");
                    verificarSaldoNegativo(contasBancarias);
                    break;
            }
        }

        System.out.println("Programa finalizado.");
    }

    public static void cadastrarConta(Conta contas[], Conta conta) {
        for (int i = 0; i < contas.length; i++) {
            if (contas[i] == null) {
                contas[i] = conta;
                break;
            }
        }
    }

    private static void ordenarContasPorNumero(Conta[] contas) {
        int n = contas.length;
        boolean trocado;

        for (int i = 0; i < n - 1; i++) {
            trocado = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (contas[j] != null && contas[j + 1] != null && contas[j].getNumeroConta() > contas[j + 1].getNumeroConta()) {
                    Conta temp = contas[j];
                    contas[j] = contas[j + 1];
                    contas[j + 1] = temp;
                    trocado = true;
                }
            }

            if (!trocado) {
                break;
            }
        }
    }

    private static void ordenarContasPorSaldo(Conta[] contas) {
        int n = contas.length;
        boolean trocado;

        for (int i = 0; i < n - 1; i++) {
            trocado = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (contas[j] != null && contas[j + 1] != null && contas[j].getSaldo() > contas[j + 1].getSaldo()) {
                    // Trocar as posições
                    Conta temp = contas[j];
                    contas[j] = contas[j + 1];
                    contas[j + 1] = temp;
                    trocado = true;
                }
            }

            if (!trocado) {
                break;
            }
        }
    }

    private static Conta pesquisarConta(Conta[] contas, int criterio, String valorPesquisa) {
        for (Conta conta : contas) {
            if (conta != null) {
                if (criterio == 1 && conta.getNumeroConta() == Integer.parseInt(valorPesquisa)) {
                    return conta;
                } else if (criterio == 2 && conta.getNomeTitular().equalsIgnoreCase(valorPesquisa)) {
                    return conta;
                }
            }
        }
        return null;
    }

    private static Conta pesquisarContaPorNumero(Conta[] contas, int numeroConta) {
        int inicio = 0;
        int fim = contas.length - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            Conta contaMeio = contas[meio];

            if (contaMeio == null) {
                fim = meio - 1;
                continue;
            }

            int numeroContaMeio = contaMeio.getNumeroConta();

            if (numeroConta == numeroContaMeio) {
                return contaMeio;
            } else if (numeroConta < numeroContaMeio) {
                fim = meio - 1;
            } else {
                inicio = meio + 1;
            }
        }

        return null;
    }

    private static double calcularSaldoTotal(Conta[] contas) {
        return calcularSaldoTotalRecursivo(contas, 0);
    }

    private static double calcularSaldoTotalRecursivo(Conta[] contas, int indice) {
        if (indice >= contas.length || contas[indice] == null) {
            return 0.0;
        }

        double saldo = contas[indice].getSaldo();
        double saldoRestante = calcularSaldoTotalRecursivo(contas, indice + 1);

        return saldo + saldoRestante;
    }

    private static void verificarSaldoNegativo(Conta[] contas) {
        verificarSaldoNegativoRecursivo(contas, 0);
    }

    private static void verificarSaldoNegativoRecursivo(Conta[] contas, int indice) {
        if (indice >= contas.length || contas[indice] == null) {
            return;
        }

        if (contas[indice].getSaldo() < 0) {
            System.out.println("Conta: " + contas[indice].getNumeroConta() + ", Saldo Negativo: " + contas[indice].getSaldo());
        }

        verificarSaldoNegativoRecursivo(contas, indice + 1);
    }
}

