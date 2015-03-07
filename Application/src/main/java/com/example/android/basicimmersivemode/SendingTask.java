package cz.tchuba.robotremote;

import java.io.IOException;
import java.io.OutputStream;

import android.os.AsyncTask;

public class SendingTask extends AsyncTask<String, Void, Void> {
	
	private ControlActivity activity;
	private boolean quit;
	
	public SendingTask (ControlActivity activity, boolean quit){
		this.activity = activity;		
		this.quit = quit;
	}

	@Override
	protected Void doInBackground(String ... params) {
		try {
			OutputStream output = ConnectionManager.getInstance().socket.getOutputStream();
			output.write(params[0].getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			quit = true;
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		if (quit){
			activity.deleteSelf();
		}
		super.onPostExecute(result);
	}

}
