package com.zohopayments.net;

public interface HttpClientInterface
{
    ZohoResponse execute(ZohoRequest request);

    default void close() {}
}
