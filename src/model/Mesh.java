package model;

import model.noise.INoiseGenerator;

import java.util.ArrayList;
import java.util.List;

public class Mesh {

    private int height;
    private int width;
    private List<Double> heights;

    public Mesh(int height, int width, INoiseGenerator noiseGenerator) {
        this.height = height;
        this.width = width;
        heights = new ArrayList<Double>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                heights.add( 1 + noiseGenerator.generate(i / (double) height, j / (double) width) / 2.0);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<Double> getHeights() {
        return heights;
    }

    public double getSingleHeight(int x, int y) {
        return heights.get(x + y * width);
    }

}
