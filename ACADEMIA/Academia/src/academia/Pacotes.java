package academia;

import java.util.ArrayList;
import java.util.List;

public class Pacotes {

	long idPacote;
	long idModalidade;
	long idAtividade;
	List<Atividades> atividade = new ArrayList<Atividades>();

	public long getIdPacote() {
		return idPacote;
	}

	public void setIdPacote(long idPacote) {
		this.idPacote = idPacote;
	}

	public List<Atividades> getAtividade() {
		return atividade;
	}

	public void setAtividade(List<Atividades> atividade) {
		this.atividade = atividade;
	}

	public void setIdModalidade(long idModalidade) {
		this.idModalidade = idModalidade;
	}

	public void setIdAtividade(long idAtividade) {
		this.idAtividade = idAtividade;
	}

	public long getIdAtividade() {
		return idAtividade;
	}
	
	public long getIdModalidade() {
		return idModalidade;
	}

	public List<Atividades> getIdAtividadeList() {
		return atividade;
	}

	public void setAtividade(Atividades a) {
		atividade.add(a);
	}

}
