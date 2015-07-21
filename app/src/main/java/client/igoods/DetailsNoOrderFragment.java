package client.igoods;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class DetailsNoOrderFragment extends Fragment implements View.OnClickListener {
    private Dialog mRefundDialog, mPaidDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int SomeInt = getArguments().getInt("someInt", 0);
//        String someTitle = getArguments().getString("someTitle", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_no_order, container, false);
//        initView(view);
//        initDlg();
        return view;
    }

    private void initView(View view){
        view.findViewById(R.id.refund).setOnClickListener(this);
        view.findViewById(R.id.partical_refund).setOnClickListener(this);
        view.findViewById(R.id.paid).setOnClickListener(this);
    }

    private void initDlg() {
        mRefundDialog = new Dialog(getActivity(), R.style.DialogTheme);
        mRefundDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mRefundDialog.setContentView(R.layout.dialog_refund);
        mRefundDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ((Button) mRefundDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefundDialog.dismiss();
            }
        });
        ((Button) mRefundDialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefundDialog.dismiss();
            }
        });

        mPaidDialog = new Dialog(getActivity(), R.style.DialogTheme);
        mPaidDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPaidDialog.setContentView(R.layout.dialog_paid);
        mPaidDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ((Button) mPaidDialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaidDialog.dismiss();
            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.refund){
            mRefundDialog.show();
        }
        if(v.getId()==R.id.partical_refund){
            Intent intent = new Intent(getActivity(),GoodsListActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.paid){
            mPaidDialog.show();
        }
    }
}