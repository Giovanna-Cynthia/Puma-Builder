package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Fly;
import repository.ProdutosRepository;

public class ProductsController {

	@FXML
	private TableView<Fly> tableView;
	
	@FXML
	private TableColumn<Fly, String> cProduto;

	@FXML
	private TableColumn<Fly, Integer> cPreco;

	@FXML
	private TableColumn<Fly, Integer> cQuantidade;

	@FXML
	private TableColumn<Fly, Integer> cCodigo;
	
	@FXML
	private TextField produto;

	@FXML
	private TextField preco;

	@FXML
	private TextField quantidade;

	@FXML
	private TextField codigo;
	
	private ObservableList<Fly> data;

	private ProdutosRepository repoProduto;
	
	@FXML
	public void initialize() {
		//iniciando com o valor default da celula (cell)
		cProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
		cPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		cQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		cCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		
		//instanciando lista observable
		data = FXCollections.observableArrayList();
		 
		//tableview aceita somente ObservableList
		tableView.setItems(data);
		
		repoProduto = new ProdutosRepository();
		
		//Preencher lista
		carregarDadosSalvos();
	}

	public void carregarDadosSalvos() {
		try (BufferedReader br = new BufferedReader(new FileReader("database-pro.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				//preencher o objeto fly
				if(parts.length >= 5) {
					Fly fly = new Fly();
					fly.setId(Integer.parseInt(parts[0]));
					fly.setProduto(parts[1]);
					fly.setPreco(Double.parseDouble(parts[2]));
					fly.setQuantidade(Integer.parseInt(parts[3]));
					fly.setCodigo(Integer.parseInt(parts[4]));
					//adicionar no observableList
					data.add(fly);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void adicionar() {
		Fly fly = new Fly();
		fly.setProduto(produto.getText());
		fly.setPreco(Double.parseDouble(preco.getText()));
		fly.setQuantidade(Integer.parseInt(quantidade.getText()));
		fly.setCodigo(Integer.parseInt(codigo.getText()));
		repoProduto.addFly(fly);
		clearProducts();
	}
	
	public void clearProducts() {
		produto.clear();
		preco.clear();
		quantidade.clear();
		codigo.clear();
	}

	public void limpar() {
		clearProducts();
	}
}
