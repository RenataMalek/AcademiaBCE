package dao;

import java.util.List;

import academia.Atividades;
import academia.Modalidade;
import academia.Pacotes;

public interface TreinadorDAO {

	void criarModalidades(Modalidade m);

	void criarAtividades(Atividades a);

	void montarPacote(Pacotes p);

	Modalidade buscarModalidade(long idMod);
	List<Modalidade> buscaTableMod(long idMod);

	Atividades buscarAtividade(long idAtiv);
	List<Atividades> buscaTableAtiv(long idAtiv);

	Pacotes buscarPacote(long idMod);

}
