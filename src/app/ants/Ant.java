package app.ants;

import javafx.util.Pair;

import java.util.ArrayList;
// import org.javatuples.Tuple;

class Ant
{
    ArrayList<Pair<Vertex, Edge>> path = new ArrayList<>();

    ArrayList<Pair<Vertex, Edge>> TraverseGraph(Vertex startingVertex, Vertex endVertex)
    {
        Vertex currentVertex = startingVertex;


        int edgesTraversed = 0;
        while ((currentVertex != endVertex) && (edgesTraversed++ < ModelParameters.LONGEST_PATH_CUT_OFF_POINT)) {

            ///TODO: Optimization. This sum can be saved model-wide, for a single-pass. It won't change for different ants until we update pheromone weights.
            double edgesSum =
                    currentVertex.ConnectedVertices().stream().mapToDouble(pair -> pair.getValue().pheromones).sum();
            double randomUniformWeightedDouble = ModelParameters.randomGenerator.nextDouble() * edgesSum;

            Pair<Vertex, Edge> nextVertexEdge = null;
            for (Pair<Vertex, Edge> e : currentVertex.ConnectedVertices()) {
                if (randomUniformWeightedDouble < e.getValue().pheromones) nextVertexEdge = e;
                else randomUniformWeightedDouble -= e.getValue().pheromones;
            }
            assert (nextVertexEdge != null);
            path.add(nextVertexEdge);
            currentVertex = nextVertexEdge.getKey();
        }

        // Spread pheromones over path taken.
        double totalPathLength = path.stream().mapToDouble(pair -> pair.getValue().weight).sum();
        if (edgesTraversed < ModelParameters.LONGEST_PATH_CUT_OFF_POINT) {   // if the ant traveled over sufficiently large amount of vertices, we assume the pheromones were so weak we can ignore them
            for (var pair : path) {
                pair.getValue().pheromoneDelta += ModelParameters.PHEROMONE_STRENGTH_CONSTANT / totalPathLength;
            }
        }

        return path;
    }

}
