package allaber.com.thevulgaritytest;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;


public class Fragment_2 extends Fragment implements View.OnClickListener {

    TextView QuestionCount;
    Button Answer1;
    Button Answer2;
    Button Answer3;
    Button Answer4;
    View rootView;
    ImageView QuestionImage;
    int Count = 0;
    int RightAnswers = 0;
    int CountQuestion = 0;
    private SQLiteDatabase mDb;
    private InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment2, container, false);

        Answer1 = rootView.findViewById(R.id.Answer1);
        Answer2 = rootView.findViewById(R.id.Answer2);
        Answer3 = rootView.findViewById(R.id.Answer3);
        Answer4 = rootView.findViewById(R.id.Answer4);
        QuestionImage = rootView.findViewById(R.id.imageView);

        QuestionCount = rootView.findViewById(R.id.QuestionCount);
        Answer1.setOnClickListener(this);
        Answer2.setOnClickListener(this);
        Answer3.setOnClickListener(this);
        Answer4.setOnClickListener(this);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-7228262520699641/1308976467");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("D18B9F8FEDE6CADE3AD410C38C141B3B").build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("D18B9F8FEDE6CADE3AD410C38C141B3B").build());
            }

        });

        DatabaseHelper mDBHelper = new DatabaseHelper(getActivity());
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        mDb = mDBHelper.getWritableDatabase();
        setTextButtonAndTextView(999);
        return rootView;
    }


    @Override
    public void onClick(View view) {
        Count++;
        switch (view.getId()) {
            case R.id.Answer1:
                setTextButtonAndTextView(1);
                break;
            case R.id.Answer2:
                setTextButtonAndTextView(2);
                break;
            case R.id.Answer3:
                setTextButtonAndTextView(3);
                break;
            case R.id.Answer4:
                setTextButtonAndTextView(4);
                break;
        }
        if (mInterstitialAd.isLoaded() && (Count > 4) && ((Count % 2) == 0)) {
            mInterstitialAd.show();
        }
    }
//package allaber.com.youiq;
    public void setTextButtonAndTextView(int answer) {
        Cursor cursor = mDb.rawQuery("SELECT * FROM QandA", null);
        CountQuestion = cursor.getCount();
        if (cursor.getCount() - 1 < Count) {
            SharedPreferences sCountQuestion;
            sCountQuestion = getActivity().getSharedPreferences("vCountQuestion", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor2 = sCountQuestion.edit();
            editor2.putInt("vCountQuestion", CountQuestion);
            editor2.apply();
            One listener = (One) getActivity();
            listener.onButtonSelected(2);
        } else {
            TypedArray imgs = getResources().obtainTypedArray(R.array.imgs);
            imgs.getResourceId(Count, -1);
            QuestionImage.setImageResource(imgs.getResourceId(Count, -1));
            imgs.recycle();

            cursor.moveToPosition(Count);

            QuestionCount.setText("Картинка " + (Count + 1) + " из " + cursor.getCount());

            Answer1.setText(cursor.getString(1));
            Answer2.setText(cursor.getString(2));
            Answer3.setText(cursor.getString(3));
            Answer4.setText(cursor.getString(4));
        }
        cursor.moveToPosition(Count - 1);
        SharedPreferences sRightAnswers;
        sRightAnswers = getActivity().getSharedPreferences("vRightAnswers", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sRightAnswers.edit();
        if (answer != 999) {
            if (answer == Integer.valueOf(cursor.getString(5))) {
                RightAnswers++;
                editor1.putInt("vRightAnswers", RightAnswers);
                editor1.apply();
            }
        }
        cursor.close();
    }

    interface One {
        void onButtonSelected(int fragIndex);
    }
}











