package com.sam.safemanager.test;

import com.sam.safemanager.engine.NumberAddressService;

import android.test.AndroidTestCase;

public class TestNumQuery extends AndroidTestCase {
	public void testGetAddress(){
		
		assertEquals("�Ϻ���", NumberAddressService.getAddress("021"));
		assertEquals("�Ϻ���", NumberAddressService.getAddress("0210"));
		assertEquals("�Ϻ���", NumberAddressService.getAddress("02100"));
		assertEquals("�Ϻ���", NumberAddressService.getAddress("021000000"));
		assertEquals("����ʡ�Ϸ���", NumberAddressService.getAddress("055100"));
		assertEquals("����ʡ�Ϸ���", NumberAddressService.getAddress("05510"));
		assertEquals("����ʡ�Ϸ���", NumberAddressService.getAddress("0551"));
		assertEquals("����ʡ�Ϸ���", NumberAddressService.getAddress("15155185170"));
	}

}
