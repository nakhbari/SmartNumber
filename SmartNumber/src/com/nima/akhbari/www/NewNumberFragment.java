package com.nima.akhbari.www;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNumberFragment extends Fragment {

	Button bNewNumber;
	EditText etNewNumber;
	ActivityCommunicator numberHandler;
	Context context;
	String phoneNumber;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		context = getActivity();
		try{
			numberHandler = (ActivityCommunicator)context;
		}catch(ClassCastException e){
			Log.i("Handler Exception", "The Activity "+ context.getClass().getSimpleName() + " does not implement ActivityCommunicator interface" );
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		bNewNumber = (Button) getView().findViewById(R.id.bNewNumber);
		etNewNumber = (EditText) getView().findViewById(R.id.etNewNumber);

		etNewNumber.addTextChangedListener(new PhoneNumberTextWatcher());

		etNewNumber.setFilters(new InputFilter[] { new PhoneNumberFilter(),
				new InputFilter.LengthFilter(12) });

		bNewNumber.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Yo Button has been pressed",
						Toast.LENGTH_SHORT).show();
				phoneNumber = etNewNumber.getText().toString();
				etNewNumber.setText("");
				numberHandler.passDataToActivity(phoneNumber);
			}

		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.new_number_fragment, container, false);

	}

}
