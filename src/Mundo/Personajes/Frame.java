package Mundo.Personajes;

public class Frame {

	private Frame siguiente;
	private Frame anterior;
	private int orden;
	
	private String rutaImagen;
	
	public Frame(String rutaImagen, Frame anterior, int orden) 
	{
		this.orden = orden;
		this.rutaImagen = rutaImagen;
		this.anterior = anterior;
		siguiente = null;
	}
	
	public Frame (String rutaImagen, int orden)
	{
		this.orden = orden;
		this.rutaImagen = rutaImagen;
		this.anterior = null;
		this.siguiente = null;
	}
	

	public Frame getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Frame siguiente) {
		this.siguiente = siguiente;
	}

	public Frame getAnterior() {
		return anterior;
	}

	public void setAnterior(Frame anterior) {
		this.anterior = anterior;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	@Override
	public String toString() {
		return "Frame [orden=" + orden + ", rutaImagen="
				+ rutaImagen + "]";
	}
	

}
