package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice14MaskFilterView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private MaskFilter maskFilter1;
    private MaskFilter maskFilter2;
    private MaskFilter maskFilter3;
    private MaskFilter maskFilter4;

    public Practice14MaskFilterView(Context context) {
        super(context);
    }

    public Practice14MaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice14MaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.what_the_fuck);
        maskFilter1 = new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL);
        maskFilter2 = new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID);
        maskFilter3 = new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER);
        maskFilter4 = new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 用 Paint.setMaskFilter 来设置不同的 BlurMaskFilter
        /**
         * radius 参数是模糊的范围，  style 是模糊的类型。一共有四种：

         NORMAL: 内外都模糊绘制
         SOLID: 内部正常绘制，外部模糊
         INNER: 内部模糊，外部不绘制
         OUTER: 内部不绘制，外部模糊（什么鬼？）
         */

        // 第一个：NORMAL
        paint.setMaskFilter(maskFilter1);
        canvas.drawBitmap(bitmap, 100, 50, paint);

        // 第二个：INNER
        paint.setMaskFilter(maskFilter2);
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 200, 50, paint);

        // 第三个：OUTER
        paint.setMaskFilter(maskFilter3);
        canvas.drawBitmap(bitmap, 100, bitmap.getHeight() + 100, paint);

        // 第四个：SOLID
        paint.setMaskFilter(maskFilter4);
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 200, bitmap.getHeight() + 100, paint);
    }
}
