package Mundo;

import java.io.Serializable;
import java.util.ArrayList;

import Mundo.Personajes.PersonajeMonstruo;

public class Nivel implements Serializable{

	private int nivel;
	private ArrayList<PersonajeMonstruo> monstruosDefinidos;
	
	public Nivel(int nivel, ArrayList<PersonajeMonstruo>monstruos)
	{
		this.nivel = nivel;
		this.monstruosDefinidos = monstruos;
	}
}
