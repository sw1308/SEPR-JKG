package com.TeamHEC.LocomotionCommotion.Goal;

import java.util.ArrayList;
import java.util.Random;

import com.TeamHEC.LocomotionCommotion.Goal.Graph.Dijkstra;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Map.WorldMap;

/**
 * 
 * @author Sam Anderson <sa902@york.ac.uk>
 * Generates random Goals.
 */
public class GoalFactory{

	private WorldMap map;                // creating world map 
	private ArrayList<Station> stations;
	private Random random;
	@SuppressWarnings("unused")
	private int turnCount;
	
	/**
	 * Initialises the GoalFactory
	 */
	public GoalFactory(int turnCount){   
		map = WorldMap.getInstance(); 
		stations = map.stationsList;  //get all the stations 
		random = new Random(); //initializes random, used throughout
		this.turnCount = turnCount;
	}	
	
	/**
	 * Uses Dijkstra to calculate the appropriate reward value for achieving the Goal we generate
	 * @param sStation The start station for the goal.
	 * @param fStation The final station for the goal.
	 * @return A value representing the reward. Will be proportional to the minimum distance between sStation and fStation.
	 */
	private int genReward(Station sStation, Station fStation){
		Dijkstra d = new Dijkstra(); //implements dijkstra 
		d.computePaths(d.lookUpNode(sStation)); //uses the loopup function to get instance of a
												//station and compute paths 
		double rew = d.lookUpNode(fStation).minDistance; // 
		return (int) rew; //returns reward casted to integer 
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
		Goal newgoal;

		Station sStation = newStation();
		Station fStation = newStation();		

		while (sStation.getName() == fStation.getName())
			fStation = newStation();		
		int reward = genReward(sStation, fStation);
		
		String cargo;
		if(random.nextInt(2) == 0)
			cargo = "Passenger"; //change this to add more cargo stations
		else
			cargo = "Cargo";
		
		if(random.nextInt(5) == 5) //random 1/5 choice of getting special goals
			newgoal = new SpecialGoal(sStation ,fStation, null, cargo, reward);		
		else
			newgoal = new Goal(sStation, fStation, null, cargo, reward);
		
		return newgoal; 
	}

}




















































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


