package control;

import academia.Cliente;
import academia.Contrato;
import dao.RecepcionistaDAOImp;

public class RecepcionistaControl {

	RecepcionistaDAOImp repDAO = new RecepcionistaDAOImp();

	// tratamento manter cliente

	public void novoCliente(Cliente c) {

		repDAO.adicionarCliente(c);

	}

	public void atualizarCliente(String CPF, String nome, String email, String telefone, String endereço) {

	}

	public Cliente buscarCliente(String CPF) {
		Cliente c = repDAO.pesquisarPorCPF(CPF);
		return c;
	}

	public void mostrarTodosClientes() {
	}

	public void novoContrato(Contrato c) {

		try {
			repDAO.adicionarContrato(c);

			ContratoControl ct = new ContratoControl();
			ct.gerarCobranca(c);

		} catch (Exception e) {
			System.out.println("CPF não está cadastrado! Proceda com o cadastro do cliente e tente novamente");
		}

	}

	public void atualizarContrato(String CPF, int qtdParcelas, double valorMes, double valorTotal) {

	}

	public Contrato buscarContrato(String CPF) {

		Contrato c = repDAO.pesquisarPorCPFCon(CPF);
		return c;

	}

	public void mostrarTodosContratos() {

	}
}
