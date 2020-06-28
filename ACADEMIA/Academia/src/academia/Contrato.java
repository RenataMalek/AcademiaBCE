package academia;

import java.time.LocalDate;

public class Contrato {
	long ID;
	LocalDate dataContrato;
	LocalDate dataComparacao;
	LocalDate dataSistema;
	String cpf_cli;

	int qtdParcelas;
	int qtdTreinos;

	double valorMes;
	double valorTotal;
	boolean acesso;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getCpf_cli() {
		return cpf_cli;
	}

	public void setCpf_cli(String cpf_cli) {
		this.cpf_cli = cpf_cli;
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

	public int getQtdTreinos() {
		return qtdTreinos;
	}

	public void setQtdTreinos(int qtdTreinos) {
		this.qtdTreinos = qtdTreinos;
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
