package control;

import academia.Cliente;
import academia.Recepcionista;
import academia.Treinador;
import dao.ClienteDAO;
import dao.RecepcionistaDAO;
import dao.TreinadorDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginControl {

	private final String RECEPCIONISTA = "Recepcionista";

	private final String TREINADOR = "Treinador";

	private final String CLIENTE = "Cliente";

	public List<String> buscaTiposAcesso() {
		List<String> tiposAcesso = new ArrayList<>();
		tiposAcesso.add(RECEPCIONISTA);
		tiposAcesso.add(TREINADOR);
		tiposAcesso.add(CLIENTE);
		return tiposAcesso;
	}

	public boolean logar(String acesso, String tipoAcesso) throws SQLException {
		if (tipoAcesso.equals(RECEPCIONISTA)) {
			// essa aqui é a busca no banco ->>> RecepcionistaDAO recepcionistaDAO = new
			// RecepcionistaDAO();
			// essa aqui é a busca no banco ->>> Recepcionista recepcionista =
			// recepcionistaDAO.buscarPorCodigoAcesso(acesso);

			Recepcionista r = new Recepcionista();
			return validarObjetoNaoNulo(r.getCodigoAcesso().equals(acesso));

		} else if (tipoAcesso.equals(TREINADOR)) {
			// essa aqui é a busca no banco ->>> TreinadorDAO treinadorDAO = new
			// TreinadorDAO();
			// essa aqui é a busca no banco ->>> Treinador treinador =
			// treinadorDAO.buscarPorCodigoAcesso(acesso);

			Treinador t = new Treinador();

			return validarObjetoNaoNulo(t.getCodigoAcesso().equals(acesso));
		} else {
			// essa aqui é a busca no banco ->>> ClienteDAO clienteDAO = new ClienteDAO();
			// essa aqui é a busca no banco ->>> Cliente cliente = clienteDAO.buscarClientePorCpf(acesso);
			
			Cliente c = new Cliente();
			
			return validarObjetoNaoNulo(c.getCPF().equals(acesso));
		}
	}

	private boolean validarObjetoNaoNulo(Object object) {
		return object != null;
	}
}
