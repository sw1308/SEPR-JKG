package com.TeamHEC.LocomotionCommotion.Goal;

import com.TeamHEC.LocomotionCommotion.Map.Station;

/**
 * @author Sam Watkins <sw1308@york.ac.uk>
 *
 */

public class ComboGoal extends SpecialGoal {

	/**
	 * Initialises the goal.
	 * @param startStation The Station the goal starts from
	 * @param finalStation The Station the goal ends at
	 * @param stationVia The Station the goal wants you to travel via
	 * @param cargo The type of cargo the train is carrying
	 * @param reward The reward (currently Gold) you get for completing the Goal
	 * @param turnLimit The number of turns allowed to complete the goal
	 */
	
	public ComboGoal(Station startStation, Station finalStation, Station stationVia, String cargo, int reward, int turnLimit) {
		super(startStation, finalStation, stationVia, cargo, reward, turnLimit);
	}

}
