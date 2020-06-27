package academia;

import java.util.ArrayList;
import java.util.List;

public class ParticiparModalidade {

	long idParticipacao;
	Contrato contrato;
	List<Pacotes> pacote = new ArrayList<Pacotes>();

	public long getIdParticipacao() {
		return idParticipacao;
	}

	public void setIdParticipacao(long idParticipacao) {
		this.idParticipacao = idParticipacao;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Pacotes> getPacote() {
		return pacote;
	}

	public void setPacote(Pacotes p) {
		pacote.add(p);
	}

}
