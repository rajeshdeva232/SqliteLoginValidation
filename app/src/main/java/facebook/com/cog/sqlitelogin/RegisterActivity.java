package facebook.com.cog.sqlitelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, password;
    Button register,login;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.btnRegister);
        login =findViewById(R.id.btnLinkToLoginScreen);
        dbHelper = new DbHelper(this);
        performAction();
    }

    private void performAction() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    dbHelper.insert(name.getText().toString().trim(),email.getText().toString().trim(),password.getText().toString().trim());
                    Toast.makeText(RegisterActivity.this,"Record Inserted successfully",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this,"Record Not Inserted successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
