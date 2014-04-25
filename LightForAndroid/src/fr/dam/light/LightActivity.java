package fr.dam.light;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import fr.dam.lightforandroid.R;

/**
 * 
 * @author Damien
 * 
 *         The purpose of this application is to allow the phone to use the
 *         torch light There are 3 functions. The first is the normal light. Can
 *         be turn on and off by pushing the light button. The second is a way
 *         to lock the light on when the application is onPause (when you use
 *         another application) The third is a warning and its seekbar to adjust
 *         the time of the time warning value
 * 
 */

// The LightActivity class will only override the useful method from Activity in
// order to separate the view, the model and the controler
public class LightActivity extends Activity implements Observer {

	// Global layout of the view
	RelativeLayout rLayout        = null;

	// Light Controler : gets all the interactions with the view
	LightControler lightControler = null;

	// Components associated with the light controler
	Button         lightButton    = null;
	Button         warningButton  = null;
	TextView       lockStatut     = null;
	CheckBox       lock           = null;

	// Component associated with the warning controler
	TextView       warningValue   = null;
	SeekBar        warningSB      = null;

	// Method to build the view
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Using the inflate method allow to "read" an XML file to turn it into
		// objects
		rLayout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_light, null);
		setContentView(rLayout);

		// Get the objects by there direct reference to the XML file
		// for the light
		lightButton = (Button) findViewById(R.id.lightButton);
		lock = (CheckBox) findViewById(R.id.lockCheck);
		lockStatut = (TextView) findViewById(R.id.lockStatut);
		// for the warning
		warningButton = (Button) findViewById(R.id.warningButton);
		warningValue = (TextView) findViewById(R.id.warningValue);
		warningSB = (SeekBar) findViewById(R.id.warningSeekBar);

		// Get the 2 controlers
		// LightControler which deals with OnClickListener
		lightControler = new LightControler();

		// Add the OnclickListner to those objects
		lightButton.setOnClickListener(lightControler);
		lock.setOnClickListener(lightControler);
		// warningButton.setOnClickListener(lightControler);

		// Add the OnSeekBarChangeLister to this object
		warningButton.setOnClickListener(lightControler);
		warningSB.setOnSeekBarChangeListener(lightControler);

		// This class is able to be
		this.lightControler.addObs(this);

	}

	@Override
	public void update(String upLight, String upWarning, String upLock, String upWarValue) {
		if (upLight != null) {
			lightButton.setText(upLight);
		}
		if (upWarning != null) {
			warningButton.setText(upWarning);
		}
		if (upLock != null) {
			lockStatut.setText(upLock);
		}
		if(upWarValue != null){
			warningValue.setText(upWarValue+"ms");
		}

	}


//	@Override
//	protected void onStop() {
//		super.onStop();
//		if (lightButton.getText().equals(getResources().getString(R.string.lightOn))) {
//			if (!lock.isChecked()) {
//				LightModel.turnOffLight();
//			}
//		}
//	}
//
//	@Override
//	protected void onRestart() {
//		super.onRestart();
//		if (lightButton.getText().equals(getResources().getString(R.string.lightOn))) {
//			if (!lock.isChecked()) {
//				LightModel.turnOnLight();
//			}
//		}
//	}

}

// this.lightControler.addObs(new Observer() {
//
// @Override
// public void update(String upLight, String upWarning, String upLockStatut) {
// if (upLight != null) {
// lightButton.setText(upLight);
// }
// if (upWarning != null) {
// warningButton.setText(upWarning);
// }
// if (upLockStatut != null) {
// lockStatut.setText(upLockStatut);
// }
// }
// });