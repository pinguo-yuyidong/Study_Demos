package us.yydcdut.camera2;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by yuyidong on 15-1-15.
 */
public class CameraActivity extends Activity implements SurfaceHolder.Callback {
    private long firstRecordTime;
    private long openRecordTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firstRecordTime = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
    }

    private Camera mCamera;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
        openRecordTime = System.currentTimeMillis();
        Log.i("Time", "openRecordTime - firstRecordTime = " + (openRecordTime - firstRecordTime));
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
