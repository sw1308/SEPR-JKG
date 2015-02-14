package com.TeamHEC.LocomotionCommotion.UI_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
/**
 * 
 * @author Robert Precious <rp825@york.ac.uk>
 * 
 * This is the centre for all the Texture's used in the game except for Map textures.
 * When fetching a Texture use : Game_TextureManager.getInstance().<desired texture name>
 *
 */
public class Game_TextureManager {
	private static Game_TextureManager instance;
	
	
	protected Game_TextureManager()
	{}
	
	public static Game_TextureManager getInstance() {
		if(instance == null)
			instance = new Game_TextureManager();
		return instance;
	}	
	
	//Top Bar
	public  Texture game_menuobject_topbar = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/TopBar.png"));
	public Texture game_menuobject_menubtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/menubtn.png"));
	
	//Resources
	public Texture game_menuobject_resourcesbar = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/resourcesbar.png"));
	//Bottom right corner
	public Texture game_menuobject_endturnbutton = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/endTurnBtn.png"));
	public Texture game_menuobject_cornerframe = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/cornerframe.png"));
	public Texture game_menuobject_infobutton = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/infobutton.png"));
		
	//Map
	public Texture map = new Texture(Gdx.files.internal("gameScreen/game_map/map.png"));
	public Texture mapInfo = new Texture(Gdx.files.internal("gameScreen/game_map/mapinfo.png"));
	
	//Pause Menu
	public Texture game_pause_blackoutscreen = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_Pausemenu/screen.png"));
	public Texture game_pause_pauselogo = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_Pausemenu/pauselogo.png"));
	public Texture game_pause_resumegame = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_Pausemenu/resumegamebtn.png"));
	public Texture game_pause_savegame = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_Pausemenu/savegamebtn.png"));
	public Texture game_pause_settings = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_Pausemenu/settingsbtn.png"));
	public Texture game_pause_mainmenu = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_Pausemenu/mainmenubtn.png"));
	public Texture game_pause_background = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_Pausemenu/pausebackground.png"));
	
	//shop
	public Texture game_shop_backdrop = new Texture(Gdx.files.internal("gameScreen/game_shop/shopbackdrop.png"));
	public Texture game_shop_backbtn = new Texture(Gdx.files.internal("gameScreen/game_shop/backbtn.png"));
	public Texture game_shop_shopbtn = new Texture(Gdx.files.internal("gameScreen/game_shop/shopbtn.png"));
	public Texture game_shop_title = new Texture(Gdx.files.internal("gameScreen/game_shop/title.png"));
	//train depot
	public Texture game_traindepot_title = new Texture(Gdx.files.internal("gameScreen/game_traindepot/title.png"));
	public Texture game_traindepot_traindepotbtn = new Texture(Gdx.files.internal("gameScreen/game_traindepot/traindepotbtn.png"));

	//Goals
	public Texture game_menuobject_ticketbtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/ticketbtn.png"));
	public Texture game_menuobject_ticket = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/ticket.png"));
	public Texture game_menuobject_emptyticket = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/emptyticket.png"));
	public Texture game_menuobject_ticketenclosure = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/ticketenclosure.png"));
	public Texture game_menuobject_removegoalbtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/removebtn.png"));
	public Texture game_menuobject_redobtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/redobtn.png"));
	public Texture game_menuobject_addgoalbtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/addgoalbtn.png"));
	//Routing
	public Texture game_menuobject_planroutebtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/planroutebtn.png"));
	public Texture game_menuobject_editroutebtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/editroutebtn.png"));
	public Texture routingModeWindow = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/routingModeWindow.png")); 
	public Texture confirmroutingModebtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/confirmRoute.png"));
	public Texture undoRouteBtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/undoRoute.png")); 
	public Texture abortRouteBtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/abortRoute.png")); 
	public Texture cancelRouteBtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/game_tickets/routeCancel.png"));
	
	public Texture game_goals_goalscreenbtn = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/goalScreenBtn.png"));
	public Texture game_goals_newgoals= new Texture(Gdx.files.internal("gameScreen/game_goalScreen/newgoals.png"));
	public Texture game_goals_backdrop = new Texture(Gdx.files.internal("gameScreen/game_goalScreen/screen.png"));

	//Cards
	public Texture game_card_gofasterstripescard = new Texture(Gdx.files.internal("gameScreen/game_cards/gofasterstripecard.png"));
	public Texture game_card_teleportcard = new Texture(Gdx.files.internal("gameScreen/game_cards/teleportCard.png"));
	public Texture game_card_goldcard = new Texture(Gdx.files.internal("gameScreen/game_cards/goldCard.png"));
	public Texture game_card_coalcard = new Texture(Gdx.files.internal("gameScreen/game_cards/coalCard.png"));
	public Texture game_card_oilcard = new Texture(Gdx.files.internal("gameScreen/game_cards/oilCard.png"));
	public Texture game_card_electriccard = new Texture(Gdx.files.internal("gameScreen/game_cards/electricCard.png"));
	public Texture game_card_nuclearcard = new Texture(Gdx.files.internal("gameScreen/game_cards/nuclearCard.png"));
	public Texture game_card_cardtoggle = new Texture(Gdx.files.internal("gameScreen/game_cards/Cardbtn-1.png"));
	public Texture game_card_usecardbtn = new Texture(Gdx.files.internal("gameScreen/game_cards/usecardbtn.png"));
		
	//SHOP
	public Texture game_shop_startscreen = new Texture(Gdx.files.internal("gameScreen/game_shop/startpage.png"));
	public Texture game_shop_startbuy = new Texture(Gdx.files.internal("gameScreen/game_shop/startBuy.png"));
	public Texture game_shop_startsell = new Texture(Gdx.files.internal("gameScreen/game_shop/startSell.png"));
	public Texture game_shop_starttrain = new Texture(Gdx.files.internal("gameScreen/game_shop/startTrains.png"));
	
	public Texture game_shop_coalitem = new Texture(Gdx.files.internal("gameScreen/game_shop/item_coal.png"));
	public Texture game_shop_oilitem = new Texture(Gdx.files.internal("gameScreen/game_shop/item_oil.png"));
	public Texture game_shop_electricityitem = new Texture(Gdx.files.internal("gameScreen/game_shop/item_electricity.png"));
	public Texture game_shop_nuclearitem = new Texture(Gdx.files.internal("gameScreen/game_shop/item_nuclear.png"));
	public Texture game_shop_coaltrain = new Texture(Gdx.files.internal("gameScreen/game_shop/item_coalTrain.png"));
	public Texture game_shop_oiltrain = new Texture(Gdx.files.internal("gameScreen/game_shop/item_oilTrain.png"));
	public Texture game_shop_electricitytrain = new Texture(Gdx.files.internal("gameScreen/game_shop/item_electricityTrain.png"));
	public Texture game_shop_nucleartrain = new Texture(Gdx.files.internal("gameScreen/game_shop/item_nuclearTrain.png"));
	public Texture game_shop_carditem = new Texture(Gdx.files.internal("gameScreen/game_shop/item_card.png"));
	public Texture game_shop_trainitem = new Texture(Gdx.files.internal("gameScreen/game_shop/item_train.png"));
	
	public Texture game_shop_addbtn = new Texture(Gdx.files.internal("gameScreen/game_shop/addbutton.png"));
	public Texture game_shop_minusbtn = new Texture(Gdx.files.internal("gameScreen/game_shop/minusbutton.png"));
	public Texture game_shop_buybtn = new Texture(Gdx.files.internal("gameScreen/game_shop/buybtn.png"));
	public Texture game_shop_sellbtn = new Texture(Gdx.files.internal("gameScreen/game_shop/sellbtn.png"));
	public Texture game_shop_blankbuybtn = new Texture(Gdx.files.internal("gameScreen/game_shop/blankbuybtn.png"));
	
	//StartGame
	public Texture game_start_getstartedwindow = new Texture(Gdx.files.internal("gameScreen/game_startsequence/getstartedwindow.png"));
	public Texture game_start_getstartedwindow2 = new Texture(Gdx.files.internal("gameScreen/game_startsequence/getstartedwindow-witharrow.png"));
	
	//Warning Window
	public Texture game_warningwindow = new Texture(Gdx.files.internal("gameScreen/game_MenuObjects/warningwindow.png"));
	

	}
