package com.TeamHEC.LocomotionCommotion.Train;

import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Resource.Coal;

/**
 * 
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 *
 */

public class CoalTrain extends Train {

	private static final int BASE_SPEED = 70;
	private static final int VALUE = 200;
		
	public CoalTrain(int speedMod, boolean inStation, Route route, Player player)
	{
		super("Steam Machine", new Coal(200), BASE_SPEED, speedMod, VALUE,
				inStation, route, player);
		fuelPerTurn = 10;
	}
}
