package com.za.websocket;

import java.net.*;
import java.io.*;

import org.json.JSONException;
import org.json.JSONObject;

import com.za.websocket.ClientJava;

public class ServerSocketJava extends Thread
{
   private ServerSocket serverSocket;
   ClientJava clientJava;
   String clientmsg = "";
   
   
   
   public ServerSocketJava(int port) throws IOException, URISyntaxException
   {
      serverSocket = new ServerSocket(port);
      clientJava = new ClientJava();
   
   }

    public void run()
    {
        while(true)
        {
            try
            {
            	Socket server = serverSocket.accept();

            	System.out.println("Just connected to " + server.getRemoteSocketAddress());
            	
            	// Reception d'un message dans in
            	BufferedReader in = new  BufferedReader( new InputStreamReader(server.getInputStream()));
            	
            	// On récupère le contenu du message recu 
            	String incomingMessage = in.readLine();
            	
            	// On construit le Json a partir du message entrant
            	JSONObject message  = new JSONObject(incomingMessage);
            	String idCapteur = message.getString("idCapteur");
            	String acquisition = message.getString("acquisition");
            	
            	//On extrait les donnee a transmettre au WebServer Glassfish
            	clientJava.accrocherCapteur(idCapteur);
            	clientJava.envoieMessage(idCapteur, acquisition);
            	
            	
            	PrintWriter out = new PrintWriter(server.getOutputStream());

            	out.println("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
                server.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
                //break;
            } catch (JSONException e) {
				System.out.println("Le message ne respecte pas la syntaxe JSON");
			}
        }
    }
    public static void main(String [] args) throws URISyntaxException
    {
        int port = 5000;
        try
        {
            Thread t = new ServerSocketJava(port);
            t.start();
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}