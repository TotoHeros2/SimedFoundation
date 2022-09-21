// Generated by the WebObjects Wizard Fri Apr 05 10:46:05 Europe/Zurich 2002
package ch.hcuge.simed.foundation.component.fop;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import com.webobjects.appserver.*;
import com.webobjects.foundation.NSData;

import er.extensions.components.ERXComponent;
//import hug.infrastructure.foundation.*;

public class Graphic extends ERXComponent {

    protected String url = null;

    /**
     * constructor : permet d'afficher une image situee dans un framework
     *
     * @param context est le context courant
     */
    public Graphic(WOContext context) {
        super(context);
    }

    /**
     * Indique que les bindings ne doivent pas etre evalues a la construction de la page
     *
     * @return "VRAI" si les bindings ne doivent pas etre evalues a la construction de la page
     */
    public boolean synchronizesVariablesWithBindings() {
        // Do not synchronize variables with the bindings
        return false;
    }

    /**
     * Retourne le nom du framework ou se trouve l'image du bouton
     *
     * @return le nom du framework ou se trouve l'image du bouton
     */
    public String framework(){
        return (String)valueForBinding("framework");
    }

    /**
     * Retourne le nom de l'image a afficher
     *
     * @return le nom de l'image a afficher
     */
    public String graphicName(){
        return (String)valueForBinding("graphicName");
    }

    /**
     * Retourne VRAI si l'image est nulle
     *
     * @return VRAI si l'image est nulle
     */
    public boolean isGraphicNameNull () {
        return ((graphicName() != null && graphicName().length() > 0) ? false : true);
    }

    /**
     * 
     * @return
     */
    public String href() {
    	return (String) valueForBinding("href");
    }
    
	/**
	 * Retourne la hauteur de l'image du bouton
	 *
	 * @return la hauteur de l'image du bouton
	 */
	public String width(){
		String width = (String)valueForBinding("width");
		if (width == null)
			width = "";
		return width;
	}

    /**
     * Retourne la hauteur de l'image du bouton
     *
     * @return la hauteur de l'image du bouton
     */
    public String height(){
        String height = (String)valueForBinding("height");
        if (height == null)
            height = "1.5cm";
        return height;
    }

    /**
     * Indique l'url de l'image a afficher
     *
     * @return l'url de l'image a afficher
     */
    public String url() throws IOException {
        if (url == null) {
        	if (href() != null) {
        		URL _url = new URL(href()); 
        		url = _url.toExternalForm();
        	} else { 
        		/*
				HugApplication app = (HugApplication)WOApplication.application();
				if (couldFindGraphicInFramework())
					url = "http://" + app.hostName() + "/WebObjects/Frameworks/" + framework() + ".framework/WebServerResources/" + graphicName();
				else {
					newFile();
					url = "http://" + app.hostName() + "/WebObjects/Frameworks/SimedInfrastructureFramework.framework/WebServerResources/" + fileName();
				}
			  */
        		url = "deja shit - Dont use ";
        	}
        }
        return url;
    }

    /**
     * 
     * @author NIC
     * @since 30 ao�t 07
     * @return
     */
    private String fileName() {
    	return session().sessionID()+ "-" + graphicName();
    }
    
    /**
     * 
     * @return
     */
    private String filePath() {
    	/*
    	HugApplication app = (HugApplication) application();
    	return app.getPathWWWRoot() + "/WebObjects/Frameworks/SimedInfrastructureFramework.framework/WebServerResources/";
    	*/
    	return "deja shit - Dont use ";
    }
    
	/**
	 * Retourne le chemin du fichier cr��er physiquement
	 * 
	 * @return le chemin du fichier cr��er physiquement
	 * @throws IOException
	 */
	public File newFile() throws IOException {
		// cr�ation de l'image
		File file = new File(filePath() + fileName());
		OutputStream is = new FileOutputStream(file);
		BufferedOutputStream isB = new BufferedOutputStream(is);
		// r�cup�ration des donn�es de l'image
		NSData data = (NSData)valueForBinding("data");
		isB.write(data.bytes(0, data.length() - 1));
		// fermeture du fichier
		isB.close();
		is.close();
		return file;
	}

	/**
	 * Indique si l'image doit �tre chercher dans un framework
	 * 
	 * @return "true" si l'image doit �tre chercher dans un framework
	 */
	public boolean couldFindGraphicInFramework() {
		return framework() != null;
	}

	/**
	 * Supprime l'image sur le disque
	 */
	public void sleep() {
		if (!couldFindGraphicInFramework()) {
			File file = new File(filePath() + fileName());
			file.delete();
		}
	}


}
