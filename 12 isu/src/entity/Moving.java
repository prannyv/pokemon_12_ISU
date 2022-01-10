// Moving animates the player and makes it move.

package entity;

import map.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.Driver2;

public class Moving {
	
	private static Player main; // the player
	private Camera camera; // the camera following the player
	private BufferedImage[] playerSprites; // the player sprites
	private int spriteCounter; // used to switch sprites
	Rectangle test = new Rectangle(128, 150, 150, 115);
	Rectangle test2 = new Rectangle(452, 410, 205, 105);
	public static int moving; // whether or not the player is moving, 0 - not moving, 1 - first moving sprite
	// 2 - second moving sprite
	public static boolean stopped;

	// Constructor
	public Moving(Player player, Camera camera)
	{
		main = player;
		this.camera = camera;
		playerSprites = main.getSprites();
		moving = 0;
		spriteCounter = 0;
		stopped = false;
	}
	
	void checkCollision(Rectangle collision, boolean cameraXOn, boolean cameraYOn)
	{
		Rectangle player = new Rectangle(camera.getX() + main.getScreenX(), camera.getY() + main.getScreenY(), Player.size, Player.size);
		if(player.intersects(collision))
		{
			double left1 = player.getX(); // player
			double right1 = player.getX() + player.getWidth();
			double top1 = player.getY();
			double bottom1 = player.getY() + player.getHeight();
			double left2 = collision.getX(); // collision
			double right2 = collision.getX() + collision.getWidth();
			double top2 = collision.getY();
			double bottom2 = collision.getY() + collision.getHeight();
			
			if(right1 > left2 && left1 < left2 && right1 - left2 < bottom1 - top2 && right1 - left2 < bottom2 - top1)
	        {
	            //rect collides from left side of the wall
				if(cameraXOn)
				{
					camera.setX((int) (camera.getX() - (right1-left2)));
				}
				else
				{
					main.setScreenX(collision.x-camera.getX() - Player.size);
				}
	        }
	        else if(left1 < right2 && right1 > right2 && right2 - left1 < bottom1 - top2 && right2 - left1 < bottom2 - top1)
	        {
	            //rect collides from right side of the wall
	        	if(cameraXOn)
				{
					camera.setX((int) (camera.getX() + right2-left1));
				}
				else
				{
					main.setScreenX(collision.x-camera.getX() + collision.width);
				}
	        }
	        else if(bottom1 > top2 && top1 < top2)
	        {

	        	if(cameraYOn)
				{
					camera.setY((int) (camera.getY() - (bottom1-top2)));
				}
				else
				{
					main.setScreenY(collision.y-camera.getY() - Player.size);
				}
	            //rect collides from top side of the wall
	        	// rect.y = wall.y - rect.height;
	        }
	        else if(top1 < bottom2 && bottom1 > bottom2)
	        {
	        	if(cameraYOn)
				{
					camera.setY((int) (camera.getY() + (bottom2-top1)));
				}
				else
				{
					main.setScreenY(collision.y-camera.getY() + collision.height);
				}
	            //rect collides from bottom side of the wall
	        	// rect.y = wall.y + wall.height;
	        }
		}
	}
	
	public void changeSprite()
	{
		spriteCounter += 1;
		if(spriteCounter > 12)
		{
			if(moving == 1)
			{
				moving = 2;
				spriteCounter = 0;
			} 
			else if (moving == 2)
			{
				moving = 1;
				spriteCounter = 0;
			}
		}
	}
	
	public void movePlayerX()
	{
		if(moving > 0)
		{
			if(main.direction.equals("left"))
			{
				main.setScreenX(main.getScreenX() - main.speed);
			}
			else if(main.direction.equals("right"))
			{
				main.setScreenX(main.getScreenX() + main.speed);
			}
			else
				return;
			
		}
		else
		{
			spriteCounter = 0;
		}
		
		if(main.getScreenX() < 0)
			main.setScreenX(0);
		else if(main.getScreenX() > Driver2.screenWidth - Player.size)
			main.setScreenX(Driver2.screenWidth - Player.size);
		
		checkCollision(test, !camera.getEdgeReachedY(), !camera.getEdgeReachedY());
		checkCollision(test2, !camera.getEdgeReachedX(), !camera.getEdgeReachedY());
		// Check if player is in center
		if(camera.getX() == 0)
		{
			if(main.getScreenX() > Driver2.screenWidth/2)
			{
				camera.setEdgeReachedX(false);
			}
		}
		else if(camera.getX() == camera.getMaxX()-Driver2.screenWidth)
		{
			if(main.getScreenX() < Driver2.screenWidth/2)
			{
				camera.setEdgeReachedX(false);
			}
		}
	}
	
	public void movePlayerY()
	{
		if(moving > 0)
		{
			if(main.direction.equals("up"))
			{
				main.setScreenY(main.getScreenY() - main.speed);
			}
			else if(main.direction.equals("down"))
			{
				main.setScreenY(main.getScreenY() + main.speed);
				
			}
			else
				return;
		}
		else
		{
			spriteCounter = 0;
			return;
		}
		
		if(main.getScreenY() < 0)
			main.setScreenY(0);
		else if(main.getScreenY() > Driver2.screenHeight - Player.size)
			main.setScreenY(Driver2.screenHeight - Player.size);
		
		checkCollision(test, !camera.getEdgeReachedX(), !camera.getEdgeReachedY());
		checkCollision(test2, !camera.getEdgeReachedX(), !camera.getEdgeReachedY());
		
		// Check if player is in center
		if(camera.getY() == 0)
		{
			if(main.getScreenY() > Driver2.screenHeight/2)
			{
				camera.setEdgeReachedY(false);
			}
		}
		else if(camera.getY() == camera.getMaxY()-Driver2.screenHeight)
		{
			if(main.getScreenY() < Driver2.screenHeight/2)
			{
				camera.setEdgeReachedY(false);
			}
		}
	
	}
	
	public void moveCameraX() 
	{
		if(moving > 0)
		{
			if(main.direction.equals("left"))
			{
				camera.setX(camera.getX() - main.speed);
			}
			else if(main.direction.equals("right"))
			{
				camera.setX(camera.getX() + main.speed);
			}	
		}
		else
		{
			spriteCounter = 0;
		}	
		
		checkCollision(test2, !camera.getEdgeReachedX(), !camera.getEdgeReachedY());
	}
	
	public void moveCameraY()
	{
		if(moving > 0)
		{
			if(main.direction.equals("up"))
			{
				camera.setY(camera.getY() - main.speed);
			}
			else if(main.direction.equals("down"))
			{
				camera.setY(camera.getY() + main.speed);
			}
		}
		else
		{
			spriteCounter = 0;
		}
		checkCollision(test2, !camera.getEdgeReachedX(), !camera.getEdgeReachedY());
	}
	
	public void draw(Graphics2D g2)
	{
		if(moving == 1)
		{
			if(main.direction.equals("up"))
				g2.drawImage(playerSprites[4], main.screenX, main.screenY, Player.size, Player.size, null);
			else if(main.direction.equals("down"))
				g2.drawImage(playerSprites[1], main.screenX, main.screenY, Player.size, Player.size, null);
			else if(main.direction.equals("left"))
				g2.drawImage(playerSprites[10], main.screenX, main.screenY, Player.size, Player.size, null);
			else if(main.direction.equals("right"))
				g2.drawImage(playerSprites[7], main.screenX, main.screenY, Player.size, Player.size, null);
		}
		else if(moving == 2)
		{
			if(main.direction.equals("up"))
				g2.drawImage(playerSprites[5], main.screenX, main.screenY, Player.size, Player.size, null);
			else if(main.direction.equals("down"))
				g2.drawImage(playerSprites[2], main.screenX, main.screenY, Player.size, Player.size, null);
			else if(main.direction.equals("left"))
				g2.drawImage(playerSprites[11], main.screenX, main.screenY, Player.size, Player.size, null);
			else if(main.direction.equals("right"))
				g2.drawImage(playerSprites[8], main.screenX, main.screenY, Player.size, Player.size, null);
		}
		else if(moving == 0)
		{
			if(main.direction.equals("up"))
				g2.drawImage(playerSprites[3], main.screenX, main.screenY, Player.size, Player.size, null);
			else if(main.direction.equals("down"))
				g2.drawImage(playerSprites[0], main.screenX, main.screenY, Player.size, Player.size, null);
			else if(main.direction.equals("left"))
				g2.drawImage(playerSprites[9], main.screenX, main.screenY, Player.size, Player.size, null);
			else if(main.direction.equals("right"))
				g2.drawImage(playerSprites[6], main.screenX, main.screenY, Player.size, Player.size, null);
		}
	}
}