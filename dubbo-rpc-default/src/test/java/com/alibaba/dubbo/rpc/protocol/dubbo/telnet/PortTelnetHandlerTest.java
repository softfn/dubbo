/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.rpc.protocol.dubbo.telnet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.exchange.ExchangeClient;
import com.alibaba.dubbo.remoting.exchange.Exchangers;
import com.alibaba.dubbo.remoting.telnet.TelnetHandler;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.alibaba.dubbo.rpc.protocol.dubbo.support.DemoService;
import com.alibaba.dubbo.rpc.protocol.dubbo.support.ProtocolUtils;

/**
 * PortTelnetHandlerTest.java
 * 
 * @author tony.chenl
 */
public class PortTelnetHandlerTest {

    private static TelnetHandler port = new PortTelnetHandler();
    private Invoker<DemoService> mockInvoker;

    @SuppressWarnings("unchecked")
    @Before
    public void before() {
        mockInvoker = EasyMock.createMock(Invoker.class);
        EasyMock.expect(mockInvoker.getInterface()).andReturn(DemoService.class).anyTimes();
        EasyMock.expect(mockInvoker.getUrl()).andReturn(URL.valueOf("dubbo://127.0.0.1:20887/demo")).anyTimes();
        EasyMock.replay(mockInvoker);
        DubboProtocol.getDubboProtocol().export(mockInvoker);
    }

    @After
    public void after() {
        EasyMock.reset(mockInvoker);
        ProtocolUtils.closeAll();
    }

    @Test
    public void testListClient() throws RemotingException {
        ExchangeClient client1 = Exchangers.connect("dubbo://127.0.0.1:20887/demo");
        ExchangeClient client2 = Exchangers.connect("dubbo://127.0.0.1:20887/demo");
        String result = port.telnet(null, "-l 20887");
        assertTrue(result.contains(client1.getLocalAddress().toString()));
        assertTrue(result.contains(client2.getLocalAddress().toString()));

    }

    @Test
    public void testListDetail() throws RemotingException {
        String result = port.telnet(null, "-l");
        assertEquals("dubbo://" + NetUtils.getLocalHost() + ":20887", result);
    }

    @Test
    public void testListAllPort() throws RemotingException {
        String result = port.telnet(null, "");
        assertEquals("20887", result);
    }

    @Test
    public void testErrorMessage() throws RemotingException {
        String result = port.telnet(null, "a");
        assertEquals("Illegal port a, must be integer.", result);
    }

    @Test
    public void testNoPort() throws RemotingException {
        String result = port.telnet(null, "-l 20880");
        assertEquals("No such port 20880", result);
    }

}