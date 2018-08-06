package Mundo.Personajes;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Control.HiloAtaque;
import Mundo.Interfaces.*;

public class PersonajePrincipal extends Personaje implements Girar, Agresion {

	public static final String RUTA_FRAME_ATAQUE_DERECHA = "./data/Frames/personaje/ataqueDerecha.png";
	public static final String RUTA_FRAME_ATAQUE_IZQUIERDA = "./data/Frames/personaje/ataqueIzquierda.png";

	public static final int SCORE_INICIAL = 0;
	public static final int POSICION_X_INICIAL = 80;
	public static final int POSICION_Y_INICIAL = 400;

	private String nombre;
	private Animacion animacionMuerte;
	private int score;
	private boolean ataco;

	public PersonajePrincipal(Animacion caminarHaciaDerecha, Animacion caminarHaciaIzquierda,
			Animacion caminarHaciaArriba, Animacion caminarHaciaAbajo, Animacion animacionAtacarDerecha,
			Animacion animacionAtacarIzquierda, String nombre, Animacion animacionMuerte) {
		super(POSICION_X_INICIAL, POSICION_Y_INICIAL, Caminar.VELOCIDAD_X_INICIAL, Caminar.VELOCIDAD_Y_INICIAL,
				Caminar.DIRECCION_NORMAL, caminarHaciaAbajo.getPrimero(), Agresion.VIDA_PERSONAJE_PRINCIPAL,
				Agresion.DAÑO_PERSONAJE, caminarHaciaDerecha, caminarHaciaIzquierda, caminarHaciaArriba,
				caminarHaciaAbajo, animacionAtacarDerecha, animacionAtacarIzquierda);
		super.setDireccion(Caminar.DIRECCION_ABAJO);
		this.nombre = nombre;
		this.animacionMuerte = animacionMuerte;
		score = SCORE_INICIAL;
		ataco = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Animacion getAnimacionMuerte() {
		return animacionMuerte;
	}

	public void setAnimacionMuerte(Animacion animacionMuerte) {
		this.animacionMuerte = animacionMuerte;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void caminar(String tecla, Frame frame) {
		if (tecla.equalsIgnoreCase("W")) {
			if (getPosicionY() + getVelocidadY() > 157) {
				setPosicionY(getPosicionY() + getVelocidadY());
			}
		}
		if (tecla.equalsIgnoreCase("A")) {
			if (getPosicionX() + getVelocidadX() > 33) {
				setPosicionX(getPosicionX() + getVelocidadX());
			}
		}
		if (tecla.equalsIgnoreCase("S")) {
			if (getPosicionY() + getVelocidadY() < 637) {
				setPosicionY(getPosicionY() + getVelocidadY());
			}
		}
		if (tecla.equalsIgnoreCase("D")) {
			if (getPosicionX() + getVelocidadX() < 1289) {
				setPosicionX(getPosicionX() + getVelocidadX());
			}
		}

		setFrameActual(frame);
	}

	@Override
	public void recibirDaño(int daño) {
		this.setVida(getVida() - daño);

	}

	@Override
	public void atacar(Agresion personaje) {
		PersonajeMonstruo r = (PersonajeMonstruo) personaje;
		Rectangle ataque = null;
		if(getVelocidadX()<0)
		{
			ataque = crearBoundAtaqueIzquierda();
		}else
		{
			ataque = crearBoundAtaqueDerecha();
		}
		HiloAtaque hilo = new HiloAtaque();
		hilo.start();
		if (ataque.intersects(r.getBound())) {
			personaje.recibirDaño(getDaño());
		}
		if (personaje.muerto()) {
			this.setScore(personaje.darPuntos());
		}
	}

	public Rectangle crearBoundAtaqueIzquierda() {
		int posicionXAtaqueInicia = getPosicionX() - 30;
		int posicionYAtaqueInicial = getPosicionY();

		return new Rectangle(posicionXAtaqueInicia, posicionYAtaqueInicial, 30, 20);

	}
	public Rectangle crearBoundAtaqueDerecha() {
		int posicionXAtaqueInicia = getPosicionX()+30;
		int posicionYAtaqueInicial = getPosicionY();

		return new Rectangle(posicionXAtaqueInicia, posicionYAtaqueInicial, 30, 20);

	}

	@Override
	public void girar(String direccion) {
		super.setDireccion(direccion);

	}

	@Override
	public boolean muerto() {

		if (getVida() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public void recibirPuntos(int score) {
		this.score = score;
	}

	@Override
	public void caminar(PersonajePrincipal objetivo, Frame frame) {
		// TODO Auto-generated method stub

	}

	@Override
	public int darPuntos() {
		// TODO Auto-generated method stub
		return 0;
	}

}
