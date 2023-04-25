package a6;

import java.util.ArrayList;
import java.util.Map;

public class NodeImpl implements Node {

    /* You will include the method signatures (return type, name, and arg types) for any node methods you
    need in this file. */

    /*Hint: Make sure you update the Node interface in Node.java when you add a new method implementation
    in NodeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    /*Also, any node fields you want to add for the object should go in this file.  */
    private String name;

    private ArrayList<Edge> edges;

    private Integer indeg;

    private Double dist;

    public NodeImpl(String n, ArrayList<Edge> e) {
        name = n;
        edges = e;
        indeg = 0;
        dist = Double.MAX_VALUE;
    }


    public boolean hasEdge(Node other) {
        for (int i = 0; i < this.edges.size(); i++) {
            if (this.edges.get(i).getDest() == other) {return true;}
        }
        return false;
    }


    public void delEdge(Node other) {
        if (!this.hasEdge(other)) {
            return;
        }
        for (int i = 0; i < this.edges.size(); i++) {
            if (this.edges.get(i).getDest() == other) {this.edges.remove(this.edges.get(i)); return;}
        }
    }
    /*Also, any node fields you want to add for the object should go in this file.  */

    public Edge getEdge(Node other) {
        Edge result = null;
        for (int i = 0; i < this.edges.size(); i++) {
            if (this.edges.get(i).getSource().getName() == this.getName() && this.edges.get(i).getDest().getName() == other.getName()) {
                result = this.edges.get(i);
            }
        }
        return result;
    }


    @Override
    public String getName() {
        return this.name;  //Dummy return value.  Remove when you implement!
    }
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public Integer getIndeg(Map<String, Node> nodes) {
        return this.indeg;}

    public void decIndeg() {
        this.indeg -= 1;
    }
    public void incIndeg() {
        this.indeg += 1;
    }

    public void setDist(Double n) {
        this.dist = n;
    }

    public void addDist(Double n) {
        this.dist += n;
    }

    public double getDist() {return this.dist;}
}
