package fr.diginamic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.model.ProduitSitc;
import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;

public class ProduitSitcDao {

	public String creerRequeteSql(List<ProduitSitc> listeDeProduitSitc) {

		String requeteSql = "INSERT INTO produits_sitc (product_id,sitc_product_code,sitc_product__name_short_en) VALUES ";

		for (int i = 0; i < listeDeProduitSitc.size(); i++) {

			if (i == listeDeProduitSitc.size() - 1) {
				requeteSql = requeteSql + "(" + listeDeProduitSitc.get(i).getProductId() + ",\""
						+ listeDeProduitSitc.get(i).getProductCode() + "\",\""
						+ listeDeProduitSitc.get(i).getProductNameShortEn() + "\");";
			} else {
				requeteSql = requeteSql + "(" + listeDeProduitSitc.get(i).getProductId() + ",\""
						+ listeDeProduitSitc.get(i).getProductCode() + "\",\""
						+ listeDeProduitSitc.get(i).getProductNameShortEn() + "\"),";

			}
		}
		System.out.println(requeteSql);
		return requeteSql;
	}

	public void insererProduitsSitc(List<ProduitSitc> listeDeProduitSitc) {

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

			stat = maConnection.prepareStatement(creerRequeteSql(listeDeProduitSitc));

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
