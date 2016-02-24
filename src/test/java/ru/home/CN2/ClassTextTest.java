package ru.home.CN2;

import static org.junit.Assert.*;

import org.junit.Test;

import DataPackage.CelssText;

public class ClassTextTest {

	@Test
	public void CreateTest(){
		assertNotNull(new ClassTextTest());
	}
	
	@Test
	public void trimTest(){
		CelssText celssText = new CelssText(" piter ");
		celssText.trim();
		assertTrue("piter".equals(celssText.getText()));
	}
	
	@Test
	public void deleteTest(){
		CelssText celsText = new CelssText("piter");
		celsText.delete();
		assertTrue("".equals(celsText.getText()));
	}
	
	@Test
	public void CreateString(){
		assertNotNull(new CelssText("piter"));
	}
	
	@Test
	public void addTextTest(){
		CelssText celss = new CelssText();
		celss.addText("pitcher");
		assertTrue("pitcher".equals(celss.getText()));
	}

}
