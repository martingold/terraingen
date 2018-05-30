import com.jogamp.opengl.util.FPSAnimator;
import model.Mesh;
import model.noise.INoiseGenerator;
import model.noise.PerlinNoiseGenerator;
import render.MeshRenderer;
import ui.CameraControls;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {

        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        final GLCanvas glcanvas = new GLCanvas(glcapabilities);

        TreeMap<Double, Color> colors = new TreeMap<>();
        colors.put(0.0, Color.BLUE);
        colors.put(0.3, Color.YELLOW);
        colors.put(0.45, Color.decode("#26c115"));
        colors.put(0.55, Color.decode("#823600"));
        colors.put(0.65, Color.decode("#4c4743"));
        colors.put(0.80, Color.decode("#CCCCCC"));
        colors.put(0.90, Color.decode("#EEEEFF"));


        int seed = (int) (Math.random() * 100000);
        int meshSize = 200;
        INoiseGenerator noiseGenerator = new PerlinNoiseGenerator(seed);
        Mesh mesh = new Mesh(meshSize, meshSize, noiseGenerator, 2.0d, 30.0);
        noiseGenerator.setSeed((int) (Math.random() * 100000));
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 4.0d, 18.0));
        noiseGenerator.setSeed((int) (Math.random() * 100000));
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 8.0d, 15.8));
        noiseGenerator.setSeed((int) (Math.random() * 100000));
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 16.0d, 12));
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 18.0d, 6));

        MeshRenderer meshRenderer = new MeshRenderer(colors);
        EventListener ev = new EventListener(meshRenderer, mesh);

        CameraControls cameraControls = new CameraControls(ev);

        glcanvas.addMouseListener(cameraControls);
        glcanvas.addMouseMotionListener(cameraControls);
        glcanvas.addKeyListener(cameraControls);
        glcanvas.addGLEventListener(ev);

        final JFrame jframe = new JFrame("Terrain generator");
        jframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                jframe.dispose();
                System.exit(0);
            }
        });

        jframe.getContentPane().add(glcanvas, BorderLayout.CENTER);
        jframe.setSize(640, 480);
        jframe.setVisible(true);

        final FPSAnimator animator = new FPSAnimator( glcanvas, 60,true);
        animator.start();

    }
}