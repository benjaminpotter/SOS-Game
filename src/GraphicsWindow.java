import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.Math;
import javax.imageio.*;

public class GraphicsWindow extends JComponent {
  
   private final static String GRID_IMG = "imgs/grid.jpg";
   private final static String S_IMG = "imgs/s.jpg";
   private final static String O_IMG = "imgs/o.jpg";
   private final static String BLANK_IMG = "imgs/blank.jpg";
   public static BufferedImage[] pieces = FileManager.loadImages(new String[]{BLANK_IMG, S_IMG, O_IMG, GRID_IMG});
   public static MouseHandler mouseHandler = new MouseHandler();  
     
   private int[][] board;
   private Line[] lines;
   
   public GraphicsWindow(int[][] board, Line[] lines) {
      super();
      
      contains(500, 500);
      
      addMouseListener(mouseHandler);
      
      updateBoard(board, lines);
   }
   
   public void updateBoard(int[][] board, Line[] lines) {
      this.board = board;
      this.lines = lines;
      repaint();
   }
   
   @Override
   public void paint(Graphics g) {
      //g.drawImage(pieces[3], 0, 0, null);
      for (int x = 0; x < 10; x++) {
         for (int y = 0; y < 10; y++) {
            g.drawImage(pieces[board[x][y]], x * 50, y * 50, null);
         }
      }
      
      for (Line line : lines) {
         g.drawLine(line.x1 * 50 + 25, line.y1 * 50 + 25, line.x2 * 50 + 25, line.y2 * 50 + 25);
      }
      
      System.out.println("Painted the screen.");
   }
   
   public static int pixelToBoard(int pixel) {
      return (int) Math.ceil(pixel / 50);
   }
}