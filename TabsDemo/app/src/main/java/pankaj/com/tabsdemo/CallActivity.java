package pankaj.com.tabsdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by pankaj on 2/21/2015.
 */
public class CallActivity extends Activity implements View.OnClickListener {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnstr, btnhash, btncall, btndelete;
    private EditText txtNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_layout);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        btnstr = (Button) findViewById(R.id.btnstar);
        btnhash = (Button) findViewById(R.id.btnhash);
        btndelete = (Button) findViewById(R.id.btndelete);
        btncall = (Button) findViewById(R.id.btncall);
        txtNumber = (EditText) findViewById(R.id.txtNumber);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        hideSoftInput();

        txtNumber.setFocusableInTouchMode(false);

        final GestureDetector.OnDoubleTapListener gestureDetector = new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                //Toast.makeText(getBaseContext(), "Single Tap", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                //Toast.makeText(getBaseContext(), "Double Tap", Toast.LENGTH_SHORT).show();
                hideSoftInput();
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                Toast.makeText(getBaseContext(), "Double Tap Event", Toast.LENGTH_SHORT).show();
                return false;
            }
        };

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setFocusableInTouchMode(true);
                v.requestFocusFromTouch();
                hideSoftInput();
            }
        };

        View.OnTouchListener doubltch = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onDoubleTap(event);
            }
        };

        View.OnFocusChangeListener focus = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    v.setFocusableInTouchMode(true);
                    //hideSoftInput();
                }
            }
        };

        txtNumber.setOnClickListener(click);
        txtNumber.setOnTouchListener(doubltch);
        txtNumber.setOnFocusChangeListener(focus);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnstr.setOnClickListener(this);
        btnhash.setOnClickListener(this);
        btncall.setOnClickListener(this);
        btndelete.setOnClickListener(this);
        btncall.setOnClickListener(this);

    }

    private void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txtNumber.getWindowToken(), 0);

    }

    @Override
    public void onClick(View v) {
        int strt = txtNumber.getSelectionStart();
        switch (v.getId()) {
            case R.id.btn1:
                txtNumber.getText().insert(strt, "1");
                break;
            case R.id.btn2:
                txtNumber.getText().insert(strt, "2");
                break;
            case R.id.btn3:
                txtNumber.getText().insert(strt, "3");
                break;
            case R.id.btn4:
                txtNumber.getText().insert(strt, "4");
                break;
            case R.id.btn5:
                txtNumber.getText().insert(strt, "5");
                break;
            case R.id.btn6:
                txtNumber.getText().insert(strt, "6");
                break;
            case R.id.btn7:
                txtNumber.getText().insert(strt, "7");
                break;
            case R.id.btn8:
                txtNumber.getText().insert(strt, "8");
                break;
            case R.id.btn9:
                txtNumber.getText().insert(strt, "9");
                break;
            case R.id.btn0:
                txtNumber.getText().insert(strt, "0");
                break;
            case R.id.btnstar:
                txtNumber.getText().insert(strt, "*");
                break;
            case R.id.btnhash:
                txtNumber.getText().insert(strt, "#");
                break;
            case R.id.btndelete:
                if (strt > 0) {
                    txtNumber.getText().delete(strt - 1, strt);
                }
                break;
            case R.id.btncall:
                Intent call1=new Intent();
                call1.setAction(android.content.Intent.ACTION_CALL).setData(Uri.parse("tel:" + txtNumber.getText()));
                startActivity(call1);
                Toast.makeText(this, "Code is not setted for Call button", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
