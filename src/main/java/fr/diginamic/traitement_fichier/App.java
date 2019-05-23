package fr.diginamic.traitement_fichier;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;
import fr.diginamic.traitement_fichier.dao.IngredientDao;
import fr.diginamic.traitement_fichier.dao.ProduitDao;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {

		// INSERTION DES INGREDIENTS
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		} catch (SQLException e) {
			throw new TechnicalException("l'appel du driver a foiré mec!", e);
		}

		File file = new File("C:/patates/Java/2019.04.16/16.04.2019/openFoodFacts.csv");
		List<String> lines = FileUtils.readLines(file, "utf8");
		lines.remove(0);
		IngredientDao ingredientDao = new IngredientDao();
		for (String line : lines) {
			ProduitDao produitDao = new ProduitDao(line);

			for (String ingredient : produitDao.getListeIngredients()) {
				ingredient = ingredientDao.factoriserIngredient(ingredient);

				if (!ingredientDao.ingredientExisteDeja(ingredient)) {

					ingredientDao.insererIngredient(ingredient);

				}
			}
		}
	}

	/**
	 * // INSERTION DES PRODUITS
	 * 
	 * try { DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); }
	 * catch (SQLException e) { throw new TechnicalException("l'appel du driver
	 * a foiré mec!", e); }
	 * 
	 * File file = new
	 * File("C:/patates/Java/2019.04.16/16.04.2019/openFoodFacts.csv");
	 * List<String> lines = FileUtils.readLines(file, "utf8"); lines.remove(0);
	 * 
	 * for (String line : lines) {
	 * 
	 * ProduitDao produitDao = new ProduitDao(line);
	 * 
	 * produitDao.insererProduit();
	 * 
	 * }
	 * 
	 * 
	 * // INSERTION DES MARQUES try { DriverManager.registerDriver(new
	 * com.mysql.cj.jdbc.Driver()); } catch (SQLException e) { throw new
	 * TechnicalException("l'appel du driver a foiré mec!", e); }
	 * 
	 * File file = new
	 * File("C:/patates/Java/2019.04.16/16.04.2019/openFoodFacts.csv");
	 * List<String> lines = FileUtils.readLines(file, "utf8"); lines.remove(0);
	 * MarqueDao marqueDao = new MarqueDao(); for (String line : lines) {
	 * Produit produit = new Produit(line);
	 * 
	 * if (!marqueDao.marqueExisteDeja(produit.getMarque())) { String marque =
	 * marqueDao.factoriserMarque(produit.getMarque());
	 * System.out.println(marque); marqueDao.insererMarque(marque);
	 * 
	 * } }
	 * 
	 * 
	 * INSERTION DES CATEGORIES try { DriverManager.registerDriver(new
	 * com.mysql.cj.jdbc.Driver()); } catch (SQLException e) { throw new
	 * TechnicalException("l'appel du driver a foiré mec!", e); }
	 * 
	 * File file = new
	 * File("C:/patates/Java/2019.04.16/16.04.2019/openFoodFacts.csv");
	 * List<String> lines = FileUtils.readLines(file, "utf8"); lines.remove(0);
	 * CategorieDao categorieDao = new CategorieDao(); for (String line : lines)
	 * { Produit produit = new Produit(line);
	 * 
	 * if (!categorieDao.categorieExisteDeja(produit.getCategorie())) { String
	 * categorie = categorieDao.factoriserCategorie(produit.getCategorie());
	 * System.out.println(categorie); categorieDao.insererCategorie(categorie);
	 * 
	 * } }
	 */

}
