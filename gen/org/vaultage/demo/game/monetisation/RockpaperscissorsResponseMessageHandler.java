package org.vaultage.demo.game.monetisation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

import org.vaultage.core.ResponseMessageHandler;
import org.vaultage.core.Vault;
import org.vaultage.core.Vaultage;
import org.vaultage.core.VaultageMessage;

import com.google.gson.reflect.TypeToken;

public class RockpaperscissorsResponseMessageHandler extends ResponseMessageHandler {

	@Override
	public void process(VaultageMessage message, String senderPublicKey, Vault vault) throws Exception {
	
		// 1. get the remote operation called
		String operation = message.getOperation();
		
		// 2. Get the remote vault's type/class and return type/class
		String remoteVaultClassName = message.getRemoteVaultType();
		String returnValueClassName = message.getReturnType();
		
		// 3. Create remote vault object using its class/type above
		Class<?> remoteClass = Class.forName(remoteVaultClassName);
		Class<?>[] parameterClasses = { Vault.class, String.class };
		Constructor<?> constructor = remoteClass.getDeclaredConstructor(parameterClasses);
		Object[] parameterValues = { vault, senderPublicKey };
		Object senderObject = constructor.newInstance(parameterValues);
		
		// 4. Get the class and type of the return value using its class/type above
		String[] types = returnValueClassName.split("<|>|,\\s");
		Class<?> returnClass;
		Type returnType;
		if (types.length > 1) {
			returnClass = Class.forName(types[0]);
			Class<?>[] typeArguments = new Class<?>[types.length - 1];
			for (int i = 1; i < types.length; i++) {
				typeArguments[i - 1] = Class.forName(types[i]);
			}
			returnType = TypeToken.getParameterized(returnClass, typeArguments).getType();
		} else {
			returnClass = Class.forName(returnValueClassName);
			returnType = (Type) returnClass;
		}
		
		// 5. deserialise the return value using the class/type above
		Object returnResult = Vaultage.deserialise(message.getValue("result"), returnType);
		
		// 6. get the response handler of the operation called
		String capitalisedOperation = operation.replaceFirst(operation.substring(0, 1),
		operation.substring(0, 1).toUpperCase());
		String packageName = this.getClass().getPackage().getName();
		Class<?> responseHandlerType = null;
		try {
			responseHandlerType = Class.forName(packageName + "." + capitalisedOperation + "ResponseHandler");
		} catch (Exception e) {
			packageName = Vault.class.getPackage().getName();
			responseHandlerType = Class.forName(packageName + "." + capitalisedOperation + "ResponseHandler");
		}
		Method getResponseHandlerMethod = vault.getClass().getMethod("getOperationResponseHandler", Class.class);
		
		// 7. get return object of that operation called
		Object responseHandlerObject = getResponseHandlerMethod.invoke(vault, responseHandlerType);
		
		// 8. get the response handler interface of the OperationResponseHander object
		Class<? extends Object> responseHandlerClass = responseHandlerObject.getClass();
		//		Class<?> getResponseHandlerInterface = geResponseHandlerClass.getInterfaces()[0];
		
		//9. set the result value
		//		Method setResult = responseHandlerClass.getMethod("setResult", Object.class);
		Method setResult = responseHandlerClass.getMethod("addResult", String.class, Object.class);
		setResult.invoke(responseHandlerObject, message.getToken(), returnResult);
		
		// 10. get the run method of the OperationResponseHander object
		Method runMethod = null;
		//		for (Method m : getResponseHandlerInterface.getMethods()) {
			for (Method m : responseHandlerClass.getMethods()) {
				Parameter[] p = m.getParameters();
				if (m.getName().equals("run") && p.length == 4 && p[0].getType().equals(vault.getClass()) //
				&& p[1].getType().equals(senderObject.getClass()) //
				&& p[2].getType().equals(String.class) //
				&& p[3].getType().equals(returnClass)) {
					runMethod = m;
					break;
				}
			}
			
			//		runMethod = getResponseHandlerInterface.getMethod("run", vault.getClass(), senderObject.getClass(),
			//					String.class, returnClass);
			if (runMethod == null)
			//			runMethod = getResponseHandlerInterface.getMethod("run", Vault.class, senderObject.getClass(), String.class,
			runMethod = responseHandlerClass.getMethod("run", Vault.class, senderObject.getClass(), String.class,
			returnClass);
			Object[] runParameterValues = { vault, senderObject, message.getToken(), returnResult };
			
			// 11. execute the run method
			runMethod.invoke(responseHandlerObject, runParameterValues);
			System.console();
		}
	}
