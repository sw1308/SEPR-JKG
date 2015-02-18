package com.TeamHEC.LocomotionCommotion.Train;

public class SpeedUpgrade extends TrainUpgrade{
	
	private final int INCREASE_BY = 10;
	
	public SpeedUpgrade(Train train)
	{
		super(train, 100);
	}
	/**
	 * upgrade a train's speed
	 */
	@Override
	public void addUpgrade()
	{
		train.increaseSpeedMod(INCREASE_BY);
	}
	/**
	 * undo a speed upgrade
	 */
	@Override
	public void undoUpgrade()
	{
		train.increaseSpeedMod(-INCREASE_BY);
	}
}
