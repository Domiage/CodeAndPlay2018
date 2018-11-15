package com.sopra.miage;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class APIres {
	public void blab() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/ping");
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = invocationBuilder.get();
		
		String reponse = response.readEntity(String.class);
		System.out.println(reponse);
	}
	
	public void connexion() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/player/getIdEquipe/Team_Hmmm/DomiLeBoss");
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseConnexion = invocationBuilder.get();
		
		String reponseCo = responseConnexion.readEntity(String.class);
		System.out.println(reponseCo);
	}
	
	public void initialisationPartie(String teamID) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/versus/next/"+teamID);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseInit = invocationBuilder.get();
		
		String reponseIn = responseInit.readEntity(String.class);
		System.out.println(reponseIn);
	}
	
	public void initialisationAffrontement(int numeroBot, String teamID) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/practice/new/"+numeroBot+"/"+teamID);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseInitAffront = invocationBuilder.get();
		
		String reponseAffront = responseInitAffront.readEntity(String.class);
		System.out.println(reponseAffront);
	}
	
	public void indiqueTourPartie(String gameID, String teamID) {
		String url = "http://codeandplay.pw/epic-ws/epic/game/status/"+gameID+"/"+teamID;
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseIndiqueTourPartie = invocationBuilder.get();
		
		String reponseTourPartie = responseIndiqueTourPartie.readEntity(String.class);
		System.out.println(reponseTourPartie);
	}
	
	public void retournePlateau(String gameID) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/game/board/"+gameID+"?format=(JSON)"); //|String|XML on ne prend que du JSON
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseRetournePlateau = invocationBuilder.get();
		
		String reponsePlateau = responseRetournePlateau.readEntity(String.class);
		System.out.println(reponsePlateau);
	}
}
