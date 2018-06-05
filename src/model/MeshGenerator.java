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
        Mesh mesh = new Mesh(meshSize, meshSize, noiseGenerator, controls.get(3).getValue(), controls.get(2).getValue());
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, controls.get(5).getValue(), controls.get(4).getValue()));
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, controls.get(7).getValue(), controls.get(6).getValue()));
        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, controls.get(9).getValue(), controls.get(8).getValue()));
//        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 4.0d, 18.0));
//        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 8.0d, 15.8));
//        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 16.0d, 12));
//        mesh.add(new Mesh(meshSize, meshSize, noiseGenerator, 18.0d, 6));
        mesh.islandize(
                controls.get(0).getValue(),
                controls.get(1).getValue()
        );
        return mesh;
    }

}
