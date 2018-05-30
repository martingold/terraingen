import listener.CameraListener;
import model.Mesh;
import model.noise.INoiseGenerator;
import model.noise.PerlinNoiseGenerator;
import render.MeshRenderer;
import transforms.Camera;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class EventListener implements GLEventListener, CameraListener {

    private final MeshRenderer meshRenderer;
    private Mesh mesh;
    private GLU glu = new GLU();
    private Camera camera = new Camera();

    public EventListener(MeshRenderer meshRenderer, Mesh mesh) {
        this.meshRenderer = meshRenderer;
        this.mesh = mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if (height <= 0)
            height = 1;

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(80.0f, h, 1.0, 2000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    }

    public void dispose(GLAutoDrawable drawable) {
    }

    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();


        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        // Clear The Screen And The Depth Buffer
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(-(float) Math.toDegrees(camera.getZenith()), 0.0f, 1.0f, 0.0f);
        gl.glRotatef(-(float) Math.toDegrees(camera.getAzimuth()), 0.0f, 0.0f, 1.0f);

        gl.glTranslatef(0f, 1f, 0f);
        gl.glTranslatef(
                (float) camera.getPosition().getX(),
                (float) camera.getPosition().getY(),
                (float) camera.getPosition().getZ()
        );


        gl.glBegin(GL2.GL_TRIANGLES);
        meshRenderer.render(mesh, gl);
        gl.glEnd();

        gl.glFlush();
    }

    @Override
    public void onCameraUpdate(Camera camera) {
        this.camera = camera;
    }
}
