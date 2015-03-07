package cz.tchuba.robotremote;

import android.view.View;
import android.view.View.OnClickListener;

public class ConnectListener implements OnClickListener{
	
	MainActivity activity;
	
	public ConnectListener(MainActivity activity){
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		ConnectTask clientTask = new ConnectTask(activity.getAddress(), activity.getPort(), activity);
		clientTask.execute();		
	}

}
