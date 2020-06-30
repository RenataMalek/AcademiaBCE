package dao;

import academia.Atividades;
import academia.Contrato;
import academia.Modalidade;
import academia.Pacotes;
import academia.ParticiparModalidade;
import academia.Treinador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreinadorDAOImp implements TreinadorDAO {

	private Connection connection;

	public TreinadorDAOImp() {
		try {
			this.connection = new ConexaoDAO().getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Treinador buscarPorCodigoAcesso(String codigoAcesso) throws SQLException {
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from treinador where codigoAcesso = ?");
		preparedStatement.setString(1, codigoAcesso);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			Treinador treinador = new Treinador(resultSet.getLong("id_treinador"), resultSet.getString("nome"),
					resultSet.getString("cpf"), resultSet.getString("telefone"), resultSet.getString("codigoAcesso"));
			return treinador;
		} else {
			return null;
		}
	}

	@Override
	public void criarModalidades(Modalidade m) {

		try {

			String sql = "INSERT INTO treino (ID_TREINO, TIPO_TREINO, NIVEL_TREINO, QTD_ATIVD_TREINO)"
					+ "VALUES (0, ?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, m.getTipo());
			stm.setString(2, m.getNivel());
			stm.setInt(3, m.getQtdAtividades());

			stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void criarAtividades(Atividades a) {

		try {

			String sql = "INSERT INTO atividades (ID_ATIVIDADE, NOME_ATIVIDADE, QTD_SECAO_ATIVIDADE, QTD_REPETICAO_ATIVIDADE, TEMPO_DURACAO_ATIVIDADE)"
					+ "VALUES (0, ?, ?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, a.getNome());
			stm.setInt(2, a.getQtdSecao());
			stm.setInt(3, a.getQtdRepeticao());
			stm.setDouble(4, a.getTempoDuracao());

			stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void montarPacote(Pacotes p) {
		try {
			String sql = "INSERT INTO atividades_treino (ID_TREINO_FK, ID_ATIVIDADE_FK)" + "VALUES (?, ?)";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setLong(1, p.getIdModalidade());
			stm.setLong(2, p.getIdAtividade());
			stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Modalidade buscarModalidade(long idMod) {

		try {

			String sql = "SELECT * FROM treino WHERE ID_TREINO like ?";

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, "%" + idMod + "%");
			ResultSet rs = stm.executeQuery();

			Modalidade m = new Modalidade();

			if (rs.next()) {
				m.setID(rs.getLong("ID_TREINO"));
				m.setTipo(rs.getString("TIPO_TREINO"));
				m.setNivel(rs.getString("NIVEL_TREINO"));
				m.setQtdAtividades(rs.getInt("QTD_ATIVD_TREINO"));

				return m;
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Modalidade> buscaTableMod() {

		String sqlM = "SELECT * FROM treino";

		List<Modalidade> listaM = new ArrayList<Modalidade>();
		try {

			PreparedStatement stm = connection.prepareStatement(sqlM);

			ResultSet rsM = stm.executeQuery();

			while (rsM.next()) {
				Modalidade m = new Modalidade();
				m.setID(rsM.getLong("ID_TREINO"));
				m.setTipo(rsM.getString("TIPO_TREINO"));
				m.setNivel(rsM.getString("NIVEL_TREINO"));
				m.setQtdAtividades(rsM.getInt("QTD_ATIVD_TREINO"));
				listaM.add(m);
			}

			// connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaM;

	}

	@Override
	public Atividades buscarAtividade(long idAtiv) {
		try {

			String sql = "SELECT * FROM atividades WHERE ID_ATIVIDADE like ?";

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, "%" + idAtiv + "%");
			ResultSet rs = stm.executeQuery();

			Atividades a = new Atividades();

			if (rs.next()) {
				a.setID(rs.getLong("ID_ATIVIDADE"));
				a.setNome(rs.getString("NOME_ATIVIDADE"));
				a.setQtdSecao(rs.getInt("QTD_SECAO_ATIVIDADE"));
				a.setQtdRepeticao(rs.getInt("QTD_REPETICAO_ATIVIDADE"));
				a.setTempoDuracao(rs.getDouble("TEMPO_DURACAO_ATIVIDADE"));

				return a;
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Atividades> buscaTableAtiv() {

		List<Atividades> lista = new ArrayList<Atividades>();

		try {

			String sql = "SELECT * FROM atividades";

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Atividades a = new Atividades();
				a.setID(rs.getLong("ID_ATIVIDADE"));
				a.setNome(rs.getString("NOME_ATIVIDADE"));
				a.setQtdSecao(rs.getInt("QTD_SECAO_ATIVIDADE"));
				a.setQtdRepeticao(rs.getInt("QTD_REPETICAO_ATIVIDADE"));
				a.setTempoDuracao(rs.getDouble("TEMPO_DURACAO_ATIVIDADE"));

				lista.add(a);
			}

			// connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public List<Atividades> buscaTableAtivRefinado(long idMod) {

		List<Atividades> lista = new ArrayList<Atividades>();

		try {

			String sql = "SELECT ATV.ID_ATIVIDADE,ATV.NOME_ATIVIDADE,ATV.QTD_SECAO_ATIVIDADE,ATV.QTD_REPETICAO_ATIVIDADE,ATV.TEMPO_DURACAO_ATIVIDADE FROM ATIVIDADES_TREINO ATR\n"
					+ "INNER JOIN ATIVIDADES ATV ON (ATR.ID_ATIVIDADE_FK = ATV.ID_ATIVIDADE)\n"
					+ "WHERE ATR.ID_TREINO_FK = ?";

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setLong(1, idMod);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {

				Atividades a = new Atividades();
				a.setID(rs.getLong("ID_ATIVIDADE"));
				a.setNome(rs.getString("NOME_ATIVIDADE"));
				a.setQtdSecao(rs.getInt("QTD_SECAO_ATIVIDADE"));
				a.setQtdRepeticao(rs.getInt("QTD_REPETICAO_ATIVIDADE"));
				a.setTempoDuracao(rs.getDouble("TEMPO_DURACAO_ATIVIDADE"));

				lista.add(a);
			}

			
			System.out.println(lista);
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public Pacotes buscarPacote(long idMod) {

		try {

			String sql = "SELECT * FROM atividades_treino WHERE ID_TREINO_FK like ?";

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, "%" + idMod + "%");
			ResultSet rs = stm.executeQuery();

			Pacotes p = new Pacotes();

			if (rs.next()) {
				p.setIdModalidade(rs.getLong("ID_TREINO_FK"));
				p.setIdAtividade(rs.getLong("ID_ATIVIDADE_FK"));

				return p;
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Pacotes> tablePct() {

		List<Pacotes> lista = new ArrayList<Pacotes>();

		try {

			String sql = "SELECT distinct ID_TREINO_FK FROM atividades_treino";

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Pacotes p = new Pacotes();
				p.setIdModalidade(rs.getLong("ID_TREINO_FK"));

				lista.add(p);
			}
			// connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;

	}

	@Override
	public List<Contrato> tableCon() {

		List<Contrato> lista = new ArrayList<Contrato>();

		try {

			String sql = "SELECT * FROM contrato";

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Contrato c = new Contrato();
				c.setID(rs.getLong("ID_CONTRATO"));
				c.setCpf_cli(rs.getString("CPF_CLIENTE_FK"));
				c.setDataContrato(rs.getDate("DATA_CONTRATO").toLocalDate());
				c.setValorMes(rs.getDouble("VALOR_MENSAL_CONTRATO"));
				c.setQtdParcelas(rs.getInt("PARCELAS"));
				c.setQtdTreinos(rs.getInt("QTD_TREINOS"));
				lista.add(c);

			}
			// connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void vincularPacoteContrato(ParticiparModalidade pm) {

		try {
			String sql = "INSERT INTO participacao_treino (ID_CONTRATO_FK, ID_TREINO_FK)" + "VALUES (?, ?)";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setLong(1, pm.getIdContrato());
			stm.setLong(2, pm.getIdModalidade());

			stm.executeUpdate();

			atualizarContrato(pm.getIdContrato());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizarContrato(long idContrato) {

		int cont;

		try {

			String sql = "SELECT * FROM contrato WHERE ID_CONTRATO LIKE ?";
			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, "%" + idContrato + "%");
			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				cont = rs.getInt("QTD_TREINOS");
				sql = "UPDATE contrato SET QTD_TREINOS = ?  WHERE ID_CONTRATO = ?";
				PreparedStatement stmU = connection.prepareStatement(sql);
				stmU.setInt(1, cont);
				stmU.setLong(2, idContrato);
				stmU.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
