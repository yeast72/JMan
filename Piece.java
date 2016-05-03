import java.awt.*;

/** An instance is a piece for use in the J*Man!! assignment 2.*/
public abstract class Piece{
    /** Constants that identify the kind of a piece. */
    public static final int BLOCK= 0;  // piece is a block
    public static final int JMAN= 1;   // piece is the J*man
    public static final int WALKER= 2; // piece is a walker
    public static final int PILLAR= 3; // piece is a pillar
    
    /** Constructor: a Piece on Map m with type t.
        Precondition: type is one of the four constants of this class. */
    public Piece(int t, Map m){
        
    }
    
    /** = this piece's type: (one of the four constants of this class. */
    public int getType(){
        return 0;
    }
    
    /** = this piece's x location. */
    public int getX(){
        return 0;
    }
    
    /** = this piece's y location. */
    public int getY(){
        return 0;
    }
    
    /** = this piece's color. */
    public Color getColor(){
        return null;
    }
    
    /** = the color of this piece, as a capitalized word (e.g. "Red").
        = the empty string if it is not one of red, green, yellow, or white*/
    public String getColorWord() {
        // complete this
        return "";
    }
    
    /** = "This piece has already acted this round". */
    public boolean hasActed(){
        return true;
    }
    
    /** = the map this piece is on. */
    public Map getMap(){
        return null;
    }
    
    /** Set this piece's x location to x. */
    public void setX(int x){
        
    }
    
    /** Set this piece's y location to y. */
    public void setY(int y){
        
    }
    
    /** Set this piece's color to the c.
        Precondition: c is Color.WHITE and this is a block,OR
        c is Color.RED, Color.GREEN, or Color.YELLOW. */
    public void setColor(Color c){
        
    }
    
    /** Set the state of this piece to the value of acted.*/
    public void setActed(boolean acted){
        
    }
    
    /** Make this piece take one action. What a piece does on
        its action is defined in the assignment. */
    public abstract void act();
    
    /** = a random integer in the range lo..hi, with all integers in
          the range being equally likely. 
          Precondition lo < hi. */
    public static int rand(int lo, int hi){
        return (int)(Math.random()*(hi-lo+1))+lo;
    }
    
}