package com.kk.springcoud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kk.springcoud.models.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long>{

	Coupon findByCode(String code);

}
