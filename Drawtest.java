import edu.princeton.cs.algs4.*;
public class Drawtest{
	private static double nutri(double x, double y){
		return Math.exp(-Math.hypot(x, y)) * 10;
	}
	
	public static void main(String[] args){
		int canvasSize = 512;
		double size = 160;
		StdDraw.setCanvasSize(canvasSize, canvasSize);
		StdDraw.setXscale(-80, 80);
		StdDraw.setYscale(-80, 80);
		StdDraw.setPenRadius(0.0);
		StdDraw.show(1);
		//StdDraw.point(-0.01,-0.01);
		for (int i = 0; i < canvasSize; i++){
			for (int j = 0; j < canvasSize; j++){
				if (i == 256)
					StdOut.println(nutri((i - (canvasSize / 2)) * (size / canvasSize), (j - (canvasSize / 2)) * (size / canvasSize)));
				StdDraw.setPenColor(0, 0, (int)((nutri((i - (canvasSize / 2)) * (size / canvasSize), (j - (canvasSize / 2)) * (size / canvasSize)) / 10) * 255));
				//StdOut.println((j - (canvasSize / 2)) * (size / canvasSize));
				StdDraw.point((i - (canvasSize / 2)) * (size / canvasSize), (j - (canvasSize / 2)) * (size / canvasSize));
				//StdDraw.show(1);
			}
		}
		StdDraw.show(1);
		StdOut.println("Done");
	}
}