package it.jaschke.alexandria;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    public static final String MESSAGE_EVENT = "MESSAGE_EVENT";
    public static final String MESSAGE_KEY = "MESSAGE_EXTRA";
    public static boolean bookIsAlreadyInLibrary = false;
    private final String ADD_BOOK_FRAGMENT_TAG = "addBookFragment";
    private BroadcastReceiver messageReceiver;
    private Fragment fragmentAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        messageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter(MESSAGE_EVENT);
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, filter);

        bookIsAlreadyInLibrary = false;

        if (findViewById(R.id.add_book_container) != null) {
            // If savedInstanceState is null, create a new fragment; otherwise, a configuration change
            // has occurred and the fragment does not need to be recreated
            if (savedInstanceState == null) {
                fragmentAddBook = new AddBook();
                fragmentAddBook.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .replace(
                                R.id.add_book_container,
                                fragmentAddBook,
                                ADD_BOOK_FRAGMENT_TAG)
                        .commit();
            } else {
                fragmentAddBook = getSupportFragmentManager()
                        .findFragmentByTag(ADD_BOOK_FRAGMENT_TAG);
            }
        }
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver);
        super.onDestroy();
    }

    private class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getStringExtra(MESSAGE_KEY) != null) {
                Toast.makeText(AddBookActivity.this, intent.getStringExtra(MESSAGE_KEY), Toast.LENGTH_LONG).show();
                if (intent.getStringExtra(MESSAGE_KEY).equals(getString(R.string.book_in_library))) {
                    bookIsAlreadyInLibrary = true;

                    if (findViewById(R.id.add_book_container) != null) {
                        findViewById(R.id.save_button).setVisibility(View.INVISIBLE);
                        findViewById(R.id.delete_button).setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }
}
