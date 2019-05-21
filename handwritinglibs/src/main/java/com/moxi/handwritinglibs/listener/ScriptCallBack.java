package com.moxi.handwritinglibs.listener;

public interface ScriptCallBack {
    void saveResult();

    /**
     * 文字转换完成
     * @param txt 文本
     */
    void transformReuslit(String txt);
    void scriptFail(String fail);
}
