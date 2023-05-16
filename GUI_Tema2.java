import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
  

public class GUI_Tema2 extends JPanel{
	private static final long serialVersionUID = 3983157687677813216L;
	public static GUI_Tema2 graph_Panel;                                       // panel to draw graph inside
	public static Graph graph;                                                 // from Graphics library
    public static Graph.Node selected;                                         // selected node
    
    
    static void Run() {                                                        // create the frame
		JFrame frame = new JFrame("Application with a graphic interface that drow a graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GUI_Tema2());
		frame.setSize(1500, 800);
		frame.setVisible(true);
	}
  
    
    public GUI_Tema2(){                                              // create the panel to draw graph inside
    	graph_Panel = this;
    	graph = new Graph();
    	MouseListener mouseListener = new MouseListener(graph_Panel);
    	this.addMouseListener(mouseListener);
    	this.addMouseMotionListener(mouseListener);
    }
  
    
    public void paint(Graphics g){                                  // canvas for graphics (nodes and edges)
    	g.setColor(Color.white);
    	g.fillRect(0,0,5000,5000);
    	g.setColor(Color.black);
    	graph.render(g);
    	g.setColor(Color.magenta);
    	if(selected != null){
    		selected.render(g);
    	}
    }
  
    
    public void actionDropSelection(){                     // ACTIONS ........ drop selection
    	selected = null;
    }
    
    
    public void actionSelect(Graph.Node n){                                    // select a node
    	selected = n;
    }
    
    
    public void actionCreateNode(int x, int y){                                // create a node
    	graph.addNode(x,y);
    }
  
    
    public void actionDeleteSelection(){                                       // node delete
    	if(graph.unconnected(selected)){
    		graph.delNode(selected);
    		selected = null;                                                 // to not hang by a deleted node
    	}
    }
  
    
    public void actionToggleEdge(Graph.Node n){                              // add / delete an edge
    	Graph.Edge e = graph.getEdge(selected, n);
    	if(e == null){
    		graph.addEdge(selected,n);
    	} else {
    		graph.delEdge(e);
    	}
    }
    
}
