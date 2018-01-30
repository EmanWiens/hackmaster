package hackmaster20.application;

// import hackmaster20.presentation.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import com.example.owner.hackmaster20.R;

//test
public class MainActivity extends AppCompatActivity {
// comment vlad is awesome
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playMessage(View v) {
        // do something
        setContentView(R.layout.activity_duel);
    }
}
