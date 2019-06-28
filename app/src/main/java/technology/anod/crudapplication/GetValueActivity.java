package technology.anod.crudapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GetValueActivity extends AppCompatActivity {

        TextView nameText, ageText, salaryEdit;
        Button editBtn;
        String nameVal, ageVal;
        int salaryVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_value);

        nameText = (TextView) findViewById(R.id.nameEdit);
        ageText = (TextView) findViewById(R.id.ageEdit);
        salaryEdit = (TextView) findViewById(R.id.salaryEdit);

        editBtn = (Button) findViewById(R.id.subBtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetValueActivity.this, EditTextActivity.class);
                intent.putExtra("name", nameVal);
                intent.putExtra("age", ageVal);
                intent.putExtra("salary", salaryVal);
                startActivity(intent);
                finish();
            }
        });
    }
}
