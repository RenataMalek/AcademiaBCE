package academia;

import java.util.ArrayList;
import java.util.List;

public class Pacotes {

	long idModalidade;
	long idAtividade;
	List<Atividades> atividade = new ArrayList<Atividades>();

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
