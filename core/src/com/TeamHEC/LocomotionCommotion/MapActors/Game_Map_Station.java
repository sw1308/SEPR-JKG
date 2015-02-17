package com.TeamHEC.LocomotionCommotion.MapActors;

import com.TeamHEC.LocomotionCommotion.Game.GameScreen;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Map.StationListener;
import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;

public class Game_Map_Station extends Game_Map_MapObj implements StationListener {

	public boolean owned;
	private Station station;

	public float offset = 0;

	public Game_Map_Station(Station station, float actorX, float actorY)
	{
		super(actorX, actorY, Game_Map_TextureManager.getInstance().station, Game_Map_TextureManager.getInstance().stationx2);
		this.station = station;
		this.owned = false;
		station.register(this);
	}
	
	/**
	 * Returns the station
	 * @return Station
	 */
	public Station getStation()
	{
		return station;
	}
	/**
	 * Update the button texture according to owner and faults
	 */
	@Override
	public void updateButton(Station station, Player player)
	{
		if(station.isFaulty()){
			//station is broken
			if(player == null){
				texture = Game_Map_TextureManager.getInstance().stationBroken;
				toggleTexture1 = Game_Map_TextureManager.getInstance().stationBroken;
				toggleTexture2 = Game_Map_TextureManager.getInstance().stationx2Broken;
			}
			else if(player.isPlayer1){
				texture = Game_Map_TextureManager.getInstance().p1StationBroken;
				toggleTexture1 = Game_Map_TextureManager.getInstance().p1StationBroken;
				toggleTexture2 = Game_Map_TextureManager.getInstance().p1Stationx2Broken;
			}
			else{
				texture = Game_Map_TextureManager.getInstance().p2StationBroken;
				toggleTexture1 = Game_Map_TextureManager.getInstance().p2StationBroken;
				toggleTexture2 = Game_Map_TextureManager.getInstance().p2Stationx2Broken;
			}
		}
		else{
			//station not broken
			if(player == null){
				texture = Game_Map_TextureManager.getInstance().station;
				toggleTexture1 = Game_Map_TextureManager.getInstance().station;
				toggleTexture2 = Game_Map_TextureManager.getInstance().stationx2;
			}
			else if(player.isPlayer1){
				texture = Game_Map_TextureManager.getInstance().p1Station;
				toggleTexture1 = Game_Map_TextureManager.getInstance().p1Station;
				toggleTexture2 = Game_Map_TextureManager.getInstance().p1Stationx2;
			}
			else{
				texture = Game_Map_TextureManager.getInstance().p2Station;
				toggleTexture1 = Game_Map_TextureManager.getInstance().p2Station;
				toggleTexture2 = Game_Map_TextureManager.getInstance().p2Stationx2;
			}
		}
	}

	@Override
	protected void onClicked()
	{
		if(this.station.getRepairable()){
			super.onClicked();
			Game_Map_StationBtn.selectedStation = this;
			if(!highlighted)
			{	
				highlighted = true;
				if(!Game_Map_Manager.routingModeWindow.isVisible())
					showInfoBox(Game_Map_StationBtn.selectedStation.getStation().isFaulty());
			}
			else
			{
				highlighted = false;
				hideInfoBox();
			}
		}
		else{
			WarningMessage.fireWarningWindow("Sorry!", "This station is beyond repair.");
		}
	}
	/**
	 * show the info box for a selected station
	 * @param faulty
	 */
	public void showInfoBox(boolean faulty)
	{
		for(int i = Game_Map_Manager.stagestart;i <= Game_Map_Manager.stagestart + Game_Map_Manager.mapActors-1; i++)	
		{ 	
			if (i > GameScreen.getStage().getActors().size-1){
			}
			else
			{
				GameScreen.getStage().getActors().get(i).setVisible(true);
			}
		}
		// Sets the labels to info from each station:
		Game_Map_Manager.stationLabelName.setText(station.getName());
		Game_Map_Manager.stationLabelCost.setText(String.format("%d", station.getBaseValue() ));
		Game_Map_Manager.stationLabelFuel.setText(String.format("%d * %s", station.getResourceType().getValue(), station.getResourceString()));

		Game_Map_Manager.moveInfoBox(this.actorX-180, this.actorY-80, faulty);


	}
	/**
	 * hides the info box for a station
	 */
	public void hideInfoBox(){
		for(int i=Game_Map_Manager.stagestart; i<=Game_Map_Manager.stagestart +Game_Map_Manager.mapActors-1;i++)	
		{ 	
			if (i > GameScreen.getStage().getActors().size-1){
			}
			else
			{
				GameScreen.getStage().getActors().get(i).setVisible(false);
			}
		}		
	}
	
	/**
	 * sets if a station is owned
	 * @param Boolean owned
	 */
	public void setOwned(Boolean b)
	{
		this.owned =b;
	}
}
