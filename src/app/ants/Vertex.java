package app.ants;

import javafx.util.Pair;
import java.util.ArrayList;

class Vertex
{
    private double x;
    private double y;
    private String name;

    private ArrayList<Pair<Vertex, Edge>> connectedVertices;

    public Vertex(String name, double x, double y) {
        this.x = x;
        this.y = y;
        this.name = name;
        connectedVertices = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public double getY()
    {
        return y;
    }

    public double getX()
    {
        return x;
    }

    /**
     * @return Collection of pairs (Vertex v, Edge e) connected to given vertex.
     * 'v' is a Vertex on the other side of 'e'
     */
    public ArrayList<Pair<Vertex, Edge>> getConnectedVertices()
    {
        return connectedVertices;
    }

    //TODO: Should probably be called from Graph, as it's Graph's responsibility to create model + called only from Edge constructor
    public void addConnectedVertexOverEdge(Vertex b, Edge edge)
    {
        connectedVertices.add(new Pair<>(b, edge));
    }
}
