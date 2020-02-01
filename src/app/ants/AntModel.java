package app.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AntModel {
    private Vertex position;
    private Circle ant;

    AntModel(Vertex pos)
    {
        position = pos;
        ant = new Circle(4.0f, Color.RED);
    }

    Vertex getPosition()
    {
        return position;
    }

    Circle getAnt()
    {
        return ant;
    }
}
