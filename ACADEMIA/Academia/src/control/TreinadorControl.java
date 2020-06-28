package control;

import academia.Atividades;
import academia.Modalidade;
import academia.Pacotes;
import dao.TreinadorDAOImp;

public class TreinadorControl {

	TreinadorDAOImp treDAO = new TreinadorDAOImp();

	public void criarModalidades(Modalidade m) {

		treDAO.criarModalidades(m);

	}

	public void criarAtividades(Atividades a) {

		treDAO.criarAtividades(a);

	}

	public void montarPacotes(Pacotes p) {

		treDAO.montarPacote(p);

	}

	public Modalidade buscarModalidade(long idMod) {

		Modalidade m = treDAO.buscarModalidade(idMod);
		return m;

	}

	public Atividades buscarAtividade(long idAtiv) {

		Atividades a = treDAO.buscarAtividade(idAtiv);
		return a;

	}

	public Pacotes buscarPacote(long idPact) {

		Pacotes p = treDAO.buscarPacote(idPact);
		return p;

	}

	public void mostrarTodasModalidades() {
	}

	public void mostrarTodasAtividades() {
	}

	public void mostrarTodosPacotes() {
	}

}
