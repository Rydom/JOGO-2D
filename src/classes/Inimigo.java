package classes;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Inimigo {
	public BufferedImage img;
	public double posX;
	public double posY;
	public double raio;
	public double altura;
	public double largura;
	public double centroX;
	public double centroY;
	public double velX;
	
	public Inimigo(double posicao) {
		try {
			img = ImageIO.read(getClass().getResource("../imgs/inimigo-menor.png"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		raio = 27.5;
                altura = 63;
                largura = 55;
		posX = posicao;
		posY = 393;
                velX = -3.5;
                //velX = -1;
		centroX = posX + raio;
		centroY = posY + raio;
	}
        public void move(){
            //this.posX = this.posX - 10;
            if(posX < raio * -1){
                posX = 640;
            }
            this.posX = this.posX + velX;
        }
}