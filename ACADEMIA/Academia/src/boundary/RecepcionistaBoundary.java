package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import academia.Cliente;
import academia.Contrato;
import control.RecepcionistaControl;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RecepcionistaBoundary {

	TextField txtID = new TextField();
	TextField txtCPF = new TextField();
	TextField txtNome = new TextField();
	TextField txtEmail = new TextField();
	TextField txtTelefone = new TextField();
	TextField txtEndereco = new TextField();

	TextField txtAvisos = new TextField();

	TextField txtDataContrato = new TextField();
	TextField txtqtdParcelas = new TextField();
	TextField txtValorMes = new TextField();
	TextField txtValorTotal = new TextField();

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	RecepcionistaControl rc = new RecepcionistaControl();

	private Stage stage;

	public RecepcionistaBoundary(Stage stage) {
		this.stage = stage;
		construirTela(stage);
	}

	private void construirTela(Stage stage) {
		Pane pane = new Pane();

		Scene scene = new Scene(pane, 700, 500);

		Button manterCliButton = new Button("Área de gestão do cliente");
		manterCliButton.relocate(220, 110);
		manterCliButton.setMinWidth(200);
		manterCliButton.setOnMouseClicked(event -> cliente());

		Button manterConButton = new Button("Área de gestão do contrato");
		manterConButton.relocate(220, 220);
		manterConButton.setMinWidth(200);
		manterConButton.setOnMouseClicked(event -> contrato());

		Button voltar = new Button("Voltar");
		voltar.relocate(220, 330);
		voltar.setMinWidth(200);
		voltar.setOnMouseClicked(event -> voltarInicio());
		
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

		Button novoCliButton = new Button("Adicionar");
		novoCliButton.relocate(300, 50);
		novoCliButton.setMinWidth(200);
		novoCliButton.setOnMouseClicked(event -> boundaryToEntityCli());

		Button buscarCliButton = new Button("Buscar");
		buscarCliButton.relocate(300, 110);
		buscarCliButton.setMinWidth(200);
		buscarCliButton.setOnMouseClicked(event -> entityToBoundaryCli(String.valueOf(txtCPF.getText())));

		Button mostrarTodosCliButton = new Button("Exibir todos");
		mostrarTodosCliButton.relocate(300, 180);
		mostrarTodosCliButton.setMinWidth(200);
		// mostrarTodosCliButton.setOnMouseClicked(event -> mostratTodosCli());

		Button voltarButton = new Button("Voltar");
		voltarButton.relocate(300, 250);
		voltarButton.setMinWidth(200);
		voltarButton.setOnMouseClicked(event -> voltar());

		pane.getChildren().add(novoCliButton);
		pane.getChildren().add(buscarCliButton);
		pane.getChildren().add(mostrarTodosCliButton);
		pane.getChildren().add(voltarButton);

		Label id = new Label("ID: ");
		id.relocate(50, 50);
		txtID.relocate(110, 50);
		txtID.setText(String.valueOf(rc.getIDCli()));

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
		pane.getChildren().add(txtID);
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

	public RecepcionistaControl boundaryToEntityCli() {
		try {

			String CPF = txtCPF.getText();
			String nome = txtNome.getText();
			String email = txtEmail.getText();
			String endereco = txtEndereco.getText();
			String telefone = txtTelefone.getText();
			rc.novoCliente(CPF, nome, email, endereco, telefone);

			JOptionPane.showMessageDialog(null, "cadastramos");

		} catch (Exception ex) {
			System.out.println("Erro ao receber os dados");
		}
		return rc;
	}

	public void entityToBoundaryCli(String CPF) {

		if (rc.buscarCliente(CPF) != null) {

			Cliente c = rc.buscarCliente(CPF);

			txtID.setText(String.valueOf(c.getID()));
			txtCPF.setText(String.valueOf(c.getCPF()));
			txtNome.setText(String.valueOf(c.getNome()));
			txtEmail.setText(String.valueOf(c.getEmail()));
			txtTelefone.setText(String.valueOf(c.getTelefone()));
			txtEndereco.setText(String.valueOf(c.getEndereco()));
		}

		else {
			txtAvisos.setText("Cliente não cadastrado");
		}
	}

	public void contrato() {

		Pane pane = new Pane();

		Scene scene = new Scene(pane, 700, 500);

		Button novoConButton = new Button("Adicionar");
		novoConButton.relocate(400, 50);
		novoConButton.setMinWidth(200);
		novoConButton.setOnMouseClicked(event -> boundaryToEntityCon());

		Button buscarConButton = new Button("Buscar");
		buscarConButton.relocate(400, 110);
		buscarConButton.setMinWidth(200);
		buscarConButton.setOnMouseClicked(event -> entityToBoundaryCon(String.valueOf(txtCPF.getText())));

		Button mostrarTodosConButton = new Button("Exibir todos");
		mostrarTodosConButton.relocate(400, 180);
		mostrarTodosConButton.setMinWidth(200);
		// mostrarTodosConButton.setOnMouseClicked(event -> mostrarTodosCon());

		Button voltarButton = new Button("Voltar");
		voltarButton.relocate(400, 250);
		voltarButton.setMinWidth(200);
		voltarButton.setOnMouseClicked(event -> voltar());

		Button buscarCliButton = new Button("Buscar");
		buscarCliButton.relocate(285, 100);
		buscarCliButton.setMinWidth(60);
		buscarCliButton.setOnMouseClicked(event -> buscarCli(String.valueOf(txtCPF.getText())));

		pane.getChildren().add(novoConButton);
		pane.getChildren().add(buscarConButton);
		pane.getChildren().add(mostrarTodosConButton);
		pane.getChildren().add(buscarCliButton);
		pane.getChildren().add(voltarButton);

		Label id = new Label("ID: ");
		id.relocate(50, 50);
		txtID.relocate(130, 50);
		txtID.setText(String.valueOf(rc.getIDCon()));

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

		txtAvisos.relocate(400, 300);
		txtAvisos.setMinWidth(200);

		pane.getChildren().add(id);
		pane.getChildren().add(txtID);
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

	public RecepcionistaControl boundaryToEntityCon() {
		try {

			String CPF = txtCPF.getText();
			LocalDate dataContrato = LocalDate.parse(txtDataContrato.getText(), dtf);
			int qtdParcelas = Integer.parseInt(txtqtdParcelas.getText());
			double valorMes = Double.parseDouble(txtValorMes.getText());

			double valorTotal = qtdParcelas * valorMes;

			rc.novoContrato(CPF, dataContrato, qtdParcelas, valorMes, valorTotal);

			entityToBoundaryCon(CPF);

		} catch (Exception ex) {
			System.out.println("Erro ao receber os dados");
		}
		return rc;
	}

	public void entityToBoundaryCon(String CPF) {

		if (rc.buscarCliente(CPF) != null) {

			Contrato c = rc.buscarContrato(CPF);

			txtID.setText(String.valueOf(c.getID()));
			txtDataContrato.setText(String.valueOf(c.getDataContrato()));
			txtqtdParcelas.setText(String.valueOf(c.getQtdParcelas()));
			txtValorMes.setText(String.valueOf(c.getValorMes()));
			txtValorTotal.setText(String.valueOf(c.getValorTotal()));
		}

		else {
			txtAvisos.setText("Contrato não existe");
		}

	}

	public void buscarCli(String CPF) {
		if (rc.buscarCliente(CPF) != null) {
			JOptionPane.showMessageDialog(null, "Cliente localizado, prosseguir!");
		} else if (rc.buscarCliente(CPF) == null) {
			JOptionPane.showMessageDialog(null,
					"Cliente não foi localizado \nCadastre o cliente e tente novamente depois");
		}
	}

	public void voltar() {
		new RecepcionistaBoundary(stage);
	}
	
	public void voltarInicio() {
		new LoginBoundary(stage);
	}
}
