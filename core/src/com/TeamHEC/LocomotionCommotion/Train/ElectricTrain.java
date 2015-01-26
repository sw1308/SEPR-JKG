package com.TeamHEC.LocomotionCommotion.Train;

import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Resource.Electric;

/**
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 */
public class ElectricTrain extends Train{
	
	private static final int BASE_SPEED = 100;
	private static final int VALUE = 500;

	public ElectricTrain(int speedMod, boolean inStation, Route route, Player player)
	{
		// Name, Fuel, baseSpeed, speedMod, baseCarriageLimit, carriageLimitMod, value, inStation
		super("Electrix", new Electric(200), BASE_SPEED, speedMod, VALUE, inStation,
				route, player);
		fuelPerTurn = 20;
	}
}
