package com.TeamHEC.LocomotionCommotion.Goal;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.TeamHEC.LocomotionCommotion.Card.Card;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Map.WorldMap;
import com.TeamHEC.LocomotionCommotion.Mocking.GdxTestRunner;
import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Resource.Score;
import com.TeamHEC.LocomotionCommotion.Resource.Coal;
import com.TeamHEC.LocomotionCommotion.Resource.Electric;
import com.TeamHEC.LocomotionCommotion.Resource.Gold;
import com.TeamHEC.LocomotionCommotion.Resource.Nuclear;
import com.TeamHEC.LocomotionCommotion.Resource.Oil;
import com.TeamHEC.LocomotionCommotion.Train.OilTrain;
import com.TeamHEC.LocomotionCommotion.Train.Route;
import com.TeamHEC.LocomotionCommotion.Train.Train;

@RunWith(GdxTestRunner.class)
public class RouteGoalTest {

	Goal goal;
	Train train;
	WorldMap wm;
	Station ss,fs;
	@Before
	public void setUp() throws Exception {
		wm = WorldMap.getInstance();
		GoalFactory gf = new GoalFactory(1);
		goal = gf.CreateRandomGoal();
		
		while(!(goal instanceof RouteGoal)) {
			goal = gf.CreateRandomGoal();
		}
		
		
		String name = "Player 1";
		Score score = new Score(0);
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
				score,
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
		assertTrue(compareStations(goal.getSStation())); //if the RouteGoal has a start station
		assertTrue(compareStations(goal.getVia())); //if the RouteGoal has a via station
		assertTrue(compareStations(goal.getFStation())); //if the RouteGoal has a finish station
	}
  	
	@Test
	public void testAssignTrain() {
		assertTrue("", goal.getTrain() == train); //RouteGoal has been successfully been assigned to a train
	}
	
	
	@Test
	public void testgetReward(){
		assertTrue(goal.getReward() > 0); //RouteGoal's reward is successfully generated and is greater than zero
	}
	
	@Test
	public void testisSpecial(){
		assertTrue(goal.isSpecial() == true); //On initialisation of RouteGoal the isSpecial bool of Goal class should be set to true 
	
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
