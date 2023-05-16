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
		JFrame frame = new JFrame("Application with a graphic interface that draw a graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GUI_Tema2());                           // add GUI_Tema2 (graph_Panel) object to the frame
		frame.setSize(1500, 800);
		frame.setVisible(true);
	}
  
    
    public GUI_Tema2(){                                                         // create the panel to draw graph inside
    	graph_Panel = this;
    	graph = new Graph();                                                    // create a graphic object/area from Graphic library
    	MouseListener mouseListener = new MouseListener(graph_Panel);           // create a MouseListener
    	this.addMouseListener(mouseListener);                                   // add the MouseListener to graph_Panel
    	this.addMouseMotionListener(mouseListener);                             // add the MouseMotionListener to graph_Panel
    }
  
    
    public void paint(Graphics g){                                              // canvas to draw graphic objects (nodes and edges)
    	g.setColor(Color.white);                                                // background colour
    	g.fillRect(0,0,1500,800);                                                // rectangle for the graphic area (same dimension as the frame)
    	g.setColor(Color.black);                                                // colour of the object
    	graph.render(g);                                                        // render/make objects (node/edge) in the graphic area (graph)
    	g.setColor(Color.magenta);                                              // colour of the selected object
    	if(selected != null){                                                   // when select an object....
    		selected.render(g);                                          // selected object is render/make again with selected colour (magenta)
    	}
    }
  
      
    
    public void actionSelectNode(Graph.Node n){                         // ACTIONS FOR GRAPHICS ........ select a node
    	selected = n;
    }
    
    
    public void actionReleaseSelectedNode(){                                                            // release selection
    	selected = null;
    }
    
    
    public void actionCreateNode(int x, int y){                                                      // create a node
    	graph.addNode(x,y);
    }
  
    
    public void actionDeleteNode(){                                                                  // delete a node
    	if(graph.unconnected(selected)){                                                                    // if selected and selected again
    		graph.deleteNode(selected);                                                                        // then delete the node
    		selected = null;                                                                                // to not hang by a deleted node
    	}
    }
  
    
    public void actionSwitchEdge(Graph.Node n){                                            			 // switch (add or delete) an edge
    	Graph.Edge e = graph.getEdge(selected, n);                                                      // select the edge of the n node, if any
    	if(e == null){                                                                                  // if not exist an edge
    		graph.addEdge(selected, n);                                                                        // create an edge
    	} else {                                                                                        // else (exist an edge)
    		graph.deleteEdge(e);                                                                                  // delete it
    	}
    }
    
}
