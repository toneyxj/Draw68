package com.moxi.handwritinglibs.listener;

import com.myscript.iink.PointerEvent;

import java.util.List;

/**
 * 绘制与myscript交互
 */
public interface MyScriptDrawListener {
    void onPenEvents(List<PointerEvent> datas);
//    void onRubber(List<PointerEvent> datas);
}
