package com.tekpyramid.merchant.repository;

import com.tekpyramid.merchant.data.models.entities.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MerchantRepository extends MongoRepository<Merchant,String> {
    public List<Merchant> findByName(String name);

    public Optional<Merchant> findByEmail(String email);

    public Optional<Merchant> findByPhoneAndPassword(long phone, String password);

    public Optional<Merchant> findByEmailAndPassword(String email, String password);
}
