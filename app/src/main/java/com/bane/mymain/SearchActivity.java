package com.bane.mymain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.bane.fragment_one.OneFiveFragment;
import com.bane.fragment_one.OneThridFragment;
import com.bane.url.InternetURL;
import com.example.administrator.android32_asynctask_json.R;

public class SearchActivity extends AppCompatActivity {



    private TextView mActivitySearchTextview0;
    private String path;
    private String keyWord;
    private OneThridFragment otf;
    private OneFiveFragment off;
    private String type;

    private void assignViews() {
        mActivitySearchTextview0 = (TextView) findViewById(R.id.activity_search_textview0);

        Intent intent = getIntent();
        keyWord = intent.getStringExtra("name");
//        path = intent.getStringExtra("path")+"'"+keyWord+"'";
        path = intent.getStringExtra("path");
        type = intent.getStringExtra("type");
        mActivitySearchTextview0.setText(keyWord);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_search);

        assignViews();


        Bundle b = new Bundle();
        b.putString("path",path);

        if(type.equals("3")) {
            otf = new OneThridFragment();
            otf.setArguments(b);
            Log.i("11111111111111111", "assignViews: " + path);
            getSupportFragmentManager().beginTransaction().add(R.id.activity_search_frag, otf).commit();
        }else if(type.equals("5")){

            off = new OneFiveFragment();
            off.setArguments(b);
            Log.i("11111111111111111", "assignViews: " + path);
            getSupportFragmentManager().beginTransaction().add(R.id.activity_search_frag, off).commit();
        }
    }
}
