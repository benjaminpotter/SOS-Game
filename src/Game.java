import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Game extends JFrame {
   
   public static Game instance; // singleton pattern
  
   private GraphicsWindow graphics; // the graphics window, custom class
   
   private int[][] board = new int[10][10]; // stores the board information
   private List<Line> lines = new ArrayList<Line>(); // stores the line information
   
   private int[] points = {0, 0};
   private JLabel pointDisplay;
   
   public Game() {
      super("SOS Game");
      
      // singleton pattern cont.
      if (instance != null) {
         System.out.println("More than one game running");
      } else {
         instance = this;
      }
      
      setSize(516, 589); // set the size
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      graphics = new GraphicsWindow(board, lines.toArray(new Line[0])); // create the graphics window object
      add(graphics, "Center");
      
      JPanel buttonGUI = new JPanel();
      
      JButton player1 = new JButton("Player 1");
      player1.addActionListener(new ButtonListener(0));
      buttonGUI.add(player1);
      
      pointDisplay = new JLabel ("0 : 0");
      buttonGUI.add(pointDisplay);
      
      JButton player2 = new JButton("Player 2");
      player2.addActionListener(new ButtonListener(1));
      buttonGUI.add(player2);
      
      add(buttonGUI, "South");
            
      setVisible(true);
      
      System.out.println("Created Game.");
   }
      
   public void click(int x, int y) {
      if (x > 9 || y > 9)
         return;
   
      int index = board[x][y];
      
      if (index == 2) {
         index = 0;
      } else {
         index++;
      }
      
      System.out.println(index);
      board[x][y] = index;
      graphics.updateBoard(board, lines.toArray(new Line[0]));
   }
   
   public void drag(int x1, int y1, int x2, int y2) {
      lines.add(new Line(x1, y1, x2, y2));
      graphics.updateBoard(board, lines.toArray(new Line[0]));
   }
   
   public void updatePoints(int playerIndex) {
      points[playerIndex] += 1;
      pointDisplay.setText(points[0] + " : " + points[1]);
   }
   
   public static void main(String[] args) {
      new Game(); // creates the game
   }
}