package helidon;

import io.helidon.config.Config;
import io.helidon.config.internal.ClasspathConfigSource;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;

import java.util.concurrent.TimeUnit;

/**
 * @author yihang.lv 2018/9/30、9:44
 */
public class HelidonStarter {
    public static void main(String[] args) {
        try {
            //服务配置
            ServerConfiguration serverConfiguration = ServerConfiguration.fromConfig(Config.builder().sources(
                    ClasspathConfigSource.from(null)
            ).build());
            //路由配置
            Routing routing = Routing.builder().any((req, res) -> res.send("it works!")).build();
            WebServer webServer = WebServer.create(serverConfiguration,routing)
                    //start the server
                    .start()
                    .toCompletableFuture().get(10, TimeUnit.SECONDS);
            //The server is bound to a random free port.
            System.out.println("server start at : http://localhost:"+webServer.port());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
