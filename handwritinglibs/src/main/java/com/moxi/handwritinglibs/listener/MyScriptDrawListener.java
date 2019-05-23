package com.moxi.handwritinglibs.listener;

import com.moxi.handwritinglibs.model.WriteModel.WLine;
import com.myscript.iink.PointerEvent;

import java.util.List;

/**
 * 绘制与myscript交互
 */
public interface MyScriptDrawListener {
    void onPenEvents(List<PointerEvent> datas);

    /**
     * up后删除的总线
     * @param lines 线段集合
     */
    void onRubber(List<WLine> lines);

    /**
     * 当前正在删除的线
     * @param list
     */
    void onCurRubberLine(List<WLine> list);

}
