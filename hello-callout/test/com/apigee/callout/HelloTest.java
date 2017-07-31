package com.apigee.callout;

import java.util.Map;
import java.util.HashMap;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import mockit.Mock;
import mockit.MockUp;

import com.apigee.flow.execution.ExecutionContext;
import com.apigee.flow.execution.ExecutionResult;
import com.apigee.flow.message.MessageContext;

public class HelloTest {

	private static String MESSAGE = "HelloMessage";
	private static String NA = "NA";
	private static String PROPERTY_HELLO_NAME = "hello-name";

	private MessageContext mctx;
	private ExecutionContext ectx;


	private static MessageContext mockMessageContext() {
		return new MockUp<MessageContext>() {
            private Map<String, String> variables = new HashMap<String, String>();
            @Mock public String getVariable(String name) {
                return variables.get(name);
            }
            @Mock public boolean setVariable(String name, Object value) {
                variables.put(name, (String) value);
                return true;
            }
            @Mock public boolean removeVariable(String name) {
                if (variables.containsKey(name)) {
                    variables.remove(name);
                }
                return true;
            }
        }.getMockInstance();
	}

	private static ExecutionContext mockExecutionContext() {
		return new MockUp<ExecutionContext>(){ }.getMockInstance();
	}

	@Before
	public void setup() {
        this.mctx = mockMessageContext();
        this.ectx = mockExecutionContext();
	}

	@Test
	public void canary() {
		assertTrue(true);
	}	

	@Test
	public void testEmptyProperties() {
		Hello hello = new Hello(new HashMap<String, String>());
		ExecutionResult result = hello.execute(this.mctx, this.ectx);
		assertEquals(ExecutionResult.ABORT, result);
	}

	@Test
	public void testNullName() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(PROPERTY_HELLO_NAME, null);
		Hello hello = new Hello(properties);
		ExecutionResult result = hello.execute(this.mctx, this.ectx);
		assertEquals(ExecutionResult.ABORT, result);
		assertEquals(NA, this.mctx.getVariable(MESSAGE));
	}

	@Test
	public void testEmptyName() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(PROPERTY_HELLO_NAME, "");
		Hello hello = new Hello(properties);
		ExecutionResult result = hello.execute(this.mctx, this.ectx);
		assertEquals(ExecutionResult.ABORT, result);
		assertEquals(NA, this.mctx.getVariable(MESSAGE));
	}

	@Test
	public void testAName() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(PROPERTY_HELLO_NAME, "Po");
		Hello hello = new Hello(properties);
		ExecutionResult result = hello.execute(this.mctx, this.ectx);
		assertEquals(ExecutionResult.SUCCESS, result);
		assertEquals("Hello Po!", this.mctx.getVariable(MESSAGE));
	}

}