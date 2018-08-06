package Mundo;

public class NoEncontradoExcepcion extends Exception {

	private int nivelMonstruoNoEncontrado;
	
	public NoEncontradoExcepcion(String message, int nivelMonstruo) {
		super(message);
		this.setNivelMonstruoNoEncontrado(nivelMonstruo);
		// TODO Auto-generated constructor stub
	}

	public int getNivelMonstruoNoEncontrado() {
		return nivelMonstruoNoEncontrado;
	}

	public void setNivelMonstruoNoEncontrado(int nivelMonstruoNoEncontrado) {
		this.nivelMonstruoNoEncontrado = nivelMonstruoNoEncontrado;
	}
	
	

}
