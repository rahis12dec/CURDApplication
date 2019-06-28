package technology.anod.crudapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditTextActivity extends AppCompatActivity {

    EditText nameEdit, ageEdit, salaryEdit;
    Button submitBtn;
    String name, age;
    int salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        nameEdit = (EditText) findViewById(R.id.nameEdit);
        ageEdit = (EditText) findViewById(R.id.ageEdit);
        salaryEdit = (EditText) findViewById(R.id.salaryEdit);

        submitBtn = (Button) findViewById(R.id.subBtn);

        name = getIntent().getStringExtra("name");
        age = getIntent().getStringExtra("age");
        salary = getIntent().getIntExtra("salary", 0);

        nameEdit.setText(name);
        ageEdit.setText(age);
        salaryEdit.setText(salary);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EditTextActivity.this, GetValueActivity.class);
                startActivity(intent);
            }
        });
    }
}
