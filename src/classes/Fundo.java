package classes;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Fundo {	
	public BufferedImage img; 
	public double posX;
	public double posY;
	public double velX;
	public double velY;
        private Inimigo inimigo;
	
	public Fundo(String nome, double posX) {
		try {
			img = ImageIO.read(getClass().getResource(nome));
		}catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!");
		}
		this.posX = posX;
		posY = 0;
		velX = -2;
		velY = 0;              
                
	}
	
	public void move(long deltaTime) {
		posX = posX + velX * (deltaTime / 10000000);
		posY = posY + velY;               
                
	}
	
	public void checkPosition() {
		if(posX+640<=0) {
			this.posX = 1920;
		}
	}
}
