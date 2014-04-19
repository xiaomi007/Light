package fr.dam.light;

import fr.dam.lightforandroid.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LightActivity extends Activity {

	RelativeLayout rLayout = null;
	LightControler lightControler = null;
	
	Button 	 light 		= null;
	Button 	 warning 	= null;
	TextView lockStatut = null;
	CheckBox lock 		= null;
			
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rLayout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_light, null);
		setContentView(rLayout);
		
		light 		= (Button)findViewById(R.id.lightButton);
		warning 	= (Button)findViewById(R.id.warningButton);
		lockStatut 	= (TextView)findViewById(R.id.lockStatut);
		lock		= (CheckBox)findViewById(R.id.lockCheck);
		lightControler = new LightControler();
		
		light.setOnClickListener(lightControler);
		warning.setOnClickListener(lightControler);
		lock.setOnClickListener(lightControler);
	
		this.lightControler.addObs(new Observer() {
			
			@Override
			public void update(String upLight, String upWarning, String upLockStatut) {
				if(upLight != null){
					light.setText(upLight);
				}
				if(upWarning != null){
					warning.setText(upWarning);
				}
				if(upLockStatut != null){
					lockStatut.setText(upLockStatut);
				}
			}
		});
	}

	@Override
    protected void onStop() {
	    super.onStop();
	    if(light.getText().equals(getResources().getString(R.string.lightOn))){
	    	if(!lock.isChecked()){
	    		LightModel.turnOffLight();
	    	}
	    }
    }
	
	
	@Override
    protected void onRestart() {
	    super.onRestart();
	    if(light.getText().equals(getResources().getString(R.string.lightOn))){
	    	if(!lock.isChecked()){
	    		LightModel.turnOnLight();
	    	}
	    }
    }





	
	
	
	



	
	
	
	


}
