package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.IContaRepository;

public class ContaController implements IContaRepository {

	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numeroContas = 0;

	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			conta.visualizar();
		} else
			System.out.println("\n AConta número" + conta + "não foi encontrada.");

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA conta número: " + conta.getNumero() + "foi criada com sucesso!");

	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}

	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());

		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA conta numéro" + conta.getNumero() + "foi atualizada com sucesso.");
		} else
			System.out.println("\nA conta numéro" + conta.getNumero() + "não foi encontrada.");

	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			if (listaContas.remove(conta) == true) {
				System.out.println("\nA conta " + numero + " foi deletada com sucesso!");
			} else {
				System.out.println("\nA conta " + numero + " não foi encontrada!");
			}
		}

	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			if (conta.sacar(valor) == true) {
				System.out.println(
						"\nO saque da conta " + numero + " no valor de " + valor + " foi efetuado com sucesso!");
			} else {
				System.out.println("\nA conta " + numero + " não foi encontrada!");
			}
		}
	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			conta.depositar(valor);
			System.out.println(
					"\nO deposito da conta " + numero + " no valor de " + valor + " foi efetuado com sucesso!");
		} else {
			System.out.println(
					"\nA conta " + numero + " não foi encontrada ou a conta destino não é uma conta corrente!");
		}

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var cantaOrigem = buscarNaCollection(numeroOrigem);
		var cantaDestino = buscarNaCollection(numeroDestino);
		
		if(cantaOrigem != null && cantaDestino != null) {
			if(cantaOrigem.sacar(valor) == true) {
				cantaDestino.depositar(valor);
				System.out.println("\nA transferência foi efetuado com sucesso!!");
		} else {
			System.out.println("\nA conta de origem e/ou destino não foram encontradas!!");
		}
	}
	}
	public int gerarNumero() {

		return numeroContas++;
	}

	public Conta buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}
}
	