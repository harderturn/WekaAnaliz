package com.burakkacar.wekaanaliz.Activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.burakkacar.wekaanaliz.R;
import com.burakkacar.wekaanaliz.TopSecretClass.TestClass;

public class MainActivity extends AppCompatActivity {

    Button analiz;
    String analizEdilecekCumle;
    EditText analizEdilecekCumleEditText;
    Context mContext;
    TestClass testClass;
    TextView sonuclar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        testClass = new TestClass(mContext);
        analiz = (Button) findViewById(R.id.button);
        analizEdilecekCumleEditText = (EditText) findViewById(R.id.editText);
        sonuclar = (TextView)findViewById(R.id.txtSonuclar);

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

                // NegPosTestEt

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
            sonuclar.setText("Değerlendirme: "+testClass.getDegerlendirme()+"\nFirma: "+testClass.getFirma()+"\n"+"Konu: "+testClass.getKonu());

            Toast.makeText(MainActivity.this,testClass.getDegerlendirme(),Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
    }

}
