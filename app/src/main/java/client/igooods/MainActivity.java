package client.igooods;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.crashlytics.android.Crashlytics;

import client.igooods.fragment.DetailsAmountFragment;
import client.igooods.fragment.DetailsFragment;
import client.igooods.fragment.DetailsNoOrderFragment;
import client.igooods.fragment.MapFragment;
import client.igooods.fragment.ProfileFragment;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    private Dialog mNewOrderDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Crashlytics.start(this);

        setContentView(R.layout.activity_main);
        setupTabs();
        initDlg();
    }

    private void initDlg() {
        mNewOrderDialog = new Dialog(this, R.style.DialogTheme);
        mNewOrderDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mNewOrderDialog.setContentView(R.layout.dialog_new_order);
        mNewOrderDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ((Button) mNewOrderDialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewOrderDialog.dismiss();
            }
        });
    }

    private void setupTabs() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

//        actionBar.setLogo(R.drawable.logo_small);
//        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(R.layout.actionbar);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        ActionBar.Tab tab1 = actionBar
                .newTab()
                .setText("Детали");
//                .setTabListener(
//                        new FragmentTabListener<FirstFragment>(R.id.flContainer, this, "first",
//                                FirstFragment.class));
        tab1.setTabListener(this);
        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);

        ActionBar.Tab tab2 = actionBar
                .newTab()
                .setText("Карта");
        tab2.setTabListener(this);
//                .setTabListener(
//                        new FragmentTabListener<SecondFragment>(R.id.flContainer, this, "second",
//                                SecondFragment.class));

        actionBar.addTab(tab2);

        ActionBar.Tab tab3 = actionBar
                .newTab()
                .setText("Профиль");
        tab3.setTabListener(this);
//                .setTabListener(
//                        new FragmentTabListener<SecondFragment>(R.id.flContainer, this, "second",
//                                SecondFragment.class));

        actionBar.addTab(tab3);

//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffffff")));
//
//        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff8DC63F")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(tab.getPosition()==0){
            Fragment fragment = new DetailsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
        }
        if(tab.getPosition()==1){
            Fragment fragment = new MapFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
        }
        if(tab.getPosition()==2){
            Fragment fragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public void showAmountFragment(){
        Fragment fragment = new DetailsAmountFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();
    }
    public void showNoOrderFragment(){
        Fragment fragment = new DetailsNoOrderFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 777) {
//            showNoOrderFragment();
        }
    }
}
