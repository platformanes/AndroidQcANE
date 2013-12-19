package com.qianchiAne.qcane;

import org.json.JSONException;
import org.json.JSONObject;

import com.qianchi.sdk.login.base.QCListener;
import com.qianchi.sdk.login.constant.Passport;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class test extends Activity {
public static final String action1 = "com.qianchiAne.qcane.test";
private LinearLayout layout;
private EditText inputEdit;
private Button submit;
@Override
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
    layout = new LinearLayout(this);
    inputEdit = new EditText(this);
    submit = new Button(this);
    submit.setText("return");
    layout.addView(inputEdit);
    layout.addView(submit);
    this.setContentView(layout);
    
   

}
}
