package control;

import java.util.List;

import academia.Atividades;
import academia.Modalidade;
import academia.Pacotes;
import dao.TreinadorDAO;
import dao.TreinadorDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TreinadorControl {

	TreinadorDAO treDAO = new TreinadorDAOImp();

	private ObservableList<Modalidade> listaM = FXCollections.observableArrayList();

	private ObservableList<Atividades> listaA = FXCollections.observableArrayList();

	public ObservableList<Modalidade> getListaM() {
		return listaM;
	}

	public ObservableList<Atividades> getListaA() {
		return listaA;
	}

	public void criarModalidades(Modalidade m) {

		treDAO.criarModalidades(m);

	}

	public void criarAtividades(Atividades a) {

		treDAO.criarAtividades(a);

	}

	public void montarPacotes(Pacotes p) {

		treDAO.montarPacote(p);

	}

	public Modalidade buscaTableMod() {
		long id = 1;

		List<Modalidade> mod = treDAO.buscaTableMod(id);
		listaM.addAll(mod);

		return listaM.get(0);
	}

	public Modalidade buscarModalidade(long idMod) {

		Modalidade m = treDAO.buscarModalidade(idMod);
		return m;

	}

	public Atividades buscarAtividade(long idAtiv) {

		Atividades a = treDAO.buscarAtividade(idAtiv);
		return a;

	}

	public Atividades buscaTableAtiv() {
		long id = 5;

		List<Atividades> ativ = treDAO.buscaTableAtiv(id);
		listaA.addAll(ativ);

		return listaA.get(0);
	}

	public Pacotes buscarPacote(long idMod) {

		Pacotes p = treDAO.buscarPacote(idMod);
		return p;

	}

	public void mostrarTodasModalidades() {
	}

	public void mostrarTodasAtividades() {
	}

	public void mostrarTodosPacotes() {
	}

}
