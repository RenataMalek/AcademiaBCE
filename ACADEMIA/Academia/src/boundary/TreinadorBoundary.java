package boundary;

import academia.Atividades;
import academia.Modalidade;
import control.TreinadorControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TreinadorBoundary implements EventHandler<ActionEvent> {

	private TreinadorControl control = new TreinadorControl();

	Button novaAtividadeButton = new Button("Adicionar");
	Button buscarAtividButton = new Button("Buscar");
	Button mostrarTodasAtivButton = new Button("Exibir todos");

	Button novaModalidadeButton = new Button("Adicionar");
	Button buscarModButton = new Button("Buscar");
	Button mostrarTodasModButton = new Button("Exibir todos");
	Button pacote = new Button("Pacote");

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

		Button atividade = new Button("Atividade");
		atividade.relocate(220, 80);
		atividade.setMinWidth(200);
		atividade.setOnMouseClicked(event -> atividade());

		Button modalidade = new Button("Modalidade");
		modalidade.relocate(220, 140);
		modalidade.setMinWidth(200);
		modalidade.setOnMouseClicked(event -> modalidade());

		pacote.relocate(220, 200);
		pacote.setMinWidth(200);
		// novoPacote.setOnMouseClicked(event -> pacote());

		Button atribuirContrato = new Button("Atribuir Pacotes");
		atribuirContrato.relocate(220, 260);
		atribuirContrato.setMinWidth(200);
		// atribuirContrato.setOnMouseClicked(event -> atribuirContrato());

		Button voltar = new Button("Voltar");
		voltar.relocate(220, 320);
		voltar.setMinWidth(200);
		voltar.setOnMouseClicked(event -> voltarInicio());

		pane.getChildren().add(atividade);
		pane.getChildren().add(modalidade);
		pane.getChildren().add(pacote);
		pane.getChildren().add(atribuirContrato);
		pane.getChildren().add(voltar);

		stage.setScene(scene);
		stage.setTitle("Treinador");
		stage.show();
	}

	public void atividade() {
		Pane pane = new Pane();

		Scene scene = new Scene(pane, 700, 500);

		novaAtividadeButton.relocate(300, 50);
		novaAtividadeButton.setMinWidth(200);

		buscarAtividButton.relocate(300, 110);
		buscarAtividButton.setMinWidth(200);

		mostrarTodasAtivButton.relocate(300, 180);
		mostrarTodasAtivButton.setMinWidth(200);

		Button voltarButton = new Button("Voltar");
		voltarButton.relocate(300, 250);
		voltarButton.setMinWidth(200);
		voltarButton.setOnMouseClicked(event -> voltar());

		pane.getChildren().add(novaAtividadeButton);
		pane.getChildren().add(buscarAtividButton);
		pane.getChildren().add(mostrarTodasAtivButton);
		pane.getChildren().add(voltarButton);

		novaAtividadeButton.setOnAction(this);
		buscarAtividButton.setOnAction(this);
		mostrarTodasAtivButton.setOnAction(this);

		Label id = new Label("ID: ");
		id.relocate(50, 50);
		txtID.relocate(110, 50);

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

	public Atividades boundaryToEntityAtiv() {

		Atividades a = new Atividades();

		try {

			a.setID(Long.parseLong(txtID.getText()));
			a.setNome(txtNome.getText());
			a.setQtdSecao(Integer.parseInt(txtQtdSecao.getText()));
			a.setQtdRepeticao(Integer.parseInt(txtQtdRepeticao.getText()));
			a.setTempoDuracao(Double.parseDouble(txtTempoDuracao.getText()));

		} catch (Exception ex) {
			System.out.println("Erro ao receber os dados");
		}
		return a;
	}

	public void entityToBoundaryAtiv(Atividades a) {

		txtID.setText(String.valueOf(a.getID()));
		txtNome.setText(String.valueOf(a.getNome()));
		txtQtdSecao.setText(String.valueOf(a.getQtdSecao()));
		txtQtdRepeticao.setText(String.valueOf(a.getQtdRepeticao()));
		txtTempoDuracao.setText(String.valueOf(a.getTempoDuracao()));
	}

	public void modalidade() {

		Pane pane = new Pane();

		Scene scene = new Scene(pane, 700, 500);

		novaModalidadeButton.relocate(400, 50);
		novaModalidadeButton.setMinWidth(200);
		novaModalidadeButton.setOnMouseClicked(event -> boundaryToEntityMod());

		buscarModButton.relocate(400, 110);
		buscarModButton.setMinWidth(200);

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

		novaModalidadeButton.setOnAction(this);
		buscarModButton.setOnAction(this);
		mostrarTodasModButton.setOnAction(this);

		Label id = new Label("ID: ");
		id.relocate(50, 50);
		txtID.relocate(130, 50);

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

	public Modalidade boundaryToEntityMod() {

		Modalidade m = new Modalidade();

		try {
			m.setID(Long.parseLong(txtID.getText()));
			m.setNivel(txtNivel.getText());
			m.setTipo(txtTipo.getText());
			m.setQtdAtividades(Integer.parseInt(txtQtdAtividades.getText()));

		} catch (Exception ex) {
			System.out.println("Erro ao receber os dados");
		}
		return m;
	}

	public void entityToBoundaryMod(Modalidade m) {

		txtID.setText(String.valueOf(m.getID()));
		txtTipo.setText(String.valueOf(m.getTipo()));
		txtNivel.setText(String.valueOf(m.getNivel()));
		txtQtdAtividades.setText(String.valueOf(m.getQtdAtividades()));

	}

	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == novaAtividadeButton) {
			Atividades a = boundaryToEntityAtiv();
			control.criarAtividades(a);
			;
		} else if (e.getTarget() == buscarAtividButton) {
			try {
				long idAtiv = Long.parseLong(txtID.getText());
				Atividades a = control.buscarAtividade(idAtiv);
				entityToBoundaryAtiv(a);
			} catch (Exception f) {
				System.out.println("Atividade nao existe");
			}
		} else if (e.getTarget() == novaModalidadeButton) {
			Modalidade m = boundaryToEntityMod();
			control.criarModalidades(m);
		} else if (e.getTarget() == buscarModButton) {
			long idMod = Long.parseLong(txtID.getText());
			Modalidade m = control.buscarModalidade(idMod);
			entityToBoundaryMod(m);
		}

	}

	public void voltar() {
		new TreinadorBoundary(stage);
	}

	public void voltarInicio() {
		new LoginBoundary(stage);
	}
}
