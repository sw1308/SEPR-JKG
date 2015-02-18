package com.TeamHEC.LocomotionCommotion.Map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.TeamHEC.LocomotionCommotion.Resource.Electric;

public class FaultsTest {

	WorldMap newMap;
	Station testStation;
	
	@Before
	public void setup(){
		newMap = WorldMap.getInstance();
		testStation = new Station("York", 200, new Electric(4), 50, null, 50, 0, 0);
	}
	
	@Test //test isFaulty(), makeFaulty(), fixFault()
	public void makeFaultyTest(){
		assertFalse("Station initialises as not-faulty", testStation.isFaulty());
		testStation.makeFaulty();
		assertFalse("Station can be made faulty", testStation.isFaulty());
		if(testStation.isRepairable()){
			testStation.fixFault();
			assertFalse("Station can be successfully fixed", testStation.isFaulty());
		}

	}
	
	@Test //test generateFaults()
	public void generateFaultsTest() {
		for(int i = 0; i < 500; i++){
			newMap.generateFaults();
		}
		
		Boolean flag = false; //creates a flag to determine if any of the stations in newMap are faulty
		
		for(int i = 0; i < newMap.stationsList.size(); i++){
			flag = flag || newMap.stationsList.get(i).isFaulty();
		}
		assertTrue("Some faults are successfully generated at random.", flag);
	}
	
	
}
