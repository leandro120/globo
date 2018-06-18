package br.com.globo.movies;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class BaseFragment extends Fragment {
    private ProgressDialog progressDialog;

    protected void showToastLong(Context context, String msgId) {
        Toast.makeText(context, msgId, Toast.LENGTH_LONG).show();
    }

    protected void showToast(Context context, int msgId) {
        Toast.makeText(context, msgId, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    protected void openDialog(Context context) {
        progressDialog = new ProgressDialog( context );
        progressDialog.setTitle(getString( R.string.loading));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    protected void closeDialog() {
        progressDialog.dismiss();
    }
}
