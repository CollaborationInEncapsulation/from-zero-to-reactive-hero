package com.example.cryptotrading;

import com.example.cryptotrading.external.CryptoConnectionHolder;
import com.example.cryptotrading.utils.JsonUtils;
import com.example.cryptotrading.utils.NettyUtils;
import com.example.cryptotrading.utils.StatisticUtils;
import reactor.ipc.netty.http.server.HttpServer;

public class Part3CryptoPlatform {
    public static void main(String[] args) {
        HttpServer.create(8080)
                .startRouterAndAwait(hsr -> hsr
                        .ws(
                                "/stream",
                                (req, res) -> StatisticUtils
                                        .transform(
                                                CryptoConnectionHolder.get(),
                                                NettyUtils.prepareInput(req)
                                                        //TODO map to long
                                                        .map(Long::valueOf)
                                        )
                                        .map(JsonUtils::writeAsString)
                                        // TODO: Add backpressure handling
                                        // It is possible that writing data to output may be slower than rate of
                                        // incoming output data
                                        .transform(NettyUtils.prepareOutbound(res))
                        )
                        .file("**", ClassLoader.getSystemResource("index.html").getPath()));
    }


}
