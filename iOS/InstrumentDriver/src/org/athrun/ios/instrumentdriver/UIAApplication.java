/**
 * 
 */
package org.athrun.ios.instrumentdriver;

/**
 * @author ziyu.hch
 * 
 */
public class UIAApplication {

	private String guid = null;

	public UIAApplication(String guid) {
		// TODO Auto-generated constructor stub
		this.guid = guid;
	}

	public UIAWindow[] windows() throws Exception {

		return UIAElementHelp.windowArray(this.guid + ".windows()");
	}

	/**
	 * mainWindow
	 */
	public UIAWindow mainWindow() throws Exception {

		return new UIAWindow("win");
		// return UIAElementHelp.getWindow(this.guid + ".mainWindow()");
	}

	public UIAActionSheet actionSheet() throws Exception {

		return UIAElementHelp.getActionSheet(this.guid + ".actionSheet()");
	}

	public UIAAlert alert() throws Exception {
		return UIAElementHelp.getAlert(this.guid + ".alert()");
	}

	public UIAKeyboard keyboard() throws Exception {

		return UIAElementHelp.getKeyboard(this.guid + ".keyboard()");
	}

	public UIANavigationBar navigationBar() throws Exception {

		return UIAElementHelp.getNavigationBar(this.guid + ".navigationBar()");
	}

	public UIAStatusBar statusBar() throws Exception {

		return UIAElementHelp.getStatusBar(this.guid + ".statusBar()");
	}

	/**
	 * tabBar
	 */
	public UIATabBar tabBar() throws Exception {

		return UIAElementHelp.getTabBar(this.guid + ".tabBar()");
	}

	public UIAToolbar toolbar() throws Exception {

		return UIAElementHelp.getToolbar(this.guid + ".toolbar()");
	}
}
