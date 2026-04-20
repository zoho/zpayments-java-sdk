package com.zoho.payments.net;

public interface HttpClientInterface
{
    ZohoResponse execute(ZohoRequest request);

    default void close() {}
}
