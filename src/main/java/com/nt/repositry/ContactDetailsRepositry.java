package com.nt.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nt.entity.ContactDetailsEntity;

@Repository
public interface ContactDetailsRepositry extends JpaRepository<ContactDetailsEntity, Integer> {
	@Transactional
	@Modifying
	@Query("update ContactDetailsEntity set activeSw=:sw where contact_Id=:cid ")
	public void updateByContactId(String sw, Integer cid);
}
