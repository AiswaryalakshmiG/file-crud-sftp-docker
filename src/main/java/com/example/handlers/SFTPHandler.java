/*
 * package com.example.handlers;
 * 
 * import java.io.FileInputStream; import java.io.FileNotFoundException; import
 * java.io.IOException; import java.util.Properties;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory;
 * 
 * import com.jcraft.jsch.ChannelSftp; import com.jcraft.jsch.JSch; import
 * com.jcraft.jsch.Session;
 * 
 * public class SFTPHandler { private static final Logger LOG =
 * LoggerFactory.getLogger(SFTPHandler.class);
 * 
 * private String host; private int port; private String user; private String
 * password; private String remotePath;
 * 
 * public SFTPHandler() throws FileNotFoundException, IOException { Properties
 * props = new Properties(); try (FileInputStream fis = new
 * FileInputStream("src/main/resources/application.properties")) {
 * props.load(fis); } this.host = props.getProperty("sftp.host"); this.port =
 * Integer.parseInt(props.getProperty("sftp.port")); this.user =
 * props.getProperty("sftp.user"); this.password =
 * props.getProperty("sftp.password"); this.remotePath =
 * props.getProperty("sftp.remote.path"); }
 * 
 * public void uploadFile(String localFilePath) { Session session = null;
 * ChannelSftp channelSftp = null; try { JSch jsch = new JSch(); session =
 * jsch.getSession(user, host, port); session.setPassword(password);
 * 
 * Properties config = new Properties(); config.put("StrictHostKeyChecking",
 * "no"); session.setConfig(config);
 * 
 * LOG.info("Connecting to SFTP server..."); session.connect();
 * LOG.info("Connected to SFTP server.");
 * 
 * channelSftp = (ChannelSftp) session.openChannel("sftp");
 * channelSftp.connect();
 * 
 * channelSftp.put(new FileInputStream(localFilePath), remotePath +
 * "/data.csv"); LOG.info("File uploaded successfully to {}", remotePath);
 * 
 * } catch (Exception e) { LOG.error("SFTP upload error: {}", e.getMessage(),
 * e); } finally { if (channelSftp != null) { channelSftp.disconnect();
 * LOG.info("SFTP channel disconnected."); } if (session != null) {
 * session.disconnect(); LOG.info("SFTP session disconnected."); } } } }
 */