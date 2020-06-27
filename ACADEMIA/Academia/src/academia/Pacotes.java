package academia;

import java.util.ArrayList;
import java.util.List;

public class Pacotes {

	long idPacote;
	Modalidade modalidade;
	List<Atividades> atividade = new ArrayList<Atividades>();

	public long getIdPacote() {
		return idPacote;
	}

	public void setIdPacote(long idPacote) {
		this.idPacote = idPacote;
	}

	public Modalidade getIdModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public List<Atividades> getIdAtividade() {
		return atividade;
	}

	public void setAtividade(Atividades a) {
		atividade.add(a);
	}

}
