package a6;

import java.util.ArrayList;
import java.util.Map;

public interface Node {

     /* You will include the method signatures (return type, name, and arg types) for any node methods you
    need in this file. */

    /*Hint: Make sure you update the Node interface in Node.java when you add a new method implementation
    in NodeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

     /**
      * @return the name of the node
      */
     String getName();
     ArrayList<Edge> getEdges();

     Edge getEdge(Node other);

     Integer getIndeg(Map<String, Node> nodes);

     void decIndeg();

     void incIndeg();
     boolean hasEdge(Node other);

     void delEdge(Node other);

     void setDist(Double n);

     void addDist(Double n);

     double getDist();

}
