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
            double edgesAttractivenessSum = currentVertex.getConnectedVertices().stream().mapToDouble(pair ->
            {
                if (pair.getValue() != finalLastEdge) return pair.getValue().getAttractiveness();
                else return 0.0;
            }).sum();
            double randomUniformWeightedDouble = ModelParameters.randomGenerator.nextDouble() * edgesAttractivenessSum;

            Pair<Vertex, Edge> nextVertexEdge = null;
            for (Pair<Vertex, Edge> e : currentVertex.getConnectedVertices()) {
                if (e.getValue() == lastEdge) continue;
                double threshold = e.getValue().getAttractiveness();
                if (randomUniformWeightedDouble <= threshold) {
                    nextVertexEdge = e;
                    break;
                } else randomUniformWeightedDouble -= threshold;
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
