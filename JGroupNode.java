package com.alacriti.tests.jgroups;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

public class JGroupNode extends ReceiverAdapter {
	JChannel channel;
	String user_name = System.getProperty("user.name", "n/a");

	private void start() throws Exception {
		channel = new JChannel("tcp.xml");
		channel.setReceiver(this);
		channel.connect("ChatCluster");
		eventLoop();
		channel.close();
	}

	public void viewAccepted(View new_view) {
		System.out.println("** view: " + new_view);
		// -- If network fails observed then Poll to DB in interval little
		// higher than jgroups interval
	}

	public void receive(Message msg) {
		if (!channel.getAddress().equals(msg.src())) {
			System.out.println(msg.getSrc() + ": " + msg.getObject());

			// -- Load from DB for that specific cache
		}
	}

	private void eventLoop() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.print("> ");
				System.out.flush();
				String line = in.readLine().toLowerCase();
				if (line.startsWith("quit") || line.startsWith("exit"))
					break;
				line = "[" + user_name + "] " + line;
				Message msg = new Message(null, null, line);
				channel.send(msg);
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new JGroupNode().start();
	}
}