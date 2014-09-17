package com.example.databasesanple;

public class Note {
	protected int id;
	protected String note;
	protected String lastupdate;

	public Note(int id, String note, String lastupdate) {
		super();
		this.id = id;
		this.note = note;
		this.lastupdate = lastupdate;
	}

	public int getId() {
		return id;
	}

	public String getNote() {
		return note;
	}

	public String getLastupdate() {
		return lastupdate;
	}
}
