package Mundo.Puntuaciones;

import java.io.Serializable;
import java.util.ArrayList;


public class Nodo implements Serializable {

	private Nodo derecha;
	private Nodo izquierda;
	private Puntuacion puntuacion;
	
	public Nodo(Puntuacion puntuacion)
	{
		this.puntuacion = puntuacion;
		this.derecha = null;
		this.izquierda = null;
	}
	
	public void insertarElemento (Puntuacion puntuacion)
	{
		if(this.puntuacion.compararPorPuntuacion(puntuacion)<0)
		{
			if(derecha!=null)
			{
				derecha.insertarElemento(puntuacion);
			}else
			{
				derecha = new Nodo(puntuacion);
			}
		}else
		{
			if(izquierda!= null)
			{
				izquierda.insertarElemento(puntuacion);
			}else
			{
				izquierda = new Nodo(puntuacion);
			}
		}
	}
	
	public ArrayList<Puntuacion> inOrder()
	{
		if(derecha==null && izquierda == null)
		{
			ArrayList<Puntuacion> puntuaciones = new ArrayList<>();
			puntuaciones.add(this.puntuacion);
			return puntuaciones;
		}else
		{
			ArrayList<Puntuacion> puntuaciones = new ArrayList<>();
			if(izquierda!= null) puntuaciones.addAll(izquierda.inOrder());
			puntuaciones.add(this.puntuacion);
			if(derecha!= null) puntuaciones.addAll(derecha.inOrder());
			
			return puntuaciones;
		}
	}

	public Nodo getDerecha() {
		return derecha;
	}

	public void setDerecha(Nodo derecha) {
		this.derecha = derecha;
	}

	public Nodo getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(Nodo izquierda) {
		this.izquierda = izquierda;
	}

	public Puntuacion getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Puntuacion puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
}
