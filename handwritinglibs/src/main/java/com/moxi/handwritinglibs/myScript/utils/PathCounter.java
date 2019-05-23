package com.moxi.handwritinglibs.myScript.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;

import com.moxi.handwritinglibs.model.WriteModel.WLine;
import com.moxi.handwritinglibs.writeUtils.PathUtils;
import com.moxi.handwritinglibs.writeUtils.PenUtils;
import com.myscript.iink.graphics.Point;
import com.myscript.iink.graphics.Transform;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：19-5-10
 * 创 建 人：黄文泰 Wade
 * 功    能：
 **/
@SuppressWarnings("WeakerAccess")
public class PathCounter {
    private Transform transform;
    public List<RectF> deleteCharsList;
    private List<RectF> charBeanList;

    public PathCounter(List<JiixForChars.CharsBean> charsBeanList, Transform transform) {
        this.transform = transform;
        deleteCharsList = new ArrayList<RectF>();
        charBeanList = new ArrayList<RectF>();
        for (JiixForChars.CharsBean charsBean : charsBeanList) {
            RectF charsRect = getGridRectF(charsBean.getGrid());
            if (charsBean.getItems() != null) {
                for (JiixForChars.CharsBean.ItemsBean itemsBean : charsBean.getItems()) {
                    RectF rf = new RectF();
                   Path path = getPath(itemsBean, transform);
                    path.computeBounds(rf, false);
                    Rect[] rectList = getRectListInPath(path,1);
                    for (Rect rect : rectList) {
                        rf.union(rect.left, rect.top, rect.right, rect.bottom);
                    }
                    charsRect.union(rf);
                }
            }
            charBeanList.add(charsRect);
        }
    }

    public void judegDeleteRect(List<WLine> lines){
        for (RectF rectF:charBeanList){
            for (WLine line:lines){
                if (PathUtils.getPathIntersect(rectF,line)){
                    if (!deleteCharsList.contains(rectF)){
                        deleteCharsList.add(rectF);
                    }
                }
            }
        }
    }


    private RectF getGridRectF(List<JiixForChars.CharsBean.GridBean> gridBeans) {
        RectF charRect = new RectF();
        if (gridBeans != null && gridBeans.size() == 4) {
            Point pt0 = transform.apply(gridBeans.get(0).getX(), gridBeans.get(0).getY());
            Point pt2 = transform.apply(gridBeans.get(2).getX(), gridBeans.get(2).getY());
            charRect = new RectF(pt0.x, pt0.y, pt2.x, pt2.y);
        }
        return charRect;
    }

    private Rect[] getRectListInPath(Path path, float penSize) {
        int iCurStep, iCount;
        float fSegmentLen = 2.0f;    // rate of flow
        PathMeasure mPathMeasure = new PathMeasure(path, false);
        float[] mLocalMatrixValues = new float[9];
        fSegmentLen = (fSegmentLen * penSize / 2);
        iCount = (int) Math.ceil((mPathMeasure.getLength() / fSegmentLen));
        Rect[] list = new Rect[iCount];
        if (iCount > 0) {
            int radius = (int) (Math.ceil(penSize) / 2);
            Matrix mxTransform = new Matrix();
            for (iCurStep = 0; iCurStep < iCount; iCurStep++) {
                mPathMeasure.getMatrix(fSegmentLen * iCurStep, mxTransform, PathMeasure.POSITION_MATRIX_FLAG
                        + PathMeasure.TANGENT_MATRIX_FLAG);
                mxTransform.getValues(mLocalMatrixValues);
                Rect pointRect = new Rect();
                pointRect.set((int) mLocalMatrixValues[Matrix.MTRANS_X] - radius,
                        (int) (mLocalMatrixValues[Matrix.MTRANS_Y] - radius),
                        (int) mLocalMatrixValues[Matrix.MTRANS_X] + radius,
                        (int) mLocalMatrixValues[Matrix.MTRANS_Y] + radius
                );
                list[iCurStep] = pointRect;
            }
        }
        return list;
    }


    private Path getPath(JiixForChars.CharsBean.ItemsBean item, Transform transform) {
        Path path = new Path();
        int length = item.getX().size();
        if (length > 0) {
            Point pt = transform.apply(item.getX().get(0), item.getY().get(0));
            path.moveTo(pt.x, pt.y);
            for (int i = 1; i < length; i++) {
                pt = transform.apply(item.getX().get(i), item.getY().get(i));
                path.lineTo(pt.x, pt.y);
            }
        }
        return path;
    }

    public Transform getTransform() {
        return transform;
    }


}
