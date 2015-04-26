import v3ga.sound.*;
import ddf.minim.*;

Sound sound;

void setup()
{
  sound = Sound.load(this, "Processing.wav");
}

void mousePressed()
{
  sound.play();
}


