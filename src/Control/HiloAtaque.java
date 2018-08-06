package Control;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class HiloAtaque  extends Thread{
	
	private URL url;
	
	public HiloAtaque()
	{
		this.url = HiloAtaque.class.getResource("Ataque.wav");
		
	}
	

	public void run()
	{
		AudioClip clip = Applet.newAudioClip(url);
		clip.play();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
