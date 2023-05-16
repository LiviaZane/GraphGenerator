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
  
        public Node(String name, int value, int x, int y){                                       // constructor
        	this.name = name;
        	this.value = value;
        	this.x = x; 
        	this.y = y;
        }
  
        public int distance(int x, int y){                                                 // distance between current this.node and a point (x, y)                                
            return (this.x - x)*(this.x - x) + (this.y - y)*(this.y - y);
        }
  
        public void render(Graphics g){                                                                          // PAINT THE NODE
        	g.fillOval(this.x - Tema2.RADIUS, this.y - Tema2.RADIUS, Tema2.RADIUS * 2, Tema2.RADIUS * 2);
        	g.drawString(this.name + ", " + Integer.toString(this.value), this.x - Tema2.RADIUS - 20, this.y - Tema2.RADIUS - 10);
        }
    }
  

    public class Edge{
    	private String name;
    	private int value;
        private Node node1;
        private Node node2;
  
        public Edge(String name, int value, Node a, Node b){                                  // constructor
        	this.name = name;
        	this.value = value;
        	this.node1 = a; 
        	this.node2 = b;
        }
  
        public boolean matches(Node a, Node b){                                                // verify if nodes a and b are the same node
            return (a == node1 && b == node2) || (a == node2 && b == node1);
        }
  
        public void render(Graphics g){                                                                         // PAINT THE EDGE
        	g.drawLine(node1.x, node1.y, node2.x, node2.y);
        	g.drawString(name + ", " + Integer.toString(value), (node1.x + node2.x)/2, (node1.y + node2.y)/2);
        }
    }
  
    
    public void addNode(int x, int y){                                              // ACTIONS ...... add to List a node with x,y
    	String name = JOptionPane.showInputDialog(GUI_Tema2.graph_Panel, "Node name:");
    	String value = JOptionPane.showInputDialog(GUI_Tema2.graph_Panel, "Node value:");
    	nodes.add(new Node(name, Integer.parseInt(value), x, y));
    }
    
    
    public void addEdge(Node n1, Node n2){                                                           // add to List an edge with nodes n1 and n2
    	String name = JOptionPane.showInputDialog(GUI_Tema2.graph_Panel, "Edge name:");
    	String value = JOptionPane.showInputDialog(GUI_Tema2.graph_Panel, "Edge value:");
    	edges.add(new Edge(name, Integer.parseInt(value), n1, n2));
    }
    
    
    public void deleteNode(Node n){                                                                     // delete from List a node
    	nodes.remove(n);
    }
    
    
    public void deleteEdge(Edge e){                                                                     // delete from List an edge
    	edges.remove(e);
    }
  
    
    public void render(Graphics g){                                                                 // render/draw the nodes and edges from Lists
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
  
    
    public Node getNode(int x, int y){                                    // get the node ........closest node around a click point (x, y)
        if(nodes.isEmpty()){                                                                       // if List nodes is empty
        	return null;
        } 
        Node closest = nodes.get(0);                                                               // start with node[0] as closest node
        int close = closest.distance(x,y);                                                             // store the distance
        for(Node n : nodes){                                                                       // and search all the List for a closest node
            int d = n.distance(x,y);
            if(d < close){                                                                         // if found a closer Node
                close = d;                                                                         // store distance and closer node
                closest = n;
            }
        }
        return (close < Math.pow(Tema2.RADIUS, 2)) ? closest : null;                               // return closest node or null, if not any
    }
  
    
    public boolean unconnected(Node n){                                                            // return true if node has not any edge
        for(Edge e : edges){
        	if(e.node1 == n || e.node2 == n){
        		return false;
        	}
        }
        return true;
    }
  
}