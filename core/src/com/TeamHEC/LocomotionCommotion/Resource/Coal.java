package com.TeamHEC.LocomotionCommotion.Resource;

/**
 * 
 *  @author Matthew Taylor <mjkt500@york.ac.uk>
 *	The object representing Coal fuel.
 *
 */
public class Coal extends Fuel {

	/**
	 * Initialises Coal
	 * @param value The initial value of the object.
	 */
	public Coal(int value)
	{
		super(value, "Coal");
		cost = 90;
	}
}
