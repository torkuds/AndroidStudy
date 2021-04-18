package com.torkuds.androidstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Window;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerOptions;
import com.mylhyl.zxing.scanner.ScannerView;

public class ScannerActivity extends AppCompatActivity implements OnScannerCompletionListener {

    private ScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scanner);

        mScannerView = (ScannerView) findViewById(R.id.scanner_view);

        mScannerView.setOnScannerCompletionListener(this);

//        mScannerView.toggleLight(true);


        // 扫描线条使用 tint 着色
        Drawable originalDrawable = ContextCompat.getDrawable(this, R.mipmap.wx_scan_line);
//        Drawable originalDrawable = ContextCompat.getDrawable(this, R.mipmap.zfb_grid_scan_line);
        Drawable tintDrawable = DrawableCompat.wrap(originalDrawable).mutate();
        DrawableCompat.setTint(tintDrawable, Color.RED);

        ScannerOptions.Builder builder = new ScannerOptions.Builder();
        builder
//                .setFrameStrokeColor(Color.RED)
//                .setFrameStrokeWidth(1.5f)
//                .setFrameSize(256, 256)
//                .setFrameCornerLength(22)
//                .setFrameCornerWidth(2)
//                .setFrameCornerColor(0xff06c1ae)
//                .setFrameCornerInside(true)

                .setLaserLine(ScannerOptions.LaserStyle.DRAWABLE_LINE, tintDrawable)
//                .setLaserLine(ScannerOptions.LaserStyle.DRAWABLE_GRID, tintDrawable)
//                .setLaserLine(ScannerOptions.LaserStyle.DRAWABLE_LINE,
//                        getResources().getDrawable(R.mipmap.wx_scan_line))
//                .setLaserLineColor(0xff06c1ae)
//                .setLaserLineHeight(18)

//                .setLaserLine(ScannerOptions.LaserStyle.RES_LINE,R.mipmap.wx_scan_line)
//                .setLaserLine(ScannerOptions.LaserStyle.RES_GRID, R.mipmap.zfb_grid_scan_line)//网格图
//                .setFrameCornerColor(0xFF26CEFF)//支付宝颜色

                .setScanFullScreen(true)

//                .setFrameHide(false)
//                .setFrameCornerHide(true)
//                .setFrameHide(true)
//                .setLaserMoveFullScreen(false)

//                .setViewfinderCallback(new ScannerOptions.ViewfinderCallback() {
//                    @Override
//                    public void onDraw(View view, Canvas canvas, Rect frame) {
//                        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.connect_logo);
//                        canvas.drawBitmap(bmp, frame.right / 2, frame.top - bmp.getHeight(), null);
//                    }
//                })

                .setScanMode(BarcodeFormat.QR_CODE)
                .setTipText("请将二维码对准摄像头")
                .setTipTextSize(19)
                .setTipTextColor(getResources().getColor(R.color.design_default_color_secondary_variant))
//                .setCameraZoomRatio(2)
        ;

        mScannerView.setScannerOptions(builder.build());
    }

    @Override
    protected void onResume() {
        mScannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mScannerView.onPause();
        super.onPause();
    }

    @Override
    public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_SHORT).show();
        vibrate();
//        mScannerView.restartPreviewAfterDelay(0);
        Intent intent = new Intent();
        intent.putExtra("result", rawResult.getText());
        finish();
        setResult(10, intent);
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}