package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField usuario;

	@FXML
	private TextField senha;

	public Stage primaryStage;

	public void entrar() {
		System.out.println("Login realizado com sucesso!");
		// logica de entrada
		if (usuario.getText().equals("admin") && senha.getText().equals("admin")) {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DashBoard.fxml"));

				StackPane root = loader.load();

				// Parent root = loader.load();

				Scene scene = new Scene(root, 932, 573);

				Stage currentStage = (Stage) usuario.getScene().getWindow();
				currentStage.setScene(scene);
				;
				currentStage.setTitle("DashBoard");
				currentStage.show();

				// primaryStage.setScene(scene);;
				// primaryStage.setTitle("DashBoard");
				// primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			mensagemDeErro();
		}
	}

	public void mensagemDeErro() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Sua senha esta errada");
		alert.setContentText("Senha incorreta!");
		alert.setHeaderText(null);
		alert.showAndWait();
	}
}
