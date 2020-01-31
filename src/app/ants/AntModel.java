package app.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AntModel {
    private Vertex position; // pozycja startowa (not null) + pozycja koncowa (may be null) private Vertex endPosition;
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

    public void setPosition(Vertex position) {
        this.position = position;
    }

    Circle getAnt()
    {
        return ant;
    }

    public void setAnt(Circle ant) {
        this.ant = ant;
    }
}
