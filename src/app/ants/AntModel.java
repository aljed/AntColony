package app.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AntModel {
    private Vertex position; // pozycja startowa (not null) + pozycja koncowa (may be null) private Vertex endPosition;
    private Circle ant;

    AntModel(Vertex pos)
    {
        position = pos;
        ant = new Circle(7.0f, Color.RED);
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

//    public void animateOnce() {
//        Line path = new Line();
//        path.setStartX(startVertex.getX());
//        path.setStartY(startVertex.getY());
//        path.setEndX(endVertex.getX());
//        path.setEndY(endVertex.getY());
//
//        PathTransition transition = new PathTransition();
//        transition.setNode(a.getAnt());
//        transition.setDuration(Duration.seconds(2));
//        transition.setPath(path);
//        transition.setCycleCount(1); // PathTransition.INDEFINITE);
//        transition.play();
//    }
}
