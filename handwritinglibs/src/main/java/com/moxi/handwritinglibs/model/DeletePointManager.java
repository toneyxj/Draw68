package com.moxi.handwritinglibs.model;

import android.view.MotionEvent;

import com.moxi.handwritinglibs.model.WriteModel.WPoint;

import java.util.ArrayList;
import java.util.List;

public class DeletePointManager {
    private static DeletePointManager install;
    public synchronized static DeletePointManager getInstall(){
        if (install==null) {
            synchronized (DeletePointManager.class) {
                if (install==null){
                    install=new DeletePointManager();
                }
            }
        }
        return install;
    }
    /**
     * 删除线框
     */
    public int deletelineWidth=0;

    /**
     *删除点的集合
     */
    private List<WPoint> dPoints=new ArrayList<WPoint>();
    private int action;

    private   void setDeletelineWidth(int deletelineWidth){
        this.deletelineWidth=deletelineWidth;
    }
    public synchronized WPoint  getFirstPoint(){
        try {
            if (dPoints.size()>0){
                return dPoints.remove(0);
            }
        }catch (Exception e){ }
        return null;
    }
    /**
     * 目前处理，删除全部使用点删除的形式
     *
     * @param x
     * @param y
     */
    public void deleteData(int acion,float x, float y, int lineWidth) {
        this.action=acion;
        setDeletelineWidth(lineWidth);
        dPoints.add(new WPoint(x,y,0));
    }
    public boolean deleteUp(){
        boolean is=action==MotionEvent.ACTION_UP;
        return is&&dPoints.size()==0;
    }
}
