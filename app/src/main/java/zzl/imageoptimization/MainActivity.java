package zzl.imageoptimization;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import zzl.imageoptimization.adapter.PhotoWallAdapter;
import zzl.imageoptimization.data.Images;

public class MainActivity extends Activity {

    /**
     * 用于展示照片墙的GridView
     */
    private GridView mPhotoWall;

    /**
     * GridView的适配器
     */
    private PhotoWallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhotoWall = findViewById(R.id.photo_wall);

        adapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls, mPhotoWall);

        mPhotoWall.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        adapter.cancelAllTasks();
    }

}

