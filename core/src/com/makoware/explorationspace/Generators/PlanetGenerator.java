package com.makoware.explorationspace.Generators;

import com.makoware.explorationspace.Entity.Planet;
import com.makoware.explorationspace.Entity.Planets.Earth;
import com.makoware.explorationspace.Entity.Planets.Jupiter;
import com.makoware.explorationspace.Entity.Planets.Mars;
import com.makoware.explorationspace.Entity.Planets.Mercury;
import com.makoware.explorationspace.Entity.Planets.Neptune;
import com.makoware.explorationspace.Entity.Planets.Satern;
import com.makoware.explorationspace.Entity.Planets.Uranus;
import com.makoware.explorationspace.Entity.Planets.Venus;

import java.util.ArrayList;

public class PlanetGenerator {

	private ArrayList<Planet> planets;
	
	private Planet mercury = new Mercury();
	private Planet venus = new Venus();
	private Planet earth = new Earth();
	private Planet mars = new Mars();
	private Planet jupiter = new Jupiter();
	private Planet satern = new Satern();
	private Planet uranus = new Uranus();
	private Planet neptune = new Neptune();
	
	
	public PlanetGenerator(ArrayList<Planet> planets){
		this.planets = planets;
	}
	
	public void initialize(){
		
//		planets.add(mercury);
//		planets.add(venus);
		planets.add(earth);
		planets.add(mars);
//		planets.add(jupiter);
//		planets.add(satern);
//		planets.add(uranus);
//		planets.add(neptune);
	}
	
	
	public Earth getEarth(){
		return (Earth) this.earth;
	}
}
