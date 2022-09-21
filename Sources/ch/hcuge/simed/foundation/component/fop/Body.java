package ch.hcuge.simed.foundation.component.fop;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXComponent;

public class Body extends ERXComponent {

    /**
     * constructor : permet d'assigner un contenu dans
     * le corps du document PDF
     *
     * @param context est le context courant
     */
    public Body(WOContext context) {
        super(context);
    }


}
