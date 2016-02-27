package ru.home.CN2;

import static org.junit.Assert.*;

import org.apache.poi.ss.usermodel.Cell;
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
	
	@Test
	public void ReplaceTextTest(){
		CelssText celss = new CelssText("hello     im      name        hiro");
		celss.replaceText("  ", " ");
		System.out.println(celss.toString());
		assertTrue("hello im name hiro".equals(celss.toString()));
	}

}
