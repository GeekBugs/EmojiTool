package com.f1reking.emojitool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.style.ImageSpan;

/**
 * author: huangyh
 * date: 2017/7/4 11:50
 * desc: 自定义ImageSpan
 */

public class EmojiImageSpan extends ImageSpan {

    //自定义对齐方式--与文字中间线对齐
    private int ALIGN_FONTCENTER = 2;

    public EmojiImageSpan(Context context, Bitmap b) {
        super(context, b);
    }

    public EmojiImageSpan(Context context, Bitmap b, int verticalAlignment) {
        super(context, b, verticalAlignment);
    }

    public EmojiImageSpan(Context context, @DrawableRes int resourceId) {
        super(context, resourceId);
    }

    public EmojiImageSpan(Context context, @DrawableRes int resourceId, int verticalAlignment) {
        super(context, resourceId, verticalAlignment);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom,
        Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        //获取画笔的文字绘制时的具体测量数据
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY = bottom - drawable.getBounds().bottom;
        if (mVerticalAlignment == ALIGN_BASELINE) {
            transY -= fm.descent;
        } else if (mVerticalAlignment == ALIGN_FONTCENTER) {
            transY = ((y + fm.descent) + (y + fm.ascent)) / 2 - drawable.getBounds().bottom / 2;
        }
        canvas.translate(x, transY);
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        Drawable drawable = getDrawable();
        Rect rect = drawable.getBounds();
        if (fm != null) {
            Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
            int fontHeight = fmPaint.bottom - fmPaint.top;
            int drHeight = rect.bottom - rect.top;

            int top = drHeight / 2 - fontHeight / 4;
            int bottom = drHeight / 2 + fontHeight / 4;

            fm.ascent = -bottom;
            fm.top = -bottom;
            fm.bottom = top;
            fm.descent = top;
        }
        return rect.right;
    }
}
