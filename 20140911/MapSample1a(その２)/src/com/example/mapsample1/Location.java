package com.example.mapsample1;

import com.google.android.gms.maps.model.LatLng;

public class Location {
	private String name;
	private LatLng location;

	public Location(String name, LatLng location) {
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}
	public LatLng getLocation() {
		return location;
	}
	@Override
	public String toString() {
		return name;
	}
}
