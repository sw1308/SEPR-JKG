package com.TeamHEC.LocomotionCommotion.Resource;

/**
 * 
 *  @author Matthew Taylor <mjkt500@york.ac.uk>
 *	The object representing Electric fuel.
 *
 */
public class Electric extends Fuel{

	/**
	 * Initialises Electric
	 * @param value The initial value of the object.
	 */
	public Electric(int value)
	{
		super(value, "Electric");
		cost = 150;
	}
}
