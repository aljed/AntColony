package app.ants;

import java.util.ArrayList;

public class Colony
{
    private ArrayList<Ant> colony = new ArrayList<>(ModelParameters.NUMBER_OF_ANTS);
    private Graph graph;

    Colony(Graph graph)
    {
        this.graph = graph;
        int ants = ModelParameters.NUMBER_OF_ANTS;
        while (ants-- > 0) colony.add(new Ant());
    }

    ArrayList<Ant> GeneratePathsTaken(Vertex start, Vertex end)
    {
        graph.ResetPathCount();
        for (int i = 0; i < colony.size(); ++i) {
            colony.get(i).TraverseGraph(start, end);
        }
        return colony;
    }

    Graph ModifyPheromones()
    {
        for (int i = 0; i < graph.getEdges().size(); ++i) {
            graph.getEdges().get(i).setPheromones((1.0 - ModelParameters.PHEROMONE_EVAPORATION_RATE) * graph.getEdges().get(i).getPheromones() + graph.getEdges().get(i).getPheromoneDelta());
        }
        return graph;
    }
}
