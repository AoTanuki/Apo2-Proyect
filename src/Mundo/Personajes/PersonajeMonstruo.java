package Mundo.Personajes;

import java.awt.Rectangle;

import Mundo.Interfaces.Agresion;
import Mundo.Interfaces.Caminar;

public abstract class PersonajeMonstruo extends Personaje implements Agresion, Caminar {

	private int score;
	private int nivel;
	private int esperaAtaque;

	public PersonajeMonstruo(int posicionX, int posicionY, int velocidadX, int velocidadY, int direccion,
			Frame frameActual, int vida, int daño, Animacion caminarHaciaDerecha, Animacion caminarHaciaIzquierda,
			Animacion caminarHaciaArriba, Animacion caminarHaciaAbajo, Animacion animacionAtacarDerecha,
			Animacion animacionAtacarIzquierda, int score, int nivel) {
		super(posicionX, posicionY, velocidadX, velocidadY, direccion, caminarHaciaAbajo.getPrimero(), vida, daño,
				caminarHaciaDerecha, caminarHaciaIzquierda, caminarHaciaArriba, caminarHaciaAbajo,
				animacionAtacarDerecha, animacionAtacarIzquierda);
		this.setScore(score);
		this.setNivel(nivel);
		this.esperaAtaque = 0;

	}

	public void caminar(PersonajePrincipal objetivo, Frame frame) {
		int posXdestino = objetivo.getPosicionX();
		int posYdestino = objetivo.getPosicionY();
		Rectangle colision = objetivo.getBound();

		// Decidir direccion:
		System.out.println(esperaAtaque);
		System.out.println(colision(objetivo.getBound()) == false && esperaAtaque==0);
		if(colision(objetivo.getBound()) == false && esperaAtaque==0) {
		if (getPosicionY() != posYdestino) {
			if (getPosicionY() <posYdestino) {
				setDireccion(DIRECCION_ABAJO);
			} else if (getPosicionY() > posYdestino) {
				setDireccion(DIRECCION_ARRIBA);
			}

			setPosicionY(getPosicionY()+getVelocidadY());
		}else if(getPosicionX()!= posXdestino)
		{
			if(getPosicionX()<posXdestino)
			{
				setDireccion(DIRECCION_DERECHA);
			}else if(getPosicionX()>posXdestino)
			{
				setDireccion(DIRECCION_IZQUIERDA);
			}
			setPosicionX(getPosicionX()+getVelocidadX());
		}
	}else if(colision(objetivo.getBound()))
		{
			if(esperaAtaque==0) {
			atacar(objetivo);
			}else
			{
				esperaAtaque-=2;
			}
		}
		System.out.println(esperaAtaque);
		if(esperaAtaque!= 0)
		{
			if(!colision(objetivo.getBound()))esperaAtaque-=2;
		}
		setFrameActual(frame);
	}
	
	@Override
	public void atacar (Agresion personaje)
	{
		personaje.recibirDaño(getDaño());
		esperaAtaque = 8;
	}
	
	@Override
	public void recibirDaño(int daño) {
		setVida(getVida()-daño);
	}




	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Retornta true si el nivel de este monstruo es mayor que el del monstruo que
	 * se pasa por parametro
	 * 
	 * @param personajeAComparar
	 * @return
	 */
	public boolean compararPorNivel(PersonajeMonstruo personajeAComparar) {
		return this.nivel > personajeAComparar.nivel;
	}

}
