import v3ga.sound.*;
import ddf.minim.*;

Micro mic;

void setup()
{
  size(300,300);
  mic = new Micro(this);
}

void draw()
{
  mic.update();
  background(0);
  fill(255);
  ellipse(150,150,mic.get()*200,mic.get()*200);
}

