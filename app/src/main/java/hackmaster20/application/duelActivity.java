package hackmaster20.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;

import hackmaster20.objects.CardClass;

public class duelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_duel);
    }

    public void showCard(CardClass show, int slot) {
        TextView edit = (TextView)findViewById(R.id.card0);
        edit.setText(show.toString());
    }
}
