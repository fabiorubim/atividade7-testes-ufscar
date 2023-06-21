package br.com.resolvedor;
import java.util.ArrayList;

public class Resolvedor {
	private ClienteRepo repo;

	public Resolvedor(ClienteRepo repo) {
		this.repo = repo;
	}

	public ArrayList<Cliente> definirPromocao(int[] cods) throws Exception {
		if (cods == null || cods.length == 0)
			throw new IllegalArgumentException("sem codigo algum");
		var clientes = new ArrayList<Cliente>();
		for (int cod : cods) {
			Cliente c = repo.getCliente(cod);
			if (c == null)
				throw new Exception("Codigo do cliente nao valido");
			clientes.add(c);
		}
		var resposta = new ArrayList<Cliente>();
		if (clientes.size() >= 3) {
			// todos ganham 25% de desconto
			for (Cliente c : clientes) {
				c.setDesconto(25);
				resposta.add(c);
			}
		} else {
			// o 1º cliente ganha 15%
			clientes.get(0).setDesconto(15);
			resposta.add(clientes.get(0));
			// e, se existir, o 2º ganha 10%
			if (clientes.size() > 1) {
				clientes.get(1).setDesconto(10);
				resposta.add(clientes.get(1));
			}
		}
		return resposta;
	}
}
