package com.nt.service;

import java.util.List;

import com.nt.domain.Contact;

public interface ContactInfoService {
	
	public boolean saveContacts(Contact c);
	
	public List<Contact> displayAllContacts();
	
	public Contact getContactById(Integer cid);
	 
	public void deleteContactById(Integer cid);
	
}
