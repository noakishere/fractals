//Author Kamyar

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;

public class Fractal {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int ceiling;
    private char direction;
    
    /**
     * Set the initial Location.
     * @param canvasWidth
     * @param canvasHeight
     * @param direction
     * @param ceiling
     */
    public Fractal( int canvasWidth, int canvasHeight, char direc, int ceiling)
    {
        
        setDirection(direc);
        setCeiling(ceiling);
        
        int e = 10;
        switch( this.direction)
        {
             case 'n' : setP1(e, canvasHeight-e);
                       setP2(e, e); 
                break;
            case 's' : setP1(canvasWidth-e, e);
                       setP2(canvasWidth-e, canvasHeight-e);
                break;
            case 'e' : setP1(e, canvasHeight-e);
                       setP2(canvasWidth-e, canvasHeight-e);
                break;
            case 'w' : setP1(canvasWidth-e, e);
                       setP2(e, e); 
                break;
           default: setP1(0, 0);
                    setP2(0, 0);
               break;
            
        }
    }

    private Fractal()
    {
   
        setP1(0,0);
        setP2(0,0);
        setCeiling(0);
        
    }
    

    private Fractal setDirection(char d){
        
        this.direction = d;
        
        return this;
    }
    

    private Fractal setP1(int x1, int y1){
        
        this.x1 = x1;
        this.y1 = y1;
        
        return this;
    }

    private Fractal setP2(int x2, int y2){
        
        this.x2 = x2;
        this.y2 = y2;
                
        return this;
    }

    private Fractal setCeiling(int c){
        
        this.ceiling = c;
        
        return this;
        
    }
    

    private int getX1(){
        return this.x1;
    }
    

    private int getY1(){
        return this.y1;
    }
    

    private int getX2(){
        return this.x2;
    }
    

    private int getY2(){
        return this.y2;
    }
    

    private int getCeiling(){
        return this.ceiling;
    }
    

    private char getDirection()
    {
        return this.direction;
    }
    

    private void drawFractal( int x1, int y1, int x2, int y2, int n, GraphicsContext gc)
    {
       Line line = new Line();

        if( n == 0){
            line.setStartX(getX1());
            line.setStartY(getY1());
            line.setEndX(getX2());
            line.setEndY(getY2());
            
            gc.strokeLine(line.getStartX(),
                          line.getStartY(),
                          line.getEndX(),
                          line.getEndY());
            
            
        }
        else{
            int f;
        
            Fractal a;
            Fractal b;
            Fractal c;
            Fractal d;
            if (x1 == x2){
                if (y1 < y2){
                    
                    f = (y2 - y1)/3;
                    
                    
                    setP2(x1, y1+f);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);

                    setP1(getX2(), getY2());
                    setP2(x1 - f, y1 + f);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);

                    setP1(getX2(), getY2());
                    setP2(x1-f, y1+ (2*f));
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
   
                    setP1(getX2(), getY2());
                    setP2(x1, y1 + (2*f));
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);

                    setP1(getX2(), getY2());
                    setP2(x2, y2);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
                      
                }
                //for north
                else if( y1 > y2){
                    f = (y1 - y2)/3;
                    
                    
                    setP2(x1, y1-f);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
 
                    setP1(getX2(), getY2());
                    setP2(x1 + f, y1 - f);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);

                    setP1(getX2(), getY2());
                    setP2(x1 + f, y1 - (2*f));
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
       
                    setP1(getX2(), getY2());
                    setP2(x1, y1 - (2*f));
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);

                    setP1(getX2(), getY2());
                    setP2(x2, y2);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
                    
                   
                } 
            }
            //for east
            else if (y1 == y2){
                if (x1 < x2){
                    f = (x2 - x1)/3;
           
                    setP2(x1+f, y1);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);

                    setP1(getX2(), getY2());
                    setP2(x1+f, y1 - f);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
  
                    setP1(getX2(), getY2());
                    setP2(x1 + (2*f), y1 - f);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
            
                    setP1(getX2(), getY2());
                    setP2(x1 + (2*f), y1);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
                    
                    setP1(getX2(), getY2());
                    setP2(x2, y2);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
                    
                }
                
                //for west
                else if(x1 > x2){
                    f = (x1 - x2)/3;
      
                    setP2(x1-f, y1);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
                
                    setP1(getX2(), getY2());
                    setP2(x1 - f, y1 + f);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
             
                    setP1(getX2(), getY2());
                    setP2(x1 - (2*f), y1 + f);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
                    
                    setP1(getX2(), getY2());
                    setP2(x1 - (2*f), y1);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
                
                    setP1(getX2(), getY2());
                    setP2(x2, y2);
                    drawFractal(getX1(), getY1(), getX2(), getY2(), n-1, gc);
                    
                    
                }
            }
        }    
    }
   
    
    public void drawFractal(GraphicsContext gc){
        
        drawFractal(getX1(), getY1(), getX2(), getY2(),
                getCeiling(), gc);
        
    
    
    }
    
    

    @Override
    public String toString(){
        
        char dirChar = getDirection();
        String direct;
        switch(dirChar){
            case 'n': direct = "North";break;
            case 's': direct = "South";break;
            case 'w': direct = "West"; break;
            case 'e': direct = "East"; break;
            default : direct = "No direction Specified"; break;
        }
        return "Fractal: \n" + "Direction: " + direct + "\n" + "Ceiling:  " + getCeiling() + "\n" +
        "Point 1: (" + getX1() + "," + getY1() + ")\n" +"Point 2: (" + getX2() + "," + getY2() + ")\n";
    }  
}
    

