package lab2game2d;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Lab2game2d implements ActionListener, KeyListener
{

    public static Lab2game2d lab2game2d;

	public final int WIDTH = 700, HEIGHT = 1000;
	public Renderer renderer;
	public Rectangle player;
	public ArrayList<RectangleR> enemies;
	public int mv=15, score, maxspeed=20, minspeed=10, colwidth=35;
	public boolean gameOver, started;
	public Random rand;

	public Lab2game2d()
	{
		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);

		renderer = new Renderer();
		rand = new Random();

		jframe.add(renderer);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);

		player = new Rectangle(WIDTH / 2, HEIGHT / 2, 30, 60);
		enemies = new ArrayList<RectangleR>();

		addEnemy();
		addEnemy();
		addEnemy();
		addEnemy();

		timer.start();
	}

	public void addEnemy()
	{
		int width = 30;
		int height = 60;
                                    if(score>0 && score%40==0){
                                                          maxspeed+=1;
                                                          minspeed+=1;
                                                      }
		enemies.add(new RectangleR((rand.nextInt(20))*colwidth, 0, width, height,rand.nextInt(maxspeed - minspeed)+minspeed));
	}

	public void paintEnemy(Graphics g, RectangleR enemy)
	{
                        try{
                            BufferedImage image = ImageIO.read(new File("C:\\halo\\enemy.png"));
                                     g.drawImage(image, enemy.x, enemy.y, null);
                        }
                        catch(Exception e){
                            g.setColor(Color.green.darker());
		g.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
                        }
		
                
	}

	public void move(int i)
	{
		if (gameOver)
		{
			player = new Rectangle(WIDTH / 2, HEIGHT / 2, 30, 60);
			enemies.clear();
			score = 0;
                                                      maxspeed=20;
                                                      minspeed=10;
			addEnemy();
			addEnemy();
			addEnemy();
			addEnemy();
			gameOver = false;
		}

		if (!started)
		{
			started = true;
		}
		else if (!gameOver)
		{
                                            switch(i)
                                            {
                                                case 0:
                                                    player.y -= mv;
                                                    break;
                                                case 1:
                                                    player.y += mv;
                                                    break;
                                                case 2:
                                                    player.x += colwidth;
                                                    break;
                                                case 3:
                                                    player.x -= colwidth;
                                                    break;      
                                            }                 
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (started)
		{
			for (int i = 0; i < enemies.size(); i++)
			{
				RectangleR enemy = enemies.get(i);

				enemy.y += enemy.getSpeed();
			}
			for (int i = 0; i < enemies.size(); i++)
			{
				RectangleR enemy = enemies.get(i);

				if (enemy.y > 1000)
				{
					enemies.remove(enemy);
                                                                                          addEnemy();
				}
			}

			for (Rectangle enemy : enemies)
			{
				if (player.y + player.height / 2 > enemy.y + enemy.height / 2 - 10 && player.y + player.height / 2 < enemy.y + enemy.height / 2 + 10)
				{
					score++;
				}

				if (enemy.intersects(player))
				{
					gameOver = true;
                                                                                          //started = false;
				}
			}

			if (player.y+60 > HEIGHT|| player.y < 0 || player.x + 30 > WIDTH || player.x < 0)
			{
				gameOver = true;
			}
		}

		renderer.repaint();
	}

	public void repaint(Graphics g)
	{
            
                                    try{
                                         final BufferedImage image = ImageIO.read(new File("C:\\halo\\road.png"));
                                         g.drawImage(image, 0, 0, null);
                                        }
                                    catch(Exception e){
                                     }
                                     try{
                                         final BufferedImage image = ImageIO.read(new File("C:\\halo\\player.png"));
                                         g.drawImage(image, player.x, player.y, null);
                                        }
                                    catch(Exception e){
                                        g.setColor(Color.red);
                                        g.fillRect(player.x, player.y, player.width, player.height);
                                     }

		for (RectangleR enemy : enemies)
		{
			paintEnemy(g, enemy);
		}

		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 60));

		if (!started)
		{
			g.drawString("Press key to start!", 75, HEIGHT / 2 - 50);
		}

		if (gameOver)
		{
			g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
		}

		if (!gameOver && started)
		{
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
		}
	}

	public static void main(String[] args)
	{
		lab2game2d = new Lab2game2d();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
                   
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
                     if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			move(0);
		}
                     if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			move(1);
		}
                     if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			move(2);
		}
                     if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			move(3);
		}
	}
    
}
