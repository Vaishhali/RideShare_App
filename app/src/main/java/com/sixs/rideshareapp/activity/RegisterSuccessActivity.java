package com.sixs.rideshareapp.activity;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.sixs.rideshareapp.MainActivity;
        import com.sixs.rideshareapp.R;

public class RegisterSuccessActivity extends AppCompatActivity {

    TextView tv_confirm, tv_cancel, tv_error_message;
    boolean isSuccess;
    LinearLayout layoutSuccess, layoutError;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success_view);

        isSuccess = getIntent().getBooleanExtra("isSuccess", false);
        msg = getIntent().getStringExtra("msg");

        layoutSuccess = (LinearLayout) findViewById(R.id.layoutSuccess);
        layoutError = (LinearLayout) findViewById(R.id.layoutError);

        tv_error_message = (TextView) findViewById(R.id.tv_error_message);

        if(isSuccess) {
            layoutSuccess.setVisibility(View.VISIBLE);
            layoutError.setVisibility(View.GONE);
        }
        else {
            layoutSuccess.setVisibility(View.GONE);
            layoutError.setVisibility(View.VISIBLE);
            tv_error_message.setText(msg.toString().trim());
        }

        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterSuccessActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                RegisterSuccessActivity.this.startActivity(intent);
                RegisterSuccessActivity.this.overridePendingTransition(R.anim.activity_exit,R.anim.activity_enter);
                RegisterSuccessActivity.this.finish();
            }
        });

        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterSuccessActivity.this, R.style.MyDialogTheme);
                builder.setTitle("Cancel Confirm");
                builder.setMessage("Please contact your HR Manager and register again.");
                builder.setCancelable(false);
                String positiveText = getString(android.R.string.ok);
                builder.setPositiveButton(positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // positive button logic
                                dialog.dismiss();
                                RegisterSuccessActivity.this.finish();
                            }
                        });

                String negativeText = getString(android.R.string.cancel);
                builder.setNegativeButton(negativeText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // negative button logic
                                dialog.dismiss();
                            }
                        });

                AlertDialog dialog = builder.create();
                // display dialog
                dialog.show();

//                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterSuccessActivity.this);
//                builder.setMessage("Rashmi Narola, You need to talk and update the details from your company and register again.")
//                    .setCancelable(false)
//                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
////                                System.exit(0);
////                        android.os.Process.killProcess(android.os.Process.myPid());
//                            RegisterSuccessActivity.this.finish();
////                                moveTaskToBack(true);
//                        }
//                    });
////                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
////                        public void onClick(DialogInterface dialog, int id) {
////                            dialog.cancel();
////                        }
////                    });
//                AlertDialog alert = builder.create();
//                alert.show();
//
                Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                pbutton.setTextColor(getResources().getColor(R.color.blue_light));

                Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setTextColor(getResources().getColor(R.color.black_light));
//
//                RegisterSuccessActivity.this.finish();
//                Utils.killApplication(RegisterSuccessActivity.this);
            }
        });
    }
}
