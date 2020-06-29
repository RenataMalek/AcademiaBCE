package dao;

import academia.Cliente;
import academia.Contrato;

public interface RecepcionistaDAO {

	void adicionarCliente(Cliente c);
	
	Cliente pesquisarPorCPF(String CPF);
	
	void adicionarContrato(Contrato c);
	
	void gerarCobranca(Contrato c);
	
	Contrato pesquisarPorCPFCon(String CPF);
}
