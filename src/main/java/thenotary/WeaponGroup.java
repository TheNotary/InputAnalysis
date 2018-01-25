package thenotary;

import java.util.LinkedList;

public class WeaponGroup extends LinkedList<Weapon> {
	static final long serialVersionUID = 1002;
	
	private int transitionState = 0;
	
	public int getTransitionState() {
		return transitionState;
	}
	
	public void setTransitionState(int value) {
		transitionState = value;
	}
}
