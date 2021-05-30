package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;



public class Game {
	
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 30;
	private int currentTurnCount = 1;
	
	
	public Player getPlayer() {
		return player;
	}
	
	public String getName() {
		return this.player.getName();
	}
	
	public ArrayList getAvailableCities() {
		return this.availableCities;
	}
	
	public ArrayList getDistances() {
		return this.distances;
	}
	
	public int getMaxTurnCount() {
		return this.maxTurnCount;
	}
	
	public int getCurrentTurnCount() {
		return this.currentTurnCount;
	}
	
	public void setCurrentTurnCount(int value) {
		this.currentTurnCount = value;
	}
	
	public void setPlayer(Player value) {
		this.player = value;
	}
	
	
	
	
	public Game(String  playerName , String playerCity) throws IOException{
		availableCities = new ArrayList<City>();
		distances = new ArrayList<Distance>();
		Player p = new Player(playerName);
		this.setPlayer(p);
		
		
		this.loadCitiesAndDistances();
		
		for (int i =0; i<availableCities.size(); i++) {
			if(availableCities.get(i).getName().equals(playerCity)) {
				availableCities.get(i).setDefendingArmy(null);
				player.getControlledCities().add(availableCities.get(i));
			}
			
		}
		
		switch(playerCity) {
			case "Cairo": loadArmy("Rome","rome_army.csv") ;
							loadArmy("Sparta","sparta_army.csv");break;
						
						
			case "Rome": loadArmy("Cairo","cairo_army.csv") ;
							loadArmy("Sparta","sparta_army.csv");break;
		
		
			case "Sparta": loadArmy("Rome","rome_army.csv") ;
							loadArmy("Cairo","cairo_army.csv");break;
		
		}
		
						
	}
	
	
	
	
	public void loadArmy(String cityName , String path) throws IOException{ 
		City c1 = new City(cityName); 
		for (int i =0; i<availableCities.size(); i++) {
			if(availableCities.get(i).getName().equals(cityName)) {
				c1=availableCities.get(i);
			}
			
		}
		String currentLine = ""; 
		FileReader fileReader= new FileReader(path); 
		BufferedReader br = new BufferedReader(fileReader); 
		ArrayList<Unit> u1 = new ArrayList<Unit>();
		while ((currentLine = br.readLine()) != null) { 
			String [] results = currentLine.split(",");
			if(results[0].equals("Archer")) {
				Archer a1;
				if(Integer.parseInt(results[1])==1) {
					a1 = new Archer(1,60,0.4,0.5,0.6);
				}else if(Integer.parseInt(results[1])==2) {
					a1 = new Archer(2,60,0.4,0.5,0.6);
				}else {
					a1 = new Archer(3,70,0.5,0.6,0.7);
				}
				u1.add(a1);
			}else if(results[0].equals("Infantry")) {
				Infantry a1;
				
				if(Integer.parseInt(results[1])==1) {
					a1 = new Infantry(1,50,0.5,0.6,0.7);
				}else if(Integer.parseInt(results[1])==2) {
					a1 = new Infantry(2,50,0.5,0.6,0.7);
				}else {
					a1 = new Infantry(3,60,0.6,0.7,0.8);
				}
				u1.add(a1);
			}else {
				Cavalry a1;
				
				if(Integer.parseInt(results[1])==1) {
					a1 = new Cavalry(1,40,0.6,0.7,0.75);
				}else if(Integer.parseInt(results[1])==2) {
					a1 = new Cavalry(2,40,0.6,0.7,0.75);
				}else {
					a1 = new Cavalry(3,60,0.7,0.8,0.9);
				}
				u1.add(a1);
				
			}
			
		}
		Army theArmy = new Army(cityName);
		theArmy.setUnits(u1);
		c1.setDefendingArmy(theArmy);
		
	}
	
	

	
	private void loadCitiesAndDistances() throws IOException{
		String currentLine = "";
		FileReader fileReader= new FileReader("distances.csv");
		BufferedReader br = new BufferedReader(fileReader);
		HashSet<String> hash = new HashSet<>();
		while ((currentLine = br.readLine()) != null){
			String [] results = currentLine.split(",");
			Distance d = new Distance(results[0] , results[1] , Integer.parseInt(results[2]));
			if(!hash.contains(results[0])){
				hash.add(results[0]);
				availableCities.add(new City(results[0]));
			}
			
			if(!hash.contains(results[1])) {
				hash.add(results[1]);
				availableCities.add(new City(results[1]));
			}
			
			distances.add(d);
		}
	}
	
	

}
