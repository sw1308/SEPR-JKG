package com.TeamHEC.LocomotionCommotion.Card;

import java.util.Random;

import com.TeamHEC.LocomotionCommotion.Map.MapObj;
import com.TeamHEC.LocomotionCommotion.Map.WorldMap;
import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Train.Train;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_TextureManager;

/**
 * 
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 * @author Sam Watkins <sw1308@york.ac.uk>
 * Teleports a train (currently to London should be changed and worked in with UI so it teleports to a specified location).
 */

public class TeleportCard extends Card{
	
	/**
	 * Initialises the card
	 * @param player The owner of the card.
	 */
	public TeleportCard(Player player)
	{
		super(player, Game_TextureManager.getInstance().game_card_teleportcard, "Teleport");
	}
	
	@Override
	/**
	 * Takes a random train from the owner's trains list and moves it to a random station instantly.
	 * Should be changed in Assessment 4 to teleport either a specified train to a random location or a specified train to a specified location.
	 */
	public void implementCard()
	{
		Random rnd = new Random();
		// Need a way to choose the train, currently selects one at random:
		Train train = getOwner().getTrains().get(rnd.nextInt(getOwner().getTrains().size()));
		
		// Need a way to choose station, currently selects one at random:
		MapObj chosenLocation = WorldMap.getInstance().stationsList.get(rnd.nextInt(WorldMap.getInstance().stationsList.size()));
		
		train.route.getRoute().clear();
		train.route.setRouteIndex(0);
		train.route.setConnectionTravelled(0);
		
		train.route.setCurrentMapObj(chosenLocation);
	}
}
