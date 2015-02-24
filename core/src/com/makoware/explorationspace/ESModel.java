package com.makoware.explorationspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.makoware.explorationspace.Entity.Asteroid;
import com.makoware.explorationspace.Entity.BackgroundStar;
import com.makoware.explorationspace.Entity.Entity;
import com.makoware.explorationspace.Entity.Player;
import com.makoware.explorationspace.Entity.Powerup.Credit;
import com.makoware.explorationspace.Entity.Powerup.MissileLauncher;
import com.makoware.explorationspace.Entity.Powerup.Powerup;
import com.makoware.explorationspace.Entity.Ships.AlienShip;
import com.makoware.explorationspace.Entity.Ships.Equipment.Weapons.Projectile;
import com.makoware.explorationspace.Entity.Ships.HumanShip;
import com.makoware.explorationspace.Entity.Ships.Ship;
import com.makoware.explorationspace.Entity.SpaceObject;
import com.makoware.explorationspace.Framework.Callback;
import com.makoware.explorationspace.Framework.Message;
import com.makoware.explorationspace.GUI.HUD;
import com.makoware.explorationspace.GUI.MCamera;
import com.makoware.explorationspace.GUI.OverlayParam;
import com.makoware.explorationspace.Generators.BackgroundStarGenerator;
import com.makoware.explorationspace.Generators.ObjectGenerator;

import java.util.ArrayList;

public class ESModel implements ContactListener{
	private static String tag = "ESModel";
	
	private Player player;
	private ArrayList<Player> players;
	
	public final static short PLAYER = 0x0001;
	public final static short PLAYER_WEAPONS = 0x0002;
	public final static short ENEMY = 0x0004;
	public final static short ENEMY_WEAPONS = 0x0008;
	public static final short POWERUP = 0x000F;
	public static final short WALL = 0x0010;
	
	public static World world;
	
	// background star generator
	private BackgroundStarGenerator starGen;
	
	// camera for needing to know stuff
	private MCamera cam;
	private HUD hud;
	private OverlayParam hudParams = new OverlayParam();
	
	// entity arrays
	private ArrayList<Entity> ents;
	private ArrayList<BackgroundStar> backgroundStars;

	private float zoomFactor = 0.01f;
	private int zoomDir;

	private Wall top;
	private Wall bottom;
	private Wall left;
	
	private boolean paused = false;
	
	private ObjectGenerator oGen;


	private Callback pause = new Callback(){public void call(Message m) {pause();Gdx.app.log(tag, "pause");}};
	private Callback unpause = new Callback(){public void call(Message m) {unpause();Gdx.app.log(tag, "unpause");}};

	private ArrayList<Callback> onPauseCallbacks = new ArrayList<Callback>();
	

	public ESModel(){
		
		this.cam = new MCamera();
		this.hud = new HUD(cam);
		this.hud.setPause(pause);
		this.hud.setUnpause(unpause);
		
		this.ents = new ArrayList<Entity>();
		this.backgroundStars = new ArrayList<BackgroundStar>();
		
		this.starGen = new BackgroundStarGenerator(backgroundStars);		
		
		world = new World(new Vector2(0,0), true);
		world.setContactListener(this);
		
		
		// only temporary
		players = new ArrayList<Player>();
		players.add(new Player(1, new HumanShip(5,25)));
		player = players.get(0);
		player.getShip().addToWorld(world);
		addOnPauseCallback(player.onPauseCallback());
		
		this.cam.setChaseEnable(true);
		this.cam.setChaseEntity(player.getShip());
		
		AlienShip alien = new AlienShip(-1,100,10);
		alien.addToWorld(world);
		alien.assignTarget(player);
		alien.setFireRate(0.5f);
		alien.setFireRange(25);
		ents.add(alien);
		
		ents.add(new Asteroid(50,5));
		ents.get(ents.size()-1).addToWorld(world);
		
		Powerup p = new MissileLauncher(5,5);
		p.addToWorld(world);
		ents.add(p);
		
		Powerup p1 = new MissileLauncher(5,8);
		p1.addToWorld(world);
		ents.add(p1);
		
		Powerup c = new Credit(10,3);
		c.addToWorld(world);
		ents.add(c);
		
		this.oGen = new ObjectGenerator(ents, player);
		
		this.hudParams.put("player", player);
		
	}
	
	public MCamera getCamera(){
		return this.cam;
	}
	
	public void resize(int width, int height) {
		float w = width;
		float h = height;
		float d = 50f;
		if(width <= height)
			cam.setToOrtho(false, d, d*(h/w));
		else 
			cam.setToOrtho(false, d*(w/h), d);
		
//		fontMatrix.setToOrtho2D(0, 0, width, height);
		this.cam.setMarioStyle(true);
		cam.calculateParallaxMatrix(0.2f, 0.2f);
		cam.onUpdate();
		hud.resize();
		// do it this way so that the box which tells the stars to jump to the other side are independent of updated screen size
		if(!this.starGen.initialized()){
			this.starGen.initialize(new Rectangle((cam.position.x)*0.2f, (cam.position.y)*0.2f, cam.viewportWidth, cam.viewportHeight), cam.getCenterVector2());
			this.top = new Wall(cam.position.x,cam.position.y+((cam.viewportHeight+0.4f)/2f),cam.viewportWidth, 0.4f);
			this.top.addToWorld(world);
			this.bottom = new Wall(cam.position.x,cam.position.y-((cam.viewportHeight+0.4f)/2f),cam.viewportWidth, 0.4f);
			this.bottom.addToWorld(world);
			this.left = new Wall(cam.position.x-(cam.viewportWidth+0.4f)/2f,cam.position.y,0.4f, cam.viewportHeight);
			this.left.addToWorld(world);
			
			ents.add(top);
			ents.add(bottom);
			ents.add(left);
		}
	}

	public void update(float delta) {
		zoom();
		if(!paused){
			this.player.update(delta);
//			this.oGen.update(delta);
			world.step(delta, 6, 2);
		}
		cam.onUpdate();
//		this.starGen.update(cam.getParalaxCenter(0.2f));
		starGen.update();
		this.top.setX(cam.position.x);
		this.bottom.setX(cam.position.x);
		this.left.setX(cam.position.x-(cam.viewportWidth+0.4f)/2f);
	}

	public ArrayList<Entity> getEnts() {
		return this.ents;
	}
	
	public ArrayList<BackgroundStar> getBackgroundStars() {
		return backgroundStars;
	}
	
//	public ArrayList<Planet> getPlanets() {
//		return this.planets;
//	}
	
	public Player getPlayer(){
		return this.player;
	}

	public World getWorld() {
		return world;
	}
	
	
	
	public void dispose() {
		world.dispose();
	}
	
	public void setZoomDir(int dir){
		this.zoomDir = dir;
	}
	
	public void zoom(){
		if(this.zoomDir!=0){
			float tmp = this.cam.zoom + (zoomDir*zoomFactor);
			if(tmp<1.8f && tmp>0.2f){
				this.cam.zoom = tmp;
//				Gdx.app.log(tag, "Zoom: "+this.cam.zoom);
			}
		}
	}

	public void zoomIn() {
		this.cam.zoom -= zoomFactor;
	}
	
	public void zoomOut(){
		this.cam.zoom += zoomFactor;
	}

	public HUD getHUD() {
		return this.hud;
	}
	
//	private OverlayParam unpause = new OverlayParam().put("unpause", new Callback(){
//
//		@Override
//		public void call(Message m) {
//			unpause();
//		}
//		
//	});	

	public void pause() {
		onPause();
		this.paused = true;
	}
	
	public void unpause(){
//		this.hud.hide("pause");
		this.paused = false;
	}
	
	public void onPause(){
		for(Callback cb : onPauseCallbacks)
			cb.call(null);
	}
	
	public void addOnPauseCallback(Callback cb){
		this.onPauseCallbacks.add(cb);
	}

	public OverlayParam getHudParams() {
		return hudParams;
	}
	
	public Player getById(int id){
		for(Player p : players){
//			Gdx.app.log(tag, "playerID: "+p.getId()+" id: "+id);
			if(p.getId()==id)
				return p;
		}
		return null;
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		final boolean damageToEnemy = (fa.getFilterData().categoryBits==PLAYER_WEAPONS || fa.getFilterData().categoryBits==ENEMY);
//		boolean damageToPlayer = (fa.getFilterData().categoryBits==ENEMY_WEAPONS || fa.getFilterData().categoryBits==PLAYER);
		final boolean grabPowerup = (fa.getFilterData().categoryBits==POWERUP || fb.getFilterData().categoryBits==POWERUP);
		final boolean playerShot = (fa.getFilterData().categoryBits==PLAYER_WEAPONS || fb.getFilterData().categoryBits==PLAYER_WEAPONS);
		final boolean enemyShot = (fa.getFilterData().categoryBits==ENEMY_WEAPONS || fb.getFilterData().categoryBits==ENEMY_WEAPONS);
		
//		if(damageToEnemy){			
//			Projectile p = (Projectile) (fa.getFilterData().categoryBits==PLAYER_WEAPONS ? ( fa.getBody().getUserData() ) : ( fb.getBody().getUserData() ));
//			Entity o = (Entity) (fa.getFilterData().categoryBits==PLAYER_WEAPONS   ? ( fb.getBody().getUserData() ) : ( fa.getBody().getUserData() ));
//			
//			if(o instanceof Projectile){
//				((Projectile)o).explode();
//				p.explode();
//			} else {
//				
//				((SpaceObject)o).damage(p);
//				if(o.isDead()){
////					Gdx.app.log(tag, "player: "+getById(p.getId())+" o: "+o.worth());
//					getById(p.getId()).scoreUp(((SpaceObject)o).worth());
//				}
//			}
//			
//			
//			
//		}
		
		if(playerShot){
			Projectile p = (Projectile) (fa.getFilterData().categoryBits==PLAYER_WEAPONS ? ( fa.getBody().getUserData() ) : ( fb.getBody().getUserData() ));
			Entity e = (Entity) (fa.getFilterData().categoryBits==PLAYER_WEAPONS   ? ( fb.getBody().getUserData() ) : ( fa.getBody().getUserData() ));
			
			if(e instanceof Projectile){ // players projectile hit enemy projectile
				((Projectile)e).explode();
				p.explode();
			} else if(e instanceof SpaceObject){
				((SpaceObject)e).damage(p);
				if(((SpaceObject)e).isDead()){
//					Gdx.app.log(tag, "player: "+getById(p.getId())+" o: "+o.worth());
					getById(p.getId()).scoreUp(((SpaceObject)e).worth());
				}
			}
		}
		if(enemyShot){
			Projectile p = (Projectile) (fa.getFilterData().categoryBits==ENEMY_WEAPONS ? ( fa.getBody().getUserData() ) : ( fb.getBody().getUserData() ));
			Entity e = (Entity) (fa.getFilterData().categoryBits==ENEMY_WEAPONS   ? ( fb.getBody().getUserData() ) : ( fa.getBody().getUserData() ));
			
			if(e instanceof SpaceObject){
				((SpaceObject)e).damage(p);
			}
		}
		
		if(grabPowerup){
			Powerup power = (Powerup) (fa.getFilterData().categoryBits==POWERUP ? ( fa.getBody().getUserData() ) : ( fb.getBody().getUserData() ) );
			Player player = getById(((Ship) (fa.getFilterData().categoryBits==POWERUP ? ( fb.getBody().getUserData() ) : ( fa.getBody().getUserData() ) )).getId());
			
			Gdx.app.log(tag, "apply Powerup");
			player.applyPowerup(power);
			
		}
		
		
		
		
		
//		if(damageToPlayer){
//			
//			
////			Projectile p = (Projectile) (fa.getFilterData().categoryBits==ENEMY_WEAPONS ? ( fa.getBody().getUserData() ) : ( fb.getBody().getUserData() ));
////			HumanShip a = (HumanShip) (fa.getFilterData().categoryBits==ENEMY_WEAPONS   ? ( fb.getBody().getUserData() ) : ( fa.getBody().getUserData() ));
////			
////			a.damage(p);
//		}
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
	
}
