package app.ants;

import javafx.util.Pair;
import java.util.ArrayList;

class Ant
{
    ArrayList<Pair<Vertex, Edge>> path = new ArrayList<>();

    ArrayList<Pair<Vertex, Edge>> TraverseGraph(Vertex startingVertex, Vertex endVertex)
    {
        path.clear();

        Vertex currentVertex = startingVertex;
        Edge lastEdge = null;
        int edgesTraversed = 0;

        while ((currentVertex != endVertex) && (edgesTraversed++ < ModelParameters.LONGEST_PATH_CUT_OFF_POINT)) {

            Edge finalLastEdge = lastEdge;
            double edgesSum = currentVertex.getConnectedVertices().stream().mapToDouble(pair ->
            {
                if (pair.getValue() != finalLastEdge)
                    return pair.getValue().getPheromones() * pair.getValue().getWeight();
                else return 0.0;
            }).sum();
            double randomUniformWeightedDouble = ModelParameters.randomGenerator.nextDouble() * edgesSum;

            Pair<Vertex, Edge> nextVertexEdge = null;
            for (Pair<Vertex, Edge> e : currentVertex.getConnectedVertices()) {
                if (e.getValue() == lastEdge) continue;
                double threshold = e.getValue().getPheromones() * e.getValue().getWeight();
                if (randomUniformWeightedDouble <= threshold) {
                    nextVertexEdge = e;
                    break;
                } else randomUniformWeightedDouble -= e.getValue().getPheromones() * e.getValue().getWeight();
            }
            assert (nextVertexEdge != null);
            path.add(nextVertexEdge);
            currentVertex = nextVertexEdge.getKey();
            lastEdge = nextVertexEdge.getValue();
        }

        // Spread pheromones over path taken.
        double totalPathLength = path.stream().mapToDouble(pair -> pair.getValue().getWeight()).sum();
        if (edgesTraversed < ModelParameters.LONGEST_PATH_CUT_OFF_POINT) {   // if the ant traveled over sufficiently large amount of vertices, we assume the pheromones were so weak we can ignore them
            for (var pair : path) {
                pair.getValue().setPheromoneDelta(pair.getValue().getPheromoneDelta() + ModelParameters.PHEROMONE_STRENGTH_CONSTANT / totalPathLength);
                int i = 4;
            }
        }
        return path;
    }

}
