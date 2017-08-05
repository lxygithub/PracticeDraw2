package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    private PathEffect corner;
    private PathEffect discrete;
    private PathEffect dash;
    private PathEffect pathDash;
    private PathEffect sum;
    private PathEffect compose;

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);

        corner = new CornerPathEffect(20);
        discrete = new DiscretePathEffect(20,5);
        dash = new DashPathEffect(new float[]{20,10,5,10},0);
        Path dashPath = new Path();
        dashPath.lineTo(50,0);
        dashPath.lineTo(0,50);
        dashPath.lineTo(100,50);
        pathDash = new PathDashPathEffect(dashPath,40,0, PathDashPathEffect.Style.TRANSLATE);
        sum = new SumPathEffect(dash, discrete);
        compose = new ComposePathEffect(dash, discrete);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect 把所有拐角变成圆角。它的构造方法 CornerPathEffect(float radius) 的参数 radius 是圆角的半径。
        paint.setPathEffect(corner);
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect 把线条进行随机的偏离，让轮廓变得乱七八糟。乱七八糟的方式和程度由参数决定。
        paint.setPathEffect(discrete);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect 使用虚线来绘制线条。
        /**
         * 它的构造方法 DashPathEffect(float[] intervals, float phase) 中，
         * 第一个参数 intervals 是一个数组，它指定了虚线的格式：数组中元素必须为偶数（最少是 2 个），
         * 按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，
         * 例如上面代码中的 20, 5, 10, 5 就表示虚线是按照「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；
         * 第二个参数 phase 是虚线的偏移量。
         */
        paint.setPathEffect(dash);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect 它是使用一个 Path 来绘制「虚线」
        /**
         * 它的构造方法 PathDashPathEffect(Path shape, float advance, float phase, PathDashPathEffect.Style style) 中，
         * shape 参数是用来绘制的 Path ； advance 是两个相邻的 shape 段之间的间隔，不过注意，这个间隔是两个 shape 段的起点的间隔，
         * 而不是前一个的终点和后一个的起点的距离； phase 和 DashPathEffect 中一样，是虚线的偏移；最后一个参数 style，
         *  是用来指定拐弯改变的时候 shape 的转换方式。style 的类型为 PathDashPathEffect.Style ，是一个 enum ，具体有三个值：

         TRANSLATE：位移
         ROTATE：旋转
         MORPH：变体
         */
        paint.setPathEffect(pathDash);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect 它的行为特别简单，就是分别按照两种 PathEffect 分别对目标进行绘制。
        paint.setPathEffect(sum);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect 这也是一个组合效果类的 PathEffect 。
        // 不过它是先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect。
        paint.setPathEffect(compose);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
