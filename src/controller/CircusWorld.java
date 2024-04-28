package controller;


import model.Objects.BarObject;
import model.Objects.PlateObject;
import model.Objects.ImageObject;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import model.FallingState;
import model.Objects.BombObject;
import model.ShapesFactory;
import model.Objects.OvalObject;
import model.Observable;
import model.ShapeIterator;
import model.Shapes;
import model.Strategy.LevelStrategy;

public class CircusWorld implements World {

    private static final int MAX_TIME = 1 * 60 * 1000;
    private int score = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    private final long startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private List<GameObject> leftFirst = new LinkedList<GameObject>();
    private List<GameObject> rightFirst = new LinkedList<GameObject>();
    
    private List<GameObject> leftSecond = new LinkedList<GameObject>();
    private List<GameObject> rightSecond = new LinkedList<GameObject>();
    
   // private List<GameObject> temp = new LinkedList<GameObject>();
     
    private LevelStrategy gameStrategy;
    private int i;
    
    private int j,k;
    
    
    /*int a=2;
    int b=2;
    int c=2;
    int d=2;*/
    
    private Observable scoreObserv = new Observable(this);
    
           

    public CircusWorld(LevelStrategy x, int screenWidth, int screenHeight) {
        gameStrategy = x;
        width = screenWidth;
        height = screenHeight;
        
        control.add(new ImageObject(screenWidth / 3, (int) (screenHeight * 0.7), "/clowns.png"));
        
        constant.add(new ImageObject(0,0,"/back1.jpeg"));
        
        constant.add(new BarObject(0, 50, 250, true, Color.BLACK));
        constant.add(new BarObject(width - 250, 50, 250, true, Color.BLACK));
        constant.add(new BarObject(0, 100, 150, true, Color.BLACK));
        constant.add(new BarObject(width - 150, 100, 150, true, Color.BLACK));
       
       
        
      
        for ( i = 0; i < 100; i++) {
            
            j=(int) (Math.random() * 1000) % 2 + 1;  // random number between 1 and 2 
           
            if (j==1){
                
            PlateObject p1=(PlateObject)ShapesFactory.getShape(0 - 50 * ( 3* i), 43, "plate"); //upper left
             p1.setUpperleft(1);  
            
   
             PlateObject p2=(PlateObject)ShapesFactory.getShape(width + (int)( i*180), 93,"plate"); //lower right
            p2.setLowerRight(1);  
            
             OvalObject o1=(OvalObject)ShapesFactory.getShape(width + (int)( i*180), 23,"oval"); //upper right
            o1.setUpperright(1);
           
            
            OvalObject o2=(OvalObject)ShapesFactory.getShape(0 - 50 * ( 3* i), 73, "oval"); //lower left
            o2.setLowerleft(1);
            
            moving.add(p1); moving.add(p2); moving.add(o1); moving.add(o2);
            
            }

            if (j==2)
            {

             
             PlateObject p1=(PlateObject)ShapesFactory.getShape(width + (int)( i*180), 43, "plate"); 
            p1.setUpperright(1);     
            
   
             PlateObject p2=(PlateObject)ShapesFactory.getShape(0 - 50 * ( 3* i), 93,"plate"); 
            p2.setLowerleft(1);  
            
             OvalObject o1=(OvalObject)ShapesFactory.getShape(0 - 50 * ( 3* i), 23,"oval"); //upper right
            o1.setUpperleft(1);
           
            
            OvalObject o2=(OvalObject)ShapesFactory.getShape(width + (int)( i*180), 73, "oval"); //lower left
            o2.setLowerRight(1);
            
            moving.add(p1); moving.add(p2); moving.add(o1); moving.add(o2);
   
            }
            
            
            moving.add(new BombObject(ThreadLocalRandom.current().nextInt(100,600), -100*(10*i),"/bomb11.png",3));
        }
        
       
            
          
    }

    private boolean intersect(GameObject o1, GameObject o2) {

        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        GameObject clown = control.get(0);
        
         ShapeIterator iter= new ShapeIterator(moving); 
         
        while(iter.hasNext()) {

            
            GameObject movingShape = iter.next();
            Shapes currentObj=(Shapes)movingShape;
             int x=movingShape.getX();
            int y=movingShape.getY();
            
            if (currentObj.getType()==3)  //if object is a bomb
            {
                y=y+gameStrategy.getSpeed();
                currentObj.setCurrentState(new FallingState(currentObj));
            
            }
                
            else  // if it is a normal shape 
            {

            
  
            if (currentObj.getUpperleft()==1) //upper leftFirst
            { x=x+gameStrategy.getSpeed();
            if (!(movingShape.getX() <250)) {
 
               y=y+gameStrategy.getSpeed();
               
               currentObj.setCurrentState(new FallingState(currentObj));
            }}
            
             
              if (currentObj.getLowerleft()==1) // lower leftFirst
              { x=x+gameStrategy.getSpeed();
            if (!(movingShape.getX() <150)) {
  
              y=y+gameStrategy.getSpeed();
               currentObj.setCurrentState(new FallingState(currentObj));
            }}
             
            
             if (currentObj.getLowerRight()==1){// lower rightFirst
                  x=x-(gameStrategy.getSpeed());
            if (!((movingShape.getX()+movingShape.getWidth()) > width-150 )) {
                y=y+gameStrategy.getSpeed();
               currentObj.setCurrentState(new FallingState(currentObj));
            
            }}
            
           
           if (currentObj.getUpperright()==1)//upper rightFirst
            {
                x=x-(gameStrategy.getSpeed());
           if (!((movingShape.getX()+movingShape.getWidth()) > width-250 )) {
              y=y+gameStrategy.getSpeed();
              
              currentObj.setCurrentState(new FallingState(currentObj));
             
            }}
           
            }
       
           currentObj.getCurrentState().move(x, y); //update coordinates of the shape whether falling or not
            
            

            
           ////////////CLOWN AND PLATE INTERSECTION///////////////
           
           
              
               
           
            if (leftFirst.isEmpty()) {
                if (clownIntersectleftFirst(movingShape) && currentObj.getType()!=3 ) {
                    moving.remove(movingShape);
                    currentObj.setC((ImageObject) clown);
                    currentObj.setHorizontalOnly(true);
                    currentObj.setType(1);
                    control.add(movingShape);
                    leftFirst.add(movingShape); updateLeftFirst();
            
                }
            } else {
                if (intersect(movingShape, leftFirst.get(leftFirst.size() - 1))) {
                    
                    if (currentObj.getType()==3){
                        for(int c=0; c<leftFirst.size();c++)
                        {
                            control.remove(leftFirst.get(c));
   
                        }
                        leftFirst.removeAll(leftFirst);
                    }

                    else{
                    moving.remove(movingShape);
                    
                    currentObj.setC((ImageObject) clown);
                    
                    currentObj.setY(leftFirst.get(leftFirst.size() - 1).getY() - currentObj.getHeight());
                   currentObj.setHorizontalOnly(true);
                    currentObj.setType(1);
                    control.add(movingShape);
                    leftFirst.add(movingShape); updateLeftFirst();
                    }
            
                }
              
            }
           
            
            
           if (rightFirst.isEmpty()) {
                if (clownIntersectrightFirst(movingShape) && currentObj.getType()!=3) {
                    moving.remove(movingShape);
                   currentObj.setC((ImageObject) clown);
                    currentObj.setHorizontalOnly(true);
                   currentObj.setType(2);
                    control.add(movingShape);
                    rightFirst.add(movingShape);
                }
            } else {
                if (intersect(movingShape, rightFirst.get(rightFirst.size() - 1))) {
                    
                     if (currentObj.getType()==3){
                        for(int c=0; c<rightFirst.size();c++)
                        {
                            control.remove(rightFirst.get(c));
   
                        }
                        rightFirst.removeAll(rightFirst);
                        

                    }
                     else{
                    moving.remove(movingShape);
                    currentObj.setC((ImageObject) clown);

                    currentObj.setY(rightFirst.get(rightFirst.size() - 1).getY() - currentObj.getHeight());

                     currentObj.setHorizontalOnly(true);
                    currentObj.setType(2);
                    control.add(movingShape);
                    rightFirst.add(movingShape);
                }}
               
            }
            updateRightFirst();

             
            
               
                

               
               
               if (leftSecond.isEmpty()) {
                if (clownIntersectleftSecond(movingShape)&& currentObj.getType()!=3) {
                    moving.remove(movingShape);
                    currentObj.setC((ImageObject) clown);
                   currentObj.setHorizontalOnly(true);
                    currentObj.setType(6);

                    control.add(movingShape);
                   leftSecond.add(movingShape);
                }
            } else {
                if (intersect(movingShape, leftSecond.get(leftSecond.size() - 1))) {
                    
                      if (currentObj.getType()==3){
                        for(int c=0; c<leftSecond.size();c++)
                        {
                            control.remove(leftSecond.get(c));
   
                        }
                        leftSecond.removeAll(leftSecond);
                        

                    }
                     else
                     {
                    moving.remove(movingShape);
                    
                    currentObj.setC((ImageObject) clown);

                
                    currentObj.setY(leftSecond.get(leftSecond.size() - 1).getY() - currentObj.getHeight());
                 
                
                     currentObj.setHorizontalOnly(true);
                   currentObj.setType(6);
                    control.add(movingShape);
                    leftSecond.add(movingShape);
                }}
               
            }
            updateLeftSecond();
            
            
            if (rightSecond.isEmpty()) {
                if (clownIntersectrightSecond(movingShape) && currentObj.getType()!=3) {
                    moving.remove(movingShape);
                    currentObj.setC((ImageObject) clown);
                   currentObj.setHorizontalOnly(true);
                    currentObj.setType(7);
                    
                    
                    control.add(movingShape);
                   rightSecond.add(movingShape);
                }
            } else {
                if (intersect(movingShape, rightSecond.get(rightSecond.size() - 1))) {
                    if (currentObj.getType()==3){
                        for(int c=0; c<rightSecond.size();c++)
                        {
                            control.remove(rightSecond.get(c));
   
                        }
                        rightSecond.removeAll(rightSecond);
                        

                    }
                    else
                    {
                    moving.remove(movingShape);
                    
                    currentObj.setC((ImageObject) clown);

                
                    currentObj.setY(rightSecond.get(rightSecond.size() - 1).getY() - currentObj.getHeight());
                 
                
                    currentObj.setHorizontalOnly(true);
                   currentObj.setType(7);
                    control.add(movingShape);
                    rightSecond.add(movingShape);
                }}
               
            }
            updateRightSecond();
            
    
        iter.index++; 
        
    }
         return !timeout;
     }
                    
    public void notifyObserver(int x){
        
       scoreObserv.updateScore(x);
            
    }
    

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);	// updateScore status
    }
    
    private boolean heightCheck(GameObject o)
    {
        if (gameStrategy.getSpeed()==1)
            return (o.getY() == control.get(0).getY() - o.getHeight());
        else if 
                (gameStrategy.getSpeed()==2)
            return ((o.getY()+1 == control.get(0).getY() - o.getHeight() ) || ((o.getY()-1 == control.get(0).getY() - o.getHeight())    ||   o.getY() == control.get(0).getY() - o.getHeight()  ) );
    
        else // speed=3
            return (  (o.getY()+1 == control.get(0).getY() - o.getHeight() ) || ((o.getY()-1 )== control.get(0).getY() - o.getHeight() )   ||  ( o.getY() == control.get(0).getY() - o.getHeight()  )  || ((o.getY()+2) == control.get(0).getY() - o.getHeight()) || ((o.getY()-2) == control.get(0).getY() - o.getHeight()));
            
    
    }

    private boolean clownIntersectleftFirst(GameObject o) {
        ImageObject clown = (ImageObject) control.get(0);
        boolean flag=heightCheck(o);
        
        //o.getY() == control.get(0).getY() - o.getHeight())
        
        return (Math.abs(clown.getX() - o.getX()) <= o.getWidth() - 10
                && flag==true);
    }
    private boolean clownIntersectrightFirst(GameObject o) {
        ImageObject clown = (ImageObject) control.get(0);
        boolean flag=heightCheck(o);
   
        return (Math.abs((clown.getX()+132) - o.getX()) <= o.getWidth() - 10
                && flag==true);
    }
    
    
    ////////
     private boolean clownIntersectleftSecond(GameObject o) {
        ImageObject clown = (ImageObject) control.get(0);
        boolean flag=heightCheck(o);
   
        return (Math.abs((clown.getX()+215) - o.getX()) <= o.getWidth() - 10
                &&flag==true);
    }
     
      private boolean clownIntersectrightSecond(GameObject o) {
        ImageObject clown = (ImageObject) control.get(0);
        boolean flag=heightCheck(o);
   
        return (Math.abs((clown.getX()+350) - o.getX()) <= o.getWidth() - 10
                && flag==true);
    }

    private void updateLeftFirst() {
        if (leftFirst.size() >= 3) {
            Shapes p1 =  (Shapes) leftFirst.get(leftFirst.size() - 1);
             Shapes p2 = ( Shapes) leftFirst.get(leftFirst.size() - 2);
             Shapes p3 = ( Shapes) leftFirst.get(leftFirst.size() - 3);
            String path1 =p1.getPath();
            String path2 =p2.getPath();
            String path3 =p3.getPath();
            
            
            if ((path1.contains("1")&path2.contains("1")& path3.contains("1")) || (path1.contains("2")&path2.contains("2")& path3.contains("2")) ) {
                leftFirst.remove(leftFirst.size() - 1);
                leftFirst.remove(leftFirst.size() - 1);
                leftFirst.remove(leftFirst.size() - 1);
                control.remove(p1);
                control.remove(p2);
                control.remove(p3);
                //score++;
                 notifyObserver(1);
            }
        }
    }
    
         private void updateRightFirst() {
        if (rightFirst.size() >= 3) {
             Shapes p1 = ( Shapes) rightFirst.get(rightFirst.size() - 1);
             Shapes p2 = ( Shapes) rightFirst.get(rightFirst.size() - 2);
             Shapes p3 = ( Shapes) rightFirst.get(rightFirst.size() - 3);
            String path1 =p1.getPath();
            String path2 =p2.getPath();
            String path3 =p3.getPath();
            
            
            if ((path1.contains("1")&path2.contains("1")& path3.contains("1")) || (path1.contains("2")&path2.contains("2")& path3.contains("2")) )

            {
                rightFirst.remove(rightFirst.size() - 1);
                rightFirst.remove(rightFirst.size() - 1);
                rightFirst.remove(rightFirst.size() - 1);
                control.remove(p1);
                control.remove(p2);
                control.remove(p3);
                 notifyObserver(1);
               // score++;
            }
        }
    }
         
         /////////
          private void updateLeftSecond() {
        if (leftSecond.size() >= 3) {
             Shapes p1 = ( Shapes) leftSecond.get(leftSecond.size() - 1);
             Shapes p2 = ( Shapes) leftSecond.get(leftSecond.size() - 2);
             Shapes p3 = ( Shapes)leftSecond.get(leftSecond.size() - 3);
            String path1 =p1.getPath();
            String path2 =p2.getPath();
            String path3 =p3.getPath();
            
            
            if ((path1.contains("1")&path2.contains("1")& path3.contains("1")) || (path1.contains("2")&path2.contains("2")& path3.contains("2")) )

            {
                leftSecond.remove(leftSecond.size() - 1);
                leftSecond.remove(leftSecond.size() - 1);
                leftSecond.remove(leftSecond.size() - 1);
                control.remove(p1);
                control.remove(p2);
                control.remove(p3);
                 notifyObserver(1);
                //score++;
            }
        }
    }
          
          
           private void updateRightSecond() {
        if (rightSecond.size() >= 3) {
             Shapes p1 = ( Shapes) rightSecond.get(rightSecond.size() - 1);
             Shapes p2 = ( Shapes)rightSecond.get(rightSecond.size() - 2);
             Shapes p3 = ( Shapes) rightSecond.get(rightSecond.size() - 3);
            String path1 =p1.getPath();
            String path2 =p2.getPath();
            String path3 =p3.getPath();
            
            
            if ((path1.contains("1")&path2.contains("1")& path3.contains("1")) || (path1.contains("2")&path2.contains("2")& path3.contains("2")) )

            {
                rightSecond.remove(rightSecond.size() - 1);
                rightSecond.remove(rightSecond.size() - 1);
                rightSecond.remove(rightSecond.size() - 1);
                control.remove(p1);
                control.remove(p2);
                control.remove(p3);
                 notifyObserver(1);
               // score++;
            }
        }
    }

    
}
