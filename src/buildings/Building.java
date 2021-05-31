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
		if(this.getLevel()==3) {
			throw new MaxLevelException("you already reached the max level");
		}
		
		if(this instanceof Farm && this.getLevel()==1) {
			this.setLevel(2);
			this.setUpgradeCost(500);
		}else if (this instanceof Market && this.getLevel()==1) {
			this.setLevel(2);
			this.setUpgradeCost(700);
		}
		
		else if(this instanceof Farm && this.getLevel()==2) {
			this.setLevel(3);
			this.setUpgradeCost(700);
		}
		else if (this instanceof Market && this.getLevel()==2) {
			this.setLevel(3);
			this.setUpgradeCost(1000);
		}
		
		
		
	}
	
	
	
	
	
}
