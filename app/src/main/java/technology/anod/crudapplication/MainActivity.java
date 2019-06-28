package technology.anod.crudapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText nameEdit, ageEdit, salaryEdit;
    Button submitBtn;
    String name, age;
    int salary;

    String url = "http://18.219.9.163:3000/movies";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdit = (EditText) findViewById(R.id.nameEdit);
        ageEdit = (EditText) findViewById(R.id.ageEdit);
        salaryEdit = (EditText) findViewById(R.id.salaryEdit);


        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(todayDate);

        Log.d("current dat", todayString);

        submitBtn = (Button) findViewById(R.id.subBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameEdit.getText().toString();
                age = ageEdit.getText().toString();
                salary = Integer.parseInt(salaryEdit.getText().toString());

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // if (!response.isEmpty()) {
                        try {
                            Log.e("Your Array Response", response);

                            JSONObject object = new JSONObject(response);
                            JSONObject dataObj = object.getJSONObject("data");
                            Log.i("data", dataObj.toString());
                           // JSONArray jsonArray = dataObj.getJSONArray("customers");

                            //Log.i("feedbacks array", jsonArray.toString());

//{"id":"5c285bcd73022d0cc4ed5242","restaurantName":"GoldCoin Fine Dine",
// "tableNumber":13,"billNumber":13,"captainName":"Yogesh","waiterName":"waiter one",
// "customerName":"Kiran Sabne","customerMobile":"9870953693","email":"kiransabne18@gmail.com","dateOfBirth":"1994-01-13",
// "dateOfAnn":"0","ambiancePoints":4.5,"ambianceRemarks":"Nice","foodQualityPoints":3.2,"foodQualityRemarks":"Well Fine",
// "customerServicePoints":4,"customerServiceRemarks":"customer Service is not great","staffBehaviourPoints":4,
// "staffBehaviourRemarks":"Staff Behaviour Remarks","valueForMoneyPoints":5,"valueForMoneyRemarks":"value fir money remarks",
// "suggestion":"Improve the service. Food is good","date":"2018-12-30T05:46:53.970Z"}


                        }catch (Exception e){
                            Log.e("TAG", e.toString());
                        }

                        /*} else {
                            Log.e("Your Array Response", "Data Null");
                        }*/
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error is ", "" + error);
                    }
                }) {

                    //This is for Headers If You Needed
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/x-www.form-urlencoded");
                        //params.put("x-access-token", jwtToken);
                        return params;
                    }

                    //Pass Your Parameters here
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("x-access-token", "abc");
                        //params.put("Pass", PassWord);
                        params.put("name", "Text");
                        params.put("released_on", "2019-01-03");
                        params.put("rating", String.valueOf(salary));

                        Log.d("Tag", String.valueOf(params));

                        return params;

                    }
                };

                queue.add(request);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
