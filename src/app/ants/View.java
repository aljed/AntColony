package app.ants;
import javafx.animation.PathTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class View {
    private Graph graph;
    private Pane root = new Pane();
    //private ArrayList<Circle> vModels = new ArrayList<>();
    private ArrayList<Line> eModels = new ArrayList<>();

    public View(Graph graph) {
        this.graph = graph;
    }

    public Pane getRoot() {
        return root;
    }

    public void initializeView(Stage stage) {
//        Pane root = new Pane();

        for (int i = 0; i < graph.getVertices().size(); ++i) {
            Circle circle = new Circle();
            circle.setCenterX(graph.getVertices().get(i).getX());
            circle.setCenterY(graph.getVertices().get(i).getY());
            circle.setRadius(5.0f);
            root.getChildren().add(circle);
        }

        for (int i = 0; i < graph.getEdges().size(); ++i) {
            Line line = new Line();
            line.setStrokeWidth(1.5f);
            for (Vertex v : graph.getVertices()) {
                if (v.getName().equals(graph.getEdges().get(i).getA())) {
                    line.setStartX(v.getX());
                    line.setStartY(v.getY());
                }

                if (v.getName().equals(graph.getEdges().get(i).getB())) {
                    line.setEndX(v.getX());
                    line.setEndY(v.getY());
                }
            }
            root.getChildren().add(line);
            eModels.add(line);
        }

        Scene scene = new Scene(root, 800, 800, Color.WHITESMOKE);

        stage.setTitle("Ant Colony");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void moveAnt(Pane root, String startTown, String endTown) {
        Vertex startVertex = null;
        Vertex endVertex = null;

        for (int i = 0; i < graph.getVertices().size(); ++i) {
            if(graph.getVertices().get(i).getName().equals(startTown)) {
                startVertex = graph.getVertices().get(i);
            }
            if(graph.getVertices().get(i).getName().equals(endTown)) {
                endVertex = graph.getVertices().get(i);
            }
        }

        AntModel a = new AntModel(startVertex);
        a.getAnt().setCenterX(a.getPosition().getX());
        a.getAnt().setCenterY(a.getPosition().getY());
        root.getChildren().add(a.getAnt());

        //setPath
        Line path = new Line();
        path.setStartX(startVertex.getX());
        path.setStartY(startVertex.getY());
        path.setEndX(endVertex.getX());
        path.setEndY(endVertex.getY());

        // a.setPosition(endVertex);

        PathTransition transition = new PathTransition();
        transition.setNode(a.getAnt());
        transition.setDuration(Duration.seconds(2));
        transition.setPath(path);
        transition.setCycleCount(1); // PathTransition.INDEFINITE);
        transition.play();
    }
}
