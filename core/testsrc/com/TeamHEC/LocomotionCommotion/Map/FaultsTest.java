package com.TeamHEC.LocomotionCommotion.Map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.TeamHEC.LocomotionCommotion.Goal.Goal;
import com.TeamHEC.LocomotionCommotion.Map.WorldMap;
import com.TeamHEC.LocomotionCommotion.Mocking.GdxTestRunner;
import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Resource.Score;
import com.TeamHEC.LocomotionCommotion.Resource.Coal;
import com.TeamHEC.LocomotionCommotion.Resource.Electric;
import com.TeamHEC.LocomotionCommotion.Resource.Gold;
import com.TeamHEC.LocomotionCommotion.Resource.Nuclear;
import com.TeamHEC.LocomotionCommotion.Resource.Oil;
import com.TeamHEC.LocomotionCommotion.Train.CoalTrain;
import com.TeamHEC.LocomotionCommotion.Train.Route;
import com.TeamHEC.LocomotionCommotion.Train.Train;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_TextureManager;
import com.badlogic.gdx.graphics.Texture;

@RunWith(GdxTestRunner.class)
public class FaultsTest {

	Station testStation;
	
	@Before
	public void setup(){
		testStation = WorldMap.getInstance().stationsList.get(0);
	}
	
	@Test //test isFaulty(), makeFaulty(), fixFault()
	public void makeFaultyTest(){
		assertFalse("Station initialises as not-faulty", testStation.isFaulty());
		testStation.makeFaulty();
		assertTrue("Station can be made faulty", testStation.isFaulty());
		if(testStation.isRepairable()){
			testStation.fixFault();
			assertFalse("Station can be successfully fixed", testStation.isFaulty());
		}

	}
	
	@Test //test generateFaults()
	public void generateFaultsTest() {
		for(int i = 0; i < 500; i++){
			WorldMap.getInstance().generateFaults();
		}
		
		Boolean flag = false; //creates a flag to determine if any of the stations in newMap are faulty
		
		for(int i = 0; i < WorldMap.getInstance().stationsList.size(); i++){
			if(WorldMap.getInstance().stationsList.get(i).isFaulty()) {
				flag = true;
				WorldMap.getInstance().stationsList.get(i).fixFault();
			}
		}
		assertTrue("Some faults are successfully generated at random.", flag);
	}
	
	
}
