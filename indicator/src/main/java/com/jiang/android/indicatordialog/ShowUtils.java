package com.jiang.android.indicatordialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class ShowUtils {


    public ShowUtils setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public ShowUtils setmLists(String[] mLists) {
        this.mLists = mLists;
        return this;
    }

    public ShowUtils setClickView(View clickView) {
        this.clickView = clickView;
        return this;
    }

    public ShowUtils setIndicatorFloat(float indicatorFloat) {
        this.indicatorFloat = indicatorFloat;
        return this;
    }

    public ShowUtils setGravityCenter(int gravityCenter) {
        this.gravityCenter = gravityCenter;
        return this;
    }

    private Activity activity;
    private String[] mLists;
    private View clickView;
    private float indicatorFloat = 0.5f;

    public ShowUtils setWindowOffset(int windowOffsetX,int windowOffsetY) {
        this.windowOffsetX = windowOffsetX;
        this.windowOffsetY = windowOffsetY;
        return this;
    }

    public ShowUtils setWidth(int dpValue) {
        this.width = dpValue;
        return this;
    }

    private int width=400;

    private int windowOffsetX=5;
    private int windowOffsetY=5;
    private int gravityCenter = IndicatorBuilder.GRAVITY_RIGHT;


    public void showTopDialog(final OnItemSelectedListener onItemSelectedListener) {
        if (activity==null||clickView==null||mLists==null||mLists.length<1){
            return;
        }

        Resources resources = activity.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        IndicatorDialog dialog = new IndicatorBuilder(activity)
                .width(width)
                .height((int) (height * 0.5))
                .height(-1)
                .ArrowDirection(IndicatorBuilder.TOP)
                .bgColor(Color.DKGRAY)
                .gravity(gravityCenter)
                .dimEnabled(true)
                .ArrowRectage(indicatorFloat)
                .radius(18)
                .layoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))
                .adapter(new BaseAdapter() {
                    @Override
                    public void onBindView(BaseViewHolder holder, int position) {
                        TextView tv = holder.getView(R.id.item_add);
                        tv.setText(mLists[position]);
                        if (position == mLists.length - 1) {
                            holder.setVisibility(R.id.item_line, BaseViewHolder.GONE);
                        } else {
                            holder.setVisibility(R.id.item_line, BaseViewHolder.VISIBLE);

                        }
                    }

                    @Override
                    public int getLayoutID(int position) {
                        return R.layout.item;
                    }

                    @Override
                    public boolean clickable() {
                        return true;
                    }

                    @Override
                    public void onItemClick(View v, int position) {
                        if (onItemSelectedListener != null) {
                            onItemSelectedListener.onItemSelected(v, position);
                        }
                    }

                    @Override
                    public int getItemCount() {
                        return mLists.length;
                    }
                }).create();

        dialog.setCanceledOnTouchOutside(true);

        dialog.show(clickView, windowOffsetX, windowOffsetY);

    }

    public interface OnItemSelectedListener {
        void onItemSelected(View view, int position);

    }
}
