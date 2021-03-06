package com.TeamHEC.LocomotionCommotion.Goal;

import java.util.ArrayList;
import java.util.Random;

import com.TeamHEC.LocomotionCommotion.GameData;
import com.TeamHEC.LocomotionCommotion.Goal.Graph.Dijkstra;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Map.WorldMap;

/**
 * @author Sam Watkins <sw1308@york.ac.uk>
 * @author Sam Anderson <sa902@york.ac.uk>
 * Generates random Goals.
 */
public class GoalFactory{

	private WorldMap map;                // creating world map 
	private ArrayList<Station> stations;
	private Random random;
	
	/**
	 * Initialises the GoalFactory
	 */
	public GoalFactory(int turnCount){   
		this();
	}
	
	public GoalFactory() {
		map = WorldMap.getInstance(); 
		stations = map.stationsList;  //get all the stations 
		random = new Random(); //initializes random, used throughout
	}
	
	/**
	 * Uses Dijkstra to calculate the appropriate reward value for achieving the Goal we generate
	 * @param sStation The start station for the goal.
	 * @param fStation The final station for the goal.
	 * @return A value representing the reward. Will be proportional to the minimum distance between sStation and fStation.
	 */
	private int genReward(Station sStation, Station fStation) {
		Dijkstra d = new Dijkstra(); //implements dijkstra 
		d.computePaths(d.lookUpNode(sStation)); //uses the lookup function to get instance of a
												//station and compute paths 
		double rew = d.lookUpNode(fStation).minDistance; // 
		return (int) rew; //returns reward casted to integer 
	}
	
	/**
	 * Generates the reward for a combo goal
	 * @param start station
	 * @param Via Station
	 * @param Final Station
	 * @return Int reward
	 */
	private int genComboReward(Station sStation, Station viaStation, Station fStation) {
		Dijkstra d = new Dijkstra(); //implements dijkstra 
		d.computePaths(d.lookUpNode(sStation)); //uses the lookup function to get instance of a
												//station and compute paths 
		double rew = d.lookUpNode(viaStation).minDistance;
		
		d.computePaths(d.lookUpNode(viaStation));
		rew += d.lookUpNode(fStation).minDistance;
		
		rew = rew * 3;
		
		return (int) rew; //returns reward casted to integer
	}
	
	/**
	 * Generates a suggested turn limit for special goals
	 * @param fStation The start station for the goal
	 * @param viaStation The station that must be passed through for special goals
	 * @param fStation The final station for the goal
	 * @return A value representing the maximum turn limit for a goal to be completed in
	 */
	private int genTurnLimit(Station fStation, Station sStation) {
		int minSpeed = Math.min(GameData.COAL_BASE, Math.min(GameData.ELECTRIC_BASE, Math.min(GameData.NUCLEAR_BASE, GameData.OIL_BASE)));
		double turnLimit;
		double dist;
		
		Dijkstra d = new Dijkstra(); //implements dijkstra 
		d.computePaths(d.lookUpNode(sStation)); //uses the lookup function to get instance of a
												//station and compute paths 
		dist = d.lookUpNode(fStation).minDistance;
		
		turnLimit = dist/minSpeed;
		
		return (int) turnLimit;
	}
	
	/**
	 * Gets the goal limit for a combo goal
	 * @param Final Station
	 * @param Via Station
	 * @param Start station
	 * @return Int turn limit
	 */
	private int genComboTurnLimit(Station fStation, Station viaStation, Station sStation) {
		int minSpeed = Math.min(GameData.COAL_BASE, Math.min(GameData.ELECTRIC_BASE, Math.min(GameData.NUCLEAR_BASE, GameData.OIL_BASE)));
		double turnLimit;
		double dist;
		
		Dijkstra d = new Dijkstra(); //implements dijkstra 
		d.computePaths(d.lookUpNode(sStation)); //uses the lookup function to get instance of a
												//station and compute paths 
		dist = d.lookUpNode(viaStation).minDistance;
		
		d.computePaths(d.lookUpNode(viaStation));
		dist += d.lookUpNode(fStation).minDistance;
		
		turnLimit = dist/minSpeed;
		
		return (int) turnLimit;
	}
	
	/**
	 * Gets a random station from the stationList.
	 * @return A random station.
	 */
	private Station newStation(){ 
		Station st = stations.get(random.nextInt(stations.size())); //get a random station
		return st;
	} 

	/**
	 * Creates a new random Goal.
	 * @return A random goal.
	 */
	public Goal CreateRandomGoal(){
		Goal newGoal;

		Station sStation = newStation();
		Station fStation = newStation();		

		while (!(stationValid(new Station[] {sStation, fStation}))) {
			sStation = newStation();
			fStation = newStation();
		}
		
		String cargo = "Any";
		
		//This section is unnecessary as train cargo is not checked against
		/*if(random.nextInt(2) == 0)
			cargo = "Passenger";
		else
			cargo = "Cargo";*/
		
		//random 1/4 chance of getting special goals
		
		if(random.nextInt(3) == 0) {
			Station viaStation = newStation();
			
			while (!(stationValid(new Station[] {sStation, viaStation, fStation}))) {
				viaStation = newStation();
			}
			
			int rand = random.nextInt(4);
			
			if(rand == 0) {
				int turnLimit = this.genComboTurnLimit(sStation, viaStation, fStation);
				int reward = this.genComboReward(sStation, viaStation, fStation);
				
				newGoal = new ComboGoal(sStation, fStation, viaStation, cargo, reward, turnLimit);
			} else if(rand == 1) {
				int turnLimit = this.genTurnLimit(sStation, fStation);
				int reward = this.genReward(sStation, fStation) * 2;
				
				newGoal = new TimedGoal(sStation, fStation, null, cargo, reward, turnLimit);
			} else if(rand == 2) {
				int reward = this.genReward(sStation, fStation) * 2;
				
				newGoal = new RouteGoal(sStation, fStation, viaStation, cargo, reward);
			} else {				
				cargo = "Diamonds";
				int reward = (int) (this.genReward(sStation, fStation) * 1.50) ;
				newGoal = new CargoGoal(sStation, fStation, null, cargo, reward);
			}
		} else {
			int reward = genReward(sStation, fStation);
			newGoal = new Goal(sStation, fStation, null, cargo, reward);
		}
		return newGoal;
	}

	/**
	 * 
	 * @param stations An array of all stations to be tested for validity
	 * @return True if all stations are valid, false otherwise.
	 */
	private boolean stationValid(Station stations[]) {
		for(int i=0; i<stations.length; i++) {
			for(int j=i+1; j<stations.length; j++) {
				if(stations[i].getName() == stations[j].getName()) {
					return false;
				}
			}
			if(!(stations[i].isRepairable())) {
				return false;
			}
		}
		return true;
	}
}


// Scalable Special Cargo Goals
/*				
int randomcargonum = random.nextInt(3);

if(randomcargonum == 0)
{
	cargo = "Graphite";
	int reward = (int) (this.genReward(sStation, fStation) * 1.10) ; 
	newGoal = new RouteGoal(sStation, fStation, null, cargo, reward);
}

else if (randomcargonum == 1)
{
	cargo = "Silver";
	int reward = (int) (this.genReward(sStation, fStation) * 1.25) ;
	newGoal = new RouteGoal(sStation, fStation, null, cargo, reward);
}

else 
{
	cargo = "Diamond";
	int reward = (int) (this.genReward(sStation, fStation) * 1.50) ;
	newGoal = new RouteGoal(sStation, fStation, null, cargo, reward);
}		
*/

















































////variables 
// 
// public boolean Special = false;
// public int Reward,Startdate; 
// public String Carriagetype, GoalString,Cargo,Mission;
// Random r = new Random();
// private final String Atype = FStation + "requires a delivery of " + cargo+ "from " +SStation+ ". the reward for this task will be " + Reward ;
// private final String Btype = "A " +Carriage+" is required to run from " +SStation+ " to "+ FStation +". the reward for this task will be " + Reward ;
// private final String Ctype = "A train with a " + Carriage + "is required to deliver " + Cargo + " to " +FStation + " from " +SStation + ". The rewared for this will be " +Reward;
// private final String S1 = "s1";
// private final String S2 = "S2";
// private final String S3 = "S3";
// private final String S4 = "S4";
// public Card card;
// 
////create goal 
// public Goal CreateGoal(){
//   if (this.CheckSpecialCount()){
//     Special = true;
//   }   
//   SStation = this.NewStation();
//   FStation = this.NewStation();
//   Carriagetype = this.NewCar();
//   Cargo = this.GetCargo();
//   Reward = this.genreward();
//   Mission = this.GetMission();
//   Startdate = this.SetStartdate();
//   card = this.genCard();
//   Goal newgoal = new Goal(SStation,FStation, Special,Reward,Startdate,Carriagetype,Cargo,Mission,card);
//   return newgoal; 
// }
// 
// //generatoes 
//
// public int genreward(){
//   if (Special == true) {
//     
//    int x = 3000; //generate a bigger int  
//   }
//   int x = 300;
//     return x;
// }
// public Card genCard(){
//   CardFactory cf = new CardFactory();
//   
//    return cf.createRandomCard();   
//     
//   
//   
// }
// public int SetStartdate(){
//   // takes turn counter
//   int tc = CoreGame.getTurnCount();
//   
//     
// return tc;
// }
//
//public boolean CheckSpecialCount(){
//  int currentS = 2;
//   if( currentS > 2){     
//    return false;    
//    }
//    if( currentS == 2){
//    int fityfity = r.nextInt(1);
//    if (fityfity == 1) {
//      return true;      
//    }   }  
//  if( currentS == 1){
//    int seventy = r.nextInt(3);
//    if (seventy < 2) {
//      return true;      
//    }   }
//  if( currentS == 0) { 
//    return true;
//  }
//  return false;  
//}
//
//public String GetCargo(){
//  if (Special == true){
//    cargo.append();      
//  }
//  int x = this.NewRandom(cargo);   
//  return (cargo[x]);
//}     
//  
//
//public String NewStation(){  
//  String st = null;
//  while (st == null) {
//   //used stations are replaced with null, do not want to generate a null stattion
//   int x = this.NewRandom(stations); //gets new station, no weighting 
//   String st = stations[x];
//   stations[x] = null;
//   return (st);   
//  }
//}
//public String NewCar(){  
//   int x = this.NewRandom(carriage);   //gets new carriage of any type, no weighting
//   return (carriage[x]);   
//} 
// public int NewRandom(String[] list){                                       //returns a random
//   int index = r.nextInt(list.length); //number from the length of a given
//   return index;                       //list }
//}
// 
// // returns a card from the existing list: 
// public void getMission(){
//   if (Special) {
//   int rand = r.nextInt(SGoalList.size());
//   String chosengoal = SGoalList.get(rand);
//   if(chosengoal.equals(S1))
//     return S1;
//   if(chosengoal.equals(S2))
//     return S2;
//   if(chosengoal.equals(S3))
//     return S3;
//   if (chosengoal.equals(S4))
//     return S4;     
//   }
//   else {
//   int rand = r.nextInt(GoalList.size());
//   String chosengoal = GoalList.get(rand);
//   if(chosengoal.equals(Atype))
//   return Atype;
//  else if(chosenCard.equals(Btype))
//   return Btype;
//  else if(chosenCard.equals(Ctype))
//   return Ctype;
//   }
// }
//  
//}


