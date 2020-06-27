package academia;

import java.time.LocalDate;

public class Cobranca {

	long ID_Contrato;
	LocalDate dataPgto;
	double valor;
	int numParcela;
	boolean pago;
	
	public long getID_Contrato() {
		return ID_Contrato;
	}
	public void setID_Contrato(long iD_Contrato) {
		ID_Contrato = iD_Contrato;
	}
	public LocalDate getDataPgto() {
		return dataPgto;
	}
	public void setDataPgto(LocalDate dataPgto) {
		this.dataPgto = dataPgto;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getNumParcela() {
		return numParcela;
	}
	public void setNumParcela(int numParcela) {
		this.numParcela = numParcela;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
}
