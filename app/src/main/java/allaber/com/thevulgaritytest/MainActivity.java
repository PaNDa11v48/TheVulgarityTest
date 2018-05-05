package allaber.com.thevulgaritytest;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements Fragment_1.One, Fragment_2.One, Fragment_3.One {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment_1 fragment1 = new Fragment_1();
        ft.add(R.id.container, fragment1, "fragment1");
        ft.commit();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LOW_PROFILE
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

    @Override
    public void onButtonSelected(int fragIndex) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment_1 fragment1 = new Fragment_1();
        Fragment_2 fragment2 = new Fragment_2();
        Fragment_3 fragment3 = new Fragment_3();
        switch (fragIndex) {
            case 1:
                ft.replace(R.id.container, fragment2, "fragment2");
                break;
            case 2:
                ft.replace(R.id.container, fragment3, "fragment3");
                break;
            case 3:
                ft.replace(R.id.container, fragment1, "fragment1");
                break;
        }
        ft.commit();
    }
}

















































