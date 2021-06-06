package engine;

import java.util.ArrayList;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Unit;
import units.Army;
import units.Status;

public class Player {

	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;
	
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<City> getControlledCities() {
		return this.controlledCities;
	}
	
	public ArrayList<Army> getControlledArmies() {
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
	
	public void recruitUnit(String type, String cityName)throws BuildingInCoolDownException ,
	MaxRecruitedException , NotEnoughGoldException{
		
		System.out.println("Enter method");
		
		// get the corresponding city
		ArrayList<City>controlled = this.getControlledCities();
		City c = null;
		for(int i = 0 ; i<controlled.size();i++) {
			if(controlled.get(i).getName().equals(cityName)) {
				c = controlled.get(i);
				break;
			}
		}
		System.out.println(c);
		if(c==null) {
			return;
		}
		System.out.println("found city "+c.getName());
		
		// getting the corresponding militaryBuilding
		ArrayList<MilitaryBuilding> militaryBuildings = c.getMilitaryBuildings();
		MilitaryBuilding recruitBuilding = null;
		if(type.equals("Archer")) {
			for(int i =0 ; i<militaryBuildings.size();i++) {
				if(militaryBuildings.get(i) instanceof ArcheryRange) {
					recruitBuilding = (ArcheryRange) militaryBuildings.get(i);
					break;
				}
			}
		}
		
		else if(type.equals("Infantry")) {
			for(int i = 0  ; i<militaryBuildings.size();i++) {
				if(militaryBuildings.get(i) instanceof Barracks) {
					recruitBuilding = (Barracks)militaryBuildings.get(i);
					break;
				}
			}
		}
		
		else if(type.equals("Cavalry")) {
			for(int i = 0  ; i<militaryBuildings.size();i++) {
				if(militaryBuildings.get(i) instanceof Stable) {
					recruitBuilding = (Stable)militaryBuildings.get(i);
					break;
				}
			}
		}
		
		if(recruitBuilding==null) {
			return;
		}
		
	
		
		// checking for the building max recruit
		if(recruitBuilding.getCurrentRecruit()==recruitBuilding.getMaxRecruit()) {
			throw new MaxRecruitedException("you reached the maximum recruit");
		}
		
		
		
		// getting the cost of the building
		int cost = recruitBuilding.getRecruitmentCost();
		 
		if(this.getTreasury()< cost) {
			throw new NotEnoughGoldException("there is enough gold");
		}
		
		
		
		Unit RecruitedUnit = recruitBuilding.recruit();
		
		// connecting the unit to it is parent army 
		RecruitedUnit.setParentArmy(c.getDefendingArmy()); // ##########################################
		
		// adding the new unit to the units of the defending army
		c.getDefendingArmy().getUnits().add(RecruitedUnit);
		
		
		// increment the number of units that are recruited from this military building 
		recruitBuilding.setCurrentRecruit(recruitBuilding.getCurrentRecruit()+1);
		this.setTreasury(this.getTreasury()-cost);
		
	}
	
	
	
	
	
	public void build(String type , String cityName) throws NotEnoughGoldException{
		
		//getting the corresponding city
		ArrayList<City>controlled = this.getControlledCities();
		
		City c = null;
		for(int i = 0 ; i<controlled.size();i++) {
			if(controlled.get(i).getName().equals(cityName)) {
				c = controlled.get(i);
				break;
			}
		}
		
		// if the city is not found
		if(c==null) {
			return;
		}
		
		
		
		
		// getting the corresponding EconomicalBuildings and MilitaryBuildings of the city
		ArrayList<EconomicBuilding> economicBuilding = c.getEconomicalBuildings();
		ArrayList<MilitaryBuilding> militaryBuilding = c.getMilitaryBuildings();
		
		// build the needed building if the player have money enough and the building is not exist in the city
		int cost = 0;
		if(type.equals("Farm")) {
			int i = 0;
			for(; i<economicBuilding.size() ; i++) {
				if(economicBuilding.get(i) instanceof Farm) {
					break;
				}
			}
			if(i==economicBuilding.size()) {
				Farm newFarm = new Farm();
				if(newFarm.getCost()>this.getTreasury()) {
					throw new NotEnoughGoldException("Not Enough Gold");
				}
				if(this.getTreasury()>=newFarm.getCost()) {
					c.getEconomicalBuildings().add(newFarm);
					this.setTreasury(this.getTreasury()-newFarm.getCost());
				}
			}
		}else if(type.equals("Market")) {
			int i = 0;
			for(; i<economicBuilding.size() ; i++) {
				if(economicBuilding.get(i) instanceof Market) {
					break;
				}
			}
			if(i==economicBuilding.size()) {
				Market newMarket = new Market();
				if(this.getTreasury()<newMarket.getCost()) {
					throw new NotEnoughGoldException("Not Enough Gold");
				}
				if(this.getTreasury()>=newMarket.getCost()) {
					c.getEconomicalBuildings().add(newMarket);
					this.setTreasury(this.getTreasury()-newMarket.getCost());
				}
			}
			
		}else if(type.equals("ArcheryRange")) {
			int i = 0;
			for(; i<militaryBuilding.size() ; i++) {
				if(militaryBuilding.get(i) instanceof ArcheryRange) {
					break;
				}
			}	
			if(i==militaryBuilding.size()) {
				ArcheryRange newArcheryRange = new ArcheryRange();
				if(this.getTreasury()< newArcheryRange.getCost()) {
					throw new NotEnoughGoldException("Not Enough Gold");
				}
				
				if(this.getTreasury()>=newArcheryRange.getCost()) {
					c.getMilitaryBuildings().add(newArcheryRange);
					this.setTreasury(this.getTreasury()-newArcheryRange.getCost());
				}
			}
		}else if(type.equals("Barracks")) {
			int i = 0;
			for(; i<militaryBuilding.size() ; i++) {
				if(militaryBuilding.get(i) instanceof Barracks) {
					break;
				}
			}
			if(i==militaryBuilding.size()) {
				Barracks newBarracks = new Barracks();
				if(this.getTreasury()<newBarracks.getCost()) {
					throw new NotEnoughGoldException("Not Enough Gold");
				}
				if(this.getTreasury()>=newBarracks.getCost()) {
					c.getMilitaryBuildings().add(newBarracks);
					this.setTreasury(this.getTreasury()-newBarracks.getCost());
				}
			}
		}else if(type.equals("Stable")) {
			int i = 0;
			for(; i<militaryBuilding.size() ; i++) {
				if(militaryBuilding.get(i) instanceof Stable) {
					break;
				}
			}
			if(i==militaryBuilding.size()) {
				Stable newStable = new Stable();
				if(this.getTreasury()<newStable.getCost()) {
					throw new NotEnoughGoldException("Not Enough Gold");
				}
				if(this.getTreasury()>=newStable.getCost()) {
					c.getMilitaryBuildings().add(newStable);
					this.setTreasury(this.getTreasury()-newStable.getCost());
				}
			}
		}
		
		
	}
	
	
	
	
	public void upgradeBuilding(Building b) throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException{
		
		
		
		
		
		if(b.getUpgradeCost()>this.getTreasury()) {
			throw new NotEnoughGoldException("Not Enough Gold");
		}
		
		if(b.getLevel()==3) {
			throw new MaxLevelException("you reached the maximum level");
		}
		
		if(b.isCoolDown()) {
			throw new BuildingInCoolDownException("the building is coolling down");
		}
		
		
		this.setTreasury(this.getTreasury()-b.getUpgradeCost());
		b.upgrade();		
		
	}
	
	
	public void initiateArmy(City city , Unit unit) {
		
		// adding unit to attacking army
		Army attackingArmy = new Army(city.getName());
		ArrayList<Unit> newUnits = new ArrayList();
		newUnits.add(unit);
		attackingArmy.setUnits(newUnits);
		
		// removing unit from defending army
		Army newDefendingArmy = city.getDefendingArmy();
		ArrayList<Unit> DefendingArmyUnits = newDefendingArmy.getUnits();
		DefendingArmyUnits.remove(unit);
		newDefendingArmy.setUnits(DefendingArmyUnits);
		city.setDefendingArmy(newDefendingArmy);
		
		
		// updata the parent army of the unit 
		unit.setParentArmy(attackingArmy);
		
		//adding the attacking army to controlled armies
		this.controlledArmies.add(attackingArmy);
		
		
		
	}
	
	
	public void laySiege(Army army , City city) throws TargetNotReachedException, FriendlyCityException{
		
		
		
		if(this.getControlledCities().contains(city)) { // city is friendly city
			throw new FriendlyCityException("Friendly exception");
		}
		
		
		if(!army.getCurrentLocation().equals(city.getName())) { // ####################################################
			throw new TargetNotReachedException("target not reached");
		}
		
		army.setCurrentStatus(Status.BESIEGING); // ###############################################
		army.setCurrentLocation(city.getName());
		army.setTarget(city.getName());
		army.setDistancetoTarget(0);
		
		
		
		
		city.setTurnsUnderSiege(0); // ############################################################
		city.setUnderSiege(true);
		
		
		
	} 
	
	
	
}
