package PruebasMundo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Mundo.PrincipalJuego;
import Mundo.Personajes.PersonajePrincipal;

class PrincipalJuegoTest {

	private PrincipalJuego principal;
	
	private void setUpEscenario() throws IOException
	{
		principal = new PrincipalJuego();
	}
	
	@Test
	void testCargarPersonajes() {
		try {
			setUpEscenario();
			principal.cargarPersonajes();
			PersonajePrincipal p= principal.getGladiador();
			
			assertTrue("./data/Frames/personaje/personajeAbajo1.png" == p.getFrameActual().getRutaImagen(), p.getFrameActual().getRutaImagen());
			
		} catch (IOException e) {
			fail("Error tipo: "+e.getMessage());
			e.printStackTrace();
		}
		
	}

}
