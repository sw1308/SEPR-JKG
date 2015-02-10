package com.TeamHEC.LocomotionCommotion.Goal;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.TeamHEC.LocomotionCommotion.Card.Card;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Map.WorldMap;
import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Resource.Coal;
import com.TeamHEC.LocomotionCommotion.Resource.Electric;
import com.TeamHEC.LocomotionCommotion.Resource.Gold;
import com.TeamHEC.LocomotionCommotion.Resource.Nuclear;
import com.TeamHEC.LocomotionCommotion.Resource.Oil;
import com.TeamHEC.LocomotionCommotion.Train.OilTrain;
import com.TeamHEC.LocomotionCommotion.Train.Route;
import com.TeamHEC.LocomotionCommotion.Train.Train;

public class TimedGoalTest {

	Goal goal;
	Train train;
	WorldMap wm;
	Station ss,fs;
	@Before
	public void setUp() throws Exception {
		wm = WorldMap.getInstance();
		GoalFactory gf = new GoalFactory(1);
		goal = gf.CreateRandomGoal();
		
		while(!(goal instanceof TimedGoal)) {
			goal = gf.CreateRandomGoal();
		}
		
		
		String name = "Player 1";
		int points = 0;
		Gold gold = new Gold(1000);
		Coal coal = new Coal(200);
		Oil oil = new Oil(200);
		Electric electric = new Electric(200);
		Nuclear nuclear = new Nuclear(200);
		ArrayList<Card> cards = new ArrayList<Card>();
		ArrayList<Goal> goals = new ArrayList<Goal>();
		ArrayList<Train> trains = new ArrayList<Train>();
		
		Player player = new Player(
				name,
				points,
				gold,
				coal,
				electric,
				nuclear,
				oil,
				cards,	
				goals,
				trains);
		
		
		train = new OilTrain(0, true, new Route(WorldMap.getInstance().AMSTERDAM), player);
		
		goal.assignTrain(train);
		
	}

	@Test
	public void testGoalStations() { 
		assertTrue(compareStations(goal.getSStation())); //if the Combo Goal has a start station
		assertTrue(compareStations(goal.getFStation())); //if the Combo Goal has a finish station
//		assertTrue(goal.stationPassed(ss, train);
	}
  	
	@Test
	public void testAssignTrain() {
		assertTrue("", goal.getTrain() == train); //Combo Goal has been successfully been assigned to a train
		//System.out.println(goal.getTrain().getSpeed());
	}
	
	
	@Test
	public void testgetReward(){
		assertTrue(goal.getReward() > 0); //Combo Goal's reward is successfully generated and is greater than zero
	}
	
	@Test
	public void testisSpecial(){
		assertTrue(goal.isSpecial() == true); //On initialisation of ComboGoal the Isspecial bool of Goal class should be set to true 
	
	}
	
	public void testTurnLimit() {
		assertTrue(goal.getTurnLimit() > 0); //On initialisation the goal would be allocated a turn limit that is greater than 0
	}
	
	public void testStartTurn() {
		assertTrue(goal.getStartTurn() > 0); //If the Combo Goal has been allocated a start turn then it would be greater than 0
	}

	public boolean compareStations(String Sname){
		 for (int i = 0; i < wm.stationsList.size(); i++){
			 if (Sname == wm.stationsList.get(i).getName()){
				 return true;
			 }
	 	 }
		 return false;
		 
	 }
}
