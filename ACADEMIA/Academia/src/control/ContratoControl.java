package control;

import java.util.ArrayList;
import java.util.List;

import academia.Cobranca;
import academia.Contrato;
import academia.Pacotes;
import academia.ParticiparModalidade;

public class ContratoControl {


	public void gerarCobranca(Contrato c) {

		for (int aux = 0; aux <= c.getQtdParcelas(); aux++) {
			Cobranca co = new Cobranca();
			co.setID_Contrato(c.getID());
			co.setDataPgto(c.getDataContrato());
			co.setValor(c.getValorMes());
			co.setNumParcela(aux + 1);
		}
	}



	public boolean permitirEntrada(Contrato c) {

		c.setComparacaoData();

		if (c.getAcesso()) {
			return true;
		} else {
			return false;
		}

	}



}
