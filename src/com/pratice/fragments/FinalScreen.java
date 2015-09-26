package com.pratice.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.techathonuserexperience.R;
import com.pratice.utils.ImageParser;
import com.pratice.utils.LocationUtils;

public class FinalScreen extends Fragment implements OnClickListener {
	
	private Button mAcceptButton = null;
	private Button mCancelButton = null;
	private EditText mTitle = null;
	private EditText mDescription = null;
	private EditText locationSpinner = null;
	private Spinner catSpinner, subcatSpinner;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.final_screen, container, false);
		
		mAcceptButton = (Button) rootView.findViewById(R.id.accept);
		mCancelButton = (Button) rootView.findViewById(R.id.cancel);
		mTitle = (EditText) rootView.findViewById(R.id.et_title);
		mTitle.setText(ImageParser.getInstance().getTitle());
		mDescription = (EditText) rootView.findViewById(R.id.desc);
		mDescription.setText(ImageParser.getInstance().getDescription());
		mCancelButton.setOnClickListener(this);
		mAcceptButton.setOnClickListener(this);
		
		locationSpinner = (EditText) rootView.findViewById(R.id.et_loc);
		locationSpinner.setText(LocationUtils.getInstance().getLocation());
		catSpinner = (Spinner) rootView.findViewById(R.id.cat);
		subcatSpinner = (Spinner) rootView.findViewById(R.id.sub_cat);
	
		ArrayList<String> categoryList = new ArrayList<String>();
		categoryList.add(ImageParser.getInstance().getCategory());
		 
		ArrayList<String> subategoryList = new ArrayList<String>();
		subategoryList.add(ImageParser.getInstance().getSubCaegory());
		
		ArrayAdapter<String> catrArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categoryList); //selected item will look like a spinner set from XML
		catrArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		catSpinner.setAdapter(catrArrayAdapter);

		ArrayAdapter<String> subcatArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, subategoryList); //selected item will look like a spinner set from XML
		subcatArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		subcatSpinner.setAdapter(subcatArrayAdapter);
		
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()) {
			
		case R.id.cancel:

			FragmentManager fragmentManager = getFragmentManager();

			fragmentManager.beginTransaction()
					.replace(R.id.container, HomeFragment.newInstance(1))
					.commit();
				
			break;

			
		case R.id.accept:

			fragmentManager = getFragmentManager();

			fragmentManager.beginTransaction()
					.replace(R.id.container, HomeFragment.newInstance(1))
					.commit();
			
			Toast.makeText(getActivity(), "Thank YOu", Toast.LENGTH_LONG).show();
			
			
			break;
		}
	}
}