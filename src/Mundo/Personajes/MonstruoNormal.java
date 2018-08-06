package Mundo.Personajes;

import Mundo.Interfaces.Agresion;
import Mundo.Interfaces.Caminar;

public class MonstruoNormal extends PersonajeMonstruo {

	public MonstruoNormal( Animacion caminarHaciaDerecha, Animacion caminarHaciaIzquierda,
			Animacion caminarHaciaArriba, Animacion caminarHaciaAbajo, int nivel) {
		
		
		super(1261, 396, Caminar.VELOCIDAD_X_INICIAL, Caminar.VELOCIDAD_Y_INICIAL, Caminar.DIRECCION_NORMAL, caminarHaciaIzquierda.getPrimero(), Agresion.VIDA_MONSTRUO_NORMAL, Agresion.DAÑO_MONSTRUO_NORMAL, caminarHaciaDerecha,
				caminarHaciaIzquierda, caminarHaciaArriba, caminarHaciaAbajo,null, null,
				Agresion.SCORE_MONSTRUO_NORMAL, nivel);
		
		super.setDaño(super.getDaño()*super.getNivel());
		super.setVida(super.getVida()*super.getNivel());
		super.setScore(getScore()*getNivel());
		super.setDireccion(Caminar.DIRECCION_IZQUIERDA);
	}

	@Override
	public void recibirDaño(int daño) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atacar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void morir() {
		// TODO Auto-generated method stub
		
	}
	
	public int entregarPuntos()
	{
		return super.getScore();
	}

	@Override
	public void caminar(String tecla, Frame frame) {
		// TODO Auto-generated method stub
		
	}


}
