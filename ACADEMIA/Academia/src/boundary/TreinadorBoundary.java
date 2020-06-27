package boundary;

import javax.swing.JOptionPane;
import academia.Atividades;
import academia.Modalidade;
import control.TreinadorControl;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TreinadorBoundary {

	TextField txtID = new TextField();
	TextField txtNome = new TextField();
	TextField txtQtdSecao = new TextField();
	TextField txtQtdRepeticao = new TextField();
	TextField txtTempoDuracao = new TextField();

	TextField txtAvisos = new TextField();

	TextField txtTipo = new TextField();
	TextField txtNivel = new TextField();
	TextField txtQtdAtividades = new TextField();

	TreinadorControl tc = new TreinadorControl();

	private Stage stage;

	public TreinadorBoundary(Stage stage) {
		this.stage = stage;
		construirTela(stage);
	}

	private void construirTela(Stage stage) {
		Pane pane = new Pane();

		Scene scene = new Scene(pane, 700, 500);

		Button novaAtividade = new Button("Adicionar nova atividade");
		novaAtividade.relocate(220, 80);
		novaAtividade.setMinWidth(200);
		novaAtividade.setOnMouseClicked(event -> atividade());

		Button novaModalidade = new Button("Adicionar nova modalidade");
		novaModalidade.relocate(220, 140);
		novaModalidade.setMinWidth(200);
		novaModalidade.setOnMouseClicked(event -> modalidade());
		

		Button novoPacote = new Button("Criar Pacote");
		novoPacote.relocate(220, 200);
		novoPacote.setMinWidth(200);
		// novoPacote.setOnMouseClicked(event -> pacote());

		Button atribuirContrato = new Button("Atribuir Pacotes");
		atribuirContrato.relocate(220, 260);
		atribuirContrato.setMinWidth(200);
		// atribuirContrato.setOnMouseClicked(event -> atribuirContrato());

		Button voltar = new Button("Voltar");
		voltar.relocate(220, 320);
		voltar.setMinWidth(200);
		voltar.setOnMouseClicked(event -> voltarInicio());

		pane.getChildren().add(novaAtividade);
		pane.getChildren().add(novaModalidade);
		pane.getChildren().add(novoPacote);
		pane.getChildren().add(atribuirContrato);
		pane.getChildren().add(voltar);

		stage.setScene(scene);
		stage.setTitle("Treinador");
		stage.show();
	}

	public void atividade() {
		Pane pane = new Pane();

		Scene scene = new Scene(pane, 700, 500);

		Button novaAtividadeButton = new Button("Adicionar");
		novaAtividadeButton.relocate(300, 50);
		novaAtividadeButton.setMinWidth(200);
		novaAtividadeButton.setOnMouseClicked(event -> boundaryToEntityAtiv());

		Button buscarAtividButton = new Button("Buscar");
		buscarAtividButton.relocate(300, 110);
		buscarAtividButton.setMinWidth(200);
		buscarAtividButton.setOnMouseClicked(event -> entityToBoundaryAtiv(Long.parseLong(txtID.getText())));

		Button mostrarTodasAtivButton = new Button("Exibir todos");
		mostrarTodasAtivButton.relocate(300, 180);
		mostrarTodasAtivButton.setMinWidth(200);
		// mostrarTodasAtivButton.setOnMouseClicked(event -> mostratTodosAtiv());

		Button voltarButton = new Button("Voltar");
		voltarButton.relocate(300, 250);
		voltarButton.setMinWidth(200);
		voltarButton.setOnMouseClicked(event -> voltar());

		pane.getChildren().add(novaAtividadeButton);
		pane.getChildren().add(buscarAtividButton);
		pane.getChildren().add(mostrarTodasAtivButton);
		pane.getChildren().add(voltarButton);

		Label id = new Label("ID: ");
		id.relocate(50, 50);
		txtID.relocate(110, 50);
		txtID.setText(String.valueOf(tc.getIDAtiv()));

		Label nome = new Label("Nome: ");
		nome.relocate(50, 100);
		txtNome.relocate(110, 100);

		Label qtdSecao = new Label("Quantas seções: ");
		qtdSecao.relocate(50, 150);
		txtQtdSecao.relocate(110, 150);

		Label qtdRepeticao = new Label("Quantas repetições: ");
		qtdRepeticao.relocate(50, 200);
		txtQtdRepeticao.relocate(110, 200);

		Label tempoDuracao = new Label("Tempo duração: ");
		tempoDuracao.relocate(50, 250);
		txtTempoDuracao.relocate(110, 250);

		txtAvisos.relocate(300, 300);
		txtAvisos.setMinWidth(200);

		pane.getChildren().add(id);
		pane.getChildren().add(txtID);
		pane.getChildren().add(nome);
		pane.getChildren().add(txtNome);
		pane.getChildren().add(qtdSecao);
		pane.getChildren().add(txtQtdSecao);
		pane.getChildren().add(qtdRepeticao);
		pane.getChildren().add(txtQtdRepeticao);
		pane.getChildren().add(tempoDuracao);
		pane.getChildren().add(txtTempoDuracao);
		pane.getChildren().add(txtAvisos);
		stage.setScene(scene);
		stage.setTitle("Novas Atividades");
		stage.show();

	}

	public TreinadorControl boundaryToEntityAtiv() {
		try {

			String nome = txtNome.getText();
			int qtdSecao = Integer.parseInt(txtQtdSecao.getText());
			int qtdRepeticao = Integer.parseInt(txtQtdRepeticao.getText());
			double tempoDuracao = Double.parseDouble(txtTempoDuracao.getText());

			tc.criarAtividades(nome, qtdSecao, qtdRepeticao, tempoDuracao);

			JOptionPane.showMessageDialog(null, "cadastramos");

		} catch (Exception ex) {
			System.out.println("Erro ao receber os dados");
		}
		return tc;
	}

	public void entityToBoundaryAtiv(long idAtiv) {

		if (tc.buscarAtividade(idAtiv) != null) {

			Atividades a = tc.buscarAtividade(idAtiv);

			txtID.setText(String.valueOf(a.getID()));
			txtNome.setText(String.valueOf(a.getNome()));
			txtQtdSecao.setText(String.valueOf(a.getQtdSecao()));
			txtQtdRepeticao.setText(String.valueOf(a.getQtdRepeticao()));
			txtTempoDuracao.setText(String.valueOf(a.getTempoDuracao()));
		}

		else {
			txtAvisos.setText("Atividade não cadastrada");
		}
	}

	public void modalidade() {

		Pane pane = new Pane();

		Scene scene = new Scene(pane, 700, 500);

		Button novaModalidadeButton = new Button("Adicionar");
		novaModalidadeButton.relocate(400, 50);
		novaModalidadeButton.setMinWidth(200);
		novaModalidadeButton.setOnMouseClicked(event -> boundaryToEntityMod());

		Button buscarModButton = new Button("Buscar");
		buscarModButton.relocate(400, 110);
		buscarModButton.setMinWidth(200);
		buscarModButton.setOnMouseClicked(event -> entityToBoundaryMod(Long.parseLong(txtID.getText())));

		Button mostrarTodasModButton = new Button("Exibir todos");
		mostrarTodasModButton.relocate(400, 180);
		mostrarTodasModButton.setMinWidth(200);
		// mostrarTodasModButton.setOnMouseClicked(event -> mostrarTodosMod());

		Button voltarButton = new Button("Voltar");
		voltarButton.relocate(400, 250);
		voltarButton.setMinWidth(200);
		voltarButton.setOnMouseClicked(event -> voltar());

		pane.getChildren().add(novaModalidadeButton);
		pane.getChildren().add(buscarModButton);
		pane.getChildren().add(mostrarTodasModButton);
		pane.getChildren().add(voltarButton);

		Label id = new Label("ID: ");
		id.relocate(50, 50);
		txtID.relocate(130, 50);
		txtID.setText(String.valueOf(tc.getIDMod()));

		Label tipo = new Label("Tipo: ");
		tipo.relocate(50, 100);
		txtTipo.relocate(130, 100);

		Label nivel = new Label("Nivel: ");
		nivel.relocate(50, 150);
		txtNivel.relocate(130, 150);

		Label qtdAtividades = new Label("Qtd atividades: ");
		qtdAtividades.relocate(50, 200);
		txtQtdAtividades.relocate(130, 200);

		txtAvisos.relocate(400, 300);
		txtAvisos.setMinWidth(200);

		pane.getChildren().add(id);
		pane.getChildren().add(txtID);
		pane.getChildren().add(tipo);
		pane.getChildren().add(txtTipo);
		pane.getChildren().add(nivel);
		pane.getChildren().add(txtNivel);
		pane.getChildren().add(qtdAtividades);
		pane.getChildren().add(txtQtdAtividades);
		pane.getChildren().add(txtAvisos);
		stage.setScene(scene);
		stage.setTitle("Novas Modalidades");
		stage.show();

	}

	public TreinadorControl boundaryToEntityMod() {
		try {

			String nivel = txtNivel.getText();
			String tipo = txtTipo.getText();
			int qtdAtividades = Integer.parseInt(txtQtdAtividades.getText());

			tc.criarModalidades(nivel, tipo, qtdAtividades);

		} catch (Exception ex) {
			System.out.println("Erro ao receber os dados");
		}
		return tc;
	}

	public void entityToBoundaryMod(long idMod) {

		if (tc.buscarAtividade(idMod) != null) {

			Modalidade m = new Modalidade();

			txtID.setText(String.valueOf(m.getID()));
			txtTipo.setText(String.valueOf(m.getTipo()));
			txtNivel.setText(String.valueOf(m.getNivel()));
			txtQtdAtividades.setText(String.valueOf(m.getQtdAtividades()));
		}

		else {
			txtAvisos.setText("Modalidade não existe");
		}

	}

	public void voltar() {
		new TreinadorBoundary(stage);
	}
	
	public void voltarInicio() {
		new LoginBoundary(stage);
	}
}
