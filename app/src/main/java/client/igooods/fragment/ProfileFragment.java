package client.igooods.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import client.igooods.R;
import client.igooods.util.Session;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private Dialog mStartDialog, mStopDialog;
    private Button mStart, mStop;
    private TextView mAmount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int SomeInt = getArguments().getInt("someInt", 0);
//        String someTitle = getArguments().getString("someTitle", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initDlg();
        initView(view);
        return view;
    }

    private void initView(View view){
        mAmount = (TextView)view.findViewById(R.id.amount);
        mStart = (Button)view.findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mStop = (Button)view.findViewById(R.id.stop);
        mStop.setOnClickListener(this);
    }

    private void initDlg() {
        mStartDialog = new Dialog(getActivity(), R.style.DialogTheme);
        mStartDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mStartDialog.setContentView(R.layout.dialog_start);
        mStartDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//		mFinishDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ((Button) mStartDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartDialog.dismiss();
            }
        });
        ((Button) mStartDialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = ((EditText) mStartDialog.findViewById(R.id.amount)).getText().toString();
                if (amount.trim().equals("")) {
                    Toast.makeText(getActivity(), "Введите сумму", Toast.LENGTH_LONG).show();
                } else {
                    mStartDialog.dismiss();
                    Session.state = true;
                    mAmount.setText(amount);
                    mStart.setVisibility(View.GONE);
                    mStop.setVisibility(View.VISIBLE);
                }
            }
        });

        mStopDialog = new Dialog(getActivity(), R.style.DialogTheme);
        mStopDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mStopDialog.setContentView(R.layout.dialog_stop);
        mStopDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//		mFinishDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ((Button) mStopDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStopDialog.dismiss();
            }
        });
        ((Button) mStopDialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = ((EditText) mStopDialog.findViewById(R.id.amount)).getText().toString();
                if (amount.trim().equals("")) {
                    Toast.makeText(getActivity(), "Введите сумму", Toast.LENGTH_LONG).show();
                } else {
                    mStopDialog.dismiss();
                    Session.state = false;
                    mStart.setVisibility(View.VISIBLE);
                    mStop.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.start){
            mStartDialog.show();
        }
        if(v.getId()==R.id.stop){
            mStopDialog.show();
        }
    }
}