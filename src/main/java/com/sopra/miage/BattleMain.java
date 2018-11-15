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
		System.out.println("idEquipe : ");
		api.connexion(); //connexion à la partie, retourne l'identifiant de l'équipe
		System.out.println("");
		
		System.out.println("idPartie contre équipe réelle : ");
		api.initialisationPartie("b84af88c-abea-4b01-8179-510c335451b8"); // initialisation d'une partie
		System.out.println("");
		
		api.initialisationAffrontement(1,"b84af88c-abea-4b01-8179-510c335451b8"); // retourne l'identifiant unique de la partie à jouer. -> dad8077e-6157-4603-b661-ac984876671c
		api.indiqueTourPartie("dad8077e-6157-4603-b661-ac984876671c", "b84af88c-abea-4b01-8179-510c335451b8"); // Indique si c’est au tour de l'équipe indiquée de jouer dans la partie concernée
		api.retournePlateau("dad8077e-6157-4603-b661-ac984876671c");

	}
}