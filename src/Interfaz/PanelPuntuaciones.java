package Interfaz;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Mundo.PrincipalJuego;
import Mundo.Puntuaciones.Puntuacion;

public class PanelPuntuaciones extends JFrame implements ActionListener {
	
	public static final String COMANDO_REGRESAR = "Regresar";
	private JButton butHome;
	
	private JList<String> lista;
	private JScrollPane scroll;
	private DefaultListModel<String>modelo;
	
	private InterfazPrincipal principal;
	private ArrayList<Puntuacion> puntuaciones;
	
	public PanelPuntuaciones(InterfazPrincipal principal) throws HeadlessException {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.principal = principal;
		this.puntuaciones = null;
		
		setLocationRelativeTo(principal);
		
		setLayout( new BorderLayout());
		lista=new JList<String>();
		modelo = new DefaultListModel<>();
		lista.setModel(modelo);
		
		butHome = new JButton("Regresar");
		butHome.setActionCommand(COMANDO_REGRESAR);
		butHome.addActionListener(this);
		
		
		scroll=new JScrollPane(lista);
		add(scroll, BorderLayout.NORTH);
		add(butHome,BorderLayout.SOUTH);
		
		pack();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase(COMANDO_REGRESAR))
		{
			this.setVisible(false);
			principal.setVisible(true);
		}
		
	}


	public void refrescar(ArrayList<Puntuacion>puntuaciones)
	{
		this.puntuaciones = puntuaciones;
		modelo.removeAllElements();
		for (int i = 0; i < puntuaciones.size(); i++) {
			String datos = puntuaciones.get(i).toString();
			modelo.addElement("Puesto: "+(i+1)+ " "+ datos);
		}
		lista.setModel(modelo);
		
	}
}
