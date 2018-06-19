package facebook.com.cog.sqlitelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewProfileActivity extends AppCompatActivity {

    TextView name;
    String stuName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        name =findViewById(R.id.name1);
        Intent intent=getIntent();
        stuName = intent.getStringExtra("name");
        name.setText(stuName);


    }
}
