package net.youssfi.customerdataservice.web;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import net.youssfi.customerdataservice.entities.Customer;
import net.youssfi.customerdataservice.mapper.CustomerMappper;
import net.youssfi.customerdataservice.repository.CustomerReposotory;
import net.youssfi.customerdataservice.stub.CustomerServiceGrpc;
import net.youssfi.customerdataservice.stub.CustomerServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {
    @Autowired
    private CustomerReposotory customerReposotory;
    @Autowired
    private CustomerMappper customerMappper;
    @Override
    public void getALlCustomers(CustomerServiceOuterClass.GetALlCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomersResponse> responseObserver) {
        List<Customer> customerList = customerReposotory.findAll();
        List<CustomerServiceOuterClass.Customer> grpcCustomers =
                customerList.stream()
                .map(customerMappper::fromCustomer).collect(Collectors.toList());
        CustomerServiceOuterClass.GetCustomersResponse customersResponse =
                CustomerServiceOuterClass.GetCustomersResponse.newBuilder()
                        .addAllCustomer(grpcCustomers)
                        .build();
        super.getALlCustomers(request, responseObserver);
        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        Customer customerEntity=customerReposotory.findById(request.getCustomerId()).get();
        CustomerServiceOuterClass.GetCustomerByIdResponse response =
                CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
                        .setCustomer(customerMappper.fromCustomer(customerEntity))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
        CustomerServiceOuterClass.CustomerRequest customerRequest = request.getCustomer();
        Customer customer = customerMappper.fromGrpcCustomerRequest(customerRequest);
        Customer savedCustomer = customerReposotory.save(customer);
        CustomerServiceOuterClass.SaveCustomerResponse response=
                CustomerServiceOuterClass.SaveCustomerResponse.newBuilder()
                        .setCustomer(customerMappper.fromCustomer(savedCustomer))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
