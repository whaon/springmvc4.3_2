package com.mileweb.glb.apiserver.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.cnc.portal.util.CryptoUtil;
import com.cnc.portal.util.EncodeUtil;


public class DigestUtil {
	public static String getSha256(String key) {

		String sha256 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(key.getBytes());
			for (byte b : md.digest()) {
				sha256 += String.format("%02x", b);
			}
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sha256;
	}

	public static String randomApiKey() {
		String[] uuids = UUID.randomUUID().toString().split("-");
		return uuids[0] + uuids[1] + uuids[2];
	}

	public static String getBASE64(String s) {
		if (s == null)
			return null;
		//return (new BASE64Encoder()).encode(s.getBytes());
		return EncodeUtil.encodeBase64(s.getBytes());
	}

	public static String signHmacSHA1(String key, String data) throws Exception {
		byte signData[] = signHmacSHA1(key.getBytes("UTF-8"), data.getBytes("UTF-8"));
		return toBase64String(signData);
	}

	private static byte[] signHmacSHA1(byte key[], byte data[]) throws Exception {
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(new SecretKeySpec(key, "HmacSHA1"));
		return mac.doFinal(data);
	}

	public static String toBase64String(byte binaryData[]) {
		//return (new BASE64Encoder()).encode(binaryData);
		return EncodeUtil.encodeBase64(binaryData);
	}

	public static void main(String[] args) {
		try {
			System.out.println(CryptoUtil.decryptApi("570bc3a1503628462a06294a97f313ca"));
			System.out.println(CryptoUtil.encryptApi("Cnc592CM"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(EncodeUtil.decodeBase64ToString("dG9tY2F0OnRvbWNhdA=="));
		
		System.out.println(randomApiKey());
	}

	public static String md5(String string) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] bytes = string.getBytes("GBK");
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			byte[] updateBytes = messageDigest.digest();
			int len = updateBytes.length;
			char myChar[] = new char[len * 2];
			int k = 0;
			for (int i = 0; i < len; i++) {
				byte byte0 = updateBytes[i];
				myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
				myChar[k++] = hexDigits[byte0 & 0x0f];
			}
			return new String(myChar);
		}
		catch (Exception e) {
			return null;
		}
	}

	public static String getWsKey(String param) {
		String cmdPath = "//usr//local//bin//encip";
		java.lang.Runtime rt = Runtime.getRuntime();
		String pwd = null;
		BufferedReader br = null;
		try {
			Process process = rt.exec(cmdPath + " " + param);
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			pwd = br.readLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (br != null) {
					br.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pwd;
	}
}
