package com.makoware.explorationspace.Generators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.makoware.explorationspace.Entity.BackgroundStar;

import java.util.ArrayList;
import java.util.Random;

public class BackgroundStarGenerator {
	private String tag = "StarGen";
	private ArrayList<BackgroundStar> stars;
	private Rectangle bounds;
	private boolean initialized;

	private boolean runOnce = true;
	private int count;
	
	
	//vars for update
	private float sx;
	private float sy;
	
	private float cx;// = position.x;
	private float cy;// = position.y;
	
	private float xMin;// = cx-(bounds.width/2f);
	private float xMax;// = cx+(bounds.width/2f);
	
	private float yMin;// = cy-(bounds.height/2f);
	private float yMax;// = cy+(bounds.height/2f);
	
	private Vector2 center;
	
	private BackgroundStar tmpStar;
	
	public BackgroundStarGenerator(ArrayList<BackgroundStar> stars){
		this.stars = stars;
		initialized = false;
		
	}
	
	public void initialize(Rectangle bounds, Vector2 center) {
		this.bounds = bounds;
		this.center = center;
		Random r = new Random();
		int dx = 4;
		int dy = 5;
		int ry;
		int rx;
		float size;
//		Gdx.app.log(tag, bounds.toString()+"; y("+(int) (bounds.y-(bounds.height/2))+":"+(int)(bounds.y+(bounds.height/2))+"); x("+(int)(bounds.x-(bounds.width/2))+":"+(int)(bounds.x+(bounds.width/2))+")");
		for(int y=(int) (bounds.y-(bounds.height/2)); y<bounds.y+(bounds.height/2)+dy; y+=dy){
			for(int x=(int)(bounds.x-(dx+bounds.width/2)); x<bounds.x+(bounds.width/2)+dx; x+=dx){
				ry = r.nextInt(dy)+y;
				rx = r.nextInt(dx)+x;
				size = r.nextInt(4)/10f;
				
				if(size>0){
					stars.add(new BackgroundStar(rx, ry, size));
//					Gdx.app.log(tag, "new star: ("+rx+","+ry+") "+size);
				}
			}
		}
		Gdx.app.log(tag, "total stars: "+stars.size());
		this.bounds.width += dx;
		this.bounds.height += dy;
		initialized = true;
	}
	
	
	public void update(float delta){
//		for(BackgroundStar s : stars){
//			s.getPosition()
//		}
	}

	public void update(){
		update(center);
	}
	
	public void update(Vector2 position) {
		cx = position.x;
		cy = position.y;
		
		xMin = cx-(bounds.width/2f);
		xMax = cx+(bounds.width/2f);
		
		yMin = cy-(bounds.height/2f);
		yMax = cy+(bounds.height/2f);
		
//		if(count<10){
//			Gdx.app.log(tag,"c("+cx+":"+cy);
//			Gdx.app.log(tag,"x("+xMin+":"+xMax+"); y("+yMin+":"+yMax+")");
//			
//			count++;
//		}
		
		
		for(int i=0;i<stars.size();i++){
			tmpStar = stars.get(i);
			sx = tmpStar.getX();
			sy = tmpStar.getY();
			
//			Gdx.app.log(tag, "para center: "+cam.getParalaxCenter(0.2f).toString());
//			Gdx.app.log(tag, "cam center: "+cam.position.toString());
//			Gdx.app.log(tag, "cam width: "+cam.viewportWidth);
			
//			Gdx.app.log(tag, "sx="+sx+" sy="+sy);
			
			
			
			if(sx<xMin || sx>xMax){
//				Gdx.app.log(tag, "need to fix X; sx="+sx+" sy="+sy);
				sx = (cx)-( sx-cx );
//				Gdx.app.log(tag, "\t after fix X; sx="+sx+" sy="+sy);
			}
			
			if(sy<yMin || sy>yMax){
//				Gdx.app.log(tag, "need to fix Y; sx="+sx+" sy="+sy);
				sy = (cy)-( sy-cy );
//				Gdx.app.log(tag, "\t after fix Y; sx="+sx+" sy="+sy);
			}
			
//			Gdx.app.log(tag, "\tsx="+sx+" sy="+sy);
			tmpStar.setPosition(sx,sy);
		}
	}

	public boolean initialized() {
		return this.initialized;
	}
}
