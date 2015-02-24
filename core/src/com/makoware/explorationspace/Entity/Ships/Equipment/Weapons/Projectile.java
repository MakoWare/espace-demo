package com.makoware.explorationspace.Entity.Ships.Equipment.Weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.makoware.explorationspace.ESModel;
import com.makoware.explorationspace.Entity.Entity;
import com.makoware.explorationspace.Entity.Player;

public abstract class Projectile extends Entity{
	private static String tag = "Projectile";
	
	protected float damage;
	protected float velocity;
	protected float dstToLive;
	protected float width;
	protected float height;
	
	protected Vector2 startPoint = new Vector2();

	private boolean exploded = false;

	private Player owner;

	private int playerId;
	
	public void setOwner(Player owner){
		this.owner = owner;
	}
	
	public void fly(float delta) {
//		Gdx.app.log(tag, "fly: "+startPoint.toString()+" to: "+body.getPosition().cpy().dst(startPoint));
		if(body.getPosition().dst(startPoint)>dstToLive){
//			removeFromWorld();
			explode();
		}
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		sprite.setPosition(body.getPosition().x, body.getPosition().y);
		sprite.draw(batch);
	}

	public void fire(int id, Vector2 origin, Vector2 direction) {
		startPoint = origin;
		tag(id);
		sprite.setBounds(startPoint.x, startPoint.y, width, height);
		sprite.setOrigin(width/2, height/2);
		sprite.setRotation(direction.angle());
		addToWorld(ESModel.world);
		body.setLinearVelocity(direction.scl(velocity));

	}
	
	@Override
	public void addToWorld(World world) {
		
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
		def.position.set(sprite.getX()-width/2f, sprite.getY()-height/2f);
		def.fixedRotation = true;
		def.bullet = true;
		
		this.body = world.createBody(def);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth()/2f,sprite.getHeight()/2f);
//		shape.set(new float[]{0f,0.05f,0.025f,0.025f,0.225f,0.025f,0.3f,0.05f,0.3f,0.7f,0.25f,0.75f,0.05f,0.75f,0f,0.7f});
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.0f;
		fdef.restitution = 0.0f;
		fdef.density = 1f;		
		if(playerId<0){
			fdef.filter.categoryBits = ESModel.ENEMY_WEAPONS;
			fdef.filter.maskBits = (ESModel.PLAYER_WEAPONS | ESModel.PLAYER);
		} else {
			fdef.filter.categoryBits = ESModel.PLAYER_WEAPONS;
			fdef.filter.maskBits = (ESModel.ENEMY_WEAPONS | ESModel.ENEMY);
		}
		body.createFixture(fdef);
		body.setLinearDamping(0f);
		body.setUserData(this);
		shape.dispose();
	}
	
	@Override
	public void removeFromWorld() {
//		Gdx.app.log(tag, "remove");
		ESModel.world.destroyBody(body);
//		body = null;
	}
	
	@Override
	public void dispose() {
//		removeFromWorld();
	}
	
	
	
	public float getDamage(){
		return damage;
	}
	
	public void explode(){
		this.exploded  = true;
		// other stuff
	}

	public boolean exploded() {
		return this.exploded;
	}

	public Player getOwner() {
		return this.owner;
	}

	public void tag(int id) {
		this.playerId = id;
	}
	
	public int getId(){
		return this.playerId;
	}

}
