package facebook.com.cog.sqlitelogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;


    public DbHelper(Context context) {
        super(context, "LOGIN.db", null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STUDENTSDETAILS( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT UNIQUE, PASSWORD TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS STUDENTSDETAILS");
        onCreate(db);
    }

    public void insert(String name, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("EMAIL", email);
        contentValues.put("PASSWORD", password);
        db.insertOrThrow("STUDENTSDETAILS", "", contentValues);
    }

    public boolean validation(String userName, String password) {

        Cursor cursor = db.rawQuery("SELECT * FROM STUDENTSDETAILS WHERE EMAIL=? AND PASSWORD=?", new String[]{userName, password});
        if(cursor !=null)
        {
            if (cursor.getCount()>0)
            {
                while (cursor.moveToNext()) {
                    System.out.println("user details from login " + cursor.getString(1));
                }
                return true;
            }

        }
        return false;
    }
}
