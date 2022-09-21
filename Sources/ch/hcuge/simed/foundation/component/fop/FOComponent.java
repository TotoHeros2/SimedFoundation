package ch.hcuge.simed.foundation.component.fop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.fop.apps.Driver;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Options;
import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.components.ERXComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class FOComponent extends ERXComponent {

	private static Logger log = Logger.getLogger(FOComponent.class);

	protected 	WOResponse					pageResponse = null;

    private String _contentType = "text/fo-xml";

    /**
     * constructor
     *
     * @param context est le context courant
     */
    public FOComponent(WOContext context) {
        super(context);
    }

    /**
     * Permet de fixer le content type
     *
     * @param aType est le nouveau content type
     */
    public void setContentType(String aType) {
        _contentType = aType;
    }

    /**
     * Retourne le content type
     *
     * @return le content type
     */
    public String contentType() {
        return _contentType;
    }

    /**
     * Permet de g?n?rer un fichier PDF  partir d'un xsl-fo
     *
     * @param response est la r?ponse sous forme d'un fichier PDF
     * @param context est le context courant
     */
    public void appendToResponseSimple(WOResponse response, WOContext context) {
        //((HugSession)session()).logPerformanceStart("G\u00E9n\u00E9ration FO",2);
        super.appendToResponse(response, context);
        //((HugSession)session()).logPerformanceEnd("G\u00E9n\u00E9ration FO",2);
    }
    
    public String convertFOPToFOPWithAccents(String chaine) {
    	return returnConvertFOPToFOPWithAccents(chaine);
    }

    /**
     * Permet de convertir un fichier PDF en un fichier PDF avec les accents corrects
     *
     * Pour caratères à convertir (http://www1.tip.nl/~t876506/utf8tbl.html) si problèmes
     *
     * @param chaine est le contenu du fichier PDF
     * @return le fichier PDF corrigé
     */
    public static String returnConvertFOPToFOPWithAccents(String chaine) {
        NSArray        components;

		components = NSArray.componentsSeparatedByString(chaine, "&amp;#");
		chaine = components.componentsJoinedByString("&#");
		
        components = NSArray.componentsSeparatedByString(chaine, "é");
        chaine = components.componentsJoinedByString("&#233;");
        components = NSArray.componentsSeparatedByString(chaine, "\u00E9");
        chaine = components.componentsJoinedByString("&#233;");

        components = NSArray.componentsSeparatedByString(chaine, "è");
        chaine = components.componentsJoinedByString("&#232;");
        components = NSArray.componentsSeparatedByString(chaine, "\u00E8");
        chaine = components.componentsJoinedByString("&#232;");

        components = NSArray.componentsSeparatedByString(chaine, "ê");
        chaine = components.componentsJoinedByString("&#234;");
        components = NSArray.componentsSeparatedByString(chaine, "\u00EA");
        chaine = components.componentsJoinedByString("&#234;");

        components = NSArray.componentsSeparatedByString(chaine, "à");
        chaine = components.componentsJoinedByString("&#224;");
        components = NSArray.componentsSeparatedByString(chaine, "\u00E0");
        chaine = components.componentsJoinedByString("&#224;");

        components = NSArray.componentsSeparatedByString(chaine, "¨");
        chaine = components.componentsJoinedByString("&#174;");
        components = NSArray.componentsSeparatedByString(chaine, "\u00A8");
        chaine = components.componentsJoinedByString("&#174;");
              
    	components = NSArray.componentsSeparatedByString(chaine, "ô");
    	chaine = components.componentsJoinedByString("&#244;");
    	components = NSArray.componentsSeparatedByString(chaine, "\u00F4");
    	chaine = components.componentsJoinedByString("&#244;");
    	
		components = NSArray.componentsSeparatedByString(chaine, "û");
		chaine = components.componentsJoinedByString("&#x00FB;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00FB");
		chaine = components.componentsJoinedByString("&#x00FB;");
    	
    	components = NSArray.componentsSeparatedByString(chaine, "ë");
    	chaine = components.componentsJoinedByString("&#235;");
    	components = NSArray.componentsSeparatedByString(chaine, "\u00EB");
    	chaine = components.componentsJoinedByString("&#235;");

		components = NSArray.componentsSeparatedByString(chaine, "°");
		chaine = components.componentsJoinedByString("&#176;");
    	components = NSArray.componentsSeparatedByString(chaine, "\u00B0");
    	chaine = components.componentsJoinedByString("&#176;");
    	
    	components = NSArray.componentsSeparatedByString(chaine, "ù");
    	chaine = components.componentsJoinedByString("&#249;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00F9");
		chaine = components.componentsJoinedByString("&#x00F9;");

		components = NSArray.componentsSeparatedByString(chaine, "'");
		chaine = components.componentsJoinedByString("&#39;");
		components = NSArray.componentsSeparatedByString(chaine, "\u0027");
		chaine = components.componentsJoinedByString("&#39;");
		
		components = NSArray.componentsSeparatedByString(chaine, "`");
		chaine = components.componentsJoinedByString("&#145;");
		components = NSArray.componentsSeparatedByString(chaine, "\u0091");
		chaine = components.componentsJoinedByString("&#145;");
		
		components = NSArray.componentsSeparatedByString(chaine, "'");
		chaine = components.componentsJoinedByString("&#146;");
		components = NSArray.componentsSeparatedByString(chaine, "\u0092");
		chaine = components.componentsJoinedByString("&#146;");
    	
		components = NSArray.componentsSeparatedByString(chaine, "ï");
		chaine = components.componentsJoinedByString("&#239;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00EF");
		chaine = components.componentsJoinedByString("&#239;");
		
    	
		components = NSArray.componentsSeparatedByString(chaine, "ç");
		chaine = components.componentsJoinedByString("&#231;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00E7");
		chaine = components.componentsJoinedByString("&#231;");
		
        components = NSArray.componentsSeparatedByString(chaine, "±");
        chaine = components.componentsJoinedByString("&#177;");
        components = NSArray.componentsSeparatedByString(chaine, "\u0081");
        chaine = components.componentsJoinedByString("&#177;");

        components = NSArray.componentsSeparatedByString(chaine, "Â");
        chaine = components.componentsJoinedByString("&#194;");
        components = NSArray.componentsSeparatedByString(chaine, "\u00C2");
        chaine = components.componentsJoinedByString("&#194;");

        components = NSArray.componentsSeparatedByString(chaine, "â");
        chaine = components.componentsJoinedByString("&#226;");
        components = NSArray.componentsSeparatedByString(chaine, "\u00E2");
        chaine = components.componentsJoinedByString("&#226;");

        components = NSArray.componentsSeparatedByString(chaine, "Ä");
        chaine = components.componentsJoinedByString("&#196;");
        components = NSArray.componentsSeparatedByString(chaine, "\u00C4");
        chaine = components.componentsJoinedByString("&#196;");

        components = NSArray.componentsSeparatedByString(chaine, "Ô");
        chaine = components.componentsJoinedByString("&#212;");
        components = NSArray.componentsSeparatedByString(chaine, "\u00D4");
        chaine = components.componentsJoinedByString("&#212;");
              
    	components = NSArray.componentsSeparatedByString(chaine, "ä");
    	chaine = components.componentsJoinedByString("&#228;");
    	components = NSArray.componentsSeparatedByString(chaine, "\u00E4");
    	chaine = components.componentsJoinedByString("&#228;");
    	
		components = NSArray.componentsSeparatedByString(chaine, "µ");
		chaine = components.componentsJoinedByString("&#181;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00B5");
		chaine = components.componentsJoinedByString("&#181;");
    	
    	components = NSArray.componentsSeparatedByString(chaine, "Æ");
    	chaine = components.componentsJoinedByString("&#198;");
    	components = NSArray.componentsSeparatedByString(chaine, "\u00C6");
    	chaine = components.componentsJoinedByString("&#198;");

		components = NSArray.componentsSeparatedByString(chaine, "Ö");
		chaine = components.componentsJoinedByString("&#214;");
    	components = NSArray.componentsSeparatedByString(chaine, "\u00D6");
    	chaine = components.componentsJoinedByString("&#214;");
    	
    	components = NSArray.componentsSeparatedByString(chaine, "æ");
    	chaine = components.componentsJoinedByString("&#230;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00E6");
		chaine = components.componentsJoinedByString("&#230;");

		components = NSArray.componentsSeparatedByString(chaine, "ö");
		chaine = components.componentsJoinedByString("&#246;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00F6");
		chaine = components.componentsJoinedByString("&#246;");
		
		components = NSArray.componentsSeparatedByString(chaine, "Ç");
		chaine = components.componentsJoinedByString("&#199;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00C7");
		chaine = components.componentsJoinedByString("&#199;");
		
		components = NSArray.componentsSeparatedByString(chaine, "É");
		chaine = components.componentsJoinedByString("&#201;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00C9");
		chaine = components.componentsJoinedByString("&#201;");
    	
		components = NSArray.componentsSeparatedByString(chaine, "÷");
		chaine = components.componentsJoinedByString("&#247;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00F7");
		chaine = components.componentsJoinedByString("&#247;");
		    	
		components = NSArray.componentsSeparatedByString(chaine, "È");
		chaine = components.componentsJoinedByString("&#200;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00C8");
		chaine = components.componentsJoinedByString("&#200;");
		
		components = NSArray.componentsSeparatedByString(chaine, "Ù");
		chaine = components.componentsJoinedByString("&#217;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00D9");
		chaine = components.componentsJoinedByString("&#217;");
		
		components = NSArray.componentsSeparatedByString(chaine, "Ê");
		chaine = components.componentsJoinedByString("&#202;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00CA");
		chaine = components.componentsJoinedByString("&#202;");
		
		components = NSArray.componentsSeparatedByString(chaine, "Ë");
		chaine = components.componentsJoinedByString("&#202;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00CB");
		chaine = components.componentsJoinedByString("&#203;");
		
		components = NSArray.componentsSeparatedByString(chaine, "Û");
		chaine = components.componentsJoinedByString("&#219;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00DB");
		chaine = components.componentsJoinedByString("&#219;");
		
		components = NSArray.componentsSeparatedByString(chaine, "Ü");
		chaine = components.componentsJoinedByString("&#220;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00DC");
		chaine = components.componentsJoinedByString("&#220;");
		
		components = NSArray.componentsSeparatedByString(chaine, "ü");
		chaine = components.componentsJoinedByString("&#252;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00FC");
		chaine = components.componentsJoinedByString("&#252;");
		
		components = NSArray.componentsSeparatedByString(chaine, "Ï");
		chaine = components.componentsJoinedByString("&#207;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00CF");
		chaine = components.componentsJoinedByString("&#207;");

		components = NSArray.componentsSeparatedByString(chaine, "ß");
		chaine = components.componentsJoinedByString("&#223;");
		components = NSArray.componentsSeparatedByString(chaine, "\u00DF");
		chaine = components.componentsJoinedByString("&#223;");
		
		components = NSArray.componentsSeparatedByString(chaine, "®");
		chaine = components.componentsJoinedByString("&#174;");
		components = NSArray.componentsSeparatedByString(chaine, "&#x00AE;");
		chaine = components.componentsJoinedByString("&#174;");
		
		components = NSArray.componentsSeparatedByString(chaine, "");
		chaine = components.componentsJoinedByString("&#231;");
		components = NSArray.componentsSeparatedByString(chaine, "\u0152");
		chaine = components.componentsJoinedByString("&#231;");
		
		components = NSArray.componentsSeparatedByString(chaine, "");
		chaine = components.componentsJoinedByString("oe");
		components = NSArray.componentsSeparatedByString(chaine, "\u0156");
		chaine = components.componentsJoinedByString("oe");
		components = NSArray.componentsSeparatedByString(chaine, "\u0153");
		chaine = components.componentsJoinedByString("oe");
		components = NSArray.componentsSeparatedByString(chaine, "\u00BF");
		chaine = components.componentsJoinedByString("oe");
		
		return chaine;
    }
    
    /**
     * Permet de générer un fichier PDF à partir d'un xsl-fo
     *
     * @param response est la réponse sous forme d'un fichier PDF
     * @param context est le context courant
     */
    public void appendToResponse(WOResponse response, WOContext context) {    	    	
		if ((pageResponse != null) && (pageResponse.contentString().length()>0)) {     
			response.setContent(pageResponse.content());
			response.setHeader("application/pdf", "Content-Type");
			response.setHeader("" + pageResponse.content().length(), "Content-Length");
		}
		else {
			log.info("Start appendToResponse");
			initialize();		
			log.info("Start appendToResponseSimple");

			appendToResponseSimple(response, context);
			log.info("End appendToResponseSimple");
	
	        String fopForDisplay = convertFOPToFOPWithAccents(response.contentString().trim());
			log.info("End convertFOPToFOPWithAccents");
	
//	        if (((HugApplication)WOApplication.application()).debugMode())
//	       	        System.out.println("CONTENT: " + fopForDisplay); 
	    	log.info("CONTENT: " + fopForDisplay);

	        NSData dataFile = null;
	        // mode avec FOPServer ?
		        InputSource foInput = new InputSource(new ByteArrayInputStream(fopForDisplay.getBytes())); 
		
		        ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		        //Logger logger = Hierarchy.getDefaultHierarchy().getLoggerFor("fop"); // Plus d'actualité avec FOP 0.20.5
	
	                
	            String userConfig = System.getProperty("hugfoundation.fop.config");
				log.info("userConfig : " + userConfig);
		
		        if(userConfig!=null) {
		          File userConfigFile = new File(userConfig);
		          try {
		            Options options = new Options();
		            options.loadUserconfiguration(userConfigFile);
		          } catch (FOPException e) {
//					HugApplication app = (HugApplication)WOApplication.application();
		            // not to perturbate other application, exception is not propagated
		            e.printStackTrace();
//					HugSession.sendLog("FOP|ERROR|CONFIG| " + app.stackTraceOfException(e), HugLog.cLOG_ERROR,"0","TRA","");
		          }
		        }
				log.info("userConfig : " + userConfig);
		
		        Driver driver = new Driver(foInput, out);
			        
		        driver.setRenderer(Driver.RENDER_PDF);
		        driver.setOutputStream(out);
		
		        try {
//					HugSession.sendLog("FOP|START|DRIVER.RUN|"+name(), HugLog.cLOG_NORMAL,"0","TRA","");
		            driver.run();
//					HugSession.sendLog("FOP|END|DRIVER.RUN|"+name(), HugLog.cLOG_NORMAL,"0","TRA","");
		        } catch (Exception e) {
		            e.printStackTrace();
//					HugApplication app = (HugApplication)WOApplication.application();
//					HugSession.sendLog("FOP|ERROR|DRIVER.RUN| " + app.stackTraceOfException(e), HugLog.cLOG_ERROR,"0","TRA","");
		        }
		        
		        dataFile = new NSData(out.toByteArray());

	        response.setContent(dataFile);
	        
	        //Pour bug affichage dans IE par appel URL
	        response.disableClientCaching();
	        

	        response.setHeader("application/pdf", "Content-Type");
	        response.setHeader(String.valueOf(dataFile.length()), "Content-Length");
	        //Pour Bug IE double appel
	        response.removeHeadersForKey("cache-control");
			response.removeHeadersForKey("pragma");
			response.removeHeadersForKey("expires");
			
			
			pageResponse = response;
		}
    }

	public void initialize() {
    }
    
	/**
	 * Prise en compte des balises HTML pour transformation en XSLFO
	 * @param html
	 * @return
	 */
	public static String convertHTML2XSLFO(String html) {
		return convertHTML2XSLFO(html, true, false);
	}
	
	/**
	 * 
	 * @param html
	 * @param includeInBlock
	 * @return
	 */
	public static String convertHTML2XSLFO(String html, boolean includeInBlock) {
		return convertHTML2XSLFO(html, includeInBlock, false);
	}
	/**
	 * 
	 * @author NIC
	 * @since 12 avr. 2006
	 * @param html
	 * @param includeInBlock
	 * @return
	 */
	public static String convertHTML2XSLFO(String html, boolean includeInBlock, boolean justify) {
		if (html != null && html.toLowerCase().indexOf("<table")>-1) {
			String t = _convertHTML2XSLFOWithTable(html);
			//System.out.println("**** HTML tableau: ****\n" + t + "\n ****      FIN       ****");
			return t;
		} else {
			return _convertHTML2XSLFO(html, includeInBlock, justify);
		}	
	}
	
	/**
	 * 
	 * @author NIC
	 * @since 12 avr. 2006
	 * @param html
	 * @param includeInBlock
	 * @return
	 */
	private static String _convertHTML2XSLFO(String html, boolean includeInBlock, boolean justify) {
		if (includeInBlock)
			return "<fo:block white-space-collapse=\"false\">"+_convertHTML2XSLFOUnBlock(html, justify)+"</fo:block>";
		else
			return _convertHTML2XSLFOUnBlock(html, justify);
	}
	
    /**
     * @author NIC
     * @since 20.04.2005 création
     * Prise en compte des balises HTML pour transformation en XSLFO
     * @param html text
     * @return xslfo text
     */
	private static String _convertHTML2XSLFOUnBlock(String html) {
		return _convertHTML2XSLFOUnBlock(html, false);
	}
	
	
	private static String _cleanBRInList(String html){
		NSArray t = NSArray.componentsSeparatedByString(html, "<ul>");
		// Pour justification. enleve les br (\n) entre ul et li
		for (int i=0; i< t.count()-1; i++) { //Retire les BR avant la balise
			String text = (String)t.objectAtIndex(i);
			
			int index = text.lastIndexOf("[BR]");
			
			if (index >-1) {
				text = text.substring(0, index)+text.substring(index+4,text.length());
				NSMutableArray newT = new NSMutableArray(t);
				newT.replaceObjectAtIndex(text,i);
				t = newT;
			}
		}
		
		//System.err.println("------------------> html avant ul:"+ html);
		if (t.count()>1){
			for (int i=1; i< t.count(); i++) { //Retire les BR apres la balise
				String text = (String)t.objectAtIndex(i);
				
	
				int indexOL = text.indexOf("<ol>");
				int index = text.indexOf("<li>");
				
				if (index == -1 || (indexOL != -1 && indexOL<index)) {
					text = "[LI2]"+text;
					index = text.length();
				}
				
				NSArray t2 = NSArray.componentsSeparatedByString(text.substring(0,index), "[BR]");
				NSMutableArray newT = new NSMutableArray(t);
				newT.replaceObjectAtIndex(t2.componentsJoinedByString("")+text.substring(index),i);
				t = newT;
			}
		}
		html = t.componentsJoinedByString("<ul>");
		
		t = NSArray.componentsSeparatedByString(html, "<ol>");
		// Pour justification. enleve les br (\n) entre ul et li
		for (int i=0; i< t.count()-1; i++) { //Retire les BR avant la balise
			String text = (String)t.objectAtIndex(i);
			
			int index = text.lastIndexOf("[BR]");
			if (index >-1) {
				text = text.substring(0, index)+text.substring(index+4,text.length());
				NSMutableArray newT = new NSMutableArray(t);
				newT.replaceObjectAtIndex(text,i);
				t = newT;
			}
		}
		
		//System.err.println("------------------> html avant ol:"+ html);
		if (t.count()>1){
			for (int i=1; i< t.count(); i++) { //Retire les BR apres la balise
				String text = (String)t.objectAtIndex(i);
				
				int indexOL = text.indexOf("<ul>");
				int index = text.indexOf("<li>");
				
				if (index == -1 || (indexOL != -1 && indexOL<index)) {
					text = "[LI2]"+text;
					index = text.length();
				}
				
				NSArray t2 = NSArray.componentsSeparatedByString(text.substring(0,index), "[BR]");
				NSMutableArray newT = new NSMutableArray(t);
				newT.replaceObjectAtIndex(t2.componentsJoinedByString("")+text.substring(index),i);
				t = newT;
			}
		}
		html = t.componentsJoinedByString("<ol>");
		
		
		t = NSArray.componentsSeparatedByString(html, "</ul>");
		// Pour justification. enleve les br (\n) entre ul et li
		
		//System.err.println("------------------> html avant /ul:"+ html);
		if (t.count()>1){
			for (int i=1; i< t.count(); i++) {
				String text = (String)t.objectAtIndex(i);
				
	
				int index = text.indexOf("<li>");
				int indexFLI = text.indexOf("</li>");
				int indexUL = text.indexOf("<ul>");
				int indexOL = text.indexOf("<ol>");
				
				if (index != -1 && (indexFLI==-1 || (indexFLI>index && (indexOL == -1 || indexOL>index) && (indexUL == -1 || indexUL>index)))) {
					text = "[/LI2]"+text;
					index = text.indexOf("<li>");
				}
				else {
					if (i<t.count()-1) {
						if (indexUL==-1 && indexOL==-1 && indexFLI==-1) 
							text = "[/LI2]"+text;	
					}
					// Pour saut de ligne dans les textes qui suivent
					int indexP = text.indexOf("<p>");
					index = text.length();
					if (indexP != -1 && indexP < index)
						index = indexP;
				}
				
				NSArray t2 = NSArray.componentsSeparatedByString(text.substring(0,index), "[BR]");
				NSMutableArray newT = new NSMutableArray(t);
				newT.replaceObjectAtIndex(t2.componentsJoinedByString("")+text.substring(index),i);
				t = newT;
			}
		}
		html = t.componentsJoinedByString("</ul>");
		
		
		t = NSArray.componentsSeparatedByString(html, "</ol>");
		// Pour justification. enleve les br (\n) entre ul et li
		
		//System.err.println("------------------> html avant /ol:"+ html);
		if (t.count()>1){
			for (int i=1; i< t.count(); i++) {
				String text = (String)t.objectAtIndex(i);
				
	
				int index = text.indexOf("<li>");
				int indexFLI = text.indexOf("</li>");
				int indexUL = text.indexOf("<ul>");
				int indexOL = text.indexOf("<ol>");
				if (index != -1 && (indexFLI==-1 || (indexFLI>index && (indexOL == -1 || indexOL>index) && (indexUL == -1 || indexUL>index)))) {
					text = "[/LI2]"+text;
					index = text.indexOf("<li>");
				}
				else {
					if (i<t.count()-1) {
						if (indexUL==-1 && indexOL==-1 && indexFLI==-1) 
							text = "[/LI2]"+text;	
					}
					// Pour saut de ligne dans les textes qui suivent
					int indexP = text.indexOf("<p>");
					index = text.length();
					if (indexP != -1 && indexP < index)
						index = indexP;
				}
				
				NSArray t2 = NSArray.componentsSeparatedByString(text.substring(0,index), "[BR]");
				NSMutableArray newT = new NSMutableArray(t);
				newT.replaceObjectAtIndex(t2.componentsJoinedByString("")+text.substring(index),i);
				t = newT;
			}
		}
		html = t.componentsJoinedByString("</ol>");
		
		t = NSArray.componentsSeparatedByString(html, "</li>");
		// Pour justification. enleve les br (\n) entre ul et li
		
		//System.err.println("------------------> html avant /li:"+ html);
		if (t.count() > 1) {
			for (int i = 1; i < t.count(); i++) {
				String text = (String) t.objectAtIndex(i);

				int indexLI = text.indexOf("<li>");
				int indexUL = text.indexOf("</ul>");
				int indexOL = text.indexOf("</ol>");
				int index = (indexLI != -1 && (indexUL == -1 || indexLI < indexUL) ? indexLI : indexUL);
				index = (index != -1 && (indexOL == -1 || index < indexOL) ? index : indexOL);
				//if (index != -1 && (indexLI == -1 || (indexLI > index && (indexUL == -1 || indexUL > index) && (indexOL == -1 || indexOL > index)))) {
					NSArray t2 = NSArray.componentsSeparatedByString(text.substring(0, index), "[BR]");
					NSMutableArray newT = new NSMutableArray(t);
					newT.replaceObjectAtIndex(t2.componentsJoinedByString("") + text.substring(index), i);
					t = newT;
				//}
			}
		}
		html = t.componentsJoinedByString("</li>");
		
		return html;
	}
	
    /**
     * @author NIC
     * @since 20.04.2005 création
     * Prise en compte des balises HTML pour transformation en XSLFO
     * @param html text
     * @return xslfo text
     */
	private static String _convertHTML2XSLFOUnBlock(String html, boolean justify) {
		// quelques reference : www-128.ibm.com/developerworks/library/x-xslfo2app/
		String xslfo = html + " "; // pour les balises de fin
		
		if (xslfo != null && xslfo.length() > 0) {
			//System.out.println("####HTML " + html);
			// remplacement des balises par du text
			// saut de ligne
//			NSArray t =NSArray.componentsSeparatedByString(xslfo, "<BR>");
//			xslfo = t.componentsJoinedByString("");
//			t =NSArray.componentsSeparatedByString(xslfo, "<br/>");
//			xslfo = t.componentsJoinedByString("");
//			t =NSArray.componentsSeparatedByString(xslfo, "\n");
//			xslfo = t.componentsJoinedByString("[BR]");

			NSArray t =NSArray.componentsSeparatedByString(xslfo, "<BR>\n");
			xslfo = t.componentsJoinedByString("[BR]");
			t =NSArray.componentsSeparatedByString(xslfo, "<br/>");
			xslfo = t.componentsJoinedByString("");
            t =NSArray.componentsSeparatedByString(xslfo, "<br />\r\n");
            xslfo = t.componentsJoinedByString("[BR]");
            t =NSArray.componentsSeparatedByString(xslfo, "<br />\r");
            xslfo = t.componentsJoinedByString("[BR]");
            t =NSArray.componentsSeparatedByString(xslfo, "<br />\n");
            xslfo = t.componentsJoinedByString("[BR]");
            t =NSArray.componentsSeparatedByString(xslfo, "<br />");
            xslfo = t.componentsJoinedByString("[BR]");
			t =NSArray.componentsSeparatedByString(xslfo, "\n");
			xslfo = t.componentsJoinedByString("[BR]");
			t =NSArray.componentsSeparatedByString(xslfo, "<BR>");
			xslfo = t.componentsJoinedByString("[BR]");		
			t =NSArray.componentsSeparatedByString(xslfo, "<br>");
			xslfo = t.componentsJoinedByString("[BR]");	
			
			try {
			xslfo = _cleanBRInList(xslfo);
			} catch (Exception e) {
				System.err.println("===============> _cleanBRInList exception");
			}
			// parfois un nbsp se cache
			t =NSArray.componentsSeparatedByString(xslfo, "&nbsp;");
			xslfo = t.componentsJoinedByString("[nbsp]");
			
			// strong
			t =NSArray.componentsSeparatedByString(xslfo, "<strong>");
			xslfo = t.componentsJoinedByString("[STRONG]");
			t =NSArray.componentsSeparatedByString(xslfo, "</strong>");
			xslfo = t.componentsJoinedByString("[/STRONG]");
			// strong
			t =NSArray.componentsSeparatedByString(xslfo, "<b>");
			xslfo = t.componentsJoinedByString("[B]");
			t =NSArray.componentsSeparatedByString(xslfo, "</b>");
			xslfo = t.componentsJoinedByString("[/B]");
			t =NSArray.componentsSeparatedByString(xslfo, "<B>");
			xslfo = t.componentsJoinedByString("[B]");
			t =NSArray.componentsSeparatedByString(xslfo, "</B>");
			xslfo = t.componentsJoinedByString("[/B]");
			// em
			t =NSArray.componentsSeparatedByString(xslfo, "<em>");
			xslfo = t.componentsJoinedByString("[ITALIC]");
			t =NSArray.componentsSeparatedByString(xslfo, "</em>");
			xslfo = t.componentsJoinedByString("[/ITALIC]");
			// i
			t =NSArray.componentsSeparatedByString(xslfo, "<i>");
			xslfo = t.componentsJoinedByString("[ITALIC]");
			t =NSArray.componentsSeparatedByString(xslfo, "</i>");
			xslfo = t.componentsJoinedByString("[/ITALIC]");
			// u
			t =NSArray.componentsSeparatedByString(xslfo, "<u>");
			xslfo = t.componentsJoinedByString("[UNDERLINE]");
			t =NSArray.componentsSeparatedByString(xslfo, "</u>");
			xslfo = t.componentsJoinedByString("[/UNDERLINE]");
			// div
			t =NSArray.componentsSeparatedByString(xslfo, "<div>");
			xslfo = t.componentsJoinedByString("");
			t =NSArray.componentsSeparatedByString(xslfo, "<div align=\"left\">");
			xslfo = t.componentsJoinedByString("");
			t =NSArray.componentsSeparatedByString(xslfo, "<div align=\"center\">");
			xslfo = t.componentsJoinedByString("");
			t =NSArray.componentsSeparatedByString(xslfo, "<div align=\"right\">");
			xslfo = t.componentsJoinedByString("");
			t =NSArray.componentsSeparatedByString(xslfo, "<div align=\"left\" />");
			xslfo = t.componentsJoinedByString("");
			t =NSArray.componentsSeparatedByString(xslfo, "<div align=\"center\" />");
			xslfo = t.componentsJoinedByString("");
			t =NSArray.componentsSeparatedByString(xslfo, "<div align=\"right\" />");
			xslfo = t.componentsJoinedByString("");
			t =NSArray.componentsSeparatedByString(xslfo, "<div align=\"left\"/>");
			xslfo = t.componentsJoinedByString("");
			t =NSArray.componentsSeparatedByString(xslfo, "<div align=\"center\"/>");
			xslfo = t.componentsJoinedByString("");
			t =NSArray.componentsSeparatedByString(xslfo, "<div align=\"right\"/>");
			xslfo = t.componentsJoinedByString("");
			
			t =NSArray.componentsSeparatedByString(xslfo, "</div>");
			xslfo = t.componentsJoinedByString("");
			// p
			//t =NSArray.componentsSeparatedByString(xslfo, "<p>");
			//xslfo = t.componentsJoinedByString("[BR]");
			t =NSArray.componentsSeparatedByString(xslfo, "<p>");
			xslfo = t.componentsJoinedByString("");
			
			//xslfo = t.componentsJoinedByString("[PARA]");
			t =NSArray.componentsSeparatedByString(xslfo, "</p>");
			xslfo = t.componentsJoinedByString("");
			//xslfo = t.componentsJoinedByString("[/PARA]");
			// <h1> et plus
			for (int ih=1;ih<20;ih++) {
				if (xslfo.indexOf("<h"+ih)>-1) {
					xslfo = removeAttributFromTag("h"+ih,xslfo); // suppression des attributs du tag qui empêche sa détection
					String hStr = "<h"+ih+">";
					t =NSArray.componentsSeparatedByString(xslfo, hStr);
					xslfo = t.componentsJoinedByString("");
				}
				String hEnd = "</h"+ih+">";
				if (xslfo.indexOf(hEnd)>-1) {
					t =NSArray.componentsSeparatedByString(xslfo, hEnd);
					xslfo = t.componentsJoinedByString("");
				}
			}
			// ul
			xslfo = removeAttributFromTag("ul",xslfo); // les ul dans les tableau peuvent avoir des attributs
			
			
			
			t = NSArray.componentsSeparatedByString(xslfo, "<ul>");
			xslfo = t.componentsJoinedByString("[UL]");
			
			xslfo += " "; //Pour avoir 2 elements dans tableau des /ul
			t = NSArray.componentsSeparatedByString(xslfo, "</ul>");
			xslfo = t.componentsJoinedByString("[/UL]");
			// li
			xslfo = removeAttributFromTag("li",xslfo); // les li dans les tableau peuvent avoir des attributs
			t =NSArray.componentsSeparatedByString(xslfo, "<li>");
			xslfo = t.componentsJoinedByString("[LI]");
	
			t =NSArray.componentsSeparatedByString(xslfo, "</li>");
			xslfo = t.componentsJoinedByString("[/LI]");			
			// ol
			xslfo = removeAttributFromTag("ol",xslfo); // les ol dans les tableau peuvent avoir des attributs
			t =NSArray.componentsSeparatedByString(xslfo, "<ol>");

			xslfo = t.componentsJoinedByString("[OL]");
			xslfo += " "; 

			t =NSArray.componentsSeparatedByString(xslfo, "</ol>");
			xslfo = t.componentsJoinedByString("[/OL]");
			
			// exposant <sup>
			t =NSArray.componentsSeparatedByString(xslfo, "<sup>");
			xslfo = t.componentsJoinedByString("[SUP_EXP]"); // SUP utilise pour caractere <
			t =NSArray.componentsSeparatedByString(xslfo, "</sup>");
			xslfo = t.componentsJoinedByString("[/SUP_EXP]");
			
			// exposant <sub>
			t =NSArray.componentsSeparatedByString(xslfo, "<sub>");
			xslfo = t.componentsJoinedByString("[SUB_EXP]"); // pour garder coherance avec sub
			t =NSArray.componentsSeparatedByString(xslfo, "</sub>");
			xslfo = t.componentsJoinedByString("[/SUB_EXP]");
			
			// font color
			while (xslfo.indexOf("<font color=") >= 0) {
				int i = xslfo.indexOf("<font color=");
				if (i >= 0) {
					xslfo = xslfo.substring(0, i) + "[FONT " + xslfo.substring(i+5, xslfo.indexOf(">", i+5))+"]"+ xslfo.substring(xslfo.indexOf(">", i+5)+1, xslfo.length());
					t =NSArray.componentsSeparatedByString(xslfo, "</font>");
					xslfo = t.componentsJoinedByString("[/FONT]");
				}
			}
			// background color - Correction de Damien du 11.01.2008
			while (xslfo.indexOf("<font style=") >= 0) {
				int i = xslfo.indexOf("<font style=");
				int j = xslfo.indexOf(">",i);
				if (i >= 0) {
					String color = "000000";
					
					int k = xslfo.substring(i, j).toLowerCase().indexOf("background-color");
					if (k >= 0) {	
						if (xslfo.substring(k+i, j).indexOf("#")>=0) {
							k = xslfo.substring(k+i, j).indexOf("#")+k+i;
							color = xslfo.substring(k+1, k+7);
						}
					}

					xslfo = xslfo.substring(0, i) + "[BACKGROUND " + color +"]"+ xslfo.substring(j+1, xslfo.length());

					t =NSArray.componentsSeparatedByString(xslfo, "</font>");
					xslfo = t.componentsJoinedByString("[/FONT]");
				}
			}
			// font size
			while (xslfo.indexOf("<font size=") >= 0) {
				int i = xslfo.indexOf("<font size=");
				if (i >= 0) {
					int size = 0;
					try {
							size=Integer.valueOf(xslfo.substring(i+12, xslfo.indexOf("\">", i+12))).intValue() * 5;	
					} catch (Exception e) {
							size=-1;
					}
					
					xslfo = xslfo.substring(0, i) + "[FONT font-size=\"" + (size>=0?size +"pt":"")+"\"]"+ xslfo.substring(xslfo.indexOf(">", i+11)+1, xslfo.length());
					
					t =NSArray.componentsSeparatedByString(xslfo, "</font>");
					xslfo = t.componentsJoinedByString("[/FONT]");
				}
			}
			// font face
			while (xslfo.indexOf("<font face=") >= 0) {
				int i = xslfo.indexOf("<font face=");
				if (i >= 0) {
					xslfo = xslfo.substring(0, i) + "[FONT font-family=" + xslfo.substring(i+11, xslfo.indexOf(">", i+11)) +"]"+ xslfo.substring(xslfo.indexOf(">", i+11)+1, xslfo.length());
					t =NSArray.componentsSeparatedByString(xslfo, "</font>");
					xslfo = t.componentsJoinedByString("[/FONT]");
				}
			}
			
			// indentation
			t =NSArray.componentsSeparatedByString(xslfo, "<blockquote dir=\"ltr\" style=\"MARGIN-RIGHT: 0px\">");
			xslfo = t.componentsJoinedByString("[INDENT]");
			t =NSArray.componentsSeparatedByString(xslfo, "</blockquote>");
			xslfo = t.componentsJoinedByString("[/INDENT]");			
			
			//les < et >
			t = NSArray.componentsSeparatedByString(xslfo, "< ");
			xslfo = t.componentsJoinedByString("[INF] ");
//			t = NSArray.componentsSeparatedByString(xslfo, "&lt;");
//			xslfo = t.componentsJoinedByString("[INF]");
//			
			t = NSArray.componentsSeparatedByString(xslfo, " >");
			xslfo = t.componentsJoinedByString(" [SUP]");
//			t = NSArray.componentsSeparatedByString(xslfo, "&gt;");
//			xslfo = t.componentsJoinedByString("[SUP]");

            //System.out.println("####TEMP_HTML " + xslfo);
			// HTML to TEXT
			try {
				// gestion du &rsquo;
				t = NSArray.componentsSeparatedByString(xslfo, "&rsquo;");
				xslfo = t.componentsJoinedByString("'");
				// gestion du &oelig;
				t = NSArray.componentsSeparatedByString(xslfo, "&oelig;");
				xslfo = t.componentsJoinedByString("&#339;");
				// convert html to txt
                System.out.println("####Html2Text BEFORE " + xslfo);
				xslfo = Html2Text.convert(xslfo);
                
				System.out.println("####Html2Text AFTER " + xslfo);
				// pour les balises de fin
				xslfo += " "; 		

			} catch (IOException e) {
				e.printStackTrace();
			}
	
            //les < et >
          t = NSArray.componentsSeparatedByString(xslfo, "<");
          xslfo = t.componentsJoinedByString("[INF]");
          t = NSArray.componentsSeparatedByString(xslfo, "&lt;");
          xslfo = t.componentsJoinedByString("[INF]");
          
          t = NSArray.componentsSeparatedByString(xslfo, ">");
          xslfo = t.componentsJoinedByString("[SUP]");
          t = NSArray.componentsSeparatedByString(xslfo, "&gt;");
          xslfo = t.componentsJoinedByString("[SUP]");

            
            
			// cas de figure pour liste numerotee venant de word:
			// <ul><ol><li>texte</li><li>texte</li>...etc</ol></ul>
			if (xslfo.indexOf("[UL]")>-1 && xslfo.indexOf("[OL]")>-1) {
				xslfo = correctionNumListFromWord(xslfo);
			}
			
			// saut de ligne
			t =NSArray.componentsSeparatedByString(xslfo, "[BR]");
			xslfo = t.componentsJoinedByString("\n");
			// les nbsp
			t =NSArray.componentsSeparatedByString(xslfo, "[nbsp]");
			xslfo = t.componentsJoinedByString(" ");
			// strong
			t =NSArray.componentsSeparatedByString(xslfo, "[STRONG]");
			xslfo = t.componentsJoinedByString("<fo:inline font-weight=\"bold\">");
			t =NSArray.componentsSeparatedByString(xslfo, "[/STRONG]");
			xslfo = t.componentsJoinedByString("</fo:inline>");
			// strong
			t =NSArray.componentsSeparatedByString(xslfo, "[B]");
			xslfo = t.componentsJoinedByString("<fo:inline font-weight=\"bold\">");
			t =NSArray.componentsSeparatedByString(xslfo, "[/B]");
			xslfo = t.componentsJoinedByString("</fo:inline>");
			// em
			t =NSArray.componentsSeparatedByString(xslfo, "[ITALIC]");
			xslfo = t.componentsJoinedByString("<fo:inline font-style=\"italic\">");
			t =NSArray.componentsSeparatedByString(xslfo, "[/ITALIC]");
			xslfo = t.componentsJoinedByString("</fo:inline>");
			// u
			t =NSArray.componentsSeparatedByString(xslfo, "[UNDERLINE]");
			xslfo = t.componentsJoinedByString("<fo:inline text-decoration=\"underline\">");
			t =NSArray.componentsSeparatedByString(xslfo, "[/UNDERLINE]");
			xslfo = t.componentsJoinedByString("</fo:inline>");

			// p
//			t =NSArray.componentsSeparatedByString(xslfo, "[PARA]");
//			xslfo = t.componentsJoinedByString("<fo:block>");
//			t =NSArray.componentsSeparatedByString(xslfo, "[/PARA]");
//			xslfo = t.componentsJoinedByString("</fo:block>");

			// les &
			t = NSArray.componentsSeparatedByString(xslfo, "&");
			xslfo = t.componentsJoinedByString("&#038;");
			//xslfo = t.componentsJoinedByString("&#x0026;");
			
			//les < et >
			t = NSArray.componentsSeparatedByString(xslfo, "[INF]");
			xslfo = t.componentsJoinedByString("&#060;");
			
			t = NSArray.componentsSeparatedByString(xslfo, "[SUP]");
			xslfo = t.componentsJoinedByString("&#062;");
			

			// li
			int nb = 1;
			while (xslfo.indexOf("[LI]") >= 0) {
				int i = xslfo.indexOf("[LI]");
				if (i >= 0) {
					int iOL = xslfo.indexOf("[/OL]", i);
					int inextOL = xslfo.indexOf("[OL]", i);
					int iUL = xslfo.indexOf("[/UL]", i);
					if (inextOL >= 0 && inextOL < iOL)
						nb = 1;
					boolean isInOL = iOL >= 0 && ( iUL == -1 || (iOL < iUL) );
					String sep = nb+".";
					if (!isInOL)
						sep = "&#x2022;";
					else
						nb++;
					xslfo = xslfo.substring(0, i) + "<fo:list-item><fo:list-item-label end-indent=\"label-end()\"><fo:block>"+sep+"</fo:block></fo:list-item-label><fo:list-item-body start-indent=\"body-start()\"><fo:block "+(justify?"text-align=\"justify\" text-align-last=\"left\"":"")+">" + xslfo.substring(i+4, xslfo.length());
				}
			}
			
			t =NSArray.componentsSeparatedByString(xslfo, "[LI2]");
			xslfo = t.componentsJoinedByString("<fo:list-item><fo:list-item-label end-indent=\"label-end()\"><fo:block></fo:block></fo:list-item-label><fo:list-item-body start-indent=\"body-start()\"><fo:block>");
			t =NSArray.componentsSeparatedByString(xslfo, "[/LI2]");
			xslfo = t.componentsJoinedByString("</fo:block></fo:list-item-body></fo:list-item>");
			
			
			//t =NSArray.componentsSeparatedByString(xslfo, "[LI]");
			//xslfo = t.componentsJoinedByString("<fo:list-item><fo:list-item-label end-indent=\"label-end()\"><fo:block>&#x2022;</fo:block></fo:list-item-label><fo:list-item-body start-indent=\"body-start()\"><fo:block>");
			t =NSArray.componentsSeparatedByString(xslfo, "[/LI]");
			xslfo = t.componentsJoinedByString("</fo:block></fo:list-item-body></fo:list-item>");
			// ul
			t =NSArray.componentsSeparatedByString(xslfo, "[UL]");
			xslfo = t.componentsJoinedByString("<fo:list-block provisional-distance-between-starts=\"5mm\" provisional-label-separation=\"15mm\">");
			t =NSArray.componentsSeparatedByString(xslfo, "[/UL]");
			xslfo = t.componentsJoinedByString("</fo:list-block>");
			// ol
			t =NSArray.componentsSeparatedByString(xslfo, "[OL]");
			xslfo = t.componentsJoinedByString("<fo:list-block provisional-distance-between-starts=\"5mm\" provisional-label-separation=\"15mm\">");
			t =NSArray.componentsSeparatedByString(xslfo, "[/OL]");
			xslfo = t.componentsJoinedByString("</fo:list-block>");
			
			// sup
			t =NSArray.componentsSeparatedByString(xslfo, "[SUP_EXP]");
			xslfo = t.componentsJoinedByString("<fo:inline vertical-align=\"super\" font-size=\"75%\">");
			t =NSArray.componentsSeparatedByString(xslfo, "[/SUP_EXP]");
			xslfo = t.componentsJoinedByString("</fo:inline>");
			
			// sub
			t =NSArray.componentsSeparatedByString(xslfo, "[SUB_EXP]");
			xslfo = t.componentsJoinedByString("<fo:inline vertical-align=\"sub\" font-size=\"75%\">");
			t =NSArray.componentsSeparatedByString(xslfo, "[/SUB_EXP]");
			xslfo = t.componentsJoinedByString("</fo:inline>");
			
			// font color
			while (xslfo.indexOf("[FONT color=") >= 0) {
				int i = xslfo.indexOf("[FONT color=");
				if (i >= 0) {
					xslfo = xslfo.substring(0, i) + "<fo:inline " + xslfo.substring(i+5, xslfo.indexOf("]", i+5))+">"+ xslfo.substring(xslfo.indexOf("]", i+5)+1, xslfo.length());
					t =NSArray.componentsSeparatedByString(xslfo, "[/FONT]");
					xslfo = t.componentsJoinedByString("</fo:inline>");
				}
			}			
			// background color
			while (xslfo.indexOf("[BACKGROUND ") >= 0) {
				int i = xslfo.indexOf("[BACKGROUND ");
				if (i >= 0) {
					String color = xslfo.substring(i+12, i+18);
					xslfo = xslfo.substring(0, i) + "<fo:inline background-color=\"#" + color +"\">"+ xslfo.substring(xslfo.indexOf("]", i+5)+1, xslfo.length());
					t =NSArray.componentsSeparatedByString(xslfo, "[/FONT]");
					xslfo = t.componentsJoinedByString("</fo:inline>");
				}
			}
			// font size
			while (xslfo.indexOf("[FONT font-size=") >= 0) {
				int i = xslfo.indexOf("[FONT font-size=");
				if (i >= 0) {
					xslfo = xslfo.substring(0, i) + "<fo:inline " + xslfo.substring(i+5, xslfo.indexOf("]", i+5))+">"+ xslfo.substring(xslfo.indexOf("]", i+5)+1, xslfo.length());
					t =NSArray.componentsSeparatedByString(xslfo, "[/FONT]");
					xslfo = t.componentsJoinedByString("</fo:inline>");
				}
			}			
			// font face
			while (xslfo.indexOf("[FONT font-family=") >= 0) {
				int i = xslfo.indexOf("[FONT font-family=");
				if (i >= 0) {
					String str = xslfo.substring(i+5, xslfo.indexOf("]", i+5));
					// font-size assoçié
					int j = str.indexOf(" size=\"");
		
					if (j >= 0) {
						t = NSArray.componentsSeparatedByString(str, " size=\"");
						str = t.componentsJoinedByString(" font-size=\"");
						int k = str.indexOf("\"", j+12);
						int size = new Integer(str.substring(j+12, k)).intValue()*5;
						
						// Modification de la hauteur du dernier block
						/*int lastblockIndex = xslfo.substring(0,i).lastIndexOf("<fo:block");
						System.err.println("substring 1"+xslfo.substring(0, lastblockIndex+9));
						System.err.println("substring 2"+xslfo.substring(lastblockIndex+9, xslfo.length()));
						if (lastblockIndex != -1) {
							String ajout = " line-height=\""+size+"pt\" ";
							xslfo = xslfo.substring(0, lastblockIndex+9)+ ajout +xslfo.substring(lastblockIndex+9, xslfo.length());
							i = i + ajout.length();
						}*/
						//System.out.println("==> k " + k + " j " + j + " " + str);
						str = str.substring(0, j+12) + size + "pt\"";
						str += ">"; 
					} else
						str += ">";
					//
					xslfo = xslfo.substring(0, i) + "<fo:inline " + str + xslfo.substring(xslfo.indexOf("]", i+5)+1, xslfo.length());
					t =NSArray.componentsSeparatedByString(xslfo, "[/FONT]");
					xslfo = t.componentsJoinedByString("</fo:inline>");
				}
			}			
			// indentation
			t =NSArray.componentsSeparatedByString(xslfo, "[INDENT]");
			xslfo = t.componentsJoinedByString("<fo:block white-space-collapse=\"false\" start-indent=\"1cm\">");
			t =NSArray.componentsSeparatedByString(xslfo, "[/INDENT]");
			xslfo = t.componentsJoinedByString("</fo:block>");
			
   		
		}
		//System.out.println("####XSLFO " + xslfo);
		//return "<fo:block white-space-collapse=\"false\">"+xslfo+"</fo:block>";
		return xslfo;
	}
 	
	/**
	 * traitement table par table
	 * @param html
	 * @return
	 */
    private static String _convertHTML2XSLFOWithTable(String html) {
    	int idx=0;
    	String r0="";
    	String htmlBk=html;
    	while (html.toLowerCase().indexOf("<table")>-1) {
    		int idxR1=-1;
    		String subR1="*subR1*";
    		String r1 = "*r1*";
    		int idxR2 = -1;
    		String subR2 = "*subR2*";
    		String r2 = "*r2*";
    		String r3 = "*r3*";
    		
    		try {
				//String r1 = _convertHTML2XSLFOUnBlock(html.substring(idx, html.toLowerCase().indexOf("<table")));
				// section avant la table
				idxR1=html.toLowerCase().indexOf("<table");
				subR1=html.substring(idx,idxR1);
				r1 = _convertHTML2XSLFOUnBlock(subR1);
				
				//String r2 = ConvertionJDom(html.substring(html.toLowerCase().indexOf("<table"), html.toLowerCase().indexOf("</table>") + 8));
				idxR2 = html.toLowerCase().indexOf("</table>") + 8;
				subR2 = html.substring(idxR1, idxR2);
				r2 = ConvertionJDom(subR2);
				
				//String r3 = html.substring(html.toLowerCase().indexOf("</table>") + 8);
				r3 = html.substring(idxR2);
				if (r3.toLowerCase().indexOf("<table")<0) {
					r3 = _convertHTML2XSLFOUnBlock(r3);
				}
			} catch (RuntimeException e) {
				System.out.println("Error in _convertHTML2XSLFOWithTable:");
				System.out.println("html origine=\"" + htmlBk + "\"\nhtml transforme=\"" + html + "\"");
				System.out.println("idxR1=" + idxR1 + "\nsubR1=\"" + subR1 + "\"\nr1=\"" + r1 + "\"\nidxR2=" + idxR2 + "\nsubR2=\"" + subR2 + "\"\nr2=\"" + r2 + "\"\nr3=\"" + r3 + "\"");
				e.printStackTrace();
			}
    		html = r0 + r1 + r2 + r3;
    		r0 = r0 + r1 + r2;
    		idx = (r0).length();
    	}
    	//"<fo:block white-space-collapse=\"false\">"+_convertHTML2XSLFOUnBlock(html)+"</fo:block>";
    	return "<fo:block white-space-collapse=\"false\">" + html +"</fo:block>";
    }
    
    /**
     * preparation convertion table HTML en table FO
     * @param HTMLString
     * @return
     */
    private static String ConvertionJDom(String HTMLString) {
    	// quelques precaution avant de passer en Jdom
    	// les caractere code en "&xyz;" ne passe pas dans Jdom 
		String r = replaceStringByString(HTMLString,"&","%_#_%");
		// uniformisation du tag br "/" obligatoire dans jDom
		r = replaceStringByString(r,"<br>","<br/>");
		r = replaceStringByString(r,"<BR>","<br/>");
		// suppression des \n, car en principe ne sert qu'a formatter le code HTML
		r = replaceStringByString(r,"\r\n","");
		r = removeExtraSpaceBetwenNode(r);
		try {
//			root element arbitraire
			r = "<dual>" + r +"</dual>" ;
	    	 // précaution supplementaire, certain code caractère pose probleme comme &nbsp; donc remplace temporairement par autre chose et le resituera après avec un &#160;
	    	r = replaceStringByString(r,"%_#_%nbsp;","_XQQ_NBSP_QQX_");
			r = convertHTMLTable(r);
			r = replaceStringByString(r,"_XQQ_NBSP_QQX_","&#160;");
			System.out.println(r);
		} catch (Exception e) {
			// en cas d'echec retourne le string d'origine
			e.printStackTrace();
			r = _convertHTML2XSLFO(HTMLString, true, false);
		}
    	return r;
    }
    
    /**
     * convertion table HTML en table FO
     * @param xmlString
     * @return
     * @throws Exception
     */
    private static String convertHTMLTable(String xmlString) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Document xmlObj = builder.build(new StringReader(xmlString));
		// traitement des colonnes
		Vector countCells=new Vector(); // nombre de colonne pour chaque ligne
		Vector widthCells=new Vector(); // largeur des colonnes pour chaque ligne
		boolean hasBorder=false;
		// si la table a une bordure (par simplification est applique la meme bordure a la table et au cellules)
		String borderAttribut="";
		String borderAttributValue="";
		
		// index de la ligne en cours de traitement
		int idxLine=-1;
		// recupère les éléments root dans un vector
		List elements = xmlObj.getRootElement().getChildren();
		// tous les elements de xmlObj seront parcouru par ce vecteur
		Vector v = new Vector();
		// pour le cas si l'element "tbody" n'existe pas
		Vector vTR= new Vector();
		
		Element table=new Element("xxx");
		boolean isThereTbody=false;
		for (int i=0;i<elements.size();i++) {
			Element e = (Element) elements.get(i);
			v.add(e);
		}
		int idx=0;
		while (idx<v.size()) {
			Element e = (Element) v.get(idx);
			// si l'element comporte des sous element, ils sont reference dans le vecteur v
			//if (e.hasChildren()) {
			if (!e.getChildren().isEmpty()) {
				List ch = e.getChildren();
				for (int j=0;j<ch.size();j++) {
					v.add(ch.get(j));
				}
			}
			if (dico().get(e.getName().toLowerCase())!=null) {
				// si table
				if (e.getName().toLowerCase().equalsIgnoreCase("table")) {
					table=(Element)e;
					if (hasAttribute(e,"border")) {
						hasBorder=true;
						borderAttribut="border";
						borderAttributValue=attributeValue(e, "border");
						borderAttributValue = getReplacingAttributeLeadingTxt(borderAttribut) + borderAttributValue + getReplacingAttributeTrailingTxt(borderAttribut);
					}
				}
				// si tbody
				if (e.getName().toLowerCase().equalsIgnoreCase("tbody")) {
					isThereTbody=true;
				}
				// si nlle ligne
				if (e.getName().toLowerCase().equalsIgnoreCase("tr")) {
					idxLine++;
					// numerote la ligne
					e.setAttribute("idxTR",Integer.toString(idxLine));
					// prepare description de la ligne: nombre de cellule et largeur
					countCells.add("0");
					widthCells.add(new Vector());
					vTR.add(idxLine,e); // pour le cas si l'element "tbody" n'existe pas
				}
				// si cellule
				if (e.getName().toLowerCase().equalsIgnoreCase("td")) {
					Element p = e.getParent();
					// compte les cellule et leur largeur
					int idxTr = Integer.parseInt((p.getAttribute("idxTR")).getValue());
					int n = Integer.parseInt((String) countCells.get(idxTr));
					// colspan et width
					Attribute w = e.getAttribute("width");
					String widthValue = "";
					if (w!=null) {widthValue=w.getValue();}
					int colspan=1;
					if (e.getAttribute("colspan")!=null) {
						colspan = Integer.parseInt(e.getAttributeValue("colspan"));
					}
					for (int nspan=0;nspan<colspan;nspan++) {
						n++;
						if (w!=null) {
							Vector sb = (Vector) widthCells.elementAt(idxTr);
							try {
								int ww = (Integer.parseInt(widthValue))/colspan;
								sb.add(Integer.toString(ww));
							} catch (NumberFormatException e1) {
								sb.add(w.getValue());
							}
						}
					}
					countCells.setElementAt(Integer.toString(n), idxTr);

					// contenu de la cellule convertit par _convertHTML2XSLFOUnBlock
					// le contenu de la cellule doit contenu dans un fo:block:
					//      - nouvel element avec le contenu de la cellule
					//      - convertion du contenu
					//      - le nouvel element remplace le contenu de la cellule
					Element cell = (Element) e.clone();
					cell.setName("fo_block");
					List rmv = cell.getAttributes();
					while(!rmv.isEmpty()) {
						cell.removeAttribute((Attribute)rmv.get(0));
					}
					
					// text alignement dans les cellule word => HTML: <div align="center">;  <div align="right"> ; gauche et justifié = <div>
					List divEl=cell.getChildren("div");
					if (divEl!=null) {
						for (int divI=0;divI<divEl.size();divI++) {
							Element divElement = (Element) divEl.get(divI);
							// alignement
							String alignmt = null;
							if (divElement.getAttribute("align")!=null) {
								if (divElement.getAttributeValue("align").equalsIgnoreCase("center")) {
									alignmt = "center";
								} else if (divElement.getAttributeValue("align").equalsIgnoreCase("right")) {
									alignmt = "end";
								}
							}
							// supprime attributs
							List rmvAtt = divElement.getAttributes();
							while (!rmvAtt.isEmpty()) {
								divElement.removeAttribute((Attribute) rmvAtt.get(0));
							}
							if (alignmt!=null) {
								divElement.setAttribute("text-align", alignmt);
							}
						}
					}
					
					boolean gotDiv=false;
					if (divEl!=null) {
						gotDiv=true;
						List allChild = cell.getChildren();
						for (int allI=0;allI<allChild.size();allI++) {
							Element child = (Element) allChild.get(allI);
							if (child.getName().equalsIgnoreCase("div")) {
								child.setName("fo_block");
							}
						}
					}
					
					String xr = convertHTMLTextInCellTable(cell);
					if (gotDiv) {
						// reparation
						xr = replaceStringByString(xr, "&#060;/fo_block&#062;", "</fo_block>");
						xr = replaceStringByString(xr, "&#060;fo_block&#062;", "<fo_block>");
						xr = replaceStringByString(xr, "&#060;fo_block text-align=\"center\"&#062;", "<fo_block text-align=\"center\">");
						xr = replaceStringByString(xr, "&#060;fo_block text-align=\"end\"&#062;", "<fo_block text-align=\"end\">");
					}
					cell.removeChildren();
					cell.addContent(xr.trim());
					e.removeChildren();
					e.addContent(cell);
				}
				// convertit le tag html en tag FO
				e.setName((String) dico().get(e.getName().toLowerCase()));
			}
			// convertit les attributs
			convertAttribut(e);
			// si cellule rajoute un espace si necessaire
			if (e.getName().equals("fo_table-cell")) {
				if (e.getAttribute("padding")==null) {
					e.setAttribute("padding", "2pt");
				}
			}
			idx++;
		}
		if (hasBorder) {
			// donne la meme bordure a la table est au cellule par simplication
			for (int idxBrd=0;idxBrd<v.size();idxBrd++) {
				Element e = (Element) v.get(idxBrd);
				if (e.getName().toLowerCase().equalsIgnoreCase("fo_table-cell")) {
					if (e.getAttribute("border")==null) {
						e.setAttribute(borderAttribut, borderAttributValue);
					}
				}
			}
		}
		
		// border des cellule
		
		// definition des colonnes
		// quel la la ligne qui compte le plus de colonne
		int idxV=0;
		for (int k=0;k<countCells.size();k++) {
			int a = Integer.parseInt((String) countCells.elementAt(idxV));
			int b = Integer.parseInt((String) countCells.elementAt(k));
			if (b>a) {idxV = k;}
		}
		int nbCol=Integer.parseInt((String) countCells.elementAt(idxV));
		Vector wdth=(Vector) widthCells.elementAt(idxV);
		// ajoute la definition column-width
		for (int c=0;c<nbCol;c++) {
			//<fo:table-column column-width="proportional-column-width(409)" />
			Element defCol=new Element("fo_table-column");
			defCol.setAttribute("column-width", "proportional-column-width(" + wdth.elementAt(c) + ")");
			table.addContent(defCol);
		}
		
		// place les declaration de colonne en tete en repoussant les autres elements ()
		List movEl = table.getChildren();
		for (int m=0;m<movEl.size();m++) {
			Element el=(Element) movEl.get(m);
			if (!el.getName().equals("fo_table-column")) {
				table.addContent(el.detach());
			}
		}
		
		// rajout du table-body si necessaire et y deplace les elements des lignes
		if (!isThereTbody) {
			Element tbody=new Element("fo_table-body");
			table.addContent(tbody);
			for (int chg=0;chg<vTR.size();chg++) {
				Element el=(Element) vTR.get(chg);
				//System.out.println(((Attribute)el.getAttribute("idxTR")).getValue());
				tbody.addContent(el.detach());
			}
		}
		
		XMLOutputter x = new XMLOutputter();
		String xr=(x.outputString(xmlObj.getRootElement()));
		// restauration des tag fo:inline, fo_block perdu au moment de la conversion du contenu des cellules
		if ((xr.indexOf("&lt;fo_inline")>-1) || (xr.indexOf("&lt;fo_block")>-1)) {
			xr=restoreFOINLINE(xr);
		}
		
		// remise en ordre des tag
		xr = replaceStringByString(xr, "<fo_", "<fo:");
		xr = replaceStringByString(xr, "</fo_", "</fo:");
		xr = replaceStringByString(xr,"%_#_%","&");
		xr = replaceStringByString(xr,"<br />","<br>");
		xr = replaceStringByString(xr,"<br/>","<br>");
		// suppresion de l'element root arbitraire
		xr = xr.substring(xr.indexOf("<dual>")+("<dual>").length(), xr.indexOf("</dual>"));
		//System.out.println("### > XSLFO tableau\n" + xr);
		return xr;
    }
    
    private static String convertHTMLTextInCellTable(Element cell) {
		XMLOutputter x = new XMLOutputter();
		String xr=(x.outputString(cell.getContent()));
		
		//xr = replaceStringByString(xr,"%_#_%","&");
		return convertHTMLTextInCellTable(xr);
    }
    
    private static String convertHTMLTextInCellTable(String xr) {
    	// remise en ordre des caractere code "&xyz;"
		xr = replaceStringByString(xr,"%_#_%","&");
		
		NSArray t =NSArray.componentsSeparatedByString(xr, "<br />");
		xr = t.componentsJoinedByString("<br>");
		t =NSArray.componentsSeparatedByString(xr, "<br/>");
		xr = t.componentsJoinedByString("<br>");
		
		xr = _convertHTML2XSLFOUnBlock(xr);
		// Jdom n'aime pas les nom d'element avec des ":"
		xr = replaceStringByString(xr, "<fo:", "<fo_");
		xr = replaceStringByString(xr, "</fo:", "</fo_");
		return xr;
    }
    
    /**
     * restaure les < et
     * les > perdu au moment du passage du contenu de la cellule dans un element jDom
     * @param s
     * @return
     */
    private static String restoreFOINLINE(String s) {
    	// prevu au debut pour inline, mais etendu a d'autre
//    	String[] sep = {"&lt;fo_inline","&lt;/fo_inline&gt;"};
//    	String[] jnt = {"<fo_inline","</fo_inline>"};
    	String[] sep = {"&lt;fo_","&lt;/fo_"};
    	String[] jnt = {"<fo_","</fo_"};
    	String lgt = "&gt;";
    	for (int a=0;a<sep.length;a++) {
        	NSMutableArray foinline = (NSMutableArray) NSArray.componentsSeparatedByString(s,sep[a]);
        	for (int i=1;i<foinline.count();i++) {
        		String c = (String) foinline.objectAtIndex(i);
        		if (c.indexOf(lgt)>-1) {
        			c = c.substring(0, c.indexOf(lgt)) + ">" + c.substring(c.indexOf(lgt) + lgt.length());
        			foinline.replaceObjectAtIndex(c, i);
        		}
        	}
        	s = foinline.componentsJoinedByString(jnt[a]);
    	}
    	return s;
    }
    
    /**
     * retourne txtHtml après avoir supprime les attribut d'un tag tagName (le nom ne doit pas comportorter les &lt; et &gt;)
     * @param tagName
     * @param txtHtml
     * @return
     */
    private static String removeAttributFromTag(String tagName, String txtHtml) {
    	if (txtHtml.indexOf("<"+tagName)>-1) {
    		NSMutableArray a = (NSMutableArray) NSArray.componentsSeparatedByString(txtHtml,"<"+tagName);
    		for (int i=1;i<a.count();i++) {
    			String c = (String) a.objectAtIndex(i);
    			if (c.indexOf(">")>-1) {
    				//c = c.substring(0, c.indexOf(">")) + ">" + c.substring(c.indexOf(">") + (">").length());
    				c = c.substring(c.indexOf(">") + (">").length());
    				a.replaceObjectAtIndex(c, i);
    			}
    		}
    		txtHtml=a.componentsJoinedByString("<"+tagName+">");
    	}
    	return txtHtml;
    }
    
    /**
     * convertit les attributs HTML en attribut FO et supprime ceux qui ne sont pas connu
     * @param e
     */
	private static void convertAttribut(Element e) {
		List l = e.getAttributes();
		Vector rmv=new Vector();
		if (l.size()>0) {
			for (int i=0;i<l.size();i++) {
				Attribute a = (Attribute) l.get(i);
				if (dicoAttribut().get(a.getName().toLowerCase())!=null) {
					a.setValue(getReplacingAttributeLeadingTxt(a.getName()) + convertValueAttribut(a.getName().toLowerCase(), a.getValue()) + getReplacingAttributeTrailingTxt(a.getName()));
					a.setName(getReplacingAttributeName(a.getName()));
				} else if (!notRmvAttrbt().contains(a.getName())) {
					rmv.add(a);
				}
			}
		}
		for (int j=0;j<rmv.size();j++) {
			e.removeAttribute((Attribute)rmv.get(j));
		}
	}
	
	/**
	 * convertit la valeur HTML de l'attribut selon dicoAttributConvVal.
	 * Si pas de conversion retourne la htmlValue
	 * @param htmlAttributName
	 * @param htmlValue
	 * @return
	 */
	private static String convertValueAttribut(String htmlAttributName, String htmlValue) {
		String r = htmlValue;
		if (dicoAttributConvVal().get(htmlAttributName)!=null) {
			HashMap v = (HashMap) dicoAttributConvVal().get(htmlAttributName);
			if (v.get(htmlValue)!=null) {
				r = (String) v.get(htmlValue);
			}
		}
		return r;
	}
	
	/**
	 * retourne vrai si l'element a un attribut avec le nom passe en parametre
	 * @param e
	 * @param attributeName
	 * @return
	 */
	private static boolean hasAttribute(Element e, String attributeName) {
		boolean r=false;
		String v = attributeValue(e,attributeName);
		if (v!=null) {
			r = true;
		}
		return r;
	}
	
	/**
	 * retourne la valeur de l'attribut avec le nom passer en parametre pour l'element passer en parametre.
	 * Si pas l'attribut n'existe pas retourne null. 
	 * @param e
	 * @param attributeName
	 * @return
	 */
	private static String attributeValue(Element e, String attributeName) {
		String r=null;
		List l = e.getAttributes();
		if (l.size()>0) {
			for (int i=0;i<l.size();i++) {
				Attribute a = (Attribute) l.get(i);
				if (a.getName().equalsIgnoreCase(attributeName)) {
					r=a.getValue();
					break;
				}
			}
		}
		return r;
	}
    
	/**
	 * remplace un string par un autre
	 * @param content
	 * @param toReplace
	 * @param byThis
	 * @return
	 */
	private static String replaceStringByString(String content, String toReplace, String byThis) {
//		while (content.indexOf(toReplace)>-1) {
//			String r1 = content.substring(0, content.indexOf(toReplace));
//			String r2 = content.substring(content.indexOf(toReplace) + toReplace.length());
//			content = r1 + byThis + r2;
//		}
//		return content;
		NSArray q = NSArray.componentsSeparatedByString(content,toReplace);
		return q.componentsJoinedByString(byThis);
	}
	
	/**
	 * supprime les espaces parasite entre les > et le <
	 * @param r
	 * @return
	 */
	private static String removeExtraSpaceBetwenNode(String r) {
		NSMutableArray a = (NSMutableArray) NSArray.componentsSeparatedByString(r, ">");
		for (int i=0;i<a.count();i++) {
			String t = (String) a.objectAtIndex(i);
			a.replaceObjectAtIndex(t.trim(),i);
		}
		r = a.componentsJoinedByString(">");
		return r;
	}
	
	/**
	 * Pour le cas d'exception des listes numerotees de word (tag UL et OL imbrique).
	 * @param html
	 * @return
	 */
	private static String correctionNumListFromWord(String html) {
    	int idx=0;
    	while (html.indexOf("[UL]",idx)>-1) {
    		String r1 = html.substring(0, html.indexOf("[UL]",idx));
    		String r2 = correctionNumListFromWordUL(html.substring(html.indexOf("[UL]",idx), html.indexOf("[/UL]",idx) + 5));
    		String r3 = html.substring(html.indexOf("[/UL]",idx) + 5);
    		html = r1 + r2 + r3;
    		idx = (r1 + r2).length();
    	}
    	
		return html;
	}
	
	/**
	 * methode associe a correctionNumListFromWord, verifie si [OL] imbrique dans [UL]
	 * @param html
	 * @return
	 */
	private static String correctionNumListFromWordUL(String html) {
		if (html.indexOf("[OL]")>-1) {
			html = correctionNumListFromWordJDOM(html);
		}
		return html;
	}
	
	/**
	 * methode associe a correctionNumListFromWord et correctionNumListFromWordUL.
	 * Remplace [UL] par [OL]
	 * @param html
	 * @return
	 */
	private static String correctionNumListFromWordJDOM(String html) {
		// prepare pour convertir en JDom
		String x = replaceStringByString(html,"[BR]","<br/>");
		x = replaceStringByString(x,"[OL]","<ol>");
		x = replaceStringByString(x,"[/OL]","</ol>");
		x = replaceStringByString(x,"[UL]","<ul>");
		x = replaceStringByString(x,"[/UL]","</ul>");
		x = replaceStringByString(x,"[LI]","<li>");
		x = replaceStringByString(x,"[/LI]","</li>");
		x = replaceStringByString(x,"\r\n","");
		x = removeExtraSpaceBetwenNode(x);
		try {
			SAXBuilder builder = new SAXBuilder();
			Document xmlObj = builder.build(new StringReader(x));
			// element root est <ul>
			List elements = xmlObj.getRootElement().getChildren("ol");
			for (int i=0;i<elements.size();i++) {
				Element e = (Element) elements.get(i);
				if (e.getName().equalsIgnoreCase("ol")) {
					// deplace tous les element de <ol> dans le root <ul>
					moveElementOL(e,xmlObj.getRootElement());
				}
			}
			//supprime les element <ol> (1 en principe et vide de leur contenu)
			boolean rr = xmlObj.getRootElement().removeChildren("ol");
			// supprime les <br> qui parasitent la mise en page
			boolean rq = xmlObj.getRootElement().removeChildren("br");
			if (rr) {
				xmlObj.getRootElement().setName("ol");
				XMLOutputter xo = new XMLOutputter();
				// remet en place la syntaxe pour la poursuite de la conversion
				String xr=(xo.outputString(xmlObj.getRootElement()));
				xr = replaceStringByString(xr,"<ol>","[OL]");
				xr = replaceStringByString(xr,"</ol>","[/OL]");
				xr = replaceStringByString(xr,"<ul>","[UL]");
				xr = replaceStringByString(xr,"</ul>","[/UL]");
				xr = replaceStringByString(xr,"<li>","[LI]");
				xr = replaceStringByString(xr,"</li>","[/LI]");
				html = xr;
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return html;
	}
	
	private static void moveElementOL(Element ol, Element ul) {
		List elementsOL=ol.getChildren();
		while (!elementsOL.isEmpty()) {
			Element e = (Element) elementsOL.get(0);
			ul.addContent(e.detach());
		}
	}
	
	private static HashMap dico = null;
	private static HashMap dicoAttribut=null;
	private static HashMap dicoAttributConvVal=null;
	private static Vector notRmvAttrbt=null;
	
	/**
	 * dictionnaire des tag/nom element
	 * @return
	 */
	private static HashMap dico() {
		if (dico==null) {
			dico = new HashMap();
			dico.put("table","fo_table");
			dico.put("tbody", "fo_table-body");
			dico.put("tr", "fo_table-row");
			dico.put("td", "fo_table-cell");
		}
		return dico;
	}
	
	/**
	 * dictionnaire des attribut.
	 * Structure du dico:
	 * {tag html = [tag fo,text avant valeur,text apres valeur]}
	 * @return
	 */
	public static HashMap dicoAttribut() {
		if (dicoAttribut==null) {
			dicoAttribut = new HashMap();
			dicoAttribut.put("cellpadding",dblArray("padding","","pt"));
			dicoAttribut.put("border",dblArray("border","0.","pt solid black"));
			dicoAttribut.put("colspan",dblArray("number-columns-spanned", "", ""));
			dicoAttribut.put("rowspan",dblArray("number-rows-spanned", "", ""));
			dicoAttribut.put("valign", dblArray("vertical-align","",""));
		}
		return dicoAttribut;
	}
	
	/**
	 * dictionnaire convertion valeur attribut.
	 * {tag html = {valeur html=valeur FO}}
	 * @return
	 */
	public static HashMap dicoAttributConvVal() {
		if (dicoAttributConvVal==null) {
			dicoAttributConvVal = new HashMap();
			dicoAttributConvVal.put("valign",toDico("top = text-top; bottom = text-bottom"));
		}
		return dicoAttributConvVal;
	}
	
	/**
	 * construit hashmap pour les valeurs dicoAttributConvVal:
	 * valeur html 1 = valeur FO 1; valeur html 2 = valeur FO 2;... etc => {valeur html=valeur FO}
	 * @param t
	 * @return
	 */
	private static HashMap toDico(String t) {
		HashMap d = new HashMap();
		NSArray a = NSArray.componentsSeparatedByString(t,";");
		for (int i=0;i<a.count();i++) {
			NSArray b = NSArray.componentsSeparatedByString((String) a.objectAtIndex(i), "=");
			d.put(((String)b.objectAtIndex(0)).trim(), ((String)b.objectAtIndex(1)).trim());
		}
		return d;
	}
	
	/**
	 * liste des attributs ne davant pas etre effacer car necessaire au traitement
	 * @return
	 */
	public static Vector notRmvAttrbt() {
		if (notRmvAttrbt==null) {
			notRmvAttrbt = new Vector();
			notRmvAttrbt.add("idxTR");
		}
		return notRmvAttrbt;
	}
	
	/**
	 * retourne un tableau de trois elements
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	private static String[] dblArray(String s1, String s2, String s3) {
		String[] r= {s1,s2,s3};
		return r;
	}
	
	/**
	 * retourne le nom de l'attribut convertit
	 * @param attributeNameToReplace
	 * @return
	 */
	private static String getReplacingAttributeName(String attributeNameToReplace) {
		try {
			String[] t = (String[]) dicoAttribut().get(attributeNameToReplace.toLowerCase());
			return t[0];
		} catch (RuntimeException e) {return "xxx";}
	}
	
	/**
	 * retourne le text se placant apres la valeur de l'attribut
	 * @param attributeNameToReplace
	 * @return
	 */
	private static String getReplacingAttributeTrailingTxt(String attributeNameToReplace) {
		try {
			String[] t = (String[]) dicoAttribut().get(attributeNameToReplace.toLowerCase());
			return t[2];
		} catch (RuntimeException e) {return "";}
	}
	
	/**
	 * retourne le texte se placant avant la valeur de l'attribut
	 * @param attributeNameToReplace
	 * @return
	 */
	private static String getReplacingAttributeLeadingTxt(String attributeNameToReplace) {
		try {
			String[] t = (String[]) dicoAttribut().get(attributeNameToReplace.toLowerCase());
			return t[1];
		} catch (RuntimeException e) {return "";}
	}
	
//	/**
//	 * Retourne les fo:inline ouvert d'un xslfo (coupé
//	 * de manière asymétrique)
//	 * @param xslfo a traiter
//	 * @return tableau des fo:inline ouvert
//	 */
    protected static NSArray getFOInlines(String xslfo) {
    	NSMutableArray foinlines = new NSMutableArray();
    	int indexLastOpenInline = xslfo.indexOf("<fo:inline", 0);
    		
    	while (indexLastOpenInline > -1) {
			// inline ouvert ?
			int indexNextCloseInline = xslfo.indexOf("</fo:inline>", indexLastOpenInline);
			int indexNextOpenInline = xslfo.indexOf("<fo:inline", indexLastOpenInline+1);
		

      		if (indexNextCloseInline == -1 || (indexNextCloseInline > indexNextOpenInline && indexNextOpenInline != -1)) {
				// ouverture sans fermeture
				int indexLastOpenInlineEnd = xslfo.indexOf(">", indexLastOpenInline);
				String foinline = xslfo.substring(indexLastOpenInline, indexLastOpenInlineEnd+1);
				foinlines.addObject(foinline);
			}
	      	else if (indexNextCloseInline != -1) {
	      		String text = xslfo.substring(indexNextCloseInline+1,(indexNextOpenInline==-1?xslfo.length():indexNextOpenInline));
	      		int indexInterCloseInline = text.indexOf("</fo:inline>");
	      		while (indexInterCloseInline > -1) {
	      			foinlines.removeLastObject();
	      			text = text.substring(indexInterCloseInline+1);
	      			indexInterCloseInline = text.indexOf("</fo:inline>");
	      		}
	      	} 	
	      	
	      	indexLastOpenInline = indexNextOpenInline; // suivant inline
    	}
      	return foinlines;
    }
	
  
	/**
	 * Permet de justifier le texte xslfo passé en paramètre :
	 * - remplacement des \n par ouverture/fermeture fo:block
	 * - tient compte des fo:inline qui seraient coupés
	 * @return xslfo
	 */
	public static String getJustifyText(String xslfo) {
     	return getJustifyText(xslfo,null);
	}
	
	/**
	 * Permet de justifier le texte xslfo passé en paramètre :
	 * - remplacement des \n par ouverture/fermeture fo:block
	 * - tient compte des fo:inline qui seraient coupés
	 * - tiens compte de la taille du texte
	 * @return xslfo
	 */
	public static String getJustifyText(String xslfo, Integer fontSize) {
    	// justifié
    	NSArray sIn = NSArray.componentsSeparatedByString(xslfo, "\n");
    	String jxslfo = "";
    	String font_Size=(fontSize!= null && fontSize.intValue()>0?" font-size=\""+fontSize.intValue()+"pt\"":"");
    	NSArray foinlines = null;
    	jxslfo +="</fo:block><fo:block text-align=\"justify\" text-align-last=\"left\" white-space-collapse=\"false\""+font_Size+">";
    	for (int i=0; i<sIn.count(); i++) {
    		String _s = (String) sIn.objectAtIndex(i);
    		if (_s.trim().startsWith("<fo:list-block") || _s.trim().startsWith("<fo:list-item>")) {
    			jxslfo += _s; // pas de justififcation dans le cas des puces
    		} else {
	    		if (_s.length() == 0) 
	    			_s = " ";
				if (foinlines != null)
	    			_s = foinlines.componentsJoinedByString("")+ _s;
				
	    		foinlines = getFOInlines(_s);
	    		// ajout du block
	    		if (foinlines.count() == 0)
	    			jxslfo += _s + "</fo:block><fo:block text-align=\"justify\" text-align-last=\"left\" white-space-collapse=\"false\""+font_Size+">";
	    		else {
	    			String foinlinesend = "";
	    			for (int j=0; j<foinlines.count(); j++) {
	    				foinlinesend += "</fo:inline>";
	    			}
	    			jxslfo += _s + foinlinesend + "</fo:block><fo:block text-align=\"justify\" text-align-last=\"left\" white-space-collapse=\"false\""+font_Size+">";
	    		}
    		}
    	}
     	return jxslfo;
	}
	
	
	
}

