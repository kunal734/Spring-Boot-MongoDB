package com.tekpyramid.merchant.dao;

import com.tekpyramid.merchant.data.models.entities.Merchant;

import java.util.List;
import java.util.Optional;

public interface MerchantDao {

    public Merchant saveMerchant(Merchant merchant);

    public Optional<Merchant> findById(String id);

    public List<Merchant> findByName(String name);

    public Optional<Merchant> findByEmail(String email);

    public Optional<Merchant> findByPhoneAndPassword(long phone, String password);

    public Optional<Merchant> findByEmailAndPassword(String email, String password);
}
