package hackmaster.application;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import hackmaster.business.DeckManager;
import hackmaster.objects.CardClass;
import hackmaster.persistence.CardDataAccessInterface;
import hackmaster.presentation.MainActivity;

public abstract class AppController {
    public static void initDeck(CardDataAccessInterface cardDataAccess) {
        cardDataAccess = Services.getCardDataAccess();
        ArrayList<CardClass> listDeck = new ArrayList<>();
        String eMsg = cardDataAccess.getRandomDeck(listDeck, new Random());

        if(eMsg!=null) {
            System.out.println(eMsg);
        }

        DeckManager.setDeck(listDeck.toArray(new CardClass[0]));
        DeckManager.resetIndex();
    }

    public static void copyDatabaseToDevice(MainActivity mainActivity) {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = mainActivity.getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = mainActivity.getAssets();

        try {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++)
                assetNames[i] = DB_PATH + "/" + assetNames[i];

            copyAssetsToDirectory(assetNames, dataDirectory, mainActivity);

            DBController.setDBPathName(dataDirectory.toString() + "/" + DBController.dbName);

        } catch (IOException ioe) {
        }
    }

    private static void copyAssetsToDirectory(String[] assets, File directory, MainActivity mainActivity) throws IOException {
        AssetManager assetManager = mainActivity.getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
