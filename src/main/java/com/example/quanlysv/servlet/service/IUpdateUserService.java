package com.example.quanlysv.servlet.service;

import com.example.quanlysv.servlet.entity.AccountEntity;

public interface IUpdateUserService {
    boolean updateUser(AccountEntity account, String phone, String email);



}
