package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.model.Address;
import com.crs.datajpa.model.dto.AddressDTO;
import com.crs.datajpa.repository.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressDTO createAddress(AddressDTO addressDTO) {

        Address cratedAddress = new Address();

        BeanUtils.copyProperties(addressDTO, cratedAddress, "user");

        var savedAddress = addressRepository.save(cratedAddress);

        return new AddressDTO(
                savedAddress.getId(),
                savedAddress.getPostalCode(),
                savedAddress.getStreetAddress(),
                savedAddress.getComplement(),
                savedAddress.getNeighborhood(),
                savedAddress.getCity(),
                savedAddress.getState(),
                savedAddress.getIbgeCode(),
                savedAddress.getAreaCode()
        );

    }

    @Transactional(readOnly = true)
    public AddressDTO getAddressByIdService(Long id){
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );

        return new AddressDTO(
                address.getId(),
                address.getPostalCode(),
                address.getStreetAddress(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getIbgeCode(),
                address.getAreaCode()
        );


    }


    @Transactional(readOnly = true)
    public List<AddressDTO> getAddressList(){
        List<Address> addressList = addressRepository.findAll();

        List<AddressDTO> addressDTOList = new ArrayList<>();

        for(Address item: addressList){
            AddressDTO addressDTO = new AddressDTO(
                    item.getId(), item.getPostalCode(), item.getStreetAddress(),
                    item.getComplement(), item.getNeighborhood(),
                    item.getCity(), item.getState(), item.getIbgeCode(),
                    item.getAreaCode()
            );

            addressDTOList.add(addressDTO);
        }

        return addressDTOList;
    }


    public AddressDTO updateAddressService(Long id, AddressDTO addressDTO){
        Address addressOptional = addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );

        BeanUtils.copyProperties(addressDTO, addressOptional, "user", "id");

        var savedAddress = addressRepository.save(addressOptional);

        return new AddressDTO(
                savedAddress.getId(),
                savedAddress.getPostalCode(),
                savedAddress.getStreetAddress(),
                savedAddress.getComplement(),
                savedAddress.getNeighborhood(),
                savedAddress.getCity(),
                savedAddress.getState(),
                savedAddress.getIbgeCode(),
                savedAddress.getAreaCode()
        );

    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }



}
