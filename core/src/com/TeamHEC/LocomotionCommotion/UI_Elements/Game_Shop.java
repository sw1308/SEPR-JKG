package com.TeamHEC.LocomotionCommotion.UI_Elements;

import java.util.ArrayList;

import com.TeamHEC.LocomotionCommotion.Card.Game_CardHand;
import com.TeamHEC.LocomotionCommotion.Game.GameScreen;
import com.TeamHEC.LocomotionCommotion.Player.Shop;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_Shop.ShopHomeScreen.Game_shop_electric_train;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_Shop.ShopHomeScreen.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;

/**
 * 
 * @author Robert Precious <rp825@york.ac.uk>
 *
 */
public class Game_Shop {
	public static Game_ShopManager actorManager;

	public void create(Stage stage){
		actorManager = new Game_ShopManager();
		actorManager.create(stage);


	}

	//ACTOR MANAGER
	public static class Game_ShopManager {

		private final static Array<Actor> actors = new Array<Actor>();
		private final static Array<Actor> startactors = new Array<Actor>();

		public Sprite game_shop_backdrop, game_shop_title;
		public SpriteButton game_shop_backbtn;
		public Game_shop_coal coalitem;
		public Game_shop_oil oilitem;
		public Game_shop_electric electricityitem;
		public Game_shop_nuclear nuclearitem;
		public Game_shop_coal_train coaltrain;
		public Game_shop_oil_train oiltrain;
		public Game_shop_electric_train electricitytrain;
		public Game_shop_nuclear_train nucleartrain;
		public Game_shop_card carditem;
		public Game_shop_train trainitem;
		public  ShopHomeScreen startpage;

		public Label goldLabel;
		public Label titleLabel;
		public static LabelStyle style;


		public boolean open=false;
		public boolean start=true;

		public static int  stageStart, stageEnd;
		public static int  startScreenStageStart, startScreenStageEnd;

		public boolean buy=false;
		public boolean sell=false;


		public Game_ShopManager(){	}

		public void create(Stage stage){

			actors.clear();
			stageStart =0;
			stageEnd=0;
			startScreenStageStart=0;
			startScreenStageEnd=0;




			game_shop_backdrop = new Sprite(-20,-15,Game_TextureManager.getInstance().game_shop_backdrop);
			actors.add(game_shop_backdrop);
			game_shop_title = new Sprite(170,820,Game_TextureManager.getInstance().game_shop_title);
			actors.add(game_shop_title);
			game_shop_backbtn = new SpriteButton(1350,860,Game_TextureManager.getInstance().game_shop_backbtn){
				@Override
				protected void onClicked(){
					if (Game_Shop.actorManager.start == false)
					{
						Game_Shop.actorManager.start= true;
						for(int i=Game_Shop.actorManager.getstartScreenStageStart(); i<=Game_Shop.actorManager.getstartScreenStageEnd();i++){
							if (i > GameScreen.getStage().getActors().size-1){

							}else
								GameScreen.getStage().getActors().get(i).setVisible(true);

						}			}
					else
					{	Game_Shop.actorManager.start= false;
					for(int i=Game_Shop.actorManager.getstartScreenStageStart(); i<=Game_Shop.actorManager.getstartScreenStageEnd();i++){
						if (i > GameScreen.getStage().getActors().size-1){

						}else
							GameScreen.getStage().getActors().get(i).setVisible(false);

					}

					}
				}
			};
			actors.add(game_shop_backbtn);

			coalitem = new Game_shop_coal();
			oilitem = new Game_shop_oil();
			electricityitem = new Game_shop_electric();
			nuclearitem = new Game_shop_nuclear();
			coaltrain = new Game_shop_coal_train();
			oiltrain = new Game_shop_oil_train();
			electricitytrain = new Game_shop_electric_train();
			nucleartrain = new Game_shop_nuclear_train();
			carditem = new Game_shop_card();
			trainitem = new Game_shop_train();
			startpage = new ShopHomeScreen();
			//Stuff for Labels for gold
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = 50;

			BitmapFont font = generator.generateFont(parameter); 
			generator.dispose();
			style = new LabelStyle();
			style.font = font;

			//end
			goldLabel= new Label(null,style);
			goldLabel.setX(750);
			goldLabel.setY(880);
			goldLabel.setColor(0,0,0,1);
			goldLabel.setText("");

			//Stuff for Labels for gold
			generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
			parameter = new FreeTypeFontParameter();
			parameter.size = 80;

			font = generator.generateFont(parameter); 
			generator.dispose();
			style = new LabelStyle();
			style.font = font;

			//end
			titleLabel= new Label(null,style);
			titleLabel.setX(380);
			titleLabel.setY(880);
			titleLabel.setColor(0,0,0,1);
			titleLabel.setText("");

			actors.add(Game_Shop.actorManager.goldLabel);
			actors.add(Game_Shop.actorManager.titleLabel);

			for(Actor a : oilitem.getActors()){
				actors.add(a);
			}
			for(Actor a : coalitem.getActors()){
				actors.add(a);
			}
			for(Actor a : electricityitem.getActors()){
				actors.add(a);
			}
			for(Actor a : nuclearitem.getActors()){
				actors.add(a);
			}
			for(Actor a : carditem.getActors()){
				actors.add(a);
			}
			for(Actor a : trainitem.getActors()){
				actors.add(a);
			}
			for(Actor a : startpage.getActors()){
				startactors.add(a);
			}






			stageStart= stage.getActors().size;
			for (Actor a : actors){
				if(open == true){
					a.setTouchable(Touchable.enabled);
					a.setVisible(true);}
				else
					a.setVisible(false);

				stage.addActor(a);
				stageEnd ++;
			}
			startScreenStageStart= stage.getActors().size;
			startScreenStageEnd = startScreenStageStart + startactors.size-1;

			for (Actor a : startactors){
				if(open == true){
					a.setTouchable(Touchable.enabled);
					a.setVisible(true);}
				else
					a.setVisible(false);

				stage.addActor(a);

			}
			stageEnd= stageStart+actors.size+startactors.size-1;


		}
		/**
		 * Gets the stage start
		 * @return Int stage start
		 */
		public  int getStageStart(){
			return stageStart;
		}
		/**
		 * get the stage end
		 * @return Int stage end
		 */
		public  int getStageEnd(){
			return stageEnd;
		}
		/**
		 * get the stage start for the start screen
		 * @return int stage screen stage start
		 */
		public int getstartScreenStageStart(){
			return startScreenStageStart;
		}
		/**
		 * get the stage end for the start screen
		 * @return int start screen stage end
		 */
		public  int getstartScreenStageEnd(){
			return startScreenStageEnd;
		}
		/**
		 * refreshes the player's gold
		 * @param i
		 */
		public static void refreshgold(int i){
			String g = new Integer(i).toString();
			Game_Shop.actorManager.goldLabel.setText(g);
			GameScreenUI.goldQuant.setText(g);
		}

	}
	/**
	 * Converts a string builder to an integer
	 * @param stringBuilder
	 * @return Int
	 */
	public static int strToInt( StringBuilder stringBuilder ){
		int i = 0;
		int num = 0;
		boolean isNeg = false;

		//Check for negative sign; if it's there, set the isNeg flag
		if (stringBuilder.charAt(0) == '-') {
			isNeg = true;
			i = 1;
		}

		//Process each character of the string;
		while( i < stringBuilder.length()) {
			num *= 10;
			num += stringBuilder.charAt(i++) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
		}

		if (isNeg)
			num = -num;
		return num;
	}


	//SHOP Actors
	//Shop Object Classes
	//SHOP HOME Screen---------------------------------------------------------------
	public static class ShopHomeScreen {
		ArrayList<Actor> actors ;


		public ShopHomeScreen(){
			this.actors = new ArrayList<Actor>();
			Sprite shopscreen = new Sprite(45, 17, Game_TextureManager.getInstance().game_shop_startscreen);
			this.actors.add(shopscreen);
			SpriteButton back = new SpriteButton(1350,860, Game_TextureManager.getInstance().game_shop_backbtn){
				@Override
				protected void onClicked(){
					Game_Shop.actorManager.buy=false;
					Game_Shop.actorManager.sell=false;
					if (Game_Shop.actorManager.open== false)
					{
						Game_Shop.actorManager.open= true;
						for(int i=Game_Shop.actorManager.getStageStart(); i<=Game_Shop.actorManager.getStageEnd();i++){
							if (i > GameScreen.getStage().getActors().size-1){

							}else
								GameScreen.getStage().getActors().get(i).setVisible(true);
						}
					}
					else
					{	Game_Shop.actorManager.open= false;
						for(int i=Game_Shop.actorManager.getStageStart(); i<=Game_Shop.actorManager.getStageEnd();i++){
							if (i > GameScreen.getStage().getActors().size-1){

							}else
								GameScreen.getStage().getActors().get(i).setVisible(false);
						}

					}

				}
			};
			this.actors.add(back);

			SpriteButton shopbuyButton = new SpriteButton(250,350,Game_TextureManager.getInstance().game_shop_startbuy){
				@Override
				protected void onClicked(){
					setToBuy();
					if (Game_Shop.actorManager.start== false)
					{
						Game_Shop.actorManager.start= true;
						for(int i=Game_Shop.actorManager.getstartScreenStageStart(); i<=Game_Shop.actorManager.getstartScreenStageEnd(); i++){
							if (i > GameScreen.getStage().getActors().size-1){

							}else
								GameScreen.getStage().getActors().get(i).setVisible(true);

						}
					}
					else
					{	Game_Shop.actorManager.start= false;
						for(int i=Game_Shop.actorManager.getstartScreenStageStart(); i<=Game_Shop.actorManager.getstartScreenStageEnd(); i++){
							if (i > GameScreen.getStage().getActors().size-1){

							}else
								GameScreen.getStage().getActors().get(i).setVisible(false);
							
						}

					}

				}
			};
			this.actors.add(shopbuyButton);

			SpriteButton shopsellButton = new SpriteButton(650,350,Game_TextureManager.getInstance().game_shop_startsell){
				@Override
				protected void onClicked(){
					setToSell();
					if (Game_Shop.actorManager.start== false)
					{
						Game_Shop.actorManager.start= true;
						for(int i=Game_Shop.actorManager.getstartScreenStageStart(); i<=Game_Shop.actorManager.getstartScreenStageEnd();i++){
							if (i > GameScreen.getStage().getActors().size-1){

							}else
								GameScreen.getStage().getActors().get(i).setVisible(true);

						}			}
					else
					{	Game_Shop.actorManager.start= false;
					for(int i=Game_Shop.actorManager.getstartScreenStageStart(); i<=Game_Shop.actorManager.getstartScreenStageEnd();i++){
						if (i > GameScreen.getStage().getActors().size-1){

						}else
							GameScreen.getStage().getActors().get(i).setVisible(false);

					}

					}

				}
			};
			this.actors.add(shopsellButton);
			
			SpriteButton train = new SpriteButton(1050,350, Game_TextureManager.getInstance().game_shop_starttrain){
				@Override
				protected void onClicked(){
				}

			};
			this.actors.add(train);


		}

		public ArrayList<Actor> getActors() {
			return this.actors;
		}

		/**
		 * Seth the shop to sell mode
		 */
		public void  setToSell(){
			Game_Shop.actorManager.buy=false;
			Game_Shop.actorManager.sell=true;
			Texture t = Game_TextureManager.getInstance().game_shop_sellbtn;
			Game_Shop.actorManager.coalitem.buyButton.setTexture(t);
			Game_Shop.actorManager.oilitem.buyButton.setTexture(t);
			Game_Shop.actorManager.electricityitem.buyButton.setTexture(t);
			Game_Shop.actorManager.nuclearitem.buyButton.setTexture(t);
			Game_Shop.actorManager.carditem.buyButton.setTexture(t);
			Game_Shop.actorManager.trainitem.costLabel.setText("Sell Trains");
			Game_Shop.actorManager.titleLabel.setText("Sell");

			//Cost is different if selling
			if(Game_Shop.actorManager.sell && !Game_StartingSequence.inProgress)
			{
				Game_Shop.actorManager.carditem.costLabel.setText(""+Shop.cardSellPrice);
				Game_Shop.actorManager.coalitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.coalitem.quantityLabel.getText())*Shop.coalSellPrice);
				Game_Shop.actorManager.oilitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.oilitem.quantityLabel.getText())*Shop.oilSellPrice);
				Game_Shop.actorManager.electricityitem.costLabel.setText(""+Math.ceil((strToInt(Game_Shop.actorManager.electricityitem.quantityLabel.getText())*Shop.electricSellPrice)));
				Game_Shop.actorManager.nuclearitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.nuclearitem.quantityLabel.getText())*Shop.nuclearSellPrice);
			}
			else
			{
				Game_Shop.actorManager.carditem.costLabel.setText(""+Shop.cardPrice);
				Game_Shop.actorManager.coalitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.coalitem.quantityLabel.getText())*Shop.coalPrice);
				Game_Shop.actorManager.oilitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.oilitem.quantityLabel.getText())*Shop.oilPrice);
				Game_Shop.actorManager.electricityitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.electricityitem.quantityLabel.getText())*Shop.electricPrice);
				Game_Shop.actorManager.nuclearitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.nuclearitem.quantityLabel.getText())*Shop.nuclearPrice);
			}
		}
		/**
		 * Sets the shop to buy mode 
		 */
		public void  setToBuy(){
			Game_Shop.actorManager.buy=true;
			Game_Shop.actorManager.sell=false;
			Texture t = Game_TextureManager.getInstance().game_shop_buybtn;
			Game_Shop.actorManager.coalitem.buyButton.setTexture(t);
			Game_Shop.actorManager.oilitem.buyButton.setTexture(t);
			Game_Shop.actorManager.electricityitem.buyButton.setTexture(t);
			Game_Shop.actorManager.nuclearitem.buyButton.setTexture(t);
			Game_Shop.actorManager.carditem.buyButton.setTexture(t);
			Game_Shop.actorManager.trainitem.costLabel.setText("Buy Trains");
			Game_Shop.actorManager.titleLabel.setText("Buy");
			
			//Cost is different if selling
			if(Game_Shop.actorManager.sell && !Game_StartingSequence.inProgress)
			{
				Game_Shop.actorManager.carditem.costLabel.setText(""+Shop.cardSellPrice);
				Game_Shop.actorManager.coalitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.coalitem.quantityLabel.getText())*Shop.coalSellPrice);
				Game_Shop.actorManager.oilitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.oilitem.quantityLabel.getText())*Shop.oilSellPrice);
				Game_Shop.actorManager.electricityitem.costLabel.setText(""+Math.ceil((strToInt(Game_Shop.actorManager.electricityitem.quantityLabel.getText())*Shop.electricSellPrice)));
				Game_Shop.actorManager.nuclearitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.nuclearitem.quantityLabel.getText())*Shop.nuclearSellPrice);
			}
			else
			{
				Game_Shop.actorManager.carditem.costLabel.setText(""+Shop.cardPrice);
				Game_Shop.actorManager.coalitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.coalitem.quantityLabel.getText())*Shop.coalPrice);
				Game_Shop.actorManager.oilitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.oilitem.quantityLabel.getText())*Shop.oilPrice);
				Game_Shop.actorManager.electricityitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.electricityitem.quantityLabel.getText())*Shop.electricPrice);
				Game_Shop.actorManager.nuclearitem.costLabel.setText(""+strToInt(Game_Shop.actorManager.nuclearitem.quantityLabel.getText())*Shop.nuclearPrice);
			}
		}


		//Card
		public static class Game_shop_card {
			ArrayList<Actor> actors ;
			public Label quantityLabel;
			public Label costLabel;
			public static Label goldLabel;
			public int quantity, cost;
			public static int posx=700;
			public static int posy=100;
			public static LabelStyle style;
			SpriteButton buyButton ;

			public Game_shop_card(){
				this.actors = new ArrayList<Actor>();

				Sprite cardItem = new Sprite(posx,posy,Game_TextureManager.getInstance().game_shop_carditem);
				buyButton = new SpriteButton(posx+75,posy+20,Game_TextureManager.getInstance().game_shop_buybtn){
					@Override
					protected void onClicked(){
						if (Game_Shop.actorManager.buy){						
							if(GameScreen.game.getPlayerTurn().getCards().size()<7){
								int newAdditionIndex = GameScreen.game.getPlayerTurn().getCards().size();
								GameScreen.game.getPlayerTurn().getShop().buyCard(false);
								if(GameScreen.game.getPlayerTurn().getCards().size()>newAdditionIndex){
									Game_CardHand.actorManager.addCard(GameScreen.game.getPlayerTurn().getCards().get(newAdditionIndex));
									GameScreenUI.refreshResources();
									Game_ShopManager.refreshgold(GameScreen.game.getPlayerTurn().getGold());
								}
							}
						}
					}

				};

				actors.add(cardItem);
				actors.add(buyButton);

				//Stuff for Labels
				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
				FreeTypeFontParameter parameter = new FreeTypeFontParameter();
				parameter.size = 32;

				BitmapFont font = generator.generateFont(parameter); 
				generator.dispose();
				style = new LabelStyle();
				style.font = font;

				//end			

				quantity =100;

				costLabel= new Label(null,style);
				costLabel.setX(posx+ 160);
				costLabel.setY(posy +43);
				costLabel.setColor(0,0,0,1);


				actors.add(costLabel);
			}





			public ArrayList<Actor> getActors() {
				return this.actors;
			}

		}

		//Coal
		public static class Game_shop_coal {
			ArrayList<Actor> actors ;
			public Label quantityLabel;
			public Label costLabel;
			public static Label goldLabel;
			public int quantity, cost;
			public static int posx=300;
			public static int posy=470;
			public static LabelStyle style;
			SpriteButton buyButton, minus, add;

			public Game_shop_coal(){
				this.actors = new ArrayList<Actor>();
				Sprite coalitem = new Sprite(posx,posy,Game_TextureManager.getInstance().game_shop_coalitem);
				add = new SpriteButton(posx+75,posy+42,Game_TextureManager.getInstance().game_shop_addbtn){
					@Override
					protected void onClicked(){
						quantity+=100;
						changeQuantity(100);

					}

				};
				minus = new SpriteButton(posx+220,posy+48,Game_TextureManager.getInstance().game_shop_minusbtn){
					@Override
					protected void onClicked(){
						if(quantity!=0){
							quantity-=100;
							changeQuantity(-100);}

					}

				};

				buyButton = new SpriteButton(posx+75,posy+20,Game_TextureManager.getInstance().game_shop_buybtn){
					@Override
					protected void onClicked(){
						if (Game_Shop.actorManager.buy){
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().buyFuel("Coal",quantity, false );									
						}
						if (Game_Shop.actorManager.sell){						
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().sellFuel("Coal", quantity, false);
						}
						GameScreenUI.refreshResources();
						Game_ShopManager.refreshgold(GameScreen.game.getPlayerTurn().getGold());
					}

				};

				actors.add(coalitem);
				actors.add(buyButton);
				actors.add(add);
				actors.add(minus);

				//Stuff for Labels
				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
				FreeTypeFontParameter parameter = new FreeTypeFontParameter();
				parameter.size = 32;

				BitmapFont font = generator.generateFont(parameter); 
				generator.dispose();
				style = new LabelStyle();
				style.font = font;

				//end

				quantity =100;

				quantityLabel= new Label(null,style);
				quantityLabel.setX(posx+ 145);
				quantityLabel.setY(posy +90);
				quantityLabel.setColor(0,0,0,1);
				quantityLabel.setText("100");

				costLabel= new Label(null,style);
				costLabel.setX(posx+ 160);
				costLabel.setY(posy +43);
				costLabel.setColor(0,0,0,1);
				//Cost is different is selling
				if(Game_Shop.actorManager.sell)
				{
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.coalSellPrice);
				}
				else
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.coalPrice);

				actors.add(quantityLabel);
				actors.add(costLabel);
			}

			public static void changeQuantity(int change){
				int newQuantity = strToInt(Game_Shop.actorManager.coalitem.quantityLabel.getText());
				newQuantity+=change;
				if (Game_Shop.actorManager.sell)
				{
					Game_Shop.actorManager.coalitem.costLabel.setText(""+(newQuantity*Shop.coalSellPrice));
				}
				else
				Game_Shop.actorManager.coalitem.costLabel.setText(""+(newQuantity*Shop.coalPrice));

				String l = new Integer(newQuantity).toString();
				Game_Shop.actorManager.coalitem.quantityLabel.setText(l);
			}


			public ArrayList<Actor> getActors() {
				return this.actors;
			}
		}
		
		//Coal Train
		public static class Game_shop_coal_train {
			ArrayList<Actor> actors ;
			public Label quantityLabel;
			public Label costLabel;
			public static Label goldLabel;
			public int quantity, cost;
			public static int posx=300;
			public static int posy=470;
			public static LabelStyle style;
			SpriteButton buyButton, minus, add;

			public Game_shop_coal_train(){
				this.actors = new ArrayList<Actor>();
				Sprite coaltrain = new Sprite(posx,posy,Game_TextureManager.getInstance().game_shop_coaltrain);
				add = new SpriteButton(posx+75,posy+42,Game_TextureManager.getInstance().game_shop_addbtn){
					@Override
					protected void onClicked(){
						quantity+=1;
						changeQuantity(1);

					}

				};
				minus = new SpriteButton(posx+220,posy+48,Game_TextureManager.getInstance().game_shop_minusbtn){
					@Override
					protected void onClicked(){
						if(quantity!=0){
							quantity-=1;
							changeQuantity(-1);}

					}

				};

				buyButton = new SpriteButton(posx+75,posy+20,Game_TextureManager.getInstance().game_shop_buybtn){
					@Override
					protected void onClicked(){
						if (Game_Shop.actorManager.buy){
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().buyTrain("Coal", quantity, false);;									
						}
						if (Game_Shop.actorManager.sell){						
							int quantity = strToInt(quantityLabel.getText());
							//no sell train implemented
						}
						GameScreenUI.refreshResources();
						Game_ShopManager.refreshgold(GameScreen.game.getPlayerTurn().getGold());
					}

				};

				actors.add(coaltrain);
				actors.add(buyButton);
				actors.add(add);
				actors.add(minus);
				
				//Stuff for Labels
				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
				FreeTypeFontParameter parameter = new FreeTypeFontParameter();
				parameter.size = 32;

				BitmapFont font = generator.generateFont(parameter); 
				generator.dispose();
				style = new LabelStyle();
				style.font = font;

				//end

				quantity =100;

				quantityLabel= new Label(null,style);
				quantityLabel.setX(posx+ 145);
				quantityLabel.setY(posy +90);
				quantityLabel.setColor(0,0,0,1);
				quantityLabel.setText("100");

				costLabel= new Label(null,style);
				costLabel.setX(posx+ 160);
				costLabel.setY(posy +43);
				costLabel.setColor(0,0,0,1);
				//Cost is different is selling
				if(Game_Shop.actorManager.sell)
				{
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.coalTrainSellPrice);
				}
				else
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.coalTrainPrice);

				actors.add(quantityLabel);
				actors.add(costLabel);
			}

			public static void changeQuantity(int change){
				int newQuantity = strToInt(Game_Shop.actorManager.coalitem.quantityLabel.getText());
				newQuantity+=change;
				if (Game_Shop.actorManager.sell)
				{
					Game_Shop.actorManager.coalitem.costLabel.setText(""+(newQuantity*Shop.coalTrainSellPrice));
				}
				else
					Game_Shop.actorManager.coalitem.costLabel.setText(""+(newQuantity*Shop.coalTrainPrice));
					String l = new Integer(newQuantity).toString();
				Game_Shop.actorManager.coalitem.quantityLabel.setText(l);
			}


			public ArrayList<Actor> getActors() {
				return this.actors;
			}
		}

		//Oil
		public static class Game_shop_oil {
			ArrayList<Actor> actors ;
			public Label quantityLabel;
			public Label costLabel;
			public static Label goldLabel;
			public int quantity, cost;
			public static int posx=700;
			public static int posy=470;
			public static LabelStyle style;
			SpriteButton buyButton, minus, add;

			public Game_shop_oil(){
				this.actors = new ArrayList<Actor>();
				Sprite oilitem = new Sprite(posx,posy,Game_TextureManager.getInstance().game_shop_oilitem);
				add = new SpriteButton(posx+75,posy+42,Game_TextureManager.getInstance().game_shop_addbtn){
					@Override
					protected void onClicked(){
						quantity+=100;
						changeQuantity(100);

					}

				};
				minus = new SpriteButton(posx+220,posy+48,Game_TextureManager.getInstance().game_shop_minusbtn){
					@Override
					protected void onClicked(){
						if(quantity!=0){
							quantity-=100;
							changeQuantity(-100);}

					}

				};

				buyButton = new SpriteButton(posx+75,posy+20,Game_TextureManager.getInstance().game_shop_buybtn){
					@Override
					protected void onClicked(){
						if (Game_Shop.actorManager.buy){
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().buyFuel("Oil",quantity, false );									
						}
						if (Game_Shop.actorManager.sell){						
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().sellFuel("Oil", quantity, false);
						}
						GameScreenUI.refreshResources();
						Game_ShopManager.refreshgold(GameScreen.game.getPlayerTurn().getGold());
					}

				};

				actors.add(oilitem);
				actors.add(buyButton);
				actors.add(add);
				actors.add(minus);

				//Stuff for Labels
				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
				FreeTypeFontParameter parameter = new FreeTypeFontParameter();
				parameter.size = 32;

				BitmapFont font = generator.generateFont(parameter); 
				generator.dispose();
				style = new LabelStyle();
				style.font = font;

				//end

				quantity =100;

				quantityLabel= new Label(null,style);
				quantityLabel.setX(posx+ 145);
				quantityLabel.setY(posy +90);
				quantityLabel.setColor(0,0,0,1);
				quantityLabel.setText("100");

				costLabel= new Label(null,style);
				costLabel.setX(posx+ 160);
				costLabel.setY(posy +43);
				costLabel.setColor(0,0,0,1);
				//Cost is different is selling
				if(Game_Shop.actorManager.sell)
				{
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.oilSellPrice);
				}
				else
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.oilPrice);


				actors.add(quantityLabel);
				actors.add(costLabel);
			}

			public static void changeQuantity(int change){
				int newQuantity = strToInt(Game_Shop.actorManager.oilitem.quantityLabel.getText());
				newQuantity+=change;
				if(Game_Shop.actorManager.sell)
				{
					Game_Shop.actorManager.oilitem.costLabel.setText(""+(newQuantity*Shop.oilSellPrice));
				}
				else
				Game_Shop.actorManager.oilitem.costLabel.setText(""+(newQuantity*Shop.oilPrice));

				String l = new Integer(newQuantity).toString();
				Game_Shop.actorManager.oilitem.quantityLabel.setText(l);
			}


			public ArrayList<Actor> getActors() {
				return this.actors;
			}
		}
		
		//Oil Train
		public static class Game_shop_oil_train {
			ArrayList<Actor> actors ;
			public Label quantityLabel;
			public Label costLabel;
			public static Label goldLabel;
			public int quantity, cost;
			public static int posx=300;
			public static int posy=470;
			public static LabelStyle style;
			SpriteButton buyButton, minus, add;

			public Game_shop_oil_train(){
				this.actors = new ArrayList<Actor>();
				Sprite oiltrain = new Sprite(posx,posy,Game_TextureManager.getInstance().game_shop_oiltrain);
				add = new SpriteButton(posx+75,posy+42,Game_TextureManager.getInstance().game_shop_addbtn){
					@Override
					protected void onClicked(){
						quantity+=1;
						changeQuantity(1);

					}

				};
				minus = new SpriteButton(posx+220,posy+48,Game_TextureManager.getInstance().game_shop_minusbtn){
					@Override
					protected void onClicked(){
						if(quantity!=0){
							quantity-=1;
							changeQuantity(-1);
						}
					}

				};

				buyButton = new SpriteButton(posx+75,posy+20,Game_TextureManager.getInstance().game_shop_buybtn){
					@Override
					protected void onClicked(){
						if (Game_Shop.actorManager.buy){
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().buyTrain("Oil", quantity, false);;									
						}
						if (Game_Shop.actorManager.sell){						
							int quantity = strToInt(quantityLabel.getText());
							//no sell train implemented
						}
						GameScreenUI.refreshResources();
						Game_ShopManager.refreshgold(GameScreen.game.getPlayerTurn().getGold());
					}

				};

				actors.add(oiltrain);
				actors.add(buyButton);
				actors.add(add);
				actors.add(minus);
					
				//Stuff for Labels
				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
				FreeTypeFontParameter parameter = new FreeTypeFontParameter();
				parameter.size = 32;

				BitmapFont font = generator.generateFont(parameter); 
				generator.dispose();
				style = new LabelStyle();
				style.font = font;

				//end

				quantity =100;

				quantityLabel= new Label(null,style);
				quantityLabel.setX(posx+ 145);
				quantityLabel.setY(posy +90);
				quantityLabel.setColor(0,0,0,1);
				quantityLabel.setText("100");

				costLabel= new Label(null,style);
				costLabel.setX(posx+ 160);
				costLabel.setY(posy +43);
				costLabel.setColor(0,0,0,1);
				//Cost is different is selling
				if(Game_Shop.actorManager.sell)
				{
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.oilTrainSellPrice);
				}
				else
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.oilTrainPrice);

				actors.add(quantityLabel);
				actors.add(costLabel);
			}

			public static void changeQuantity(int change){
				int newQuantity = strToInt(Game_Shop.actorManager.oilitem.quantityLabel.getText());
				newQuantity+=change;
				if (Game_Shop.actorManager.sell)
				{
					Game_Shop.actorManager.oilitem.costLabel.setText(""+(newQuantity*Shop.oilTrainSellPrice));
				}
				else
					Game_Shop.actorManager.oilitem.costLabel.setText(""+(newQuantity*Shop.oilTrainPrice));
					String l = new Integer(newQuantity).toString();
				Game_Shop.actorManager.oilitem.quantityLabel.setText(l);
			}

			public ArrayList<Actor> getActors() {
				return this.actors;
			}
		}

		//Electricity
		public static class Game_shop_electric {
			ArrayList<Actor> actors ;
			public Label quantityLabel;
			public Label costLabel;
			public static Label goldLabel;
			public int quantity, cost;
			public static int posx=1100;
			public static int posy=470;
			public static LabelStyle style;
			SpriteButton buyButton, minus, add;

			public Game_shop_electric(){
				this.actors = new ArrayList<Actor>();
				Sprite electricitem = new Sprite(posx,posy,Game_TextureManager.getInstance().game_shop_electricityitem);
				add = new SpriteButton(posx+75,posy+42,Game_TextureManager.getInstance().game_shop_addbtn){
					@Override
					protected void onClicked(){
						quantity+=100;
						changeQuantity(100);

					}

				};
				minus = new SpriteButton(posx+220,posy+48,Game_TextureManager.getInstance().game_shop_minusbtn){
					@Override
					protected void onClicked(){
						if(quantity!=0){
							quantity-=100;
							changeQuantity(-100);}

					}

				};

				buyButton = new SpriteButton(posx+75,posy+20,Game_TextureManager.getInstance().game_shop_buybtn){
					@Override
					protected void onClicked(){
						if (Game_Shop.actorManager.buy){
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().buyFuel("Electric",quantity, false );									
						}
						if (Game_Shop.actorManager.sell){						
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().sellFuel("Electric", quantity, false);
						}
						GameScreenUI.refreshResources();
						Game_ShopManager.refreshgold(GameScreen.game.getPlayerTurn().getGold());
					}

				};

				actors.add(electricitem);
				actors.add(buyButton);
				actors.add(add);
				actors.add(minus);

				//Stuff for Labels
				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
				FreeTypeFontParameter parameter = new FreeTypeFontParameter();
				parameter.size = 32;

				BitmapFont font = generator.generateFont(parameter); 
				generator.dispose();
				style = new LabelStyle();
				style.font = font;

				//end

				quantity =100;

				quantityLabel= new Label(null,style);
				quantityLabel.setX(posx+ 145);
				quantityLabel.setY(posy +90);
				quantityLabel.setColor(0,0,0,1);
				quantityLabel.setText("100");

				costLabel= new Label(null,style);
				costLabel.setX(posx+ 160);
				costLabel.setY(posy +43);
				costLabel.setColor(0,0,0,1);
				//Cost is different is selling
				if(Game_Shop.actorManager.sell)
				{
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.electricSellPrice);
				}
				else
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.electricPrice);


				actors.add(quantityLabel);
				actors.add(costLabel);
			}

			public static void changeQuantity(int change){
				int newQuantity = strToInt(Game_Shop.actorManager.electricityitem.quantityLabel.getText());
				newQuantity+=change;
				if(Game_Shop.actorManager.sell)
				{
					Game_Shop.actorManager.electricityitem.costLabel.setText(""+(Math.ceil(newQuantity*Shop.electricSellPrice)));
				}
				else
				Game_Shop.actorManager.electricityitem.costLabel.setText(""+(newQuantity*Shop.electricPrice));

				String l = new Integer(newQuantity).toString();
				Game_Shop.actorManager.electricityitem.quantityLabel.setText(l);
			}


			public ArrayList<Actor> getActors() {
				return this.actors;
			}
		}

		//electric Train
		public static class Game_shop_electric_train {
			ArrayList<Actor> actors ;
			public Label quantityLabel;
			public Label costLabel;
			public static Label goldLabel;
			public int quantity, cost;
			public static int posx=300;
			public static int posy=470;
			public static LabelStyle style;
			SpriteButton buyButton, minus, add;

			public Game_shop_electric_train(){
				this.actors = new ArrayList<Actor>();
				Sprite electrictrain = new Sprite(posx,posy,Game_TextureManager.getInstance().game_shop_electricitytrain);
				add = new SpriteButton(posx+75,posy+42,Game_TextureManager.getInstance().game_shop_addbtn){
					@Override
					protected void onClicked(){
						quantity+=1;
						changeQuantity(1);
					}

				};
				minus = new SpriteButton(posx+220,posy+48,Game_TextureManager.getInstance().game_shop_minusbtn){
					@Override
					protected void onClicked(){
						if(quantity!=0){
							quantity-=1;
							changeQuantity(-1);
						}
					}

				};

				buyButton = new SpriteButton(posx+75,posy+20,Game_TextureManager.getInstance().game_shop_buybtn){
					@Override
					protected void onClicked(){
						if (Game_Shop.actorManager.buy){
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().buyTrain("Electric", quantity, false);;									
						}
						if (Game_Shop.actorManager.sell){						
							int quantity = strToInt(quantityLabel.getText());
							//no sell train implemented
						}
						GameScreenUI.refreshResources();
						Game_ShopManager.refreshgold(GameScreen.game.getPlayerTurn().getGold());
					}
				};

				actors.add(electrictrain);
				actors.add(buyButton);
				actors.add(add);
				actors.add(minus);
					
				//Stuff for Labels
				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
				FreeTypeFontParameter parameter = new FreeTypeFontParameter();
				parameter.size = 32;

				BitmapFont font = generator.generateFont(parameter); 
				generator.dispose();
				style = new LabelStyle();
				style.font = font;
				//end

				quantity =100;

				quantityLabel= new Label(null,style);
				quantityLabel.setX(posx+ 145);
				quantityLabel.setY(posy +90);
				quantityLabel.setColor(0,0,0,1);
				quantityLabel.setText("100");

				costLabel= new Label(null,style);
				costLabel.setX(posx+ 160);
				costLabel.setY(posy +43);
				costLabel.setColor(0,0,0,1);
				//Cost is different is selling
				if(Game_Shop.actorManager.sell)
				{
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.electricTrainSellPrice);
				}
				else
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.electricTrainPrice);
					actors.add(quantityLabel);
				actors.add(costLabel);
			}

			public static void changeQuantity(int change){
				int newQuantity = strToInt(Game_Shop.actorManager.electricityitem.quantityLabel.getText());
				newQuantity+=change;
				if (Game_Shop.actorManager.sell)
				{
					Game_Shop.actorManager.electricityitem.costLabel.setText(""+(newQuantity*Shop.electricTrainSellPrice));
				}
				else
					Game_Shop.actorManager.electricityitem.costLabel.setText(""+(newQuantity*Shop.electricTrainPrice));
					String l = new Integer(newQuantity).toString();
				Game_Shop.actorManager.electricityitem.quantityLabel.setText(l);
			}

			public ArrayList<Actor> getActors() {
				return this.actors;
			}
		}

		//Nuclear
		public static class Game_shop_nuclear {
			ArrayList<Actor> actors ;
			public Label quantityLabel;
			public Label costLabel;
			public static Label goldLabel;
			public int quantity, cost;
			public static int posx=300;
			public static int posy=100;
			public static LabelStyle style;
			SpriteButton buyButton, minus, add;

			public Game_shop_nuclear(){
				this.actors = new ArrayList<Actor>();
				Sprite nuclearitem = new Sprite(posx,posy,Game_TextureManager.getInstance().game_shop_nuclearitem);
				add = new SpriteButton(posx+75,posy+42,Game_TextureManager.getInstance().game_shop_addbtn){
					@Override
					protected void onClicked(){
						quantity+=100;
						changeQuantity(100);

					}

				};
				minus = new SpriteButton(posx+220,posy+48,Game_TextureManager.getInstance().game_shop_minusbtn){
					@Override
					protected void onClicked(){
						if(quantity!=0){
							quantity-=100;
							changeQuantity(-100);}

					}

				};

				buyButton = new SpriteButton(posx+75,posy+20,Game_TextureManager.getInstance().game_shop_buybtn){
					@Override
					protected void onClicked(){
						if (Game_Shop.actorManager.buy){
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().buyFuel("Nuclear",quantity, false );									
						}
						if (Game_Shop.actorManager.sell){						
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().sellFuel("Nuclear", quantity, false);
						}
						GameScreenUI.refreshResources();
						Game_ShopManager.refreshgold(GameScreen.game.getPlayerTurn().getGold());
					}

				};

				actors.add(nuclearitem);
				actors.add(buyButton);
				actors.add(add);
				actors.add(minus);

				//Stuff for Labels
				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
				FreeTypeFontParameter parameter = new FreeTypeFontParameter();
				parameter.size = 32;

				BitmapFont font = generator.generateFont(parameter); 
				generator.dispose();
				style = new LabelStyle();
				style.font = font;

				//end

				quantity =100;

				quantityLabel= new Label(null,style);
				quantityLabel.setX(posx+ 145);
				quantityLabel.setY(posy +90);
				quantityLabel.setColor(0,0,0,1);
				quantityLabel.setText("100");

				costLabel= new Label(null,style);
				costLabel.setX(posx+ 160);
				costLabel.setY(posy +43);
				costLabel.setColor(0,0,0,1);
				//Cost is different is selling
				if(Game_Shop.actorManager.sell)
				{
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.nuclearSellPrice);
				}
				else
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.nuclearPrice);


				actors.add(quantityLabel);
				actors.add(costLabel);
			}

			public static void changeQuantity(int change){
				int newQuantity = strToInt(Game_Shop.actorManager.nuclearitem.quantityLabel.getText());
				newQuantity+=change;
				if(Game_Shop.actorManager.sell)
				{
					Game_Shop.actorManager.nuclearitem.costLabel.setText(""+(newQuantity*Shop.nuclearSellPrice));
				}
				else
				Game_Shop.actorManager.nuclearitem.costLabel.setText(""+(newQuantity*Shop.nuclearPrice));

				String l = new Integer(newQuantity).toString();
				Game_Shop.actorManager.nuclearitem.quantityLabel.setText(l);
			}


			public ArrayList<Actor> getActors() {
				return this.actors;
			}
		}
		
		//nuclear Train
		public static class Game_shop_nuclear_train {
			ArrayList<Actor> actors ;
			public Label quantityLabel;
			public Label costLabel;
			public static Label goldLabel;
			public int quantity, cost;
			public static int posx=300;
			public static int posy=470;
			public static LabelStyle style;
			SpriteButton buyButton, minus, add;

			public Game_shop_nuclear_train(){
				this.actors = new ArrayList<Actor>();
				Sprite nucleartrain = new Sprite(posx,posy,Game_TextureManager.getInstance().game_shop_nucleartrain);
				add = new SpriteButton(posx+75,posy+42,Game_TextureManager.getInstance().game_shop_addbtn){
					@Override
					protected void onClicked(){
						quantity+=1;
						changeQuantity(1);

					}

				};
				minus = new SpriteButton(posx+220,posy+48,Game_TextureManager.getInstance().game_shop_minusbtn){
					@Override
					protected void onClicked(){
						if(quantity!=0){
							quantity-=1;
							changeQuantity(-1);
						}
					}

				};

				buyButton = new SpriteButton(posx+75,posy+20,Game_TextureManager.getInstance().game_shop_buybtn){
					@Override
					protected void onClicked(){
						if (Game_Shop.actorManager.buy){
							int quantity = strToInt(quantityLabel.getText());
							GameScreen.game.getPlayerTurn().getShop().buyTrain("Nuclear", quantity, false);;									
						}
						if (Game_Shop.actorManager.sell){						
							int quantity = strToInt(quantityLabel.getText());
							//no sell train implemented
						}
						GameScreenUI.refreshResources();
						Game_ShopManager.refreshgold(GameScreen.game.getPlayerTurn().getGold());
					}

				};

				actors.add(nucleartrain);
				actors.add(buyButton);
				actors.add(add);
				actors.add(minus);
							
				//Stuff for Labels
				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
				FreeTypeFontParameter parameter = new FreeTypeFontParameter();
				parameter.size = 32;

				BitmapFont font = generator.generateFont(parameter); 
				generator.dispose();
				style = new LabelStyle();
				style.font = font;

				//end

				quantity =100;

				quantityLabel= new Label(null,style);
				quantityLabel.setX(posx+ 145);
				quantityLabel.setY(posy +90);
				quantityLabel.setColor(0,0,0,1);
				quantityLabel.setText("100");

				costLabel= new Label(null,style);
				costLabel.setX(posx+ 160);
				costLabel.setY(posy +43);
				costLabel.setColor(0,0,0,1);
				//Cost is different is selling
				if(Game_Shop.actorManager.sell)
				{
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.nuclearTrainSellPrice);
				}
				else
					costLabel.setText(""+strToInt(quantityLabel.getText())*Shop.nuclearTrainPrice);

				actors.add(quantityLabel);
				actors.add(costLabel);
			}

			public static void changeQuantity(int change){
				int newQuantity = strToInt(Game_Shop.actorManager.nuclearitem.quantityLabel.getText());
				newQuantity+=change;
				if (Game_Shop.actorManager.sell)
				{
					Game_Shop.actorManager.nuclearitem.costLabel.setText(""+(newQuantity*Shop.nuclearTrainSellPrice));
				}
				else
					Game_Shop.actorManager.nuclearitem.costLabel.setText(""+(newQuantity*Shop.nuclearTrainPrice));
					String l = new Integer(newQuantity).toString();
				Game_Shop.actorManager.nuclearitem.quantityLabel.setText(l);
			}

			public ArrayList<Actor> getActors() {
				return this.actors;
			}
		}

		//Train
		public static class Game_shop_train {
			ArrayList<Actor> actors ;
			public static Label quantityLabel;
			public Label costLabel;
			public static Label goldLabel;
			public int quantity, cost, posx=1100, posy=100;
			public static LabelStyle style;
			SpriteButton buyButton;
			public Game_shop_train(){
				this.actors = new ArrayList<Actor>();
				Sprite trainItem = new Sprite(posx,posy,Game_TextureManager.getInstance().game_shop_trainitem);
				buyButton = new SpriteButton(posx+75,posy+20,Game_TextureManager.getInstance().game_shop_blankbuybtn);
				actors.add(trainItem);
				actors.add(buyButton);

				//Stuff for Labels
				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
				FreeTypeFontParameter parameter = new FreeTypeFontParameter();
				parameter.size = 32;

				BitmapFont font = generator.generateFont(parameter); 
				generator.dispose();
				style = new LabelStyle();
				style.font = font;

				//end

				quantity =100;



				costLabel= new Label(null,style);
				costLabel.setX(posx+ 100);
				costLabel.setY(posy +43);
				costLabel.setColor(0,0,0,1);
				costLabel.setText("Buy Trains");

				actors.add(costLabel);


			}
			public ArrayList<Actor> getActors() {
				return this.actors;
			}
		}
	}
}

