package Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Mundo.NoEncontradoExcepcion;
import Mundo.Personajes.MonstruoJefe;
import Mundo.Personajes.MonstruoNormal;
import Mundo.Personajes.PersonajeMonstruo;


public class PanelBestiario extends JFrame implements ActionListener {

	public static final String COMANDO_BUSCAR="Buscar";
	public static final String COMANDO_REGRESAR = "Regresar";
	public static final String COMANDO_SIGUIENTE = "Siguiente";
	public static final String COMANDO_ANTERIOR = "Anterior";
	
	private PersonajeMonstruo monstruoActual;
	private InterfazPrincipal principal;
	
	private JLabel lblImagenMonstruo;
	private JLabel lblNombreMonstruo;
	
	private JLabel lblBuscar;
	private JTextField txtBuscar;
	private JButton butBuscar;
	
	private JButton butAnterior;
	private JButton butSiguiente;
	
	private JLabel lblNivel;
	private JTextField txtNivel;
	private JLabel lblVida;
	private JTextField txtVida;
	private JLabel lblDanio;
	private JTextField txtDanio;
	private JLabel lblPuntos;
	private JTextField txtPuntos;
	
	private JButton butHome;
	
	
	public PanelBestiario(InterfazPrincipal principal, PersonajeMonstruo actual)
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.monstruoActual = actual;
		setTitle("BESTIARIO");
		setLocationRelativeTo(principal);
		
		lblImagenMonstruo = new JLabel();
		lblImagenMonstruo.setIcon(new ImageIcon(actual.getCaminarHaciaAbajo().getPrimero().getRutaImagen()));
		lblNombreMonstruo = new JLabel(" ");
		
		if(actual instanceof MonstruoJefe) {
			MonstruoJefe m = (MonstruoJefe) actual;
			lblNombreMonstruo.setText(m.getNombre());
		}
		
		lblBuscar = new JLabel("Buscar por nivel");
		txtBuscar = new JTextField();
			
		
		lblNivel = new JLabel("Nivel :");
		txtNivel = new JTextField(actual.getNivel()+"");
		txtNivel.setEnabled(false);
		
		lblVida = new JLabel("Vida :");
		txtVida = new JTextField(actual.getVida()+"");
		txtVida.setEnabled(false);
		
		lblDanio = new JLabel("Daño :");
		txtDanio = new JTextField(actual.getDaño()+"");
		txtDanio.setEnabled(false);
		
		lblPuntos = new JLabel("Puntos :");
		txtPuntos = new JTextField(actual.getScore()+"");
		txtPuntos.setEnabled(false);
		
		
		this.principal = principal;
		
		this.butBuscar = new JButton("Buscar Monstruo por nivel");
		butBuscar.addActionListener(this);
		butBuscar.setActionCommand(COMANDO_BUSCAR);
		
		this.butHome = new JButton("Regresar");
		butHome.addActionListener(this);
		butHome.setActionCommand(COMANDO_REGRESAR);
		
		this.butAnterior = new JButton("<");
		butAnterior.addActionListener(this);
		butAnterior.setActionCommand(COMANDO_ANTERIOR);
		
		this.butSiguiente	 = new JButton(">");
		butSiguiente.addActionListener(this);
		butSiguiente.setActionCommand(COMANDO_SIGUIENTE);
		
		setLayout(new BorderLayout(40,40));
		
		JPanel auxi1 = new JPanel();
		auxi1.setLayout(new BorderLayout());
		auxi1.add(lblNombreMonstruo, BorderLayout.NORTH);
		auxi1.add(lblImagenMonstruo,BorderLayout.CENTER);
		auxi1.add(butAnterior,BorderLayout.WEST);
		auxi1.add(butSiguiente,BorderLayout.EAST);
		add(auxi1,BorderLayout.WEST);
		
		
		JPanel auxi2 = new JPanel();
		auxi2.setLayout(new BorderLayout());
		
		JPanel auxiArriba = new JPanel();
		auxiArriba.setLayout(new GridLayout(2, 1));
		auxiArriba.add(lblBuscar);
		JPanel anonimo = new JPanel();
		anonimo.setLayout(new GridLayout(1, 2));
		anonimo.add(txtBuscar);
		anonimo.add(butBuscar);
		auxiArriba.add(anonimo);
		auxi2.add(auxiArriba, BorderLayout.NORTH);
		
		JPanel auxiCentro = new JPanel();
		auxiCentro.setBorder(new TitledBorder("Estadisticas:"));
		auxiCentro.setLayout(new GridLayout(4, 2));
		auxiCentro.add(lblNivel);
		auxiCentro.add(txtNivel);
		auxiCentro.add(lblVida);
		auxiCentro.add(txtVida);
		auxiCentro.add(lblDanio);
		auxiCentro.add(txtDanio);
		auxiCentro.add(lblPuntos);
		auxiCentro.add(txtPuntos);
		auxi2.add(auxiCentro, BorderLayout.CENTER);
		auxi2.add(butHome, BorderLayout.SOUTH);
		
		add(auxi2, BorderLayout.CENTER);
		pack();
	}
	
	public void regresar()
	{
		this.setVisible(false);
		principal.setVisible(true);
	}
	
	public void refrescar(PersonajeMonstruo nuevo)
	{
		txtNivel.setText(nuevo.getNivel()+"");
		txtVida.setText(nuevo.getVida()+"");
		txtDanio.setText(nuevo.getDaño()+"");
		txtPuntos.setText(nuevo.getScore()+"");
		lblImagenMonstruo.setIcon(new ImageIcon(nuevo.getCaminarHaciaAbajo().getPrimero().getRutaImagen()));
		if(nuevo instanceof MonstruoJefe)
		{
			MonstruoJefe n = (MonstruoJefe) nuevo;
			lblNombreMonstruo.setText(n.getNombre());
		}else
		{
			lblNombreMonstruo.setText(" ");
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equalsIgnoreCase(COMANDO_REGRESAR))
		{
			regresar();
		}
		if(arg0.getActionCommand().equalsIgnoreCase(COMANDO_BUSCAR))
		{
			try {
				int valor = Integer.parseInt(txtBuscar.getText());
				PersonajeMonstruo nuevo = principal.buscarPersonajePorNivel(valor);
				refrescar(nuevo);
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Digite un valor numerico");
			}catch(NoEncontradoExcepcion e)
			{
				JOptionPane.showMessageDialog(this, e.getMessage(),"Información", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("./data/iconos/iconoInformacion.jpg") );
			}	
		}
		if(arg0.getActionCommand().equalsIgnoreCase(COMANDO_SIGUIENTE))
		{
			try {
				int valor = monstruoActual.getNivel()-1;
				if(valor==0) valor=5;
				monstruoActual = principal.buscarPersonajePorNivel(valor);
				refrescar(monstruoActual);
			}catch(NoEncontradoExcepcion e)
			{
				JOptionPane.showMessageDialog(this, e.getMessage(),"Información", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("./data/iconos/iconoInformacion.jpg") );
			}	
		}
		if(arg0.getActionCommand().equalsIgnoreCase(COMANDO_ANTERIOR))
		{
			try {
				int valor = monstruoActual.getNivel()+1;
				if(valor==6)valor=0;
				
				monstruoActual = principal.buscarPersonajePorNivel(valor);
				refrescar(monstruoActual);
			}catch(NoEncontradoExcepcion e)
			{
				JOptionPane.showMessageDialog(this, e.getMessage(),"Información", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("./data/iconos/iconoInformacion.jpg") );
			}	
		}
		
	}
}

