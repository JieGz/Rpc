package com.qiyue.client.service;

import com.qiyue.client.connect.TcpClient;
import com.qiyue.entity.User;
import com.qiyue.protocol.RpcProtocol;
import com.qiyue.util.ByteConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int addUser(User userInfo) throws Exception {
		//初始化客户端连接
		TcpClient client = TcpClient.GetInstance();
		try {
			client.init();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("init rpc client error");
		}

		//构造请求数据
		RpcProtocol rpcReq = new RpcProtocol();
		rpcReq.setCmd(RpcProtocol.CMD_CREATE_USER);
		rpcReq.setVersion(0x01);
		rpcReq.setMagicNum(0x20110711);
		byte[] body = rpcReq.userInfoToByteArray(userInfo);
		rpcReq.setBodyLen(body.length);
		rpcReq.setBody(body);

		//序列化
		byte[] reqData = rpcReq.generateByteArray();

		//发送请求
		client.sendData(reqData);

		//接收请求结果
		byte[] recvData = client.recvData();

		//反序列化结果
		RpcProtocol rpcResp = new RpcProtocol();
		rpcResp.byteArrayToRpcHeader(recvData);
		int ret = ByteConverter.bytesToInt(rpcResp.getBody(), 0);
		return ret;
	}
}
