import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.*;

class Day {
	int date;
	//char weekday;
	int weekday;
	
	int calculateWeekday() {
		
		return 0;
	}
	
	Day(String day) {
		date = Integer.parseInt(day);
		System.out.println("The date is : " + date);
	}
	
	Day(int num) {
		date = num;
		
		calculateWeekday();
	}
}

/*class Week {
	int weekNum;
	Day week[];
	
	Week(){
		
	}
}*/

class Month {
	int monthNum;
	//Week month[];
	Day month[];
	int totalDays[] = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	Month(String currMo){
		monthNum = Integer.parseInt(currMo);
		System.out.println("The month is: " + monthNum);
		
	}
	
	Month(int num){
		monthNum = num;
		month = new Day[totalDays[monthNum-1]];
		for(int d = 1; d <= totalDays[monthNum-1]; d++) {
			month[d-1] = new Day(d);
		}
	}
}

class Year {
	Month year[];
	int yearNum;
	
	Year() {
		year = new Month[12];
		for(int mo = 1; mo <= 12; mo++) {
			year[mo-1] = new Month(mo);
			
		}
		
	}
	
	int getDate(int monthNum) {
		return year[monthNum].totalDays[monthNum];
	}
}

public class Calendar extends JFrame {
	
	private JPanel p;
	private int days;
	GridLayout experiment = new GridLayout(0,7);
	
	Year currentYear;
	int currentMo, currentDay;
	
	public Calendar() {
		
		//Initialize based on current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		System.out.println(currentDate);
		
		String dateComponents[] = currentDate.split("/");
		
		/*for (int x = 0; x < dateComponents.length; x++)
			System.out.println(dateComponents[x]);*/
		
		//Initialize the Year object
		currentYear = new Year();
		
		currentYear.yearNum = Integer.parseInt(dateComponents[0]);
		
		currentMo = Integer.parseInt(dateComponents[1]);
		//new Month(dateComponents[1]);
		//new Day(dateComponents[2]);
		
	}
	
	public void setButtons(final Container p) {
		
		final JPanel components = new JPanel();
		components.setLayout(experiment);
		JPanel title = new JPanel();
		title.setLayout(new GridLayout(0,1));
		JPanel headings = new JPanel();
		headings.setLayout(new GridLayout(0,9));
		JPanel leftNavigation = new JPanel();
		JPanel rightNavigation = new JPanel();
		leftNavigation.setLayout(new GridLayout(0,1));
		rightNavigation.setLayout(new GridLayout(0,1));
		//p.setVisible(true);
		
		//Set number of days to display based on currentMo 
		days = currentYear.getDate(currentMo-1);
		
		//Days of the month
		for (int count = 0; count < days; count++)
		{
			components.add(new JButton(Integer.toString(count+1)));
		
		}
		
		//Day of the Week labels
		headings.add(new Label(""));
		headings.add(new Label("Sunday"));
		headings.add(new Label("Monday"));
		headings.add(new Label("Tuesday"));
		headings.add(new Label("Wednesday"));
		headings.add(new Label("Thursday"));
		headings.add(new Label("Friday"));
		headings.add(new Label("Saturday"));
		headings.add(new Label(""));
		
		//Navigation buttons
		leftNavigation.add(new JButton("Previous"));
		rightNavigation.add(new JButton("Next"));
		
		title.add(new Label("Month: " + Integer.toString(currentMo)));
		
		//Set gaps and size
		experiment.setHgap(0);
		experiment.setVgap(0);
		experiment.layoutContainer(components);
		
		//Add components to panel
		p.add(title, BorderLayout.NORTH);
		p.add(headings, BorderLayout.NORTH);
		p.add(components, BorderLayout.CENTER);
		p.add(leftNavigation, BorderLayout.WEST);
		p.add(rightNavigation, BorderLayout.EAST);
		
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
