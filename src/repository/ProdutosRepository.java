package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Fly;

public class ProdutosRepository {

	private List<Fly> products;
	private File database;
	
	public ProdutosRepository() {
		this.database = new File("database-products.txt");
		this.products = new ArrayList<>();
		//Carregar os dados
		loadProducts();
	}
	
	// Carregar
	private void loadProducts() {
		try (Scanner sc = new Scanner(database)){
			while(sc.hasNextLine()) {
				String[] data = sc.nextLine().split(";");
				if(data.length >= 4) {
					// 0 -> id, 1 -> nome, 2 -> inicio, 3 -> fim
					Fly fly = new Fly();
					fly.setId(Integer.parseInt(data[0]));
					fly.setNome(data[1]);
					fly.setInicioFly(data[2]);
					fly.setFimFly(data[3]);
					products.add(fly);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado, criando um novo!");
		}
	}
	
	//CRUD - Create, Read, Update and Delete
	
	//Update - Atualizar
	public void updateFly(Fly updateFly) {
		for(int i = 0; i < products.size(); i++) {
			if(products.get(i).getId() == updateFly.getId()) {
				products.set(i, updateFly);
				saveProducts();
				break;
			}
		}
	}
	
	//Buscar especifico 
	public Fly getFlyById(int id) {
		for(Fly fly : products) {
			if(fly.getId() == id) {
				return fly;
			}
		}
		return null;
	}
	
	//Buscar Todos
	public List<Fly> buscarTodos() {
		return new ArrayList<>(products);
	}
	
	//Deletar Objeto Especifico
	public void deleteFly(int id) {
		//Percorrer todo o Array, caso seja igual ele vai remover
		products.removeIf(fly -> fly.getId() == id);
		saveProducts();
	}
	
	//Criar Fly
	public void addFly(Fly fly) {
		//vai ficar o ID
		fly.setId(getNextId());
		products.add(fly);
		//Sobrescrever o arquivo dataBase
	}
	
	//Sobrescrever o arquivo
	private void saveProducts() {
		//Buscando e recuperando arquivo
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(database, false))){
			for(Fly fly : products) {
				//Formatando a linha do dado
				String data = fly.getId() + ";" + fly.getNome() + ";" + fly.getInicioFly() + ";" + fly.getFimFly();
				//Escrever uma linha e passa para a proxima
				writer.println(data);
 			}
			//FileNotFoundException = arquivo não encontrado
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo database não encontrado! Deu ruim");
		}
	}
	
	public int getNextId() {
		int maxId = 0;
		for(Fly fly : products) {
			//Verifica se o numero é maior que o nosso numero maximo
			if(fly.getId() > maxId) {
				maxId = fly.getId();
			}
		}
		return maxId + 1;
	}
	
}
