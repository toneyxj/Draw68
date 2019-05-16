package com.moxi.writeNote.adapter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Created by xj on 2018/8/8.
 */

public class PdfBackground extends PdfPageEventHelper {
    public static int BORDER_WIDTH = 0;

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        //设置pdf背景色为白色
        PdfContentByte canvas = writer.getDirectContentUnder();
        Rectangle rect = document.getPageSize();
        canvas.setColorFill(BaseColor.WHITE);
        canvas.rectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight());
        canvas.fill();
        //设置pdf页面内间距
        PdfContentByte canvasBorder = writer.getDirectContent();
        Rectangle rectBorder = document.getPageSize();
        rectBorder.setBorder(Rectangle.BOX);
        rectBorder.setBorderWidth(BORDER_WIDTH);
        rectBorder.setBorderColor(BaseColor.WHITE);
        rectBorder.setUseVariableBorders(true);
        canvasBorder.rectangle(rectBorder);
    }
}
