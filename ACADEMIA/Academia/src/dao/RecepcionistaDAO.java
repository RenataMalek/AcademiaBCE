package dao;

import java.util.List;

import academia.Cliente;
import academia.Cobranca;
import academia.Contrato;

public interface RecepcionistaDAO {

	void adicionarCliente(Cliente c);
	
	Cliente pesquisarPorCPF(String CPF);
	
	void adicionarContrato(Contrato c);
	
	void gerarCobranca(Contrato c);
	
	List<Cobranca> tableViewCobranca(long idContrato);
	
	Contrato pesquisarPorCPFCon(String CPF);
}
