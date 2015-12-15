package ml.dpgames.infinite.stone.main;

import java.util.Locale;

public abstract class Handler {
	
	public void achievement(String name) {
		
	}
	
	public void signIn() {
		
	}
	
	public String getLanguage() {
		return Locale.getDefault().getLanguage();
	}
	
}
