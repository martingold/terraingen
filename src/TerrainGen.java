import com.jogamp.opengl.util.FPSAnimator;
import listener.ParameterListener;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TerrainGen implements ParameterListener {

    private MeshGenerator meshGenerator;
    private EventListener ev;
    private List<ControlOption> controls;

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

        int seed = (int) (Math.random() * 100000);
        INoiseGenerator noiseGenerator = new PerlinNoiseGenerator(seed);
        meshGenerator = new MeshGenerator(400, noiseGenerator);

        controls = new ArrayList<>();
        controls.add(new ControlOption("Island 'b' param", 1.0f, this, 0.0f, 2.0f));
        controls.add(new ControlOption("Island 'c' param", 1.0f, this, 0.0f, 2.0f));
        controls.add(new ControlOption("Layer 1 amplitude", 30.0f, this, 1.0f, 40.0f));
        controls.add(new ControlOption("Layer 1 frequency", 2.0f, this, 1.0f, 30.0f));
        controls.add(new ControlOption("Layer 2 amplitude", 18.0f, this, 1.0f, 40.0f));
        controls.add(new ControlOption("Layer 2 frequency", 4.0f, this, 1.0f, 30.0f));
        controls.add(new ControlOption("Layer 3 amplitude", 15.0f, this, 1.0f, 40.0f));
        controls.add(new ControlOption("Layer 3 frequency", 8.0f, this, 1.0f, 30.0f));
        controls.add(new ControlOption("Layer 4 amplitude", 12.0f, this, 1.0f, 40.0f));
        controls.add(new ControlOption("Layer 4 frequency", 16.0f, this, 1.0f, 30.0f));

        MeshRenderer meshRenderer = new MeshRenderer(colors);
        ev = new EventListener(meshRenderer, meshGenerator.generate(controls));


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

    @Override
    public void onParameterUpdate() {
        ev.setMesh(meshGenerator.generate(controls));
    }
}
