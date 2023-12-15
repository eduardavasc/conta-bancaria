package conta;

import conta.model.Conta;
import conta.util.Cores;

public class Menu {

	public static void main(String[] args) {
		System.out.println(Cores.TEXT_YELLOW_BOLD+ Cores.ANSI_BLACK_BACKGROUND);
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
		System.out.println("                                                     " + Cores.TEXT_RESET);
		
		Conta c1 = new Conta(123456, 123, 1, "Maria dos Santos", 2500.0f);
		c1.visualizar();  
		
	
	
	}
}
