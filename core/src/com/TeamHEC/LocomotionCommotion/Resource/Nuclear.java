package com.TeamHEC.LocomotionCommotion.Resource;

/**
 * 
 *  @author Matthew Taylor <mjkt500@york.ac.uk>
 *	The object representing Nuclear fuel.
 *
 */
public class Nuclear extends Fuel{

	/**
	 * Initialises Nuclear
	 * @param value The initial value of the object.
	 */
	public Nuclear(int value)
	{
		super(value, "Nuclear");
		cost = 200;
	}
}
