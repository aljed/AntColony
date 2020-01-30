package app.ants;

import javafx.util.Pair;

import java.util.ArrayList;

class Vertex
{
    private double x;
    private double y;
    private String name;

    private ArrayList<Pair<Vertex, Edge>> connectedVertices;

//    void setName(String name)
//    {
//        this.name = name;
//    }

    String getName()
    {
        return name;
    }

//    void setY(double y)
//    {
//        this.y = y;
//    }

    double getY()
    {
        return y;
    }

//    void setX(double x)
//    {
//        this.x = x;
//    }

    double getX()
    {
        return x;
    }

    /**
     * @return Kolekcja par (Wierzchołek w, Krawędź k) połączonych z danym wierzchołkiem.
     * e jest krawędzią między (k, this)
     */
    ArrayList<Pair<Vertex, Edge>> ConnectedVertices()
    {
        return connectedVertices;
    }

    /// Called only from Edge constructor. Which is kind of smelly. //TODO: Should probably be called from Graph, as it's Graph's responsibility to create model.  
    void addConnectedVertexOverEdge(Vertex b, Edge edge)
    {
        connectedVertices.add(new Pair<>(b, edge));
    }
}
