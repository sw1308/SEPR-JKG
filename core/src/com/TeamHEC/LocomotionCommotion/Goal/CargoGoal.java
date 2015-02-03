package com.TeamHEC.LocomotionCommotion.Goal;

import com.TeamHEC.LocomotionCommotion.Map.Station;

public class CargoGoal extends SpecialGoal {

	public CargoGoal(Station startStation, Station finalStation, Station stationVia, String cargo, int reward2) {
		super(startStation, finalStation, stationVia, cargo, reward2);
		this.specialcargo = true;
	}

}
