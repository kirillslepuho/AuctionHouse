package by.epam.auctionhouse.crypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {
	public static String getHash(String str) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {

		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		// �������� � MessageDigest ����-��� ������
		m.update(str.getBytes("utf-8"));
		// �������� MD5-��� ������ ��� ���������� �����
		String s2 = new BigInteger(1, m.digest()).toString(16);
		StringBuilder sb = new StringBuilder(32);
		// ��������� ������ �� 32 ��������, � ������ �������������
		//System.out.println(32 - s2.length());
		for (int i = 0, count = 32 - s2.length(); i < count; i++) {
			sb.append("0");
		}
		// ���������� MD5-���
		return sb.append(s2).toString();
	}
}