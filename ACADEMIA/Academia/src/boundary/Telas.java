package boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Telas extends Application {

	Stage tela;
	Scene inicio;
	Scene acesso;

	@Override
	public void start(Stage primaryStage) {
		new LoginBoundary(primaryStage);
		primaryStage.setResizable(false);
	}

	public void cenaInicio() {
		Button recepcionista = new Button("RECEPCIONISTA");
		recepcionista.setOnAction(e -> {
			tela.setScene(acesso);
		});

		Button treinador = new Button("TREINADOR");
		Button cliente = new Button("CLIENTE");

		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(recepcionista, treinador, cliente);

		inicio = new Scene(box, 400, 300);
	}

	public void cenaAcesso() throws IOException {
		String arquivoCSS = getClass().getResource("/control/Login.css").toExternalForm();
		URL arquivoFXML = getClass().getResource("/control/Login.fxml");
		GridPane raiz = FXMLLoader.load(arquivoFXML);

		acesso = new Scene(raiz, 300, 300);
		acesso.getStylesheets().add(arquivoCSS);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
