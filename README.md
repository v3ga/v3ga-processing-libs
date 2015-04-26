# processing libs
This library is a repository of code snippets I often use in courses or workshops. They simplify the code to access 
"not-so-direct" functionalities.

### v3ga.vision.FaceOSC
The class ([code](https://github.com/v3ga/v3ga-processing-libs/blob/master/src/v3ga/vision/FaceOSC.java) here) was made to retrieve data provided by [FaceOSC](https://github.com/kylemcdonald/ofxFaceTracker/downloads) in a simple way. It is a direct composition of Dan Shiffman [FaceOSCTriangleMesh](https://github.com/shiffman/Face-It/tree/master/FaceOSC/FaceOSCTriangleMesh) & Dan Wilcox [FaceOSCReceiverGraph](https://github.com/CreativeInquiry/FaceOSC-Templates/tree/master/processing/FaceOSCReceiverGrapher) code. 
```processing
import v3ga.vision.*;
import oscP5.*;

FaceOSC face;
void setup()
{
  size(800,600);
  face = new FaceOSC(this);
}

void draw()
{
  face.update();
  if (face.found>0)
  {
    ellipse(face.getEyeLeftPosition().x,face.getEyeLeftPosition().y,20,20);
    ellipse(face.getEyeRightPosition().x,face.getEyeRightPosition().y,20,20);
  }
}

```
### v3ga.sound.Micro
A simple class to connect to an input device ([code](https://github.com/v3ga/v3ga-processing-libs/blob/master/src/v3ga/sound/Micro.java))

```processing
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

```

### v3ga.sound.Sound
A simple class to load sounds in Processing in a PImage / PFont fashion ([code](https://github.com/v3ga/v3ga-processing-libs/blob/master/src/v3ga/sound/Sound.java))

```processing
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
```
