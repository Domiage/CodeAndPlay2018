package com.sopra.miage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import static java.lang.Thread.sleep;

public class BattleMain {
	public static void main(String[] args) throws ParseException, IOException {
		Options options = new Options();
		options.addOption("p", false, "affichage pong");
		options.addOption("config", false, "affichage proprietes de configuration");
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);
		
		Properties prop = new Properties();
		//OutputStream output = null;
		InputStream input = null;
		String filename = "configuration.properties";
		input = BattleMain.class.getClassLoader().getResourceAsStream(filename);
		prop.load(input);
		
		// on récupère les informations du fichier "configurations.properties"
		String restURL = prop.getProperty("rest.base.url");
        String teamName = prop.getProperty("team.name");
        String password = prop.getProperty("team.password");
		
		if (cmd.hasOption("p")) {
			System.out.println("pong");
		}
		
		if (cmd.hasOption("config")) {
			try {				
				
				if(input==null){
    	            System.out.println("Désolé, impossible de trouver le fichier " + filename);
    	            return;
				}
				prop.load(input);
				
				System.out.println(prop.getProperty("rest.base.url"));
    	        System.out.println(prop.getProperty("team.name"));
    	        System.out.println(prop.getProperty("team.password"));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		APIres api  = new APIres();
		//api.blab(); //méthode ping
		//System.out.println("identifiant : " + teamName);
		//System.out.println("mdp : " + password);
		
		String teamID = api.connexion(teamName, password);
		
		practice(api, teamID);
		
		
		
		/*
		System.out.println("teamID : " + teamID); //à faire pour toutes les variables -> ne pas les mettre en dur
		System.out.println("");
		
		String gameID = api.initialisationPartieEquipe(teamID); // initialisation d'une partie
		System.out.println("gameID contre équipe : " + gameID);
		System.out.println("");
		
		
		String partieID = api.initialisationAffrontementBot(1, teamID); // retourne l'identifiant unique de la partie à jouer. -> dad8077e-6157-4603-b661-ac984876671c
		System.out.println("partieID contre Bot : " + partieID);
		System.out.println("");
		
		System.out.println("indiqueTourPartie : ");
		System.out.println(api.indiqueTourPartie("dad8077e-6157-4603-b661-ac984876671c", "b84af88c-abea-4b01-8179-510c335451b8")); // Indique si c’est au tour de l'équipe indiquée de jouer dans la partie concernée
		System.out.println("");
		
		System.out.println("retournePlateau : ");
		System.out.println(api.retournePlateau("dad8077e-6157-4603-b661-ac984876671c"));
		System.out.println("");
		
		System.out.println("retournePlateau2 (avec idEquipe) : ");
		System.out.println(api.retournePlateau2("dad8077e-6157-4603-b661-ac984876671c", "b84af88c-abea-4b01-8179-510c335451b8"));
		System.out.println("");
		
		System.out.println("retourneDernierCoup : ");
		System.out.println(api.retourneDernierCoup("dad8077e-6157-4603-b661-ac984876671c", "b84af88c-abea-4b01-8179-510c335451b8"));
		System.out.println("");
		
		System.out.println("jouerCoup : ");
		System.out.println(api.jouerCoup("dad8077e-6157-4603-b661-ac984876671c", "b84af88c-abea-4b01-8179-510c335451b8","1"));
		System.out.println("");
		
		System.out.println("nomAdversaire : ");
		System.out.println(api.nomAdversaire("dad8077e-6157-4603-b661-ac984876671c", "b84af88c-abea-4b01-8179-510c335451b8"));
		//System.out.println("");
		*/
	}
	
	public static void action(int nbtour, APIres api, String idPartie, String teamID, JSONObject plateau){
		switch (nbtour){
        case 0 :
            api.jouerCoup(idPartie, teamID, "ORC");
            break;
        case 1 :
            api.jouerCoup(idPartie, teamID, "PRIEST");
            break;
        case 2 :
            api.jouerCoup(idPartie, teamID, "GUARD");
            break;
        default:
            break;
		}
		if(nbtour > 2){
			JSONArray myTeam = plateau.getJSONArray("playerBoards").getJSONObject(0).getJSONArray("fighters");
            JSONArray myEnnemie = plateau.getJSONArray("playerBoards").getJSONObject(1).getJSONArray("fighters");
            if(!myEnnemie.getJSONObject(0).getBoolean("isDead")){
                api.jouerCoup(idPartie, teamID, "A1,ATTACK,E1$A2,ATTACK,E1$A3,ATTACK,E1");
            }
            else if(!myEnnemie.getJSONObject(1).getBoolean("isDead")){
                api.jouerCoup(idPartie, teamID, "A1,ATTACK,E2$A2,ATTACK,E2$A3,ATTACK,E2");
            }
            else if(!myEnnemie.getJSONObject(2).getBoolean("isDead")){
                api.jouerCoup(idPartie, teamID, "A1,ATTACK,E3$A2,ATTACK,E3$A3,ATTACK,E3");
            }
		}
	}
	
	// contre bot
	public static void practice(APIres api, String teamID) {
		int numeroBot = 1; // numéro du bot à changer ici -> le passer en paramètre ?
		
		//String gameID = "NA"; // car on est en practice : on joue contre des bots
		String idPartie = "NA";
		
		while(idPartie.contentEquals("NA")) {
			idPartie = api.initialisationAffrontementBot(numeroBot, teamID);
		}
		 
		
		String tourPartie = api.indiqueTourPartie(idPartie, teamID);
        System.out.println(tourPartie);
        int nbtour = 0;
        
        do {
        	try {
        		System.out.println("last : " + api.retourneDernierCoup(idPartie, teamID));
        		if (tourPartie.contentEquals("CANPLAY")) {
        			String Board = api.retournePlateau2(idPartie, teamID);
        			JSONObject plateau = new JSONObject(api.retournePlateau2(idPartie, teamID));
        			// ACTION A FAIRE
        			action(nbtour, api, idPartie, teamID, plateau);
        			
        			nbtour++;
        			api.nomAdversaire(idPartie, teamID);
        		}
        		else if (tourPartie.contentEquals("CANTPLAY")) {
                    sleep(500);
                }
        		tourPartie = api.indiqueTourPartie(idPartie, teamID);
                System.out.println("tourPartie : " + tourPartie);
        	} catch(Exception e){
        		System.out.println(e);
        		tourPartie = "CANCELLED";
        	}
        }while(!tourPartie.contentEquals("VICTORY") && !tourPartie.contentEquals("DEFEAT") && !tourPartie.contentEquals("CANCELLED"));
	}
}