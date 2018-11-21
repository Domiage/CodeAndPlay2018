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
	
	public String connexion() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/player/getIdEquipe/Team_Hmmm/DomiLeBoss");
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseConnexion = invocationBuilder.get();
		
		String reponseCo = responseConnexion.readEntity(String.class);
		return(reponseCo);
	}
	
	public String initialisationPartie(String teamID) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/versus/next/"+teamID);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseInit = invocationBuilder.get();
		
		String reponseIn = responseInit.readEntity(String.class);
		return(reponseIn);
	}
	
	public String initialisationAffrontement(int numeroBot, String teamID) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/practice/new/"+numeroBot+"/"+teamID);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseInitAffront = invocationBuilder.get();
		
		String reponseAffront = responseInitAffront.readEntity(String.class);
		return(reponseAffront);
	}
	
	public String indiqueTourPartie(String gameID, String teamID) {
		String url = "http://codeandplay.pw/epic-ws/epic/game/status/"+gameID+"/"+teamID;
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseIndiqueTourPartie = invocationBuilder.get();
		
		String reponseTourPartie = responseIndiqueTourPartie.readEntity(String.class);
		return(reponseTourPartie);
	}
	
	public String retournePlateau(String gameID) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/game/board/"+gameID+"?format=(JSON)"); //|String|XML on ne prend que du JSON
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseRetournePlateau = invocationBuilder.get();
		
		String reponsePlateau = responseRetournePlateau.readEntity(String.class);
		return(reponsePlateau);
	}
	
	public String retournePlateau2(String gameID, String teamID) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/game/board/"+gameID+"/"+teamID+"?format=(JSON)"); //|String|XML on ne prend que du JSON
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseRetournePlateau2 = invocationBuilder.get();
		
		String reponsePlateau2 = responseRetournePlateau2.readEntity(String.class);
		return(reponsePlateau2);
	}
	
	public String retourneDernierCoup(String gameID, String teamID) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/game/getlastmove/"+gameID+"/"+teamID);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseRetourneDernierCoup = invocationBuilder.get();
		
		String reponseDernierCoup = responseRetourneDernierCoup.readEntity(String.class);
		return(reponseDernierCoup);
	}
	
	public String jouerCoup(String gameID, String teamID, String move) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/game/play/"+gameID+"/"+teamID+"/"+move);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseRetourneDernierCoup = invocationBuilder.get();
		
		String reponseDernierCoup = responseRetourneDernierCoup.readEntity(String.class);
		return(reponseDernierCoup);
		
		// move -> ORC,... pour les trois premiers tours
		// move -> A1,ATTACK,E1$A2,DEFEND,E1$A3,REST,A3 pour les tours suivants
	}
	
	public String nomAdversaire(String gameID, String teamID) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://codeandplay.pw/epic-ws/epic/game/opponent/"+gameID+"/"+teamID);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response responseNomAdversaire = invocationBuilder.get();
		
		String reponseNom = responseNomAdversaire.readEntity(String.class);
		return(reponseNom);
	}
}