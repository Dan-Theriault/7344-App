package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by dianamilton on 2/8/18.
 */

public class JournalActivity extends AppCompatActivity {

    private ListView aListViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        aListViewNotes = (ListView) findViewById(R.id.main_listview_notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main_new_note:
                //start NoteActivity in NewNote node
                Intent newNoteActivity = new Intent(this, NoteActivity.class);
                startActivity(newNoteActivity);
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        aListViewNotes.setAdapter(null);
        ArrayList<Note> notes = Utilities.getAllSavedNotes(this);
        if (notes == null || notes.size() == 0) {
            Toast.makeText(this, "You have no notes saved.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            NoteAdapter na = new NoteAdapter(this, R.layout.item_note, notes);
            aListViewNotes.setAdapter(na);
        }
    }
}
