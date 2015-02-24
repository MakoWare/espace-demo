package com.makoware.explorationspace.GUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.makoware.explorationspace.Framework.Callback;

public class TextMenuItem extends MenuItem {
	
	protected Rectangle box = new Rectangle();
	protected String text;
	
	protected BitmapFont font;
	protected float fontScale;
	protected Color fontColor;
	
	protected Sprite sprite;
	
	protected float percent = 0.9f;
	
//	protected int index;
	
	public TextMenuItem(Rectangle box, String text, boolean selectable, Callback cb){
		this.box = box;
		this.text = text;
		this.cb = cb;
		this.fontColor = Color.WHITE;
		this.fontScale = 1.0f;
		this.selected = false;
		this.selectable = selectable;
		this.sprite = new Sprite(HUD.arrow);
		this.font = HUD.font;
	}

	@Override
	public void draw(SpriteBatch batch) {
		if(selectable && selected)
			this.sprite.draw(batch);

		TextBounds tb = font.getBounds(text);
		this.sprite.setBounds(box.x+(percent*tb.height), box.height-(tb.height* (1+percent) *index), tb.height, tb.height);
		font.setScale(fontScale);
		font.setColor(fontColor);
		font.draw(batch, text, sprite.getX()+(1.1f*sprite.getHeight()), sprite.getY()+sprite.getHeight());
		
	}
	

}
