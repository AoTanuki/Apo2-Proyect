package Mundo.Puntuaciones;

import java.io.Serializable;
import java.sql.Date;

public class Puntuacion implements Serializable {
	
	private String nombreGladiado;
	private int puntosGladiador;
	private java.util.Date fecha;
	public Puntuacion(String nombreGladiado, int puntosGladiador, java.util.Date date) {
		super();
		this.nombreGladiado = nombreGladiado;
		this.puntosGladiador = puntosGladiador;
		this.fecha =  date;
	}
	
	public String getNombreGladiado() {
		return nombreGladiado;
	}
	public void setNombreGladiado(String nombreGladiado) {
		this.nombreGladiado = nombreGladiado;
	}
	public int getPuntosGladiador() {
		return puntosGladiador;
	}
	public void setPuntosGladiador(int puntosGladiador) {
		this.puntosGladiador = puntosGladiador;
	}
	
	
	public int compararPorPuntuacion (Puntuacion comparado)
	{
		int valor= 0;
		
		if(puntosGladiador< comparado.puntosGladiador)
		{
			valor = -1;
		}
		if(puntosGladiador> comparado.puntosGladiador)	
		{
			valor = 1;
		}
		
		return valor;
	}

	@Override
	public String toString() {
		return "[nombreGladiador=" + nombreGladiado + ", puntosGladiador=" + puntosGladiador + ", fecha="
				+ fecha + "]";
	}

}
