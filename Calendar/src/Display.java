import javax.swing.JPanel;
import javax.swing.*;
import java.awt.BorderLayout;

public class Display extends JPanel {
	
	public Display() {
		JPanel newPanel = new JPanel(new BorderLayout());
		//newPanel.setLayout(new GridBagLayout());
		JLabel label = new JLabel("Enter username: ");
		
		newPanel.add(label, BorderLayout.NORTH);
		
		add(newPanel);
		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		Display calendar = new Display();
	}
}
