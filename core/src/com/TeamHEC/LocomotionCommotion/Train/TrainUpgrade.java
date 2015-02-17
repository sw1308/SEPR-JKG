package com.TeamHEC.LocomotionCommotion.Train;

public abstract class TrainUpgrade {
	
	private int price;
	
	public Train train;
	
	public TrainUpgrade(Train train, int price)
	{
		this.train = train;
		this.price = price;
	}
	
	/**
	 * gets the price of an upgrade
	 * @return Int price
	 */
	public int getCost()
	{
		return price;
	}
	
	// These can be overwritten by every subclass
	public void addUpgrade(){}
	public void undoUpgrade(){}
}
