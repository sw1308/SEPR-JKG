package com.TeamHEC.LocomotionCommotion.Goal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.TeamHEC.LocomotionCommotion.Goal.GoalFactory;
import com.TeamHEC.LocomotionCommotion.Map.Station;
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
		boolean faultyPresent = false;
		
		//Station used to track a permanently broken station
		Station faultyStation = WorldMap.getInstance().stationsList.get(0);
		
		/*
		 * Force creation of permanently broken Station
		 */
		while(faultyPresent == false) {
			for(int i=0; i<WorldMap.getInstance().stationsList.size(); i++) {
				if(WorldMap.getInstance().stationsList.get(i).getRepairable()) {
					WorldMap.getInstance().generateFaults();
				} else {
					faultyPresent = true;
					faultyStation = WorldMap.getInstance().stationsList.get(i);
				}
			}
		}
		
		faultyPresent = false;
		
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
					goal.getCargo() == "Any" || goal.getCargo() == "Diamonds" );
			assertTrue(
					"GoalFactory's reward was not set correctly, iteration: " + i,
					goal.getReward() > 0);
			assertTrue(
					"GoalFactory did not have a valid via station, iteration: " + i,
					goal.stationVia == null || checkExistence(goal.stationVia.getName()));
			
			if (goal.isSpecial()){ 
				spl = true; //The value of spl is set to true if one or more special goals are created by the method
			} else {
				nor = true; //The value of nor is set to true if one or more non-special goals are created by the method
			}
			
			if(goal.fStation.getName() == faultyStation.getName()) {
				faultyPresent = true;
			}
			
			if (goal instanceof CargoGoal){ 
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
		
		assertTrue("GoalFactory successfully created one or more normal/non-special goals", nor);
		assertTrue("GoalFactory successfully created one or more special goals", spl);
		assertFalse("GoalFactory successfully avoided creating a goal that makes use of a broken station", faultyPresent);
		assertTrue("GoalFactory successfully created one or more special Cargo goals", cspl);
		assertTrue("GoalFactory successfully created one or more special Route goals", routeg);
		assertTrue("GoalFactory successfully created one or more special Combo goals", combog);
		assertTrue("GoalFactory successfully created one or more special Combo goals", timedg);
	}
	
	private boolean checkExistence(String stationName) {
		for(int i = 0; i < WorldMap.getInstance().stationsList.size(); i++)
		{
			if(WorldMap.getInstance().stationsList.get(i).getName() == stationName)
				return true;
		}
		
		return false;
	}
}
