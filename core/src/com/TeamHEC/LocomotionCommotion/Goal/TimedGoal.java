package com.TeamHEC.LocomotionCommotion.Goal;

import com.TeamHEC.LocomotionCommotion.Map.Station;

public class TimedGoal extends SpecialGoal {

	public TimedGoal(Station startStation, Station finalStation, Station stationVia, String cargo, int reward, int turnLimit) {
		super(startStation, finalStation, stationVia, cargo, reward, turnLimit);
	}

}
