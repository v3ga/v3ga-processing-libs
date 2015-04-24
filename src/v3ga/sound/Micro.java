package v3ga.sound;

import processing.core.PApplet;
import ddf.minim.*;

public class Micro 
{
	  // Object minim qui représente l'interface
	  // avec la carte audio
	  Minim minim;

	  // Object qui capte l'entrée miro
	  AudioInput input;

	  // Niveau micro
	  float level = 0.0f, levelSmooth = 0.0f;
	  float levelSmoothValue = 0.15f;
	  
	  // ----------------------------------------------------
	 public Micro(PApplet p)
	  {
	    this.setup(p);
	  }

	  // ----------------------------------------------------
	  void setup(PApplet p)
	  {
	    minim = Sound.getMinim(p);
	    input = minim.getLineIn(Minim.STEREO, 512);
	  }

	  // ----------------------------------------------------
	  public void setSmoothValue(float v)
	  {
		  levelSmoothValue = v;
	  }
	  
	  // ----------------------------------------------------
	  public void update()
	  {
	    level = input.mix.level();
	    levelSmooth += (level-levelSmooth)*levelSmoothValue;
	  }

	  // ----------------------------------------------------
	  public float get()
	  {
	    return levelSmooth;
	  }
	
}
