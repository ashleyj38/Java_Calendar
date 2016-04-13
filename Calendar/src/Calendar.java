import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.*;

class Day {
	int date;
	//char weekday;
	int weekday;
	int anchor = 2;
	
	int calculateWeekday(int year, int month, int daysInMonth[]) {
		
		int step1, step2, step3, step4, step5, step6;
		
		//Step 1: How many times does 12 fit into the last two year digits
		double roundedYr = 100 * Math.floor((year+50)/100);
		int diff = year - (int)roundedYr;
		step1 = diff/12;
		//System.out.println("step 1: " + step1);
		
		//Step2: Difference btn last two 
		step2 = diff%12;
		System.out.println("step 2: " + step2);
		
		//Step3: 4 in results of 2
		step3 = step2/4;
		//System.out.println("step 3: " + step3);
		
		step4 = anchor;
		//System.out.println("step 4: " + step4);
		
		//Step5: Sum 1 through 4
		step5 = step1 + step2 + step3 + step4;
		//System.out.println("step 5: " + step5);
		
		//Step6: Remainder of 7
		step6 = Math.abs(step5%7);
		
		//System.out.println("The final result of calculation is : " + step6);
		
		//Calculate weekday based off of doomsday/step 6
		
		if (month > 4){
			int monthDiff = month - 4;
			int dayDiff = 26;
			
			for (int start = month-2; start < 4; month--) {
				dayDiff += daysInMonth[start-1];
			}
			
			int weekdayDiff = dayDiff%7;
			
			weekday = anchor + weekdayDiff;
			
			if(weekday > 6) {
				weekday -= 7;
			}
		}
		else if (month < 4){
			int monthDiff = 4 - month;
			int dayDiff = 4;
			
			for (int start = month; start > 4; month++) {
				dayDiff += daysInMonth[start-1];
			}
			
			int weekdayDiff = dayDiff%7;
			
			weekday = anchor + weekdayDiff;
			
			if(weekday > 6) {
				weekday -= 7;
			}
		}
		else {
			
			//System.out.println("We make it here!");
			weekday = step6 - 3;
			
			if (weekday < 0)
				weekday += 7;
		}
		
		//System.out.println("The month starts on weekday " + weekday);
		
		return weekday;
	}
	
	Day(String day) {
		date = Integer.parseInt(day);
		System.out.println("The date is : " + date);
	}
	
	Day(int num) {
		date = num;
		
		//calculateWeekday();
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
	
	int calculateDay(int year, int num) {
		return this.month[num].calculateWeekday(year, num, totalDays);
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
	
	int getStartDay(int monthNum) {
		return year[monthNum].calculateDay(yearNum,monthNum);
	}
}

public class Calendar extends JFrame {
	
	private JPanel p;
	private int days;
	GridLayout experiment = new GridLayout(0,7);
	
	Year currentYear;
	int currentMo, currentDay, weekdayStart;
	
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
		weekdayStart = currentYear.getStartDay(currentMo);
		
		for (int placement = 0; placement < weekdayStart; placement++) {
			components.add(new JButton(""));
		}
		System.out.println("The weekday returned is: " + weekdayStart);
		
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
