package fr.diginamic.traitement_fichier;

import java.util.Arrays;
import java.util.List;

public class Produit {

	private String categorie;
	private String marque;
	private String nomProduit;
	private List<String> listeIngredients;

	public Produit(String line) {
		String[] attributsDuProduit = line.split("\\|");
		this.categorie = attributsDuProduit[0];
		this.marque = attributsDuProduit[1];
		this.nomProduit = attributsDuProduit[2];
		this.listeIngredients = Arrays.asList(attributsDuProduit[4].split(","));
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

}
