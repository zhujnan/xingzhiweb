package com.web01.dao;

import java.util.ArrayList;

import com.web01.entity.Emp;

public interface IUserDao {

	boolean login(String userName, String passWorld) throws Exception;

	ArrayList<Emp> findAll();

	boolean deleteEmp(int id);

	boolean addEmp(Emp emp);

	Emp findEmpById(Integer id);

	boolean updateEmp(Emp emp);

	Emp findUserByUser(Emp emp);


}
