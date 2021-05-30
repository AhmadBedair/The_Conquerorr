package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Unit;

abstract public class MilitaryBuilding extends Building {

	private int recruitmentCost;
	private int currentRecruit;
	private final int maxRecruit = 3;
	
	public MilitaryBuilding(int cost , int upgradeCost , int recruitmentCost) {
		super(cost,upgradeCost);
		this.recruitmentCost = recruitmentCost;
	}
	
	
	public int getRecruitmentCost() {
		return recruitmentCost;
	}
	
	public void setRecruitmentCost(int value) {
		this.recruitmentCost = value;
	}
	
	public int getCurrentRecruit() {
		return currentRecruit;
	}
	
	public void setCurrentRecruit(int value) {
		this.currentRecruit = value;
	}
	
	public int getMaxRecruit() {
		return maxRecruit;
	}
	
	public void upgrade() throws BuildingInCoolDownException , MaxLevelException{
		if(this instanceof ArcheryRange && this.getLevel()==1) {
			this.setRecruitmentCost(450);
			this.setLevel(2);
			this.setUpgradeCost(700);
		}else if(this instanceof ArcheryRange && this.getLevel()==2) {
			this.setLevel(3);
			this.setRecruitmentCost(500);
		}else if(this instanceof Barracks && this.getLevel()==1) {
			this.setLevel(2);
			this.setRecruitmentCost(550);
			this.setUpgradeCost(1500);
		}else if(this instanceof Barracks && this.getLevel()==2) {
			this.setLevel(3);
			this.setRecruitmentCost(600);
		}else if(this instanceof Stable && this.getLevel()==1) {
			this.setLevel(2);
			this.setRecruitmentCost(650);
			this.setUpgradeCost(2000);
		}else if(this instanceof Stable && this.getLevel()==2) {
			this.setLevel(3);
			this.setRecruitmentCost(700);
		}
		
		this.setCoolDown(false);
	}
	
	public abstract Unit recruit() throws BuildingInCoolDownException , MaxRecruitedException;
	
	
}
