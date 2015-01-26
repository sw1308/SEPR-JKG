package com.TeamHEC.LocomotionCommotion.Map;

import java.util.ArrayList;
import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_MapObj;

/**
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 */

public class MapObj {
	
	public Game_Map_MapObj actor;
	public ArrayList<Connection> connections = new ArrayList<Connection>();
	public float x, y;
	private String name;
	
	/**
	 * Every Station and Junction on the map
	 * @param x xPosition on map
	 * @param y yPosition on map
	 */
	public MapObj(float x, float y, String name)
	{
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	/**
	 * Returns the name of the station or junction the MapObj represents
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return returns null if not a station, or is overwritten by Station subclass
	 */
	public Station getStation()
	{
		return null;
	}
	/**
	 * 
	 * @return The Actor (UI element) associated with the MapObj
	 */
	public Game_Map_MapObj getActor()
	{
		return actor;
	}
}