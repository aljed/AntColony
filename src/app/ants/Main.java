package app.ants;

import javafx.application.Application;
import javafx.stage.Stage;
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

        System.out.println(start.getName());
        System.out.println(end.getName());

//        for ( var e : start.getConnectedVertices() )
//            System.out.println(e.getKey().getName());

//        for ( Vertex v : graph.getVertices() )
//            System.out.println(v.getName());
//
//        if ( true ) return;

        ModelParameters.NUMBER_OF_ANTS = 9;
        Colony colony = new Colony(graph);
        var ants = colony.GeneratePathsTaken(start, end);
        for (var ant : ants) {
            for (var pair : ant.path)
                System.out.print(pair.getKey().getName() + ", ");
            System.out.println();
        }

        ModelParameters.NUMBER_OF_ANTS = 10;
        colony = new Colony(graph);

        for (int i = 0; i < 100000; ++i) {
            var ants2 = colony.GeneratePathsTaken(start, end);
            colony.ModifyPheromones();
            if (i % 1000 == 0) {

//                for ( Edge e : graph.getEdges() )
//                {
//                    System.out.println(e.getWeight() + ", " + e.getPheromones() + ", " + e.getPheromoneDelta());
//                }

                int percentage = 0;
                // int length_shortest = ModelParameters.LONGEST_PATH_CUT_OFF_POINT;
                int percentage_shortest = 0;
                double shortest_weight = Double.MAX_VALUE;
                double weight_sum = 0.0;
                for (Ant ant : ants2) {
                    if (ant.path.get(ant.path.size() - 1).getKey().equals(end)) {
                        ++percentage;
                    }
                    double weight = ant.path.stream().mapToDouble(pair -> pair.getValue().getWeight()).sum();
                    weight_sum += weight;
                    shortest_weight = Math.min(shortest_weight, weight);
                }
                for (Ant ant : ants2) {
                    double weight = ant.path.stream().mapToDouble(pair -> pair.getValue().getWeight()).sum();
                    if (weight == shortest_weight) ++percentage_shortest;
                }

                // System.out.println("% of ants finding path " + percentage  + " in generation " + i);
                System.out.println("Shortest path is " + shortest_weight);
                // System.out.println("% of ants traveling shortest path " + percentage_shortest );
                System.out.println("All paths weighted sum is " + weight_sum / ModelParameters.NUMBER_OF_ANTS);
            }


        }
//            colony.GeneratePathsTaken(start, end);
//            colony.ModifyPheromones();
//        }

        //perform
//        Colony colony = new Colony(graph);
//        for (int i = 0; i < 1000; ++i) {
//            colony.GeneratePathsTaken(start, end);
//            colony.ModifyPheromones();
//        }
//        ArrayList<Pair<Vertex, Edge>> pathTakenByFifthAntAfter1000Generations =
//                colony.GeneratePathsTaken(start, end).get(5).path;
//        for (Pair<Vertex, Edge> p : pathTakenByFifthAntAfter1000Generations) {
//            System.out.println(p.getKey().getName());
//        }

    }

}
