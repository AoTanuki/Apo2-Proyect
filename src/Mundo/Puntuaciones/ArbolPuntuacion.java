package Mundo.Puntuaciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;



public class ArbolPuntuacion implements Serializable{

	private Nodo raiz;
	private ArrayList<Puntuacion> puntuacionesOrdenadas;
	
	public ArbolPuntuacion()
	{
		this.raiz = null;
		this.puntuacionesOrdenadas = new ArrayList<>();
	}
	
	public void agregarElemento(Puntuacion puntuacion)
	{
		if(raiz==null)
		{
			raiz = new Nodo(puntuacion);
		}else
		{
			raiz.insertarElemento(puntuacion);
		}
	}
	
	private void llenarLista() throws SinPuntajesExcepcion
	{
		if (raiz != null)
		{
			puntuacionesOrdenadas =raiz.inOrder();
			Collections.reverse(puntuacionesOrdenadas);
		}else
		{
			throw new SinPuntajesExcepcion();
		}
	}
	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	public ArrayList<Puntuacion> getPuntuacionesOrdenadas() throws SinPuntajesExcepcion {
		
		llenarLista();
		return puntuacionesOrdenadas;
	}

	public void setPuntuacionesOrdenadas(ArrayList<Puntuacion> puntuacionesOrdenadas) {
		this.puntuacionesOrdenadas = puntuacionesOrdenadas;
	}
	
	
}
