package com.TeamHEC.LocomotionCommotion.Train;

import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Resource.Oil;

/**
 * 
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 *
 */

public class OilTrain extends Train{

	private static final int BASE_SPEED = 80;
	private static final int VALUE = 350;

	public OilTrain(int speedMod, boolean inStation, Route route, Player player)
	{
		super("Diesel Weasel", new Oil(200), BASE_SPEED, speedMod, VALUE, inStation,
				route, player);
		fuelPerTurn = 15;
	}
}
