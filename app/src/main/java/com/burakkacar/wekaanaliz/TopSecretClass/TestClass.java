package com.burakkacar.wekaanaliz.TopSecretClass;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * Created by burakkacar on 5.05.2017.
 */

public class TestClass
{
    String degerlendirme, firma, konu;
    Context mContext;

    // Sayaclar
    int neg,pos,nm;
    int vatan,teknosa,bimeks,ist, mediaMarkt,yokFirma;
    int fiyat,kargo,urun,ssh,personel,magaza,reklam,websitesi,yokKonu;


    // constructor..
    public TestClass(Context context)
    {
        setDegerlendirme("-");
        setKonu("-");
        setFirma("-");
        mContext = context;

    }

    public void NegPosTestEt(String negPostTestArffDosyaAdi,boolean testArffSilinsinmi) throws Exception{
        //NaiveBayesMultinomial
        //Eğitici Data okundu.
        //BufferedReader reader = new BufferedReader(
        //        new FileReader("NegPos.arff"));

        BufferedReader reader = new BufferedReader(dosyadanOku("NegPos.arff"));

        Instances train = new Instances(reader);
        reader.close();

        //Test datası okundu.
        BufferedReader testreader = new BufferedReader(dosyadanOku(negPostTestArffDosyaAdi));
        Instances test = new Instances(testreader);
        testreader.close();

        // setting class attribute for test data
        int lastIndex = train.numAttributes() - 1;
        test.setClassIndex(lastIndex);

        // setting class attribute
        train.setClassIndex(lastIndex);

        StringToWordVector stwv = new StringToWordVector();
        stwv.setInputFormat(train);

        NaiveBayesMultinomial bayes = new NaiveBayesMultinomial();

        // Filtre uygulanıyor
        train = weka.filters.Filter.useFilter(train, stwv);
        test = weka.filters.Filter.useFilter(test, stwv);

        bayes.buildClassifier(train);

        for(int i = 0; i < test.numInstances(); i++) {
            //System.out.println(i);
            double index = bayes.classifyInstance(test.instance(i));
            String className = train.attribute(0).value((int)index);
            //System.out.println("Değerlendirme: "+className);
            setDegerlendirme(className);


            //Sayaclar arttiriliyor
            switch (className)
            {
                case "neg":
                    setNeg(getNeg()+1);
                    break;
                case "pos":
                    setPos(getPos()+1);
                    break;
                case "nm":
                    setNm(getNm()+1);
                    break;
            }


        }

        if (testArffSilinsinmi)
        {
            File file = new File(mContext.getExternalFilesDir(null),negPostTestArffDosyaAdi);
            file.delete();
        }


    }

    public void KonuTestEt(String konuTestArffDosyaAdi,boolean testArffSilinsinmi) throws Exception{
        //NaiveBayes
        //Eğitici Data okundu.
        //BufferedReader reader = new BufferedReader(
        //       new FileReader("yeniKonu.arff"));
        BufferedReader reader = new BufferedReader(dosyadanOku("yeniKonu.arff"));


        Instances train = new Instances(reader);
        reader.close();

        //Test datası okundu.
        BufferedReader testreader = new BufferedReader(dosyadanOku(konuTestArffDosyaAdi));
        Instances test = new Instances(testreader);
        testreader.close();

        // setting class attribute for test data
        int lastIndex = train.numAttributes() - 1;
        test.setClassIndex(lastIndex);

        // setting class attribute
        train.setClassIndex(lastIndex);

        StringToWordVector stwv = new StringToWordVector();
        stwv.setInputFormat(train);

        NaiveBayes bayes = new NaiveBayes();

        // Filtre uygulanıyor
        train = weka.filters.Filter.useFilter(train, stwv);
        test = weka.filters.Filter.useFilter(test, stwv);

        bayes.buildClassifier(train);

        for(int i = 0; i < test.numInstances(); i++) {
            //System.out.println(i);
            double index = bayes.classifyInstance(test.instance(i));
            String className = train.attribute(0).value((int)index);
            //System.out.println("Konu: "+className);
            setKonu(className);

            // Sayaclar arttiriliyor
            switch (className)
            {
                case "fiyat":
                    setFiyat(getFiyat()+1); break;
                case "kargo":
                    setKargo(getKargo()+1); break;
                case "urun":
                    setUrun(getUrun()+1);break;
                case "ssh":
                    setSsh(getSsh()+1); break;
                case "personel":
                    setPersonel(getPersonel()+1); break;
                case "magaza":
                    setMagaza(getMagaza()+1); break;
                case "reklam":
                    setReklam(getReklam()+1); break;
                case "websitesi":
                    setWebsitesi(getWebsitesi()+1); break;
                case "yok":
                    setYokKonu(getYokKonu()+1); break;

            }
        }
        if (testArffSilinsinmi)
        {
            File file = new File(mContext.getExternalFilesDir(null),konuTestArffDosyaAdi);
            file.delete();
        }


    }

    public void FirmaTestEt(String firmaTestArffDosyaAdi,boolean testArffSilinsinmi) throws Exception{
        //BayesNet
        //Eğitici Data okundu.
        //BufferedReader reader = new BufferedReader(
        //        new FileReader("yeniFirma.arff"));
        BufferedReader reader = new BufferedReader(dosyadanOku("yeniFirma.arff"));

        Instances train = new Instances(reader);
        reader.close();

        //Test datası okundu.
        BufferedReader testreader = new BufferedReader(dosyadanOku(firmaTestArffDosyaAdi));
        Instances test = new Instances(testreader);
        testreader.close();

        // setting class attribute for test data
        int lastIndex = train.numAttributes() - 1;
        test.setClassIndex(lastIndex);

        // setting class attribute
        train.setClassIndex(lastIndex);

        StringToWordVector stwv = new StringToWordVector();
        stwv.setInputFormat(train);

        BayesNet bayes = new BayesNet();

        // Filtre uygulanıyor
        train = weka.filters.Filter.useFilter(train, stwv);
        test = weka.filters.Filter.useFilter(test, stwv);

        bayes.buildClassifier(train);

        for(int i = 0; i < test.numInstances(); i++) {
            //System.out.println(i);
            double index = bayes.classifyInstance(test.instance(i));
            String className = train.attribute(0).value((int)index);
            //System.out.println("Firma: "+className);
            setFirma(className);

            // Sayaclar arttiriliyor
            switch (className)
            {
                case "bimeks":
                    setBimeks(getBimeks()+1); break;
                case "istBilisim":
                    setIst(getIst()+1); break;
                case "mediaMarkt":
                    setMediaMarkt(getMediaMarkt()+1);break;
                case "teknosa":
                    setTeknosa(getTeknosa()+1); break;
                case "vatanBilgisayar":
                    setVatan(getVatan()+1); break;
                case "yok":
                    setYokFirma(getYokFirma()+1); break;

            }

        }

        if (testArffSilinsinmi)
        {
            File file = new File(mContext.getExternalFilesDir(null),firmaTestArffDosyaAdi);
            file.delete();
        }

    }

    public void CumleTestEt(String analizEdilecek) throws Exception{
        String cumle = "";
        String negPosTest, firmaTest, konuTest;

        negPosTest = "@relation degerlendirme\n@attribute text  String\n@attribute degerlendirme   {neg,pos,nm}\n@Data\n";

        firmaTest = "@RELATION firma\n@ATTRIBUTE text  String\n@ATTRIBUTE firmaxox   {bimeks,istBilisim,mediaMarkt,teknosa,vatanBilgisayar,yok}\n@Data\n";

        konuTest = "@RELATION konu\n@ATTRIBUTE text  String\n@ATTRIBUTE konuxox   {fiyat,kargo,urun,ssh,personel,magaza,reklam,websitesi,yok}\n@Data\n";
        //System.out.println("Analiz edilecek cümleyi girin: ");

        // Tek ve çift tırnaklar temizleniyor
        analizEdilecek = analizEdilecek.replaceAll("\'", " ");
        analizEdilecek = analizEdilecek.replaceAll("\"", " ");

        negPosTest = negPosTest + "'" + cumle + "', ?\n";
        firmaTest = firmaTest + "'" + cumle + "', ?\n";
        konuTest = konuTest + "'" + cumle + "', ?\n";

        dosyayaYaz(negPosTest,"negPosTest.arff");
        dosyayaYaz(firmaTest,"firmaTest.arff");
        dosyayaYaz(konuTest,"konuTest.arff");

        //Test arffleri silinmeli - boolean deger 1
        NegPosTestEt("negPosTest.arff",true);
        FirmaTestEt("firmaTest.arff",true);
        KonuTestEt("konuTest.arff",true);

    }



    //Append kapali, dosya varsa sonuna eklemez. dosyayaYaz("ornek.txt","hello world");
    public void dosyayaYaz(String veriler, String dosyaTamAdi /* uzanti dahil*/) {
        try {
            // Creates a file in the primary external storage space of the
            // current application.
            // If the file does not exists, it is created.
            File testFile = new File(mContext.getExternalFilesDir(null), dosyaTamAdi);
            if (!testFile.exists())
                testFile.createNewFile();

            // Adds a line to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(testFile, false /*append*/));
            writer.write(veriler);
            writer.close();
            // Refresh the data so it can seen when the device is plugged in a
            // computer. You may have to unplug and replug the device to see the
            // latest changes. This is not necessary if the user should not modify
            // the files.
            MediaScannerConnection.scanFile(mContext,
                    new String[]{testFile.toString()},
                    null,
                    null);
        } catch (IOException e) {
            Log.e("ReadWriteFile", "Unable to write to the "+dosyaTamAdi+" file.");
        }
    }


    public Reader dosyadanOku(String dosyaTamAdi) {

        String textFromFile = "";
        // Gets the file from the primary external storage space of the
        // current application.
        File okunacakDosya = new File(mContext.getExternalFilesDir(null), dosyaTamAdi);
        // Boyle bir dosya varsa
        if (okunacakDosya != null)
        {
            StringBuilder stringBuilder = new StringBuilder();
            // Reads the data from the file
            BufferedReader reader = null;
            try
            {
                reader = new BufferedReader(new FileReader(okunacakDosya));

                /*
                String line;

                while ((line = reader.readLine()) != null) {
                    textFromFile += line.toString();
                    textFromFile += "\n";
                }
                */

                return reader;
            }
            catch (Exception e)
            {
                Log.e("ReadWriteFile", "Unable to read the TestFile.txt file.");
                return null;
            }
        }
        return null;
    }


    public String getDegerlendirme() {
        return degerlendirme;
    }

    public void setDegerlendirme(String degerlendirme) {
        this.degerlendirme = degerlendirme;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public int getNeg() {
        return neg;
    }

    public void setNeg(int neg) {
        this.neg = neg;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getNm() {
        return nm;
    }

    public void setNm(int nm) {
        this.nm = nm;
    }

    public int getVatan() {
        return vatan;
    }

    public void setVatan(int vatan) {
        this.vatan = vatan;
    }

    public int getTeknosa() {
        return teknosa;
    }

    public void setTeknosa(int teknosa) {
        this.teknosa = teknosa;
    }

    public int getBimeks() {
        return bimeks;
    }

    public void setBimeks(int bimeks) {
        this.bimeks = bimeks;
    }

    public int getIst() {
        return ist;
    }

    public void setIst(int ist) {
        this.ist = ist;
    }

    public int getMediaMarkt() {
        return mediaMarkt;
    }

    public void setMediaMarkt(int mediaMarkt) {
        this.mediaMarkt = mediaMarkt;
    }

    public int getYokFirma() {
        return yokFirma;
    }

    public void setYokFirma(int yokFirma) {
        this.yokFirma = yokFirma;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public int getKargo() {
        return kargo;
    }

    public void setKargo(int kargo) {
        this.kargo = kargo;
    }

    public int getUrun() {
        return urun;
    }

    public void setUrun(int urun) {
        this.urun = urun;
    }

    public int getSsh() {
        return ssh;
    }

    public void setSsh(int ssh) {
        this.ssh = ssh;
    }

    public int getPersonel() {
        return personel;
    }

    public void setPersonel(int personel) {
        this.personel = personel;
    }

    public int getMagaza() {
        return magaza;
    }

    public void setMagaza(int magaza) {
        this.magaza = magaza;
    }

    public int getReklam() {
        return reklam;
    }

    public void setReklam(int reklam) {
        this.reklam = reklam;
    }

    public int getWebsitesi() {
        return websitesi;
    }

    public void setWebsitesi(int websitesi) {
        this.websitesi = websitesi;
    }

    public int getYokKonu() {
        return yokKonu;
    }

    public void setYokKonu(int yokKonu) {
        this.yokKonu = yokKonu;
    }
}
