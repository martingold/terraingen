package model;

import model.noise.INoiseGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Mesh {

    private int height;
    private int width;
    private double amplitude;
    private List<Double> heights;

    public Mesh(int height, int width, INoiseGenerator noiseGenerator, double frequency, double amplitude) {
        this.height = height;
        this.width = width;
        this.amplitude = amplitude;
        heights = new ArrayList<Double>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                double h = noiseGenerator.generate(
                        i / (double) height * frequency,
                        j / (double) width * frequency
                );
                heights.add(h);
            }
        }
        normalize();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public double getSingleHeight(int x, int y) {
        return heights.get(x + y * width) * amplitude;
    }

    public double getSingleHeightNormalized(int x, int y) {
        return heights.get(x + y * width);
    }

    public void add(Mesh m){
        double amplitude = m.getAmplitude() / this.amplitude;
        for(int x = 0; x < width; x++ ) {
            for(int y = 0; y < height; y++ ) {
                int index = x + y * width;
                heights.set(index , getSingleHeightNormalized(x, y) + (m.getSingleHeightNormalized(x ,y) * amplitude));
            }
        }
        normalize();
    }

    private void normalize() {
        double max = Collections.max(heights);
        double min = Collections.min(heights);
        for(int i = 0; i < heights.size(); i++) {
            heights.set(i, ((heights.get(i) - min) / (max - min)) - 0.5);
        }

    }

}
