package com.bankrate.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.bankrate.R;

/**
 * @Description: activity man hinh splash screen
 * @author:truonglt2
 * @since:Feb 7, 2014 4:24:04 PM
 * @version: 1.0
 * @since: 1.0
 */
public class WellcomeActivity extends Activity implements OnClickListener {
	private static int SPLASH_TIME = 1000;
	int mTimerStep = 300;
	int timer = 1;
	ImageView splashView;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.wellcome_activity);
		splashView = (ImageView) findViewById(R.id.bgImage_screenFirst);
		splashView.setOnClickListener(this);
		startSplash();
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onClick(View v) {
		if (v == splashView) {
			timer += SPLASH_TIME;
		}
	}

	public void startSplash() {
		// select background base on screen size
		Thread splashThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(mTimerStep);
						timer += mTimerStep;
						if ((timer > SPLASH_TIME) || (timer > 30 * SPLASH_TIME)) {
							break;
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						break;
					}
				}
				Intent main = new Intent(WellcomeActivity.this,
						MainActivity.class);
				startActivity(main);
				finish();
			}
		});
		splashThread.start();
	}
}
