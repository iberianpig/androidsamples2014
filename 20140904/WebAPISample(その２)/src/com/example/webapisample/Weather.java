package com.example.webapisample;

public class Weather {
	private String mDateLabel;
	private String mTelop;
	private String mHighTemperture;
	private String mLowTemperture;

	public Weather() {
		super();
		this.mHighTemperture = "--";
		this.mLowTemperture = "--";
	}

	public String getmDateLabel() {
		return mDateLabel;
	}

	public void setmDateLabel(String mDateLabel) {
		this.mDateLabel = mDateLabel;
	}

	public String getmTelop() {
		return mTelop;
	}

	public void setmTelop(String mTelop) {
		this.mTelop = mTelop;
	}

	public String getmHighTemperture() {
		return mHighTemperture;
	}

	public void setmHighTemperture(String mHighTemperture) {
		this.mHighTemperture = mHighTemperture;
	}

	public String getmLowTemperture() {
		return mLowTemperture;
	}

	public void setmLowTemperture(String mLowTemperture) {
		this.mLowTemperture = mLowTemperture;
	}
}
