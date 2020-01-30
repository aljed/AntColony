package app.ants;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Graph graph = new Graph();
        //graph.createVerticesFromFile("germany50vertices.txt");
        //graph.createEdgesFromFile("germany50edges.txt");
        graph.createVerticesFromFile("janosUSCAvertices.txt");
        graph.createEdgesFromFile("janosUSCAedges.txt");
        View view = new View(graph);
        view.initializeView(primaryStage);
        view.moveAnt(view.getRoot(), "Calgary", "SaltLakeCity");
        view.moveAnt(view.getRoot(), "Calgary", "SaltLakeCity");

        view.moveAnt(view.getRoot(), "ElPaso", "Dallas");
        view.moveAnt(view.getRoot(), "Charlotte", "Tampa");


        /**
         * Sample usage.
         */
        Vertex start = new Vertex();
        Vertex end = new Vertex();

        Colony colony = new Colony(graph);
        for (int i = 0; i < 999; ++i) {
            colony.GeneratePathsTaken(start, end);
            colony.ModifyPheromones();
        }
        ArrayList<Pair<Vertex, Edge>> pathTakenByFifthAntAfter1000Generations =
                colony.GeneratePathsTaken(start, end).get(5).path;
        // see: Vertex.ConnectedVertices() documentation.

    }

}
