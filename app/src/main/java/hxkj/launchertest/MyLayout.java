package hxkj.launchertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by tc on 2016/8/3.
 */
public class MyLayout extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoLayoutConifg.getInstance().useDeviceSize();
        setContentView(R.layout.item_);
        AutoLinearLayout autoLinearLayout = (AutoLinearLayout) findViewById(R.id.bg);
        autoLinearLayout.addView(getLongView());
//        autoLinearLayout.addView(getLin());
        autoLinearLayout.addView(getShortView());
    }

    private View getLongView() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_3, null);
        return view;
    }
    private View getLin() {
        View view = LayoutInflater.from(this).inflate(R.layout.lin, null);
        return view;
    }

    private View getShortView() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_4, null);
        return view;
    }
}
