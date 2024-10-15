package com.example.stars;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.app.ShareCompat;
import com.example.stars.adapter.StarAdapter;
import com.example.stars.beans.Star;
import com.example.stars.service.StarService;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StarAdapter starAdapter;
    private StarService service;
    private static final String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Définit cette Toolbar comme ActionBar

        service = StarService.getInstance();
        init();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
    }

    public void init() {
        service.create(new Star("Cristiano Ronaldo", R.drawable.ro, 5.0f));
        service.create(new Star("Lionel Messi", R.drawable.on, 5.0f));
        service.create(new Star("\n" + "Kylian Mbappé\n", R.drawable.k, 4.50f));
        service.create(new Star("\n" + "Neymar JR\n", R.drawable.no, 4.0f));
        service.create(new Star("\n" + "Erling Haaland\n", R.drawable.ha, 5.0f));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, newText);
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            String txt = "Stars"; // Le texte à partager
            String mimeType = "text/plain"; // Le type MIME
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Share Stars")
                    .setText(txt)
                    .startChooser();
            return true; // Indique que l'événement a été traité
        }
        return super.onOptionsItemSelected(item);
    }
}
