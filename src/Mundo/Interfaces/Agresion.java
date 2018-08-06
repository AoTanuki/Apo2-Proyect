package Mundo.Interfaces;

import Mundo.Personajes.Personaje;

public interface Agresion {

	int VIDA_MONSTRUO_JEFE = 300;
	int VIDA_MONSTRUO_NORMAL = 40;
	int VIDA_PERSONAJE_PRINCIPAL = 600;
	
	int DAÑO_PERSONAJE = 20;
	int DAÑO_MONSTRUO_NORMAL = 15;
	int DAÑO_MONSTRUO_JEFE = 20;
	
	int SCORE_MONSTRUO_NORMAL = 100;
	int SCORE_MONSTRUO_JEFE = 500;
	
	void recibirDaño(int daño);
	void atacar(Agresion personaje);
	boolean muerto();
	int darPuntos();
}
