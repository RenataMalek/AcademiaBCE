package boundary;

import academia.Atividades;
import academia.Modalidade;
import academia.Pacotes;
import control.TreinadorControl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TreinadorBoundary implements EventHandler<ActionEvent> {

	TreinadorControl tc = new TreinadorControl();

	private Stage stage;

	private TableView<Modalidade> tableViewMod = new TableView<>(tc.getListaM());
	private TableView<Atividades> tableViewAtiv = new TableView<>(tc.getListaA());

	Button novaAtividadeButton = new Button("Adicionar");
	Button buscarAtividButton = new Button("Buscar");
	Button mostrarTodasAtivButton = new Button("Exibir todos");

	Button novaModalidadeButton = new Button("Adicionar");
	Button buscarModButton = new Button("Buscar");
	Button mostrarTodasModButton = new Button("Exibir todos");

	Button novoPacoteButton = new Button("Adicionar");
	Button buscarPacoteButton = new Button("Buscar");
	Button mostrarTodosPacButton = new Button("Exibir todos");

	TextField txtID = new TextField();
	TextField txtNome = new TextField();
	TextField txtQtdSecao = new TextField();
	TextField txtQtdRepeticao = new TextField();
	TextField txtTempoDuracao = new TextField();

	TextField txtAvisos = new TextField();

	TextField txtTipo = new TextField();
	TextField txtNivel = new TextField();
	TextField txtQtdAtividades = new TextField();

	TextField txtPcMod = new TextField();
	TextField txtPcAtiv = new TextField();

	public void generateTableMod() {

		TableColumn<Modalidade, Long> colIDM = new TableColumn<>("IDM");
		colIDM.setCellValueFactory(new PropertyValueFactory<Modalidade, Long>("ID"));

		TableColumn<Modalidade, String> colTipo = new TableColumn<>("Tipo");
		colTipo.setCellValueFactory(new PropertyValueFactory<Modalidade, String>("tipo"));

		TableColumn<Modalidade, String> colNivel = new TableColumn<>("Nivel");
		colNivel.setCellValueFactory(new PropertyValueFactory<Modalidade, String>("nivel"));

		TableColumn<Modalidade, Integer> colQtdAtiv = new TableColumn<>("QtdAtividades");
		colQtdAtiv.setCellValueFactory(new PropertyValueFactory<Modalidade, Integer>("qtdAtividades"));

		tableViewMod.getColumns().addAll(colIDM, colTipo, colNivel, colQtdAtiv);
		tableViewMod.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Modalidade>() {

			@Override
			public void changed(ObservableValue<? extends Modalidade> m, Modalidade antigo, Modalidade novo) {
				entityToBoundaryTBMod(novo);
			}

		});
	}

	public void generateTableAtiv() {

		TableColumn<Atividades, Integer> colIDA = new TableColumn<>("IDA");
		colIDA.setCellValueFactory(new PropertyValueFactory<Atividades, Integer>("ID"));

		TableColumn<Atividades, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<Atividades, String>("Nome"));

		TableColumn<Atividades, Integer> colQtdSecao = new TableColumn<>("QtdSecao");
		colQtdSecao.setCellValueFactory(new PropertyValueFactory<Atividades, Integer>("qtdSecao"));

		TableColumn<Atividades, Integer> colQtdRepeticao = new TableColumn<>("QtdRepeticao");
		colQtdRepeticao.setCellValueFactory(new PropertyValueFactory<Atividades, Integer>("qtdRepeticao"));

		TableColumn<Atividades, Double> colTempo = new TableColumn<>("TempoDuracao");
		colTempo.setCellValueFactory(new PropertyValueFactory<Atividades, Double>("tempoDuracao"));

		tableViewAtiv.getColumns().addAll(colIDA, colNome, colQtdSecao, colQtdRepeticao, colTempo);
		tableViewAtiv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Atividades>() {

			@Override
			public void changed(ObservableValue<? extends Atividades> a, Atividades antigo, Atividades novo) {
				entityToBoundaryTBAtiv(novo);
			}

		});

	}

	public TreinadorBoundary(Stage stage) {
		this.stage = stage;
		construirTela(stage);
	}

	private void construirTela(Stage stage) {
		Pane pane = new Pane();
		
		Image image =new Image("file:images/Verde_fundo.png");
		ImageView mv=new ImageView(image);
				
		Group root=new Group();
		root.getChildren().addAll(mv);
					
		pane.getChildren().addAll(root);

		Scene scene = new Scene(pane, 700, 500);

		Button atividade = new Button("Atividade");
		atividade.relocate(220, 80);
		atividade.setMinWidth(200);
		atividade.setOnMouseClicked(event -> atividade());

		Button modalidade = new Button("Modalidade");
		modalidade.relocate(220, 140);
		modalidade.setMinWidth(200);
		modalidade.setOnMouseClicked(event -> modalidade());

		Button pacote = new Button("Pacote");
		pacote.relocate(220, 200);
		pacote.setMinWidth(200);
		pacote.setOnMouseClicked(event -> pacote());

		Button atribuirContrato = new Button("Atribuir Pacotes");
		atribuirContrato.relocate(220, 260);
		atribuirContrato.setMinWidth(200);
		// atribuirContrato.setOnMouseClicked(event -> atribuirContrato());

		Button voltar = new Button("Voltar");
		voltar.relocate(220, 320);
		voltar.setMinWidth(200);
		voltar.setOnMouseClicked(event -> voltarInicio());

		atividade.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
		modalidade.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
		pacote.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
		atribuirContrato.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
		voltar.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
	
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

	public void pacote() {

		Pane pane = new Pane();

		generateTableMod();
		tc.buscaTableMod();

		generateTableAtiv();
		tc.buscaTableAtiv();

		Scene scene = new Scene(pane, 800, 600);

		novoPacoteButton.relocate(295, 10);
		novoPacoteButton.setMinWidth(200);

		buscarPacoteButton.relocate(295, 70);
		buscarPacoteButton.setMinWidth(200);

		mostrarTodosPacButton.relocate(295, 130);
		mostrarTodosPacButton.setMinWidth(200);

		Button voltarButton = new Button("Voltar");
		voltarButton.relocate(295, 190);
		voltarButton.setMinWidth(200);
		voltarButton.setOnMouseClicked(event -> voltar());

		pane.getChildren().add(novoPacoteButton);
		pane.getChildren().add(buscarPacoteButton);
		pane.getChildren().add(mostrarTodosPacButton);
		pane.getChildren().add(voltarButton);

		novoPacoteButton.setOnAction(this);
		buscarPacoteButton.setOnAction(this);
		mostrarTodosPacButton.setOnAction(this);

		Label pctMod = new Label("Modalidade: ");
		pctMod.relocate(15, 70);
		txtPcMod.relocate(90, 70);

		Label pctAtiv = new Label("Atividade: ");
		pctAtiv.relocate(15, 130);
		txtPcAtiv.relocate(90, 130);

		tableViewMod.relocate(15, 295);
		tableViewMod.setMaxSize(400, 300);

		tableViewAtiv.relocate(400, 295);
		tableViewAtiv.setMaxSize(400, 300);

		pane.getChildren().add(pctMod);
		pane.getChildren().add(txtPcMod);
		pane.getChildren().add(pctAtiv);
		pane.getChildren().add(txtPcAtiv);
		pane.getChildren().add(tableViewMod);
		pane.getChildren().add(tableViewAtiv);

		stage.setScene(scene);
		stage.setTitle("Novas Atividades");
		stage.show();

	}

	public Pacotes boundaryToEntityPacote() {

		Pacotes p = new Pacotes();

		try {

			p.setIdModalidade(Long.parseLong(txtPcMod.getText()));
			p.setIdAtividade(Long.parseLong(txtPcAtiv.getText()));

		} catch (Exception ex) {
			System.out.println("Erro ao receber os dados");
		}
		return p;
	}

	public void entityToBoundaryPacote(Pacotes p) {

		txtPcMod.setText(String.valueOf(p.getIdModalidade()));
		txtPcAtiv.setText(String.valueOf(p.getIdAtividade()));
	}

	public void entityToBoundaryTBMod(Modalidade m) {

		txtPcMod.setText(String.valueOf(m.getID()));
	}
	
	public void entityToBoundaryTBAtiv(Atividades a) {

		txtPcAtiv.setText(String.valueOf(a.getID()));
	}
	
	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == novaAtividadeButton) {
			Atividades a = boundaryToEntityAtiv();
			tc.criarAtividades(a);

		} else if (e.getTarget() == buscarAtividButton) {
			try {
				long idAtiv = Long.parseLong(txtID.getText());
				Atividades a = tc.buscarAtividade(idAtiv);
				entityToBoundaryAtiv(a);
			} catch (Exception f) {
				System.out.println("Atividade nao existe");
			}
		} else if (e.getTarget() == novaModalidadeButton) {
			Modalidade m = boundaryToEntityMod();
			tc.criarModalidades(m);
		} else if (e.getTarget() == buscarModButton) {
			long idMod = Long.parseLong(txtID.getText());
			Modalidade m = tc.buscarModalidade(idMod);
			entityToBoundaryMod(m);
		} else if (e.getTarget() == novoPacoteButton) {
			Pacotes p = boundaryToEntityPacote();
			tc.montarPacotes(p);

		} else if (e.getTarget() == buscarPacoteButton) {
			try {
				long idMod = Long.parseLong(txtPcMod.getText());
				Pacotes p = tc.buscarPacote(idMod);
				entityToBoundaryPacote(p);
			} catch (Exception g) {
				System.out.println("Pacote não existe");
			}
		}

	}

	public void voltar() {
		new TreinadorBoundary(stage);
	}

	public void voltarInicio() {
		new LoginBoundary(stage);
	}
}
