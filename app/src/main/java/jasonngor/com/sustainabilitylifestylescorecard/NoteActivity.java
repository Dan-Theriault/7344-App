package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    private EditText EdTitle;
    private EditText EdContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        EdTitle = (EditText) findViewById(R.id.note_ed_title);
        EdContent = (EditText) findViewById(R.id.note_ed_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_save, menu);
        Toast.makeText(this, "Remember to write about your clothing options!", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_note_save:
                saveNote();
        }
        return true;
    }

    private void saveNote() {
        Note note = new Note(System.currentTimeMillis(), EdTitle.getText().toString(), EdContent.getText().toString());
        if (Utilities.saveNote(this, note)) {
            Toast.makeText(this, "Your note is saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cannot save note, make sure you have enough space on your device", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}
