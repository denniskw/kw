package gunlei.gun.com.gunleibarrage;

import android.app.ActionBar;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler;
    TextView show_dialog;
    TextView to_second_activity,close_dialog;
    private boolean isAdded = false; // 是否已增加悬浮窗
    private static WindowManager wm;
    private static WindowManager.LayoutParams params;
    private Button btn_floatView;
    ActivityManager mActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        show_dialog = (TextView) findViewById(R.id.show_dialog);

        show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDialog(MainActivity.this);
                createFloatView();

            }
        });
        to_second_activity = (TextView) findViewById(R.id.to_second_activity);
        to_second_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        close_dialog = (TextView) findViewById(R.id.close_dialog);
        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseFloatView();
            }
        });
    }

//    @Override
//    protected void setBaseContext()
//    {
//
//    }

    private void showDialog(final Context context) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                View layout = View.inflate(context, R.layout.barrage, null);
                final Dialog dialog = new AlertDialog.Builder(context).create();
                dialog.show();
                dialog.getWindow().setGravity(Gravity.TOP);
                dialog.getWindow().setContentView(layout);
//                mHandler.postDelayed(this, 3000);
            }
        };
        mHandler.postDelayed(runnable, 1000);

    }

    private void createFloatView() {
        if(!isAdded) {
//            <span style="white-space:pre">    </span>
            btn_floatView = new Button(getApplicationContext());
            btn_floatView.setText("17秒前，有人订购此车辆");
            btn_floatView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"Roast",Toast.LENGTH_LONG).show();
                }
            });
//        RelativeLayout.LayoutParams par=new  RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
//        par.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        par.addRule(RelativeLayout.ALIGN_LEFT);
//        btn_floatView.setLayoutParams(par);
//        btn_floatView.setBackgroundResource(R.drawable.barrage);
//        btn_floatView.setLayoutParams();

//            wm = (WindowManager) getApplicationContext()
//                    .getSystemService(Context.WINDOW_SERVICE);
            wm=(WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);

            // 设置window type
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
       /*
        * 如果设置为params.type = WindowManager.LayoutParams.TYPE_PHONE;
        * 那么优先级会降低一些, 即拉下通知栏不可见
        */

            params.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明

            // 设置Window flag
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
       /*
        * 下面的flags属性的效果形同“锁定”。
        * 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
       wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
                              | LayoutParams.FLAG_NOT_FOCUSABLE
                              | LayoutParams.FLAG_NOT_TOUCHABLE;
        */

//         设置悬浮窗的长得宽
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.x=70;
        params.y=200;
//            params.verticalMargin=200;
            params.gravity = Gravity.LEFT | Gravity.TOP;

//        // 设置悬浮窗的Touch监听
//        btn_floatView.setOnTouchListener(new View.OnTouchListener() {
//            int lastX, lastY;
//            int paramX, paramY;
//
//            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        lastX = (int) event.getRawX();
//                        lastY = (int) event.getRawY();
//                        paramX = params.x;
//                        paramY = params.y;
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        int dx = (int) event.getRawX() - lastX;
//                        int dy = (int) event.getRawY() - lastY;
//                        params.x = paramX + dx;
//                        params.y = paramY + dy;
//                        // 更新悬浮窗位置
//                        wm.updateViewLayout(btn_floatView, params);
//                        break;
//                }
//                return true;
//            }
//        });
//        params.
            wm.addView(btn_floatView, params);
            isAdded = true;
        }
        else testHandler.sendEmptyMessage(1);
    }
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch(msg.what) {
//                case HANDLE_CHECK_ACTIVITY:
//                    if(isHome()) {
//                        if(!isAdded) {
//                            wm.addView(btn_floatView, params);
//                            isAdded = true;
//                        }
//                    } else {
//                        if(isAdded) {
//                            wm.removeView(btn_floatView);
//                            isAdded = false;
//                        }
//                    }
//                    mHandler.sendEmptyMessageDelayed(HANDLE_CHECK_ACTIVITY, 1000);
//                    break;
//            }
//        }
//    };
//    public boolean isHome(){
//        if(mActivityManager == null) {
//            mActivityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
//        }
//        List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
//        return homeList.contains(rti.get(0).topActivity.getPackageName());
//    }

    private void CloseFloatView()
    {
        btn_floatView.setVisibility(View.GONE);
        isAdded = false;
    }
    Handler testHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    btn_floatView.setText("这个是第二条信息");
            }
        }
    };

}
