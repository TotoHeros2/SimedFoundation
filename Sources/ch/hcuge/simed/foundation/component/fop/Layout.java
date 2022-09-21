package ch.hcuge.simed.foundation.component.fop;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXComponent;

public class Layout extends ERXComponent {
    
    /**
     * constructor : defini la disposition generale de la page PDF
     * (marge etc....)
     *
     * @param context est le context courant
     */
    public Layout(WOContext context) {
        super(context);
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
     * 
     * @author NIC
     * @since 21 déc. 2005
     * @return
     */
	public Boolean useRegionStart(){
		return (Boolean)valueForBinding("useRegionStart"); // API
	}

    /**
     * 
     * @author NIC
     * @since 21 déc. 2005
     * @return
     */
	public String marginTop() {
	    String margin = (String)valueForBinding("marginTop");
        if (margin == null)
            margin = "1cm";
        return margin;
    }
    
    /**
     * 
     * @author NIC
     * @since 21 déc. 2005
     * @return
     */
    public String marginBottom() {
        String margin = (String)valueForBinding("marginBottom");
        if (margin == null)
            margin = "1.7cm";
        return margin;
    }

    /**
     * 
     * @author NIC
     * @since 21 déc. 2005
     * @return
     */
    public String marginLeft() {
        String margin = (String)valueForBinding("marginLeft");
        if (margin == null)
            margin = "1.5cm";
        return margin;
    }

    /**
     * 
     * @author NIC
     * @since 21 déc. 2005
     * @return
     */
    public String marginRight() {
        String margin = (String)valueForBinding("marginRight");
        if (margin == null)
            margin = "1cm";
        return margin;
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
 
}
