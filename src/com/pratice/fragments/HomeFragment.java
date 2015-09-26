package com.pratice.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.techathonuserexperience.MainActivity;
import com.example.techathonuserexperience.R;
import com.pratice.utils.LocationUtils;


public class HomeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private Button mBechDeButton = null;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	//Start fetch the location, so that user dont ve to wait for it
    	LocationUtils.getInstance().startNavigationEngine();
    	
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mBechDeButton = (Button) rootView.findViewById(R.id.bech_de_button);
        mBechDeButton.setOnClickListener(mBechDeListener);
        
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
    

	private OnClickListener mBechDeListener = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			ImageCapture imageCaptureFg = new ImageCapture();
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.container, imageCaptureFg).commit();
			
		}
	};
}