package v3ga.utils;

import processing.core.PApplet;

public class Timer
{
  float now = 0;
  float before = 0;
  float dt = 0;
  PApplet applet;
  
  public  Timer(PApplet p)
  {
	this.applet = p;
    now = before = p.millis()/1000.0f;
  }

  public float dt()
  {
    now = applet.millis()/1000.0f;
    dt = now - before;
    before = now;
    return dt;
  }

}