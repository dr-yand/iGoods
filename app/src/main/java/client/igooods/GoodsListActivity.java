package client.igooods;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

public class GoodsListActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Dialog mPaidDialog;
    private ListView mList, mList2;
    private Dialog mReasonDialog, mCountDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        initDlg();
        initMenu();
        initView();

        loadList();
    }

    private void initView(){
        findViewById(R.id.paid).setOnClickListener(this);

        mList = (ListView)findViewById(R.id.list);
        mList2 = (ListView)findViewById(R.id.list2);

        mList.setOnItemClickListener(this);
        mList2.setOnItemClickListener(this);
    }

    private void loadList(){
        String[] data = new String[]{"Товар 1","Товар 2","Товар 3","Товар 4","Товар 5","Товар 6",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_goods, R.id.title, data);
        mList.setAdapter(adapter);

        String[] data2 = new String[]{"Товар 1","Товар 2","Товар 3"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.item_goods, R.id.title, data2);
        mList2.setAdapter(adapter2);

        Utility.setListViewHeightBasedOnChildren2(mList);
        Utility.setListViewHeightBasedOnChildren2(mList2);
    }

    static class Utility {
        public static void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }

            int totalHeight = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        }

        public static void setListViewHeightBasedOnChildren2(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }

            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        }
    }

    private void initMenu(){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDlg() {
        mPaidDialog = new Dialog(this, R.style.DialogTheme);
        mPaidDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPaidDialog.setContentView(R.layout.dialog_paid);
        mPaidDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ((Button) mPaidDialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaidDialog.dismiss();
                finish();
            }
        });

        mReasonDialog = new Dialog(this, R.style.DialogTheme);
        mReasonDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mReasonDialog.setContentView(R.layout.dialog_reason);
        mReasonDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ((Button) mReasonDialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReasonDialog.dismiss();
                mCountDialog.show();
            }
        });

        mCountDialog = new Dialog(this, R.style.DialogTheme);
        mCountDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mCountDialog.setContentView(R.layout.dialog_count);
        mCountDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ((Button) mCountDialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.paid){
            mPaidDialog.show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mReasonDialog.show();
    }
}
