package com.TeamHEC.LocomotionCommotion.Goal;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.TeamHEC.LocomotionCommotion.Card.Card;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Map.WorldMap;
import com.TeamHEC.LocomotionCommotion.Mocking.GdxTestRunner;
import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Resource.Coal;
import com.TeamHEC.LocomotionCommotion.Resource.Electric;
import com.TeamHEC.LocomotionCommotion.Resource.Gold;
import com.TeamHEC.LocomotionCommotion.Resource.Nuclear;
import com.TeamHEC.LocomotionCommotion.Resource.Oil;
import com.TeamHEC.LocomotionCommotion.Train.OilTrain;
import com.TeamHEC.LocomotionCommotion.Train.Route;
import com.TeamHEC.LocomotionCommotion.Train.Train;

@RunWith(GdxTestRunner.class)
public class CargoGoalTest {

	Goal goal;
	Train train;
	WorldMap wm;
	Station ss,fs;
	@Before
	public void setUp() throws Exception {
		wm = WorldMap.getInstance();
		GoalFactory gf = new GoalFactory(1);
		goal = gf.CreateRandomGoal();
		while (goal.specialcargo == false){
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
	public void testSpecialCargoGoal(){
		assertTrue(goal.specialcargo); //specialcargo Flag should be set to True on the goal's creation
		System.out.println("CargoGoal has been successfully initialized : " + goal.specialcargo);
	}
	
	@Test
	public void testCargoGoal(){
		assertTrue(goal.getCargo() == "Special (Diamond)"); 
		System.out.println("The Goal's Type is : " + goal.getCargo());
	}
	
	@Test
	public void testGoalStations() { 
		assertTrue(compareStations(goal.getSStation())); //if the Cargo Goal has a start station
		assertTrue(compareStations(goal.getFStation())); //if the Cargo Goal has a finish station
//		assertTrue(goal.stationPassed(ss, train);
	}
  	
	@Test
	public void testAssignTrain() {
		assertTrue("", goal.getTrain() == train); //Cargo Goal has been successfully been assigned to a train
		//System.out.println(goal.getTrain().getSpeed());
	}
	
	
	@Test
	 public void testgetReward(){
		   assertTrue(goal.getReward() > 0); //Cargo Goal's reward is successfully generated and is greater than zero
	   }
	
	@Test
   public void testisSpecial(){
	   assertTrue(goal.isSpecial() == true); //On initialisation of CargoGoal the Isspecial bool of Goal class should be set to true 
	   
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

//@Test 
//public void testgetStartDate(){
	//assertTrue( goal.getStartDate() != null);
//}

