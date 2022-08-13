// package com.example.demo.grpc;

// import java.math.BigDecimal;
// import java.util.Map;

// import com.example.demo.api.proto.AccountDetailsRequest;
// import com.example.demo.api.proto.BalanceResponse;
// import com.example.demo.api.proto.MoneyTransferRequest;
// import com.example.demo.api.proto.MoneyTransferResponse;
// import com.example.demo.api.proto.TransferStatus;
// import com.example.demo.api.proto.WalletGrpcServiceGrpc;
// import com.example.demo.dto.Balance;
// import com.example.demo.dto.MoneyTransferData;
// import com.example.demo.dto.MoneyTransferResult;
// import com.example.demo.dto.TransferStatusType;
// import com.example.demo.service.WalletService;

// import io.grpc.stub.StreamObserver;
// import lombok.RequiredArgsConstructor;
// import net.devh.boot.grpc.server.service.GrpcService;

// @GrpcService
// @RequiredArgsConstructor
// public class WalletGrpcService extends WalletGrpcServiceGrpc.WalletGrpcServiceImplBase {
//     private final WalletService service;
//     private final Map<TransferStatusType, TransferStatus> convertMap = Map.of(
//             TransferStatusType.FAIL, TransferStatus.FAILURE,
//             TransferStatusType.OK, TransferStatus.SUCCESS,
//             TransferStatusType.NOT_ENOUGH_MONEY, TransferStatus.NOT_ENOUGH_MONEY
//     );

//     @Override
//     public void reserveMoney(final MoneyTransferRequest request, final StreamObserver<MoneyTransferResponse> responseObserver) {
//         responseObserver.onNext(convertTransferStatus(service.creditAccount(builderTransferData(request))));
//         responseObserver.onCompleted();
//     }

//     private static MoneyTransferData builderTransferData(final MoneyTransferRequest request) {
//         return new MoneyTransferData(request.getAccountId(), BigDecimal.valueOf(request.getAmount()), request.getReference());
//     }

//     @Override
//     public void addMoney(final MoneyTransferRequest request, final StreamObserver<MoneyTransferResponse> responseObserver) {
//         responseObserver.onNext(convertTransferStatus(service.debitAccount(builderTransferData(request))));
//         responseObserver.onCompleted();
//     }

//     private MoneyTransferResponse convertTransferStatus(final MoneyTransferResult result) {
//         return MoneyTransferResponse.newBuilder()
//                 .setAccountId(result.accountId())
//                 .setStatus(convertStatusType(result.status()))
//                 .setMessage(result.message())
//                 .build();
//     }

//     private TransferStatus convertStatusType(final TransferStatusType status) {
//         return convertMap.get(status);
//     }

//     @Override
//     public void balance(final AccountDetailsRequest request, final StreamObserver<BalanceResponse> responseObserver) {
//         responseObserver.onNext(converBalance(service.balance(request.getAccountId())));
//         responseObserver.onCompleted();
//     }

//     private BalanceResponse converBalance(final Balance balance) {
//         return BalanceResponse.newBuilder()
//                 .setAccountId(balance.accountId())
//                 .setAmount(balance.amount().longValue())
//                 .build();
//     }
// }
