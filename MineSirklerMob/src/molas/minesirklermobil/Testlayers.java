package molas.minesirklermobil;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Testlayers extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testlayers);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_testlayers, menu);
        return true;
    }
}
