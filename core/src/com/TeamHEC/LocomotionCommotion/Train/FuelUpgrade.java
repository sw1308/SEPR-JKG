package com.TeamHEC.LocomotionCommotion.Train;

public class FuelUpgrade extends TrainUpgrade{
	
	private final int FUEL_DEC = 10;
	
	public FuelUpgrade(Train train)
	{
		super(train, 500);
	}
	/**
	 * upgrade the train
	 */
	@Override
	public void addUpgrade()
	{
		train.decreaseFuelPerTurn(FUEL_DEC);
	}
	/**
	 * undo an upgrade to the train
	 */
	@Override
	public void undoUpgrade()
	{
		train.decreaseFuelPerTurn(-FUEL_DEC);
	}
}
