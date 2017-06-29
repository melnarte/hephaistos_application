package com.za.websocket;

import java.io.StringReader;
import javax.json.Json;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class SensorMessageDecoder implements Decoder.Text<SensorMessage>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SensorMessage decode(String message) throws DecodeException {
		String idCapteur = (Json.createReader(new StringReader(message)).readObject().getString("idCapteur"));
		String acquisition = (Json.createReader(new StringReader(message)).readObject().getString("acquisition"));
		String date = (Json.createReader(new StringReader(message)).readObject().getString("date"));
		SensorMessage sensorMessage = new SensorMessage(idCapteur,acquisition,date);
		return sensorMessage;
	}

	@Override
	public boolean willDecode(String message) {
		boolean flag = true;
		try{
			Json.createReader(new StringReader(message)).readObject();
		}catch (Exception e){
			flag = false;
		}
		return flag;
	}

}
