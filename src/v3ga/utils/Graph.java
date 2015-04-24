package v3ga.utils;

import java.util.ArrayList;

import processing.core.PApplet;

public class Graph extends ArrayList
{

	PApplet applet;
	
	  float maxValue, minValue;
	  boolean watching;
	  int recent = 10;
	  int minAdapt = 2;
	  int maxAdapt = 100;

	  String name;
	  int r,g,b;

	  public Graph(PApplet p, String name) 
	  {
		this.applet = p;
	    this.name = name;
	    this.watching = true;
	    this.maxValue = Float.NEGATIVE_INFINITY;
	    this.minValue = Float.POSITIVE_INFINITY;
	  }
	  
	  public void setColor(int r, int g, int b)
	  {
		  this.r = r;
		  this.g = g;
		  this.b = b;
	  }
	  
	  public void add(float value) 
	  {
	    if(watching) {
	      if(value == Float.NEGATIVE_INFINITY ||
	        value == Float.POSITIVE_INFINITY ||
	        value != value)
	        return;
	      if(value > maxValue)
	        maxValue = value;
	      if(value < minValue)
	        minValue = value;
	    }
	    super.add(value);
	  }
	  public float getFloat(int i) {
	    if(size() == 0)
	      return 0;
	    return ((Float) super.get(i)).floatValue();
	  }
	  public float getLastFloat() {
	    return getFloat(size() - 1);
	  }
	  float normalize(float x) {
	    return PApplet.constrain(PApplet.norm(x, minValue, maxValue), 0, 1);
	  }
	  float getNorm(int i) {
	    return normalize(getFloat(i));
	  }
	  float getLastNorm() {
	    return getNorm(size() - 1);
	  }
	  float getLinear(int i) {
	    return PApplet.sqrt(1.0f / getNorm(i));
	  }
	  float getLastLinear() {
	    return getLinear(size() - 1);
	  }
	  float mean() {
	    float sum = 0;
	    for(int i = 0; i < size(); i++)
	      sum += getFloat(i);
	    return sum / size();
	  }
	  float recentMean() {
	    float mean = 0;
	    int n = PApplet.min(size(), recent);
	    for(int i = 0; i < n; i++)
	      mean += getFloat(size() - i - 1);
	    return mean / n;
	  }
	  float recentVarianceWeighted() {
	    float mean = recentMean();
	    float recentVariance = 0;
	    int n = PApplet.min(size(), recent);
	    float weights = 0;
	    for(int i = 0; i < n; i++) {
	      float w = 1.0f - ((float) i / (float) n);
	      recentVariance += PApplet.abs(getFloat(size() - i - 1) - mean) * w;
	      weights += w;
	    }
	    return recentVariance / weights;
	  }
	  
	  float recentAdaptive(float adapt) {
	    float sum = 0;
	    float weights = 0;
	    float curRecent = PApplet.map(adapt, 0, 1, minAdapt, maxAdapt);
	    int n = PApplet.min(size(), 1 + (int) curRecent);
	    for(int i = 0; i < n; i++) {
	      float w = 1.0f - ((float) i / (float) n);
	      sum += getFloat(size() - i - 1) * w;
	      weights += w;
	    }
	    PApplet.println(sum + " " + weights + " " + n);
	    return sum / weights;
	  }
	  public void draw(int x, int y, int width, int height) {
		applet.pushMatrix();
		applet.translate(x, y);
		applet.fill(getNorm(size() - 1) * 255);
	    //rect(0, 0, width, height);
	    
	    applet.fill(this.r,this.g,this.b);
	    applet.stroke(this.r,this.g,this.b);
	    
	    applet.textAlign(PApplet.LEFT, PApplet.CENTER);
	    applet.text(PApplet.nf(getLastFloat(), 0, 0) + " " + name, 10, height - normalize(recentMean()) * height);
	    
	    applet.textAlign(PApplet.LEFT, PApplet.TOP);
	    applet.text(PApplet.nf(minValue, 0, 0), width - 20, height - 20);
	    
	    applet.noFill();
	    applet.beginShape();
	    applet.vertex(0, height);
	    for(int i = 0; i < width && i < size(); i++) {
	      int position = size() - i - 1;
	      applet.vertex(i, height - getNorm(position) * height);
	    }
	    applet.vertex(width, height);
	    applet.endShape();
	    
	    applet.fill(this.r,this.g,this.b);
	    applet.textAlign(PApplet.LEFT, PApplet.BOTTOM);
	    applet.text(PApplet.nf(maxValue, 0, 0), width - 20, 20);
	    applet.popMatrix();
	  }
}	  