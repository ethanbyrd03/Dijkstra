package a6;

public class EdgeImpl implements Edge {
    /* You will include the implementations for any edge methods you need in this file. */

    /*Hint: Make sure you update the Edge interface in Edge.java when you add a new method implementation
    in EdgeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */
    private Node source;

    private Node dest;

    private double weight;

    public EdgeImpl(Node src, Node dst, double wgt) {
        this.source = src;
        this.dest = dst;
        this.weight = wgt;
    }
    /*Hint: Make sure you update the Edge interface in Edge.java when you add a new method implementation
    in EdgeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */
    public Node getSource() {return this.source;}

    public Node getDest() {return this.dest;}

    public double getWeight() {return this.weight;}

    /*Also, any edge fields you want to add for the object should go in this file.  */

}
