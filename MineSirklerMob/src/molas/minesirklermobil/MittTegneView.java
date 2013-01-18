package molas.minesirklermobil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;


public class MittTegneView extends View implements OnTouchListener {
	
	Context context;
	
	private ShapeDrawable sirkel8;
	private ShapeDrawable sirkel7;
	private ShapeDrawable sirkel6;
	private ShapeDrawable sirkel5;
	private ShapeDrawable sirkel4;
	private ShapeDrawable sirkel3;
	private ShapeDrawable sirkel2;
	private ShapeDrawable sirkel1;
	
	//private String sSirkel1;
	private Graphics sSirkel1;
	
	//private MittSirkelView sirkel_test;


	//Implementerer en ontouchListener som ikke virker hvis viewet ikke er hovedvindu   
	   public boolean onTouch(View v, MotionEvent event) {
           if(event.getAction() == MotionEvent.ACTION_UP) {
               float x = event.getX();
               float y = event.getY();
               
               Intent target = new Intent(context, DetaljerActivity.class);
   	    	context.startActivity(target);
               
               return true;
           }
           return false;
       }
	
	
    public MittTegneView(Context context) {
        super(context);
        this.context = context;
        setOnTouchListener(this);

        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        super.setLayoutParams(params);
       
        //Vi må hente størelsen på skjermen for å plassere sirklene riktig i view
        //super.getHeight();
        
       
        
        sirkel8 = tegnSirkel(-100,-100,1600,1600,Color.LTGRAY);
        sirkel7 = tegnSirkel(-0,-0,1400,1400,Color.LTGRAY);    
        sirkel6 = tegnSirkel(100,100,1200,1200,Color.RED);
        sirkel5 = tegnSirkel(200,200,1000,1000,Color.BLUE);
        sirkel4 = tegnSirkel(300,300,800,800,Color.YELLOW);
        sirkel3 = tegnSirkel(400,400,600,600,Color.GREEN);
        sirkel2 = tegnSirkel(500,500,400,400,Color.MAGENTA);
        sirkel1 = tegnSirkel(600,600,200,200,Color.CYAN);
        
        //sSirkel1 = "Test Database";
        //canvas.drawText(text, 0, 0, paint);
        
        //sirkel_test = new MittSirkelView(context,1,"RED","Meg");
      
    }

    protected void onDraw(Canvas canvas) {
    	
    	ShapeDrawable skille7_8 = tegnSirkel(-1,-1,1402,1402,Color.BLACK);
   
    	sirkel8.draw(canvas);
    	skille7_8.draw(canvas);
    	sirkel7.draw(canvas);
        sirkel6.draw(canvas);
        sirkel5.draw(canvas);
        sirkel4.draw(canvas);
        sirkel3.draw(canvas);
        sirkel2.draw(canvas);
        sirkel1.draw(canvas);
    }
    
    //Tegner sirklene. Dersom en ønsker å skifte plassering, endre verdier i setbounds
    private ShapeDrawable tegnSirkel(int x, int y, int h, int b, int col){
    	ShapeDrawable sirkelobjekt = new ShapeDrawable(new OvalShape());
    	sirkelobjekt.getPaint().setColor(col);
    	
    	int xcoord, ycoord = 0;

    	sirkelobjekt.setBounds(x-460, y, (x + h)-460, (y + b));
    	
        return sirkelobjekt;
    }
    
 
    
}
