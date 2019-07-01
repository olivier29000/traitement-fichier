package fr.diginamic.atlas_of_economic;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.diginamic.dao.PaysDao;
import fr.diginamic.model.Pays;
import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;

public abstract class ControllerPays {
	// INSERTION DES PAYS

	public static void controllerPays() throws IOException {

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		} catch (SQLException e) {
			throw new TechnicalException("l'appel du driver a foiré mec!", e);
		}

		File file = new File("C:/patates/Java/2019.04.16/16.04.2019/location.csv");
		List<String> listeDeLignesPays = FileUtils.readLines(file, "utf8");
		List<String> listeDeLignes = new ArrayList();
		for (int i = 0; i < listeDeLignesPays.size(); i++) {
			listeDeLignes.add(listeDeLignesPays.get(i).replaceAll("\"", ""));
		}
		List<Pays> listeDePays = new ArrayList();
		listeDeLignes.remove(0);
		for (String ligne : listeDeLignes) {
			String[] attributsDuPays = ligne.split(",");

			Pays pays = new Pays((Integer) Integer.parseInt(attributsDuPays[0]), attributsDuPays[1],
					attributsDuPays[2]);
			listeDePays.add(pays);
		}

		PaysDao paysDao = new PaysDao();
		paysDao.insererPays(listeDePays);

	}
}
