package example.android.com.inventory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Main activity creates necessary tables and the database using InventoryDBOperations helper class
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InventoryDBOperations dbOps = InventoryDBOperations.getInstance(this);
        dbOps.insertData();
        dbOps.queryData();
    }
}
