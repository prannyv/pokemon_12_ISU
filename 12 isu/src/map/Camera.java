package map;
import java.awt.*;
import java.awt.image.BufferedImage;

import entity.Moving;
import main.Driver2;

public class Camera {
	
	private int worldX;
	private int worldY;
	private int maxX;
	private int maxY;
	private BufferedImage location;
	private boolean edgeReachedX; 
	private boolean edgeReachedY; 
	
	private final int screenWidth = Driver2.screenWidth;
	private final int screenHeight = Driver2.screenHeight;
	
	public Camera(int worldX, int worldY, BufferedImage starting, boolean edgeReachedX, boolean edgeReachedY)
	{
		this.worldX = worldX;
		this.worldY = worldY;
		location = starting;
		maxX = location.getWidth();
		maxY = location.getHeight();
		this.edgeReachedX = edgeReachedX;
		this.edgeReachedY = edgeReachedY;
	}
	
	// Drawing the terrain
	public void draw(Graphics2D g2)
	{
		if(worldX < 0)
		{
			edgeReachedX = true;
			worldX = 0;
		}
		else if (worldX > maxX-screenWidth)
		{
			edgeReachedX = true;
			worldX = maxX-screenWidth;
		}
		
		if(worldY < 0)
		{
			edgeReachedY = true;
			worldY = 0;
		}
		else if (worldY > maxY-screenHeight)
		{
			edgeReachedY = true;
			worldY = maxY-screenHeight;
		}
		
		g2.drawImage(location, 0, 0, screenWidth, screenHeight, worldX, worldY, worldX+screenWidth, worldY+screenHeight, null);
	}
	
	// Getters and Setters
	public int getX()
	{
		return worldX;
	}
	public int getY()
	{
		return worldY;
	}	
	public int getMaxX()
	{
		return maxX;
	}
	public int getMaxY()
	{
		return maxY;
	}
	public boolean getEdgeReachedX()
	{
		return edgeReachedX;
	}
	public boolean getEdgeReachedY()
	{
		return edgeReachedY;
	}
	
	public void setX(int X)
	{
		worldX = X;
	}
	public void setY(int Y)
	{
		worldY = Y;
	}
	public void setEdgeReachedX(boolean edge)
	{
		edgeReachedX = edge;
	}
	public void setEdgeReachedY(boolean edge)
	{
		edgeReachedY = edge;
	}

}
