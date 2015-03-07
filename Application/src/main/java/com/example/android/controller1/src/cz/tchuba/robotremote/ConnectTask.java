package cz.tchuba.robotremote;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class ConnectTask extends AsyncTask<Void, Void, Void> {
	  
	  String dstAddress;
	  int dstPort;
	  String response = "";
	  MainActivity activity;
	  
	  ConnectTask(String addr, int port, MainActivity activity){
	   dstAddress = addr;
	   dstPort = port;
	   this.activity = activity;
	  }

	  @Override
	  protected Void doInBackground(Void... arg0) {
	   
	   Socket socket = null;
	   
	   try {
	    socket = new Socket(dstAddress, dstPort);//192.168.43.227  8080
	    ConnectionManager.getInstance().socket = socket;
	    
//	    ByteArrayOutputStream byteArrayOutputStream = 
//	                  new ByteArrayOutputStream(1024);
//	    byte[] buffer = new byte[1024];
//	    
//	    int bytesRead;
//	    InputStream inputStream = socket.getInputStream();
	    
	    /*InputStream socketReader = socket.getInputStream();
		OutputStream socketWriter = socket.getOutputStream();
		
		//socketWriter.write("Hello robot!".getBytes());
		byte [] buffer = new byte[1024];
		socketReader.read(buffer);
		response = new String(buffer);*/
	    /*
	     * notice:
	     * inputStream.read() will block if no data return
	     */
//	             while ((bytesRead = inputStream.read(buffer)) != -1){
//	                 byteArrayOutputStream.write(buffer, 0, bytesRead);
//	                 response += byteArrayOutputStream.toString("UTF-8");
//	             }

	   } catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    //response = "UnknownHostException: " + e.toString();
	   } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    //response = "IOException: " + e.toString();
	   }
	   return null;
	  }

	  @Override
	  protected void onPostExecute(Void result) {
	   //textResponse.setText(response);
		  if (ConnectionManager.getInstance().socket!=null){
			  activity.startControler();
		  }else{
			  Context context = activity.getApplicationContext();
			  CharSequence text = "Connection error";
			  int duration = Toast.LENGTH_SHORT;
			  
			  Toast toast = Toast.makeText(context, text, duration);
			  toast.show();
		  }
	   
	   super.onPostExecute(result);
	  }
	  
	 }