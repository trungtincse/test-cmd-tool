/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 */
package com.vng.zing.testfeature.servers;

import com.vng.zing.thriftserver.ThriftServers;

/**
 *
 * @author namnq
 */
public class TServers {

	public boolean setupAndStart() {
		ThriftServers servers = new ThriftServers("main");
		return servers.start();
	}
}
