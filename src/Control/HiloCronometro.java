package Control;

import java.util.Date;

import Interfaz.InterfazPrincipal;
import Interfaz.PanelArena;
import Mundo.PrincipalJuego;

public class HiloCronometro extends Thread {

	public static final long CONVERSION_SEGUNDO = 1000;
	public static final long CONVERSION_MINUTO = 60000;
	
	
	private int minutoActual;
	private int segundoActual;
	private PanelArena panelArena;
	private PrincipalJuego mundo;
	
	public HiloCronometro (PanelArena panelArena, PrincipalJuego mundo)
	{
		this.mundo = mundo;
		this.panelArena = panelArena;
	}
	
	public void run()
	{
		while(this.mundo.getEstadoDuelo().equalsIgnoreCase(PrincipalJuego.ESTADO_EN_CURSO))
		{
			try {
				sleep(CONVERSION_SEGUNDO);
				
			
				segundoActual+=1;
				if(segundoActual>60)
				{
					minutoActual+=1;
					segundoActual =0;
				}
				panelArena.setTiempo(this.segundoActual,this.minutoActual);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
