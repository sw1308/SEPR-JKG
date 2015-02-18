package com.TeamHEC.LocomotionCommotion.Map;

import com.TeamHEC.LocomotionCommotion.Player.Player;

/**
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 * @author Oliver Binns <ob601@york.ac.uk>
 */

public interface StationListener {
	
	/**
	 * Any class than implements this will be notified of a change in ownership
	 * of the station
	 * @param station the station which has changed owner
	 * @param player the new owner of the station
	 */
	public void updateButton(Station station, Player player);
	
	/**
	 * Method has been replaced with updateButton method which takes the same parameters.
	 * Update button is a more appropriate name as faults are now supported by this method.
	 * 
	 * @deprecated please use updateButton method instead
	 * 
	 * @param station the station which has changed owner
	 * @param player the new owner of the station
	 */
	public void ownerChanged(Station station, Player player);
}
