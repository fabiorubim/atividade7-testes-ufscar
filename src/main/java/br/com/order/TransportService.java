package br.com.order;

public interface TransportService {
	public boolean isDown();

	public int makeTag(int code, String address);
}
