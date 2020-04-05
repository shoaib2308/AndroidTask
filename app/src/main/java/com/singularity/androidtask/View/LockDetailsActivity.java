package com.singularity.androidtask.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.singularity.androidtask.Model.LockDetailsResponse;
import com.singularity.androidtask.R;
import com.singularity.androidtask.Utils.ApiClient;
import com.singularity.androidtask.Utils.Constant;
import com.singularity.androidtask.Utils.IRestService;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LockDetailsActivity extends AppCompatActivity {
    IRestService iRestService;
    TextView tv_mac, tv_name, tv_status;
    int roomid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_details);

        /* getting room id from adapter */
        Intent i = getIntent();
        roomid = i.getIntExtra("roomid", -1);

        tv_mac = findViewById(R.id.tv_mac);
        tv_name = findViewById(R.id.tv_name);
        tv_status = findViewById(R.id.tv_status);

        // Check Internet Connection
        if (Constant.isConnected(LockDetailsActivity.this)) {
            getLockDetails(roomid, Constant.getUnixTimeStamp()); /* Calling the method to get Lock Details */
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }

    private void getLockDetails(int roomId, String unixTime) {
        iRestService = ApiClient.getRetrofit().create(IRestService.class);
        iRestService.getLockDetails(roomId, unixTime).enqueue(new Callback<LockDetailsResponse>() {
            @Override
            public void onResponse(Call<LockDetailsResponse> call, Response<LockDetailsResponse> response) {
                tv_mac.setText("MAC : " + response.body().getMAC());
                tv_name.setText("name : " + response.body().getName());
                tv_status.setText("status : " + response.body().getSuccess().toString());
            }

            @Override
            public void onFailure(Call<LockDetailsResponse> call, Throwable t) {

            }
        });
    }
}
