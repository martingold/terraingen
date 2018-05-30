package model;

import model.noise.INoiseGenerator;
import ui.ControlOption;

import java.util.List;

public class MeshGenerator {

    private int meshSize;
    private INoiseGenerator noiseGenerator;

    public MeshGenerator (int meshSize, INoiseGenerator noiseGenerator) {
        this.meshSize = meshSize;
        this.noiseGenerator = noiseGenerator;
    }

    public Mesh generate(List<ControlOption> controls) {
        Mesh mesh = new Mesh(meshSize, meshSize, noiseGenerator, 2.0d, 30.0);
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 4.0d, 18.0));
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 8.0d, 15.8));
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 16.0d, 12));
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 18.0d, 6));
        mesh.islandize(
                controls.get(0).getValue(),
                controls.get(1).getValue(),
                controls.get(2).getValue()
        );
        return mesh;
    }

}
