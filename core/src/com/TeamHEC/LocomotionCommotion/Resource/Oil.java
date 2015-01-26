package com.TeamHEC.LocomotionCommotion.Resource;

/**
 * 
 *  @author Matthew Taylor <mjkt500@york.ac.uk>
 *	The object representing Oil fuel.
 *
 */
public class Oil extends Fuel{

	/**
	 * Initialises Oil
	 * @param value The initial value of the object.
	 */
	public Oil(int value)
	{
		super(value, "Oil");
		cost = 100;
	}
}
