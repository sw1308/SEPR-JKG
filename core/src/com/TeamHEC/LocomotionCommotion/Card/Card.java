package com.TeamHEC.LocomotionCommotion.Card;

import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 * @author Callum Hewitt <ch1194@york.ac.uk>
 *
 */
public abstract class Card {
	
	private Player owner;
	private Texture cardImage;
	private String name;
	
	/**
	 * Initialise's the Card's key parameters. Card is abstract so should only be called as super();
	 * @param player The owner of the card.
	 * @param image The texture used in displaying the card in the dock
	 * @param name The name of the card. Eg. \"Coal\" or \"Teleport\"
	 */
	public Card(Player player, Texture image, String name)
	{		
		owner = player;	
		cardImage = image;
		this.name = name;
	}
	
	/**
	 * Returns the player that owns that card
	 * @return the player of the card
	 */
	public Player getOwner()
	{
		return owner;
	}
	
	/**
	 * Sets the owner of the card
	 * @param owner this is the owner of the card
	 */
	public void setOwner(Player owner)
	{
		this.owner = owner;
	}	
	
	/**
	 * Gets the texture of the card
	 * @return Texture - cardImage
	 */
	public Texture getImage(){
		return cardImage;
	}
	
	/**
	 * Gets the name of the card
	 * @return string - name
	 */
	public String getName(){
		return name;
	}

	/**
	 * A method to be overridden in subclasses. This determines what happens when the card is used.
	 */
	public void implementCard(){}
	
}