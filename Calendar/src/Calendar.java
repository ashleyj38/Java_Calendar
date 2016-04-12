import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.*;

class Day {
	int date;
	char weekday;
	
	Day(String day) {
		date = Integer.parseInt(day);
		System.out.println("The date is : " + date);
	}
}

class Week {
	int weekNum;
	Day week[];
	
	Week(){
		
	}
}

class Month {
	int monthNum;
	Week month[];
	
	Month(String currMo){
		monthNum = Integer.parseInt(currMo);
		System.out.println("The month is: " + monthNum);
	}
}

class Year {
	Month year[];
	
	Year() {
		
	}
}

public class Calendar extends JFrame {
	
	private JPanel p;
	private int days = 30;
	GridLayout experiment = new GridLayout(0,7);
	
	public Calendar() {
		
		//Initialize based on current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		System.out.println(currentDate);
		
		String dateComponents[] = currentDate.split("/");
		
		for (int x = 0; x < dateComponents.length; x++)
			System.out.println(dateComponents[x]);
		
		new Month(dateComponents[1]);
		new Day(dateComponents[2]);
		
	}
	
	public void setButtons(final Container p) {
		
		final JPanel components = new JPanel();
		components.setLayout(experiment);
		JPanel headings = new JPanel();
		headings.setLayout(experiment);
		//p.setVisible(true);
		
		for (int count = 0; count < days; count++)
		{
			components.add(new JButton("Button1"));
		
		/*components.add(new JButton("Button1"));
		components.add(new JButton("Button1"));
		components.add(new JButton("Button1"));
		components.add(new JButton("Button1"));
		components.add(new JButton("Button1"));
		components.add(new JButton("Button1"));
		components.add(new JButton("Button1"));
		components.add(new JButton("Button1"));
		components.add(new JButton("Button1"));
		components.add(new JButton("Button1"));
		components.add(new JButton("Button1"));*/
		}
		
		headings.add(new Label("Sunday"));
		headings.add(new Label("Monday"));
		headings.add(new Label("Tuesday"));
		headings.add(new Label("Wednesday"));
		headings.add(new Label("Thursday"));
		headings.add(new Label("Friday"));
		headings.add(new Label("Saturday"));
		
		
		experiment.setHgap(0);
		experiment.setVgap(0);
		experiment.layoutContainer(components);
		
		p.add(headings, BorderLayout.NORTH);
		p.add(components, BorderLayout.CENTER);
		
	}
	
	private static void showGUI() {
		Calendar frame = new Calendar();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setButtons(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new GridLayoutTest();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showGUI();
			}
		});
	}

}
