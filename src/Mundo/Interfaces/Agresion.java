package Mundo.Interfaces;

import Mundo.Personajes.Personaje;

public interface Agresion {

	int VIDA_MONSTRUO_JEFE = 300;
	int VIDA_MONSTRUO_NORMAL = 40;
	int VIDA_PERSONAJE_PRINCIPAL = 600;
	
	int DA�O_PERSONAJE = 20;
	int DA�O_MONSTRUO_NORMAL = 15;
	int DA�O_MONSTRUO_JEFE = 20;
	
	int SCORE_MONSTRUO_NORMAL = 100;
	int SCORE_MONSTRUO_JEFE = 500;
	
	void recibirDa�o(int da�o);
	void atacar(Agresion personaje);
	boolean muerto();
	int darPuntos();
}
