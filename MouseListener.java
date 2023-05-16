import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;


public class MouseListener extends MouseInputAdapter{
    private GUI_Tema2 graph_Panel;
    private Graph.Node drowedNode; 
  
    
    public MouseListener(GUI_Tema2 gui){
    	graph_Panel = gui;
    }
  
    
    @Override
    public void mouseClicked(MouseEvent e){
        Graph.Node clickedNode = GUI_Tema2.graph.getNode(e.getX(), e.getY());
        if(clickedNode != null){                                           // if clicked on a node
            if(GUI_Tema2.selected == null){                                      // if node is not selected
        	    graph_Panel.actionSelect(clickedNode);                                 // select the clicked nod
            } else {                                                               // else
                if(clickedNode == GUI_Tema2.selected){                               // if click on selected node
            	    graph_Panel.actionDeleteSelection();                                    // delete the node
                } else {                                                             // else
            	    graph_Panel.actionToggleEdge(clickedNode);    // toggle the edge between both nodes (first is selected and second is clicked)
                }
            }
        } else {                                                                   // else .....clicked on open space
            if(GUI_Tema2.selected != null){                                          // if a node is selected
        	    graph_Panel.actionDropSelection();                                         // release the selection
            } else {                                                                   // else..... no node selected
        	    graph_Panel.actionCreateNode(e.getX(), e.getY());                          // create a new node
            }
        }
        graph_Panel.repaint();                                             // repaint the frame
    }
  
    
    @Override
    public void mousePressed(MouseEvent e){
        Graph.Node clickedNode = GUI_Tema2.graph.getNode(e.getX(), e.getY());      // prevent dragging unless they are dragging the selected node
        if(clickedNode != null && clickedNode == GUI_Tema2.selected){
        	drowedNode = clickedNode;
        }
    }
 
    
    @Override
    public void mouseReleased(MouseEvent e){
    	drowedNode = null;
    }
  
    
    public void mouseDragged(MouseEvent e){
        if(drowedNode != null){
            drowedNode.x = e.getX(); 
            drowedNode.y = e.getY();
            graph_Panel.repaint();
        }
    }

}