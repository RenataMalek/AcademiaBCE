package control;

import java.util.List;

import academia.Cliente;
import academia.Cobranca;
import academia.Contrato;
import dao.RecepcionistaDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RecepcionistaControl {

	RecepcionistaDAOImp repDAO = new RecepcionistaDAOImp();

	private ObservableList<Cliente> listaCli = FXCollections.observableArrayList();
	private ObservableList<Contrato> listaCon = FXCollections.observableArrayList();
	private ObservableList<Cobranca> lista = FXCollections.observableArrayList();

	public ObservableList<Cobranca> getLista() {
		return lista;
	}

	public ObservableList<Cliente> getListaCli() {
		return listaCli;
	}

	public ObservableList<Contrato> getListaCon() {
		return listaCon;
	}

	public Cobranca buscaTableCob(long idContrato) {

		List<Cobranca> cob = repDAO.tableViewCobranca(idContrato);
		lista.addAll(cob);

		return lista.get(0);
	}

	public Cliente buscaTableCli() {
		List<Cliente> cli = repDAO.tableViewCliente();
		listaCli.addAll(cli);
		return listaCli.get(0);
	}

	public Contrato buscaTableCon() {
		List<Contrato> con = repDAO.tableViewContrato();
		listaCon.addAll(con);
		return listaCon.get(0);
	}

	// tratamento manter cliente

	public void novoCliente(Cliente c) {

		repDAO.adicionarCliente(c);

	}

	public Cliente buscarCliente(String CPF) {
		Cliente c = repDAO.pesquisarPorCPF(CPF);
		return c;
	}

	public void mostrarTodosClientes() {
	}

	public void novoContrato(Contrato c) {

		repDAO.adicionarContrato(c);

	}

	public Contrato buscarContrato(String CPF) {

		Contrato c = repDAO.pesquisarPorCPFCon(CPF);
		return c;

	}

	public void mostrarTodosContratos() {

	}
}
