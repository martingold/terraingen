package ui;

import listener.CameraListener;
import transforms.Camera;

import java.awt.event.*;

public class CameraControls implements KeyListener, MouseListener, MouseMotionListener {

    private int mOffsetX = 0;
    private int mOffsetY = 0;
    private long timeDiff = System.currentTimeMillis();

    private Camera camera;
    private CameraListener cameraListener;

    public CameraControls(CameraListener cameraListener) {
        this.cameraListener = cameraListener;
        camera = new Camera();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mOffsetX = e.getX();
        mOffsetY = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Throttle mouse events
        if(System.currentTimeMillis() - timeDiff < 1000/30) return;
        timeDiff = System.currentTimeMillis();
        int offsetX = mOffsetX - e.getX();
        int offsetY = mOffsetY - e.getY();
        double azimuth = camera.getAzimuth() - Math.atan(offsetX / 100.0);
        if(azimuth < 0) azimuth = Math.PI * 2 + azimuth;
        if(azimuth > Math.PI * 2) azimuth -= Math.PI * 2;

        double zenith = camera.getZenith() - Math.atan(offsetY / 100.0);
        if(zenith < 0) zenith = Math.PI * 2 + zenith;
        if(zenith > Math.PI * 2) zenith -= Math.PI * 2;

        camera = camera.withAzimuth(azimuth).withZenith(zenith);
        mOffsetX = e.getX();
        mOffsetY = e.getY();
        cameraListener.onCameraUpdate(camera);
    }

    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                camera = camera.left(1);
                break;
            case KeyEvent.VK_D:
                camera = camera.right(1);
                break;
            case KeyEvent.VK_W:
                camera = camera.backward(1);
                break;
            case KeyEvent.VK_S:
                camera = camera.forward(1);
                break;
        }
        cameraListener.onCameraUpdate(camera);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
