package codesPK280;

import java.awt.Font;

import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.*;
import org.jogamp.java3d.utils.image.TextureLoader;
import org.jogamp.vecmath.*;

public abstract class Lab5ShapesPK{
	protected abstract Node create_Object();	           // use 'Node' for both Group and Shape3D
	public abstract Node position_Object();
}

class StringShape6 extends Lab5ShapesPK {
    private TransformGroup objTG;                              // use 'objTG' to position an object
    private String str;
    public StringShape6(String str_ltrs) {
        str = str_ltrs;        
        Transform3D scaler = new Transform3D();
        Vector3d v3d = new Vector3d(-0.1,0.10,-0.11);      //positioning the string on the self made pyramid
        scaler.setScale(v3d);                              // scaling 4x4 matrix 
        objTG = new TransformGroup(scaler);
        objTG.addChild(create_Object());           // apply scaling to change the string's size
    }
    protected Node create_Object() {
        Font my2DFont = new Font("Arial", Font.PLAIN, 1);  // font's name, style, size
        FontExtrusion myExtrude = new FontExtrusion();
        Font3D font3D = new Font3D(my2DFont, myExtrude);        

        Point3f pos = new Point3f(-4.0f, 6.25f, 1.05f);// position for the string 
        Text3D text3D = new Text3D(font3D, str, pos);      // create a text3D object
        Appearance appear = new Appearance();
        ColoringAttributes clr_a = new ColoringAttributes(new Color3f(1.0f, 1.0f, 1.0f), ColoringAttributes.SHADE_FLAT);  //adding white color to string
        appear.setColoringAttributes(clr_a);
        
        return new Shape3D(text3D, appear);
    }
    public Node position_Object() {
        return objTG;
    }
}

class CylinderShape2 extends Lab5ShapesPK{
	private TransformGroup objTG;
	public CylinderShape2() {
		Transform3D trfm = new Transform3D();
		objTG = new TransformGroup(trfm);                  // set the combined transformation
	
		objTG.addChild(create_Object());
	}
	protected Node create_Object() {
		return new Cylinder(0.12f,1.0f,Cylinder.GENERATE_NORMALS, 30, 30, CommonsPK.obj_Appearance(CommonsPK.Orange));
	}
	public Node position_Object(){
		return objTG;
	}
}

class SphereShape2 extends Lab5ShapesPK{
	private TransformGroup objTG;
	public SphereShape2() {
		Transform3D trfm = new Transform3D();
		trfm.setTranslation(new Vector3f(0f, 0.5f, 0f));
		objTG = new TransformGroup(trfm);                  // set the combined transformation
	
		objTG.addChild(create_Object());
	}
	
	protected Node create_Object() {
		Appearance appear = new Appearance();
		ColoringAttributes clr_a = new ColoringAttributes(CommonsPK.Red, ColoringAttributes.SHADE_FLAT);
        appear.setColoringAttributes(clr_a);

		return new Sphere(0.12f,Sphere.GENERATE_NORMALS, 30, appear);
	}
	
	public Node position_Object(){
		return objTG;
	}
}

class BoxShape2 extends Lab5ShapesPK{
	private TransformGroup objTG;
	public BoxShape2() {
		Transform3D trfm = new Transform3D();
		trfm.setTranslation(new Vector3f(0.16f, 0.68f, 0f));
		objTG = new TransformGroup(trfm);                  // set the combined transformation
	
		objTG.addChild(create_Object());
	}
	protected Node create_Object() {
		Appearance appear = new Appearance();
		ColoringAttributes clr_a = new ColoringAttributes(CommonsPK.Cyan, ColoringAttributes.SHADE_FLAT);
        appear.setColoringAttributes(clr_a);

		return new Box(0.26f, .06f, 0.12f, Box.GENERATE_NORMALS, appear);
	}
	public Node position_Object(){
		return objTG;
	}
}

class Sphere2 extends Lab5ShapesPK{
	private TransformGroup objTG;
	public Sphere2() {
		Transform3D trfm = new Transform3D();
		trfm.setTranslation(new Vector3f(0.41f, 0.68f, 0f));
		objTG = new TransformGroup(trfm);                  // set the combined transformation
	
		objTG.addChild(create_Object());
	}
	protected Node create_Object() {
		Appearance appear = new Appearance();
		ColoringAttributes clr_a = new ColoringAttributes(CommonsPK.Red, ColoringAttributes.SHADE_FLAT);
        appear.setColoringAttributes(clr_a);

		return new Sphere(0.06f,Sphere.GENERATE_NORMALS, 30, appear);
	}
	public Node position_Object(){
		return objTG;
	}
}

class Box2 extends Lab5ShapesPK{
    private TransformGroup objTG;
    public Box2() {
        Transform3D trfm = new Transform3D();
        trfm.setTranslation(new Vector3f(0.47f, 0.68f, 0f));
        objTG = new TransformGroup(trfm);                  // set the combined transformation
    
        objTG.addChild(create_Object());               // set the combined transformation
    
        objTG.addChild(create_Object());
    }
    protected Node create_Object() {
        Appearance appear = new Appearance();
        ColoringAttributes clr_a = new ColoringAttributes(CommonsPK.Magenta, ColoringAttributes.SHADE_FLAT);
        appear.setColoringAttributes(clr_a);
        
        Box box = new Box(0.01f, 0.06f, 0.5f, Box.GENERATE_NORMALS, appear);
        TransformGroup tg = new TransformGroup();
        tg.addChild(box);
                        //Making the fan rotate in Z axis
        Transform3D trans3d = new Transform3D();
        trans3d.rotZ(-Math.PI/2.0);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha alpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, 5000, 0, 0, 0, 0, 0);
        RotationInterpolator rotator = new RotationInterpolator(alpha, tg, trans3d, 0, (float) Math.PI*2.0f);
        
        rotator.setSchedulingBounds(new BoundingSphere(new Point3d(0.0f,0.0f, 0.0f), 100.0));
        
        tg.addChild(rotator);       
        return tg;
    }
    public Node position_Object(){
        return objTG;
    }
}

class Base2 extends Lab5ShapesPK{
	private TransformGroup objTG;
	public Base2() {
		Transform3D trfm = new Transform3D();
		trfm.setTranslation(new Vector3f(0f, -0.55f, -0f));
		objTG = new TransformGroup(trfm);                  // set the combined transformation
	
		objTG.addChild(create_Object());
	}
	private Texture setTexture() {
		String filename = "src/codesPK280/images.jfif";
		TextureLoader loader = new TextureLoader(filename, null);
		ImageComponent2D image = loader.getImage();
		if(image==null) {
			System.out.println("load failed for texture:"+filename);
		}
	    Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,image.getWidth(), image.getHeight());
	    texture.setImage(0, image);
	    return texture;
	}
	protected Node create_Object() {
		Appearance appear = new Appearance();
		ColoringAttributes clr_a = new ColoringAttributes(CommonsPK.White, ColoringAttributes.SHADE_FLAT);
        appear.setColoringAttributes(clr_a);
        
        TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.FASTEST,0.5f);//transparent base
        appear.setTransparencyAttributes(ta);
        
        appear.setTexture(setTexture());
        
        TextureAttributes text_a = new TextureAttributes();
        text_a.setTextureMode(TextureAttributes.REPLACE);
        appear.setTextureAttributes(text_a);
        
        TexCoordGeneration tcg = new TexCoordGeneration(TexCoordGeneration.SPHERE_MAP,TexCoordGeneration.TEXTURE_COORDINATE_3 );
        appear.setTexCoordGeneration(tcg);
        
        
		return new Box(0.5f, 0.04f, 0.5f, Box.GENERATE_NORMALS, appear);
	}
	public Node position_Object(){
		return objTG;
	}
}

class FrameShape4 extends Lab5ShapesPK {
	protected Node create_Object() {
		LineArray lineArr = new LineArray(2, LineArray.COLOR_3 | LineArray.COORDINATES);
		lineArr.setCoordinate(0, new Point3f(0f,0f,1f));
		lineArr.setCoordinate(1, new Point3f(0f, 0f, -1f));
		lineArr.setColor(1, CommonsPK.Red);
		
		LineArray lineArr1 = new LineArray(2, LineArray.COLOR_3 | LineArray.COORDINATES);
		lineArr1.setCoordinate(0, new Point3f(-1f, 0f, 0f));
		lineArr1.setCoordinate(1, new Point3f(1f, 0f, 0f));
		lineArr1.setColor(1, CommonsPK.Yellow);
		
		LineArray lineArr2 = new LineArray(2, LineArray.COLOR_3 | LineArray.COORDINATES);
		lineArr2.setCoordinate(0, new Point3f(0f, -1f, 0f));
		lineArr2.setCoordinate(1, new Point3f(0f, 1f, 0f));
		lineArr2.setColor(1, CommonsPK.Green);
		
		Shape3D x = new Shape3D(lineArr);
		x.addGeometry(lineArr1);
		x.addGeometry(lineArr2);

		return x;
	}
	public Node position_Object() {
		return create_Object();
	}
}