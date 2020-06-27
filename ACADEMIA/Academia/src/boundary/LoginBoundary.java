package boundary;

import javax.swing.JOptionPane;


import control.ClienteControl;
import control.LoginControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginBoundary {

	private Stage stage;

	private final LoginControl loginControl = new LoginControl();

	private TextField acesso;

	private ComboBox<String> tipoAcesso;

	public LoginBoundary(Stage stage) {
		this.stage = stage;
		construirTela(stage);
	}

	private void construirTela(Stage stage) {
		Pane pane = new Pane();

		Scene scene = new Scene(pane, 700, 500);

		Label bemVindo = new Label("Bem vindo!");
		bemVindo.relocate(260, 60);
		bemVindo.setMinWidth(170);
		bemVindo.setAlignment(Pos.CENTER);
		
		Label informacao = new Label("Digite seu codigo de acesso e escolha o usuario:");
		informacao.relocate(200, 90);
		informacao.setAlignment(Pos.CENTER);
		
		acesso = new TextField();
		acesso.relocate(260, 150);
		acesso.setMinWidth(170);

		
		tipoAcesso = new ComboBox<>();
		tipoAcesso.relocate(260, 220);
		tipoAcesso.setMinWidth(170);
		carregarComboTipoUsuarios();

		
		Button clienteButton = new Button("Logar");
		clienteButton.relocate(260, 290);
		clienteButton.setMinWidth(170);
		clienteButton.setOnMouseClicked(event -> logar());

		pane.getChildren().add(bemVindo);
		pane.getChildren().add(informacao);
		pane.getChildren().add(acesso);
		pane.getChildren().add(tipoAcesso);
		pane.getChildren().add(clienteButton);
		
		
		stage.setScene(scene);
		stage.setTitle("Academia Login");
		stage.show();
	}

	private void carregarComboTipoUsuarios() {
		ObservableList<String> observableList = FXCollections.observableList(loginControl.buscaTiposAcesso());
		tipoAcesso.setItems(observableList);
	}

	private void logar() {
		String tipoAcessoSelecionado = tipoAcesso.getSelectionModel().getSelectedItem();
		if (!acesso.getText().equals("") && tipoAcessoSelecionado != null && !tipoAcessoSelecionado.equals("")) {

			try {
				boolean logado = loginControl.logar(acesso.getText(), tipoAcessoSelecionado);
				if (logado) {

					if (tipoAcessoSelecionado.equalsIgnoreCase("recepcionista")) {
						new RecepcionistaBoundary(stage);
					} else if (tipoAcessoSelecionado.equalsIgnoreCase("treinador")) {
						new TreinadorBoundary(stage);
					} else if (tipoAcessoSelecionado.equalsIgnoreCase("cliente")) {

					/*	String CPF = acesso.getText();
						ClienteControl cc = new ClienteControl();
						if (cc.entrarAcademia(CPF)) {
							JOptionPane.showMessageDialog(null, "Entrada Liberada!!");
						} else {
							JOptionPane.showMessageDialog(null, "Problemas na liberação, procure a recepcionista");
						}

						new LoginBoundary(stage); */
					
						System.out.println("Bem vindo cliente");
						
					}

				} else {
					System.out.println("Código de acesso invalido");
				}
			} catch (Exception exception) {
				System.out.println("Erro ao fazer login!");
			}

		} else {
			System.out.println("preencha todos os campos para logar!");
		}
	}
}
