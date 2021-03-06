package chatroom;

import functions.*;

public class TextTransferProtocol {
	
	private static final String SPLITTER = "�";
	private static final String CS = "�"; //�����������
	
	private ChatServerThread thread;
	
	public TextTransferProtocol(ChatServerThread thread) {
		this.thread = thread; //use this for calling commands on the thread
	}

    public String processIn(String in) {
    	
    	String out = "";
    	//scan for commands and stuff here
    	
    	if (in.startsWith("/")) {
    		String[] cmd = in.substring(1, in.length()).trim().split(" ");
    		ChatServer.pushToChat(thread.name + ": " + in);
    		thread.runCmd(cmd);
    		p.nl(thread.name + ": " + in);
    	} else if (thread.isMuted()) {
    		ChatServer.pushToChat(thread.name + ": " + in);
    		ChatServer.sendOne(thread.getID(), CS+"cNo one can hear you.");
    	} else {
    		out = in.trim();
    	}
    	
    	if (out != ""){
            return out;   		
    	}
    	return null;
    }
}
