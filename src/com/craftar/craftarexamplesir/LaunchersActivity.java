// com.craftar.craftatexamplesir is free software. You may use it under the MIT license, which is copied
// below and available at http://opensource.org/licenses/MIT
//
// Copyright (c) 2014 Catchoom Technologies S.L.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of
// this software and associated documentation files (the "Software"), to deal in
// the Software without restriction, including without limitation the rights to use,
// copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
// Software, and to permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
// INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
// FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
// DEALINGS IN THE SOFTWARE.

package com.craftar.craftarexamplesir;

import com.craftar.fragmentexample.ScreenSlideActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LaunchersActivity extends Activity implements OnClickListener {

	// Howto links
	private TextView mHowToLink;
	private LinearLayout mAboutRecognitionFinder;
	private LinearLayout mAboutRecognitionOnly;
	private LinearLayout mAboutFragment;

	
	// Launch example links
	private LinearLayout mRecognitionFinder;
	private LinearLayout mRecognitionOnly;
	private LinearLayout mRecognitionFragment;

	// Bottom links
	private ImageButton mButtonCatchoom;
	private Button mButtonSignUp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_launchers);
		
		// Setup howto links
		mHowToLink = (TextView)findViewById(R.id.howto_link);
		mHowToLink.setClickable(true);
		mHowToLink.setOnClickListener(this);
		mAboutRecognitionFinder = (LinearLayout)findViewById(R.id.howto_link_finder);
		mAboutRecognitionFinder.setClickable(true);
		mAboutRecognitionFinder.setOnClickListener(this);
		mAboutRecognitionOnly = (LinearLayout)findViewById(R.id.howto_link_recognition_only);
		mAboutRecognitionOnly.setClickable(true);
		mAboutRecognitionOnly.setOnClickListener(this);
		mAboutFragment = (LinearLayout)findViewById(R.id.howto_link_fragment);
		mAboutFragment.setClickable(true);
		mAboutFragment.setOnClickListener(this);

		// Setup example links
		mRecognitionFinder = (LinearLayout)findViewById(R.id.play_finder);
		mRecognitionFinder.setClickable(true);
		mRecognitionFinder.setOnClickListener(this);
		mRecognitionOnly = (LinearLayout)findViewById(R.id.play_recognition_only);
		mRecognitionOnly.setClickable(true);
		mRecognitionOnly.setOnClickListener(this);
		mRecognitionFragment = (LinearLayout)findViewById(R.id.play_fragment_finder);
		mRecognitionFragment.setClickable(true);
		mRecognitionFragment.setOnClickListener(this);
		
		// Setup bottom Links
		mButtonCatchoom = (ImageButton)findViewById(R.id.imageButton_logo);
		mButtonCatchoom.setOnClickListener(this);
		mButtonSignUp = (Button)findViewById(R.id.button_signUp);
		mButtonSignUp.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		// Clicked on title or howto links
		Intent launchHowto = null;
		if (v == mHowToLink) {
			launchHowto = new Intent(this, HowToActivity.class);
			launchHowto.putExtra(HowToActivity.HOWTO_LAYOUT_EXTRA, R.layout.activity_howto);
		} else if (v == mAboutRecognitionFinder) {
			launchHowto = new Intent(this, HowToActivity.class);
			launchHowto.putExtra(HowToActivity.HOWTO_LAYOUT_EXTRA, R.layout.activity_howto_reco_finder);
		} else if (v == mAboutRecognitionOnly) {
			launchHowto = new Intent(this, HowToActivity.class);
			launchHowto.putExtra(HowToActivity.HOWTO_LAYOUT_EXTRA, R.layout.activity_howto_recognition_only);
		} else if (v == mAboutFragment) {
			launchHowto = new Intent(this, HowToActivity.class);
			launchHowto.putExtra(HowToActivity.HOWTO_LAYOUT_EXTRA, R.layout.activity_howto_fragment);
		}
		if (launchHowto != null) {
			startActivity(launchHowto);
			return;
		}
		
		// Clicked on play links
		Intent playExampleIntent = null;
		if (v == mRecognitionFinder) {
			playExampleIntent = new Intent(this, RecognitionFinderActivity.class);
		} else if (v == mRecognitionOnly) {
			playExampleIntent = new Intent(this, RecognitionOnlyActivity.class);
		} else if (v == mRecognitionFragment) {
			playExampleIntent = new Intent(this, ScreenSlideActivity.class);
		}
		
		if (playExampleIntent != null) {
			startActivity(playExampleIntent);
			return;
		}
		
		// Clicked on bottom links
		if (v == mButtonCatchoom || v == mButtonSignUp) {
			// mButtonCatchoom
			String url = "http://catchoom.com/product/?utm_source=CraftARExamplesApp&amp;utm_medium=Android&amp;utm_campaign=HelpWithAPI";
			if (v == mButtonSignUp) {
				url = "https://my.craftar.net/try-free?utm_source=CraftARExamplesApp&amp;utm_medium=Android&amp;utm_campaign=HelpWithAPI";
			}
			
			Intent launchWebView = new Intent(this, WebActivity.class);
			launchWebView.putExtra(WebActivity.WEB_ACTIVITY_URL, url);
			startActivity(launchWebView);			
			return;
		}
	}

}
