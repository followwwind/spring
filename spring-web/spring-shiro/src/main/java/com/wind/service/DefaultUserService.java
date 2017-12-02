/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.wind.service;

import com.wind.dao.UserDAO;
import com.wind.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link UserService} interface.  This service implements
 * operations related to User data.
 */
@Transactional
@Service("userService")
public class DefaultUserService implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getCurrentUser() {
        final Long currentUserId = (Long) SecurityUtils.getSubject().getPrincipal();
        if( currentUserId != null ) {
            return getUser(currentUserId);
        } else {
            return null;
        }
    }

    @Override
    public void createUser(String username, String email, String password) {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword( new Sha256Hash(password).toHex() );
        userDAO.createUser( user );
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUser(Long userId) {
        return userDAO.getUser(userId);
    }

    @Override
    public void deleteUser(Long userId) {
        userDAO.deleteUser( userId );
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser( user );
    }

}
