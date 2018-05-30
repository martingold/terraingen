package render;

import model.Mesh;

import javax.media.opengl.GL2;
import java.awt.*;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MeshRenderer {

    TreeMap<Double, Color> colors;

    public MeshRenderer(TreeMap<Double, Color> colors) {
        this.colors = colors;
    }

    public void render(Mesh mesh, GL2 gl) {
        for (int x = 0; x < mesh.getWidth() - 1; x++) {
            for (int y = 0; y < mesh.getHeight() - 1; y++) {
                Color c = getColor(mesh.getSingleHeightNormalized(x, y));
                gl.glColor3f(
                        c.getRed() / 255.0f,
                        c.getGreen() / 255.0f,
                        c.getBlue() / 255.0f
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

    private Color getColor(double h) {
        return colors.floorEntry(Math.abs(h - 0.5)).getValue();
    }

}
