package boundary;

import javax.swing.JOptionPane;

import academia.Atividades;
import academia.Contrato;
import academia.Modalidade;
import academia.Pacotes;
import academia.ParticiparModalidade;
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
	private TableView<Pacotes> tableViewPct = new TableView<>(tc.getListaP());
	private TableView<Contrato> tableViewCon = new TableView<>(tc.getListaC());
	private TableView<Atividades> tableViewAtivRef = new TableView<>(tc.getListaARefinado());

	Button novaAtividadeButton = new Button("Adicionar");
	Button buscarAtividButton = new Button("Buscar");
	Button mostrarTodasAtivButton = new Button("Exibir todos");

	Button novaModalidadeButton = new Button("Adicionar");
	Button buscarModButton = new Button("Buscar");
	Button mostrarTodasModButton = new Button("Exibir todos");

	Button novoPacoteButton = new Button("Adicionar");
	Button buscarPacoteButton = new Button("Buscar");
	Button mostrarTodosPacButton = new Button("Exibir todos");

	Button atribuirPctButton = new Button("Vincular");

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

	TextField txtPacote = new TextField();
	TextField txtContrato = new TextField();

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

		tableViewAtivRef.getColumns().addAll(colIDA, colNome, colQtdSecao, colQtdRepeticao, colTempo);
		tableViewAtiv.getColumns().addAll(colIDA, colNome, colQtdSecao, colQtdRepeticao, colTempo);
		tableViewAtiv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Atividades>() {

			@Override
			public void changed(ObservableValue<? extends Atividades> a, Atividades antigo, Atividades novo) {
				entityToBoundaryTBAtiv(novo);
			}

		});

	}

	public void generateTableAtivRef() {

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

		tableViewAtivRef.getColumns().addAll(colIDA, colNome, colQtdSecao, colQtdRepeticao, colTempo);

	}

	public void generateTablePct() {

		TableColumn<Pacotes, Long> colIDM = new TableColumn<>("Codigo da modalidade");
		colIDM.setCellValueFactory(new PropertyValueFactory<Pacotes, Long>("idModalidade"));

		tableViewPct.getColumns().add(colIDM);
		tableViewPct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pacotes>() {

			@Override
			public void changed(ObservableValue<? extends Pacotes> p, Pacotes antigo, Pacotes novo) {
				entityToBoundaryTBPct(novo);
			}

		});

	}

	public void generateTableCon() {

		TableColumn<Contrato, Long> colIDC = new TableColumn<>("IDC");
		colIDC.setCellValueFactory(new PropertyValueFactory<Contrato, Long>("ID"));

		TableColumn<Contrato, String> colCPF = new TableColumn<>("CPF Cliente");
		colCPF.setCellValueFactory(new PropertyValueFactory<Contrato, String>("cpf_cli"));

		tableViewCon.getColumns().add(colIDC);
		tableViewCon.getColumns().add(colCPF);
		tableViewCon.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contrato>() {

			@Override
			public void changed(ObservableValue<? extends Contrato> c, Contrato antigo, Contrato novo) {
				entityToBoundaryTBCon(novo);
			}

		});

	}

	public TreinadorBoundary(Stage stage) {
		this.stage = stage;
		construirTela(stage);
	}

	private void construirTela(Stage stage) {
		Pane pane = new Pane();

		Image image = new Image("file:images/Verde_fundo.png");
		ImageView mv = new ImageView(image);

		Group root = new Group();
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
		atribuirContrato.setOnMouseClicked(event -> atribuirPctContrato());

		Button voltar = new Button("Voltar");
		voltar.relocate(220, 320);
		voltar.setMinWidth(200);
		voltar.setOnMouseClicked(event -> voltarInicio());

		atividade.setStyle(
				"-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
		modalidade.setStyle(
				"-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
		pacote.setStyle(
				"-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
		atribuirContrato.setStyle(
				"-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
		voltar.setStyle(
				"-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");

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
		
		Image image =new Image("file:images/Peso_tela.png");
		ImageView mv=new ImageView(image);
				
		Group root=new Group();
		root.getChildren().addAll(mv);
			
		pane.getChildren().addAll(root);

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

		novaAtividadeButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		buscarAtividButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		mostrarTodasAtivButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		voltarButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		
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

		id.setStyle("-fx-font-size:15; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		nome.setStyle("-fx-font-size:15; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		qtdSecao.setStyle("-fx-font-size:15; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		qtdRepeticao.setStyle("-fx-font-size:15; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		tempoDuracao.setStyle("-fx-font-size:15; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		txtID.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;");
		txtNome.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;");
		txtQtdSecao.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;");
		txtQtdRepeticao.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;");
		txtTempoDuracao.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;");
		txtAvisos.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;");
	
		
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
		stage.setTitle("Atividades");
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
		
		Image image =new Image("file:images/Peso_tela_azul.png");
		ImageView mv=new ImageView(image);
				
		Group root=new Group();
		root.getChildren().addAll(mv);
					
		pane.getChildren().addAll(root);

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
		
		novaModalidadeButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		buscarModButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		mostrarTodasModButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		voltarButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
	
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

		id.setStyle("-fx-font-size:20; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		tipo.setStyle("-fx-font-size:20; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		nivel.setStyle("-fx-font-size:20; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		qtdAtividades.setStyle("-fx-font-size:20; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		txtID.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");
		txtTipo.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");
		txtNivel.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");
		txtQtdAtividades.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");
		txtAvisos.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");
	
		
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
		stage.setTitle("Modalidades");
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
		
		Image image =new Image("file:images/Peso_Menor_tela_Verde.png");
		ImageView mv=new ImageView(image);
			
		Group root=new Group();
		root.getChildren().addAll(mv);
				
		pane.getChildren().addAll(root);

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

		novoPacoteButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		buscarPacoteButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		mostrarTodosPacButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		voltarButton.setStyle("-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;");
		
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
		
		pctMod.setStyle("-fx-font-size:20; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		pctAtiv.setStyle("-fx-font-size:20; -fx-text-fill:darkblue;-fx-font-weight: bold;");
		txtPcMod.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");
		txtPcAtiv.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");
		tableViewMod.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");
		tableViewAtiv.setStyle("-fx-background-color: '#aaccff';-fx-font-weight: bold;-fx-border-color:'#000000';-fx-border-radius:10px;-fx-min-width: 200");

		pane.getChildren().add(pctMod);
		pane.getChildren().add(txtPcMod);
		pane.getChildren().add(pctAtiv);
		pane.getChildren().add(txtPcAtiv);
		pane.getChildren().add(tableViewMod);
		pane.getChildren().add(tableViewAtiv);

		stage.setScene(scene);
		stage.setTitle("Pacotes");
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

	public void atribuirPctContrato() {

		Pane pane = new Pane();

		generateTablePct();
		tc.buscarTablePct();

		generateTableCon();
		tc.buscarTableCon();

		Scene scene = new Scene(pane, 800, 600);

		atribuirPctButton.relocate(295, 10);
		atribuirPctButton.setMinWidth(200);

		Button voltarButton = new Button("Voltar");
		voltarButton.relocate(295, 190);
		voltarButton.setMinWidth(200);
		voltarButton.setOnMouseClicked(event -> voltar());

		pane.getChildren().add(atribuirPctButton);
		pane.getChildren().add(voltarButton);

		atribuirPctButton.setOnAction(this);

		Label pacote = new Label("Pacote: ");
		pacote.relocate(15, 70);
		txtPacote.relocate(90, 70);

		Label contrato = new Label("Contrato: ");
		contrato.relocate(15, 130);
		txtContrato.relocate(90, 130);

		tableViewPct.relocate(15, 295);
		tableViewPct.setMaxSize(400, 300);

		tableViewCon.relocate(400, 295);
		tableViewCon.setMaxSize(400, 300);

		pane.getChildren().add(pacote);
		pane.getChildren().add(txtPacote);
		pane.getChildren().add(contrato);
		pane.getChildren().add(txtContrato);
		pane.getChildren().add(tableViewPct);
		pane.getChildren().add(tableViewCon);

		stage.setScene(scene);
		stage.setTitle("Vincular pacotes e contratos");
		stage.show();

	}

	public ParticiparModalidade boundaryToEntityParticipar() {

		ParticiparModalidade pm = new ParticiparModalidade();

		try {

			pm.setIdContrato(Long.parseLong(txtContrato.getText()));
			pm.setIdModalidade(Long.parseLong(txtPacote.getText()));

		} catch (Exception ex) {
			System.out.println("Erro ao receber os dados");
		}
		return pm;
	}

	public void entityToBoundaryTBPct(Pacotes p) {

		txtPacote.setText(String.valueOf(p.getIdModalidade()));
	}

	public void entityToBoundaryTBCon(Contrato c) {

		txtContrato.setText(String.valueOf(c.getID()));
	}

	public void mostrarUmPacote(Pacotes p) {

		Pane pane = new Pane();

		generateTableAtivRef();
		tc.buscarTableAtivRefinado(p.getIdModalidade());

		Scene scene = new Scene(pane, 650, 600);

		Button voltarButton = new Button("Voltar");
		voltarButton.relocate(290, 530);
		voltarButton.setMinWidth(200);
		voltarButton.setOnMouseClicked(event -> voltar());

		pane.getChildren().add(voltarButton);

		Label idMod = new Label("ID");
		idMod.relocate(20, 70);
		txtID.relocate(20, 90);

		Label tipo = new Label("TIPO");
		tipo.relocate(180, 70);
		txtTipo.relocate(180, 90);

		Label nivel = new Label("NIVEL");
		nivel.relocate(340, 70);
		txtNivel.relocate(340, 90);

		tableViewAtivRef.relocate(20, 210);
		tableViewAtivRef.setMaxSize(480, 250);

		pane.getChildren().add(idMod);
		pane.getChildren().add(txtID);
		pane.getChildren().add(tipo);
		pane.getChildren().add(txtTipo);
		pane.getChildren().add(nivel);
		pane.getChildren().add(txtNivel);
		pane.getChildren().add(tableViewAtivRef);

		stage.setScene(scene);
		stage.setTitle("Pacote");
		stage.show();

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
				JOptionPane.showMessageDialog(null, "Atividade nao existe");
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
				mostrarUmPacote(p);
			} catch (Exception g) {
				JOptionPane.showMessageDialog(null, "Pacote não existe");
			}
		} else if (e.getTarget() == atribuirPctButton) {
			ParticiparModalidade pm = boundaryToEntityParticipar();
			tc.vincularPacotes(pm);
		}

	}

	public void voltar() {
		new TreinadorBoundary(stage);
	}

	public void voltarInicio() {
		new LoginBoundary(stage);
	}
}
