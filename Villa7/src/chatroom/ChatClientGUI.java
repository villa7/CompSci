package chatroom;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import functions.p;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;

public class ChatClientGUI {
	
	private final int WIDTH_SETTINGS = 300,
			  		  HEIGHT_SETTINGS = 200,
			  		  WIDTH_MAIN = 900,
			  		  HEIGHT_MAIN = 600;
	private final double ver = 1.2;
	
	private JFrame framePre,
				   frame;
	private JPanel panelPre,
				   panel;
	private JTextField userNameSet,
					   ipSet,
					   portSet,
					   input;
	private JEditorPane outputText;
	private JScrollPane output;
	private JLabel userNameLabel,
				   ipLabel,
				   portLabel,
				   errorLabel;
	private JButton btnSend;
	private JButton btnConnect;
	private Box horizontalBox;
	private String username, ip, port;
	private boolean doneSettings = false;
	private ChatClient client;

	public ChatClientGUI(ChatClient client) {
		this.client = client;
		framePre = new JFrame("ChatClient v" + ver + " - Setup");
		framePre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePre.setResizable(false);
		
		panelPre = new JPanel();
		panelPre.setPreferredSize(new Dimension(WIDTH_SETTINGS, HEIGHT_SETTINGS));
		ipSet = new JTextField(30);
		ipSet.addActionListener(new SettingsListener());
		portSet = new JTextField(30);
		portSet.addActionListener(new SettingsListener());
		
		framePre.getContentPane().add(panelPre, BorderLayout.CENTER);
		panelPre.setLayout(new GridLayout(8, 4, 0, 0));
		userNameLabel = new JLabel("Set your username.");
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelPre.add(userNameLabel);
		
		userNameSet = new JTextField(30);
		userNameSet.addActionListener(new SettingsListener());
		panelPre.add(userNameSet);
		ipLabel = new JLabel("Server IP:");
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelPre.add(ipLabel);
		panelPre.add(ipSet);
		portLabel = new JLabel("Server Port:");
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelPre.add(portLabel);
		panelPre.add(portSet);
		
		/*horizontalBox = Box.createHorizontalBox();
		panelPre.add(horizontalBox);*/
		errorLabel = new JLabel();
		errorLabel.setForeground(new Color(255, 0, 0));
		panelPre.add(errorLabel);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new SettingsListener());
		panelPre.add(btnConnect);
		
		/* --------------- Fancy stuff made with WindowBuilder -------------- */
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(WIDTH_MAIN, HEIGHT_MAIN));
		
		input = new JTextField(30);
		input.setFont(new Font("Arial", Font.PLAIN, 14));
		input.addActionListener(new SendListener());
		
		output = new JScrollPane();
		output.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
		);
		
		//outputText = new JTextArea();
		outputText = new JEditorPane();
		//outputText.setFont(new Font("Arial", Font.PLAIN, 14));
		output.setViewportView(outputText);
		outputText.setEditable(false);
		outputText.setContentType("text/html");
		outputText.setBackground(new Color(30, 30, 30));
		output.setAutoscrolls(true);
		DefaultCaret caret = (DefaultCaret) outputText.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		//outputText.setForeground(Color.WHITE);
		HTMLEditorKit kit = new HTMLEditorKit();
		outputText.setEditorKit(kit);
		Font font = new Font("Segoe UI", Font.PLAIN, 14);
	    String stylesheet = "body { font-family: " + font.getFamily() + "; " +
	            "font-size: " + font.getSize() + "pt; color: white;}";
		((HTMLDocument) outputText.getDocument()).getStyleSheet().addRule(stylesheet);
		//outputText.setLineWrap(true);
		//outputText.setWrapStyleWord(true);
				
		btnSend = new JButton("Send");
		btnSend.addActionListener(new SendListener());
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(input, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSend)
					.addContainerGap())
				.addComponent(output, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(output, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend))
					.addGap(6))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		
	}
	
	public boolean displaySettings() {
		framePre.pack();
		frame.setVisible(false);
		framePre.setVisible(true);
		return doneSettings;
	}
	public void displayMain() {
		frame.pack();
		frame.setTitle("ChatClient v" + ver + " - " + username + "@" + ip + ":" + port);
		framePre.setVisible(false);
		frame.setVisible(true);
		doneSettings = false;
	}
	public String getName() {
		return (userNameSet.getText().length() > 0) ? userNameSet.getText() : "";
	}
	public String getIp() {
		return (ipSet.getText().length() > 0) ? ipSet.getText() : "";
	}
	public int getPort() {
		return (portSet.getText().length() > 0) ? Integer.parseInt(portSet.getText()) : 0;
	}
	public void pushToChat(String msg) {
		append(msg + "\n");
	}
	public void append(String s) {
		   try {
		      HTMLDocument doc = (HTMLDocument) outputText.getDocument();
		      //doc.insertString(doc.getLength(), s, null);
		      HTMLEditorKit kit = (HTMLEditorKit) outputText.getEditorKit();
		      
		      StringReader reader = new StringReader(s);
		      try {
				kit.read(reader, doc, doc.getLength());
			} catch (IOException e) {
				e.printStackTrace();
			}
		      outputText.setCaretPosition(outputText.getDocument().getLength()); //3rd attempt to make it always always scroll
		   } catch (BadLocationException e) {
		      e.printStackTrace();
		   }
		}
	
	private class SettingsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (getName().length() < 1) {
				errorLabel.setText("Set a username.");
			} else if (getName().contains(" ")) {
				errorLabel.setText("Username cannot contain spaces");
			} else {
				errorLabel.setText("");
				if (!getIp().equals("")) ChatClient.host = getIp();
				else ChatClient.host = "localhost";
				if (!portSet.getText().equals("")) ChatClient.port = getPort();
				else ChatClient.port = 25565;
				ChatClient.user = getName();
				username = ChatClient.user;
				ip = ChatClient.host;
				port = ""+ChatClient.port;
				doneSettings = true;
			}
		}
	}
	private class SendListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!input.getText().equals("")) {
				client.send(input.getText()); //when send button pressed, send message in text box
				input.setText(""); //clear box
			}
		}
	}

}
