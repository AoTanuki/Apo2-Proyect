package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Mundo.Interfaces.Caminar;
import Mundo.Personajes.PersonajeMonstruo;
import Mundo.Puntuaciones.Puntuacion;
import Mundo.Puntuaciones.SinPuntajesExcepcion;
import Mundo.NoEncontradoExcepcion;
import Mundo.PrincipalJuego;

public class InterfazPrincipal extends JFrame {

	private PanelInicio panelInicio;
	protected PrincipalJuego mundo;
	private PanelArena panelArena;
	private PanelBestiario panelBestiario;
	private PanelPuntuaciones panelPuntuaciones;

	public InterfazPrincipal() {
		setResizable(false);
		setTitle("Arcuz");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(PanelInicio.DIMENSION_VENTANA);

		try {
			mundo = new PrincipalJuego();
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		panelBestiario = new PanelBestiario(this, mundo.getJefe());
		panelPuntuaciones = new PanelPuntuaciones(this);

		
		panelArena = new PanelArena(this);
		// Instancia del panel de inicio:--------------------------------------
		Object[] datosprimitivas;
		try {
			datosprimitivas = mundo.cargarDibujo();
			int[] posx = (int[]) datosprimitivas[0];
			int[] posy = (int[]) datosprimitivas[1];
			Color[] colores = (Color[]) datosprimitivas[2];
			panelInicio = new PanelInicio(this, posx, posy, colores);
			panelInicio.repaint();
			add(panelInicio, BorderLayout.CENTER);
			ajustarPantalla();
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				JOptionPane.showMessageDialog(this, "El archivo no se encontró");

			} else {
				JOptionPane.showMessageDialog(this, "Hubo un error al momento de cargar el archivo");
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Hubo un error de conversion al momento de cargar la imagen");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfazPrincipal ventana = new InterfazPrincipal();
		ventana.setVisible(true);
	}

	public void ajustarPantalla() {
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) (pantalla.getWidth() - this.getWidth()) / 2,
				(int) (pantalla.getHeight() - this.getHeight()) / 2);
	}

	public void mostrarArena() {
		getContentPane().removeAll();
		setSize(Caminar.DIMENSION_TABLERO);
		add(panelArena, BorderLayout.CENTER);
		setLocation(0, 0);
		panelArena.repaint();
		panelArena.pedirFoco();
		JOptionPane.showConfirmDialog(this, "Bienvenido a Arcuz \n Para moverte hacia la derecha presiona: D"
										+ "\n para moverte hacia la izquierda presiona A" +"\n para moverte hacia la abajo presiona: S"
										+"\n para moverte hacia la arriba presiona: W"+ "\n finalmente, para atacar presiona: espacio");
		panelArena.iniciar();
	}

	public void MostrarInicio() {
		setSize(PanelInicio.DIMENSION_VENTANA);
		getContentPane().removeAll();
		add(panelInicio, BorderLayout.CENTER);
		ajustarPantalla();
	}

	public PersonajeMonstruo buscarPersonajePorNivel(int nivel) throws NoEncontradoExcepcion {

		return mundo.buscarPersonajePorNivel(nivel);

	}
	
	public void monstrarPuntuaciones()
	{
		try {
			ArrayList<Puntuacion> puntuaciones = mundo.darPuntuacionesRankeadas();
			panelPuntuaciones.setVisible(true);
			this.setVisible(false);
			panelPuntuaciones.refrescar(puntuaciones);
		} catch (SinPuntajesExcepcion e) {
			JOptionPane.showMessageDialog(this, "No hay puntuaciones de momento");
		}
	}
	
	public ArrayList<Puntuacion> puntajesRankeados() throws SinPuntajesExcepcion
	{
		return mundo.darPuntuacionesRankeadas();
	}

	public void monstrarBestiario() {
		setVisible(false);
		panelBestiario.setVisible(true);
	}
	
	public void iniciarJuego()
	{
		mundo.iniciarJuego();
		try {
			mundo.cargarMonstruos();
			mundo.cargarPersonajes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		panelArena = new PanelArena(this);
		mostrarArena();
	}
	
	public void finalizarJuego()
	{
		mundo.finalizarJuego();
		mundo.agregarPuntuacion();
		MostrarInicio();
	}
	
	
}
