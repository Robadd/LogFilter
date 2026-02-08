package de.robadd.logfilter.model;

import org.xml.sax.Attributes;

public interface EventBuilder<T extends Event>
{

	void fillEvent(Attributes attributes);

	void fillLocationInfo(Attributes attributes);

	T getEvent();

	void messageCharacters(char[] ch, int start, int length);

	void throwableCharacters(char[] ch, int start, int length);

	void fillElement(String uri, String localName, String qName, Attributes attributes);

	EventFactory<T> getEventFactory();

	void addToIndex(Index index);
}
