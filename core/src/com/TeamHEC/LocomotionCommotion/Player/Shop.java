package com.TeamHEC.LocomotionCommotion.Player;

import com.TeamHEC.LocomotionCommotion.Card.CardFactory;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;

/**
 * 
 * @author Callum Hewitt <ch1194@york.ac.uk>
 * The shop object used by a player to buy and sell fuel and cards.
 *
 */

public class Shop {

	private Player customer;
	private CardFactory cardFactory;
	
	//Sell price is 70% of the buy price
	public final static int coalPrice = 1;
	public final static float coalSellPrice = 0.7f;
	public final static int oilPrice = 2;
	public final static float oilSellPrice = 1.4f;
	public final static int electricPrice = 3;
	public final static float electricSellPrice = 2.1f;
	public final static int nuclearPrice = 4;
	public final static float nuclearSellPrice = 2.8f;
	public final static int coalTrainPrice = 1000;
	public final static int coalTrainSellPrice = 700;
	public final static int oilTrainPrice = 2000;
	public final static int oilTrainSellPrice = 1400;
	public final static int electricTrainPrice = 3000;
	public final static int electricTrainSellPrice = 2100;
	public final static int nuclearTrainPrice = 4000;
	public final static int nuclearTrainSellPrice = 2800;
	public final static int cardPrice = 1000;
	public final static float cardSellPrice = 700f;
	
	/**
	 * The initialiser for shop. Creates cardFactory and assigns customer.
	 * @param customer The player who will be buying and selling things from this shop object.
	 */
	public Shop(Player customer)
	{
		this.customer = customer;	
		cardFactory = new CardFactory(customer);
	}
	
	/**
	 * Purchases fuel from the shop using the customer's money. If the player doesn't have enough gold it will display a warning window (unless testing)
	 * @param fuelType The type of fuel as string the player will obtain: "Coal", "Oil", "Electric", "Nuclear"
	 * @param quantity The amount of fuel the player will purchase
	 * @param testCase Determines if the run is a testCase or not.
	 */
	public void buyFuel(String fuelType, int quantity, boolean testCase)
	{		
		if(fuelType == "Coal" && customer.getGold() >= (quantity*coalPrice)) {
			customer.addFuel(fuelType, quantity);
			customer.subGold(quantity * coalPrice);
		}
		else if(fuelType == "Oil" && customer.getGold() >= (quantity*oilPrice)) {
			customer.addFuel(fuelType, quantity);
			customer.subGold(quantity * oilPrice);
		}
		else if(fuelType == "Electric" && customer.getGold() >= (quantity*electricPrice)) {
			customer.addFuel(fuelType, quantity);
			customer.subGold(quantity * electricPrice);
		}
		else if(fuelType == "Nuclear" && customer.getGold() >= (quantity*nuclearPrice)) {
			customer.addFuel(fuelType, quantity);
			customer.subGold(quantity * nuclearPrice);
		}	
		else
		{
			if(!testCase)
				WarningMessage.fireWarningWindow("SORRY", "You don't have enough gold!");
		}
	}
	
	/**
	 * lets a player buy a train.
	 * Reduced the player's gold by the cost of the train and then adds a train to the players list of trains
	 * 
	 * @param trainType - Train type with capital
	 * @param quantity - Number of trains being bought
	 * @param testCase - If it is a JUnit test
	 */
	public void buyTrain(String trainType, int quantity, boolean testCase) {
		int trainPrice = 0;
		
		if(trainType == "Coal") {
			trainPrice = coalTrainPrice;
		}else if(trainType == "Oil") {
			trainPrice = oilTrainPrice;
		}else if(trainType == "Electric") {
			trainPrice = electricTrainPrice;
		}else if(trainType == "Nuclear") {
			trainPrice = nuclearTrainPrice;
		}
		
		if(customer.getGold() >= trainPrice * quantity) {
			for(int i = 0; i < quantity; i++) {
			customer.subGold(trainPrice);
			customer.addTrain(trainType);
			}
		} else {
			if(!testCase) {
				WarningMessage.fireWarningWindow("SORRY", "You don't have enough gold!");
			}
			
		}
	}
	
	/**
	 * Repairs a specified station.
	 * Reduces the player's gold by the cost of the repair and then removes the station fault
	 * 
	 * @param station - the station to be repaired
	 * @param testCase - If this is a JUnit test
	 */
	public void repairStation(Station station, boolean testCase) {
		if(customer.getGold() >= 300) {
			station.fixFault();
			customer.subGold(300);
		} else {
			if(!testCase){
				WarningMessage.fireWarningWindow("SORRY", "You don't have enough gold!");
			}
		}
	}
	
	/**
	 * Upgrades a station
	 * Reduces the player's gold by the cost of upgrading a station and increases the station level by 1
	 * 
	 * @param station - the station to be upgraded
	 * @param testCase - if this is a JUnit test
	 */
	public void upgradeStation(Station station, boolean testCase) {
		if(customer.getGold() >= 400) {
			station.upgradeStation();
			customer.subGold(400);
		}
		else{
			if(!testCase){
				WarningMessage.fireWarningWindow("SORRY", "You don't have enough gold!");
			}	
		}
	}
	
	public void sellFuel(String fuelType, int quantity, boolean testCase)
	{
		
		if(fuelType == "Coal" && customer.getFuel(fuelType) >= quantity) {
			customer.subFuel(fuelType, quantity);
			customer.addGold((int)(Math.ceil(quantity * coalSellPrice)));
		}
		
		
		else if(fuelType == "Oil" && customer.getFuel(fuelType) >= quantity) {
			customer.subFuel(fuelType, quantity);
			customer.addGold((int)(Math.ceil(quantity * oilSellPrice)));
		}
		
		
		else if(fuelType == "Electric" && customer.getFuel(fuelType) >= quantity) {
			customer.subFuel(fuelType, quantity);
			customer.addGold((int)(Math.ceil(quantity * electricSellPrice))); //DO NOT REMOVE MATH.CEIL IT ROUNDS WIERDLY
		}
		
		
		else if(fuelType == "Nuclear" && customer.getFuel(fuelType) >= quantity) {
			customer.subFuel(fuelType, quantity);
			customer.addGold((int)(Math.ceil(quantity * nuclearSellPrice)));
		}
		else
		{
			if(!testCase)
				WarningMessage.fireWarningWindow("SORRY", "You don't have enough "+fuelType+"!");
		}
	}

	/**
	 * Purchases a card for the player
	 * @param testCase A boolean deciding if this is a testCase run or not.
	 */
	public void buyCard(boolean testCase)
	{
		if (customer.getCards().size() < 7 && customer.getGold() >= 1000)
		{			
			// Sets the owner to the card and subtract gold from player
			customer.addCard(cardFactory.createAnyCard());
			customer.subGold(1000);
			WarningMessage.fireWarningWindow("Card purchased", "Have a look at the card menu to find your new card!");
		}
		else
		{
			if(!testCase)
			{
				if(customer.getGold() < 1000)
				{
					WarningMessage.fireWarningWindow("SORRY", "You don't have enough gold!");
				}
				else 
				{
					WarningMessage.fireWarningWindow("SORRY", "You have too many cards already");
				}
			}
		}
	}
}