package molas.minesirklermobil;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

public class MineSirklerActivity extends Activity implements OnTouchListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_sirkler);
        
        
        /*  For å legge til et tegneview til canvaset  */
        LinearLayout layout = new LinearLayout(this);      
        MittTegneView mTegneView = new MittTegneView(this);      
        
        
        layout.addView(mTegneView);
        
        //View onTouchView = findViewById(R.id.whatever_id);
        mTegneView.setOnTouchListener(this);

        
      //mTegneView.setOnTouchListener(this);  
        //image_Listener.onTouch(mTegneView, null);
        
        setContentView(layout);    
        
    }
    
    public boolean onTouch(View view, MotionEvent event) 
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            int x = Math.round(event.getRawX());
            int y = Math.round(event.getRawY());
            
            velgViewInnhold(view, x,y);
            
            return true;
        }
        return false;
    }
    
  
    
    
    /*Brukes av Interface for å åpne activity dealjer */
    public void velgViewInnhold(View v, int coordX, int coordY) {
    	int valgtSirkel = 0;
    	int sentrumX = 240;
    	int sentrumY = 810;
    	double avstandFraSenter = 0;
        
    	double a = sentrumX-coordX;	
    	double b = sentrumY-coordY;
    	
    	avstandFraSenter = Math.sqrt((a*a)+(b*b));
    	
    	if (avstandFraSenter > 0 && avstandFraSenter < 100 )
    		valgtSirkel = 1; 
    	else if(avstandFraSenter > 100 && avstandFraSenter < 200 )
    		valgtSirkel = 2; 
    	if (avstandFraSenter > 200 && avstandFraSenter < 300 )
    		valgtSirkel = 3; 
    	else if(avstandFraSenter > 300 && avstandFraSenter < 400 )
    		valgtSirkel = 4; 
    	if (avstandFraSenter > 400 && avstandFraSenter < 500 )
    		valgtSirkel = 5; 
    	else if(avstandFraSenter > 500 && avstandFraSenter < 600 )
    		valgtSirkel = 6; 
    	if (avstandFraSenter > 600 && avstandFraSenter < 700 )
    		valgtSirkel = 7; 
    	else if(avstandFraSenter > 700 && avstandFraSenter < 800 )
    		valgtSirkel = 8; 
    
    	
    	//Send valgt sirkel til target
        //Intent target = new Intent(this, DetaljerActivity.class);
        //startActivity(target);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mine_sirkler, menu);
        return true;
    }
}
