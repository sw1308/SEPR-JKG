package com.TeamHEC.LocomotionCommotion.Goal;

import com.TeamHEC.LocomotionCommotion.GameData;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Train.RouteListener;
import com.TeamHEC.LocomotionCommotion.Train.Train;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;

/**
 * @author Sam Watkins <sw1308@york.ac.uk>
 * @author Eashan Maheshwari <em948@york.ac.uk>
 * @author Sam Anderson <sa902@york.ac.uk>
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 *
 */
public class Goal implements RouteListener{ 
	//Variables
	protected Station sStation;
	protected Station fStation;
	protected Station stationVia;
	private String cargo;
	protected boolean special;
	private int reward;
	private String startDate;
	private int startTurn;
	private int turnLimit;

	// Variables used to track Goal completion:
	private Train train;	
	private boolean startStationPassed;
	private boolean finalStationPassed;
	
	
	//Variables used to track special Goal completion
	private boolean stationViaPassed;
	private boolean withinTurnLimit;
	protected boolean specialcargo;
	protected int ospeedmod; //Used to keep track of original speedmod of train during special cargo goal
	
	
	public static GoalActor goalActor;
	
	/**
	 * Initialises the goal.
	 * @param startStation The Station the goal starts from
	 * @param finalStation The Station the goal ends at
	 * @param stationVia The Station the goal wants you to travel via
	 * @param cargo The type of cargo the train is carrying
	 * @param reward The reward (currently Gold) you get for completing the Goal
	 * @param startTurn The turn number that the goal is started on
	 * @param turnLimit The number of turns allowed to complete the goal
	 */
	
	//Overloaded constructor to allow instantiation of goals without turnLimits.
	public Goal(Station startStation, Station finalStation, Station stationVia, String cargo, int reward) {
		this(startStation, finalStation, stationVia, cargo, reward, 0);
	}

	public Goal(Station startStation, Station finalStation, Station stationVia, String cargo, int reward, int turnLimit) {
		this.sStation = startStation;
		this.fStation = finalStation;
		this.stationVia = stationVia;
		this.setSpecial(false);
		this.reward = reward;  
		this.cargo = cargo;
		
        startDate = "1"; //initialized to 1, not yet implemented.
        this.startTurn = GameData.turnCount;
        this.turnLimit = turnLimit;
        this.withinTurnLimit = true;
        
        //initialised as false
        this.specialcargo = false ;
        		
		// initialise goal completion variables to false
		startStationPassed = false;
		if(stationVia == null)
		stationViaPassed = true; //does not exist hence always passed 
		else
		stationViaPassed = false;
		finalStationPassed = false;
	}

	public boolean isSpecial()
	{
		return special;
	}

	public String getSStation()
	{
		return this.sStation.getName();
	}
	
	public String getFStation()
	{
		return this.fStation.getName();
	}

	public int getReward()
	{
		return reward;
	}
	
	public int getStartTurn() {
		return startTurn;
	}
	
	public int getTurnLimit() {
		return turnLimit;
	}
	
	public void setActor(GoalActor actor)
	{
		goalActor = actor;
	}
	
	/**
	 * Returns the name of the viaStation. Returns "Any" if StationVia is null.
	 * @return The name of the viaStation. Returns "Any" if StationVia is null.
	 */
	public String getVia()
	{
		if(stationVia == null)
			return "Any";
		else
			return stationVia.getName();
	}
	public String getCargo()
	{
		return cargo;
	}

	/**
	 * Assigns a goal to a train and registers listeners
	 * @param train The train to assign to
	 * 
	 */
	public void assignTrain(Train train)
	{
		this.train = train;
		train.route.register(this);

		if(train.route.getStation() == sStation)
			startStationPassed = true;

		if (this.specialcargo) 
		{
			this.ospeedmod = this.train.getSpeedMod(); //keeps track of original train speed mod before the train being assigned a diamonds goal 
			int dec = (int) ( this.train.getSpeed() / 10 ); // decrease in speed by 10% for special diamond goals 
			this.train.decreaseSpeedMod(dec);
		}

	}

	public Train getTrain()
	{
		return train;
	}
	
	/**
	 * Called when the goal is successfully complete
	 */	
	public void goalComplete()
	{
		WarningMessage.fireWarningWindow("GOAL COMPLETE!", "You've successfully complete the route: " + getSStation()
				+ " to " + getFStation() + "\n you've won " + getReward());
		
		train.getOwner().addGold(getReward());
		train.getOwner().addScore(getReward());
		train.route.unregister(this);
		
		train.getOwner().getGoals().remove(this);
		
		//if(goalActor != null)
		//{
		//	goalActor.setPlanRouteButtonVisible(false);
		//}
		
		startStationPassed = false;
		stationViaPassed = false;
		finalStationPassed = false;
		withinTurnLimit = true;
		
		if (specialcargo) {
		specialcargo = false;
		train.setSpeedMod(this.ospeedmod);
		}
		
	}
	
	/**
	 * Called when the goal has been failed
	 */
	public void goalFailed() {
		WarningMessage.fireWarningWindow("GOAL FAILED!", "You failed to complete the route: " + getSStation()
				+ " to " + getFStation() + "\n Better luck next time!");
		
		train.route.unregister(this);
		
		train.getOwner().getGoals().remove(this);
		
		startStationPassed = false;
		stationViaPassed = false;
		finalStationPassed = false;
		withinTurnLimit = true;
	}
	
	/**
	 * Listener trigger when a train passes a station
	 */
	@Override
	public void stationPassed(Station station, Train train)
	{
		if(train == this.train)
		{
			System.out.println(train.getName() +" passed " + station.getName());
			
			if(station.equals(sStation)) {
				startStationPassed = true;
				System.out.println("start passed");
			}
			if(startStationPassed && station.equals(fStation)) {
				finalStationPassed = true;
				System.out.println("final passed");
			}
			if(stationVia == null || (startStationPassed && station.equals(stationVia))) {
				stationViaPassed = true;
				System.out.println("via passed");
			}
			if( turnLimit == 0 || startTurn + (turnLimit * 2) > GameData.turnCount) {
				withinTurnLimit = true;
				System.out.println("within turn limit");
			} else {
				withinTurnLimit = false;
				System.out.println("turn limit exceeded, goal failed");
				goalFailed();
			}
			if(startStationPassed && finalStationPassed && stationViaPassed && withinTurnLimit)
				goalComplete();
		}
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}
}

