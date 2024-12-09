package swing;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class BottomPaneBuilder {
    public void buildbottompane(JFrame frame) {
    	JTextPane textPane2 = new JTextPane();
		textPane2.setText("Compiling.......");
		JScrollPane scrollPane2 = new JScrollPane(textPane2);
		scrollPane2.setPreferredSize(new Dimension(1260,150));
		frame.getContentPane().add(scrollPane2, BorderLayout.SOUTH);
    }
}
