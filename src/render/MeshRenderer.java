package render;

import model.Mesh;

import javax.media.opengl.GL2;

public class MeshRenderer {

    public void render(Mesh mesh, GL2 gl) {
        for (int x = 0; x < mesh.getWidth() - 1; x++) {
            for (int y = 0; y < mesh.getHeight() - 1; y++) {
                System.out.println(mesh.getSingleHeight(x, y));
                gl.glColor3f(
                        (float) mesh.getSingleHeight(x, y),
                        (float) mesh.getSingleHeight(x, y),
                        (float) mesh.getSingleHeight(x, y)
                );
                // Upper triangle
                gl.glVertex3f(x, y, (float) mesh.getSingleHeight(x, y));
                gl.glVertex3f(x + 1, y, (float) mesh.getSingleHeight(x + 1, y));
                gl.glVertex3f(x + 1, y + 1, (float) mesh.getSingleHeight(x + 1, y + 1));
                // Lower triangle
                gl.glVertex3f(x, y, (float) mesh.getSingleHeight(x, y));
                gl.glVertex3f(x, y + 1, (float) mesh.getSingleHeight(x, y + 1));
                gl.glVertex3f(x + 1, y + 1, (float) mesh.getSingleHeight(x + 1, y + 1));

            }
        }
    }

}
