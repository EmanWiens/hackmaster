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

    public CardClass(int id, String name, String type, String d, ResourceManager resourceManager) {
        this.id = id;
        this.name = name;
        this.type = type;
        description = d;
        resManager = resourceManager;
    }

    public String toString() {
        String strung = "Error, there is not card.";

        strung = name + " type:" + type;
        strung += "R:" + resManager.toString();

        return strung;
    }
}
