package classes;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Paleta_direita {	
	public int posX;
	public int posY;
	public int largura;
	public int altura;
	public int velY;
	
	public Paleta_direita() {
		largura = 6;
		altura = 26;
		posX = (int)(Principal.LARGURA_TELA * 0.9) - (largura/2);
		posY = (Principal.ALTURA_TELA/2) - (altura/2);
		velY = 0;
	}
}
