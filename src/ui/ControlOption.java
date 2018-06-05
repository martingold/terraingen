package ui;

import listener.ParameterListener;

import javax.swing.*;

public class ControlOption {

    private String description;
    private float value;
    private JSlider slider;

    public ControlOption(String description, float value, ParameterListener parameterListener) {
        this.description = description;
        this.value = value;
        slider = initSlider(parameterListener, -1.0f, 1.0f, value);
    }

    public ControlOption(String description, float value, ParameterListener parameterListener, float min, float max) {
        this.description = description;
        this.value = value;
        slider = initSlider(parameterListener, min, max, value);
    }

    public float getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public JSlider getSlider() {
        return slider;
    }

    public JSlider initSlider(ParameterListener parameterListener, float min, float max, float value) {
        JSlider slider = new JSlider();
        slider.setValue((int) value * 100);
        slider.setMinimum((int) (min * 100));
        slider.setMaximum((int) (max * 100));
        slider.addChangeListener(event -> {
            JSlider source = (JSlider) event.getSource();
            this.value = source.getValue() / 100.0f;
            parameterListener.onParameterUpdate();
        });
        slider.setMajorTickSpacing((int)(Math.abs(max - min)  / 0.05f));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

}
