package cz.tchuba.robotremote;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	TextView textResponse;
	 EditText editTextAddress, editTextPort; 
	 Button buttonConnect, buttonClear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editTextAddress = (EditText)findViewById(R.id.address);
		  editTextPort = (EditText)findViewById(R.id.port);
		  buttonConnect = (Button)findViewById(R.id.connect);
		  buttonClear = (Button)findViewById(R.id.clear);
		  textResponse = (TextView)findViewById(R.id.response);
		  
		  buttonConnect.setOnClickListener(new ConnectListener(this));
		  
		  buttonClear.setOnClickListener(new OnClickListener(){

		   @Override
		   public void onClick(View v) {
		    textResponse.setText("");
		   }});
		  
//		  try {
//			Thread.sleep(2000);
//			startControler();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		  
	}

			 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startControler() {
		Intent intent = new Intent (this, ControlActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public String getAddress() {		
		return editTextAddress.getText().toString();
	}

	public int getPort() {		
		return Integer.parseInt(editTextPort.getText().toString());
	}
}
