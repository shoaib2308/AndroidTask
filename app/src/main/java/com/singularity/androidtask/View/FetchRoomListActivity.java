package com.singularity.androidtask.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.singularity.androidtask.Adapter.Adapter;
import com.singularity.androidtask.Model.Datum;
import com.singularity.androidtask.Model.MainDataResponse;
import com.singularity.androidtask.R;
import com.singularity.androidtask.Utils.ApiClient;
import com.singularity.androidtask.Utils.Constant;
import com.singularity.androidtask.Utils.IRestService;
import com.singularity.androidtask.Utils.onClickInterface;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchRoomListActivity extends AppCompatActivity {
    private IRestService iRestService;
    private RecyclerView roomRecyclerView;
    private List<Datum> roomListResponse = new ArrayList<>();
    private Adapter roomListAdapter;
    private onClickInterface onClickInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roomRecyclerView = (RecyclerView) findViewById(R.id.roomRecyclerView);

        /**
         * Getting roomId from Adpater via OnclickInterface
         */
        onClickInterface = new onClickInterface() {
            @Override
            public void setonClick(int roomId) {
                Intent i = new Intent(FetchRoomListActivity.this, LockDetailsActivity.class);
                i.putExtra("roomid", roomId);
                startActivity(i);
            }
        };

        roomListAdapter = new Adapter(FetchRoomListActivity.this, roomListResponse, onClickInterface);
        roomRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        roomRecyclerView.setAdapter(roomListAdapter);

        /**
         * Check for Internet Connection
         */
        if (Constant.isConnected(FetchRoomListActivity.this)) {
            getRoomList(Constant.getUnixTimeStamp()); // Calling method to get room list
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }

    public void getRoomList(final String unixTime) {
        iRestService = ApiClient.getRetrofit().create(IRestService.class);
        iRestService.getMainData(unixTime).enqueue(new Callback<MainDataResponse>() {
            @Override
            public void onResponse(Call<MainDataResponse> call, Response<MainDataResponse> response) {
                if (!response.body().getSuccess().toString().isEmpty()) {
                    roomListResponse.addAll(response.body().getData());
                    roomListAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(FetchRoomListActivity.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MainDataResponse> call, Throwable t) {

            }
        });

    }


}
