package fr.diginamic.traitement_fichier.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;

public class IngredientDao {

	private List<String> listeIngredients = new ArrayList();
	private Set<String> listeSetDesIngredients = new HashSet<String>();

	public boolean ingredientExisteDeja(String ingredient) {

		// code pour vérifier si ingrédient existe déjà
		int longueurAvant = this.getListeSetDesIngredients().size();
		this.getListeSetDesIngredients().add(ingredient);
		int longueurApres = this.getListeSetDesIngredients().size();

		if (longueurAvant != longueurApres) {
			return false;
		} else {
			return true;
		}

	}

	public String factoriserIngredient(String ingredient) {

		char[] listeDesCharASupprimer = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_', '%', '.', '*' };

		for (char c : listeDesCharASupprimer) {
			ingredient = ingredient.replace(c, ' ');

		}
		if (ingredient.length() > 45) {

			return ingredient.substring(0, 45);
		} else {
			return ingredient;
		}

	}

	public void insererIngredient(String ingredient) {

		// code pour inserer ingrédient dans la table ingredients de MySQL
		Connection maConnection = null;
		PreparedStatement stat = null;

		// Récupération des informations dans database.properties
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String user = monFichierConf.getString("database.user");
		String url = monFichierConf.getString("database.url");
		String password = monFichierConf.getString("database.password");

		try {
			maConnection = DriverManager.getConnection(url, user, password);
			maConnection.setAutoCommit(false);

			Statement monStatement = maConnection.createStatement();

			String nomIngredient = ingredient;

			stat = maConnection.prepareStatement("INSERT INTO ingredients (nom_ingredient) VALUES (?)");
			stat.setString(1, nomIngredient);
			stat.executeUpdate();

			// int nb1 = monStatement.executeUpdate("INSERT INTO marques
			// (nom_marque) VALUES('" + nomMarque + ")");

			maConnection.commit();
		} catch (SQLException e) {
			throw new TechnicalException("une exception est apparut", e);
		} finally {
			try {
				maConnection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new TechnicalException("boooom", e);
			}
		}

	}

	/**
	 * @return the listeIngredients
	 */
	public List<String> getListeIngredients() {
		return listeIngredients;
	}

	/**
	 * Setter
	 * 
	 * @param listeIngredients
	 *            the listeIngredients to set
	 */
	public void setListeIngredients(List<String> listeIngredients) {
		this.listeIngredients = listeIngredients;
	}

	/**
	 * @return the listeSetDesIngredients
	 */
	public Set<String> getListeSetDesIngredients() {
		return listeSetDesIngredients;
	}

	/**
	 * Setter
	 * 
	 * @param listeSetDesIngredients
	 *            the listeSetDesIngredients to set
	 */
	public void setListeSetDesIngredients(Set<String> listeSetDesIngredients) {
		this.listeSetDesIngredients = listeSetDesIngredients;
	}

}
