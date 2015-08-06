package client.igooods;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import client.igooods.model.ServerResponseStatus;
import client.igooods.model.User;
import client.igooods.task.ApiClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ApiClient.OnSignin, ApiClient.OnGetOrders {

    private EditText mLogin, mPassword;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Crashlytics.start(this);

        setContentView(R.layout.activity_login);

        initView();
        initDlg();

        User user = new User();
        user.setId(6);
        user.setLogin("");
        user.setPassword("");
        new ApiClient(this).getOrders(this, user);
    }


    private void initDlg(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("Пожалуйста подождите...");
    }

    private void initView(){
        ((Button)findViewById(R.id.signin)).setOnClickListener(this);
        mLogin = (EditText)findViewById(R.id.login);
        mPassword = (EditText)findViewById(R.id.password);
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
    public void onClick(View v) {
        if(v.getId()==R.id.signin){
            String login = mLogin.getText().toString();
            String password = mPassword.getText().toString();
            if(login.trim().equals("")||login.trim().equals("")){
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_LONG).show();
            }
            else{
                signinRequest(login, password);
            }
        }
    }

    private void signinRequest(String login, String password){
        mProgressDialog.show();
        new ApiClient(this).singin(this, login, password);
    }

    @Override
    public void onSignin(ServerResponseStatus responseStatus, boolean result) {
        mProgressDialog.dismiss();
        if(responseStatus==ServerResponseStatus.OK){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Не удалось авторизоваться",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onGetOrders(ServerResponseStatus responseStatus, boolean result) {

    }
}

