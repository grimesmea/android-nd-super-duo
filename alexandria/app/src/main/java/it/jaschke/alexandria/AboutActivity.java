package it.jaschke.alexandria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (savedInstanceState == null) {
            // Create the about fragment and add it to the activity
            // using a fragment transaction.
            About aboutFragment = new About();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.about_container, aboutFragment)
                    .commit();
        }
    }
}
