/** Copyright 2019 Mek Global Limited. */
package org.knowm.xchange.kucoin.service;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.kucoin.dto.request.CreateAccountRequest;
import org.knowm.xchange.kucoin.dto.request.InnerTransferRequest;
import org.knowm.xchange.kucoin.dto.response.*;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("api")
@Produces(MediaType.APPLICATION_JSON)
public interface AccountAPI {

  /**
   * Get a list of accounts.
   *
   * <p>Your accounts are separate from your KuCoin accounts. See the Deposits section for
   * documentation on how to deposit funds to begin trading.
   *
   * @param currency The code of the currency
   * @param type Account type:，"main" or "trade"
   * @return The accounts.
   * @throws IOException on socket errors.
   * @throws KucoinApiException when errors are returned from the exchange.
   */
  @GET
  @Path("v1/accounts")
  KucoinResponse<List<AccountBalancesResponse>> getAccountList(
      @HeaderParam(APIConstants.API_HEADER_KEY) String apiKey,
      @HeaderParam(APIConstants.API_HEADER_SIGN) ParamsDigest signature,
      @HeaderParam(APIConstants.API_HEADER_TIMESTAMP) SynchronizedValueFactory<Long> nonce,
      @HeaderParam(APIConstants.API_HEADER_PASSPHRASE) String apiPassphrase,
      @QueryParam("currency") String currency,
      @QueryParam("type") String type)
      throws IOException;

  @POST
  @Path("v1/accounts")
  @Consumes(MediaType.APPLICATION_JSON)
  KucoinResponse<Void> createAccount(
      @HeaderParam(APIConstants.API_HEADER_KEY) String apiKey,
      @HeaderParam(APIConstants.API_HEADER_SIGN) ParamsDigest signature,
      @HeaderParam(APIConstants.API_HEADER_TIMESTAMP) SynchronizedValueFactory<Long> nonce,
      @HeaderParam(APIConstants.API_HEADER_PASSPHRASE) String apiPassphrase,
      CreateAccountRequest req)
      throws IOException;

  @POST
  @Path("v2/accounts/inner-transfer")
  @Consumes(MediaType.APPLICATION_JSON)
  KucoinResponse<InternalTransferResponse> innerTransfer(
      @HeaderParam(APIConstants.API_HEADER_KEY) String apiKey,
      @HeaderParam(APIConstants.API_HEADER_SIGN) ParamsDigest signature,
      @HeaderParam(APIConstants.API_HEADER_TIMESTAMP) SynchronizedValueFactory<Long> nonce,
      @HeaderParam(APIConstants.API_HEADER_PASSPHRASE) String apiPassphrase,
      InnerTransferRequest req)
      throws IOException;

  @GET
  @Path("v1/accounts/{accountId}/ledgers")
  KucoinResponse<Pagination<AccountLedgersResponse>> getAccountLedgers(
      @HeaderParam(APIConstants.API_HEADER_KEY) String apiKey,
      @HeaderParam(APIConstants.API_HEADER_SIGN) ParamsDigest signature,
      @HeaderParam(APIConstants.API_HEADER_TIMESTAMP) SynchronizedValueFactory<Long> nonce,
      @HeaderParam(APIConstants.API_HEADER_PASSPHRASE) String apiPassphrase,
      @PathParam("accountId") String accountId,
      @QueryParam("startAt") Long startAt,
      @QueryParam("endAt") Long endAt,
      @QueryParam("pageSize") Integer pageSize,
      @QueryParam("currentPage") Integer currentPage)
      throws IOException;

  @GET
  @Path("v1/accounts/{accountId}")
  KucoinResponse<AccountResponse> getAccount(
      @HeaderParam(APIConstants.API_HEADER_KEY) String apiKey,
      @HeaderParam(APIConstants.API_HEADER_SIGN) ParamsDigest signature,
      @HeaderParam(APIConstants.API_HEADER_TIMESTAMP) SynchronizedValueFactory<Long> nonce,
      @HeaderParam(APIConstants.API_HEADER_PASSPHRASE) String apiPassphrase,
      @PathParam("accountId") String accountId)
      throws IOException;
}
