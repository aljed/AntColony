package app.ants;

import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Graph graph = new Graph();
        graph.createVerticesFromFile("germany50vertices.txt");
        graph.createEdgesFromFile("germany50edges.txt");
        //graph.createVerticesFromFile("janosUSCAvertices.txt");
        //graph.createEdgesFromFile("janosUSCAedges.txt");
        View view = new View(graph);
        view.initializeView(primaryStage);

        /**
         * Sample usage.
         */
        //TEST:
        String startTown = "Hamburg";
        String endTown = "Ulm";

        Vertex start = null;
        Vertex end = null;

        //search for vertices with given names
        for (int i = 0; i < graph.getVertices().size(); ++i) {
            if (graph.getVertices().get(i).getName().equals(startTown)) {
                start = graph.getVertices().get(i);
            } else if (graph.getVertices().get(i).getName().equals(endTown)) {
                end = graph.getVertices().get(i);
            }
        }

        ModelParameters.NUMBER_OF_ANTS = 50;
        Colony colony = new Colony(graph);
        int delay = 3000; //delays animations (1st one played with 3s delay, next generations every 5s)
        //how many generations
        for (int i = 0; i <= 2000; ++i) {
            var antsGeneration = colony.GeneratePathsTaken(start, end);
            colony.ModifyPheromones();

            if (i % 100 == 0) {
                //every ant of current generation
                for (var ant : antsGeneration) {
                    SequentialTransition st = new SequentialTransition();
                    st.getChildren().add(new PauseTransition(Duration.millis(delay))); //setting PauseTransition as 1st animation to play in a sequence
                    //every step of current ant - setting animation path
                    PathTransition pt = view.moveAnt(startTown, ant.path.get(0).getKey().getName());
                    st.getChildren().add(pt);
                    for (int j = 0; j < ant.path.size() - 1; ++j) {
                        pt = view.moveAnt(ant.path.get(j).getKey().getName(), ant.path.get(j + 1).getKey().getName());
                        st.getChildren().add(pt);
                    }
                    st.setCycleCount(1);
                    st.play();
                }
                delay += 5000;
            }
        }
    }
}