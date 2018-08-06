package Control;

import java.util.ArrayList;

import Interfaz.PanelArena;
import Mundo.Interfaces.Caminar;
import Mundo.Personajes.Animacion;
import Mundo.Personajes.Personaje;
import Mundo.Personajes.PersonajePrincipal;

public class HiloAnimacionCaminarMonstruos extends Thread{

	private PanelArena panelArena;
	private Caminar personajeInterfaz;
	private Personaje personaje;
	private PersonajePrincipal objetivo;
	private ArrayList<Animacion>  animaciones;
	private String letra;
	
	public HiloAnimacionCaminarMonstruos(ArrayList<Animacion> animaciones, PanelArena panelArena, Personaje personaje, PersonajePrincipal objetivo,String letra) {
		this.animaciones = animaciones;
		this.panelArena = panelArena;
		this.personaje = personaje;
		this.personajeInterfaz = (Caminar) personaje;
		this.objetivo = objetivo;
		this.letra = letra;
	}
	
	
	public void run()
	{	
		Mundo.Personajes.Frame siguiente;
		Animacion usado = null;
		try {
			char evaluador  = letra.charAt(0);
			if(evaluador== 'W' || evaluador== 'A' || evaluador== 'S' || evaluador== 'D' ||evaluador == 'E') {
			if(personaje.getPosicionY()!= objetivo.getPosicionY())
			{
				if (personaje.getPosicionY() <objetivo.getPosicionY()) {
					usado = animaciones.get(1);
				} else if (personaje.getPosicionY() >objetivo.getPosicionY()) {
					usado = animaciones.get(0);
				}
			}else if(personaje.getPosicionX()!= objetivo.getPosicionX()+29)
			{
				if(personaje.getPosicionX()<objetivo.getPosicionX())
				{
					usado = animaciones.get(2);
					
				}else if(personaje.getPosicionX()>objetivo.getPosicionX())
				{
					usado = animaciones.get(3);
				}
			}
			
			siguiente = usado.getPrimero();
			siguiente = siguiente.getSiguiente();
			while (siguiente != usado.getPrimero()) {
				personajeInterfaz.caminar(objetivo, siguiente);
				siguiente = siguiente.getSiguiente();
				try {
					panelArena.repaint();
					this.sleep(120);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				panelArena.setLabelVidaJefe(personaje.getVida());
		}
			
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

