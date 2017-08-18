package com.jdyy.cfunding.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.jdyy.cfunding.R;


/**
 * 进度条控件
 * Created by BHKJ on 2016/11/22.
 */

public class ProgressView extends View {

    /**
     * The progress area bar color.
     * 当前进度值文本之前的进度条颜色
     */
    private int mReachedBarColor;

    /**
     * The bar unreached area color.
     * 当前进度值文本之后的进度条颜色
     */
    private int mUnreachedBarColor;

    /**
     * The progress text color.
     * 当前进度值文本的颜色
     */
    private int mTextColor;

    /**
     * The progress text size.
     * 当前进度值文本的字体大小
     */
    private int mTextSize;
    /**
     * 当前进度条的圆角
     */
    private int mRectCorners;
    //默认显示当前进度值文本   0为显示，1为不显示
    private static final int PROGRESS_TEXT_VISIBLE = 0;

    private final int default_progress_text_size = 30;

    private final int default_progress_line_width = 3;

    private final int default_progress_rect_corners = 20;


    private int width,height;

    private int mLineWidth;

    private Paint  mPaint;

    private  int mProgress=100;

    private int mTextWidth,mTextHeight;

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        mReachedBarColor = typedArray.getColor(R.styleable.ProgressView_progress_reached_color, Color.parseColor("#34b1fd"));
        mUnreachedBarColor = typedArray.getColor(R.styleable.ProgressView_progress_unreached_color,Color.parseColor("#dcdce2"));
        mTextColor = typedArray.getColor(R.styleable.ProgressView_progress_text_color,Color.WHITE);
        mTextSize = typedArray.getDimensionPixelOffset(R.styleable.ProgressView_progress_text_size,default_progress_text_size);
        mRectCorners = typedArray.getDimensionPixelOffset(R.styleable.ProgressView_progress_rect_corners,default_progress_rect_corners);
        mLineWidth = typedArray.getDimensionPixelOffset(R.styleable.ProgressView_progress_line_width, default_progress_line_width);
        init();
    }

    public ProgressView(Context context) {
        this(context,null);
    }


    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        String str = "0000";
        mPaint.setTextSize(mTextSize);
        mTextWidth = (int) mPaint.measureText(str);
        mTextHeight = (int) -(mPaint.descent()+mPaint.ascent());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        滑块的可移动距离
        int rectMax = width-mTextWidth-mRectCorners*2;
        int rectWidth = mTextWidth+mRectCorners*2;
        int rectHeight = mTextHeight+mRectCorners;

        mPaint.setColor(mUnreachedBarColor);
        mPaint.setStrokeWidth(mLineWidth);
        canvas.drawLine(0 ,height/2,width,height/2,mPaint);

        mPaint.setColor(mReachedBarColor);
        mPaint.setStrokeWidth(mLineWidth*2);
        canvas.drawLine(0 ,height/2,width*mProgress/100,height/2,mPaint);


        Path path1 =new Path();
        Path path2 =new Path();
        Path path3 =new Path();
        path1.addCircle(rectMax*mProgress/100+(width-rectMax)/2-rectWidth/2+mRectCorners,
                height/2,rectHeight/2 ,Path.Direction.CW);
        path2.addRect(rectMax*mProgress/100+(width-rectMax)/2-rectWidth/2+mRectCorners,height/2-rectHeight/2,
                rectMax*mProgress/100+(width-rectMax)/2+rectWidth/2-mRectCorners,height/2+rectHeight/2,Path.Direction.CW);
        path3.addCircle(rectMax*mProgress/100+(width-rectMax)/2+rectWidth/2-mRectCorners,
                height/2,rectHeight/2 ,Path.Direction.CW);

        canvas.drawPath(path1,mPaint);
        canvas.drawPath(path2,mPaint);
        canvas.drawPath(path3,mPaint);

//        RectF rectF = new RectF();
//        rectF.set(rectMax*mProgress/100+(width-rectMax)/2-rectWidth/2,height/2-rectHeight/2,
//                rectMax*mProgress/100+(width-rectMax)/2+rectWidth/2,height/2+rectHeight/2);
//        canvas.drawRoundRect(rectF,mRectCorners,mRectCorners,mPaint);
        mPaint.setColor(mTextColor);
        String str =null;
        if (mProgress==0){
            str="0";
        }else {
            str=mProgress+"%";
        }
        int textWidth = (int) mPaint.measureText(str);
        canvas.drawText(str,rectMax*mProgress/100+(width-rectMax)/2-textWidth/2,height/2+mTextHeight/2,mPaint);

    }

    public void setProgress(int progress){
        mProgress =progress;
        invalidate();
    }

    /**
     * dp转px
     */
    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    /**
     * sp转px
     */
    public float sp2px(float sp) {
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

}
