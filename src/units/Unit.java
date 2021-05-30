package units;

import exceptions.FriendlyFireException;

abstract public class Unit {

	private int level;
	private int maxSoldierCount;
	private int currentSoldierCount = maxSoldierCount;
	private double idleUpkeep;
	private double marchingUpkeep;
	private double siegeUpkeep;
	private Army parentArmy;
	
	
	public Army getParentArmy() {
		return this.parentArmy;
	}
	
	public void setParentArmy(Army a) {
		this.parentArmy = a;
	}
	
	
	public int getLevel() {
		return level;
	}
	
	public int getMaxSoldierCount() {
		return maxSoldierCount;
	}
	
	public int getCurrentSoldierCount() {
		return currentSoldierCount;
	}
	
	public double getIdleUpkeep() {
		return idleUpkeep;
	}
	
	public double getMarchingUpkeep() {
		return marchingUpkeep;
	}
	
	public double getSiegeUpkeep() {
		return siegeUpkeep;
	}
	
	public void setCurrentSoldierCount(int value) {
		this.currentSoldierCount = value;
	}
	
	
	public Unit(int level , int maxSoldierCount , double idleUpkeep , double marchingUpkeep , double siegeUpkeep) {
		this.level = level;
		this.maxSoldierCount = maxSoldierCount;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
	}
	
	
	public void attack(Unit target) throws FriendlyFireException{
		
		if(this instanceof Archer) {
			if(this.getLevel()==1) {
				if(target instanceof Archer) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.3*this.currentSoldierCount));
				}else if(target instanceof Infantry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.2*this.currentSoldierCount));
				}else if(target instanceof Cavalry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.1*this.currentSoldierCount));
				}
			}else if(this.getLevel()==2) {
				if(target instanceof Archer) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.4*this.currentSoldierCount));
				}else if(target instanceof Infantry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.3*this.currentSoldierCount));
				}else if(target instanceof Cavalry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.1*this.currentSoldierCount));
				}
			}else {
				if(target instanceof Archer) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.5*this.currentSoldierCount));
				}else if(target instanceof Infantry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.4*this.currentSoldierCount));
				}else if(target instanceof Cavalry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.2*this.currentSoldierCount));
				}
			}
			
		}else if(this instanceof Infantry) {
			if(this.getLevel()==1) {
				if(target instanceof Archer) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.3*this.currentSoldierCount));
				}else if(target instanceof Infantry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.1*this.currentSoldierCount));
				}else if(target instanceof Cavalry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.1*this.currentSoldierCount));
				}
			}else if(this.getLevel()==2) {
				if(target instanceof Archer) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.4*this.currentSoldierCount));
				}else if(target instanceof Infantry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.2*this.currentSoldierCount));
				}else if(target instanceof Cavalry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.2*this.currentSoldierCount));
				}
			}else {
				if(target instanceof Archer) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.5*this.currentSoldierCount));
				}else if(target instanceof Infantry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.3*this.currentSoldierCount));
				}else if(target instanceof Cavalry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.25*this.currentSoldierCount));
				}
			}
			
		}else if(this instanceof Cavalry) {
			if(this.getLevel()==1) {
				if(target instanceof Archer) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.5*this.currentSoldierCount));
				}else if(target instanceof Infantry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.3*this.currentSoldierCount));
				}else if(target instanceof Cavalry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.2*this.currentSoldierCount));
				}
			}else if(this.getLevel()==2) {
				if(target instanceof Archer) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.6*this.currentSoldierCount));
				}else if(target instanceof Infantry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.4*this.currentSoldierCount));
				}else if(target instanceof Cavalry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.2*this.currentSoldierCount));
				}
			}else {
				if(target instanceof Archer) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.7*this.currentSoldierCount));
				}else if(target instanceof Infantry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.5*this.currentSoldierCount));
				}else if(target instanceof Cavalry) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.3*this.currentSoldierCount));
				}
			}
		}
		
	}
	
	
	
	
	
}
