package com.TeamHEC.LocomotionCommotion.Goal;

import com.TeamHEC.LocomotionCommotion.GameData;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Train.RouteListener;
import com.TeamHEC.LocomotionCommotion.Train.Train;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;
import com.TeamHEC.LocomotionCommotion.UI_Elements.GameScreenUI;
import com.TeamHEC.LocomotionCommotion.Game.GameScreen;

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
	private int startTurn;
	private int turnLimit;

	// Variables used to track Goal completion:
	private Train train;	
	private boolean startStationPassed;
	private boolean finalStationPassed;
	
	
	//Variables used to track special Goal completion
	private boolean stationViaPassed;
	private boolean withinTurnLimit;
	
	
	public static GoalActor goalActor;
	
	/**
	 * Initialises a standard goal.
	 * @param startStation The Station the goal starts from
	 * @param finalStation The Station the goal ends at
	 * @param stationVia The Station the goal wants you to travel via
	 * @param cargo The type of cargo the train is carrying
	 * @param reward The reward (currently Gold) you get for completing the Goal
	 */
	
	//Overloaded constructor to allow instantiation of goals without turnLimits.
	public Goal(Station startStation, Station finalStation, Station stationVia, String cargo, int reward) {
		this(startStation, finalStation, stationVia, cargo, reward, 0);
	}

	/**
	 * Initialises a turn-limited goal.
	 * @param startStation The Station the goal starts from
	 * @param finalStation The Station the goal ends at
	 * @param stationVia The Station the goal wants you to travel via
	 * @param cargo The type of cargo the train is carrying
	 * @param reward The reward (currently Gold) you get for completing the Goal
	 * @param turnLimit The number of turns allowed to complete the goal
	 */
	public Goal(Station startStation, Station finalStation, Station stationVia, String cargo, int reward, int turnLimit) {
		this.sStation = startStation;
		this.fStation = finalStation;
		this.stationVia = stationVia;
		this.setSpecial(false);
		this.reward = reward;  
		this.cargo = cargo;
		
        this.startTurn = 0;
        this.turnLimit = turnLimit;
        this.withinTurnLimit = true;
        		
		// initialise goal completion variables to false
		startStationPassed = false;
		if(stationVia == null)
		stationViaPassed = true; //does not exist hence always passed 
		else
		stationViaPassed = false;
		finalStationPassed = false;
	}
	
	/**
	 * Gets if the goal is a special goal
	 * @return Boolean special
	 */
	public boolean isSpecial()
	{
		return special;
	}
	
	/**
	 * Gets the start station
	 * @return Station
	 */
	public String getSStation()
	{
		return this.sStation.getName();
	}
	
	/**
	 * Gets the finish station for the goal
	 * @return Station
	 */
	public String getFStation()
	{
		return this.fStation.getName();
	}
	
	/**
	 * Gets the reward for the goal
	 * @return Int reward
	 */
	public int getReward()
	{
		return reward;
	}
	
	/**
	 * Gets the start turn for the station
	 * @return	Int start turn
	 */
	public int getStartTurn() {
		return startTurn;
	}
	
	/**
	 * Gets the turn limit for the goal
	 * @return turnLimit
	 */
	public int getTurnLimit() {
		return turnLimit;
	}
	
	/**
	 * Sets the goal actor for the goal
	 * @param actor the actor for this goal
	 */
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
		this.startTurn = GameData.turnCount;
		this.train = train;
		train.route.register(this);

		if(train.route.getStation() == sStation)
			startStationPassed = true;

		if (this instanceof CargoGoal) 
		{
			train.backupSpeedMod(); //keeps track of original train speed mod before the train being assigned a diamonds goal 
			int dec = (int) ( train.getSpeed() / 10 ); // decrease in speed by 10% for special diamond goals 
			train.decreaseSpeedMod(dec);
		}

	}
	
	/**
	 * Gets the train assigned to the goal
	 * @return Train
	 */
	public Train getTrain()
	{
		return train;
	} 
	
	/**
	 * Called when the goal is successfully complete, giving the player the appropriate amount of Gold and Score.
	 */	
	public void goalComplete()
	{
		WarningMessage.fireWarningWindow("GOAL COMPLETE!", "You've successfully complete the route: " + getSStation()
				+ " to " + getFStation() + "\n you've won " + getReward());
		
		train.getOwner().addGold(getReward());
		train.getOwner().addScore(getReward()/100);
		GameScreenUI.playerScore.setText("Turn "+ GameData.turnCount + "   " + GameScreen.game.getPlayer1().getName()+ "    " + GameScreen.game.getPlayer1().getScore() + "     SCORE     " + GameScreen.game.getPlayer2().getScore() + "     "+ GameScreen.game.getPlayer2().getName() + "     " + GameScreen.game.getPlayerTurn().getName()+" it's your turn ");
		train.route.unregister(this);
		
		train.getOwner().getGoals().remove(this);
		
		startStationPassed = false;
		stationViaPassed = false;
		finalStationPassed = false;
		withinTurnLimit = true;
		
		if (this instanceof CargoGoal) {
			train.restoreSpeedMod();
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

	/**
	 * Sets the special flag for the goal
	 * @param special A boolean value for the special flag to be set to
	 */
	public void setSpecial(boolean special) {
		this.special = special;
	}
}

