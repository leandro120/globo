package br.com.globo.movies;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext( CalligraphyContextWrapper.wrap(newBase));
    }

    protected void showToastLong(String msgId) {
        Toast.makeText(this, msgId, Toast.LENGTH_LONG).show();
    }

    protected void showToast(int msgId) {
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void openDialog(Context context) {
        progressDialog = new ProgressDialog( context);
        progressDialog.setTitle(getString( R.string.loading));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    protected void closeDialog() {
        progressDialog.dismiss();
    }
}
