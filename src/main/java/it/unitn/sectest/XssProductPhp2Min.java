package it.unitn.sectest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import po.CategoriesPage;
import po.DashBoardPage;
import po.ProductPage;
import utils.BaseTest;

public class XssProductPhp2Min extends BaseTest{



	@Test
	public void test() {

		dashboardPage = defaultLogin();
		waitForWebsite();

		categoriesPage = dashboardPage.goToCategoriesPage();

		categoriesPage.addCategory("Malicious<script>alert(\"categories\")</script>", "Available");

		waitForWebsite();

		productPage = categoriesPage.goToProductPage();


		assertTrue(productPage.clickAlert());
		productPage.clickAlert();

	}

	@After
	public void reset() {

		categoriesPage = productPage.goToCategoriesPage();
		categoriesPage.clickAlert();
		waitForWebsite();
		categoriesPage.removeCategory();
		waitForWebsite();
		categoriesPage.logout();

	}

}
