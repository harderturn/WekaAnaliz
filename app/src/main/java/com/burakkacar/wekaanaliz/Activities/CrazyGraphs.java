package com.burakkacar.wekaanaliz.Activities;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.burakkacar.wekaanaliz.R;
import com.burakkacar.wekaanaliz.TopSecretClass.TestClass;

import java.util.ArrayList;

import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

/**
 * Created by burakkacar on 9.05.2017.
 */

public class CrazyGraphs extends AppCompatActivity
{

    TestClass testClass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafik_layout);

        testClass = new TestClass(getApplicationContext());
        new myTask().execute();

    }


    public void grafikCiz(float neg, float pos, float nm)
    {

        float toplam,negOran,posOran,nmOran;
        toplam = neg + pos + nm;

        // Oran hesaplanmasi..
        negOran = (neg / toplam) * 100;
        posOran = (pos / toplam) * 100;
        nmOran = (nm / toplam) * 100;


        // PieChart.. - > https://github.com/HackPlan/AndroidCharts
        PieView pieView = (PieView)findViewById(R.id.pie_view);
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();

        //Data doldurulmasi
        pieHelperArrayList.add(new PieHelper(posOran, Color.BLUE));
        pieHelperArrayList.add(new PieHelper(nmOran, Color.YELLOW));
        pieHelperArrayList.add(new PieHelper(negOran,Color.RED));


        pieView.setDate(pieHelperArrayList);
        //pieView.selectedPie(2); //optional
        //pieView.setOnPieClickListener(listener) //optional
        pieView.showPercentLabel(true); //optional
    }


    public class myTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params)
        {

            try
            {
                testClass.NegPosTestEt("testNegPos.arff",false);

            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.i("weka","hata");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            grafikCiz(testClass.getNeg(),testClass.getPos(),testClass.getNm());
            Toast.makeText(CrazyGraphs.this,"Neg "+testClass.getNeg()+"\nPos "+testClass.getPos()+"\nNm "+testClass.getNm(),Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
    }
}
