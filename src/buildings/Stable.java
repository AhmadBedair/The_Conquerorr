package buildings;

import units.Cavalry;
import units.Infantry;
import units.Unit;

public class Stable extends MilitaryBuilding{
	
	public Stable() {
		super(2500 , 1500 , 600);
	}
	
	public Unit recruit() {
		if(this.getLevel()==1) {
			return new Cavalry(1,40,0.6,0.7,0.75);
		}else if (this.getLevel()==2) {
			return new Cavalry(2,40,0.6,0.7,0.75);
		}else {
			return new Cavalry(3,60,0.7,0.8,0.9);
		}
	}
	

}
