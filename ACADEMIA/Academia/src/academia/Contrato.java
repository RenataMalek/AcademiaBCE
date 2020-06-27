package academia;

import java.time.LocalDate;

public class Contrato {

	Cliente cliente = new Cliente();
	long ID;
	LocalDate dataContrato;
	LocalDate dataComparacao;
	LocalDate dataSistema;
	int qtdParcelas;
	double valorMes;
	double valorTotal;
	boolean acesso;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataContrato() {
		return dataContrato;
	}

	public void setDataContrato(LocalDate dataContrato) {
		this.dataContrato = dataContrato;
	}

	public int getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(int qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public double getValorMes() {
		return valorMes;
	}

	public void setValorMes(double valorMes) {
		this.valorMes = valorMes;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean getAcesso() {
		return acesso;
	}

	public void setComparacaoData() {
		dataComparacao = this.dataContrato;
		if (dataComparacao.isBefore(dataSistema) == true) {
			acesso = true;
		} else {
			acesso = false;
			dataComparacao.plusMonths(1);
		}
	}
}
