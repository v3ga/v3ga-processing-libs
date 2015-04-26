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


