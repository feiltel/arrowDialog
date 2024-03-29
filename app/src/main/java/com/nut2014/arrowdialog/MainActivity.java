package com.nut2014.arrowdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jiang.android.indicatordialog.ShowUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.left_im).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showArrowDialog(v,0.15f);
            }
        });
        findViewById(R.id.center_im).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showArrowDialog(v,0.5f);
            }
        });
        findViewById(R.id.right_im).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showArrowDialog(v,0.85f);
            }
        });
    }

    private void showArrowDialog(View v,float indicatorFloat) {
        new ShowUtils()
                .setActivity(this)
                .setClickView(v)
                //.setLight(true)//设置成白色主题
                .setIndicatorFloat(indicatorFloat)//箭头位置
                // .setWidth(400)//设置窗口宽度
                .setWindowOffset(0,0)//窗口偏移
                .setmLists(new String[]{"创建群聊", "加好友", "扫一扫", "面对面快传"})//显示Iitem
                .showTopDialog(new ShowUtils.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(View view, int position) {
                        Toast.makeText(MainActivity.this, "POS" + position, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_data) {
            Toast.makeText(MainActivity.this, "POS" , Toast.LENGTH_SHORT).show();
            showArrowDialog(findViewById(R.id.add_data),0.85f);
        }
        return super.onOptionsItemSelected(item);
    }

}
