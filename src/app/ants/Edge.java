package app.ants;

class Edge
{
    /// Total number of pheromones accumulated over evolution. Modified by Colony.
    double pheromones = 0.0;
    /// Pheromones _deposited_ in current Colony Traversal. Modified by Ant.
    double pheromoneDelta = 0.0;
    // Length of the edge.
    double weight = 1.0;

    ///TODO: Edge connects Vertices. Not strings.
    private Vertex a;
    private Vertex b;

    Edge(Vertex a, Vertex b)
    {
        var X_distance = a.getX() - b.getX();
        var Y_distance = a.getY() - b.getY();
        weight = Math.sqrt(X_distance * X_distance + Y_distance * Y_distance);

        this.a = a;
        this.b = b;

        a.addConnectedVertexOverEdge(b, this);
        b.addConnectedVertexOverEdge(a, this);
    }

    ////TODO: IMMUTABLE! No need to ever change it. Use constructor instead.
//    void setA(String a) {
//        this.a = a;
//    }

    /**
     *
     */
    // double pheromonoe;

    Vertex getA()
    {
        return a;
    }

    ////TODO: IMMUTABLE! No need to ever change it. Use constructor instead.
//    void setB(String b) {
//        this.b = b;
//    }

    Vertex getB()
    {
        return b;
    }
}
