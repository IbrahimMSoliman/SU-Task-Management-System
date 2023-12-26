/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tms.validator;

import jakarta.faces.component.UIInput;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ListenerFor;
import jakarta.faces.event.SystemEvent;
import jakarta.faces.event.SystemEventListener;

@ListenerFor(sourceClass = jakarta.faces.component.html.HtmlInputText.class, systemEventClass = jakarta.faces.event.PostValidateEvent.class)
public class PostValidationListener implements SystemEventListener
    {

    @Override
    public boolean isListenerForSource(Object source)
        {
        return true;
        }

    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException
        {
        UIInput source = (UIInput) event.getSource();
        if(!source.isValid())
            source.getAttributes().put("styleClass", "ui-state-error");
        }
    }
