package molas.minesirklermobil;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SubListeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_liste);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sub_liste, menu);
        return true;
    }
}
