package com.example.firstapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class TouchDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder
			= new AlertDialog.Builder(getActivity());
		builder.setMessage("円がクリックされました");
		builder.setPositiveButton("OK", null);
		return builder.create();
	}
}
