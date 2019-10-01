package pages.project1.desktop.imp;

import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.TextBox;

import pages.project1.ILoginPage;

public class LoginPage implements ILoginPage {

	@Override
	public void login(String username, String password) {
		usernameTextBox.waitForDisplay();
		usernameTextBox.enter(username);
		passwordTextBox.enter(password);
		loginButton.click();
	}

	private TextBox usernameTextBox = new TextBox("id=body_UserName");
	private TextBox passwordTextBox = new TextBox("id=body_Password");
	private Button loginButton = new Button("id=body_LoginButton");
}
