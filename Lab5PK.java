package codesPK280;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.vecmath.*;

public class Lab5PK extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static final int OBJ_NUM = 8;

	/* a function to build the content branch */
	public static BranchGroup create_Scene() {
		BranchGroup sceneBG = new BranchGroup();           // create the scene' BranchGroup
		TransformGroup sceneTG = new TransformGroup();     // create the scene's TransformGroup

		Lab5ShapesPK[] lab5Shapes = new Lab5ShapesPK[OBJ_NUM];
		lab5Shapes[0] = new FrameShape4();
		lab5Shapes[1] = new Base2();
		lab5Shapes[2] = new CylinderShape2();
		lab5Shapes[3] = new SphereShape2();
		lab5Shapes[4] = new StringShape6("PK's Lab5");
		lab5Shapes[5] = new BoxShape2();
		lab5Shapes[6] = new Sphere2();
		lab5Shapes[7] = new Box2();

		
		for (int i = 4; i < OBJ_NUM; i++) {
			sceneTG.addChild(lab5Shapes[i].position_Object());
		}
		sceneBG.addChild(CommonsPK.add_Lights(CommonsPK.White, 1));	
		sceneBG.addChild(CommonsPK.rotate_Behavior(20000, sceneTG));	
		sceneBG.addChild(sceneTG);                         // make 'sceneTG' continuous rotating
		sceneBG.addChild(lab5Shapes[0].position_Object());
		sceneBG.addChild(lab5Shapes[1].position_Object());
		sceneBG.addChild(lab5Shapes[2].position_Object());
		sceneBG.addChild(lab5Shapes[3].position_Object());
		return sceneBG;
	}
	
	
	/* NOTE: Keep the constructor for each of the labs and assignments */
	public Lab5PK(BranchGroup sceneBG) {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);
		
		SimpleUniverse su = new SimpleUniverse(canvas);    // create a SimpleUniverse
		CommonsPK.define_Viewer(su, new Point3d(4.0d, 0.0d, 1.0d));
		
		sceneBG.addChild(CommonsPK.key_Navigation(su));     // allow key navigation
		sceneBG.compile();		                           // optimize the BranchGroup
		su.addBranchGraph(sceneBG);                        // attach the scene to SimpleUniverse

		setLayout(new BorderLayout());
		add("Center", canvas);
		frame.setSize(800, 800);                           // set the size of the JFrame
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		frame = new JFrame("PK's Lab5");                   
		frame.getContentPane().add(new Lab5PK(create_Scene()));  // create an instance of the class
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
