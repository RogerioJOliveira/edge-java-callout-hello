package com.apigee.callout;

import java.util.Map;

import com.apigee.flow.execution.spi.Execution;
import com.apigee.flow.execution.ExecutionContext;
import com.apigee.flow.execution.ExecutionResult;
import com.apigee.flow.message.MessageContext;

public class Hello implements Execution {

	private static String PROPERTY_NAME = "hello-name";
	private static String DEBUG_MESSAGE = "HelloDebugMessage";
	private static String MESSAGE = "HelloMessage";
	private static String NA = "NA";

	private Map<String, String> properties;
	private String name;
	private MessageContext msgContext;
	private ExecutionContext execContext;

	public Hello(Map<String, String> properties) {
		this.properties = properties;
	}

	public ExecutionResult execute(MessageContext msgContext, ExecutionContext execContext) {
		try {
			this.init(msgContext, execContext);
			this.validateProperties();
			this.msgContext.setVariable(MESSAGE, "Hello " + this.name + "!");
			return ExecutionResult.SUCCESS;
		} catch (Exception e) {
			this.msgContext.setVariable(MESSAGE, NA);
			return ExecutionResult.ABORT;
		}
	}

	private void init(MessageContext msgContext, ExecutionContext execContext) throws Exception {
		this.msgContext = msgContext;
		this.execContext = execContext;
		this.name = this.resolveVariable(this.properties.get(PROPERTY_NAME));
		this.debugMessage("Properties", this.printMap(this.properties));
	}

	private String resolveVariable(String variable) {
		if (isEmpty(variable)) return variable;
		if (variable.startsWith("{") && variable.endsWith("}")) {
			return this.msgContext.getVariable(variable.substring(1, variable.length() - 1));
		} else {
			return variable;
		}
	}

	private void validateProperties() throws Exception {
		if (isEmpty(this.name)) {
			throw new Exception("Error: hello-name property is empty");
		}
	}

	private void debugMessage(String key, String message) {
		this.msgContext.setVariable(DEBUG_MESSAGE + key, key + "____" + message);
		this.msgContext.setVariable(DEBUG_MESSAGE, key + "____" + message);
	}

	private boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}

	private String printMap(Map<String, String> map) {
		String result = "";
		for (Map.Entry entry : map.entrySet()) {
			result += entry.getKey() + "=" + entry.getValue() + "&";
		}
		return result;
	}

}

