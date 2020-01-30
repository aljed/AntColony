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

        colony = new Colony(graph);
        ModelParameters.NUMBER_OF_ANTS = 1000;
        for (int i = 0; i < 10; ++i) {
            int j = 0;
            for (; j < 100; ++j) {
                colony.GeneratePathsTaken(start, end);
                colony.ModifyPheromones();
            }
            var ants2 = colony.GeneratePathsTaken(start, end);
            int percentage = 0;
            for (Ant ant : ants2)
                if (ant.path.get(ant.path.size() - 1).getKey().equals(start)) {
                    ++percentage;
                }
            System.out.println("# of ants founding path " + percentage + " in generation " + j * i);

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
