package com.example.mapsample1;

public class Location {
	private String name;
	private LatLng location;




	public Location(String name, LatLng location ) {
		super();
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
