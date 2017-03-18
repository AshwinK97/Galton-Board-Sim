package galtonBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	
	private static final long serialVersionUID = 4190408071671335505L; // to get rid of the warning in eclipse
	private static final int frameX = 1280, frameY = 720; // width and height for the JFrame
	private static final String title = "Galton Board Simulation"; // title for the JFrame
	private int n, nTodo; // number of balls and balls left to be drawn
	private static boolean simRunning; // true as long as nTodo > 0
	private Random rand; // to generate random numbers
	private int[] columns; // to store the number of balls in each column
	
	public Main(int n, int columns) {
		this.n = n; // total number of balls to place
		nTodo = n; // number of balls to place starts at total
		simRunning = true; // when simulation starts
		this.columns = new int[columns]; // initialize columns array to the specified number of columns
		rand = new Random(); // to generate random numbers
	}
	
	public void placeBall() { // places a ball
		int bin = 0, choice;
		for (int i=0; i<columns.length; i++) { // performs choose operation #columns times
			choice = rand.nextInt(2); // generate random 0 or 1
			if (choice==1 && bin < columns.length-1) // if 1, move 1 to the right 
				bin+= 1;
			// if 0, keep ball in same place
		}
		columns[bin] += 1; // place ball in the column we found
		nTodo-=1; // 1 less ball to place
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0,  0, frameX, frameY);
		for(int i=0; i<columns.length; i++) {
			g2d.setColor(Color.white);
			g2d.drawRect(i + i*(frameX/columns.length) - 10, 0, frameX/columns.length, frameY); // draw outlines of columns
			g2d.fillRect(i + i*(frameX/columns.length) - 10, 0, frameX/columns.length, (frameY - 35) - columns[i]/(3*(n/10000)*(int)(Math.log10(columns.length))+1)); // draws columns in reverse
		}
	}
	
	public static void skipLine() {
		for (int i=0; i<50; i++)
			System.out.println();
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int input = 0;
		JFrame frame = new JFrame(title); // create new frame with title
		Main simulation;
		
		frame.setSize(frameX, frameY); // choose frame size
		frame.setResizable(false); // fixed frame size
//		frame.setLocationRelativeTo(null); // frame starts in the middle of screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // frame terminates on close
		frame.setVisible(false);
		
		skipLine();
		while(input >= 0) { // loops forever, until window is closed
			System.out.print("Enter # of balls: "); // prompt the user to enter the number of balls to be dropped
			input = in.nextInt(); // stores next integer entered by the user
			if (input <= 0) { // check if the user entered a number less than 1
				System.out.println("<Enter only positive integers> \n\n");
				input = 0;
				continue; // skips everything below this until the loop condition
			}
			simulation = new Main(input, 25); // create instance of simulation (number of balls, number of columns)
			frame.add(simulation); //add instance of simulation to frame
			frame.setFocusable(true); // make frame main focus
			frame.setVisible(true); // frame appears on screen
			frame.setAlwaysOnTop(true); // shows frame always on top
			frame.setAlwaysOnTop(false);
			
			long runtime = System.nanoTime(); // get the run times
			System.out.print("Simulation running ..."); // tell the user that the simulation is starting
			while(simRunning) { // run simulation as long as simRunning is true
				simulation.placeBall(); // places a single ball on the board
				simulation.repaint(); // repaints the board
				if (simulation.nTodo <= 0) // if there are no more balls to place
					simRunning = false; // change simRunning to false, loop will exit
			}
			runtime = System.nanoTime() - runtime; // get runtime by subtracting current time - previously saved time
			System.out.println(" done. \n" + "Runtime: " + (runtime/1000000) + "ms \n\n"); // tell the user simulation is done and output runtime
		}
		in.close();
	}
}
