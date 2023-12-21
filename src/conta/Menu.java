package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {

		ContaController contas = new ContaController();

		Scanner leia = new Scanner(System.in);
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		while (true) {

			System.out.println(Cores.TEXT_YELLOW_BOLD + Cores.ANSI_BLACK_BACKGROUND);
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println(Cores.TEXT_YELLOW_BOLD + Cores.ANSI_BLACK_BACKGROUND);

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {

				System.out.println();
				System.out.println("*********************************************************");
				System.out.println("\n    Banco do Brazil com Z - O seu futuro começa aqui!    ");
				sobre();
				leia.close();
				System.exit(0);
			}
			System.out.println(Cores.TEXT_YELLOW_BOLD + Cores.ANSI_BLACK_BACKGROUND);
			switch (opcao) {
			case 1:
				
				System.out.println("\n Criar Conta");
				System.out.println("Digite o número da agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o nome do titular: ");
				leia.skip("\\R");
				titular = leia.nextLine();
				do {
					System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();
				} while (tipo < 1 && tipo > 2);
				System.out.println("Digite o Saldo da Conta: ");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito(R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversário da Conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}
				keyPress();
				break;
			case 2:
				System.out.println("\n Listar todas as Contas");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println("\n Buscar Conta por número");
				System.out.println("Digite o Número da Conta: ");
				numero = leia.nextInt();
				contas.procurarPorNumero(numero);
				keyPress();
				break;
			case 4:
				System.out.println("\n Atualizar dados da Conta");
				System.out.print("Digite o número da conta: ");
				numero = leia.nextInt();
				
				var buscarConta = contas.buscarNaCollection(numero);
				
				if(buscarConta != null) {
					tipo = buscarConta.getTipo();
					System.out.println("Digite o número da agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o nome do titular: ");
					leia.skip("\\R");
					titular = leia.nextLine();
					System.out.println("Digite o Saldo da Conta: ");
					saldo = leia.nextFloat();

					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o Limite de Crédito(R$): ");
						limite = leia.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversário da Conta: ");
						aniversario = leia.nextInt();
						contas.cadastrar(
								new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Tipo de conta inválida");
					}
					}		
				} else {
					System.out.println("Conta não encontrada.");
				}
				keyPress();
				break;
			case 5:
				System.out.println("\n Apagar Conta");
				System.out.println("Digite o Número da Conta: ");
				numero = leia.nextInt();
				contas.deletar(numero);
				keyPress();
				break;
			case 6:
				System.out.println("\n Sacar");
				System.out.print("Digite o número da conta: ");
				numero = leia.nextInt();
				do {
					System.out.print("Digite o valor do saque: ");
					valor = leia.nextFloat();
				} while(valor <= 0);
				
				contas.sacar(numero, valor);
				keyPress();
				break;
			case 7:
				System.out.println("\n Depositar");
				System.out.print("Digite o número da conta: ");
				numero = leia.nextInt();
				do {
					System.out.print("Digite o valor do saque: ");
					valor = leia.nextFloat();
				} while(valor <= 0);
				
				contas.sacar(numero, valor);
				keyPress();
				break;
			case 8:
				System.out.println("\n Transferir");
				System.out.print("Digite o número da conta de origem: ");
				int contaOrigem = leia.nextInt();
				System.out.print("Digite o número da conta de destino: ");
				int contaDestino = leia.nextInt();
				do {
					System.out.print("Digite o valor da Transferência: ");
					valor = leia.nextFloat();
				} while(valor <= 0);
				
				contas.transferir(contaOrigem, contaDestino, valor);
				keyPress();
				break;
			default:
				System.out.println("\nOpção Inválida" + Cores.TEXT_RESET);

				keyPress();
				break;
			}
		}
	}
	
	private static void keyPress() {
		try {
			System.out.println(Cores.TEXT_YELLOW_BOLD + Cores.ANSI_BLACK_BACKGROUND);
			System.out.println( "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");
		}

	}

	private static void sobre() {
		System.out.println(Cores.TEXT_YELLOW_BOLD + Cores.ANSI_BLACK_BACKGROUND);
		System.out.println("\n*********************************************************");
		System.out.println("                Projeto Desenvolvido por:                  ");
		System.out.println("Maria Eduarda Vasconcelos - eduuardavasconcelos@gmail.com");
		System.out.println("                https://github.com/eduardavasc           ");
		System.out.println("*********************************************************");

	}
}