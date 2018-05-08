package model;

import java.util.ArrayList;
import java.util.List;

public class Mesh {

    private int height;
    private int width;
    private List<Float> heights;

    public Mesh(int height, int width) {
        this.height = height;
        this.width = width;
        this.heights = new ArrayList<Float>();
    }

}
