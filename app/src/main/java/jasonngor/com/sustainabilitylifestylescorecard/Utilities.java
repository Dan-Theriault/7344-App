package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by dianamilton on 2/8/18.
 */

public class Utilities {

    public static final String FILE_EXTENSION = ".bin";

    public static boolean saveNote(Context context, Note note) {

        String fileName = String.valueOf(note.getDateTime()) + FILE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(note);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public static ArrayList<Note> getAllSavedNotes(Context context) {
        ArrayList<Note> notes = new ArrayList<>();

        File filesDir = context.getFilesDir();
        ArrayList<String> noteFiles = new ArrayList<>();

        for (String file: filesDir.list()) {
            if (file.endsWith(FILE_EXTENSION)) {
                noteFiles.add(file);
            }
        }

        FileInputStream fis;
        ObjectInputStream ois;

        for (int i = 0; i < noteFiles.size(); i++) {

            try{
                fis = context.openFileInput(noteFiles.get(i));
                ois = new ObjectInputStream(fis);
                notes.add((Note) ois.readObject());
                fis.close();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        return notes;
    }

}