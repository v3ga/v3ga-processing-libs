package v3ga;


import processing.core.*;

public class Library {
  PApplet parent;

  public Library(PApplet parent) 
  {
    this.parent = parent;
    parent.registerMethod("dispose", this);
  }

  public void dispose() {
    // Anything in here will be called automatically when 
    // the parent sketch shuts down. For instance, this might
    // shut down a thread used by this library.
  }
}

