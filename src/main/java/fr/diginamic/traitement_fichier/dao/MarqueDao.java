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

public class MarqueDao {

	private Set<String> listeSetDesMarques = new HashSet<String>();

	public boolean marqueExisteDeja(String marque) {

		int longueurAvant = this.getListeSetDesMarques().size();
		this.getListeSetDesMarques().add(marque);
		int longueurApres = this.getListeSetDesMarques().size();

		if (longueurAvant != longueurApres) {
			return false;
		} else {
			return true;
		}

	}

	public void insererMarque(String marque) {

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

			Statement monStatement = maConnection.createStatement();

			String nomMarque = marque;

			stat = maConnection.prepareStatement("INSERT INTO marques (nom_marque) VALUES (?)");
			stat.setString(1, nomMarque);
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

	public String factoriserMarque(String marque) {
		if (marque.length() > 45) {

			return marque.substring(0, 45);
		} else {
			return marque;
		}
	}

	/**
	 * @return the listeSetDesMarques
	 */
	public Set<String> getListeSetDesMarques() {
		return listeSetDesMarques;
	}

	/**
	 * Setter
	 * 
	 * @param listeSetDesMarques
	 *            the listeSetDesMarques to set
	 */
	public void setListeSetDesMarques(Set<String> listeSetDesMarques) {
		this.listeSetDesMarques = listeSetDesMarques;
	}

}
