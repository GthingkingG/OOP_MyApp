package swing;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreePaneBuilder {
	public void buildtreepane(JFrame frame) {
		DefaultMutableTreeNode src = new DefaultMutableTreeNode("SRC");
		JTree tree = new JTree(src);
		
		DefaultMutableTreeNode gui = new DefaultMutableTreeNode("GUI");
		DefaultMutableTreeNode lib = new DefaultMutableTreeNode("lib");
		
		DefaultMutableTreeNode hello = new DefaultMutableTreeNode("hello.java");
		DefaultMutableTreeNode world = new DefaultMutableTreeNode("world.java");
		
		src.add(gui);
		src.add(lib);
		gui.add(hello);
		lib.add(world);
		
		JScrollPane scrollPane3 = new JScrollPane(tree);
		scrollPane3.setPreferredSize(new Dimension(200,800));
		frame.add(scrollPane3, BorderLayout.WEST);
	}
}
