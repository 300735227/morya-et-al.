package com.example.ezhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReceptionistAccount extends Activity {
	
	private static final String userType = "Receptionist";
	
	private EditText etUserName, etPassword, etConfirmPassword;
	private Button btnCreateAccount;
	private String userName, password, confirmPassword;
	
	//Database connection
	final DatabaseHandler db = new DatabaseHandler(this);
	private int receptionistUserId; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_account);
		
		etUserName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
		btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
		
		btnCreateAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				userName = etUserName.getText().toString();
				password = etPassword.getText().toString();
				confirmPassword = etConfirmPassword.getText().toString();
				
				if(password.equals(confirmPassword)){
					db.addUser(new UserLogin(userType, userName, MainActivity.md5(password)));
					receptionistUserId = db.getUserId(userType, userName,MainActivity.md5(password) );
					Intent i = new Intent(getBaseContext(), AddReceptionist.class);
					i.putExtra("ReceptionistUserId", receptionistUserId);
					startActivity(i);
					
				} else {
					Toast.makeText(getBaseContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
					etPassword.setText("");
					etConfirmPassword.setText("");
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist_account, menu);
		return true;
	}

	
}
