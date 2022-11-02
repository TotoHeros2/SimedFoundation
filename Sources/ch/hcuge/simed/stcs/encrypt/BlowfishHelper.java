package ch.hcuge.simed.stcs.encrypt;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.eof.ERXEC;

public class BlowfishHelper {
	private static String key = "Put real key here";
	private static Blowfish bf = new Blowfish(key);

	public static String encrypt(String name)
	{
		if (name == null || name.trim().equals(""))
		{
			return "";
		}
		return bf.encryptString(name.trim());
	}
	
	public static String decrypt(String encrytedName)
	{
		if (encrytedName == null || encrytedName.trim().equals(""))
		{
			return "";
		}
		return bf.decryptString(encrytedName.trim());

	}
	
    static byte[] encode(byte[] arr){
        Charset utf8charset = Charset.forName("UTF-8");
        Charset iso88591charset = Charset.forName("ISO-8859-15");

        ByteBuffer inputBuffer = ByteBuffer.wrap( arr );

        // decode UTF-8
        CharBuffer data = utf8charset.decode(inputBuffer);

        // encode ISO-8559-1
        ByteBuffer outputBuffer = iso88591charset.encode(data);
        byte[] outputData = outputBuffer.array();

        return outputData;
    }

}
