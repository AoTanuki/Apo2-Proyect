package PruebasMundo;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.ImagingOpException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.Test;


class AnimacionTest {

	private Mundo.Personajes.Animacion animacion;
	
	private void setUpEscenario()
	{
		animacion = new Mundo.Personajes.Animacion();
	}
	
	
	@Test
	void testAgregarFrame() {
		String frame1 = "./data/Frames/personaje/personajeAbajo1.png";
		String frame2 = "./data/Frames/personaje/personajeAbajo2.png";
		String frame3 = "./data/Frames/personaje/personajeAbajo3.png";
		setUpEscenario();
		try
		{
		int ordenActual = 0; // es el orden de los frames que se han ingresado
		while (ordenActual<3)
		{
			++ordenActual;
			animacion.agregarFrame("./data/Frames/Monstruos/Movimientos/monstruo2Abajo"+ordenActual+".png");
			
			//Haré un recorrido circular 10 veces para asegurarme de que 
			int indice= 0;
			Mundo.Personajes.Frame siguiente = animacion.getPrimero();
			while(indice<ordenActual)
			{
				if(ordenActual ==1)
				{
					assertTrue(siguiente.getSiguiente().getOrden() == 0);
					assertTrue(siguiente.getAnterior().getOrden() == 0);
				}else
				{
					if(indice ==0)
					{
						assertTrue(siguiente.getSiguiente().getOrden() == indice+1);
						assertTrue(siguiente.getAnterior().getOrden() == ordenActual-1);
					}else if (indice ==ordenActual-1 )
					{
						assertTrue(siguiente.getSiguiente().getOrden() == 0);
						assertTrue(siguiente.getAnterior().getOrden() == indice-1);
					}else
					{
						assertTrue(siguiente.getSiguiente().getOrden()  == indice+1);
						assertTrue(siguiente.getAnterior().getOrden() == indice-1);
					}
				}
				
				indice++;
				siguiente = siguiente.getSiguiente();
				System.out.println("el siguiente es este: "+ siguiente.toString());
			}
			
			
		}
		
		//prueba de animacion:
//		JFrame ventanita = new JFrame("prueba");
//		ventanita.setVisible(true);
//		ventanita.setSize(100, 100);
//		ventanita.setLocationRelativeTo(null);
//		ventanita.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JLabel lblImagen = new JLabel();
//		ventanita.add(lblImagen);
//		int h = 100;
//		Frame siguiente = animacion.getPrimero();
//		while (h>0)
//		{
//			if(siguiente!= animacion.getPrimero()) {
//			ImageIcon imagen = new ImageIcon(siguiente.getRutaImagen());
//			lblImagen.setIcon(imagen);
//			Thread.sleep(400);
//			h--;
//			}
//			siguiente = siguiente.getSiguiente();
			
//		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
			fail("Se presento la excepcion: "+e.getMessage());
		}
	}

}
