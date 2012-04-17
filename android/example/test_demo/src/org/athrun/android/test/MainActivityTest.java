package org.athrun.android.test;

import org.athrun.android.app.R;
import org.athrun.android.framework.AthrunTestCase;
import org.athrun.android.framework.Test;
import org.athrun.android.framework.ViewOperation;
import org.athrun.android.framework.viewelememt.AbsListViewElement;
import org.athrun.android.framework.viewelememt.SlideableElement;
import org.athrun.android.framework.viewelememt.TextViewElement;
import org.athrun.android.framework.viewelememt.ViewElement;
import org.athrun.android.framework.viewelememt.ViewGroupElement;

import android.widget.AbsListView;

public class MainActivityTest extends AthrunTestCase {
	private static final String LOG_TAG = "MainActivityTest";

	public MainActivityTest() throws Exception {
		super("org.athrun.android.app", "org.athrun.android.app.MainActivity");
		AthrunTestCase.setMaxTimeToFindView(2000);
	}

	@Test
	public void testWaitForActivity() throws Exception {
		// log("This is a test for log() method");
		assertEquals(true, getDevice().waitForActivity("MainActivity", 1000));
		assertEquals(false, getDevice().waitForActivity("ScrollActivity", 1000));
		findElementByText("ScrollView").doClick();
		assertEquals(true, getDevice().waitForActivity("ScrollActivity", 1000));
		assertEquals(false, getDevice().waitForActivity("MainActivity", 1000));
		getDevice().pressBack();
		assertEquals(true, getDevice().waitForActivity("MainActivity", 1000));
		assertEquals(false, getDevice().waitForActivity("ScrollActivity", 1000));
	}

	@Test
	public void testFindElementInTree() throws Exception {
		ViewGroupElement include = findElementById("include_checkbox",
				ViewGroupElement.class);
		include.findElementById("my_checkbox", ViewElement.class).doClick();
		TextViewElement tmtsTextView = include.findElementById("textview",
				TextViewElement.class);
		assertEquals("CheckBox is checked!", tmtsTextView.getText());
	}

	@Test
	public void testFindTmtsViewInTree() throws Exception {
		findElementInTree("include_checkbox>my_checkbox", ViewElement.class)
				.doClick();
		TextViewElement tmtsTextView = findElementInTree(
				"include_checkbox>textview", TextViewElement.class);
		assertEquals("CheckBox is checked!", tmtsTextView.getText());
	}

	@Test
	public void testFindViewByIdDirect() throws Exception {
		findElementById("my_checkbox").doClick();
		assertEquals("CheckBox is checked!",
				findElementById("textview", TextViewElement.class).getText());
	}

	@Test
	public void testLongClick() throws Exception {
		findElementById("my_imageview", ViewElement.class).doLongClick();
		assertEquals("LongClick", findToastElement("").getText());
	}

	@Test
	public void testPressMenu() throws Exception {
		getDevice().pressMenu();
		findElementByText("Toast").doClick();
		assertEquals("Hello World", findToastElement("Hello World").getText());
	}

	@Test
	public void testPressHome() throws InterruptedException {
		getDevice().pressHome();
		Thread.sleep(2000);
	}

	@Test
	public void testPressBack() throws Exception {
		findElementById("btn_scrollview_activity").doClick();
		findElementByText("Bottom Button").doClick();
		getDevice().pressBack();
		assertEquals(true, getDevice().waitForActivity("MainActivity", 2000));
	}

	@Test
	public void testFindViewByText() throws Exception {
		findElementByText("ListView").doClick();
		AbsListViewElement listView = findElementById("my_listview",
				AbsListViewElement.class);
		listView.scrollToNextScreen();
		ViewElement tmtsView = listView.getChildByIndex(35, ViewElement.class);
		tmtsView.doLongClick();
		findElementByText("Item One").doClick();
		assertEquals("1 pressed!", findToastElement("").getText());
	}

	@Test
	public void testScrollListInDialog() throws Exception {
		getDevice().pressMenu();
		findElementByText("Dialog With List").doClick();
		AbsListViewElement listView = findElementByIndex(0, AbsListView.class,
				AbsListViewElement.class);
		listView.scrollToLine(9);
		assertEquals(9, listView.getLastVisiblePosition());
		findElementByText("OK").doClick();
		assertEquals("Botton OK in dialog with list is pressed!",
				findToastElement("").getText());
	}

	@Test
	public void testSetScreen() throws InterruptedException {
		getDevice().setScreenOrientation(
				android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		assertEquals("landscape", getDevice().getScreenOrientation());
	}

	@Test
	public void testGetStringById() throws Exception {
		findElementByText("ScrollView").doClick();
		assertEquals(true, getStringById("scroll_text").contains("道可道"));
		// log(getStringById("scroll_text"));
	}

	@Test
	public void testFindToastById() throws Exception {
		findElementById("my_imageview", ViewElement.class).doLongClick();
		assertNotNull(findToastElement(""));
		assertEquals("LongClick", findToastElement("").getText());
	}

	@Test
	public void testRequestFocus() throws Exception {
		findElementByText("ScrollView").requestFocus();
		getDevice().pressDown();
		getDevice().pressUp();
		getDevice().pressEnter();
		assertEquals(true, getDevice().waitForActivity("ScrollActivity", 2000));
	}

	@Test
	public void testPressEnter() throws Exception {
		findElementByText("ListView").requestFocus();
		getDevice().pressDown();
		getDevice().pressEnter();
		getDevice().pressEnter();
		Thread.sleep(2000);
	}

	@Test
	public void testFindViewByIntId() throws Exception {
		findElementById(R.id.btn_scrollview_activity, ViewElement.class)
				.doClick();
		assertEquals(true, getDevice().waitForActivity("ScrollActivity", 2000));
	}

	@Test
	public void testDevicePressKey() throws Exception {
		findElementByText("ScrollView").requestFocus();
		getDevice().pressEnter();
		assertEquals(true, getDevice().waitForActivity("ScrollActivity", 1000));
		getDevice().pressBack();
		assertEquals(true, getDevice().waitForActivity("MainActivity", 1000));
	}

	@Test
	public void testFindViewOutOfScreen() throws Exception {
		findElementById("btn_scrollview_activity").doClick();
		TextViewElement tb = findElementByText("Top Button");
		// log("x is " + tb.getLocation().getX());
		// log("y is " + tb.getLocation().getY());
		tb.doClick();
		Thread.sleep(3000);
		// log("x is " + tb.getLocation().getX());
		// log("y is " + tb.getLocation().getY());
		findElementByText("Top Button").doClick();
		Thread.sleep(3000);
	}

	@Test
	public void testFindViewOutOfScreen2() throws Exception {
		ViewGroupElement rootGroup = findElementById("mainroot",
				ViewGroupElement.class);
		rootGroup.findElementById("btn_scrollview_activity", ViewElement.class)
				.doClick();
	}

	@Test
	public void testFindViewOutOfScreen3() throws Exception {
		ViewGroupElement rootGroup = findElementById("mainroot",
				ViewGroupElement.class);
		rootGroup.getChildByIndex(7, ViewElement.class).doClick();
	}

	@Test
	public void testFindViewOutOfScreen4() throws Exception {
		ViewGroupElement rootGroup = findElementById("mainroot",
				ViewGroupElement.class);
		String button = rootGroup.getChildByIndex(7, TextViewElement.class)
				.getText();
		assertEquals("GridView", button);
	}

	@Test
	public void testSlide() throws Exception {
		SlideableElement gallery = findElementById("my_gallery",
				SlideableElement.class);

		for (int i = 0; i < 10; i++) {
			gallery.slide(ViewOperation.Direction.LEFT);
		}
	}
}