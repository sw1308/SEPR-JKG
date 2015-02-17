package com.TeamHEC.LocomotionCommotion.Goal;

import com.TeamHEC.LocomotionCommotion.Map.Station;

/**
 * @author Eashan Maheshwari <em948@york.ac.uk>
 *
 */

public class CargoGoal extends SpecialGoal {

	/**
	 * Initialises the goal.
	 * @param startStation The Station the goal starts from
	 * @param finalStation The Station the goal ends at
	 * @param stationVia The Station the goal wants you to travel via
	 * @param cargo The type of cargo the train is carrying
	 * @param reward2 The reward (currently Gold) you get for completing the Goal
	 * 
	 */
	
	public CargoGoal(Station startStation, Station finalStation, Station stationVia, String cargo, int reward2) {
		super(startStation, finalStation, stationVia, cargo, reward2);
	}

}
