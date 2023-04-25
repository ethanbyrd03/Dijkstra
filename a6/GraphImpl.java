package a6;

import java.util.*;

public class GraphImpl implements Graph {
    Map<String, Node> nodes; //Do not delete.  Use this field to store your nodes.
                             // key: name of node. value: a5.Node object associated with name

    public GraphImpl() {
        nodes = new HashMap<>();
    }

    @Override
    public boolean addNode(String name) {
        if (this.nodes.containsKey(name)) { return false;} // if it's already here, false
        if (name == null) {return false;} // if there is no name given, false
        ArrayList<Edge> edg;
        edg = new ArrayList<Edge>();// creating an edge for the node
        Node n = new NodeImpl(name, edg);
        this.nodes.put(name, n);// creating a node and putting it into the map
        return true;
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        if (weight < 0) {return false;} // if negative weight, false
        if (this.nodes.containsKey(src) == false || this.nodes.containsKey(dest) == false) {return false;} // if src or dest are not in graph, false
        if (this.nodes.get(src).hasEdge(this.nodes.get(dest))) {return false;} //if it already has an edge, false
        Edge e = new EdgeImpl(this.nodes.get(src), this.nodes.get(dest), weight);//creating a new edge
        this.nodes.get(dest).incIndeg();
        return this.nodes.get(src).getEdges().add(e);//adding edge to the src node
    }

    @Override
    public boolean deleteNode(String name) {
        //Hint: Do you need to remove edges when you delete a node?
        if (this.nodes.containsKey(name) == false) {return false;} //if there is already a node with the same name, false
        for (int i = 0; i < this.nodes.get(name).getEdges().size(); i++) {
            deleteEdge(this.nodes.get(name).getName(), this.nodes.get(name).getEdges().get(i).getDest().getName()); // looping through the node and deleting all edges
        }
        // remove the node from the graph
        String o;
        Set<String> keys = this.nodes.keySet();
        Iterator<String> iter = keys.iterator(); // loop through rest of the nodes
        while (iter.hasNext()) {
            o = iter.next();
            if (this.nodes.get(o).hasEdge(this.nodes.get(name))) {deleteEdge(o, name); this.nodes.get(name).decIndeg();} //if the rest of the nodes has an edge pointing towards the deleted node, delete that edge
        }
        this.nodes.remove(this.nodes.get(name));
        return true;// after deletion, return true
    }

    @Override
    public boolean deleteEdge(String src, String dest) {
        if (!this.nodes.get(src).hasEdge(this.nodes.get(dest))) {return false;}
        this.nodes.get(src).delEdge(this.nodes.get(dest)); this.nodes.get(dest).decIndeg();
        return true;
    }

    @Override
    public int numNodes() {
        int n = 0;
        Iterator<String> it = this.nodes.keySet().iterator();
        while(it.hasNext()) {
            n += 1;
            it.next();}
        return n;    }

    @Override
    public int numEdges() {
        int n = 0;
        Set<String> keys = this.nodes.keySet();
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            n += this.nodes.get(iter.next()).getEdges().size();
        }
        return n;    }

    @Override
    public Map<String, Double> dijkstra(String start) {
        /*Map<String, Double> result = new HashMap<>();
        result.put(start, 0.0);
        while (result.size() < this.nodes.size()) {
            Iterator<String> it = this.nodes.keySet().iterator();
            String o;
            while (it.hasNext()) {
                o = it.next();
                if (o != start) {
                    result.put(o, findpathlength(start, o));
                }
            }
        }
        return result;  //Dummy return value.  Remove when you implement!*/
        Map<String, Double> settled = new HashMap<>();
        Comparator<ShortestPathQueueObject> compare = (a, b) -> Double.compare(a.distance, b.distance);
        PriorityQueue<ShortestPathQueueObject> queue = new PriorityQueue<ShortestPathQueueObject>(compare);
        this.nodes.get(start).setDist(0.0);
        queue.add(new ShortestPathQueueObject(start, this.nodes.get(start).getDist()));

        while (queue.size() > 0) {
            String n = queue.poll().getLabel();
            if (settled.containsKey(n)) {continue;}
            settled.put(n, this.nodes.get(n).getDist());
            ArrayList<Edge> edges = this.nodes.get(n).getEdges();
            for (int i = 0; i < edges.size(); i++) {
                if (!settled.containsKey(edges.get(i).getDest().getName())) {
                    Double pLen = this.nodes.get(n).getDist() + this.nodes.get(n).getEdges().get(i).getWeight();
                    if (pLen < this.nodes.get(n).getEdges().get(i).getDest().getDist()) {
                        this.nodes.get(n).getEdges().get(i).getDest().setDist(pLen);
                        queue.add(new ShortestPathQueueObject(this.nodes.get(n).getEdges().get(i).getDest().getName(), pLen));
                    }
                }
            }
        }
        return settled;
    }

    public double findpathlength(String start, String end) {
        double result = 0.0;
        if (this.nodes.get(start).hasEdge(this.nodes.get(end))) {result += this.nodes.get(start).getEdge(this.nodes.get(end)).getWeight();}
        return result;
    }
}
