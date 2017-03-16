import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	
	private static final int frameX = 1280, frameY = 720;
	private static final String title = "Galton Board Simulation";
	private int n, nTodo, nDone; // number of balls, balls to be drawn, and balls already drawn
	private static boolean simRunning; // true as long as nTodo > 0
	private Random rand;
	private int[] columns;
	
	public Main(int n, int columns) {
		this.n = n;
		nTodo = n;
		simRunning = true;
		this.columns = new int[columns];
		rand = new Random();
	}
	
	public void placeBall() { // places 1 ball at a time
		int bin = 0, choice;
		for (int i=0; i<columns.length; i++) {
			choice = rand.nextInt(2);
			if (choice==0 && bin < columns.length-1)
				bin+= 1;
		}
		columns[bin] += 1;
		nTodo-=1;
		nDone+=1;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0,  0, frameX, frameY);
		for(int i=0; i<columns.length; i++) {
			g2d.setColor(Color.WHITE);
			g2d.drawRect(i + i*(frameX/columns.length) - 10, 0, frameX/columns.length, frameY); // draw outlines of columns
			g2d.fillRect(i + i*(frameX/columns.length) - 10, 0, frameX/columns.length, (frameY - 40) - columns[i]/(3*(n/10000)*(int)(Math.log10(columns.length))+1)); // draw columns in reverse
		}
	}
	
	public static void main(String args[]) throws InterruptedException {
		JFrame frame = new JFrame(title); // create new frame with title
		Main simulation = new Main(10000, 21); // create instance of simulation (number of balls, number of columns)
		frame.setSize(frameX, frameY); // choose frame size
		frame.setResizable(false); // fixed frame size
		frame.setLocationRelativeTo(null); // frame starts in the middle of screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // frame terminates on close
		frame.setVisible(true); // frame appears on screen
		frame.add(simulation); //add instance of simulation to frame
		
		long runtime = System.nanoTime();
		
		System.out.print("Simulation start ...");
		while(simRunning) {
			simulation.placeBall();
			simulation.repaint();
			if (simulation.nTodo <= 0) // loop as long as there are more balls to place
				simRunning = false;
		}
		runtime = System.nanoTime() - runtime;
		System.out.println(" finished! \n" + "runtime: " + (runtime/1000000) + "ms");
	}
}
