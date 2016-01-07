package it.jaschke.alexandria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle args = new Bundle();
            args.putString(BookDetail.EAN_KEY, getIntent().getExtras().getString(BookDetail.EAN_KEY));

            BookDetail detailFragment = new BookDetail();
            detailFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.book_detail_container, detailFragment)
                    .commit();
        }
    }
}
