package com.TeamHEC.LocomotionCommotion.Player;

import java.util.ArrayList;
import java.util.HashMap;

import com.TeamHEC.LocomotionCommotion.Card.Card;
import com.TeamHEC.LocomotionCommotion.Goal.Goal;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Resource.*;
import com.TeamHEC.LocomotionCommotion.Train.*;
import com.TeamHEC.LocomotionCommotion.UI_Elements.GameScreenUI;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_StartingSequence;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;

/**
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 * @author Callum Hewitt <ch1194@york.ac.uk>
 * @author Elliot Bray <eb1033@york.ac.uk>
 * @author Richard Brierley <rb1038@york.ac.uk>
 * This class defines the object of player and all of the things the player owns, resources and trains etc.
 */

public class Player implements RouteListener{

	private String name;
	private Score score;
	private Gold gold;
	private Coal coal;
	private Oil oil;
	private Electric electric;
	private Nuclear nuclear;
	private ArrayList<Card> cards;
	private Shop shop;
	private ArrayList<Goal> goals;
	private ArrayList<Train> trains;
	private ArrayList<Station> stations = new ArrayList<Station>();
	private int[] lines = new int[8];

	private HashMap<String, Fuel> playerFuel;

	public boolean isPlayer1;

	public Player(String name, Score score, Gold gold, Coal coal, Electric electric, Nuclear nuclear, Oil oil, ArrayList<Card> cards, ArrayList<Goal> goals, ArrayList<Train> trains)
	{
		this.name = name;
		this.score = score;
		this.gold = gold;
		this.coal = coal;
		this.oil = oil;
		this.electric = electric;
		this.nuclear = nuclear;
		this.cards = cards;
		this.shop = new Shop(this);
		this.goals = goals;
		this.trains = trains;

		//this.stations = stations;
		for (int i = 0; i<6;i++)
		{
			this.lines[i] = 0;
		}

		playerFuel = new HashMap<String, Fuel>();

		playerFuel.put("Coal", this.coal);
		playerFuel.put("Electric", this.electric);
		playerFuel.put("Nuclear", this.nuclear);
		playerFuel.put("Oil", this.oil);
	}

	/**
	 * @return returns the players name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return returns the player's score
	 */
	public int getScore()
	{
		return score.getValue();
	}
	
	/**
	 * 
	 * @param value amount to be added to the player's score
	 */
	public void addScore(int value)
	{
		score.addValue(value);
		if(!Game_StartingSequence.inProgress)
				GameScreenUI.refreshResources();
	}

	/**
	 * 
	 * @return returns the player's instance of shop
	 */
	public Shop getShop(){
		return shop;
	}
	
	/**
	 * 
	 * @param quantity is the amount on coal to be added to the player's stockpile
	 */
	public void buyCoal(int quantity)
	{
		shop.buyFuel("Coal", quantity, false);
	}

	/**
	 * 
	 * @param quantity  is the amount on Oil to be added to the player's stockpile
	 */
	public void buyOil(int quantity)
	{
		shop.buyFuel("Oil", quantity, false);
	}

	/**
	 * 
	 * @param quantity is the amount on Electric to be added to the player's stockpile
	 */
	public void buyElectric(int quantity)
	{
		shop.buyFuel("Electric", quantity, false);
	}

	/**
	 * 
	 * @param quantity  is the amount on Nuclear to be added to the player's stockpile
	 */
	public void buyNuclear(int quantity)
	{
		shop.buyFuel("Nuclear", quantity, false);
	}

	public void buyCard(int quantity)
	{
		shop.buyCard(false);
	}

	/**
	 * 
	 * @param fuelType the kind of fuel that the query will return the amount of.
	 * @return the amount of fuel of type fuelType
	 */
	public int getFuel(String fuelType)
	{
		return playerFuel.get(fuelType).getValue();
	}

	/**
	 * 
	 * @param fuelType the kind of fuel that the function will add the amount of.
	 * @param quantity the amount to increment the fuel value
	 */
	public void addFuel(String fuelType, int quantity)
	{
		playerFuel.get(fuelType).addValue(quantity);
		if(!Game_StartingSequence.inProgress)
			GameScreenUI.refreshResources();
	}

	/**
	 * 
	 * @param fuelType the kind of fuel that the function will subtract the amount of.
	 * @param quantity the amount to decrement the fuel value
	 */
	public void subFuel(String fuelType, int quantity){
		playerFuel.get(fuelType).subValue(quantity);
		if(!Game_StartingSequence.inProgress)
			GameScreenUI.refreshResources();
	}

	/**
	 * Adds a train of the type specified to the player's trains.
	 * 
	 * @param trainType fuel type for the train
	 */
	public void addTrain(String trainType) {
		if(trainType == "Coal") {
			CoalTrain train = new CoalTrain(0, true, new Route(stations.get(0)), this);
			trains.add(train);
		} else if(trainType == "Electric") {
			ElectricTrain train = new ElectricTrain(0, true, new Route(stations.get(0)), this);
			trains.add(train);
		} else if(trainType == "Nuclear") {
			NuclearTrain train = new NuclearTrain(0, true, new Route(stations.get(0)), this);
			trains.add(train);
		} else if(trainType == "Oil") {
			OilTrain train = new OilTrain(0, true, new Route(stations.get(0)), this);
			trains.add(train);
		}
	}

	/**
	 * 
	 * @return the amount of gold the player has
	 */
	public int getGold()
	{
		return gold.getValue();
	}

	/**
	 * 
	 * @param value the amount to increment the gold value
	 */
	public void addGold(int value)
	{
		gold.setValue(gold.getValue() + value);
		if(!Game_StartingSequence.inProgress)
			GameScreenUI.refreshResources();
	}

	/**
	 * 
	 * @param value the amount to decrement the gold value
	 */
	public void subGold(int value)
	{
		gold.setValue(gold.getValue() - value);
		if(!Game_StartingSequence.inProgress)
			GameScreenUI.refreshResources();
	}

	/**
	 * 
	 * @param card an object of type Card to add the the player's stockpile
	 */
	public void addCard(Card card)
	{
		cards.add(card);
	}

	/**
	 * 
	 * @param card an object of type card to discard from the player's hand
	 */
	public void discardCard(Card card)
	{
		cards.remove(card);
	}

	/**
	 * 
	 * @return the number of cards the player owns
	 */
	public int getNumCards()
	{
		return cards.size();
	}

	/**
	 * 
	 * @return returns the array of cards the player owns
	 */
	public ArrayList<Card> getCards()
	{
		return cards;
	}

	//Stations
	/**
	 * @return returns the number of stations owned by the player
	 */
	public int getNumStations()
	{
		return stations.size();
	}	
	/**
	 * @return returns the array list of stations
	 */
	public ArrayList<Station> getStations()
	{
		return stations;
	}
	/**
	 * @return returns the array containing how much of each line a player owns
	 */
	public int[] getLines()
	{
		return lines;
	}

	/**
	 * @param station purchases the station for the player and keeps track of lines owned
	 */
	public void purchaseStation(Station station)
	//If the player doesn't have enough gold or if the station is owned by a player or if the player does not 
	//have a train in that station then nothing will happen
	//There is space to add some sort of message for the player
	{
		boolean trainInStation = false;
		for (int j=0; j < trains.size(); j ++)
		{
			if(station.getOwner() == null)
			{
				if(trains.get(j).getRoute().getStation() == station)
				{
					if(getGold() >= station.getBaseValue())
					{
						stations.add(station);
						this.subGold(station.getBaseValue());
						for (int i=0; i<station.getLineType().length; i++)
						{	
							if (((i > 0) && (station.getLineType()[i] != station.getLineType()[i-1])) || (i==0))
								//Line is an array of 3 line colours, this loop will add the first line colour
								//then if a station is on another line of a different colour it will add that
								//hence when i > 0 (checking the second colour) AND is a different colour to the previous colour
								//add that colour to the players lines
							{
								switch(station.getLineType()[i])
								{ //keeps track of how much of a line the player owns
								case Red:
									lines[0] += 1;	
									break;
								case Blue:
									lines[1] += 1;
									break;
								case Green:
									lines[2] += 1;
									break;
								case Yellow:
									lines[3] += 1;
									break;
								case Purple: 
									lines[4] += 1;
									break;
								case Black:
									lines[5] += 1;
									break;
								case Brown:
									lines[6] += 1;
									break;
								case Orange:
									lines[7] += 1;
									break;
								default:
									throw new IllegalArgumentException("Could not find line for Station: " + station.getName() + " owned by Player " + station.getOwner().name);
								}
							}
						}
						station.setOwner(this);
						trainInStation = true;
						this.lineBonuses();				
					}
					else
					{
						WarningMessage.fireWarningWindow("SORRY", "You don't have enough gold!");
					}
				}
			}
			else
			{
				WarningMessage.fireWarningWindow("SORRY", "Station is already owned");
				trainInStation = true;
			}
		}
		if(trainInStation == false) {
			WarningMessage.fireWarningWindow("SORRY", "You must have a train at a station to buy it");
		}
	}

	public void sellStation(Station station)
	{
		//UI DOES NOT CURRENTLY SUPPORT SELLING OF STATIONS
		//but sell station has been tested and does work
		/*
		if (this.stations.contains(station))
		{
			for (int i=0; i<station.getLineType().length; i++)
			{	
				if (((i > 0) && (station.getLineType()[i] != station.getLineType()[i-1])) || (i==0))
				{
					//same method as in purchase station
					switch(station.getLineType()[i])
					{ //keeps track of how many of a line the player owns
					case Red:
						lines[0] -= 1;			
						break;
					case Blue:
						lines[1] -= 1;
						break;
					case Green:
						lines[2] -= 1;
						break;
					case Yellow:
						lines[3] -= 1;
						break;
					case Purple: 
						lines[4] -= 1;
						break;
					case Black:
						lines[5] -= 1;
						break;
					case Brown:
						lines[6] -= 1;
						break;
					case Orange:
						lines[7] -= 1;
						break;
					default:
						throw new IllegalArgumentException("Could not find line for Station: " + station.getName() + " owned by Player " + station.getOwner().name);
					}
				}
			}
			this.addGold((int)(station.getBaseValue() * 0.7));
			station.setOwner(null);
			stations.remove(station);
			station.setOwner(null);
			this.lineBonuses();
		}
		 */
	}


	@Override
	/**
	 * 
	 * Prints the stations as they are passed
	 * 
	 */
	public void stationPassed(Station station, Train train) {
		// TODO Auto-generated method stub

		System.out.println("Train passed " + station.getName());

	}

	/**
	 * calculates the bonus for each individual station based on how many other stations on the same line are owned
	 */
	public void lineBonuses() //MUST BE CALLED BEFORE YOU ACCESS A STATIONS VALUE, RENT OR RESOURCE AMOUNTS IF STATIONS OWNED HAS CHANGED
	{
		for (int i = 0; i<stations.size(); i++)
		{
			Station currentStation = stations.get(i);
			int red = 0;
			int blue = 0;
			int green = 0;
			int yellow = 0;
			int purple = 0;
			int black = 0;
			int brown = 0;
			int orange = 0;

			for (int j = 0; j < currentStation.getLineType().length; j++) //line type length should be 3, so for 0 to 2		
			{
				if (j == 0 || ((j > 0) && (currentStation.getLineType()[j] != currentStation.getLineType()[j-1])))
					//if 0 check line type 
					//if 1 check if it is the same as 0 if not check line type
					//if 2 check if it is the same as 1 if not check line type
					//always configure your line type array to have repeats in 1 and 2 e.g. (Black, Red, Red) NOT (Black, Red, Black) else this will not work
				{
					switch(currentStation.getLineType()[j])
					{	
					case Red:
						red = lines[0];
						if (red == 5)
						{
							red += 3;
						}
						//5 stations on red line
						break;
					case Blue:
						blue = lines[1];
						if (blue == 5)
						{
							blue += 3;
						}
						//5 stations on blue line
						break;
					case Green:
						green = lines[2];
						if (green == 3)
						{
							green += 1;
						}
						//3 stations on green line
						break;
					case Yellow:
						yellow = lines[3];
						if (yellow == 4)
						{
							yellow += 2;
						}
						//4 stations on yellow line
						break;
					case Purple:
						purple = lines[4];
						if (purple == 4)
						{
							purple += 2;
						}
						//4 stations on purple line
						break;
					case Black:
						black = lines[5];
						if (black == 5)
						{
							black += 3;
						}
						//5 stations on black line
						break;
					case Brown:
						brown = lines[6];
						if (brown == 5)
						{
							brown += 3;
						}
						//5 stations on brown line
						break;
					case Orange:
						orange = lines[7];
						if (orange == 6)
						{
							orange += 4;
						}
						//6 stations on orange line
						break;
					default:
						throw new IllegalArgumentException("Could not find line associated with value");
					}
				}
			}
			//owning an entire line is worth an additional reward (3 stations 5%, 4 stations 10%, 5 stations 15%, 6 stations 20%)
			//increase rent, resource and value by 5% per line you have a station connected too, this may be adjusted to due scaling at larger values
			currentStation.setResourceOutMod(((red + blue + green + yellow + purple + black + brown + orange) * (int)(currentStation.getBaseResourceOut() * 0.05)));

			//Rent not currently increased but is increased for later use anyway
			currentStation.setRentValueMod(((red + blue + green + yellow + purple + black + brown + orange) * (int)(currentStation.getBaseRentValue() * 0.05)));
			//Increasing value has no affect as stations cannot be currently be sold but if increased for later use anyway
			currentStation.setValueMod(((red + blue + green + yellow + purple + black + brown + orange) * (int)(currentStation.getValueMod() * 0.05)));
		}
	}

	/**
	 * Generates fuel from stations.
	 */
	public void stationRewards()
	{

		for (int i = 0; i < stations.size(); i++)
		{
			Station currentStation = stations.get(i);
			if (currentStation.getResourceString() == "Gold") //Special case for Monaco which gives gold
			{
				this.addGold(currentStation.getBaseResourceOut());
			}
			else
			{
				this.addFuel(currentStation.getResourceString(), currentStation.getTotalResourceOut());
				this.addGold(currentStation.getBaseRentValue());
			}
		}
	}

	/**
	 * 
	 * @return the goals of a player as an arraylist
	 */
	public ArrayList<Goal> getGoals()
	{
		return goals;
	}

	/**
	 * 
	 * @return the trains that a player owns as an arraylist
	 */
	public ArrayList<Train> getTrains()
	{
		return trains;
	}
}