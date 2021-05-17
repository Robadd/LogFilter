package de.robadd.logfilter.model;

import java.lang.reflect.Method;

import org.junit.Test;

import de.robadd.logfilter.logtypes.ebay.EbayMessage;
import de.robadd.logfilter.logtypes.serverlog.ServerEvent;
import de.robadd.logfilter.model.EventFilter.EventFilterBuilder;
import de.robadd.logfilter.model.filter.EventFilterFactory;

public class EventFilterTest
{
	@Test
	public void filterTest() throws NoSuchMethodException, SecurityException
	{
		ServerEvent event = new ServerEvent();
		event.setClazz("test");
		event.setLevel(LogLevel.ERROR);

		Method getter1 = Event.class.getMethod("getClazz", (Class<?>[]) null);
		EventFilter<ServerEvent, String> test1 = new EventFilterBuilder<ServerEvent, String>().build(getter1);
		test1.addfilterValues("test");

		Method getter2 = Event.class.getMethod("getLevel", (Class<?>[]) null);
		EventFilter<ServerEvent, LogLevel> test2 = new EventFilterBuilder<ServerEvent, LogLevel>().build(getter2);
		test2.addfilterValues(LogLevel.ERROR);

		EventFilterFactory<ServerEvent> eventFilterFactory = new EventFilterFactory<>();
		eventFilterFactory.addFilter(test1);
		eventFilterFactory.addFilter(test2);
		assert eventFilterFactory.test(event);

		event.setLevel(LogLevel.DEBUG);

		assert !eventFilterFactory.test(event);
	}

	@Test
	public void t2()
	{
		Message msg = new EbayMessage();
		msg.character(
			"<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header/><soapenv:Body><ns1:CompleteSaleRequest xmlns:ns1=\"urn:ebay:apis:eBLBaseComponents\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:CompleteSaleRequestType\"><ns1:DetailLevel>ReturnAll</ns1:DetailLevel><ns1:ErrorLanguage>de_DE</ns1:ErrorLanguage><ns1:Version>1131</ns1:Version><ns1:ErrorHandling>BestEffort</ns1:ErrorHandling><ns1:Shipped>false</ns1:Shipped><ns1:OrderID>19-05357-27675</ns1:OrderID></ns1:CompleteSaleRequest></soapenv:Body></soapenv:Envelope>\r\n");
		assert !msg.getRequestMethod().isEmpty();
	}
}
