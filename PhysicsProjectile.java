
// This program computes the trajectory of a projectile.

import java.util.*;
import java.awt.*;

public class PhysicsProjectile {

   public static final double ACCELERATION = -9.81;
   public static final double ballArea = 1/3;
   public static final double xSectionArea = 3.1415926535 * Math.pow(ballArea, 2);
   
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      giveIntro();
      
      System.out.print("velocity (meters/second)? ... (100 is reasonable)");
      double velocity = console.nextDouble();
      System.out.print("angle (degrees)? ... (80 is reasonable)");
      double angle = Math.toRadians(console.nextDouble());
      System.out.print("number of steps to display? ... (pick between 30 and 300)");
      int steps = console.nextInt();
      System.out.println();
      
      printTable(velocity, angle, steps);
   }
   
   
//----------------------------------------------------------------------------------------
/*   Method printTable

     Pre-Conditions:
          User has chosen values for velocity, angle, and number of steps to be shown,
	  for the projectile.

     Post-Conditions:
          Prints a table showing the trajectory of an object given its initial
          velocity and angle and including the given number of steps in the table.
          Also creates a window, showing graphical points of the projectile's path. */

   public static void printTable(double velocity, double angle, int steps) {
   
      double xVelocity = velocity * Math.cos(angle);
      double yVelocity = velocity * Math.sin(angle);
      double totalTime = (-2.0 * yVelocity) / ACCELERATION;
      double timeIncrement = totalTime / steps;
      double xIncrement = xVelocity * timeIncrement;
      
      
      double x = 0.0;
      double y = 0.0;
      double t = 0.0;
      int width = 900;
      int height = 500;
      //System.out.println("step\tx\ty\ttime");
      //System.out.println("0\t0.0\t0.0\t0.0");
      DrawingPanel panel = new DrawingPanel(width, height);
      panel.setBackground(Color.white);
      Graphics g = panel.getGraphics();
      for (int i = 1; i <= steps; i++) {
         t += timeIncrement;
         x += xIncrement;
         y = displacement(yVelocity, t, ACCELERATION);
         g.setColor(Color.RED);
         g.fillOval((int)x - (int)((1/4) * xSectionArea * Math.pow(xVelocity, 2)), height - (int)y - (int)((1/4) * xSectionArea * Math.pow(yVelocity, 2)), 20, 20);
         g.setColor(Color.BLACK);
         g.drawOval((int)x - (int)((1/4) * xSectionArea * Math.pow(xVelocity, 2)), height - (int)y - (int)((1/4) * xSectionArea * Math.pow(yVelocity, 2)), 20, 20);
         System.out.println(i + "\t" + round2(x) + "\t" + round2(y) + "\t" + round2(t));
      }
   }
   
//----------------------------------------------------------------------------------------
/*   Method giveIntro

     Pre-Conditions:
          Gravity, area of projectile, and scanner are initialized.

     Post-Conditions:
          Gives a brief description to the user, of what this program does. */

   public static void giveIntro() {
   
      System.out.println("This program computes the trajectory of a projectile, given");
      System.out.println("its initial velocity and its angle relative to the horizontal.");
      System.out.println();
   }
   
//----------------------------------------------------------------------------------------   
/*   Method displacement

     Pre-Conditions:
          The projectile's current y-velocity, elapsed time, and acceleration of gravity
	  for the previous step are known and passed in.

     Post-Conditions:
          Returns the vertical displacement for the projectile. */

   public static double displacement(double v, double t, double a) {
   
      return (v * t) + (0.5 * a * t * t);
   }
      
//----------------------------------------------------------------------------------------   
/*   Method round2

     Pre-Conditions:
          Current x-position, y-position, and elapsed time are known and passed in one
	  at a time.

     Post-Conditions:
          Rounds the input parameter to 2 digits after the decimal point. Allows readability
          in the table that tracks position of the projectile. */

   public static double round2(double n) {
   
      return Math.round(n * 100.0) / 100.0;
   }
   
//----------------------------------------------------------------------------------------
}
