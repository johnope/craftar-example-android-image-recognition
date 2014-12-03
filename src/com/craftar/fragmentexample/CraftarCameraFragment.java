package com.craftar.fragmentexample;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.craftar.CraftARActivity;
import com.craftar.CraftARCameraView;
import com.craftar.CraftARCloudRecognition;
import com.craftar.CraftARCloudRecognitionError;
import com.craftar.CraftARItem;
import com.craftar.CraftARResponseHandler;
import com.craftar.CraftARSDK;
import com.craftar.craftarexamplesir.R;

public class CraftarCameraFragment extends Fragment implements CraftARResponseHandler{

	private static final String TAG = "CraftARFragmentSlider";
	private CraftARCloudRecognition mCloudRecognition;
	private String mCollectionToken = "craftarexamples1";
	private boolean mIsCraftarInitialized = false;
	private CraftARActivity mCraftARActivity;
	private View mScanningView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_camera, container, false);
        CraftARCameraView cameraView = (CraftARCameraView) rootView.findViewById(R.id.camera_preview);
        mScanningView = rootView.findViewById(R.id.layout_scanning);
        mCraftARActivity =  (CraftARActivity)getActivity();
		mCraftARActivity.setCameraView(cameraView);
		
        initCraftAR();
        return rootView;
    }
    
    
    private void initCraftAR(){
    	
    	//Initialize the SDK. From this SDK, you will be able to retrieve the necessary modules to use the SDK (camera, tracking, and cloud-recgnition)
		CraftARSDK.init(mCraftARActivity.getApplicationContext(),mCraftARActivity);
		mCloudRecognition = CraftARSDK.getCloudRecognition();
		mCloudRecognition.setCollectionToken(mCollectionToken);
		mCloudRecognition.setResponseHandler(this);
		mCloudRecognition.startFinding();
    	mScanningView.setVisibility(View.VISIBLE);
    	
		mIsCraftarInitialized = true;
    }
    
    public void onCameraHiddenChanged(boolean hidden){
    	if(!mIsCraftarInitialized){
			Log.d(TAG,"CraftAR not initialized...");
        	return;	
    	}
    	
    	if(hidden){
			Log.d(TAG,"STOP FINDER MODE!!!");
        	mCloudRecognition.stopFinding();
        	mScanningView.setVisibility(View.GONE);
    	}else{
    		Log.d(TAG,"START FINDER MODE!!!");
    		mCloudRecognition.startFinding();
        	mScanningView.setVisibility(View.VISIBLE);
    	}
    }
	@Override
	public void connectCompleted() {

	}

	@Override
	public void requestFailedResponse(int requestCode,CraftARCloudRecognitionError responseError) {
		Log.d(TAG,"Received error with code " +responseError.getErrorCode() +" and message "+responseError.getErrorMessage());
	}

	@Override
	public void searchCompleted(ArrayList<CraftARItem> items) {
		if(items.isEmpty()){
			Log.d(TAG,"Nothing found!");
			return;
		}
		
		mCloudRecognition.stopFinding();
    	mScanningView.setVisibility(View.GONE);
		for(CraftARItem item:items){
			String text= "Found item with name :"+item.getItemName();
			Log.d(TAG,text);
			Toast.makeText(getActivity().getApplicationContext(),text,Toast.LENGTH_SHORT).show();
		}
	}
}
