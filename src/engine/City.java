package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import units.Army;



public class City {
	
	private String name;
	private ArrayList<EconomicBuilding> economicalBuildings;
	private ArrayList<MilitaryBuilding> militaryBuildings;
	private Army defendingArmy;
	private int turnsUnderSiege;
	private boolean underSiege = false;
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<EconomicBuilding> getEconomicalBuildings() {
		return this.economicalBuildings;
	}
	
	public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
		return this.militaryBuildings;
	}
	
	public Army getDefendingArmy() {
		return defendingArmy;
	}
	
	public int getTurnsUnderSiege() {
		return turnsUnderSiege;
	}
	
	public boolean isUnderSiege() {
		return underSiege;
	}
	
	
	
	public void setDefendingArmy(Army value) {
		this.defendingArmy = value;
	}
	
	public void setTurnsUnderSiege(int value) {
		this.turnsUnderSiege = value;
	}
	
	public void setUnderSiege(boolean value) {
		this.underSiege = value;
	}
	
	public City(String name) {
		this.name = name;
		economicalBuildings = new ArrayList<>();
		militaryBuildings = new ArrayList<>();
		defendingArmy = new Army(name);
	}
	 
	
	
	
	
	
	
	
	
	
	
	
	
}
