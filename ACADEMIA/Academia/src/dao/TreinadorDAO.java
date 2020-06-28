package dao;

import academia.Atividades;
import academia.Modalidade;
import academia.Pacotes;

public interface TreinadorDAO {

	void criarModalidades(Modalidade m);

	void criarAtividades(Atividades a);

	void montarPacote(Pacotes p);

	Modalidade buscarModalidade(long idMod);

	Atividades buscarAtividade(long idAtiv);

	Pacotes buscarPacote(long idPac);

}
