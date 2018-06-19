package facebook.com.cog.sqlitelogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button  login,signup;
    DbHelper dbHelper;
    SQLiteDatabase db;
    SQLiteOpenHelper sqLiteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login=findViewById(R.id.btnLogin);
        signup = findViewById(R.id.btnLinkToRegisterScreen);
        dbHelper = new DbHelper(this);
        sqLiteOpenHelper = new DbHelper(this);
        db =sqLiteOpenHelper.getWritableDatabase();
        performAction();
    }

    private void performAction() {

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dbHelper.validation(email.getText().toString().trim(),password.getText().toString());
                if (result)
                {

                    Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                    Cursor cursor = db.rawQuery("SELECT * FROM STUDENTSDETAILS WHERE EMAIL='"+email.getText().toString().trim()+"'",null);
                    if (cursor!=null)
                    {
                        System.out.println("cursor : is not null "+email.getText().toString());
                        if (cursor.getCount()>0)
                        {
                            System.out.println("cursor : is count >0");
                            while (cursor.moveToNext()) {
                                System.out.println("details from user login " + cursor.getString(1));
                                Intent intent =new Intent(MainActivity.this,ViewProfileActivity.class);
                                intent.putExtra("name",email.getText().toString().trim());
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            System.out.println("cursor : is count <0");
                        }

                    }else
                    {
                        System.out.println("cursor : is null");
                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Unsuccessfully",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
