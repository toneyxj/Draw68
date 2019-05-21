package com.moxi.handwritinglibs.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.moxi.handwritinglibs.db.DBTool.BackImageTools;
import com.moxi.handwritinglibs.db.WritePadUtils;
import com.moxi.handwritinglibs.listener.DbPhotoListener;
import com.moxi.handwritinglibs.listener.JiixLodingListener;
import com.moxi.handwritinglibs.model.WriteModel.WLine;
import com.moxi.handwritinglibs.model.WriteModel.WritePageData;
import com.moxi.handwritinglibs.writeUtils.PenUtils;
import com.mx.mxbase.constant.APPLog;
import com.mx.mxbase.utils.LocationImageLoader;
import com.mx.mxbase.utils.WindowsUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JiixLoaderManager {
    private static JiixLoaderManager instatnce = null;

    public static JiixLoaderManager getInstance() {
        if (instatnce == null) {
            synchronized (JiixLoaderManager.class) {
                if (instatnce == null) {
                    instatnce = new JiixLoaderManager(3);
                }
            }
        }
        return instatnce;
    }

    /**
     * 开启线程数,默认为5
     */
    private int threadCount = 3;
    /**
     * 图片缓存的核心类
     */
    private LruCache<String, String> mLruCache;
    /**
     * 线程池
     */
    private ExecutorService mThreadPool;
    /**
     * 运行在UI线程的handler，用于给ImageView设置图片
     */
    private Handler mHandler;
    private boolean isStop=false;
    public void isStopPool(boolean isStop){
        this.isStop=isStop;
    }
    private JiixLoaderManager(int threadCount) {
        this.threadCount = threadCount;

        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 16;
        // 设置图片缓存大小为程序最大可用内存的1/8
        mLruCache = new LruCache<String, String>(cacheSize) {
            @Override
            protected int sizeOf(String key, String bitmap) {
                return bitmap.getBytes().length;
            }
        };

        mThreadPool = Executors.newFixedThreadPool(threadCount);
    }

    private void createHandler() {
        if (mHandler == null) {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    BeanHolder holder = (BeanHolder) msg.obj;

                    String path = holder.path;
                    JiixLodingListener listener = holder.listener;

                    if (listener != null)
                        listener.onLoaderSucess(path,getFromLruCache(path));
                }
            };
        }
    }
    /**
     * 获取jiix内容
     * @param path 背景图片路径
     * @param listener  获取图片结果监听
     */
    public void loding(final String path,final JiixLodingListener listener){
        createHandler();
        //获得缓存数据
        String bm = getFromLruCache(path);
        if (bm != null) {
            //构建传输参数
            BeanHolder holder = new BeanHolder();
            holder.path = path;
            holder.listener = listener;

            Message message = Message.obtain();
            message.obj = holder;
            mHandler.sendMessage(message);
        } else {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (isStop)return;
                    String bm = getFromLruCache(path);
                    if (bm == null) {
                        addToLruCache(path,readJiix(path));
                    }
                    BeanHolder holder = new BeanHolder();
                    holder.path = path;
                    holder.listener = listener;

                    Message message = Message.obtain();
                    message.obj = holder;
                    mHandler.sendMessage(message);
                }
            };
            //添加入线程池
            mThreadPool.execute(runnable);
        }
    }
    /**
     * 从LruCache中获取一张图片，如果不存在就返回null
     */
    public String getFromLruCache(String key) {
        return mLruCache.get(key);
    }
    /**
     * lruCache中添加一张图
     *
     * @param key
     * @param bitmap
     */
    public void addToLruCache(String key, String bitmap) {
        if (key==null||bitmap==null)return;
        mLruCache.put(key, bitmap);
    }

    /**
     * 清除缓存
     * @param key
     */
    public void clearData(String key){
        mLruCache.remove(key);
    }
    private class BeanHolder {
        String path;
        JiixLodingListener listener;
    }

    public String readJiix(String path){
        String value=null;
        //文件输入流
        FileInputStream in=null;
        //设置文件路径
        File file=new File(path);

        try {
            in=new FileInputStream(file);
            //使用缓冲来读
            byte[] buf=new byte[1024];//每1024字节读一次
            StringBuilder builder=new StringBuilder();
            while (in.read(buf)!=-1) {
                builder.append(new String(buf).trim());
            }
            value=builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(in!=null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

}
