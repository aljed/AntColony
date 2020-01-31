package app.ants;

import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;

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
        //graph.createVerticesFromFile("germany50vertices.txt");
        //graph.createEdgesFromFile("germany50edges.txt");
        graph.createVerticesFromFile("janosUSCAvertices.txt");
        graph.createEdgesFromFile("janosUSCAedges.txt");
        View view = new View(graph);
        view.initializeView(primaryStage);
//        view.moveAnt("Calgary", "SaltLakeCity");
//        Thread.sleep(200);
//        view.moveAnt("Calgary", "SaltLakeCity");
//        view.moveAnt("ElPaso", "Dallas");
//        view.moveAnt("Charlotte", "Tampa");
//        for (int i = 0; i < 100; ++i) {
//            view.moveAnt("Calgary", "SaltLakeCity");
//            //Thread.sleep(100);
//        }

        /**
         * Sample usage.
         */
        //TEST:
        String startTown = "Vancouver";
        String endTown = "Miami";

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

        ModelParameters.NUMBER_OF_ANTS = 10;
        Colony colony = new Colony(graph);
        int delay = 4000;
        //how many generations
        for (int i = 0; i < 20000; ++i) {
            var antsGeneration = colony.GeneratePathsTaken(start, end);
            if (i % 1000 == 0) {
            //every ant of current generation
                for (var ant : antsGeneration) {
                    SequentialTransition st = new SequentialTransition();
                    st.getChildren().add(new PauseTransition(Duration.millis(delay)));
                    //setting animation path for current ant
                    for (int j = 0; j < ant.path.size() - 1; ++j) {
                        //System.out.print(ant.path.get(j).getKey().getName() + ", ");
                        PathTransition pt = view.moveAnt(ant.path.get(j).getKey().getName(), ant.path.get(j + 1).getKey().getName());
                        st.getChildren().add(pt);
                    }
                    st.setCycleCount(1);
                    st.play();
                }
                delay += 500;
            }
            colony.ModifyPheromones();

            //SOME STATISTICS not affecting running program
//            if (i % 1000 == 0) {
//                int percentage = 0;
//                int percentage_shortest = 0;
//                double shortest_weight = Double.MAX_VALUE;
//                double weight_sum = 0.0;
//                for (Ant ant : antsGeneration) {
//                    if (ant.path.get(ant.path.size() - 1).getKey().equals(end)) {
//                        ++percentage;
//                    }
//                    double weight = ant.path.stream().mapToDouble(pair -> pair.getValue().getWeight()).sum();
//                    weight_sum += weight;
//                    shortest_weight = Math.min(shortest_weight, weight);
//                }
//                for (Ant ant : antsGeneration) {
//                    double weight = ant.path.stream().mapToDouble(pair -> pair.getValue().getWeight()).sum();
//                    if (weight == shortest_weight) ++percentage_shortest;
//                }
//
//                // System.out.println("% of ants finding path " + percentage  + " in generation " + i);
//                System.out.println("Shortest path is " + shortest_weight);
//                // System.out.println("% of ants traveling shortest path " + percentage_shortest );
//                System.out.println("All paths weighted sum is " + weight_sum / ModelParameters.NUMBER_OF_ANTS);
//            } //STATISTICS
        }
    }

}
