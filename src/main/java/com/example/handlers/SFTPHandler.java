package com.example.handlers;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SFTPHandler {
	private String remoteHost = "localhost";
	private String username = "sftpuser";
	private String password = "passw0rd";
	private int port = 22;
	private String knownHostsFile = System.getProperty("user.home") + "/.ssh/known_hosts";

	public ChannelSftp setupJsch() throws JSchException {
		JSch jsch = new JSch();
		jsch.setKnownHosts(knownHostsFile);

		Session session = jsch.getSession(username, remoteHost, port);
		session.setPassword(password);

		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.connect();
		Channel channel = session.openChannel("sftp");
		channel.connect();
		return (ChannelSftp) channel;
	}

	public void uploadFile(String localFilePath, String remoteDir) {
		ChannelSftp channelSftp = null;
		try {
			channelSftp = setupJsch();
			channelSftp.put(new FileInputStream(localFilePath), remoteDir);
			System.out.println("File uploaded: " + localFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (channelSftp != null)
				channelSftp.exit();
		}
	}

	public void downloadFile(String remoteFilePath, String localFilePath) {
		ChannelSftp channelSftp = null;
		try {
			channelSftp = setupJsch();
			channelSftp.get(remoteFilePath, new FileOutputStream(localFilePath));
			System.out.println("File downloaded: " + localFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (channelSftp != null)
				channelSftp.exit();
		}
	}
}
