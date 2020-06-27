package control;

import java.util.ArrayList;
import java.util.List;

import academia.Atividades;
import academia.Modalidade;
import academia.Pacotes;

public class TreinadorControl {

	// remover iniciador quando montar o banco de dados; substituir por increment ID
	long idModalidade = 0;
	long idAtividade = 0;
	long idPacote = 0;
	long idPtcp = 0;

	public long getIDAtiv() {
		return idAtividade;
	}
	
	public long getIDMod() {
		return idModalidade;
	}
	
	public long getIDPacote() {
		return idPacote;
	}
	
	public long getIDPtcp() {
		return idPtcp;
	}
	
	List<Modalidade> modalidade = new ArrayList<Modalidade>();
	List<Atividades> atividades = new ArrayList<Atividades>();
	List<Pacotes> pacote = new ArrayList<Pacotes>();

	public void criarModalidades(String nivel, String tipo, int qtdAtividades) {

		Modalidade m = new Modalidade();
		m.setID(idModalidade);
		m.setNivel(nivel);
		m.setTipo(tipo);

		// qtdAtividades pode sair não pode nao???

		idModalidade++;
		modalidade.add(m);
	}

	public void criarAtividades(String nome, int qtdSecao, int qtdRepeticao, double tempoDuracao) {
		Atividades a = new Atividades();
		a.setID(idAtividade);
		a.setNome(nome);
		a.setQtdSecao(qtdSecao);
		a.setQtdRepeticao(qtdRepeticao);
		a.setTempoDuracao(tempoDuracao);

		idAtividade++;
		atividades.add(a);

	}

	public void montarPacotes() {
		Pacotes p = new Pacotes();
		p.setIdPacote(idPacote);
		idPacote++;

		long idMod = 0; // codificar para receber o id da modalidade pelo usuario

		Modalidade m = buscarModalidade(idMod);
		p.setModalidade(m);

		// criar um loop para ir inserindo atividades enquanto o usuario for clicando em
		// + na tela

		long idAtiv = 0; // codificar para receber o id das atividades pelo usuario;
		Atividades a = buscarAtividade(idAtiv);
		p.setAtividade(a);

		// encerra loop de inserção de atividades quando o cliente terminar de inserir

		pacote.add(p);
	}

	public Modalidade buscarModalidade(long idMod) {
		int cont = 0;
		for (Modalidade m : modalidade) {
			if (m.getID() == idMod) {
				cont++;
				return m;
			}
		}
		if (cont == 0) {
			System.out.println("Modalidade não cadastrada");
		}

		return null;
	}

	public Atividades buscarAtividade(long idAtiv) {

		int cont = 0;
		for (Atividades a : atividades) {
			if (a.getID() == idAtiv) {
				cont++;
				return a;
			}
		}
		if (cont == 0) {
			System.out.println("Atividade não cadastrada");
		}
		return null;
	}

	public Pacotes buscarPacote(long idPact) {
		int cont = 0;

		for (Pacotes p : pacote) {
			if (p.getIdPacote() == idPact) {
				cont++;
				return p;
			}
		}

		if (cont == 0) {
			System.out.println("Pacote não existe");
		}

		return null;
	}

	public void mostrarTodasModalidades() {
		for (Modalidade m : modalidade) {
			System.out.println(m);
		}
	}

	public void mostrarTodasAtividades() {
		for (Atividades a : atividades) {
			System.out.println(a);
		}
	}

	public void mostrarTodosPacotes() {
		for (Pacotes p : pacote) {
			System.out.println(p);
		}
	}

}
