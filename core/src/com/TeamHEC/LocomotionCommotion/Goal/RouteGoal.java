package com.TeamHEC.LocomotionCommotion.Goal;

import com.TeamHEC.LocomotionCommotion.Map.Station;

public class RouteGoal extends SpecialGoal {

	public RouteGoal(Station startStation, Station finalStation, Station stationVia, String cargo, int reward) {
		super(startStation, finalStation, stationVia, cargo, reward);
	}

}
