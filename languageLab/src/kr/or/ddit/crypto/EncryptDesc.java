package kr.or.ddit.crypto;

import java.security.CryptoPrimitive;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import kr.or.ddit.util.CryptoUtils;

/**
 * encoding(부호화) : 데이터의 전송이나 저장을 목적으로 해당 매체가 이해할 수 있는 방식으로 데이터의 표현을 바꾸는 작업.
 * 	ex) URL encoding, Base64 encoding
 * encrypting(암호화) : 허가받지 않은 접근에 의해 데이터가 읽히거나 변환될수 없도록 막기 위한 작업.
 * 	1. 단방향 암호화 : 복호화가 불가능한 암호화 기법(해시-hash 함수).
 * 			ex) Sha-512
 * 			hash 함수란? 다양한 길이의 입력데이터를 처리하여, 일정 길이의 문자열을 만드는 함수(충돌의 위험 잔존).
 *  2. 양방향 암호화 : 복호화를 통해 원래의 평문이 복원 가능한 기법.
 *     1) 비밀키(대칭키) 암호화 : AES
 *     2) 공개키(비대칭키) 암호화 : 한쌍의 키(공개키, 개인키)로 데이터를 암복호화. 암호화와 복호화에 서로 반대키가 사용됨. RSA
 *
 */
public class EncryptDesc {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		String plain = "java";
		
		String encoded = CryptoUtils.sha512EncryptBase64(plain);
		System.out.println(encoded);
		
//		KeyPair keyPair = CryptoUtils.keyPairGenerateForRSA(2048);
//		PrivateKey privateKey = keyPair.getPrivate();
//		PublicKey publicKey = keyPair.getPublic();
//		String encoded = CryptoUtils.rsaEncryptBase64(plain, privateKey);
//		String decryptedStr = CryptoUtils.rsaDecryptBase64(encoded, publicKey);
//		String ivValue = "ㅁㄴㅇㄻㄴㅇㄹ";
//		SecretKey key = CryptoUtils.generateAESKey(256);
//		String encoded = CryptoUtils.aesEncryptBase64(plain, ivValue, key);
//		String decryptedStr = CryptoUtils.aesDecryptBase64(encoded, ivValue, key);
		
	}
	
}













