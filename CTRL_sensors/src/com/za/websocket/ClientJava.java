package com.za.websocket;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;



public class ClientJava{
	SensorClientEndpoint client;
	ArrayList<String> capteurs;
	
	public ClientJava() throws URISyntaxException, IOException{
		this.client = new SensorClientEndpoint();
		this.capteurs = new ArrayList<String>();
	}
	
	public boolean accrocherCapteur(String idCapteur){
		return capteurs.add(idCapteur);
	}
	
	public boolean deccrocherCapteur(String idCapteur){
		return capteurs.remove(idCapteur);
	}
	
	public boolean containsCapteur(String idCapteur){
		return capteurs.contains(idCapteur);
	}
	
	public static String buildJson(String idCapteur,String acquisition) throws UnknownHostException{
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("idCapteur", idCapteur)
				.add("acquisition", acquisition)
				.add("date", (new Date()).toString())
				.build();
		StringWriter stringWriter = new StringWriter();
		try(JsonWriter jsonWriter = Json.createWriter(stringWriter)){jsonWriter.write(jsonObject);}
		return stringWriter.toString();
	}
	
	public boolean envoieMessage(String idCapteur,String message){
		try {
			if(capteurs.contains(idCapteur)){
				client.sendMessage(buildJson(idCapteur,message));
				return true;
			}
			System.out.println("\t[Capteur "+idCapteur + " inconnu]");
			return false;
		} catch (IOException e) {
			System.out.println("Impossible d'envoyer le message");
			return false;
		}
	}	
	
	public boolean fermerClient(){
		try {
			this.client.session.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	public void afficheCapteurs(){
		System.out.println(capteurs);
	}
}
