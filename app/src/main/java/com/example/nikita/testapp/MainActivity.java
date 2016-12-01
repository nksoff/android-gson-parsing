package com.example.nikita.testapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://json-storage.herokuapp.com/community/105";
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        JSONParser parser = new JSONParser();
                        UserCollection collection = parser.parse(response.body().string());

                        Log.d("JSON_name", collection.getName());
                        Log.d("JSON_id", String.valueOf(collection.getId()));
                        Log.d("JSON_size", String.valueOf(collection.getSize()));

                        Ordering<User> uSort = Ordering.natural().reverse().onResultOf(new Function<User, Integer>() {
                            @Nullable
                            @Override
                            public Integer apply(User input) {
                                return input.getName().length();
                            }
                        });

                        Collections.sort(Arrays.asList(collection.getUsers()), uSort);

                        int i = 1;
                        for(User u : collection.getUsers()) {
                            Log.d("User name", u.getName());
                        }
                    }
                });

            }
        });
        t.run();
    }
}
