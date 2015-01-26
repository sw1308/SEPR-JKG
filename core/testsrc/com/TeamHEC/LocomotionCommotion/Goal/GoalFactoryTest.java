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
					goal.getCargo() == "Passenger" || goal.getCargo() == "Cargo");
			assertTrue(
					"GoalFactory's reward was not set correctly, iteration: " + i,
					goal.getReward() > 0);
			assertTrue(
					"GoalFactory did not have a valid via station, iteration: " + i,
					goal.stationVia == null || checkExistence(goal.stationVia.getName()));
			
		}
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
