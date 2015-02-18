package com.TeamHEC.LocomotionCommotion.Resource;

/**
 * 
 *  @author Matthew Taylor <mjkt500@york.ac.uk>
 *	The Resource class for which all Resources inherit from (not including Cards).
 *
 */
public abstract class Resource {

	private int value;
	private String type;
	
	/**
	 * The initialiser for Resource.
	 * @param value The initial value of the object.
	 * @param type The type of resource as a String.
	 */
	public Resource(int value, String type)
	{
		this.value = value;
		this.type = type;
	}
	
	/**
	 * 
	 * @return the amount of the resource
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * 
	 * @return the type of that resource
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * 
	 * @param value the new value that the resource will be set to
	 */
	public void setValue(int value)
	{
		this.value = value;
	}
	
	/**
	 * Subtracts from value using an integer
	 * @param subVal An integer representing the amount to subtract from value.
	 */
	public void subValue(int subVal) 
	{
		this.value = this.value - subVal;		
	}
	
	/**
	 * Subtracts from value using another Resource object.
	 * @param subResource A Resource object whose value is subtracted from this object's value.
	 */
	public void subValue(Resource subResource)
	{
		this.value = this.value - subResource.getValue();
	}
	
	/**
	 * Adds to value using an integer
	 * @param addVal An integer representing the amount to add to value.
	 */
	public void addValue(int addVal) 
	{
		this.value = this.value + addVal;		
	}
	
	/**
	 * Adds to value using another Resource object.
	 * @param addResource A Resource object whose value is added to this object's value.
	 */
	public void addValue(Resource addResource)
	{
		this.value = this.value + addResource.getValue();
	}
}