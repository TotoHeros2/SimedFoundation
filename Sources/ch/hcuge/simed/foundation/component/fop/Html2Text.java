package ch.hcuge.simed.foundation.component.fop;

import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
import java.io.*;

public class Html2Text extends HTMLEditorKit.ParserCallback {

	private String text = "";

	/**
	 * Réimplémenter pour stocker la conversion
	 */
	public void handleText(char[] data, int pos) {
		text += new String(data);
		//System.out.println("##### handleText " + data + " | " + pos);
	}

	/**
	 * Convertie la chaine de texte HTML en texte pure
	 * @param html est la chaine de texte a transformer
	 * @return texte HTML transformé en Texte
	 * @throws IOException si le parsing échoue
	 */
	public static String convert(String html) throws IOException {
		try {
			Reader r = new StringReader(html);
			ParserDelegator parser = new ParserDelegator();
			HTMLEditorKit.ParserCallback callback = new Html2Text();
			parser.parse(r, callback, true); // or 'false' if you like
			return ((Html2Text)callback).getText();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Le texte HTML transformé en Texte
	 * @return
	 */
	public String getText() {
		return text;
	}

}