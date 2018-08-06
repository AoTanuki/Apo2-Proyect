package Control;

import java.awt.Frame;

import org.junit.jupiter.engine.discovery.predicates.IsScannableTestClass;

import Interfaz.PanelArena;
import Mundo.Personajes.Animacion;
import Mundo.Interfaces.Caminar;
import Mundo.Personajes.*;

public class HiloAnimacionCaminar extends Thread {

	private Animacion animacionCaminar;
	private PanelArena panelArena;
	private Caminar personajeInterfaz;
	private Personaje personaje;
	private String tecla;

	public HiloAnimacionCaminar(Animacion animacionCaminar, PanelArena panelArena, Personaje personaje, String tecla) {
		this.animacionCaminar = animacionCaminar;
		this.panelArena = panelArena;
		this.personaje = personaje;
		this.personajeInterfaz = (Caminar) personaje;
		this.tecla = tecla;
	}

	public void run() {
		Mundo.Personajes.Frame siguiente = animacionCaminar.getPrimero();

		siguiente = siguiente.getSiguiente();
		while (siguiente != animacionCaminar.getPrimero()) {
			personajeInterfaz.caminar(tecla, siguiente);
			siguiente = siguiente.getSiguiente();
			try {
				panelArena.repaint();
				this.sleep(120);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		panelArena.setLabelVida(personaje.getVida());
		personajeInterfaz.caminar(tecla, siguiente);
		panelArena.repaint();
	}

}
