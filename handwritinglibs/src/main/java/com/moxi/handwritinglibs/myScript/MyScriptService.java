package com.moxi.handwritinglibs.myScript;

import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.moxi.handwritinglibs.myScript.utils.FontMetricsProvider;
import com.moxi.handwritinglibs.myScript.utils.JiixForChars;
import com.moxi.handwritinglibs.utils.StringUtils;
import com.mx.mxbase.constant.APPLog;
import com.mx.mxbase.utils.ToastUtils;
import com.myscript.iink.Configuration;
import com.myscript.iink.ContentPackage;
import com.myscript.iink.ContentPart;
import com.myscript.iink.Editor;
import com.myscript.iink.Engine;
import com.myscript.iink.MimeType;
import com.myscript.iink.ParameterSet;
import com.myscript.iink.Renderer;
import com.myscript.iink.graphics.Transform;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2018/8/13 0013.
 */

@SuppressWarnings("WeakerAccess")
public class MyScriptService {
    private Engine engine;
    private Editor editor;
    private Gson gson;
    private ContentPackage package1;
    private ContentPart part;

    public MyScriptService() {
        gson = new Gson();
    }

    public void init(float dpiX, float dpiY, Context context) {
        try {
            engine = IInkApplication.getEngine();
            Configuration conf = engine.getConfiguration();
            String confDir = "zip://" + context.getPackageCodePath() + "!/assets/conf";
            conf.setStringArray("configuration-manager.search-path", new String[]{confDir});
            String tempDir = context.getFilesDir().getPath() + File.separator + "tmp";
            conf.setString("content-package.temp-folder", tempDir);

            conf.setString("lang", "zh_CN");


            conf.setNumber("text.margin.top", 0);
            conf.setNumber("text.margin.left", 0);
            conf.setNumber("text.margin.right", 0);

            // Configure the engine to disable guides (recommended)
            //配置引擎以禁用指南（推荐）
            engine.getConfiguration().setBoolean("text.guides.enable", false);

            // Create a renderer with a null render target
            //使用null渲染目标创建渲染器
//        float dpiX = 300;
//        float dpiY = 800;
            Renderer renderer = engine.createRenderer(dpiX, dpiY, null);

            // Create the editor
            //创建编辑器
            editor = engine.createEditor(renderer);

            // The editor requires a font metrics provider and a view size *before* calling setPart()
            //在调用setPart（）之前，编辑器需要字体度量提供程序和视图大小*
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            Map<String, Typeface> typefaceMap = new HashMap<String, Typeface>();
            editor.setFontMetricsProvider(new FontMetricsProvider(displayMetrics, typefaceMap));
            editor.setViewSize((int) dpiX, (int) dpiY);
        }catch (Exception e){
            ToastUtils.getInstance().showToastShort("初始化myScript引擎失败！！");
        }
    }

    public boolean setPackage(Context context,String midr,String name){
        if (editor!=null)
            editor.clear();
        if (package1!=null) {
            package1.close();
        }
        if (part != null) {
            part.close();
        }

        String packageName = name+".iink";
        File dmid = new File(context.getFilesDir(), midr);
        if (!dmid.exists())dmid.mkdirs();
        File file=new File(dmid,packageName);
        APPLog.e("setPackage",file.getAbsoluteFile());
        boolean res=file.exists();
        package1 = null;
        try {
            if (res){
                APPLog.e("openPackage",file.getAbsoluteFile());
                package1 = engine.openPackage(file);
                part = package1.getPart(0);//
            }else {
                APPLog.e("createPackage",file.getAbsoluteFile());
                package1 = engine.createPackage(file);
                part = package1.createPart("Text");//
            }
            APPLog.e("Package-content",package1.getPartCount());
            editor.setPart(part);
        } catch (IOException e) {
            e.printStackTrace();
            res=false;
        }
        return res;
    }

    public void delete(Context context,String midr,String name){
        String packageName = name+".iink";
        File dmid = new File(context.getFilesDir(), midr);
        if (!dmid.exists())dmid.mkdirs();
        File file=new File(dmid,packageName);
        APPLog.e("setPackage",file.getAbsoluteFile());
        if (file.exists())
        file.delete();
    }


    public void savePacke(){
        if (package1==null)return;
        try {
            package1.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String change() throws IOException, JSONException {
        ParameterSet parameterSet = editor.getEngine().createParameterSet();
        parameterSet.setBoolean("export.jiix.strokes", false);
        parameterSet.setBoolean("export.jiix.glyphs", false);
        parameterSet.setBoolean("export.jiix.primitives", false);
        parameterSet.setBoolean("export.jiix.style", false);
        parameterSet.setBoolean("export.jiix.text.chars", false);
        parameterSet.setBoolean("export.jiix.text.words", true);

        String jiixString = editor.export_(editor.getRootBlock(), MimeType.JIIX, parameterSet);
        JSONObject jo = new JSONObject(jiixString);
        String label = "";
        if (jo.has("label")) {
            label = jo.getString("label");
        }
        return label;
    }


    public void clear() {
        editor.clear();
    }

    public Editor getEditor() {
        return editor;
    }

    public void close() {
        APPLog.e("closemyscriptService");
        if (editor != null)
            editor.close();
        if (engine != null)
            engine.close();
        if (package1 != null)
            package1.close();
        if (part != null)
            part.close();
        engine = null;

    }

    public Transform getTransform() {
        return editor.getRenderer().getViewTransform();
    }

    public MimeType[] getExportType() {
        return editor.getSupportedExportMimeTypes(editor.getRootBlock());
    }

    public String export(MimeType mimeType) throws IOException {
        if (mimeType == MimeType.JIIX) {
            return exportJiixByChar();
        }
        return editor.export_(editor.getRootBlock(), mimeType);
    }

    public String exportJiixByChar() throws IOException {
        ParameterSet parameterSet = editor.getEngine().createParameterSet();
        parameterSet.setBoolean("export.jiix.strokes", true);
        parameterSet.setBoolean("export.jiix.glyphs", false);
        parameterSet.setBoolean("export.jiix.primitives", false);
        parameterSet.setBoolean("export.jiix.style", true);
        parameterSet.setBoolean("export.jiix.text.chars", true);
        parameterSet.setBoolean("export.jiix.text.words", false);
        return editor.export_(editor.getRootBlock(), MimeType.JIIX, parameterSet);
    }

    /**
     * 判断引擎是否闲置，限制状态下可以进行保存，跳转等操作
     * @return
     */
    public boolean isIdle(){
        return editor.isIdle();
    }
    /**
     * 导入json文件
     * @param jiix
     */
    public void loadJiix(String jiix){
        editor.import_(MimeType.JIIX, jiix, editor.getRootBlock());
    }

    public JiixForChars getJiixForChars() throws IOException {
        return gson.fromJson(exportJiixByChar(), JiixForChars.class);
    }

}
