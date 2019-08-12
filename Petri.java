import edu.princeton.cs.algs4.*;
import java.awt.Color;
/**
  * The <tt>Petri</tt> class is able to simulate the chemotatic motion
  * of a given number of bacterias from <tt>Cel</tt> class.
  * <p>
  * This implementations uses a time-based simulation.
  * <p>
  * Also, this class depends on <i>Algorithms, 4th Edition</i>  package
  * by Robert Sedgewick and Kevin Wayne.
  *
  * @author Bruno Daiki Yamada
  * @author Rafael Zuolo Coppini Lima
*/
public class Petri{
	private Cell[] cells;
	private double size;
	private double tick;
	private double maxConc;
	private int canvasSize;
	private Draw draw; 
	private int pattern;
	
	/**
	 * Class constructor.
	 *
	 * @param cells given cells for simulation
	 * @param size the scale of the simulation screen in micrometers
	 * @param tick the time interval between ticks on time-based simulation
	 * @param maxConc the maximum concentration to be found on the simulation by the bacterias
	 * @param name the name of the experiment
	 * @param pattern the chosen patter of attractant concentration on the experiment
	 */
	
	public Petri(Cell[] cells, double size, double tick, double maxConc, String name, int pattern){ //class constructor
		this.draw = new Draw(name);
		this.cells   = cells;
		this.size    = size;
		this.tick    = tick;
		this.maxConc = maxConc;
		this.pattern = pattern;
		canvasSize   = 512;
		draw.setCanvasSize(canvasSize, canvasSize);
		draw.setPenRadius(0.0);
		draw.setXscale(-size/2, size/2);
		draw.setYscale(-size/2, size/2);
		draw.show(1);
		
		for (int i = 0; i < canvasSize; i++){ 						//prints gradient
			for (int j = 0; j < canvasSize; j++){
				int intensity = (int)((nutri((i - (canvasSize / 2)) * (size / canvasSize), (j - (canvasSize / 2)) * (size / canvasSize)) / 10) * 255);
				if (intensity > 255 || intensity < 0)
					intensity = 255;
				draw.setPenColor(255 - intensity, 255 - intensity, 255);
				draw.point((i - (canvasSize / 2)) * (size / canvasSize), (j - (canvasSize / 2)) * (size / canvasSize));
			}
		}
	}
	
	/**
	 * To a given point, returns the concentration.
	 * 
	 * @param x the horizontal coordinate
	 * @param y the vertical coordinate
	*/
	
	private double nutri(double x, double y){ 	//return the concenetration of attractant on given position
		if (pattern == 1){						//cicle pattern
			if (Math.hypot(x, y) > 10)
				return 0;
			return maxConc;
		}
		if (pattern == 0) 						//exponential pattern
			return Math.exp(-Math.hypot(x, y)) * maxConc;
		if (pattern == 2){
			int iterations = 40;
			for (int i = iterations; i >= 1; i--){
				if (Math.sqrt(x*x + y*y) < (size/(i)))
					return ((maxConc/iterations) * i);
			}
			return 0;
		}
		assert false : "Failed to acquire concentration.";
		return 0;
	}
	
	/**
	 * Runs the time based simulation one tick forward.
	*/
	
	private void swimAll(){ 					//makes all the cells swim
		for (Cell cell : cells){
			cell.swim(nutri(cell.getX(), cell.getY()), tick);
		}
	}
	
	/**
	 * Updates the screen with the current bacterias position.
	*/
	
	private void draw(){
		draw.setPenRadius(1);
		draw.setPenColor(Draw.BLACK);
		for (int i = 0; i < cells.length; i++){ //draws cells with different colors
			draw.setPenColor(Color.getHSBColor((float)i/cells.length, (float)1, (float)1.0));
			draw.filledCircle(cells[i].getX(), cells[i].getY(), size/500);
		}
		if (draw.mousePressed()){ 				//pauses the program if it's cicked
			while (draw.mousePressed()){
				
			}
			while (!draw.mousePressed()){
				
			}
			while (draw.mousePressed()){
				
			}
		}
		draw.show(10);
	}
	
	public static void main(String[] args){
		Cell[] cells_1 = new Cell[Integer.parseInt(args[0])];
		Cell[] cells_2 = new Cell[Integer.parseInt(args[0])];
		Cell[] cells_3 = new Cell[Integer.parseInt(args[0])];
		for (int i = 0; i < Integer.parseInt(args[0]); i++){ 		//randomly generates the cells
			cells_1[i] = new Cell(-40 + (80 * StdRandom.uniform()), -40 + (80 * StdRandom.uniform()), 0);
		}
		Petri petri = new Petri(cells_1, 160, 5, 10, "Petri_1", 0); //creates petri
		for (int i = 0; i < Integer.parseInt(args[0]); i++){
			cells_2[i] = new Cell(-40 + (80 * StdRandom.uniform()), -40 + (80 * StdRandom.uniform()), 0);
		}
		Petri petry = new Petri(cells_2, 160, 5, 10, "Petri_2", 1);
		for (int i = 0; i < Integer.parseInt(args[0]); i++){
			cells_3[i] = new Cell(-40 + (80 * StdRandom.uniform()), -40 + (80 * StdRandom.uniform()), 0);
		}
		Petri petrie = new Petri(cells_3, 160, 5, 10, "Petri_3", 2);
		while (true){ 												//swim stuff
			petri.swimAll();
			petry.swimAll();
			petrie.swimAll();
			petri.draw();
			petry.draw();
			petrie.draw();
		}
	}
}