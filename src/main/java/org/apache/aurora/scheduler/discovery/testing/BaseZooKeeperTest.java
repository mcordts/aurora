/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.aurora.scheduler.discovery.testing;

import org.apache.aurora.common.testing.TearDownTestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * A base-class for in-process zookeeper tests.
 */
public abstract class BaseZooKeeperTest extends TearDownTestCase {

  private ZooKeeperTestServer zkTestServer;

  @Rule
  public TemporaryFolder tmpFolder = new TemporaryFolder();

  @Before
  public final void setUp() throws Exception {
    zkTestServer = new ZooKeeperTestServer(tmpFolder.newFolder(), tmpFolder.newFolder());
    addTearDown(zkTestServer::stop);
    zkTestServer.startNetwork();
  }

  /**
   * Returns the running in-process ZooKeeper server.
   *
   * @return The in-process ZooKeeper server.
   */
  protected final ZooKeeperTestServer getServer() {
    return zkTestServer;
  }

  /**
   * Returns the current port to connect to the in-process zookeeper instance.
   */
  protected final int getPort() {
    return getServer().getPort();
  }
}
