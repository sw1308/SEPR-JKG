package com.TeamHEC.LocomotionCommotion.Goal;

import com.TeamHEC.LocomotionCommotion.Map.Station;

public class SpecialGoal extends Goal{
 
	 public SpecialGoal(Station Startstation, Station FinalStation, Station stationVia, String cargo, int reward2)
	 {
		 super(Startstation, FinalStation, stationVia , cargo, reward2);  
		 this.setSpecial(true);
	 }
}