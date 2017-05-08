package com.burakkacar.wekaanaliz;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button analiz;
    String analizEdilecekCumle;
    EditText analizEdilecekCumleEditText;
    Context mContext;
    TestClass testClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        testClass = new TestClass(mContext);
        analiz = (Button) findViewById(R.id.button);
        analizEdilecekCumleEditText = (EditText) findViewById(R.id.editText);


        analiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                analizEdilecekCumle = String.valueOf(analizEdilecekCumleEditText.getText());

                new myTask().execute();

            }
        });

    }

    public class myTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params)
        {

            try
            {
                Log.i("weka","cumletestet calisti");
                testClass.CumleTestEt(analizEdilecekCumle);


            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.i("weka","olmadı");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

            Log.i("weka","sonuç "+ testClass.getDegerlendirme());
            Toast.makeText(MainActivity.this,testClass.getDegerlendirme(),Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
    }

}
