package example.android.com.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;


// All the db operations are performed through this class
public class InventoryDBOperations {

    private final InventoryDBHelper inventoryDBHelper;

    private static InventoryDBOperations instance = null;

    private InventoryDBOperations(Context context) {
        inventoryDBHelper = new InventoryDBHelper(context);
    }

    public static InventoryDBOperations getInstance(Context context) {
        if(instance == null) {
            instance = new InventoryDBOperations(context);
        }
        return instance;
    }

    // Reads rows from table and prints the data to console
    public void queryData() {
        SQLiteDatabase db = inventoryDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                InventoryContract.Inventory.COLUMN_NAME_PRODUCT_NAME,
                InventoryContract.Inventory.COLUMN_NAME_SUPPLIER_NAME,
                InventoryContract.Inventory.COLUMN_NAME_PRICE
        };

        String selection = InventoryContract.Inventory.COLUMN_NAME_PRODUCT_NAME + " = ?";
        String[] selectionArgs = { "Life of Chloe!" };

        Cursor cursor = db.query(
                InventoryContract.Inventory.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            String productName = cursor.getString(cursor.getColumnIndexOrThrow(InventoryContract.Inventory.COLUMN_NAME_PRODUCT_NAME));
            String supplierName = cursor.getString(cursor.getColumnIndexOrThrow(InventoryContract.Inventory.COLUMN_NAME_SUPPLIER_NAME));
            Double price = cursor.getDouble(cursor.getColumnIndexOrThrow(InventoryContract.Inventory.COLUMN_NAME_PRICE));
            System.out.println("Name: ["+productName+"] \n supplierName: ["+supplierName+"] \n price: [$"+price+"]");
        }
        cursor.close();
    }

    // Inserts a row of data into inventory table
    public void insertData() {
        SQLiteDatabase db = inventoryDBHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(InventoryContract.Inventory.COLUMN_NAME_PRODUCT_NAME, "Life of Chloe!");
        values.put(InventoryContract.Inventory.COLUMN_NAME_PRICE, 999.99);
        values.put(InventoryContract.Inventory.COLUMN_NAME_QUANTITY, 10000);
        values.put(InventoryContract.Inventory.COLUMN_NAME_SUPPLIER_NAME, "Brie Brie");
        values.put(InventoryContract.Inventory.COLUMN_NAME_SUPPLIER_PHONE_NUMBER, "9090909090");
        db.insertOrThrow(InventoryContract.Inventory.TABLE_NAME, null, values);
    }

}
