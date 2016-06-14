package com.test.mapptracker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


import cn.com.iresearch.mapptracker.IRCallBack;
import cn.com.iresearch.mapptracker.IRMonitor;
import cn.com.iresearch.mapptracker.test.R;

public class MainActivity extends Activity implements IRCallBack {

    private String TAG = MainActivity.class.getSimpleName();

    private TextView tv_pre, tv_send, tv_result;
    private Handler handler = new Handler();

    private static final MyIRCallBack myIRCallBack = new MyIRCallBack();

    private final static class MyIRCallBack implements IRCallBack {

        private IRCallBack irCallBack;

        public void setIrCallBack(IRCallBack irCallBack) {
            this.irCallBack = irCallBack;
        }

        @Override
        public void preSend() {
            if (irCallBack != null) {
                irCallBack.preSend();
            }
        }

        @Override
        public void sendSuccess() {
            if (irCallBack != null) {
                irCallBack.sendSuccess();
            }
        }

        @Override
        public void sendFail(String s) {
            if (irCallBack != null) {
                irCallBack.sendFail(s);
            }
        }
    }

    ;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 下面这行代码的逻辑是为了方便测试而做，正常开发可无视
         */
        myIRCallBack.setIrCallBack(this);
        /**
         * 初始化
         *
         * Context context 上下文
         * appkey 艾瑞分配的appkey
         * youUID 使用你们自己定义的uid，以便核对数据，(可为空，默认为imei)
         * showLog 显示日志
         * IRCallBack 回调信息
         */
        IRMonitor.getInstance().init(this, "test_android", "your_uid", true, myIRCallBack);

        /**
         * (可选) context 上下文 channel 渠道名 (也可在AndroidManifest中配置渠道名)
         */
        //IRMonitor.getInstance().setAppChannel(this, "channel");


        initView();


    }

    private void initView() {
        tv_pre = (TextView) findViewById(R.id.pre_send);
        tv_send = (TextView) findViewById(R.id.start_send);
        tv_result = (TextView) findViewById(R.id.send_result);
    }


    @Override
    protected void onResume() {
        super.onResume();

        //onResume调用
        IRMonitor.getInstance().onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();

        //onPause中调用
        IRMonitor.getInstance().onPause(this);
    }


    //-----------------------CallBack---------------------------

    @Override
    public void preSend() {
        // 数据上报前
        System.out.println("上报数据前,记录一次");


        handler.post(new Runnable() {
            @Override
            public void run() {
                tv_pre.setTextColor(getResources().getColor(R.color.color_send_ok));
                tv_pre.setText(getString(R.string.pre_send) + "\t" + getTime_yyyyMMddHHmmss());
            }
        });
    }

    @Override
    public void sendSuccess() {
        // 数据上报，成功
        System.out.println("上报数据成功,记录一次");


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_result.setTextColor(getResources().getColor(R.color.color_send_ok));
                tv_result.setText(getString(R.string.send_result_succeed) + "\t" + getTime_yyyyMMddHHmmss());
            }
        }, 500);
    }

    @Override
    public void sendFail(String msg) {
        // 数据上报，失败
        System.out.println("上报数据失败,记录一次:" + msg);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_result.setTextColor(getResources().getColor(R.color.color_send_fail));
                tv_result.setText(getString(R.string.send_result_fail) + "\t" + getTime_yyyyMMddHHmmss());
            }
        }, 500);
    }

    public static String getTime_yyyyMMddHHmmss() {
        try {
            Date newTime = new Date(System.currentTimeMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            return simpleDateFormat.format(newTime);
        } catch (Exception ex) {
            return "";
        }
    }
}
