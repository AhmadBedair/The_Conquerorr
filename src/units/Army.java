package units;

import java.util.ArrayList;

import exceptions.MaxCapacityException;

public class Army {
	
	private Status currentStatus = Status.IDLE;
	private ArrayList<Unit> units;
	private int distancetoTarget=-1;
	private String target = "";
	private String currentLocation;
	private final int maxToHold = 10;
	

	
	
	public Status getCurrentStatus() {
		return this.currentStatus;
	}
	
	public ArrayList getUnits() {
		return units;
	}
	
	public int getDistancetoTarget() {
		return this.distancetoTarget;
	}
	
	public String getTarget() {
		return this.target;
	}
	
	public String getCurrentLocation() {
		return this.currentLocation;
	}
	
	public int getMaxToHold(){
		return this.maxToHold;
	}
	
	public void setCurrentStatus(Status value) {
		this.currentStatus = value;
	}
	
	public void setUnits(ArrayList<Unit> value) {
		this.units = value;
	}
	
	public void setDistancetoTarget(int value) {
		this.distancetoTarget = value;
	}
	
	public void setTarget(String value) {
		this.target = value;
	}
	
	public void setCurrentLocation(String value) {
		this.currentLocation = value;
	}
	
	
	
	
	public Army(String currentLocation) {
		this.currentLocation = currentLocation;
		units = new ArrayList<>();
	}
	
	public void relocateUnit(Unit unit) throws MaxCapacityException{
		
	}
	
	public void handleAttackedUnit(Unit u) {
		
	}
	
	public double foodNeeded() {
		double out = 0;
		if(this.getCurrentStatus()==Status.IDLE) {
			for(int i = 0 ; i<units.size();i++) {
				if(units.get(i) instanceof Archer) {
					out += units.get(i).getIdleUpkeep()*units.get(i).getCurrentSoldierCount();
				}else if(units.get(i) instanceof Infantry) {
					out += units.get(i).getIdleUpkeep()*units.get(i).getCurrentSoldierCount();
				}else {
					out += units.get(i).getIdleUpkeep()*units.get(i).getCurrentSoldierCount();
				}
			}
		}else if(this.getCurrentStatus()==Status.MARCHING){
			for(int i = 0 ; i<units.size();i++) {
				if(units.get(i) instanceof Archer) {
					out += units.get(i).getMarchingUpkeep()*units.get(i).getCurrentSoldierCount();
				}else if(units.get(i) instanceof Infantry) {
					out += units.get(i).getMarchingUpkeep()*units.get(i).getCurrentSoldierCount();
				}else {
					out += units.get(i).getMarchingUpkeep()*units.get(i).getCurrentSoldierCount();
				}
			}
		}else {
			for(int i = 0 ; i<units.size();i++) {
				if(units.get(i) instanceof Archer) {
					out += units.get(i).getSiegeUpkeep()*units.get(i).getCurrentSoldierCount();
				}else if(units.get(i) instanceof Infantry) {
					out += units.get(i).getSiegeUpkeep()*units.get(i).getCurrentSoldierCount();
				}else {
					out += units.get(i).getSiegeUpkeep()*units.get(i).getCurrentSoldierCount();
				}
			}
		}
		
		return out;
		
	}
	
	
}
