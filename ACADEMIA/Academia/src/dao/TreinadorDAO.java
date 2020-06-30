package dao;

import java.util.List;

import academia.Atividades;
import academia.Contrato;
import academia.Modalidade;
import academia.Pacotes;
import academia.ParticiparModalidade;

public interface TreinadorDAO {

	void criarModalidades(Modalidade m);

	void criarAtividades(Atividades a);

	void montarPacote(Pacotes p);

	Modalidade buscarModalidade(long idMod);
	List<Modalidade> buscaTableMod();

	Atividades buscarAtividade(long idAtiv);
	List<Atividades> buscaTableAtiv();
	List<Atividades> buscaTableAtivRefinado(long idMod);

	Pacotes buscarPacote(long idMod);
	List<Pacotes> tablePct();
	
	List<Contrato> tableCon();
	
	void vincularPacoteContrato(ParticiparModalidade pm);


}
