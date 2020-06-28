package dao;

import academia.Atividades;
import academia.Modalidade;
import academia.Pacotes;
import academia.Treinador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public Pacotes buscarPacote(long idPac) {

		try {

			String sql = "SELECT * FROM atividades_treino WHERE ID_PACOTE like ?";

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, "%" + idPac + "%");
			ResultSet rs = stm.executeQuery();

			Pacotes p = new Pacotes();

			if (rs.next()) {
				p.setIdModalidade(rs.getLong("ID_TREINO"));
				p.setIdAtividade(rs.getLong("ID_ATIVIDADE"));

				return p;
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
}
