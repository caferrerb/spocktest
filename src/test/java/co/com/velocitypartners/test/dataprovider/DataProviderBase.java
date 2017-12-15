package co.com.velocitypartners.test.dataprovider;

import org.testng.ITestContext;

public interface DataProviderBase {

	public Object[][] data(ITestContext ctx);
}
