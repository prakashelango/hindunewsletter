package com.hindu.newslettersubscription.provider;

import com.hindu.newslettersubscription.common.Promise;
import com.hindu.newslettersubscription.common.React;
import com.hindu.newslettersubscription.common.TwoTrack;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Arrays;

public class DeferredResultProvider {

    public static <T> DeferredResult<ResponseEntity<T>> createDeferredResultTwoTrack(final Promise<TwoTrack<T>> task,
                                                                                     final HttpStatus httpStatus) {
        final DeferredResult<ResponseEntity<T>> deferredResult = new DeferredResult<>();
        task.success((t) -> {

            t.onSuccess(v -> deferredResult.setResult(new ResponseEntity<>(v, httpStatus)));
            t.onFailure(e -> {
                System.out.println(e.getErrorCode() + " : " + e.getHttpStatus() + " : " + Arrays.asList(e.getParams()));
                for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                    System.out.println(stackTraceElement);
                }
                deferredResult.setErrorResult(new ResponseEntity<>(e, e.getHttpStatus()));
            });
        }).failure(deferredResult::setErrorResult);
        return deferredResult;
    }

    public static <T> DeferredResult<ResponseEntity<T>> createDeferredResult(final Promise<T> task,
                                                                             final HttpStatus httpStatus) {
        return createDeferredResultTwoTrack(React.of(task).then(TwoTrack::of).getPromise(), httpStatus);
    }

}
