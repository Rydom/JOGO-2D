package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel{
	private boolean k_pulo = false;
	private final double decay = 10000000;
        private int pontuacao = 0;
	Personagem person;
	Inimigo inimigo, inimigo2;
        Fundo bg01, bg02, bg03, bg04;
	public Game() {
		person = new Personagem();
		inimigo = new Inimigo(300);
                inimigo2 = new Inimigo(640);
                try {
			bg01 = new Fundo("../imgs/floresta.png",0);
			bg02 = new Fundo("../imgs/floresta2.png",640);
			bg03 = new Fundo("../imgs/floresta3.png",1280);
			bg04 = new Fundo("../imgs/cidade.png",1920);
		}catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!");
		}
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE: k_pulo=true; break;
				
				}
			}
		});
		setFocusable(true);
		setLayout(null);
		
		new Thread(new Runnable() { // instancia da Thread + classe interna anonima
			@Override
			public void run() {
				gameloop(); // inicia o gameloop
			}
		}).start(); // dispara a Thread
	}
	// GAMELOOP -------------------------------
	public void gameloop() {
		long tempoInicio=System.nanoTime();
		long tempoAnterior = tempoInicio;
		long deltaTime=0;
		while(true) { // repeticao intermitente do gameloop
			tempoInicio = System.nanoTime();
			deltaTime = tempoInicio - tempoAnterior;
			tempoAnterior = tempoInicio;
			handlerEvents();
			update(deltaTime);
			render();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void handlerEvents() {
		
	}
	public void update(long deltaTime) {
		bg01.checkPosition();
		bg02.checkPosition();
		bg03.checkPosition();
		bg04.checkPosition();
		bg01.move(deltaTime);
		bg02.move(deltaTime);
		bg03.move(deltaTime);
		bg04.move(deltaTime);                
                inimigo.move();
                inimigo2.move();
		person.posX = person.posX + person.velX;
		person.posY = person.posY + (person.velY * deltaTime / decay);		
		//testeColisoes(deltaTime);	
            if(k_pulo==true && person.estado_pulando==false) {
			person.iniciarPulo();
		}
		if(person.posY>353) {
			person.velY=0;
			person.aceY=0;
			person.estado_pulando=false;
			k_pulo = false;
			person.posY=353;
		}
		person.pular(deltaTime);
		testeColisoes(inimigo);
                testeColisoes(inimigo2);
	}
	public void render() {
		repaint();
	}
	
	// OUTROS METODOS -------------------------
	public void testeColisoes(Inimigo ini) {
		// colis�o comum ---------------------------------
                
                double ladoDireito = person.posX + person.raio;
                double distanciaLateral = ini.posX - ladoDireito;
                double distanciaVertical = (person.posY + person.altura) - (ini.posY+ini.altura);
                if(distanciaVertical < 0){
                    distanciaVertical = distanciaVertical * -1;
                }
                if(distanciaLateral < 0){
                    distanciaLateral = distanciaLateral * -1;
                }
                
                // Compara se a Distancia horizontal entre o personagem e o inimigo
                // menor que a largura do inimigo E se a distancia vertical é menor que a altura do inimigo
                if(distanciaLateral <= ini.largura && distanciaVertical <= ini.altura - 10){                                      
                    JOptionPane.showMessageDialog(this,"Sua pontuação é: " + pontuacao);
                    System.exit(0);
                }
                // Pontua enquanto estiver pulando por cima do inimigo
                if(distanciaLateral <= ini.largura && distanciaVertical >= ini.altura - 10){
                    pontuacao = pontuacao + 1;                    
                }
                if(pontuacao >= 200){
                    inimigo.velX = -4.5;
                    inimigo2.velX = -4.5;
                }
                if(pontuacao >= 400){
                    inimigo.velX = -6;
                    inimigo2.velX = -6;
                }
                

	}
		
	// METODO SOBRESCRITO ---------------------
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform af01 = new AffineTransform();
		AffineTransform af02 = new AffineTransform();
		AffineTransform af03 = new AffineTransform();
		AffineTransform af04 = new AffineTransform();
		AffineTransform af05 = new AffineTransform();
		AffineTransform af06 = new AffineTransform();
		AffineTransform af07 = new AffineTransform();
		af01.translate(bg01.posX, bg01.posY);
		g2d.drawImage(bg01.img, af01, null);
		
		af02.translate(bg02.posX, bg02.posY);
		g2d.drawImage(bg02.img, af02, null);
                
		af03.translate(bg03.posX, bg03.posY);
		g2d.drawImage(bg03.img, af03, null);
                
                af07.translate(bg04.posX, bg04.posY);
		g2d.drawImage(bg04.img, af07, null);
                		
		af04.translate(inimigo.posX, inimigo.posY);
		g2d.drawImage(inimigo.img, af04, null);
                
                af05.translate(inimigo2.posX, inimigo2.posY);
                g2d.drawImage(inimigo.img, af05, null);
                
		af06.translate(person.posX, person.posY);
		g2d.drawImage(person.img, af06, null);   
	}
}