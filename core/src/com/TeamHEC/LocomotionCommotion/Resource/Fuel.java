package com.TeamHEC.LocomotionCommotion.Resource;

/**
 * 
 *  @author Matthew Taylor <mjkt500@york.ac.uk>
 *	The object representing Fuel. All fuel types inherit from this.
 *
 */
public abstract class Fuel extends Resource{

	public int cost;
	
	/**
	 * Initialises Fuel
	 * @param value The initial value of the object.
	 * @param type The type of Fuel as a String.
	 */
	public Fuel(int value, String type)
	{
		super(value, type);		
	}
}
