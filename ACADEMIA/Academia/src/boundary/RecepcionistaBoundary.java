package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import academia.Cliente;
import academia.Cobranca;
import academia.Contrato;
import control.RecepcionistaControl;
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

public class RecepcionistaBoundary implements EventHandler<ActionEvent> {

	private RecepcionistaControl control = new RecepcionistaControl();

	RecepcionistaControl rc = new RecepcionistaControl();

	private TableView<Cliente> tableViewCli = new TableView<>(rc.getListaCli());
	private TableView<Contrato> tableViewCon = new TableView<>(rc.getListaCon());
	private TableView<Cobranca> tableView = new TableView<>(rc.getLista());

	TextField txtIDCli = new TextField();
	TextField txtCPF = new TextField();
	TextField txtNome = new TextField();
	TextField txtEmail = new TextField();
	TextField txtTelefone = new TextField();
	TextField txtEndereco = new TextField();
	TextField txtQtdTreinos = new TextField();

	TextField txtAvisos = new TextField();

	TextField txtIDCon = new TextField();
	TextField txtDataContrato = new TextField();
	TextField txtqtdParcelas = new TextField();
	TextField txtValorMes = new TextField();
	TextField txtValorTotal = new TextField();

	Button manterCliButton = new Button("Área de gestão do cliente");
	Button manterConButton = new Button("Área de gestão do contrato");
	Button voltar = new Button("Voltar");

	Button novoCliButton = new Button("Adicionar Cliente");
	Button buscarCliButton = new Button("Buscar Cliente");
	Button verCobrancaButton = new Button("Mostrar cobranças");
	Button mostrarTodosCliButton = new Button("Exibir todos");
	Button voltarButton = new Button("Voltar");

	Button novoConButton = new Button("Adicionar Contrato");
	Button buscarConButton = new Button("Buscar Contrato");
	Button mostrarTodosConButton = new Button("Exibir todos");
	Button voltarButton2 = new Button("Voltar");
	Button buscarCliConButton = new Button("Buscar");

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void generateTable() {

		TableColumn<Cobranca, Long> colID = new TableColumn<>("ID Cobranca");
		colID.setCellValueFactory(new PropertyValueFactory<Cobranca, Long>("ID_Cobranca"));

		TableColumn<Cobranca, Long> colIDC = new TableColumn<>("ID Contrato");
		colIDC.setCellValueFactory(new PropertyValueFactory<Cobranca, Long>("ID_Contrato"));

		TableColumn<Cobranca, LocalDate> colData = new TableColumn<>("Data Vencimento");
		colData.setCellValueFactory(new PropertyValueFactory<Cobranca, LocalDate>("dataPgto"));

		TableColumn<Cobranca, Double> colValor = new TableColumn<>("Valor");
		colValor.setCellValueFactory(new PropertyValueFactory<Cobranca, Double>("valor"));

		TableColumn<Cobranca, Integer> colNumParcela = new TableColumn<>("Nº Parcela");
		colNumParcela.setCellValueFactory(new PropertyValueFactory<Cobranca, Integer>("numParcela"));

		TableColumn<Cobranca, Boolean> colPago = new TableColumn<>("Pago");
		colPago.setCellValueFactory(new PropertyValueFactory<Cobranca, Boolean>("pago"));

		tableView.getColumns().addAll(colID, colIDC, colData, colValor, colNumParcela, colPago);

	}

	public void generateTableCli() {

		TableColumn<Cliente, Long> colID = new TableColumn<>("ID Cliente");
		colID.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("ID"));

		TableColumn<Cliente, String> colCPF = new TableColumn<>("ID Contrato");
		colCPF.setCellValueFactory(new PropertyValueFactory<Cliente, String>("CPF"));

		TableColumn<Cliente, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));

		TableColumn<Cliente, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));

		TableColumn<Cliente, String> colTelefone = new TableColumn<>("Telefone");
		colTelefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone"));

		TableColumn<Cliente, String> colEndereco = new TableColumn<>("Pago");
		colEndereco.setCellValueFactory(new PropertyValueFactory<Cliente, String>("endereco"));

		tableViewCli.getColumns().addAll(colID, colCPF, colNome, colEmail, colTelefone, colEndereco);

	}

	public void generateTableCon() {

		TableColumn<Contrato, Long> colID = new TableColumn<>("ID Contrato");
		colID.setCellValueFactory(new PropertyValueFactory<Contrato, Long>("ID"));

		TableColumn<Contrato, String> colCPFCli = new TableColumn<>("CPF Cliente");
		colCPFCli.setCellValueFactory(new PropertyValueFactory<Contrato, String>("cpf_cli"));

		TableColumn<Contrato, LocalDate> colData = new TableColumn<>("Data Contrato");
		colData.setCellValueFactory(new PropertyValueFactory<Contrato, LocalDate>("dataContrato"));

		TableColumn<Contrato, Double> colValor = new TableColumn<>("Valor Mensal");
		colValor.setCellValueFactory(new PropertyValueFactory<Contrato, Double>("valorMes"));

		TableColumn<Contrato, Integer> colQtdParcelas = new TableColumn<>("Qtd Parcelas");
		colQtdParcelas.setCellValueFactory(new PropertyValueFactory<Contrato, Integer>("qtdParcelas"));

		TableColumn<Contrato, Integer> colQtdTreinos = new TableColumn<>("Qtd Treinos");
		colQtdTreinos.setCellValueFactory(new PropertyValueFactory<Contrato, Integer>("qtdTreinos"));

		TableColumn<Contrato, Double> colValorTotal = new TableColumn<>("Valor Total");
		colValorTotal.setCellValueFactory(new PropertyValueFactory<Contrato, Double>("valorTotal"));

		tableViewCon.getColumns().addAll(colID, colCPFCli, colData, colValor, colQtdParcelas, colQtdTreinos,
				colValorTotal);

	}

	private Stage stage;

	public RecepcionistaBoundary(Stage stage) {
		this.stage = stage;
		construirTela(stage);
	}

	private void construirTela(Stage stage) {
		Pane pane = new Pane();

		Image image = new Image("file:images/Laranja_fundo.png");
		ImageView mv = new ImageView(image);

		Group root = new Group();
		root.getChildren().addAll(mv);

		pane.getChildren().addAll(root);

		Scene scene = new Scene(pane, 700, 500);

		manterCliButton.relocate(220, 110);
		manterCliButton.setMinWidth(200);
		manterCliButton.setOnMouseClicked(event -> cliente());

		manterConButton.relocate(220, 220);
		manterConButton.setMinWidth(200);
		manterConButton.setOnMouseClicked(event -> contrato());

		voltar.relocate(220, 330);
		voltar.setMinWidth(200);
		voltar.setOnMouseClicked(event -> voltarInicio());

		manterCliButton.setStyle(
				"-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
		manterConButton.setStyle(
				"-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");
		voltar.setStyle(
				"-fx-background-color: '#4422ff';-fx-text-fill:'#ffffff';-fx-font-weight: bold;-fx-font-size:20;");

		pane.getChildren().add(manterCliButton);
		pane.getChildren().add(manterConButton);
		pane.getChildren().add(voltar);

		stage.setScene(scene);
		stage.setTitle("Recepcionista");
		stage.show();
	}

	public void cliente() {
		Pane pane = new Pane();

		Scene scene = new Scene(pane, 700, 500);

		novoCliButton.relocate(400, 50);
		novoCliButton.setMinWidth(200);
		novoCliButton.setOnMouseClicked(event -> boundaryToEntityCli());

		buscarCliButton.relocate(400, 110);
		buscarCliButton.setMinWidth(200);

		mostrarTodosCliButton.relocate(400, 180);
		mostrarTodosCliButton.setMinWidth(200);

		voltarButton.relocate(400, 250);
		voltarButton.setMinWidth(200);
		voltarButton.setOnMouseClicked(event -> voltar());

		pane.getChildren().add(novoCliButton);
		pane.getChildren().add(buscarCliButton);
		pane.getChildren().add(mostrarTodosCliButton);
		pane.getChildren().add(voltarButton);

		novoCliButton.setOnAction(this);
		buscarCliButton.setOnAction(this);
		mostrarTodosCliButton.setOnAction(this);

		Label id = new Label("ID: ");
		id.relocate(50, 50);
		txtIDCli.relocate(110, 50);

		Label cpf = new Label("CPF: ");
		cpf.relocate(50, 100);
		txtCPF.relocate(110, 100);

		Label nome = new Label("Nome: ");
		nome.relocate(50, 150);
		txtNome.relocate(110, 150);

		Label email = new Label("Email: ");
		email.relocate(50, 200);
		txtEmail.relocate(110, 200);

		Label telefone = new Label("Telefone: ");
		telefone.relocate(50, 250);
		txtTelefone.relocate(110, 250);

		Label endereco = new Label("Endereco: ");
		endereco.relocate(50, 300);
		txtEndereco.relocate(110, 300);

		txtAvisos.relocate(300, 300);
		txtAvisos.setMinWidth(200);

		pane.getChildren().add(id);
		pane.getChildren().add(txtIDCli);
		pane.getChildren().add(cpf);
		pane.getChildren().add(txtCPF);
		pane.getChildren().add(nome);
		pane.getChildren().add(txtNome);
		pane.getChildren().add(email);
		pane.getChildren().add(txtEmail);
		pane.getChildren().add(telefone);
		pane.getChildren().add(txtTelefone);
		pane.getChildren().add(endereco);
		pane.getChildren().add(txtEndereco);
		pane.getChildren().add(txtAvisos);
		stage.setScene(scene);
		stage.setTitle("Manutenção Cliente");
		stage.show();

	}

	public Cliente boundaryToEntityCli() {

		Cliente c = new Cliente();

		try {

			c.setID(Long.parseLong(txtIDCli.getText()));
			c.setCPF(txtCPF.getText());
			c.setNome(txtNome.getText());
			c.setEmail(txtEmail.getText());
			c.setEndereco(txtEndereco.getText());
			c.setTelefone(txtTelefone.getText());

		} catch (Exception ex) {
			System.out.println("Erro ao receber os dados");
		}
		return c;
	}

	public void entityToBoundaryCli(Cliente c) {

		txtIDCli.setText(String.valueOf(c.getID()));
		txtCPF.setText(String.valueOf(c.getCPF()));
		txtNome.setText(String.valueOf(c.getNome()));
		txtEmail.setText(String.valueOf(c.getEmail()));
		txtTelefone.setText(String.valueOf(c.getTelefone()));
		txtEndereco.setText(String.valueOf(c.getEndereco()));
	}

	public void mostrarTodosCli() {

		Pane pane = new Pane();

		generateTableCli();
		rc.buscaTableCli();

		Scene scene = new Scene(pane, 700, 500);

		voltarButton2.relocate(155, 430);
		voltarButton2.setMinWidth(200);
		voltarButton2.setOnMouseClicked(event -> voltar());

		tableViewCli.setMaxSize(680, 500);

		pane.getChildren().add(voltarButton2);
		pane.getChildren().add(tableViewCli);

		stage.setScene(scene);
		stage.setTitle("Clientes");
		stage.show();

	}

	public void contrato() {

		Pane pane = new Pane();

		Scene scene = new Scene(pane, 700, 500);

		novoConButton.relocate(400, 50);
		novoConButton.setMinWidth(200);
		novoConButton.setOnMouseClicked(event -> boundaryToEntityCon());

		buscarConButton.relocate(400, 100);
		buscarConButton.setMinWidth(200);

		verCobrancaButton.relocate(400, 150);
		verCobrancaButton.setMinWidth(200);

		mostrarTodosConButton.relocate(400, 200);
		mostrarTodosConButton.setMinWidth(200);

		voltarButton2.relocate(400, 250);
		voltarButton2.setMinWidth(200);
		voltarButton2.setOnMouseClicked(event -> voltar());

		buscarCliConButton.relocate(285, 100);
		buscarCliConButton.setMinWidth(60);
		buscarCliConButton.setOnMouseClicked(event -> buscarCli(String.valueOf(txtCPF.getText())));

		pane.getChildren().add(novoConButton);
		pane.getChildren().add(buscarConButton);
		pane.getChildren().add(verCobrancaButton);
		pane.getChildren().add(mostrarTodosConButton);
		pane.getChildren().add(buscarCliConButton);
		pane.getChildren().add(voltarButton2);

		novoConButton.setOnAction(this);
		buscarConButton.setOnAction(this);
		verCobrancaButton.setOnAction(this);
		mostrarTodosConButton.setOnAction(this);
		buscarCliConButton.setOnAction(this);

		Label id = new Label("ID: ");
		id.relocate(50, 50);
		txtIDCon.relocate(130, 50);

		Label cpf = new Label("CPF: ");
		cpf.relocate(50, 100);
		txtCPF.relocate(130, 100);

		Label dataCon = new Label("Data: ");
		dataCon.relocate(50, 150);
		txtDataContrato.relocate(130, 150);

		Label qtdParcelas = new Label("Qtd parcelas: ");
		qtdParcelas.relocate(50, 200);
		txtqtdParcelas.relocate(130, 200);

		Label valorMes = new Label("Valor mes: R$");
		valorMes.relocate(50, 250);
		txtValorMes.relocate(130, 250);
		txtValorMes.setText("55.00");

		Label valorTotal = new Label("Valor total: ");
		valorTotal.relocate(50, 300);
		txtValorTotal.relocate(130, 300);

		Label qtdTreinos = new Label("Qtd treinos: ");
		qtdTreinos.relocate(50, 350);
		txtQtdTreinos.relocate(130, 350);

		txtAvisos.relocate(400, 300);
		txtAvisos.setMinWidth(200);

		pane.getChildren().add(id);
		pane.getChildren().add(txtIDCon);
		pane.getChildren().add(cpf);
		pane.getChildren().add(txtCPF);
		pane.getChildren().add(dataCon);
		pane.getChildren().add(txtDataContrato);
		pane.getChildren().add(qtdParcelas);
		pane.getChildren().add(txtqtdParcelas);
		pane.getChildren().add(valorMes);
		pane.getChildren().add(txtValorMes);
		pane.getChildren().add(valorTotal);
		pane.getChildren().add(txtValorTotal);
		pane.getChildren().add(txtAvisos);
		stage.setScene(scene);
		stage.setTitle("Manutenção Contrato");
		stage.show();

	}

	public Contrato boundaryToEntityCon() {
		Contrato c = new Contrato();

		try {

			c.setID(Long.parseLong(txtIDCon.getText()));
			c.setCpf_cli(txtCPF.getText());

			LocalDate dt = LocalDate.parse(txtDataContrato.getText(), dtf);
			c.setDataContrato(dt);

			c.setQtdParcelas(Integer.parseInt(txtqtdParcelas.getText()));
			c.setValorMes(Double.parseDouble(txtValorMes.getText()));
			c.setValorTotal(Double.parseDouble(txtValorTotal.getText()));

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao receber os dados");
		}
		return c;
	}

	public void entityToBoundaryCon(Contrato c) {

		try {
			txtIDCon.setText(String.valueOf(c.getID()));
			txtCPF.setText(String.valueOf(c.getCpf_cli()));
			txtDataContrato.setText(String.valueOf(c.getDataContrato()));
			txtqtdParcelas.setText(String.valueOf(c.getQtdParcelas()));
			txtValorMes.setText(String.valueOf(c.getValorMes()));
			txtValorTotal.setText(String.valueOf(c.getValorTotal()));
		} catch (Exception e) {
			txtAvisos.setText("Contrato não existe");
		}
	}

	public void mostrarTodosCon() {

		Pane pane = new Pane();

		generateTableCon();
		rc.buscaTableCon();

		Scene scene = new Scene(pane, 700, 500);

		voltarButton2.relocate(155, 430);
		voltarButton2.setMinWidth(200);
		voltarButton2.setOnMouseClicked(event -> voltar());

		tableViewCon.setMaxSize(680, 500);

		pane.getChildren().add(voltarButton2);
		pane.getChildren().add(tableViewCon);

		stage.setScene(scene);
		stage.setTitle("Contratos");
		stage.show();

	}

	public void buscarCli(String CPF) {

		Cliente c = control.buscarCliente(CPF);

		if (c != null) {
			JOptionPane.showMessageDialog(null, "Cliente possui cadastro, prosseguir");
		} else {
			JOptionPane.showMessageDialog(null, "Cliente não localizado \nRealize seu cadastro para prosseguir");
		}

	}

	public void cobranca(long idContrato) {

		Pane pane = new Pane();

		generateTable();
		rc.buscaTableCob(idContrato);

		Scene scene = new Scene(pane, 700, 500);

		voltarButton2.relocate(155, 430);
		voltarButton2.setMinWidth(200);
		voltarButton2.setOnMouseClicked(event -> voltar());

		tableView.setMaxSize(680, 500);

		pane.getChildren().add(voltarButton2);
		pane.getChildren().add(tableView);

		stage.setScene(scene);
		stage.setTitle("Cobranças");
		stage.show();

	}

	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == novoCliButton) {
			Cliente c = boundaryToEntityCli();
			control.novoCliente(c);
		} else if (e.getTarget() == buscarCliButton) {
			try {
				String CPF = txtCPF.getText();
				Cliente c = control.buscarCliente(CPF);
				entityToBoundaryCli(c);
			} catch (Exception f) {
				System.out.println("Cliente não possui cadastro");
			}

		} else if (e.getTarget() == novoConButton) {
			Contrato c = boundaryToEntityCon();
			control.novoContrato(c);
		} else if (e.getTarget() == buscarConButton) {
			String CPF = txtCPF.getText();
			Contrato c = control.buscarContrato(CPF);
			entityToBoundaryCon(c);
		} else if (e.getTarget() == verCobrancaButton) {
			try {
				long id = Long.parseLong(txtIDCon.getText());
				cobranca(id);
			} catch (Exception g) {
				JOptionPane.showMessageDialog(null, "Não existem cobranças a serem mostradas");
			}
		} else if (e.getTarget() == mostrarTodosCliButton) {
			mostrarTodosCli();
		} else if (e.getTarget() == mostrarTodosConButton) {
			mostrarTodosCon();
		}

	}

	public void voltar() {
		new RecepcionistaBoundary(stage);
	}

	public void voltarInicio() {
		new LoginBoundary(stage);
	}

}
