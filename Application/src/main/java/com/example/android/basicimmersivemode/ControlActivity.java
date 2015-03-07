package cz.tchuba.robotremote;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class ControlActivity extends ActionBarActivity implements
		OnClickListener {

	Button forward, backward, left, right, backLeft, backRight, stop, quit;
	CheckBox distance, line;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);

		forward = (Button) findViewById(R.id.forward);
		backward = (Button) findViewById(R.id.backward);
		left = (Button) findViewById(R.id.left);
		right = (Button) findViewById(R.id.right);
		backLeft = (Button) findViewById(R.id.backLeft);
		backRight = (Button) findViewById(R.id.backRight);
		stop = (Button) findViewById(R.id.stop);
		
		quit = (Button) findViewById(R.id.quit);
		
		forward.setOnClickListener(this);
		backward.setOnClickListener(this);
		left.setOnClickListener(this);
		right.setOnClickListener(this);
		backRight.setOnClickListener(this);
		backLeft.setOnClickListener(this);
		stop.setOnClickListener(this);
		
		quit.setOnClickListener(this);

		distance = (CheckBox) findViewById(R.id.distance);
		line = (CheckBox) findViewById(R.id.line);
		
		distance.setOnClickListener(this);
		line.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.control, menu);
		return true;
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

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.forward: {
			sendMessage(Messages.DRIVE + Messages.TYPE_DELIMITER
					+ Messages.FORWARD + Messages.CONTENT_DELIMITER + 100, false);
			break;
		}
		case R.id.backward: {
			sendMessage(Messages.DRIVE + Messages.TYPE_DELIMITER
					+ Messages.BACKWARDS + Messages.CONTENT_DELIMITER + 100, false);
			break;
		}
		case R.id.left: {
			sendMessage(Messages.DRIVE + Messages.TYPE_DELIMITER
					+ Messages.FORWARD_LEFT + Messages.CONTENT_DELIMITER + 100, false);
			break;
		}
		case R.id.right: {
			sendMessage(Messages.DRIVE + Messages.TYPE_DELIMITER
					+ Messages.FORWARD_RIGHT + Messages.CONTENT_DELIMITER + 100, false);
			break;
		}
		case R.id.backLeft: {
			sendMessage(Messages.DRIVE + Messages.TYPE_DELIMITER
					+ Messages.BACKWARDS_LEFT + Messages.CONTENT_DELIMITER
					+ 100, false);
			break;
		}
		case R.id.backRight: {
			sendMessage(Messages.DRIVE + Messages.TYPE_DELIMITER
					+ Messages.BACKWARDS_RIGHT + Messages.CONTENT_DELIMITER
					+ 100, false);
			break;
		}
		case R.id.stop: {
			sendMessage(Messages.DRIVE + Messages.TYPE_DELIMITER
					+ Messages.STOP + Messages.CONTENT_DELIMITER + 100, false);
			break;
		}
		case R.id.distance: {
			if (distance.isChecked()) {
				sendMessage(Messages.MODE + Messages.TYPE_DELIMITER
						+ Messages.MODE_DISTANCE + Messages.CONTENT_DELIMITER
						+ Messages.ON, false);
			} else {
				sendMessage(Messages.MODE + Messages.TYPE_DELIMITER
						+ Messages.MODE_DISTANCE + Messages.CONTENT_DELIMITER
						+ Messages.OFF, false);
			}
			break;
		}
		case R.id.line: {
			if (line.isChecked()) {
				sendMessage(Messages.MODE + Messages.TYPE_DELIMITER
						+ Messages.MODE_LINE + Messages.CONTENT_DELIMITER
						+ Messages.ON, false);
			} else {
				sendMessage(Messages.MODE + Messages.TYPE_DELIMITER
						+ Messages.MODE_LINE + Messages.CONTENT_DELIMITER
						+ Messages.OFF, false);
			}
			break;
		}
		case R.id.quit:{
			sendMessage(Messages.QUIT + Messages.TYPE_DELIMITER, true);			
		}
		default: {

		}
		}

	}

	@Override
	protected void onPause() {
		//sendMessage(Messages.QUIT + Messages.TYPE_DELIMITER, false);
		super.onPause();
	}
	
	public void sendMessage(String message, boolean quit) {
		new SendingTask(this, quit).execute(message);
	}
	
	public void deleteSelf(){
		try {
			ConnectionManager.getInstance().socket.close();
			ConnectionManager.getInstance().deleteSelf();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finish();
	}
}
