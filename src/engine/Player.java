package engine;

import java.util.ArrayList;

import buildings.ArcheryRange;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Unit;
import units.Army;

public class Player {

	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;
	
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList getControlledCities() {
		return this.controlledCities;
	}
	
	public ArrayList getControlledArmies() {
		return this.controlledArmies;
	}
	
	public double getTreasury() {
		return this.treasury;
	}
	
	public double getFood() {
		return this.food;
	}
	
	public void setTreasury(double value) {
		this.treasury = value;
	}
	
	public void setFood(double value) {
		this.food = value;
	}
	

	
	
	public Player(String name) {
		this.name = name;
		controlledCities = new ArrayList<>();
		controlledArmies = new ArrayList<>();
	}
	
	public void recruitUnit(String type, String cityName)throws BuildingInCoolDownException , MaxRecruitedException , NotEnoughGoldException{
		ArrayList<City>controlled = this.getControlledCities();
		City c = null;
		for(int i = 0 ; i<controlled.size();i++) {
			if(controlled.get(i).getName().equals(cityName)) {
				c = controlled.get(i);
				break;
			}
		}
		
		
		ArrayList militaryBuildings = c.getMilitaryBuildings();
		if(type.equals("Archer")) {
			ArcheryRange recruitBuilding = null;
			for(int i =0 ; i<militaryBuildings.size();i++) {
				if(militaryBuildings.get(i) instanceof ArcheryRange) {
					recruitBuilding = (ArcheryRange) militaryBuildings.get(i);
					break;
				}
			}
			
			Unit RecruitedUnit = recruitBuilding.recruit();
			Army oldDefendingArmy = c.getDefendingArmy();
			ArrayList oldUnits = oldDefendingArmy.getUnits();
			oldUnits.add(RecruitedUnit);
			Army newArmy = new Army(cityName);
			newArmy.setUnits(oldUnits);
			c.setDefendingArmy(newArmy);
			
			
		}
		
		
		
		
	}
	
	
	
	
}
