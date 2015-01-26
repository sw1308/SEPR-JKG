package com.TeamHEC.LocomotionCommotion.Train;

import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Resource.Nuclear;

/**
 * 
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 *
 */

public class NuclearTrain extends Train{
	
	private static final int BASE_SPEED = 120;
	private static final int VALUE = 750;

	public NuclearTrain (int speedMod, boolean inStation, Route route, Player player)
	{
		super("Atom Bomb", new Nuclear(200), BASE_SPEED, speedMod, VALUE, inStation,
				route, player);
		fuelPerTurn = 25;
	}
}
