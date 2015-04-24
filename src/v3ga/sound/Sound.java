package v3ga.sound;

import processing.core.PApplet;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class Sound 
{
	AudioPlayer player;

	static Minim minim;
	
	// ----------------------------------------------------
	public static Minim getMinim(PApplet p)
	{
		if (minim == null){
			minim  = new Minim(p);
		}
		return minim;
	}

	// ----------------------------------------------------
	public static Sound load(PApplet p, String filename)
	{
		return new Sound(p,filename);
	}
	
	// ----------------------------------------------------
	public Sound(PApplet p, String filename)
	{
		player = getMinim(p).loadFile(filename);
	}

	// ----------------------------------------------------
	public void play()
	{
		if (player!=null)
		{
			player.rewind();
			player.play();
		}
	}

	// ----------------------------------------------------
	public void pause()
	{
		if (player !=null)
		{
			player.pause();
		}
	}
	
	// ----------------------------------------------------
	public void setVolume(float v)
	{
		if (player !=null)
		{
			// player.setVolume(v);
			player.setGain( PApplet.map(v,0,1,-80,0) );
		}
	}
	
	// ----------------------------------------------------
	public boolean isPlaying()
	{
		if (player !=null)
		{
			return player.isPlaying();
		}
		return false;
	}
	
}
