package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelInicio extends JPanel implements ActionListener {

	//Constantes de comandos:
	public static final Dimension DIMENSION_VENTANA = new Dimension (506,523);
	public static final String COMANDO_INICIAR = "Iniciar nueva partida";
	public static final String COMANDO_PUNTUACION = "Ver Puntuacion";
	public static final String COMANDO_SALIR = "Salir";
	public static final String COMANDO_BESTIARIO = "Bestiario";
	//Dibujo con primitivas:
	private int[] puntosXPrimitivas;
	private int[] puntosYPrimitivas;
	private Color[] colorPuntoPrimitivas;
	
	
	//Atributos
	private InterfazPrincipal principal;
	private JButton butIniciarNuevo,butCagarPartida,butSalir,butBestiario;
	
	public PanelInicio(InterfazPrincipal principal, int[] puntosXPrimitivas, int[] puntosYPrimitivas, Color[] colorPuntoPrimitivas) 
	{
		setFocusable(true);
		this.puntosXPrimitivas = puntosXPrimitivas;
		this.puntosYPrimitivas = puntosYPrimitivas;
		this.colorPuntoPrimitivas = colorPuntoPrimitivas;
		
		butIniciarNuevo = new JButton("Iniciar Nueva Partida");
		butIniciarNuevo.setActionCommand(COMANDO_INICIAR);
		butIniciarNuevo.addActionListener(this);
		
		butCagarPartida = new JButton("Puntuaciones");
		butCagarPartida.setActionCommand(COMANDO_PUNTUACION);
		butCagarPartida.addActionListener(this);
		
		butSalir = new JButton("Salir");
		butSalir.setActionCommand(COMANDO_SALIR);
		butSalir.addActionListener(this);
		
		butBestiario = new JButton("Bestiario");
		butBestiario.setActionCommand(COMANDO_BESTIARIO);
		butBestiario.addActionListener(this);
		this.principal = principal;
		
		distribucionGrafica();
		
		
	}

	private void distribucionGrafica()

	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
//		gbc.weightx = 1.0;
//		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		add(butIniciarNuevo, gbc);
		
		gbc.gridx =1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
//		gbc.weightx = 1.0;
//		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		add(butCagarPartida, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
//		gbc.weightx = 1.0;
//		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		add(butBestiario, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
//		gbc.weightx = 1.0;
//		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		add(butSalir, gbc);
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase(COMANDO_INICIAR))
		{
			principal.mundo.getGladiador().setNombre(JOptionPane.showInputDialog(this,"Bienvenido Gladiador. ¿Cuál es tu nombre? ","Inicio",JOptionPane.INFORMATION_MESSAGE));
			principal.iniciarJuego();
		}if(e.getActionCommand().equalsIgnoreCase(COMANDO_BESTIARIO))
		{
			principal.monstrarBestiario();
		}if(e.getActionCommand().equalsIgnoreCase(COMANDO_SALIR))
		{
			System.exit(0);
		}if(e.getActionCommand().equalsIgnoreCase(COMANDO_PUNTUACION))
		{
			principal.monstrarPuntuaciones();
		}
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	
		for (int i = 0; i < colorPuntoPrimitivas.length; i++) 
		{
			
			g.setColor(colorPuntoPrimitivas[i]);
			g.drawLine(puntosXPrimitivas[i], puntosYPrimitivas[i], puntosXPrimitivas[i],puntosYPrimitivas[i]);
		}
	}
	

}
