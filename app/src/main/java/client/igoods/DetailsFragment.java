package client.igoods;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class DetailsFragment  extends Fragment implements View.OnClickListener {
    private Dialog mDialDialog, mDeliveredDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int SomeInt = getArguments().getInt("someInt", 0);
//        String someTitle = getArguments().getString("someTitle", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        initView(view);
        initDlg();
        return view;
    }

    private void initView(View view){
        view.findViewById(R.id.dial).setOnClickListener(this);
        view.findViewById(R.id.delivered).setOnClickListener(this);
    }

    private void initDlg() {
        mDialDialog = new Dialog(getActivity(), R.style.DialogTheme);
        mDialDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialDialog.setContentView(R.layout.dialog_dial);
        mDialDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ((Button) mDialDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialDialog.dismiss();
            }
        });
        ((Button) mDialDialog.findViewById(R.id.dial)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialDialog.dismiss();
                String number = "+7977777777777";
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:" + number));
                startActivityForResult(phoneIntent, 999);
            }
        });

        mDeliveredDialog = new Dialog(getActivity(), R.style.DialogTheme);
        mDeliveredDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDeliveredDialog.setContentView(R.layout.dialog_delivered);
        mDeliveredDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ((Button) mDeliveredDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeliveredDialog.dismiss();
            }
        });
        ((Button) mDeliveredDialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeliveredDialog.dismiss();
                ((MainActivity)getActivity()).showAmountFragment();
            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dial){
            mDialDialog.show();
        }
        if(v.getId()==R.id.delivered){
            mDeliveredDialog.show();
        }
    }
}