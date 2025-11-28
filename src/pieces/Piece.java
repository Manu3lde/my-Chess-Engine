package pieces;

import javax.imageio.ImageIO;

import main.Board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {

    public int col, row;
    public int xPos, yPos;

    public boolean isWhite;
    public String name;
    public int value;

    BufferedImage sheet;
    {
        java.io.InputStream is = null;
        try {
            // Try loading from classpath using the class's resource path.
            is = Piece.class.getResourceAsStream("../res/PiecesHD.png");
            if (is == null) {
                // Resource not found: create a small placeholder so code using `sheet` won't NPE.
                System.err.println("Resource 'pieces.png' not found on classpath; using placeholder image.");
                // create placeholder with two rows (height 160) so subimage calls for both
                // white and black rows won't fail (sheetScale = width / 6)
                sheet = new BufferedImage(480, 160, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = sheet.createGraphics();
                g.setColor(Color.MAGENTA);
                g.fillRect(0, 0, sheet.getWidth(), sheet.getHeight());
                g.dispose();
            } else {
                sheet = ImageIO.read(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (sheet == null) {
                sheet = new BufferedImage(480, 160, BufferedImage.TYPE_INT_ARGB);
            }
        } finally {
            if (is != null) {
                try { is.close(); } catch (IOException ignored) {}
            }
        }
    }


    protected int sheetScale = sheet.getWidth()/6;

    Image sprite;
    Board board;
    public Piece(Board board){
        this.board = board;
    }



    public boolean isValidMovement(int col, int row){
        return true;
    }
    
    public boolean moveCollidesWithPiece(int col, int row){
        return false;
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite, xPos, yPos, null);
    }



}
