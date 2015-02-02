package com.TeamHEC.LocomotionCommotion.Goal;

import com.TeamHEC.LocomotionCommotion.Map.Station;

public class SpecialGoal extends Goal{
 
	public SpecialGoal(Station startStation, Station finalStation, Station stationVia, String cargo, int reward2) {
		this(startStation, finalStation, stationVia, cargo, reward2, 0);
	}
	
	 public SpecialGoal(Station startstation, Station finalStation, Station stationVia, String cargo, int reward2, int turnLimit)
	 {
		 super(startstation, finalStation, stationVia , cargo, reward2);  
		 this.setSpecial(true);
	 }
}