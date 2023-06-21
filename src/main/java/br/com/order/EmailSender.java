package br.com.order;

public interface EmailSender {
	public boolean isOffline();

	public int sendEmail(String email, String title, String body);
}
