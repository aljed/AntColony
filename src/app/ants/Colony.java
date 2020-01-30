package app.ants;

import java.util.ArrayList;

public class Colony
{
    private ArrayList<Ant> colony = new ArrayList<>();
    private Graph graph;

    public Colony(Graph graph)
    {
        this.graph = graph;
    }

    ArrayList<Ant> GeneratePathsTaken(Vertex start, Vertex end)
    {
        graph.ResetPathCount();
        for (Ant a : colony) {
            a.TraverseGraph(start, end);
        }
        return colony;
    }

    Graph ModifyPheromones()
    {
        for (Edge e : graph.getEdges()) {
            e.setPheromones((1.0 - ModelParameters.PHEROMONE_EVAPORATION_RATE) * e.getPheromones() + e.getPheromoneDelta());
        }

        return graph;
    }

//    public void setGraph(Graph graph)
//    {
//        this.graph = graph;
//    }
}
