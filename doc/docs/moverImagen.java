package ejemplo;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

public class moverImagen extends JFrame implements KeyListener, MouseListener {

	private int x = 0, y = 0;

	private Color colorGuardado;

	public moverImagen() {

		super("Ejemplo Dibujo");
		setSize(400, 400);

		addKeyListener(this);
		addMouseListener(this);
		setBackground(Color.white);
		setVisible(true);

	}

	public void paint(Graphics g) {

		// Image imagen1 = Toolkit.getDefaultToolkit().getImage("./imagenes/fondo.jpg");
		// g.drawImage(imagen1, 0, 0, null);

		Image imagen = Toolkit.getDefaultToolkit().getImage("./imagenes/Captura1.jpg");
		g.drawImage(imagen, x, y, null);

	}

	public static void main(String args[]) {
		moverImagen aplicacion = new moverImagen();
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) // left
			x -= 10;
		if (e.getKeyCode() == 39) // right
			x += 10;
		if (e.getKeyCode() == 38) // up
			y -= 10;
		if (e.getKeyCode() == 40) // down
			y += 10;

		repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		try {
			InputStream input = new FileInputStream("./imagenes/Captura1.jpg");
			ImageInputStream imageInput = ImageIO.createImageInputStream(input);
			BufferedImage imagenL = ImageIO.read(imageInput);

			PrintWriter escritor = new PrintWriter(new File("C:\\Users\\jhonatan\\Desktop\\Secret\\Ookami Kodomo no Ame\\data\\config\\DibujoPrimitivo.cvs"));
			escritor.println("#numero de puntos:");
			escritor.println(imagenL.getHeight()*imagenL.getWidth()+"");
			escritor.println("#CoordenadaX, CoordenadaY, ColorR, ColorG, ColorB");
			for (int y = 0; y < imagenL.getHeight(); y++) {
				for (int x = 0; x < imagenL.getWidth(); x++) {

					int srcPixel = imagenL.getRGB(x, y);
					Color c = new Color(srcPixel);
					int valR = c.getRed();
					int valG = c.getGreen();
					int valB = c.getBlue();
					String mensaje = x + "," + y + "," + valR + "," + valG + "," + valB;
					escritor.println(mensaje);
					
				}
			}
			JOptionPane.showMessageDialog(this, "La acción ha finalizado");
			escritor.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}