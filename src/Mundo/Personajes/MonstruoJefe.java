package Mundo.Personajes;

import Mundo.Interfaces.Agresion;
import Mundo.Interfaces.Caminar;

public class MonstruoJefe extends PersonajeMonstruo {
	
	private String nombre;
	
	public MonstruoJefe( Animacion caminarHaciaDerecha, Animacion caminarHaciaIzquierda,
			Animacion caminarHaciaArriba, Animacion caminarHaciaAbajo, Animacion animacionAtacarDerecha,
			Animacion animacionAtacarIzquierda) {
		
		super(1256, 320, Caminar.VELOCIDAD_X_INICIAL, Caminar.VELOCIDAD_Y_INICIAL, Caminar.DIRECCION_NORMAL, caminarHaciaAbajo.getPrimero(), Agresion.VIDA_MONSTRUO_JEFE, Agresion.DAÑO_MONSTRUO_JEFE, caminarHaciaDerecha,
				caminarHaciaIzquierda, caminarHaciaArriba, caminarHaciaAbajo, animacionAtacarDerecha, animacionAtacarIzquierda,
				SCORE_MONSTRUO_JEFE, 5);
		this.setNombre("Bryan, el decapitador");
		super.setDaño(super.getDaño());
		super.setVida(super.getVida()*super.getNivel());
		super.setDireccion(Caminar.DIRECCION_IZQUIERDA);
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public boolean muerto() {
		if(getVida()<=0)
		{
			return true;
		}else
		{
			return false;
		}
		
	}



	@Override
	public void caminar(String tecla, Frame frame) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int darPuntos() {
		// TODO Auto-generated method stub
		return getScore();
	}

	
	

}
