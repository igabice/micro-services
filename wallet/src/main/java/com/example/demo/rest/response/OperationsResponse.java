package com.example.demo.rest.response;

import java.util.List;

public record OperationsResponse(
        List<Operation> operations,
        boolean hasMore
) {
}
