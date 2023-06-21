package br.com.cifrar;

import java.util.ArrayList;

public class AClass {
	private Coder1 coder1;
	private Coder2 coder2;
	private Coder3 coder3;

	public AClass(Coder1 coder1, Coder2 coder2, Coder3 coder3) {
		this.coder1 = coder1;
		this.coder2 = coder2;
		this.coder3 = coder3;
	}

	public String cifrar(ArrayList<String> msg) {
		if (msg == null || msg.size() == 0 || msg.contains("STOP"))
			return "INVALID";
		if (coder1.m1(msg) && coder2.m2(msg))
			return "C1-C2";
		var count = 0;
		for (String s : msg) {
			if (s.equals("HI"))
				count++;
		}
		if (count >= 2)
			return "2 OR MORE HIs";
		int num = coder3.m3();
		if (num > 1) {
			String ret = "";
			for (int i = 1; i <= num; i++)
				ret += "-" + msg.get(0);
			return ret;
		}
		return msg.get(0);
	}
}
