package com.TeamHEC.LocomotionCommotion.Map;

import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_TextureManager;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

/**
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 */
public class Connection{
	
	private MapObj startMapObj, endMapObj;
	private float length;
	
	private Vector2 vector;
	
	// Displaying Route stuff:
	private Array<Actor> connectionBlips = new Array<Actor>();
	private Sprite redRouteBlip;
	private int blipSize;

	/**
	 * A connection between two adjacent MapObjs in the Map.
	 * Defubes the distance between them (length0 and direction (vector) 
	 * @param startMapObj Where the connection begins
	 * @param endMapObj Where the connection ends
	 */
	public Connection(MapObj startMapObj, MapObj endMapObj)
	{
		this.startMapObj = startMapObj;
		this.endMapObj = endMapObj;
		
		float dX =  endMapObj.x - startMapObj.x;
		float dY =  endMapObj.y - startMapObj.y;
		
		// Creates a vector so we can find the length and normalise for direction:
		vector = new Vector2(dX, dY);
		
		// The length between the two mapobjs:
		length = vector.len();
		
		// Normalises direction so we can scale later:
		vector.nor();
		
		// ==== UI blips for connections ====
		/*
		 * Creates a series of white blips for a connection, with a red blip traversing that connection
		 * to indicate direction
		 */
		
		// Use the direction vector to find coordiantes by scaling it by how far we want the blips
		// to be apart:
		
		Vector2 blipVector = vector.cpy();
		
		// 30 pixels apart:
		for(int i = 30; i < length - 15; i += 30)
		{
			Vector2 startPos = new Vector2(startMapObj.x, startMapObj.y);
			blipVector.scl(i);
			startPos.add(blipVector);
			
			// Creates a sprite blip in that position:
			Sprite blip = new Sprite(startPos.x, startPos.y, Game_Map_TextureManager.getInstance().routeBlip);
			blip.setVisible(false);
			
			// Adds it to an Array of UI Actors:
			connectionBlips.add(blip);
			// Normalises so we can rescale again:
			blipVector.nor();
		}
		
		// Red blip to indicate direction:
		blipSize = connectionBlips.size;
		redRouteBlip = new Sprite(-50, -50, Game_Map_TextureManager.getInstance().redRouteBlip){
			
			int counter = 0;
			int delay = 0;
			
			@Override
			public void act(float delta)
			{
				// Delays the animation so the position isn't reset immediately:
				if(delay > 20)
				{
					if(counter > blipSize)
						counter = 0;
					
					setPosition(connectionBlips.get(counter).getX(), connectionBlips.get(counter).getY());
					counter++;
					
					delay = 0;
				}
				else
					delay++;
			}
		};
		redRouteBlip.setVisible(false);
		connectionBlips.add(redRouteBlip);
	}
	
	/**
	 * @return the length between the start and end of a connection
	 */
	public float getLength()
	{
		return length;
	}
	
	/**
	 * @return A normalised vector of the direction needed to reach the end of the connection
	 */
	public Vector2 getVector()
	{
		return vector;
	}
	
	/**
	 * @return the start of a connection
	 */
	public MapObj getStartMapObj()
	{
		return startMapObj;
	}
	/**
	 * @return the destination of a connection
	 */
	public MapObj getDestination()
	{
		return endMapObj;
	}
	/**
	 * 
	 * @return An Array of routeblips used when creating a route
	 */
	public Array<Actor> getRouteBlips()
	{
		return connectionBlips;
	}
	/**
	 * @param connection to be compared
	 * @return true if two connections are the same but reversed
	 */
	public boolean isReverseOf(Connection connection)
	{
		if(startMapObj == connection.getDestination() && endMapObj == connection.getStartMapObj())
			return true;
		else
			return false;
	}
}