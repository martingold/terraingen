package ui;

import javax.swing.*;

public class ControlOption {

    private String description;
    private float value;
    private JSlider slider;

    public ControlOption(String description, float value) {
        this.description = description;
        this.value = value;
        slider = new JSlider();
        slider.setMinimum(-200);
        slider.setMaximum(200);
        slider.addChangeListener(event -> {
            JSlider source = (JSlider) event.getSource();
            this.value = source.getValue() / 100.0f;
        });
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
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

    public String toString() {
        return this.description + ": " + this.value;
    }
}
