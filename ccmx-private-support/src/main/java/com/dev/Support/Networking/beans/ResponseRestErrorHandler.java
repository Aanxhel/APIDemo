package com.dev.Support.Networking.beans;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRestErrorHandler implements ResponseErrorHandler {

	private String error;
    private String codigo;

    public ResponseRestErrorHandler() {
        error = "Ocurrio un error en la petición";
        codigo = "500";
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().value() != 200 && response.getStatusCode().value() != 204 && response.getStatusCode().value() != 201) {
            String result = IOUtils.toString(response.getBody(), "UTF-8");
            error = result;
            codigo = String.valueOf(response.getStatusCode().value());
            return true;
        }

        return false;
    }

}
