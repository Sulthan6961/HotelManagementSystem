package com.app.repository;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.AddPhotos;
import com.app.entity.AddressPage;

public interface AddressRepository extends JpaRepository<AddressPage, Long> {

	Address save(Address updatedAddress);

	static AddPhotos save(AddPhotos addPhotos) {
		// TODO Auto-generated method stub
		return null;
	}
}
