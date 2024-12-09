package swing;
import java.awt.*;

import javax.swing.*;

public class ListPaneBuilder {
	public void buildlistpane(JFrame frame) {
		String[] data = {"hello.java", "world.java"};
		JList<String> list  = new JList<>(data);
		
		JScrollPane scrollPane4 = new JScrollPane(list);
		
		scrollPane4.setPreferredSize(new Dimension(200,800));
		frame.add(scrollPane4, BorderLayout.EAST);
	}
}
