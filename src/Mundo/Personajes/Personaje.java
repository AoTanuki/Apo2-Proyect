package Mundo.Personajes;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import Mundo.Interfaces.Caminar;

public abstract class Personaje {
	
	//atributos:
	private int posicionX;
	private int posicionY;
	private int velocidadX;
	private int velocidadY;
	private Frame frameActual;
	private int vida;
	private int daño;
	private String direccionActual;
	
	
	private Animacion caminarHaciaDerecha;
	private Animacion caminarHaciaIzquierda;
	private Animacion caminarHaciaArriba;
	private Animacion caminarHaciaAbajo;
	private Animacion animacionAtacarDerecha;
	private Animacion animacionAtacarIzquierda;
	
	
	
	
	
	public Personaje(int posicionX, int posicionY, int velocidadX, int velocidadY, int direccion, Frame frameActual,
			int vida, int daño, Animacion caminarHaciaDerecha, Animacion caminarHaciaIzquierda,
			Animacion caminarHaciaArriba, Animacion caminarHaciaAbajo, Animacion animacionAtacarDerecha,
			Animacion animacionAtacarIzquierda) {
		super();
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.frameActual = frameActual;
		this.vida = vida;
		this.daño = daño;
		this.caminarHaciaDerecha = caminarHaciaDerecha;
		this.caminarHaciaIzquierda = caminarHaciaIzquierda;
		this.caminarHaciaArriba = caminarHaciaArriba;
		this.caminarHaciaAbajo = caminarHaciaAbajo;
		this.animacionAtacarDerecha = animacionAtacarDerecha;
		this.animacionAtacarIzquierda = animacionAtacarIzquierda;
		this.direccionActual = Caminar.DIRECCION_ABAJO;
	}




	//Metodos:
	public void paint(Graphics2D g2d)
	{
		Image imagen1 = Toolkit.getDefaultToolkit().getImage(frameActual.getRutaImagen());
	
		g2d.drawImage(imagen1, posicionX, posicionY, null);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.draw(getBound());
	}
	
	
	
	
	//GET'S Y SET'S

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public Animacion getCaminarHaciaDerecha() {
		return caminarHaciaDerecha;
	}

	public void setCaminarHaciaDerecha(Animacion caminarHaciaDerecha) {
		this.caminarHaciaDerecha = caminarHaciaDerecha;
	}

	public Animacion getCaminarHaciaIzquierda() {
		return caminarHaciaIzquierda;
	}

	public void setCaminarHaciaIzquierda(Animacion caminarHaciaIzquierda) {
		this.caminarHaciaIzquierda = caminarHaciaIzquierda;
	}

	public Animacion getCaminarHaciaArriba() {
		return caminarHaciaArriba;
	}

	public void setCaminarHaciaArriba(Animacion caminarHaciaArriba) {
		this.caminarHaciaArriba = caminarHaciaArriba;
	}

	public Animacion getCaminarHaciaAbajo() {
		return caminarHaciaAbajo;
	}

	public void setCaminarHaciaAbajo(Animacion caminarHaciaAbajo) {
		this.caminarHaciaAbajo = caminarHaciaAbajo;
	}

	public Animacion getAnimacionAtacarDerecha() {
		return animacionAtacarDerecha;
	}

	public void setAnimacionAtacarDerecha(Animacion animacionAtacarDerecha) {
		this.animacionAtacarDerecha = animacionAtacarDerecha;
	}

	public Animacion getAnimacionAtacarIzquierda() {
		return animacionAtacarIzquierda;
	}

	public void setAnimacionAtacarIzquierda(Animacion animacionAtacarIzquierda) {
		this.animacionAtacarIzquierda = animacionAtacarIzquierda;
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	public Frame getFrameActual() {
		return frameActual;
	}

	public void setFrameActual(Frame frameActual) {
		this.frameActual = frameActual;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getDaño() {
		return daño;
	}

	public void setDaño(int daño) {
		this.daño = daño;
	}

	public void setDireccion(String direccion)
	{
		if(direccion.equalsIgnoreCase(Caminar.DIRECCION_DERECHA))
		{
			if(velocidadX<0) this.velocidadX = velocidadX*Caminar.DIRECCION_CONTRARIA;
			this.direccionActual = Caminar.DIRECCION_DERECHA;
			this.frameActual = caminarHaciaDerecha.getPrimero();
			
		}else if(direccion.equalsIgnoreCase(Caminar.DIRECCION_IZQUIERDA))
		{
			if(velocidadX>0) this.velocidadX = velocidadX*Caminar.DIRECCION_CONTRARIA;
			this.direccionActual = Caminar.DIRECCION_IZQUIERDA;
			this.frameActual = caminarHaciaIzquierda.getPrimero();
			
		}else if(direccion.equalsIgnoreCase(Caminar.DIRECCION_ARRIBA))
		{
			
			if(velocidadY>0) this.velocidadY = velocidadY*Caminar.DIRECCION_CONTRARIA;
			this.direccionActual = Caminar.DIRECCION_ARRIBA;
			this.frameActual = caminarHaciaArriba.getPrimero();
			
		}else if((direccion.equalsIgnoreCase(Caminar.DIRECCION_ABAJO)))
		{
			if(velocidadY<0) this.velocidadY = velocidadY*Caminar.DIRECCION_CONTRARIA;
			this.direccionActual = Caminar.DIRECCION_ABAJO;
			this.frameActual = caminarHaciaAbajo.getPrimero();
		}
	}

	public String getDireccionActual() {
		return direccionActual;
	}


	public Rectangle getBound()
	{
		
		ImageInputStream imageInput;
		BufferedImage imagenL = null;
		try {
			InputStream input = new FileInputStream(frameActual.getRutaImagen());
			imageInput = ImageIO.createImageInputStream(input);
			imagenL = ImageIO.read(imageInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new Rectangle(posicionX, posicionY, imagenL.getWidth(), imagenL.getHeight()) ;
			
	}
	
	public Rectangle getBoundAumento(int posX, int posY)
	{
		
		ImageInputStream imageInput;
		BufferedImage imagenL = null;
		try {
			InputStream input = new FileInputStream(frameActual.getRutaImagen());
			imageInput = ImageIO.createImageInputStream(input);
			imagenL = ImageIO.read(imageInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new Rectangle(posX, posY, imagenL.getWidth(), imagenL.getHeight()) ;
			
	}
	
	public boolean colision(Rectangle bound,int posX ,int posY)
	{
		return this.getBoundAumento(posX,posY).intersects(bound);
		
	}
	
	public boolean colision (Rectangle bound)
	{
		return this.getBound().intersects(bound);
	}
	
	public ArrayList<Animacion> getAnimaciones()
	{
		ArrayList<Animacion>animacion = new ArrayList<>();
		animacion.add(caminarHaciaArriba); //0
		animacion.add(caminarHaciaAbajo); //1
		animacion.add(caminarHaciaDerecha); //2
		animacion.add(caminarHaciaIzquierda); //3
		
		return animacion;
	}
	
	
}
