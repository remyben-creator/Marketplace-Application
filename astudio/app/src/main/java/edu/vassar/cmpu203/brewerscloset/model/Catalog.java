package edu.vassar.cmpu203.brewerscloset.model;

import java.util.LinkedList;
import java.util.List;

public interface Catalog {
    int getLength();
    List getList();
    Object getItem(int index);

}