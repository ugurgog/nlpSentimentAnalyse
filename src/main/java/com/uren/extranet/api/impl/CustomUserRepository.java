package com.uren.extranet.api.impl;

import com.uren.extranet.api.model.BaseResponseModel;

public interface CustomUserRepository {

	BaseResponseModel updateUserSalary(int id, long salary);
}
