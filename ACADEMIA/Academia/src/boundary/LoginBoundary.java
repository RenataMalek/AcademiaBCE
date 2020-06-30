package boundary;

import javax.swing.JOptionPane;

import control.LoginControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

		Image image = new Image("file:images/logo_bx_op.png");
		ImageView mv = new ImageView(image);

		Image favicon = new Image("file:images/favicon.png");
		stage.getIcons().add(favicon);

		Group root = new Group();
		root.getChildren().addAll(mv);

		pane.getChildren().addAll(root);
		Scene scene = new Scene(pane, 700, 500);

		Label bemVindo = new Label("Bem vindo!");
		bemVindo.relocate(260, 60);
		bemVindo.setMinWidth(170);
		bemVindo.setAlignment(Pos.CENTER);

		Label informacao = new Label("Digite seu codigo de acesso e escolha o usuario:");
		informacao.relocate(200, 90);
		informacao.setAlignment(Pos.CENTER);

		acesso = new PasswordField();
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

		bemVindo.setStyle("-fx-font-size:30; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		informacao.setStyle("-fx-font-size:20; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		acesso.setStyle(
				"-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");
		tipoAcesso.setStyle(
				"-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");
		clienteButton.setStyle(
				"-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");

		pane.getChildren().add(bemVindo);
		pane.getChildren().add(informacao);
		pane.getChildren().add(acesso);
		pane.getChildren().add(tipoAcesso);
		pane.getChildren().add(clienteButton);

		stage.setScene(scene);
		stage.setTitle("Academia Login");
		stage.show();

		bemVindo.relocate((scene.getWidth() / 2) - (bemVindo.getWidth() / 2), 0);
		informacao.relocate((scene.getWidth() / 2) - (informacao.getWidth() / 2), 90);
		acesso.relocate((scene.getWidth() / 2) - (acesso.getWidth() / 2), 150);
		tipoAcesso.relocate((scene.getWidth() / 2) - (tipoAcesso.getWidth() / 2), 220);

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

						JOptionPane.showMessageDialog(null, "Entrada liberada!");
						new LoginBoundary(stage);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Código de acesso invalido");
				}
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null, "Erro ao fazer login!");
			}

		} else {
			JOptionPane.showMessageDialog(null, "preencha todos os campos para logar!");
		}
	}
}
