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
		
		if (cmd.hasOption("p")) {
			System.out.println("pong");
		}
		
		if (cmd.hasOption("config")) {
			try {				
				String filename = "configuration.properties";
				input = BattleMain.class.getClassLoader().getResourceAsStream(filename);
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
		
		String teamID = api.connexion();
		System.out.println("teamID : " + teamID); //à faire pour toutes les variables -> ne pas les mettre en dur
		System.out.println("");
		
		String gameID = api.initialisationPartie(teamID); // initialisation d'une partie
		System.out.println("gameID : " + gameID);
		System.out.println("");
		
		String partieID = api.initialisationPartie(teamID); // retourne l'identifiant unique de la partie à jouer. -> dad8077e-6157-4603-b661-ac984876671c
		System.out.println("partieID : " + partieID);
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
		
		int nbTours; // à créer ? -> OUI !
		// move -> ORC,... pour les trois premiers tours
		// move -> A1,ATTACK,E1$A2,DEFEND,E1$A3,REST,A3 pour les tours suivants
		
		
		
		
		
		
		
		
		/*
		BattleMain de Yoann
		
	    import java.io.FileInputStream;
		import java.io.FileNotFoundException;
		import java.io.IOException;
		import java.io.InputStream;
		import java.util.Properties;
		
		import org.apache.commons.cli.CommandLine;
		import org.apache.commons.cli.CommandLineParser;
		import org.apache.commons.cli.DefaultParser;
		import org.apache.commons.cli.Options;
		import org.apache.commons.cli.ParseException;
		
		public class BattleMain {
			
			public static void main(String[] args) throws ParseException, IOException {
			//create option
			Options options = new Options();
			
			//add option
			options.addOption("p", false, "display pong" );
			options.addOption("config", false, "display config" );
			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(options, args);
			
				if(cmd.hasOption("p")) {
					System.out.println("pong");
				}
				if (cmd.hasOption("config")) {
					try {
					InputStream input = null;
						Properties prop = new Properties();
						String filename = "configuration.properties";
						input = BattleMain.class.getClassLoader().getResourceAsStream(filename);
						if(input == null) {
							System.out.println("Pas de fichier : " +filename);
						}
						// load a properties file
						prop.load(input);
			
						// get the property value and print it out
						String restURL = prop.getProperty("rest.base.url");
						String teamName = prop.getProperty("team.name");
						String password = prop.getProperty("team.password");
						
						
						//Practice
						Number nbBot = 3;
						APIrest api = new APIrest();
						String idTeam = api.GetIdEquipe(restURL, teamName, password);
						System.out.println(idTeam);
						String idPartie = api.NewGamePractice(restURL, nbBot , idTeam);
						System.out.println(idPartie);
						String status = api.GetStatus(restURL, idPartie, idTeam);
						if (status.contentEquals("CANPLAY")) {
							String Board = api.GetBoard(restURL, idPartie);
							
						} else if (status.contentEquals("CANTPLAY")) {
							while (status.contentEquals("CANTPLAY")) {
								try {
						            Thread.sleep(500);
						         } catch (Exception e) {
						            System.out.println(e);
						         }
								status = api.GetStatus(restURL, idPartie, idTeam);
							}
						} else if (status.contentEquals("DEFEAT")) {
							System.out.println("Vous êtes naze!");
						} else if (status.contentEquals("VICTORY")) {
							System.out.println("Vous êtes fort!");
						} else if (status.contentEquals("CANTPLAY")) {
							System.out.println("Partie Annulée");
						}
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				
			}
			
			public static void pingPong(){
				
			}
		}
		 * 
		 */
	}
}