package org.vcell.sybil.gui.graph.nodes;

/*   DefaultSimpleNodeShape  --- by Oliver Ruebenacker, UCHC --- July 2007 to February 2009
 *   Default shape for simple nodes
 */

import org.vcell.sybil.gui.graph.Graph;
import org.vcell.sybil.models.graphcomponents.RDFGraphComponent;

public class NodeShapeDefault extends NodeShape {

	int radius = 8;

	public NodeShapeDefault(Graph graphNew, RDFGraphComponent newSybComp) {
		super(graphNew, newSybComp);
		setColorFGSelected(java.awt.Color.black);
		setColorBG(java.awt.Color.green);
	}

}
