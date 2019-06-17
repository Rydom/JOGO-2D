package classes;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Personagem {	
	public BufferedImage img;  
	public double posX;
	public double posY;
	public double altura;
	public double raio;
	public double velX;
	public double velY;
	public double aceY;
	public boolean estado_pulando;
	
	public Personagem() {
		try {
			img = ImageIO.read(getClass().getResource("../imgs/viking-menor.png"));
		}catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!");
		}
		altura = 100;
		raio = 50;
		posX = 50;
		posY = 353;
		velX = 0;
		velY = 0;
		aceY=0;
		estado_pulando = false;
	}
	
	public void pular(long deltaTime) {
		posY = posY + velY * (deltaTime / 10000000);
		velY = velY + aceY * (deltaTime / 10000000);
	}
	
	public void iniciarPulo() {
		estado_pulando = true;
		velY = -7 ;
		aceY = 0.3;
	}
}
