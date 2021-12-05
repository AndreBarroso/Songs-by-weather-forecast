package com.andrebarroso.backend.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class spotfyResponse {

	private List<Object> items = new ArrayList<Object>();
	private String item;
	private String album;
	private String song;
	private String artist;
	
	public List getItems() {
		return items; 
	}
	
	private String getTrack(int i) {
        item = items.get(i).toString();
		return item.substring(item.indexOf("track"), item.indexOf("uri=spotify:track:"));
	}
	
	public String getSong(int i) {
		String t = getTrack(i);
		String wordToDiscartCharacter = t.substring(t.indexOf("is_local"));
		song = wordToDiscartCharacter
				.substring(wordToDiscartCharacter.indexOf("name") + 5, wordToDiscartCharacter.indexOf("popularity") - 2);
		return song;
	}
	
	public String getAlbum(int i) {
		String t = getTrack(i);
		String wordToDiscartCharacter = t.substring(t.indexOf("], name="));
		album = wordToDiscartCharacter
				.substring(wordToDiscartCharacter.indexOf("=") + 1,wordToDiscartCharacter.indexOf("release") - 2);
		return album;
	}
	
	public String getArtist(int i) {
		String t = getTrack(i);
		String wordToDiscartCharacter = t.substring(t.indexOf("artists"));
		artist = wordToDiscartCharacter
				.substring(wordToDiscartCharacter.indexOf("name") + 5, wordToDiscartCharacter.indexOf("type") - 2);
		return artist;
	}
}
