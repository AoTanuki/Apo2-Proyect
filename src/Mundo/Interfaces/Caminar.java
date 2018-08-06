package Mundo.Interfaces;

import java.awt.Dimension;

import Mundo.Personajes.Frame;
import Mundo.Personajes.PersonajePrincipal;

public interface Caminar {

	int VELOCIDAD_X_INICIAL = 8;
	int VELOCIDAD_Y_INICIAL = 8;
	int DIRECCION_NORMAL = 1;
	int DIRECCION_CONTRARIA = -1;
	
	int CANTIDAD_PIXELES_X = 42;
	int CANTIDAD_PIXELES_Y = 22;
	int DIMENSION_PIXELES = 32;
	Dimension DIMENSION_TABLERO = new Dimension(CANTIDAD_PIXELES_X*DIMENSION_PIXELES,DIMENSION_PIXELES*CANTIDAD_PIXELES_Y);
	
	
	String DIRECCION_DERECHA = "Derecha";
	String DIRECCION_IZQUIERDA = "izquierda";
	String DIRECCION_ARRIBA = "Arriba";
	String DIRECCION_ABAJO = "Abajo";
	
	void caminar(String tecla,Frame frame);
	void caminar(PersonajePrincipal objetivo,Frame frame);
}
