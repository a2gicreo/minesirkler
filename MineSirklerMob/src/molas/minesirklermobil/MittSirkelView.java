package molas.minesirklermobil;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;


public class MittSirkelView extends View {

	Context context;
	private int nummer;
	private String farge;
	private String navn;
	private ShapeDrawable sirkel;
	
	public MittSirkelView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	//Denne klassen tegner en sirkel, der x-y koordinat og størrelse bestemmes av nummer
	public MittSirkelView(Context context,int nummer_, String farge_, String navn_) {
		super(context);
		
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        super.setLayoutParams(params);
	
		SettParametre(nummer_,farge_,navn_);
		
		int diameter = (nummer*100)*2;
		int koord = 700-(nummer*100);
		
		sirkel = tegnSirkel(koord,koord,diameter,diameter, Color.parseColor(farge));
	}
	
	public void SettParametre(int nummer_, String farge_, String navn_){
		nummer = nummer_;
		farge = farge_;
		navn = navn_;
	}
	
	protected void onDraw(Canvas canvas){
		sirkel.draw(canvas);
	}
	

	
	 //Tegner sirkel. Dersom en ønsker å skifte plassering, endre verdier i setbounds
    private ShapeDrawable tegnSirkel(int x, int y, int h, int b, int col){
    	ShapeDrawable sirkelobjekt = new ShapeDrawable(new OvalShape());
    	sirkelobjekt.getPaint().setColor(col);
    	
    	int xcoord, ycoord = 0;

    	sirkelobjekt.setBounds(x-460, y, (x + h)-460, (y + b));
    	
        return sirkelobjekt;
    }

	
	
	
}
