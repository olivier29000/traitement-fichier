package fr.diginamic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.model.Pays;
import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;

public class PaysDao {

	public String creerRequeteSql(List<Pays> listeDePays) {

		String requeteSql = "INSERT INTO pays (location_id,location_code,location_name_short_en) VALUES ";

		for (int i = 0; i < listeDePays.size(); i++) {

			if (i == listeDePays.size() - 1) {
				requeteSql = requeteSql + "(" + listeDePays.get(i).getLocationId() + ",\""
						+ listeDePays.get(i).getLocationCode() + "\",\"" + listeDePays.get(i).getLocationNameShortEn()
						+ "\");";
			} else {
				requeteSql = requeteSql + "(" + listeDePays.get(i).getLocationId() + ",\""
						+ listeDePays.get(i).getLocationCode() + "\",\"" + listeDePays.get(i).getLocationNameShortEn()
						+ "\"),";

			}
		}
		System.out.println(requeteSql);
		return requeteSql;
	}

	public void insererPays(List<Pays> listeDePays) {

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

			stat = maConnection.prepareStatement(creerRequeteSql(listeDePays));
			System.out.println(creerRequeteSql(listeDePays));
			stat.executeUpdate();
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

}
