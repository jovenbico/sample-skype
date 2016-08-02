package com.bicjo.skype;

import java.util.Collection;

import org.junit.Test;

import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NotParticipatingException;
import com.samczsun.skype4j.exceptions.handler.ErrorHandler;
import com.samczsun.skype4j.exceptions.handler.ErrorSource;

public class SkypeTest {

	private String username = "?";
	private String password = "?";

	@Test
	public void message_test() {
		try {

			Skype skype = new SkypeBuilder(username, password).withAllResources()
					.withExceptionHandler(new ErrorHandler() {
						@Override
						public void handle(ErrorSource errorSource, Throwable throwable, boolean willShutdown) {
							System.out.println("Error: " + errorSource + " " + throwable + " " + willShutdown);
							throwable.printStackTrace();
						}
					}).build();

			skype.login();
			skype.subscribe();

			Collection<Chat> chats = skype.getAllChats();
			for (Chat chat : chats) {
				System.out.println(chat.getIdentity());
			}

			skype.logout();
		} catch (NotParticipatingException | InvalidCredentialsException | ConnectionException e) {
			e.printStackTrace();
		}

	}

}
