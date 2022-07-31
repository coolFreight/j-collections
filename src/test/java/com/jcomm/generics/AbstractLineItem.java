package com.jcomm.generics;

public abstract class AbstractLineItem implements ReportItem{
    @Override
    public String lineItem() {
        return "Abstract Line Item";
    }

    @Override
    public String toString() {
        return "AbstractLineItem{}"+lineItem();
    }
}
