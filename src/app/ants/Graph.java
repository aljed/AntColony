package app.ants;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Graph
{
    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    ArrayList<Vertex> getVertices()
    {
        return vertices;
    }

    ArrayList<Edge> getEdges()
    {
        return edges;
    }

    void createVerticesFromFile(String fileName) throws FileNotFoundException
    {
        File file = new File("src/" + fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Vertex newV = new Vertex(scanner.next(), Double.parseDouble(scanner.next()), Double.parseDouble(scanner.next()));
            vertices.add(newV);
        }
        scanner.close();
    }

    void createEdgesFromFile(String fileName) throws FileNotFoundException
    {
        File file = new File("src/" + fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Vertex vA = null;
            Vertex vB = null;
            String a = scanner.next();
            String b = scanner.next();

            //Searching for vertices with names got from file
            for (int i = 0; i < vertices.size(); ++i) {
                if (vertices.get(i).getName().equals(a)) {
                    vA = vertices.get(i);
                } else if (vertices.get(i).getName().equals(b)) {
                    vB = vertices.get(i);
                }
            }
            Edge newE = new Edge(vA, vB);
            edges.add(newE);
        }
        scanner.close();
    }

    void ResetPathCount()
    {
        for (int i = 0; i < edges.size(); ++i) {
            edges.get(i).setPheromoneDelta(0.0);
        }
    }
}
