import java.awt.Graphics;
import java.util.Vector;
import javax.swing.JOptionPane;

  
public class Graph {
    public Vector<Node> nodes = new Vector<Node>();                                               // List of nodes
    public Vector<Edge> edges = new Vector<Edge>();                                               // List of edges
    

    public class Node{
    	private String name;
    	private int value;
        public int x;
        public int y;
  
        public Node(String name, int value, int x, int y){
        	this.name = name;
        	this.value = value;
        	this.x = x; 
        	this.y = y;
        }
  
        public int dist(int x, int y){
            return (this.x - x)*(this.x - x) + (this.y - y)*(this.y - y);
        }
  
        public int dist(Node n){
        	return this.dist(n.x, n.y);
        }
  
        public void render(Graphics g){                                                                          // PAINT THE NODE
        	g.fillOval(this.x - Tema2.RADIUS, this.y - Tema2.RADIUS, Tema2.RADIUS * 2, Tema2.RADIUS * 2);
        	g.drawString(name + ", " + Integer.toString(value), this.x - 35, this.y - Tema2.RADIUS - 10);
        }
    }
  

    public class Edge{
    	private String name;
    	private int value;
        private Node n1;
        private Node n2;
  
        public Edge(String name, int value, Node a, Node b){
        	this.name = name;
        	this.value = value;
        	this.n1 = a; 
        	this.n2 = b;
        }
  
        public boolean matches(Node a, Node b){
            return (a == n1 && b == n2) || (a == n2 && b == n1);
        }
  
        public void render(Graphics g){                                                         // PAINT THE EDGE
        	g.drawLine(n1.x, n1.y, n2.x, n2.y);
        	g.drawString(name + ", " + Integer.toString(value), (n1.x + n2.x)/2, (n1.y + n2.y)/2);
        }
    }
  
    
    public void addNode(int x, int y){                                              // ACTIONS ...... add to List a node with x,y
    	String name = JOptionPane.showInputDialog(GUI_Tema2.graph_Panel, "Node name:");
    	String value = JOptionPane.showInputDialog(GUI_Tema2.graph_Panel, "Node value:");
    	nodes.add(new Node(name, Integer.parseInt(value), x, y));
    }
    
    
    public void addNode(Node n){                                                                     // add to List a node
    	nodes.add(n);
    }
    
    
    public void addEdge(Node n1, Node n2){                                                           // add to List an edge with node1 and node2
    	String name = JOptionPane.showInputDialog(GUI_Tema2.graph_Panel, "Edge name:");
    	String value = JOptionPane.showInputDialog(GUI_Tema2.graph_Panel, "Edge value:");
    	edges.add(new Edge(name, Integer.parseInt(value), n1, n2));
    }
    
    
    public void addEdge(Edge e){                                                                     // add to List an edge
    	edges.add(e);
    }
    
    
    public void delNode(Node n){                                                                     // delete from List a node
    	nodes.remove(n);
    }
    
    
    public void delEdge(Edge e){                                                                     // delete from List an edge
    	edges.remove(e);
    }
  
    
    public void render(Graphics g){                                                                 // render/draw the nodes and adges from Lists
        for(Edge e : edges){
        	e.render(g);
        }
        for(Node n : nodes){
        	n.render(g);
        }
    }
  
    
    public Edge getEdge(Node a, Node b){                                                           // return the edge between nodes a and b
        for(Edge e : edges){
            if (e.matches(a, b)){
            	return e;
            }
        }
        return null;                                                                               // if was not found in List
    }
  
    
    public Node getNode(int x, int y){
        if(nodes.isEmpty()){                                                                       // no nodes to match
        	return null;
        } 
        Node closest = nodes.get(0);                                                               // start with node[0] as closest node
        int close = closest.dist(x,y);                                                             // store the distance
        for(Node n : nodes){                                                                       // and search all the List for a closest node
            int d = n.dist(x,y);
            if(d < close){                                                                         // if found a closer Node
                close = d;                                                                         // store distance and closer node
                closest = n;
            }
        }
        return (close < Tema2.PROXIMITY) ? closest : null;                                              // return closest node or null, if not any
    }
  
    
    public boolean unconnected(Node n){                                                            // return true if node has not any edge
        for(Edge e : edges){
        	if(e.n1 == n || e.n2 == n){
        		return false;
        	}
        }
        return true;
    }
  
}