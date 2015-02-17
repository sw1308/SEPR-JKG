package com.TeamHEC.LocomotionCommotion.MapActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
/*
 * Holds all the textures and file paths for all actors in StartMenu
 * This means if we need to change a file path you come here.
 */
public class Game_Map_TextureManager{
	private static Game_Map_TextureManager instance = null;
	
	protected Game_Map_TextureManager()
	{}
	
	/**
	 * gets an instance of the texture manager
	 * @return
	 */
	public static Game_Map_TextureManager getInstance() {
		if(instance == null)
			instance = new Game_Map_TextureManager();
		return instance;
	}	
	
	//Map
	public Texture map = new Texture(Gdx.files.internal("gameScreen/game_map/map.png"));
	public Texture mapInfo = new Texture(Gdx.files.internal("gameScreen/game_map/mapinfo.png"));
	public Texture station = new Texture(Gdx.files.internal("gameScreen/game_map/station.png"));
	public Texture stationBroken = new Texture(Gdx.files.internal("gameScreen/game_map/station-broken.png"));
	public Texture stationx2 = new Texture(Gdx.files.internal("gameScreen/game_map/Stop.png"));
	public Texture stationx2Broken = new Texture(Gdx.files.internal("gameScreen/game_map/Stop-broken.png"));
	
	public Texture stationInfo = new Texture(Gdx.files.internal("gameScreen/game_map/stationInfoframe.png"));
	public Texture stationSelect = new Texture(Gdx.files.internal("gameScreen/game_map/stationSelectBtn.png"));
	public Texture stationRepair = new Texture(Gdx.files.internal("gameScreen/game_map/stationRepairBtn.png"));
	public Texture stationBuy = new Texture(Gdx.files.internal("gameScreen/game_map/stationBuyBtn.png"));
	public Texture stationUpgrade = new Texture(Gdx.files.internal("gameScreen/game_map/stationUpgradeBtn.png"));
	public Texture trainInfo = new Texture(Gdx.files.internal("gameScreen/game_map/trainInfo.png"));
	public Texture trainInfoPlanRoute = new Texture(Gdx.files.internal("gameScreen/game_map/trainInfoPlanRoute.png"));
	
	public Texture junction = new Texture(Gdx.files.internal("gameScreen/game_map/junction.png"));
	public Texture junctionx2 = new Texture(Gdx.files.internal("gameScreen/game_map/junction2.png"));
	
	public Texture p1Station = new Texture(Gdx.files.internal("gameScreen/game_map/p1station.png"));
	public Texture p1StationBroken = new Texture(Gdx.files.internal("gameScreen/game_map/p1station-broken.png"));
	public Texture p1Stationx2 = new Texture(Gdx.files.internal("gameScreen/game_map/p1station2.png"));
	public Texture p1Stationx2Broken = new Texture(Gdx.files.internal("gameScreen/game_map/p1station2-broken.png"));
	public Texture p1Train = new Texture(Gdx.files.internal("gameScreen/game_map/p1train.png"));
	public Texture p1Trainx2 = new Texture(Gdx.files.internal("gameScreen/game_map/p1train2.png"));
	
	public Texture p2Station = new Texture(Gdx.files.internal("gameScreen/game_map/p2station.png"));
	public Texture p2StationBroken = new Texture(Gdx.files.internal("gameScreen/game_map/p2station-broken.png"));
	public Texture p2Stationx2 = new Texture(Gdx.files.internal("gameScreen/game_map/p2station2.png"));
	public Texture p2Stationx2Broken = new Texture(Gdx.files.internal("gameScreen/game_map/p2station2-broken.png"));
	public Texture p2Train = new Texture(Gdx.files.internal("gameScreen/game_map/p2train.png"));
	public Texture p2Trainx2 = new Texture(Gdx.files.internal("gameScreen/game_map/p2train2.png"));
	
	public Texture routeBlip = new Texture(Gdx.files.internal("gameScreen/game_map/routeBlip.png"));
	public Texture redRouteBlip = new Texture(Gdx.files.internal("gameScreen/game_map/routeBlip2.png"));
	
}
