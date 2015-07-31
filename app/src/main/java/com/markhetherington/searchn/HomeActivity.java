package com.markhetherington.searchn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

    @Inject
    SearchNApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchNApplication.get(this).getAppComponent().inject(this);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(mApplication, "injected context", Toast.LENGTH_LONG);
    }

}
