package app.ants;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void createVerticesFromFile(String fileName) throws FileNotFoundException {
        File file = new File("src/" + fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Vertex newV = new Vertex();
            newV.setName(scanner.next());
            newV.setX(Double.parseDouble(scanner.next()));
            newV.setY(Double.parseDouble(scanner.next()));
            vertices.add(newV);
        }
        scanner.close();
    }

    public void createEdgesFromFile(String fileName) throws FileNotFoundException {
        File file = new File("src/" + fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Edge newE = new Edge();
            newE.setA(scanner.next());
            newE.setB(scanner.next());
            edges.add(newE);
        }
        scanner.close();
    }

}
