package hackmaster20.objects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;

import hackmaster20.business.ResourceManager;

/**
 * Created by Owner on 1/29/2018.
 */

public class CardClass {
    private String name;
    private String description;
    private ResourceManager resManager;
    private String type;
    private int id = 0;

    public CardClass(int i, String n, String t, String d, ResourceManager r) {
        id = i;
        name = n;
        type = t;
        description = d;
        resManager = r;
    }

    public String toString() {
        String strung = null;

        strung = name + " type:" + type + " d:" + description;
        strung += "R:" + resManager.toString();

        return strung;
    }
    public String getName (){
        return name;
    }
}
