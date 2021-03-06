package com.za.websocket;

public class SensorMessage {
	
	private String idCapteur;
	private String acquisition;
	private String date;
	private String commande;
	
	public String getCommande() {
		return commande;
	}

	public void setCommande(String commande) {
		this.commande = commande;
	}

	public SensorMessage(String idCapteur, String acquisition, String date,String commande) {
		this.idCapteur = idCapteur;
		this.acquisition = acquisition;
		this.date = date;
		this.commande = commande;
	}
	
	public SensorMessage(String commande, String idCapteur) {
		this.idCapteur = idCapteur;
		this.commande = commande;
	}
	
	public String getIdCapteur() {
		return idCapteur;
	}
	public void setIdCapteur(String idCapteur) {
		this.idCapteur = idCapteur;
	}
	public String getAcquisition() {
		return acquisition;
	}
	public void setAcquisition(String acquisition) {
		this.acquisition = acquisition;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
