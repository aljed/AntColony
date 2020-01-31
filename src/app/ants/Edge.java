package app.ants;

class Edge
{
    // Total number of pheromones accumulated over evolution. Modified by Colony.
    private double pheromones = 1.0;
    // Pheromones _deposited_ in current Colony Traversal. Modified by Ant.
    private double pheromoneDelta = 0.0;
    // Length of the edge.
    private double weight = 1.0;

    ///TODO: Edge connects Vertices. Not strings.
    private Vertex a;
    private Vertex b;

    public Edge(Vertex a, Vertex b)
    {
        double X_distance = a.getX() - b.getX();
        double Y_distance = a.getY() - b.getY();
        weight = Math.sqrt(X_distance * X_distance + Y_distance * Y_distance);

        this.a = a;
        this.b = b;

        a.addConnectedVertexOverEdge(b, this);
        b.addConnectedVertexOverEdge(a, this);
    }

    double getAttractiveness()
    {
        return pheromones * (1.0 / weight);
    }

    public double getPheromones() {
        return pheromones;
    }

    public void setPheromones(double pheromones) {
        this.pheromones = pheromones;
    }

    public double getPheromoneDelta() {
        return pheromoneDelta;
    }

    public void setPheromoneDelta(double pheromoneDelta) {
        this.pheromoneDelta = pheromoneDelta;
    }

    public double getWeight() {
        return weight;
    }

    public Vertex getA() {
        return a;
    }

    public Vertex getB() {
        return b;
    }
}
