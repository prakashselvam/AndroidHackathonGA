package com.global.analytics.firstsampleapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Prakash on 17/10/15.
 */
public class PhaseFiveFragment extends Fragment {

    int mCurrentPage;
    View v;
    private PieChart mChart;
    protected String[] mParties = new String[] {
            "Rent", "Loans", "Bills", "Transaport", "Food", "Others",
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Getting the arguments to the Bundle object */
        Bundle data = getArguments();

        /** Getting integer data of the key current_page from the bundle */
        mCurrentPage = data.getInt("current_page", 0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.content_phase_five, container,false);
//        List<PacpieSlice> slices =new ArrayList<>();

//        PacpieSlice slice1 = new PacpieSlice();
//        slice1.count = 20;
//        slice1.color = Color.parseColor("#7B1FA2");
//        slices.add(slice1);
//
//        PacpieSlice slice2 = new PacpieSlice();
//        slice2.count = 30;
//        slice2.color = Color.parseColor("#7C4DFF");
//        slices.add(slice2);
//
//        PacpieSlice slice3 = new PacpieSlice();
//        slice3.count = 10;
//        slice3.color = Color.parseColor("#E91E63");
//        slices.add(slice3);
//
//        PacpieSlice slice4 = new PacpieSlice();
//        slice4.count = 10;
//        slice4.color = Color.parseColor("#7C4DFF");
//        slices.add(slice4);
//
//        PacpieSlice slice5 = new PacpieSlice();
//        slice5.count = 5;
//        slice5.color = Color.parseColor("#FF5722");
//        slices.add(slice5);
//
//        PacpieSlice slice6 = new PacpieSlice();
//        slice6.count = 5;
//        slice6.color = Color.parseColor("#8BC34A");
//        slices.add(slice6);
//
//
//        Pacpie pacpie = (Pacpie)v.findViewById(R.id.pacpieChart);
//        pacpie.setValues(slices);
        mChart = (PieChart) v.findViewById(R.id.chart);
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);


        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        setData(5, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);


        NiceSpinner Rent = (NiceSpinner) v.findViewById(R.id.Rent);
        List<String> dataset = new LinkedList<>(Arrays.asList("Rent", "£ 1-50", "£ 51-100",
                "£ 101-150", "£ 151-200", "£ 201-250", "£ 251-300", "£ 301-350", "£ 351-400",
                "£ 401-450", "£ 451-500"));
        Rent.attachDataSource(dataset);

        NiceSpinner Loans = (NiceSpinner) v.findViewById(R.id.Loans);
        List<String> dataset1 = new LinkedList<>(Arrays.asList("Loans", "£ 1-50", "£ 51-100",
                "£ 101-150", "£ 151-200", "£ 201-250", "£ 251-300", "£ 301-350", "£ 351-400",
                "£ 401-450", "£ 451-500"));
        Loans.attachDataSource(dataset1);

        NiceSpinner Bills = (NiceSpinner) v.findViewById(R.id.Bills);
        List<String> dataset3 = new LinkedList<>(Arrays.asList("Bills", "£ 1-50", "£ 51-100",
                "£ 101-150", "£ 151-200", "£ 201-250", "£ 251-300", "£ 301-350", "£ 351-400",
                "£ 401-450", "£ 451-500"));
        Bills.attachDataSource(dataset3);

        NiceSpinner Transportation = (NiceSpinner) v.findViewById(R.id.Transportation);
        List<String> dataset4 = new LinkedList<>(Arrays.asList("Transport", "£ 1-50", "£ 51-100",
                "£ 101-150", "£ 151-200", "£ 201-250", "£ 251-300", "£ 301-350", "£ 351-400",
                "£ 401-450", "£ 451-500"));
        Transportation.attachDataSource(dataset4);

        NiceSpinner Food = (NiceSpinner) v.findViewById(R.id.Food);
        List<String> dataset5 = new LinkedList<>(Arrays.asList("Food", "£ 1-50", "£ 51-100",
                "£ 101-150", "£ 151-200", "£ 201-250", "£ 251-300", "£ 301-350", "£ 351-400",
                "£ 401-450", "£ 451-500"));
        Food.attachDataSource(dataset5);

        NiceSpinner Other = (NiceSpinner) v.findViewById(R.id.Other);
        List<String> dataset6 = new LinkedList<>(Arrays.asList("Others", "£ 1-50", "£ 51-100",
                "£ 101-150", "£ 151-200", "£ 201-250", "£ 251-300", "£ 301-350", "£ 351-400",
                "£ 401-450", "£ 451-500"));
        Other.attachDataSource(dataset6);

        if (mCurrentPage == 1) {

        }else if (mCurrentPage == 2) {

        }else if (mCurrentPage == 3) {

        }
        return v;
    }
    private void setData(int count, float range) {

        float mult = range;

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < count + 1; i++) {
            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count + 1; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, "Election Results");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.DKGRAY);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        unbindDrawables(v);
        System.gc();
    }
    private void unbindDrawables(View view)
    {
        try {
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
                }
                ((ViewGroup) view).removeAllViews();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}