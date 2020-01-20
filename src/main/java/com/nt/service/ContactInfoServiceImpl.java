package com.nt.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.domain.Contact;
import com.nt.entity.ContactDetailsEntity;
import com.nt.repositry.ContactDetailsRepositry;
import com.nt.utility.MailUtility;
@Service
public class ContactInfoServiceImpl implements ContactInfoService {
	@Autowired	
	private ContactDetailsRepositry cntctRepo;
	@Autowired
	private MailUtility mailUtil;
		
		/**
		 * method for adding contact
		 */
	@Override
	public boolean saveContacts(Contact c) {
		ContactDetailsEntity entity=new ContactDetailsEntity();
		BeanUtils.copyProperties(c, entity);
		entity.setActiveSw("Y");
		entity=cntctRepo.save(entity);
		if(entity.getContactId()==1) {
			try {
				sendEmailTo(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entity.getContactId()>0;
	}

	/**
	 * fetching all records from DB
	 */
	
	@Override
	public List<Contact> displayAllContacts() {
		List<ContactDetailsEntity> ctctEntity = cntctRepo.findAll();
		List<Contact> listContact=new ArrayList();
		List<ContactDetailsEntity> collectList=null;
		if(!ctctEntity.isEmpty()) {
			  collectList = ctctEntity.stream().filter(entity->"Y".equals(entity.getActiveSw())).collect(Collectors.toList());
					  		
		}
		
		
		if(collectList!=null) {
		//copy cntctntity to listContact
			collectList.forEach(entity->{
			Contact c=new Contact();
			//copy entity to domain
			BeanUtils.copyProperties(entity, c);
			listContact.add(c);
		});
		}
		return listContact;
	}
	
	
	/**
	 * Edit contacts
	 */

	@Override
	public Contact getContactById(Integer cid) {
		ContactDetailsEntity contactDetailsEntity;
		Contact c=null;
		Optional<ContactDetailsEntity> optional = cntctRepo.findById(cid);
			if(optional.isPresent()) {
				contactDetailsEntity = optional.get();
				c=new Contact();
				BeanUtils.copyProperties(contactDetailsEntity, c);
			}
		return c;
	}

	/**
	 * delete the contacts
	 */
	
	
	@Override
	public void deleteContactById(Integer cid) {
		cntctRepo.updateByContactId("N", cid);
	}
	
	private void sendEmailTo(Contact c) throws Exception {
		String fileName="MailTemplate";
		StringBuilder sb=new StringBuilder();
		FileReader reader=new FileReader(new File(fileName));
		BufferedReader br=new BufferedReader(reader);
		String line=br.readLine();
		while(line!=null) {
			if(line.contains("${NAME}")) {
				line=line.replace("${NAME}",c.getContactName());
				sb.append(line);
				line=br.readLine();
			} 
			if(line.contains("${PHNO}")) {
				line=line.replace("${PHNO}",String.valueOf(c.getContactNo()));
				sb.append(line);
				line=br.readLine();
			} 
		}
		mailUtil.sendMail(c.getContactEmail(),sb.toString(),"For greetings..");
	}

}
