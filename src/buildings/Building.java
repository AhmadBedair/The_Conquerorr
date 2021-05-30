package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

abstract public class Building {
	private int cost;
	private int level;
	private int upgradeCost;
	private boolean coolDown;
	
	
	
	
	public Building(int cost , int upgradeCost) {
		this.cost = cost;
		this.upgradeCost = upgradeCost;
		this.level = 1;
		this.coolDown = true;
	}
	
	
	public int getCost() {
		return cost;
	}
	
	
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int value) {
		this.level = value;
	}
	
	
	public int getUpgradeCost() {
		return upgradeCost;
	}
	
	public void setUpgradeCost(int value) {
		this.upgradeCost = value;
	}
	
	
	public boolean isCoolDown() {
		return coolDown;
	}

	public void setCoolDown(boolean inCooldown) {
		this.coolDown = inCooldown;
	}
	
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
		if(this instanceof Farm) {
			this.setLevel(2);
			this.setUpgradeCost(700);
		}else if (this instanceof Market) {
			this.setLevel(2);
			this.setUpgradeCost(1000);
		}
		this.setCoolDown(false);
	}
	
	
	
	
	
}
