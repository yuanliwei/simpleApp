package com.ylw.net.utils.storage;

import java.util.List;

import org.junit.Test;

import com.ylw.net.utils.storage.ormliteutils.OrmLiteUtils;

public class OrmliteTest {
	@Test
	public void testQuery() {
		List<Account> accounts = OrmLiteUtils.queryForAll(Account.class);
		for (Account account : accounts) {
			System.out.println(account.toString());
		}
	}

	@Test
	public void testInsert() {
		OrmLiteUtils.save(new Account("ylw", "345676543456"));
	}

	@Test
	public void testQuaryForSameId() {
		Account a = OrmLiteUtils.queryForSameId(new Account(100));
		System.out.println(a.toString());
	}
}
