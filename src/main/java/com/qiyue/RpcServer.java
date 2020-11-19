package com.qiyue;


import com.qiyue.server.connect.TcpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 下一步是接入注册中心,先接入Zookeeper,然后再接入Nacos
 */
public class RpcServer {

	private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);
	private static final int SERVER_LISTEN_PORT = 58885;

	public static void main(String[] args) throws Exception {
		Thread tcpServerThread = new Thread("tcpServer") {
			public void run() {
				TcpServer tcpServer = new TcpServer(SERVER_LISTEN_PORT);
				try {
					tcpServer.start();
				} catch (Exception e) {
					logger.info("TcpServer start exception: " + e.getMessage());
				}
			}
		};
		tcpServerThread.start();
		tcpServerThread.join();
	}
}