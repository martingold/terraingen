import com.jogamp.opengl.util.FPSAnimator;
import ui.CameraControls;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {

        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        final GLCanvas glcanvas = new GLCanvas(glcapabilities);

        CameraControls cameraControls = new CameraControls();

        glcanvas.addGLEventListener(new EventListener());

        final JFrame jframe = new JFrame("One Triangle Swing GLCanvas");
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