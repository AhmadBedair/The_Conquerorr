package buildings;

public class Market extends EconomicBuilding{

	public Market() {
		super(1500,700);
	}
	
	
	
	public int harvest() {
		if(this.getLevel()==1) {
			return 1000;
		}else if (this.getLevel()==2) {
			return 1500;
		}else {
			return 2000;
		}
	}
	
}
