import edu.princeton.cs.algs4.*;
/**
  * The <tt>Cell</tt> class is a program which represents a single
  * cell for the <tt>Petri</tt> class.
  * <p>
  * This class features a random walk based motile funcion for concentrations
  * given by the client. It also implements a brownian direction drift.
  * <p>
  * Also, this class depends on <i>Algorithms, 4th Edition</i>  package
  * by Robert Sedgewick and Kevin Wayne.
  *
  * @author Bruno Daiki Yamada
  * @author Rafael Zuolo Coppini Lima
*/
public class Cell {
    private double posX;
    private double posY;
    private double runAngle;
    private double concOld;     // "memoria" da concentracao
    private double tumbleProb = 0.1;  
    private final double speed = .16; // run speed
    private final double probCutOff = 0.2; // normalizador da probabilidade pelo tempo
	
    /**
	 * Class constructor.
	 * 
	 * @param posX initial horizontal coordinate of the bacteria
	 * @param posY initial verital coordinate of the bacteria
	 * @param conc concentration of the initial point
	*/
    public Cell (double posX, double posY, double conc) {
        this.posX     = posX;
        this.posY     = posY;
        this.concOld  = conc;
        this.runAngle = Math.random() * 2 * Math.PI;
    }
	
	/**
	 * Returns the current horizontal coordinate.
	*/
    public double getX () {
        return posX;
    }
    
	/**
	 * Returns the current vertical coordinate.
	*/
    public double getY () {
        return posY;
    }

	private void brown () {
		this.runAngle += (StdRandom.random() * Math.PI/6) - Math.PI/12;
	}
    
	/**
	 * Runs the simulations for a single client given tick. It also may change direction due to
	 * brownian motion and tumble probability.
	 *
	 * @param concNew the concentration on the current point.
	 * @param tick the interval of time between the tick on the time-based simulation.
	*/
    public void swim (double concNew, double tick) {
		this.brown();
		if (concNew == 0)
			tumbleProb = probCutOff/2;
		else
			tumbleProb = probCutOff * ( 1 / (1 + Math.exp((concNew - concOld)/ concNew)));
		
        concOld    = concNew;
        if (Math.random() < tumbleProb) {
            runAngle = Math.random() * 2 * Math.PI;
		}
        posX += Math.cos(runAngle) * speed * tick;
        posY += Math.sin(runAngle) * speed * tick;
    }
}


//