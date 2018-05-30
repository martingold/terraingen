import com.jogamp.opengl.util.FPSAnimator;
import model.MeshGenerator;
import model.noise.INoiseGenerator;
import model.noise.PerlinNoiseGenerator;
import render.MeshRenderer;
import ui.CameraControls;
import ui.ControlOption;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TerrainGen {

    public TerrainGen() {
        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        final GLCanvas glcanvas = new GLCanvas(glcapabilities);

        TreeMap<Double, Color> colors = new TreeMap<>();
        colors.put(0.0, Color.YELLOW);
        colors.put(0.45, Color.decode("#26c115"));
        colors.put(0.55, Color.decode("#823600"));
        colors.put(0.65, Color.decode("#4c4743"));
        colors.put(0.80, Color.decode("#CCCCCC"));
        colors.put(0.90, Color.decode("#EEEEFF"));

        List<ControlOption> controls = new ArrayList<>();
        controls.add(new ControlOption("Island 'a' param", 1.0f));
        controls.add(new ControlOption("Island 'b' param", 1.0f));
        controls.add(new ControlOption("Island 'c' param", 1.0f));

        int seed = (int) (Math.random() * 100000);
        INoiseGenerator noiseGenerator = new PerlinNoiseGenerator(seed);
        MeshGenerator meshGenerator = new MeshGenerator(200, noiseGenerator);

        MeshRenderer meshRenderer = new MeshRenderer(colors);
        EventListener ev = new EventListener(meshRenderer, meshGenerator.generate(controls), meshGenerator);

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
        JPanel controlsContainer = new JPanel();
        controlsContainer.setLayout(new BoxLayout(controlsContainer, BoxLayout.PAGE_AXIS));
        for(ControlOption control : controls) {
            JLabel label = new JLabel();
            label.setText(control.getDescription());
            controlsContainer.add(label);
            controlsContainer.add(control.getSlider());
        }

        jframe.getContentPane().add(glcanvas, BorderLayout.CENTER);
        jframe.getContentPane().add(controlsContainer, BorderLayout.EAST);
        jframe.setSize(640, 480);
        jframe.setVisible(true);

        final FPSAnimator animator = new FPSAnimator(glcanvas, 60, true);
        animator.start();
    }

}
