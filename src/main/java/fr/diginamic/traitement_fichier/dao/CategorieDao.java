package fr.diginamic.traitement_fichier.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;

public class CategorieDao {

	private Set<String> listeSetDesCategories = new HashSet<String>();

	public boolean categorieExisteDeja(String categorie) {

		// code pour vérifier si ingrédient existe déjà

		int longueurAvant = this.getListeSetDesCategories().size();
		this.getListeSetDesCategories().add(categorie);
		int longueurApres = this.getListeSetDesCategories().size();

		if (longueurAvant != longueurApres) {
			return false;
		} else {
			return true;
		}

	}

	public void insererCategorie(String categorie) {

		// code pour inserer marque dans la table marques de MySQL

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
			// System.out.println(maConnection);
			Statement monStatement = maConnection.createStatement();

			String nomCategorie = categorie;

			stat = maConnection.prepareStatement("INSERT INTO categories (nom_categorie) VALUES (?)");
			stat.setString(1, nomCategorie);
			stat.executeUpdate();

			// int nb1 = monStatement
			// .executeUpdate("INSERT INTO categories (nom_categorie) VALUES('"
			// + nomCategorie + "')");

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

	public String factoriserCategorie(String categorie) {
		if (categorie.length() > 45) {

			return categorie.substring(0, 45);
		} else {
			return categorie;
		}
	}

	/**
	 * @return the listeSetDesCategories
	 */
	public Set<String> getListeSetDesCategories() {
		return listeSetDesCategories;
	}

	/**
	 * Setter
	 * 
	 * @param listeSetDesCategories
	 *            the listeSetDesCategories to set
	 */
	public void setListeSetDesCategories(Set<String> listeSetDesCategories) {
		this.listeSetDesCategories = listeSetDesCategories;
	}

}
