package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import java.util.Objects;

import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.core.body.Body;
import io.gatling.core.body.StringBody;
import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.CheckMaterializer;
import io.gatling.http.Predef;
import io.gatling.http.action.HttpRequestActionBuilder;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.request.builder.HttpRequestBuilder;

import com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper.CheckBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class HttpRequestBuilderWrapper extends RequestBuilderWrapper<HttpRequestBuilder, HttpRequestBuilderWrapper> implements ActionBuilderSupplier {

    private final HttpRequestBuilder httpRequestBuilder;

    public HttpRequestBuilderWrapper(HttpRequestBuilder httpRequestBuilder) {
        this.httpRequestBuilder = Objects.requireNonNull(httpRequestBuilder);
    }

    @Override
    public HttpRequestBuilder get() {
        return httpRequestBuilder;
    }

    @Override
    protected HttpRequestBuilderWrapper newInstance(HttpRequestBuilder newStructure) {
        return new HttpRequestBuilderWrapper(newStructure);
    }

    public HttpRequestBuilderWrapper formParam(String name, Object value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);

        return newInstance(httpRequestBuilder.formParam(Expression.of(name), Expression.of(value)));
    }

    public HttpRequestBuilderWrapper body(String body) {
        Objects.requireNonNull(body);

        Body stringBody = new StringBody(Expression.of(body), GatlingConfigurationSupplier.GATLING_CONFIGURATION);
        return newInstance(httpRequestBuilder.body(stringBody));
    }

    public HttpRequestBuilderWrapper check(CheckBuilderWrapper checkBuilderWrapper) {
        Objects.requireNonNull(checkBuilderWrapper);

        CheckBuilder<?, ?, ?> checkBuilder = checkBuilderWrapper.get();
        CheckMaterializer/*<CssCheckType, HttpCheck , Response, NodeSelector>*/ materializer = checkBuilderWrapper.getCheckMaterializer();
        HttpCheck httpCheck = Predef.checkBuilder2HttpCheck(checkBuilder, materializer);
        return check(httpCheck);
    }

    public HttpRequestBuilderWrapper check(HttpCheck httpCheck) {
        Objects.requireNonNull(httpCheck);

        return newInstance(httpRequestBuilder.check(ScalaHelper.map(httpCheck)));
    }

    @Override
    public ActionBuilder toActionBuilder() {
        return new HttpRequestActionBuilder(httpRequestBuilder);
    }
}
