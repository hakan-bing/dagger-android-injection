package com.base.di.module;

import com.base.BuildConfig;
import com.base.data.network.ApiService;
import com.base.data.network.TLSSocketFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(String baseUrl, OkHttpClient unsafeOkHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(unsafeOkHttpClient)
                .build();
    }

    @Singleton
    @Provides
    String provideBaseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Singleton
    @Provides
    OkHttpClient provideUnsafeOkHttpClient(Interceptor httpLoggingInterceptor, Interceptor headerInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor) /** Log request and response. */
                .addInterceptor(headerInterceptor) /** Add header to request. */
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideSafeOkHttpClient(Interceptor httpLoggingInterceptor, Interceptor headerInterceptor) {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        /**
         * Set the SHA256 hash obtained from the Certificate
         *
         * Run this command to get the SHA256 fingerprint
         * openssl s_client -connect api.github.com:443 | openssl x509 -pubkey -noout | openssl rsa -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64
         *
         */
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("HOST", "PUBLIC_KEY_HASH")
                .build();
        /**
         * Force the connection to use only TLS v.1.2 avoiding the fallback to older version to avoid vulnerabilities
         */
        ConnectionSpec.Builder connectionSpecBuilder = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS);
        connectionSpecBuilder.tlsVersions(TlsVersion.TLS_1_2).build();

        TLSSocketFactory tlsSocketFactory;
        try {
            tlsSocketFactory = new TLSSocketFactory();
            httpBuilder.sslSocketFactory(tlsSocketFactory, tlsSocketFactory.systemDefaultTrustManager());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return httpBuilder.certificatePinner(certificatePinner)
                .connectionSpecs(Collections.singletonList(connectionSpecBuilder.build()))
                .addInterceptor(httpLoggingInterceptor) /** Log request and response. */
                .addInterceptor(headerInterceptor) /** Add header to request. */
                .build();
    }

    @Singleton
    @Provides
    Interceptor provideHttpLogginInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Singleton
    @Provides
    Interceptor provideHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                Request request = original.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };
    }
}
