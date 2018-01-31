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

    public CardClass(String n, String t, String d, ResourceManager r) {
        name = n;
        type = t;
        description = d;
        resManager = r;
    }

    public String toString() {
        String strung = null;

        strung = "Card\nName:" + name + " type:" + type + " description:" + description;
        strung += "ResourceManager:" + resManager.toString();

        return strung;
    }
}
