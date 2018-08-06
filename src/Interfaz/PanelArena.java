package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectInputValidation;
import java.security.Key;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Control.HiloAnimacionCaminar;
import Control.HiloAnimacionCaminarMonstruos;
import Control.HiloCronometro;
import Mundo.Interfaces.Caminar;
import Mundo.Personajes.PersonajePrincipal;

public class PanelArena extends JPanel implements KeyListener, MouseListener {

	public static final String TIEMPO = "Tiempo: ";
	
	private InterfazPrincipal principal;
	private String teclaPresionada;
	private HiloAnimacionCaminar hiloCaminarPersonaje;
	private HiloAnimacionCaminarMonstruos hiloCaminarMonstruos;
	private HiloCronometro hiloCronometro;
	private String StringMinuto;
	private String StringSegundo;
	private int minuto;
	private int segundo;
	private JLabel lblTiempo;
	private JLabel lblVidaPersonaje;
	private JLabel lblVidaJefe;
	
	public PanelArena(InterfazPrincipal principal) {
		this.principal = principal;
		addKeyListener(this);
		setFocusable(true);
		addMouseListener(this);
		teclaPresionada = " ";
		StringMinuto = "00";
		StringSegundo = "00";
		hiloCaminarPersonaje = new HiloAnimacionCaminar(null, null, null, null);
		lblTiempo = new JLabel(TIEMPO+StringMinuto+ ":"+StringSegundo);
		lblTiempo.setFont(new Font("", 20, 20));
		lblVidaPersonaje = new JLabel("Tu vida: "+principal.mundo.getGladiador().getVida());
		lblVidaPersonaje.setFont(new Font("", 20, 20));
		lblVidaJefe = new JLabel("Jefe: " +principal.mundo.getJefe().getVida());
		lblVidaJefe.setFont(new Font("", 20, 20));
		add(lblTiempo);
		add(lblVidaPersonaje);
		add(lblVidaJefe);
	}

	public void pedirFoco()
	{
		requestFocusInWindow();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("./data/config/se.png"), 0, 0, this);
		
		Graphics2D g2d = (Graphics2D) g;

		principal.mundo.getGladiador().paint(g2d);
		principal.mundo.getJefe().paint(g2d);
	}

	public void setTiempo(int segundo, int minuto)
	{
		
		if(segundo<10) {
			this.StringSegundo = "0"+segundo;
		}else
		{
			this.StringSegundo = segundo+"";
		}
		if(minuto<10)
		{
			this.StringMinuto = "0"+minuto;
		}else
		{
			this.StringMinuto = minuto+"";
		}
		this.minuto = minuto;
		this.segundo = segundo;
		String mensaje = TIEMPO+StringMinuto+":"+StringSegundo;
		lblTiempo.setText(TIEMPO+StringMinuto+ ":"+StringSegundo);
	}
	
	public void setLabelVida(int vida)
	{
		lblVidaPersonaje.setText("Tu vida: "+vida);
		
	}
	
	public void setLabelVidaJefe(int vida)
	{
		lblVidaJefe.setText("Jefe: " +vida);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		String presionado = KeyEvent.getKeyText(e.getKeyCode());
		if(hiloCaminarPersonaje!= null) {
			
			if(!hiloCaminarPersonaje.isAlive()) {
		//Si la tecla presionada es:
			
			
		//--------------------------------------------
		//----------------------TECLA W---------------
		//--------------------------------------------
		//W para ir arriba
		if(presionado.equalsIgnoreCase("W"))
		{
			//Si la tecla presionada no es igual a la tecla presionada anteriormente, esto es solo indica que el personaje girará
			//Esto con el proposito de que si la persona solo unde una vez la tecla, pueda girarse y atacar sin tener que moverse.
			if(!teclaPresionada.equalsIgnoreCase(presionado) ) {
				teclaPresionada = "W";
				principal.mundo.getGladiador().girar(Caminar.DIRECCION_ARRIBA);
				hiloCaminarPersonaje = new HiloAnimacionCaminar(principal.mundo.getGladiador().getCaminarHaciaArriba(),this, principal.mundo.getGladiador(),teclaPresionada);
				hiloCaminarPersonaje.start();
			} //En caso contrario, sí la persona desea repetir la accion con la misma letra entonces se procede a moverse
			else
			{
				hiloCaminarPersonaje = new HiloAnimacionCaminar(principal.mundo.getGladiador().getCaminarHaciaArriba(),this, principal.mundo.getGladiador(),teclaPresionada);
				hiloCaminarPersonaje.start();
			}
		}
		
		//--------------------------------------------
		//----------------------TECLA S---------------
		//--------------------------------------------
		//S para ir abajo
		if(presionado.equalsIgnoreCase("S"))
		{//Si la tecla presionada no es igual a la tecla presionada anteriormente, esto es solo indica que el personaje girará
			//Esto con el proposito de que si la persona solo unde una vez la tecla, pueda girarse y atacar sin tener que moverse.
			if(!teclaPresionada.equalsIgnoreCase(presionado) ) {
				teclaPresionada = "S";
				principal.mundo.getGladiador().girar(Caminar.DIRECCION_ABAJO);
				hiloCaminarPersonaje = new HiloAnimacionCaminar(principal.mundo.getGladiador().getCaminarHaciaAbajo(),this, principal.mundo.getGladiador(),teclaPresionada);
				hiloCaminarPersonaje.start();
			} //En caso contrario, sí la persona desea repetir la accion con la misma letra entonces se procede a moverse
			else
			{
				hiloCaminarPersonaje = new HiloAnimacionCaminar(principal.mundo.getGladiador().getCaminarHaciaAbajo(),this, principal.mundo.getGladiador(),teclaPresionada);
				hiloCaminarPersonaje.start();
			}
			
		}
		//--------------------------------------------
		//----------------------TECLA A---------------
		//--------------------------------------------
		//A para ir a la derecha
		if(presionado.equalsIgnoreCase("A"))
		{//Si la tecla presionada no es igual a la tecla presionada anteriormente, esto es solo indica que el personaje girará
			//Esto con el proposito de que si la persona solo unde una vez la tecla, pueda girarse y atacar sin tener que moverse.
			if(!teclaPresionada.equalsIgnoreCase(presionado) ) {
				teclaPresionada = "A";
				principal.mundo.getGladiador().girar(Caminar.DIRECCION_IZQUIERDA);
				hiloCaminarPersonaje = new HiloAnimacionCaminar(principal.mundo.getGladiador().getCaminarHaciaIzquierda(),this, principal.mundo.getGladiador(),teclaPresionada);
				hiloCaminarPersonaje.start();
			} //En caso contrario, sí la persona desea repetir la accion con la misma letra entonces se procede a moverse
			else
			{
				hiloCaminarPersonaje = new HiloAnimacionCaminar(principal.mundo.getGladiador().getCaminarHaciaIzquierda(),this, principal.mundo.getGladiador(),teclaPresionada);
				hiloCaminarPersonaje.start();

			}
		}
		
		//--------------------------------------------
		//----------------------TECLA D---------------
		//--------------------------------------------
		//D para ir a la izquierda
		if(presionado.equalsIgnoreCase("D"))
		{//Si la tecla presionada no es igual a la tecla presionada anteriormente, esto es solo indica que el personaje girará
			//Esto con el proposito de que si la persona solo unde una vez la tecla, pueda girarse y atacar sin tener que moverse.
			if(!teclaPresionada.equalsIgnoreCase(presionado) ) {
				teclaPresionada = "D";
				principal.mundo.getGladiador().girar(Caminar.DIRECCION_DERECHA);
				hiloCaminarPersonaje = new HiloAnimacionCaminar(principal.mundo.getGladiador().getCaminarHaciaDerecha(),this, principal.mundo.getGladiador(),teclaPresionada);
				hiloCaminarPersonaje.start();
			} //En caso contrario, sí la persona desea repetir la accion con la misma letra entonces se procede a moverse
			else
			{
				hiloCaminarPersonaje = new HiloAnimacionCaminar(principal.mundo.getGladiador().getCaminarHaciaDerecha(),this, principal.mundo.getGladiador(),teclaPresionada);
				hiloCaminarPersonaje.start();
				
			}
		}
		
		hiloCaminarMonstruos = new HiloAnimacionCaminarMonstruos(principal.mundo.getJefe().getAnimaciones(), this, principal.mundo.getJefe(), principal.mundo.getGladiador(), presionado);
		hiloCaminarMonstruos.start();
		
		if(presionado.equals("Espacio"))
		{
			principal.mundo.getGladiador().atacar(principal.mundo.getJefe());
		}
		repaint();
		
		if(principal.mundo.getGladiador().muerto() || principal.mundo.getJefe().muerto())
		{
			principal.mundo.getGladiador().setScore(principal.mundo.getGladiador().getScore()+minuto*segundo);
			JOptionPane.showMessageDialog(this, "El juego ha terminado, su puntaje es:"+ principal.mundo.getGladiador().getScore());
			principal.finalizarJuego();
		}
	}
  }
}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX()+ " "+ e.getY());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void iniciar() {
		hiloCronometro = new HiloCronometro(this,principal.mundo);
		hiloCronometro.start();
		
		
	}

	
}
