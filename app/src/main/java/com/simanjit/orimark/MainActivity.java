package com.simanjit.orimark;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    EditText inputid;
    Button btnEnter;
    TextView userid,id,title,completed;
    UserViewModel model;
    ConstraintLayout dataLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.setTitle("Orimark Fetch Data");

        inputid = findViewById(R.id.inputid);
        btnEnter = findViewById(R.id.btnEnter);
        userid = findViewById(R.id.userid);
        id = findViewById(R.id.id);
        title = findViewById(R.id.title);
        completed = findViewById(R.id.completed);
        dataLayout = findViewById(R.id.dataLayout);


        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataLayout.setVisibility(View.GONE);
                if (inputid.getText().toString().trim().length()>0){
                loadDetail();
                }else{
                    Toast.makeText(getApplicationContext(), "Give an Id to fetch data ", Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    public void loadDetail() {
        model = ViewModelProviders.of(this).get(UserViewModel.class);

        model.getUserResponse(Integer.parseInt(inputid.getText().toString())).observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(@Nullable UserResponse userResponse) {
                if ( userResponse != null) {
                    dataLayout.setVisibility(View.VISIBLE);
                    userid.setText(getString(R.string.stringUserid, userResponse.getUserId()));
                    id.setText(getString(R.string.stringId, userResponse.getId()));
                    title.setText(getString(R.string.stringTitle, userResponse.getTitle()));
                    completed.setText(getString(R.string.stringCompleted, userResponse.getCompleted()));
                }else {
                    dataLayout.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No data available for this Id", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}