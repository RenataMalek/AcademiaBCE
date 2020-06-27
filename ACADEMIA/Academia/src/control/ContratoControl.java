package control;

import java.util.ArrayList;
import java.util.List;

import academia.Cobranca;
import academia.Contrato;
import academia.Pacotes;
import academia.ParticiparModalidade;

public class ContratoControl {

	long idPartcp = 0;

	List<ParticiparModalidade> pm = new ArrayList<ParticiparModalidade>();
	List<Cobranca> cobrancas = new ArrayList<Cobranca>();

	public void gerarCobranca(Contrato c) {

		for (int aux = 0; aux <= c.getQtdParcelas(); aux++) {
			Cobranca co = new Cobranca();
			co.setID_Contrato(c.getID());
			co.setDataPgto(c.getDataContrato());
			co.setValor(c.getValorMes());
			co.setNumParcela(aux + 1);
			cobrancas.add(co);
		}
	}

	public void mostrarCobrancas() {
		for (Cobranca c : cobrancas) {
			System.out.println(c);
		}
	}

	public void confirmarPagamento(int parcela) {
		for (Cobranca co : cobrancas) {
			if (co.getNumParcela() == parcela) {
				co.setPago(true);
			}
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

	public void vincularPacote_Contrato(Contrato contrato) {
		TreinadorControl tc = new TreinadorControl();
		ParticiparModalidade p = new ParticiparModalidade();
		p.setIdParticipacao(idPartcp);
		idPartcp++;
		p.setContrato(contrato);

		// criar um loop para ir inserindo atividades enquanto o usuario for clicando em
		// + na tela

		long idPacote = 0; // codificar para receber o id dos pacotes pelo usuario;
		Pacotes pacote = tc.buscarPacote(idPacote);
		p.setPacote(pacote);

		// encerra loop de inserção de pacotes quando o cliente terminar de inserir

		pm.add(p);
	}

	public void mostrarVinculosPacote_Contrato(Contrato contrato) {
		for (ParticiparModalidade p : pm) {
			if (p.getContrato() == contrato) {
				System.out.println(p);
			}
		}
	}

}
