package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

	private ProdutosRepository repoProduto;
	
	@FXML
	public void initialize() {
		repoProduto = new ProdutosRepository();
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
