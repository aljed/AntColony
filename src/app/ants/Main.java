package app.ants;

import javafx.application.Application;
import javafx.stage.Stage;

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
        Thread.sleep(200);
        view.moveAnt(view.getRoot(), "Calgary", "SaltLakeCity");

        view.moveAnt(view.getRoot(), "ElPaso", "Dallas");
        view.moveAnt(view.getRoot(), "Charlotte", "Tampa");
    }

}
