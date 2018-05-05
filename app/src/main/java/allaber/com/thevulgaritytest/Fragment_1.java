package allaber.com.thevulgaritytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Fragment_1 extends Fragment implements View.OnClickListener {
    Animation anim;
    ImageView imagePlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);

        imagePlay = rootView.findViewById(R.id.imagePlay);
        imagePlay.setOnClickListener(this);

        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.pulse);
        imagePlay.startAnimation(anim);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imagePlay:
                One listener1 = (One) getActivity();
                listener1.onButtonSelected(1);
                break;
        }
    }

    public interface One {
        void onButtonSelected(int fragIndex);
    }
}









