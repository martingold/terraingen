package model.noise;

public interface INoiseGenerator {

    double generate(double x, double y);

    void setSeed(int seed);

}
