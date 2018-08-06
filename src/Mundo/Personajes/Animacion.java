package Mundo.Personajes;

public class Animacion {

	private Frame primero;
	private Frame ultimo;

	public Animacion() {
		primero = null;
		ultimo = primero;
	}

	public void agregarFrame(String rutaImagen) {
		// TODO
		if (primero != null) {
			Frame nuevo = new Frame(rutaImagen, primero.getAnterior().getOrden() + 1);
			ultimo.setSiguiente(nuevo);
			primero.setAnterior(nuevo);
			nuevo.setAnterior(ultimo);
			nuevo.setSiguiente(primero);
			ultimo = nuevo;
		} else {
			this.primero = new Frame(rutaImagen, 0);
			this.ultimo = primero;
			this.primero.setSiguiente(primero);
			this.primero.setAnterior(primero);
		}
	}

//	public void eliminarFrame (int orden)
//	{
//		if(primero!= null)
//		{
//			if(orden == 0)
//			{
//				if(primero == ultimo)
//				{
//					primero = null;
//				}
//				else
//				{
//					primero = primero.getSiguiente();
//					primero.setAnterior(ultimo);
//					ultimo.setSiguiente(primero);
//				}
//			}else if(primero!= ultimo)
//			{
//				boolean encontrado = false;
//				Frame siguiente = primero;
//				while (siguiente.getSiguiente()!= primero && !encontrado)
//				{
//					if(siguiente.getOrden() == orden)
//					{
//						siguiente.setAnterior(siguiente.getSiguiente());
//						siguiente.setSiguiente(siguiente.getAnterior());
//						encontrado = true;
//					}
//					siguiente = siguiente.getSiguiente();
//				}
//			}
//		}
//	}

	public Frame getPrimero() {
		return primero;
	}

	public void setPrimero(Frame primero) {
		this.primero = primero;
	}

	public Frame getUltimo() {
		return ultimo;
	}

	public void setUltimo(Frame ultimo) {
		this.ultimo = ultimo;
	}

	@Override
	public String toString() {
		return "Animacion [primero=" + primero + ", ultimo=" + ultimo + "]";
	}

}
