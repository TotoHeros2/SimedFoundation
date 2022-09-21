package ch.hcuge.simed.foundation.component.fop;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.components.ERXComponent;

public class Footer extends ERXComponent {

	protected NSTimestamp 	today = new NSTimestamp();
	protected String 		format = "le %d/%m/%Y \u00E0 %H:%M";
    
	/**
	 * constructor : permet d'assigner un pied de page avec par
	 * defaut le user qui a imprime ainsi que la date d'impression
	 * et le numero de la page par rapport au total
	 *
	 * @param context est le context courant
	 */
	public Footer(WOContext context) {
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
	 * Retourne l'utilisateur qui est sense etre celui qui lance l'impression
	 *
	 * @return l'utilisateur qui est sense etre celui qui lance l'impression
	 */
	/*
	public UserInfo user(){
		return (UserInfo)valueForBinding("user");
	}
*/
	/**
	 * Retourne la date d'impression
	 *
	 * @return la date d'impression
	 */
	public NSTimestamp today(){
		return today;
	}

	/**
	 * Retourne le format de la date d'impression
	 *
	 * @return le format de la date d'impression
	 */
	public String format(){
		return format;
	}

	/**
	 * Indique si le format d'impression est en mode paysage
	 *
	 * @return "VRAI" si le format d'impression est en mode paysage
	 */
	public Boolean landscape(){
		return (Boolean)valueForBinding("landscape"); // API
	}
	
	/**
	 * Indique si "imprimé par ..." s'affiche ou pas
	 * @author CAB
     * @since 15.08.2008
	 * @return "VRAI" si "imprimé par ..." doit s'afficher
	 */
	public Boolean hidePrintBy(){
		return (Boolean)valueForBinding("hidePrintBy"); 
	}


	/**
	 * Rend le string du numero de page (si vide affiche page x/y)
	 *
	 * @return le string du numero de page
	 */
	public String pageNumberString(){
		return (String)valueForBinding("pageNumberString");
	}
    
	/**
	 * Indique si le contenu du footer s'imprime sous la mention du user ("imprimeur")
	 *
	 * @return "VRAI" si le contenu du footer s'imprime sous la mention du user
	 */
	public Boolean footerContentBottom(){
		return (Boolean)valueForBinding("footerContentBottom"); // API
	}
    
	public boolean isContentBottom() {
		return (footerContentBottom() == null || footerContentBottom().booleanValue());
	}
    
	/**
     * Retourne la longuer de la page
     * @author NIC
     * @since 22 déc. 2005
     * @return
	 */
    public double length() {
        String bind = (String)valueForBinding("lengthInCM");
        double length = 0;
        if (bind == null) {
            length = 18;
            if (landscape() != null && landscape().booleanValue())
                length = 26.7;
        } else {
            length = Double.valueOf(bind).doubleValue();
        }
        return length;
    }
    
    /**
     * 
     * @author NIC
     * @since 22 déc. 2005
     * @return
     */
    public double demi_length() {
        return length()/2;
    }
    
    public Boolean displayInitials(){
		return (Boolean)valueForBinding("displayInitials");
	}
}