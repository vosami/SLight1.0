package com.syncworks.s_light;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.syncworks.s_light.fragment.BleSetFragment;
import com.syncworks.s_light.fragment.BrightFragment;
import com.syncworks.s_light.fragment.EffectFragment;
import com.syncworks.s_light.fragment.LedSelectFragment;
import com.syncworks.s_light.fragment.OnCommFragmentListener;


public class CommActivity extends ActionBarActivity implements OnCommFragmentListener{
    // 블루투스 관련

    private final static int MAX_STEP = 4;
    private static int fragmentStep = 1;

    // Fragment 정의
    private BleSetFragment fragment1st;
    private LedSelectFragment fragment2nd;
    private BrightFragment fragment3rd;
    private EffectFragment fragment4th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm);
        fragmentStep = 1;
        createFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createFragment() {
        String deviceName, deviceAddr;
        deviceName = "";
        deviceAddr = "";
        fragment1st = BleSetFragment.newInstance(deviceName,deviceAddr);
        fragment2nd = LedSelectFragment.newInstance("","");
        fragment3rd = BrightFragment.newInstance("","");
        fragment4th = EffectFragment.newInstance("","");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,fragment1st);
        fragmentTransaction.commit();
    }
    // 상단 타이틀 변경
    private void changeActionBarText(int step) {
        if (step > 0 && step <= MAX_STEP) {
            String titleText[] = getResources().getStringArray(R.array.comm_activity_step);
            setTitle(titleText[step-1]);
            //getActionBar().setTitle(R.string.ble_set_title_current_device);
        }
    }

    private void changeFragment(int stepFragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (stepFragment) {
            case 1:
                fragmentTransaction.replace(R.id.fragment, fragment1st);
                break;
            case 2:
                fragmentTransaction.replace(R.id.fragment, fragment2nd);
                break;
            case 3:
                fragmentTransaction.replace(R.id.fragment, fragment3rd);
                break;
            case 4:
                fragmentTransaction.replace(R.id.fragment, fragment4th);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    private void changeStep() {
        if (fragmentStep < 1 && fragmentStep > 4) {
            fragmentStep = 1;
        }
        fragmentStep ++;
        if (fragmentStep > 4) {
            fragmentStep = 2;
        }
        //TODO 사전 설정
        // 타이틀 바 텍스트 설정
        changeActionBarText(fragmentStep);
        // Fragment 교체
        changeFragment(fragmentStep);
        //TODO 사후 설정
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_comm_next:
                changeStep();
                break;
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
