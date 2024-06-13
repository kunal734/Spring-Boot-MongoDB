package com.tekpyramid.merchant.dao.impl;

import com.tekpyramid.merchant.dao.MerchantDao;
import com.tekpyramid.merchant.data.models.entities.Merchant;
import com.tekpyramid.merchant.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MerchantDaoImpl implements MerchantDao {

    private final MerchantRepository merchantRepository;

    @Override
    public Merchant saveMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public Optional<Merchant> findById(String id) {
        return merchantRepository.findById(id);
    }

    @Override
    public List<Merchant> findByName(String name) {
        return merchantRepository.findByName(name);
    }

    @Override
    public Optional<Merchant> findByEmail(String email) {
        return merchantRepository.findByEmail(email);
    }

    @Override
    public Optional<Merchant> findByPhoneAndPassword(long phone, String password) {
        return merchantRepository.findByPhoneAndPassword(phone, password);
    }

    @Override
    public Optional<Merchant> findByEmailAndPassword(String email, String password) {
        return merchantRepository.findByEmailAndPassword(email, password);
    }
}
