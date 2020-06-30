package control;

import academia.Cliente;
import academia.Recepcionista;
import academia.Treinador;
import dao.ClienteDAOImp;
import dao.RecepcionistaDAOImp;
import dao.TreinadorDAOImp;

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
			RecepcionistaDAOImp recepcionistaDAO = new RecepcionistaDAOImp();
			Recepcionista recepcionista = recepcionistaDAO.buscarPorCodigoAcesso(acesso);

			return validarObjetoNaoNulo(recepcionista.getCodigoAcesso().equals(acesso));

		} else if (tipoAcesso.equals(TREINADOR)) {
			TreinadorDAOImp treinadorDAO = new TreinadorDAOImp();
			Treinador treinador = treinadorDAO.buscarPorCodigoAcesso(acesso);


			return validarObjetoNaoNulo(treinador.getCodigoAcesso().equals(acesso));
		} else {
			ClienteDAOImp clienteDAO = new ClienteDAOImp();
			Cliente cliente = clienteDAO.buscarClientePorCpf(acesso);

			return validarObjetoNaoNulo(cliente.getCPF().equals(acesso));
		}
	}

	private boolean validarObjetoNaoNulo(Object object) {
		return object != null;
	}
}
