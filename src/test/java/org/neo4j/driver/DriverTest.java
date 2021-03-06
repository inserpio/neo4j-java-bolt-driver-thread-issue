package org.neo4j.driver;
import static org.neo4j.driver.v1.Config.build;

import org.junit.Assert;
import org.junit.Test;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class DriverTest {

	@Test
	public void multipleDriverInstances() {

		for (int i = 0; i < 4000; i++) {
			
			Config.ConfigBuilder builder = build();
			builder = builder.withEncryptionLevel(Config.EncryptionLevel.NONE);
			Config config = builder.toConfig();
			
			Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "neo4j" ), config );

			try (Session s = driver.session()) {
				System.out.println(i);
			}
			catch (Exception e) {
			 Assert.fail(e.getMessage());
			}

			driver.close();
		}
		
		System.out.println("done.");
	}
}
