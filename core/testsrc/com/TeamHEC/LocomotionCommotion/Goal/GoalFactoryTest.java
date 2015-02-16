package com.TeamHEC.LocomotionCommotion.Goal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.TeamHEC.LocomotionCommotion.Goal.GoalFactory;
import com.TeamHEC.LocomotionCommotion.Map.WorldMap;
import com.TeamHEC.LocomotionCommotion.Mocking.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class GoalFactoryTest {
	
	GoalFactory tester;
	@Before
	public void setUp() throws Exception {
		tester = new GoalFactory(1);
	}

	@Test
	public void testCreateRandomGoal() {
		
		//Booleans used to track creation of special goals
		boolean nor = false;
		boolean spl = false;
		boolean cspl = false;
		boolean combog = false;
		boolean routeg = false;
		boolean timedg = false;
		
		for(int i = 0; i < 500; i ++)
		{			
			Goal goal = tester.CreateRandomGoal();
			
						assertTrue(
					"GoalFactory's goal did not have a valid start station, iteration: " + i,
					checkExistence(goal.getSStation()));
			assertTrue(
					"GoalFactory's goal did not have a valid end station, iteration: " + i,
					checkExistence(goal.getFStation()));
			assertTrue(
					"GoalFactory's goal did not have a valid cargo, iteration: " + i,
					goal.getCargo() == "Passenger" || goal.getCargo() == "Cargo" || goal.getCargo() == "Diamonds" );
			assertTrue(
					"GoalFactory's reward was not set correctly, iteration: " + i,
					goal.getReward() > 0);
			assertTrue(
					"GoalFactory did not have a valid via station, iteration: " + i,
					goal.stationVia == null || checkExistence(goal.stationVia.getName()));
			
			if (goal.isSpecial() == false) {
				nor = true; //The value of nor is set to true if one or more non-special goals are created by the method
			}
			
			if (goal.isSpecial()){ 
				spl = true; //The value of spl is set to true if one or more special goals are created by the method
			}
			
			if (goal.specialcargo){ 
				cspl = true; //The value of cspl is set to true if one or more special goals are created by the method
			}	
			if (goal instanceof ComboGoal){
				combog = true;
			}
			if (goal instanceof RouteGoal){
				routeg = true;
			}
			if (goal instanceof TimedGoal){
				timedg = true;
			}
		}
		
		assertTrue("GoalFactory successsfully created one or more normal/non-special goals", cspl);
		assertTrue("GoalFactory successsfully created one or more special goals", spl);
		assertTrue("GoalFactory successsfully created one or more special Cargo goals", nor);
		assertTrue("GoalFactory successsfully created one or more special Route goals", routeg);
		assertTrue("GoalFactory successsfully created one or more special Combo goals", combog);
		assertTrue("GoalFactory successsfully created one or more special Combo goals", timedg);
	}
	
	//@Test
	//public void testSplGoalCreation(){
	//	assertTrue( "GoalFactory did not have a valid via station, iteration: ", spl);
	//
	//System.out.println(goal.getCargo() + ", Goal is special? " + goal.isSpecial() + ", Value of IsCargoSpecial: " + goal.specialcargo);
// }
	
	private boolean checkExistence(String stationName) {
		for(int i = 0; i < WorldMap.getInstance().stationsList.size(); i++)
		{
			if(WorldMap.getInstance().stationsList.get(i).getName() == stationName)
				return true;
		}
		
		return false;
	}
}
