package Mundo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import Mundo.Personajes.Animacion;
import Mundo.Personajes.Frame;
import Mundo.Personajes.MonstruoJefe;
import Mundo.Personajes.MonstruoNormal;
import Mundo.Personajes.Personaje;
import Mundo.Personajes.PersonajeMonstruo;
import Mundo.Personajes.PersonajePrincipal;
import Mundo.Puntuaciones.ArbolPuntuacion;
import Mundo.Puntuaciones.Puntuacion;
import Mundo.Puntuaciones.SinPuntajesExcepcion;

public class PrincipalJuego{

	public static final String ESTADO_NO_INICIADO = "no iniciado";
	public static final String ESTADO_EN_CURSO	 = "En curso";
	public static final String ESTADO_CONCLUIDO = "Concluido";
	
	
	private PersonajePrincipal gladiador;
	private MonstruoJefe jefe;
	private ArrayList<PersonajeMonstruo> monstruos;
	private ArbolPuntuacion arbolPuntuaciones;
	private String estadoDuelo;
	
	public PrincipalJuego() throws IOException, ClassNotFoundException {
		cargarPersonajes();
		monstruos = new ArrayList<>();
		arbolPuntuaciones = new ArbolPuntuacion();
		cargarMonstruos();
		cargarPuntuacion();
		setEstadoDuelo(ESTADO_NO_INICIADO);
	}

	public Object[] cargarDibujo() throws IOException,NumberFormatException
	{
		Object[] datos = new Object[3];
		
		File archivo = new File("./data/config/DibujoPrimitivo.cvs");
		BufferedReader bfr = new BufferedReader(new FileReader(archivo));
		
		String line = bfr.readLine();
		
		//Se hace otra vez para pasar la parte que describe lo que hace la linea que viene, osea, indicar la cantidad de datos que habrá.
		line = bfr.readLine();
		
		int[] posx = new int[Integer.parseInt(line)];
		int[] posy = new int[Integer.parseInt(line)];
		Color[] colores = new Color[Integer.parseInt(line)];
	
		line = bfr.readLine();
		int pos = 0;
		while(pos<posx.length)
		{
			if(!line.startsWith("#"))
			{
				String[] data = line.split(",");
				posx[pos] = Integer.parseInt(data[0]);
				posy[pos] = Integer.parseInt(data[1]);
				
				int r = Integer.parseInt(data[2]);
				int g = Integer.parseInt(data[3]);
				int b = Integer.parseInt(data[4]);
				
				colores[pos] = new Color(r, g, b); 
				pos++;
				line = bfr.readLine();
			}else 
				line = bfr.readLine();
		}
		datos[0] = posx;
		datos[1] = posy;
		datos[2] = colores;
		bfr.close();
		return datos;
	}
	
	
	public void cargarPersonajes() throws IOException
	{
		File file = new File("./data/config/personajes.txt");
		BufferedReader bfr = new BufferedReader(new FileReader(file));
		String line = bfr.readLine();
		
		//PersonajePrincipal
		String rutaFrames = "";		
		Animacion haciaAbajo = new Animacion(),haciaDerecha= new Animacion(),haciaIzquierda= new Animacion(),haciaArriba= new Animacion(),muerte= new Animacion(),
				ataqueDerecha= new Animacion(),ataqueIzquierda = new Animacion();
//		System.out.println(line);
		line = bfr.readLine();
		while (line!= null )
		{
			if(line.startsWith("#"))
			{
				if(line.equalsIgnoreCase("#Frames: carpeta:"))
				{
					line = bfr.readLine();
					rutaFrames =line;
				}else if(line.equalsIgnoreCase("#CaminarHaciaAbajo:"))
				{
					line = bfr.readLine();
					String[] rutas =  line.split(",");
					for (int i = 0; i < rutas.length; i++) {
						haciaAbajo.agregarFrame(rutaFrames+rutas[i]);
					}
				}else if(line.equalsIgnoreCase("#CaminarHaciaArriba:"))
				{
					line = bfr.readLine();
					String[] rutas =  line.split(",");
					for (int i = 0; i < rutas.length; i++) {
						haciaArriba.agregarFrame(rutaFrames+rutas[i]);
					}
				}else if(line.equalsIgnoreCase("#CaminarHaciaDerecha:"))
				{
					line = bfr.readLine();
					String[] rutas =  line.split(",");
					for (int i = 0; i < rutas.length; i++) {
						haciaDerecha.agregarFrame(rutaFrames+rutas[i]);
					}
				}else if(line.equalsIgnoreCase("#CaminarHaciaIzquierda:"))
				{
					line = bfr.readLine();
					String[] rutas =  line.split(",");
					for (int i = 0; i < rutas.length; i++) {
						haciaIzquierda.agregarFrame(rutaFrames+rutas[i]);
					}
				}else if(line.equalsIgnoreCase("#Muerto:"))
				{
					line = bfr.readLine();
					String[] rutas =  line.split(",");
					for (int i = 0; i < rutas.length; i++) {
						muerte.agregarFrame(rutaFrames+rutas[i]);
					}
				}else if(line.equalsIgnoreCase("AtaqueDerecha:"))
				{
					line = bfr.readLine();
					String[] rutas =  line.split(",");
					for (int i = 0; i < rutas.length; i++) {
						ataqueDerecha.agregarFrame(rutaFrames+rutas[i]);
					}
				}else if(line.equalsIgnoreCase("AtaqueIzquierda:"))
				{
					line = bfr.readLine();
					String[] rutas =  line.split(",");
					for (int i = 0; i < rutas.length; i++) {
						ataqueIzquierda.agregarFrame(rutaFrames+rutas[i]);
					}
				}
			}
			line = bfr.readLine();
		}
		
		setGladiador(new PersonajePrincipal(haciaDerecha, haciaIzquierda, haciaArriba, haciaAbajo, ataqueDerecha, ataqueIzquierda, " ", muerte));
		
	}

	public void cargarMonstruos() throws IOException
	{
		File file = new File("./data/config/monstruos.txt");
		BufferedReader bfr = new BufferedReader(new FileReader(file));
		String line = " ";
		while(!line.equalsIgnoreCase("JefeIzquierda1.png,JefeIzquierda2.png,JefeIzquierda3.png"))
		{
			Animacion haciaAbajo = new Animacion(),haciaDerecha= new Animacion(),haciaIzquierda= new Animacion(),haciaArriba= new Animacion();
			int nivel;
			String rutaCarpeta;
			String[] rutaImagen;
			
			
			line = bfr.readLine(); //Es comentado
			line = bfr.readLine(); //Es el valor del nivel
			nivel = Integer.parseInt(line);
			line = bfr.readLine(); //Otro comentario
			line = bfr.readLine(); //es la ruta de la carpeta
			rutaCarpeta = line;
			line = bfr.readLine(); //Otro comentario
			line = bfr.readLine(); //son las rutas de las imagenes de la animacion hacia abajo
			rutaImagen = line.split(",");
			haciaAbajo.agregarFrame(rutaCarpeta+rutaImagen[0]);haciaAbajo.agregarFrame(rutaCarpeta+rutaImagen[1]);haciaAbajo.agregarFrame(rutaCarpeta+rutaImagen[2]);
			line = bfr.readLine(); //Otro comentario
			line = bfr.readLine(); //son las rutas de las imagenes de la animacion hacia arriba
			rutaImagen = line.split(",");
			haciaArriba.agregarFrame(rutaCarpeta+rutaImagen[0]);haciaArriba.agregarFrame(rutaCarpeta+rutaImagen[1]);haciaArriba.agregarFrame(rutaCarpeta+rutaImagen[2]);
			line = bfr.readLine(); //Otro comentario
			line = bfr.readLine(); //son las rutas de las imagenes de la animacion hacia Derecha
			rutaImagen = line.split(",");
			haciaDerecha.agregarFrame(rutaCarpeta+rutaImagen[0]);haciaDerecha.agregarFrame(rutaCarpeta+rutaImagen[1]);haciaDerecha.agregarFrame(rutaCarpeta+rutaImagen[2]);
			line = bfr.readLine(); //Otro comentario
			line = bfr.readLine(); //son las rutas de las imagenes de la animacion hacia Izquierda
			rutaImagen = line.split(",");
			haciaIzquierda.agregarFrame(rutaCarpeta+rutaImagen[0]);haciaIzquierda.agregarFrame(rutaCarpeta+rutaImagen[1]);haciaIzquierda.agregarFrame(rutaCarpeta+rutaImagen[2]);
			
			if(nivel<=4)
			{
				monstruos.add(new MonstruoNormal(haciaDerecha, haciaIzquierda, haciaArriba, haciaAbajo, nivel));
			}
			else
			{
				jefe = new MonstruoJefe(haciaDerecha, haciaIzquierda, haciaArriba, haciaAbajo, haciaDerecha, haciaIzquierda);
				monstruos.add(jefe);
				 
			}
		}
		
	}
	
	public ArrayList<PersonajeMonstruo> ordenarMonstruosMayorMenor()
	{
		ArrayList<PersonajeMonstruo> monstruos = (ArrayList<PersonajeMonstruo>) this.monstruos.clone();
		
		for (int i = 0; i < monstruos.size()-1; i++) {
			int posEncontrado =i;
			PersonajeMonstruo encontrado = monstruos.get(i);
			for (int j = i+1; j < monstruos.size(); j++) {
				if(monstruos.get(j).compararPorNivel(encontrado))
				{
					posEncontrado = j;
					encontrado = monstruos.get(j);
				}
			}
			PersonajeMonstruo temp = monstruos.get(i);
			monstruos.set(i, encontrado);
			monstruos.set(posEncontrado, temp);
		}
		
		return monstruos;
	}

	public PersonajeMonstruo buscarPersonajePorNivel(int nivel) throws NoEncontradoExcepcion
	{
		ArrayList<PersonajeMonstruo> monstruos1 = ordenarMonstruosMayorMenor();
		int inicio= 0;
		int fin = monstruos1.size()-1;
		int medio = (monstruos1.size()-1)/2;
		PersonajeMonstruo  buscado = null;
		boolean encontrado = false;
		
		while(inicio<= fin && !encontrado)
		{
			if(nivel == monstruos1.get(medio).getNivel())
			{
				encontrado = true;
				buscado = monstruos1.get(medio);
			}else if(nivel<monstruos1.get(medio).getNivel())
			{
				inicio = medio+1;
				medio = (inicio+fin)/2;
			}else if(nivel>monstruos1.get(medio).getNivel())
			{
				fin = medio-1;
				medio = (inicio+fin)/2;
			}
		}
			if(buscado == null)
			{
				throw new NoEncontradoExcepcion("El monstruo con el nivel: "+ nivel+ " no se ha encontrado",nivel);
			}
			
		return buscado;
	}

	public void agregarPuntuacion()
	{
		arbolPuntuaciones.agregarElemento(new Puntuacion(gladiador.getNombre(), gladiador.getScore(),new Date()));
		try {
			guardarPuntuacion();
		} catch (IOException e) {
			System.out.print("No se pudo guardar");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Puntuacion> darPuntuacionesRankeadas() throws SinPuntajesExcepcion
	{
		return arbolPuntuaciones.getPuntuacionesOrdenadas();
	}
	
	public void cargarPuntuacion() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectInputStream entrada=new ObjectInputStream(new FileInputStream("./data/config/puntuaciones.obj"));
		this.arbolPuntuaciones = (ArbolPuntuacion) entrada.readObject();
	}
	
	public void guardarPuntuacion() throws FileNotFoundException, IOException
	{
		ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream("./data/config/puntuaciones.obj"));
	    salida.writeObject(arbolPuntuaciones);
	    salida.close();
	}
	
	public PersonajePrincipal getGladiador() {
		return gladiador;
	}
	

	public void setGladiador(PersonajePrincipal gladiador) {
		this.gladiador = gladiador;
	}

	public MonstruoJefe getJefe() {
		return jefe;
	}

	public void setJefe(MonstruoJefe jefe) {
		this.jefe = jefe;
	}

	public ArrayList<PersonajeMonstruo> getMonstruos() {
		return monstruos;
	}

	public void setMonstruos(ArrayList<PersonajeMonstruo> monstruos) {
		this.monstruos = monstruos;
	}
	

	public void iniciarJuego()
	{
		setEstadoDuelo(ESTADO_EN_CURSO);
	}
	
	public void finalizarJuego()
	{
		setEstadoDuelo(ESTADO_CONCLUIDO);
	}

	public String getEstadoDuelo() {
		return estadoDuelo;
	}

	public void setEstadoDuelo(String estadoDuelo) {
		this.estadoDuelo = estadoDuelo;
	}
}
