package ch.hcuge.simed.foundation.component.fop;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXComponent;

public class Header  extends ERXComponent {

    private 	final Boolean 	RESPONSE_YES = Boolean.TRUE;

    /**
     * constructor : permet d'assigner un contenu dans
     * le haut du document PDF
     *
     * @param context est le context courant
     */
    public Header(WOContext context) {
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

    public Boolean headerForFirstPage(){
        if (hasBinding("first"))
            return (Boolean)valueForBinding("first");
        else
            return RESPONSE_YES;
    }

    public Boolean headerForSecondPage(){
        if (hasBinding("second"))
            return (Boolean)valueForBinding("second");
        else
            return RESPONSE_YES;
    }

    /**
     * Indique si le format d'impression est en mode paysage
     *
     * @return "VRAI" si le format d'impression est en mode paysage
     */
    public Boolean landscape(){
        return (Boolean)valueForBinding("landscape"); // API
    }

}
