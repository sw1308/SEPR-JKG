package com.TeamHEC.LocomotionCommotion.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
/**
 * Holds all the textures and file paths for all actors in StartMenu
 * This means if we need to change a file path you come here.
 */
public class SM_TextureManager {
	private static SM_TextureManager instance;
	
	protected SM_TextureManager()
	{}
	
	public static SM_TextureManager getInstance() {
		if(instance == null)
			instance = new SM_TextureManager();
		return instance;
	}	
	
	//Start Menu
	//Start Menu Main Page
	public Texture sm_main_title = new Texture(Gdx.files.internal("startMenu/sm_main/smTitle.png"));
	public Texture sm_main_newgamebtn = new Texture(Gdx.files.internal("startMenu/sm_main/sm_newgame.png"));
	public Texture sm_main_loadgamebtn = new Texture(Gdx.files.internal("startMenu/sm_main/sm_loadgame.png"));
	public Texture sm_main_preferencesbtn = new Texture(Gdx.files.internal("startMenu/sm_main/sm_preferences.png"));
	public Texture sm_main_howtoplaybtn = new Texture(Gdx.files.internal("startMenu/sm_main/sm_howtoplay.png"));
	public Texture sm_main_exitButton = new Texture(Gdx.files.internal("startMenu/sm_main/sm_exitgame.png"));
	public Texture sm_main_linesimg = new Texture(Gdx.files.internal("startMenu/sm_main/lines.png"));
	
	//Start Menu NewGame Page
	public Texture sm_newgame_MenuText = new Texture(Gdx.files.internal("startMenu/sm_newgame/newgamescreen.png"));
	public Texture sm_newgame_BackBtn = new Texture(Gdx.files.internal("startMenu/sm_newgame/backButton.png"));
	public Texture sm_newgame_GoBtn = new Texture(Gdx.files.internal("startMenu/sm_newgame/goBtn.png"));
	public Texture sm_newgame_TempTextBox = new Texture(Gdx.files.internal("startMenu/sm_newgame/tempTextBox.png"));
	public Texture sm_newgame_TurnTimeOutBtn = new Texture(Gdx.files.internal("startMenu/sm_newgame/turnTimeoutBtn.png"));
	public Texture sm_newgameTurnTimeOut_unselected_Btn = new Texture(Gdx.files.internal("startMenu/sm_newgame/turnTimeout_unselected_Btn.png"));
	public Texture sm_newgame_StationDomBtn = new Texture(Gdx.files.internal("startMenu/sm_newgame/stationDominationBtn.png"));
	public Texture sm_newgame_StationDom_unselected_Btn = new Texture(Gdx.files.internal("startMenu/sm_newgame/stationDomination_unselected_Btn.png"));
	public Texture sm_newgame_Turn50Btn = new Texture(Gdx.files.internal("startMenu/sm_newgame/turn50.png"));
	public Texture sm_newgame_Turn100Btn = new Texture(Gdx.files.internal("startMenu/sm_newgame/turn100.png"));
	public Texture sm_newgame_Turn150Btn = new Texture(Gdx.files.internal("startMenu/sm_newgame/turn150.png"));
	public Texture sm_newgame_Turn50_unselected_Btn = new Texture(Gdx.files.internal("startMenu/sm_newgame/turn50_unselected.png"));
	public Texture sm_newgame_Turn100_unselected_Btn = new Texture(Gdx.files.internal("startMenu/sm_newgame/turn100_unselected.png"));
	public Texture sm_newgame_Turn150_unselected_Btn = new Texture(Gdx.files.internal("startMenu/sm_newgame/turn150_unselected.png"));
	
	//StartMenu LoadGame Page
	public Texture sm_loadgame_Title = new Texture(Gdx.files.internal("startMenu/sm_loadgame/loadgametitle.png"));
	public Texture sm_loadgame_Examples = new Texture(Gdx.files.internal("startMenu/sm_loadgame/loadgameexamples.png"));
	
	//StartMenu Preferences Page
	public Texture sm_preferences_VertLine = new Texture(Gdx.files.internal("startMenu/sm_preferences/vertline.png"));
	public Texture sm_preferences_Title = new Texture(Gdx.files.internal("startMenu/sm_preferences/preferencestitle.png"));
	public Texture sm_preferences_GameSettingsBtn = new Texture(Gdx.files.internal("startMenu/sm_preferences/gamesettingsbtn.png"));
	public Texture sm_preferences_DisplaySettingsBtn = new Texture(Gdx.files.internal("startMenu/sm_preferences/displaysettingsbtn.png"));
	public Texture sm_preferences_SoundSettingsBtn = new Texture(Gdx.files.internal("startMenu/sm_preferences/soundsettingsbtn.png"));
	public Texture sm_preferences_ControlSettingsBtn = new Texture(Gdx.files.internal("startMenu/sm_preferences/controlsettingsbtn.png"));
	
	
	//StartMenu HowtoPlayPage
	public Texture sm_howtoplay_line = new Texture(Gdx.files.internal("startMenu/sm_howtoplay/howtoplayline.png"));
	public Texture sm_howtoplay_frame = new Texture(Gdx.files.internal("startMenu/sm_howtoplay/howtoplayframe.png"));
	public Texture sm_howtoplay_title = new Texture(Gdx.files.internal("startMenu/sm_howtoplay/howtoplaytitle.png"));
	public Texture sm_howtoplay_nextbtn = new Texture(Gdx.files.internal("startMenu/sm_howtoplay/nextbtn.png"));
	public Texture sm_howtoplay_previousbtn = new Texture(Gdx.files.internal("startMenu/sm_howtoplay/previousbtn.png"));
	public Texture sm_howtoplay_homebtn = new Texture(Gdx.files.internal("startMenu/sm_howtoplay/homebtn.png"));
	
}
