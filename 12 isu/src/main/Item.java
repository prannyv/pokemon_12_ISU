package main;

import java.awt.image.BufferedImage;

import getimages.LoadImage;

public class Item {
	
	String name;
	String effect;
	String category;
	//pokeball, medicine, key item, other item
	int cost;
	BufferedImage sprite;
	
	LoadImage loader = new LoadImage();
	//take image loading code from Pokemon class
	
	double catchChance;
	BufferedImage[] ballSheet;
	
	int healing;
	boolean removeStatus;
	int ppIncrease;
	boolean doesRevive;
	
	//poke ball
	public Item (String name, String effect, int cost, double catchChance) {
		//i dont know how to import the ball sheet lmao
		this.name = name;
		this.effect = effect;
		this.category = "PokeBall";
		this.cost = cost;
		this.catchChance = catchChance;
	}
	
	//healing item
	public Item(String name, String effect, int cost, int healing, boolean removeStatus, int ppIncrease, boolean doesRevive) {
		this.name = name;
		this.effect = effect;
		this.category = "Medicine";
		this.cost = cost;
		this.healing = healing;
		this.removeStatus = removeStatus;
		this.ppIncrease = ppIncrease;
		this.doesRevive = doesRevive;
	}
	
	//key items (cannot be sold btw)
	public Item(String name, String effect) {
		this.name = name;
		this.effect = effect;
		this.category = "Key Items";
	}

}

//items in game
/*
 * __PokeBalls__
 * Poke Ball
 * Great Ball
 * Ultra Ball
 * Master Ball
 * 
 * __Medicine__
 * Potion
 * Super Potion
 * Hyper Potion
 * Full Restore
 * Full Heal
 * Elixir
 * Revive
 * Max Revive
 * 
 * 
 * 
 * __Key Items__
 * Town Map
 * Badge Case
 * exp share
 * oak's letter
 * Fishing rod (not needed)
 * bike (not needed)
 * 
 * 
 */
