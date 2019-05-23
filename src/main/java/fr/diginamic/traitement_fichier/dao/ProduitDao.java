package fr.diginamic.traitement_fichier.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;

public class ProduitDao {

	private int idCategorie;
	private int idMarque;
	private String categorie;
	private String marque;
	private String nomProduit;
	private List<String> listeIngredients;

	public ProduitDao(String line) {
		String[] attributsDuProduit = line.split("\\|");

		this.categorie = factoriser(attributsDuProduit[0]);
		this.marque = factoriser(attributsDuProduit[1]);
		this.idCategorie = retrouverIdCategorie(this.categorie);
		this.idMarque = retrouverIdMarque(this.marque);
		this.nomProduit = factoriser(attributsDuProduit[2]);
		this.listeIngredients = Arrays.asList(attributsDuProduit[4].split(","));
	}

	public String factoriser(String string) {
		if (string.length() > 45) {

			return string.substring(0, 45);
		} else {
			return string;
		}
	}

	public int retrouverIdCategorie(String nomCategorie) {

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

			PreparedStatement selectPizzaSt = maConnection
					.prepareStatement("SELECT id_categorie FROM categories WHERE nom_categorie=?");
			selectPizzaSt.setString(1, nomCategorie);
			ResultSet resultats = selectPizzaSt.executeQuery();
			if (resultats.next()) {
				int id = resultats.getInt("id_categorie");
				return id;
			}
			return 0;

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

	public int retrouverIdMarque(String nomMarque) {

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

			PreparedStatement selectPizzaSt = maConnection
					.prepareStatement("SELECT id_marque FROM marques WHERE nom_marque=?");
			selectPizzaSt.setString(1, nomMarque);
			ResultSet resultats = selectPizzaSt.executeQuery();
			if (resultats.next()) {
				int id = resultats.getInt("id_marque");
				return id;
			}
			return 0;

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

	public void insererProduit() {

		// code pour inserer produit dans la table produits de MySQL

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

			String nomProduit = this.getNomProduit();
			int idCategorie = this.getIdCategorie();
			int idMarque = this.getIdMarque();

			stat = maConnection
					.prepareStatement("INSERT INTO produits (nom_produit,id_categorie,id_marque) VALUES (?,?,?)");
			stat.setString(1, nomProduit);
			stat.setInt(2, idCategorie);
			stat.setInt(3, idMarque);
			stat.executeUpdate();

			// int nb1 = monStatement.executeUpdate("INSERT INTO produits
			// (nom_produit,id_categorie,id_marque) VALUES('"
			// + nomProduit + "'" + idCategorie + "'" + idMarque + ")");

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
	 * @return the nomProduit
	 */
	public String getNomProduit() {
		return nomProduit;
	}

	/**
	 * Setter
	 * 
	 * @param nomProduit
	 *            the nomProduit to set
	 */
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
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
	 * @return the idCategorie
	 */
	public int getIdCategorie() {
		return idCategorie;
	}

	/**
	 * Setter
	 * 
	 * @param idCategorie
	 *            the idCategorie to set
	 */
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	/**
	 * @return the idMarque
	 */
	public int getIdMarque() {
		return idMarque;
	}

	/**
	 * Setter
	 * 
	 * @param idMarque
	 *            the idMarque to set
	 */
	public void setIdMarque(int idMarque) {
		this.idMarque = idMarque;
	}

	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * Setter
	 * 
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * Setter
	 * 
	 * @param marque
	 *            the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}
}
