package com.moxi.handwritinglibs.listener;

import com.moxi.handwritinglibs.model.WriteModel.WLine;
import com.myscript.iink.PointerEvent;

import java.util.List;

/**
 * 绘制与myscript交互
 */
public interface MyScriptDrawListener {
    void onPenEvents(List<PointerEvent> datas);
    void onRubber(List<WLine> lines);
}
