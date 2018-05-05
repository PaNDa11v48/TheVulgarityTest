package allaber.com.thevulgaritytest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

public class Fragment_3 extends Fragment implements View.OnClickListener {
    ConstraintLayout view;
    int ans1;
    int ans2;
    double s;
    WaveLoadingView mWaveLoadingView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment3, container, false);

        ImageView imageRefresh = rootView.findViewById(R.id.imageRefresh);
        ImageView imageShare = rootView.findViewById(R.id.imageShare);
        ImageView imageExit = rootView.findViewById(R.id.imageExit);

        imageRefresh.setOnClickListener(this);
        imageShare.setOnClickListener(this);
        imageExit.setOnClickListener(this);

        SharedPreferences sRightAnswers;
        sRightAnswers = getActivity().getSharedPreferences("vRightAnswers", Context.MODE_PRIVATE);
        ans1 = sRightAnswers.getInt("vRightAnswers", Context.MODE_PRIVATE);
        SharedPreferences sCountQuestion;
        sCountQuestion = getActivity().getSharedPreferences("vCountQuestion", Context.MODE_PRIVATE);
        ans2 = sCountQuestion.getInt("vCountQuestion", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor1 = sRightAnswers.edit();
        editor1.putInt("vRightAnswers", 0);
        editor1.apply();

        view = rootView.findViewById(R.id.screen);

        if(!(ans1 == 0)) {
            s = (100 / ans2) * (ans2 - ans1);
            if (ans1 == 21) s = 100;
        }else{
            s = 0;
        }
        mWaveLoadingView = rootView.findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        //mWaveLoadingView.setTopTitle("Top Title");
        mWaveLoadingView.setCenterTitle((int) s + " %");
        //mWaveLoadingView.setCenterTitleColor(Color.GREEN);
        //mWaveLoadingView.setBottomTitleSize(18);
        mWaveLoadingView.setProgressValue((int) s);
        // mWaveLoadingView.setBorderWidth(10);
        mWaveLoadingView.setAmplitudeRatio(35);
        //mWaveLoadingView.setWaveColor(Color.RED);
        //mWaveLoadingView.setBorderColor(Color.RED);
        mWaveLoadingView.setTopTitleStrokeColor(Color.GREEN);
        mWaveLoadingView.setTopTitleStrokeWidth(3);
        mWaveLoadingView.setAnimDuration(3000);
        // mWaveLoadingView.pauseAnimation();
        // mWaveLoadingView.resumeAnimation();
        // mWaveLoadingView.cancelAnimation();
        mWaveLoadingView.startAnimation();

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageExit:
                getActivity().finish();
                break;
            case R.id.imageRefresh:
                One listener = (One) getActivity();
                listener.onButtonSelected(3);
                break;
            case R.id.imageShare:
                String textToShare = "  https://play.google.com/store/apps/details?id=allaber.com.myapplication&hl=ru";
                final Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plan");
                String textToSend = String.valueOf("Мой результат в приложении \"Тест на пошлость\" равен " + (int) s + " %" + textToShare);
                intent.putExtra(Intent.EXTRA_TEXT, textToSend);
                try {
                    startActivity(Intent.createChooser(intent, "Описание действия"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "Some error", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public interface One {
        void onButtonSelected(int fragIndex);
    }
}











