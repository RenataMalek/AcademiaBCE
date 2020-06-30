package control;

import java.util.List;

import academia.Atividades;
import academia.Contrato;
import academia.Modalidade;
import academia.Pacotes;
import academia.ParticiparModalidade;
import dao.TreinadorDAO;
import dao.TreinadorDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TreinadorControl {

	TreinadorDAO treDAO = new TreinadorDAOImp();

	private ObservableList<Modalidade> listaM = FXCollections.observableArrayList();

	private ObservableList<Atividades> listaA = FXCollections.observableArrayList();
	
	private ObservableList<Pacotes> listaP = FXCollections.observableArrayList();
	
	private ObservableList<Contrato> listaC = FXCollections.observableArrayList();
	
	private ObservableList<Atividades> listaARefinado = FXCollections.observableArrayList();

	public ObservableList<Modalidade> getListaM() {
		return listaM;
	}

	public ObservableList<Atividades> getListaA() {
		return listaA;
	}
	
	public ObservableList<Pacotes> getListaP() {
		return listaP;
	}

	public ObservableList<Contrato> getListaC() {
		return listaC;
	}
	
	public ObservableList<Atividades> getListaARefinado() {
		return listaARefinado;
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
	
	public void vincularPacotes(ParticiparModalidade pm) {
		treDAO.vincularPacoteContrato(pm);
	}

	public Modalidade buscaTableMod() {

		List<Modalidade> mod = treDAO.buscaTableMod();
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

		List<Atividades> ativ = treDAO.buscaTableAtiv();
		listaA.addAll(ativ);

		return listaA.get(0);
	}

	public Pacotes buscarPacote(long idMod) {

		Pacotes p = treDAO.buscarPacote(idMod);
		return p;

	}
	
	public Pacotes buscarTablePct() {

		List<Pacotes> pct = treDAO.tablePct();
		listaP.addAll(pct);

		return listaP.get(0);
	}
	
	public Contrato buscarTableCon() {

		List<Contrato> con = treDAO.tableCon();
		listaC.addAll(con);

		return listaC.get(0);
	}
		
	public Atividades buscarTableAtivRefinado(long idMod) {

		List<Atividades> aref = treDAO.buscaTableAtivRefinado(idMod);
		listaARefinado.addAll(aref);

		return listaARefinado.get(0);
	}

	public void mostrarTodasModalidades() {
	}

	public void mostrarTodasAtividades() {
	}

	public void mostrarTodosPacotes() {
	}

}
