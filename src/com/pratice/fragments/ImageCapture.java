package com.pratice.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.techathonuserexperience.R;
import com.pratice.utils.ImageParser;
import com.pratice.utils.ParsingListener;

public class ImageCapture extends Fragment implements OnClickListener, ParsingListener {

	private static final int CAMERA_REQUEST = 1001;
	private static final int GALLERY_REQUEST = 1002;
	private static final int REFRESH_LIST = 1003;
	
	Button mCameraButton = null;
	Button mGalleryButton = null;
	ListView mListView = null;
	
	ArrayList<String> mListItmes = null;
	private Button mAcceptButton = null;
	private Button mCancelButton = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.image_capture, container, false);
		
		mListView = (ListView) rootView.findViewById(R.id.capture_list);
		mCameraButton = (Button) rootView.findViewById(R.id.camera_button);
		mGalleryButton = (Button) rootView.findViewById(R.id.gallery_button);
		mAcceptButton = (Button) rootView.findViewById(R.id.accept);
		mCancelButton = (Button) rootView.findViewById(R.id.cancel);
	
		mCameraButton.setOnClickListener(this);
		mGalleryButton.setOnClickListener(this);
		mAcceptButton.setOnClickListener(this);
		mCancelButton.setOnClickListener(this);
		
		mListItmes = new ArrayList<String>();
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()) {
		
		case R.id.camera_button:
			
			  Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
              startActivityForResult(cameraIntent, CAMERA_REQUEST); 
              
			break;
			
		case R.id.gallery_button:
			
			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("content://media/internal/images/media"));
			startActivityForResult(intent, GALLERY_REQUEST);

			break;
			
		case R.id.cancel:

			FragmentManager fragmentManager = getFragmentManager();

			fragmentManager.beginTransaction()
					.replace(R.id.container, HomeFragment.newInstance(1))
					.commit();
				 
			break;

		case R.id.accept:

			if(mListItmes == null || mListItmes.size() <=0 ){
				
				Toast.makeText(getActivity(), "NO image selected", Toast.LENGTH_LONG).show();
				return;
			}
			else {
			
				FinalScreen imageCaptureFg = new FinalScreen();
				fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.container, imageCaptureFg).commit();
				
			}
			
			break;
		}
	}
	
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {  
		 
	        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {  
	            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
	            //imageView.setImageBitmap(photo);
	            
	            mListItmes.add("Added itme to the list, Camera");
	            refreshList();
	        }  
	        else if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {  
	            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
	            //imageView.setImageBitmap(photo);
	            mListItmes.add("Added itme to the list, Gallery");
	            refreshList();
	        }  
	    }

	private void refreshList() {
		
		Message msg = new Message();
		msg.what = REFRESH_LIST;
		mUiUpdateHandler.sendMessage(msg );
		
		Toast.makeText(getActivity(), "Parsing the IMAGES to fetch information", Toast.LENGTH_LONG).show();
		ImageParser.getInstance().startFetchingImageInfo(this);
	} 
	
	private final Handler mUiUpdateHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {

			case REFRESH_LIST:
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						getActivity(), android.R.layout.simple_list_item_1,
						mListItmes);

				// Assign adapter to ListView
				mListView.setAdapter(adapter);
			            
				break;
			}
		}
	};

	@Override
	public void onCompletion(boolean success) {
		// TODO Auto-generated method stub
		
	}
}